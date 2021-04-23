package frc.team6502.robot.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.controller.ProfiledPIDController
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.team6502.robot.Constants

object Intake : SubsystemBase() {
    // motors
    private val elevator = CANSparkMax(Constants.ELEVATOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kBrake
        setSmartCurrentLimit(40)
    }

    private val feeder = CANSparkMax(Constants.INTAKE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kCoast
        setSmartCurrentLimit(40)
        inverted = true
    }

    // elevator
    val elevatorEncoder = elevator.encoder.apply {
        position = 0.0
        velocityConversionFactor = 1.0 / 10.0  // check this
        positionConversionFactor = 1.0 / 10.0
    }

    val elevatorCtrl = ProfiledPIDController(30.0, 2.0, 5.0, TrapezoidProfile.Constraints(3.0, 2.0)).apply {
        this.setIntegratorRange(0.0,3.0)  // todo: tune this
    }

    const val elevatorPositionMin = 0.0  // todo: figure these out
    const val elevatorPositionMax = 10.0

    var elevatorPosition
        get() = elevatorEncoder.position
        set(value) {
            var setpoint = value.coerceIn(elevatorPositionMin, elevatorPositionMax)
            val output = elevatorCtrl.calculate(setpoint, value)
            elevator.setVoltage(output)
        }

    // intake
    const val intakePosition = 1.0;

    var intakeSpeed
        get() = feeder.appliedOutput
        set(value) {
            feeder.setVoltage(value)  // todo: tune this
        }
}