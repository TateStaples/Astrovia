package frc.team6502.robot.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.controller.ProfiledPIDController
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.team6502.robot.Constants
import kotlin.math.PI

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
    const val PULLEY_RADIUS = 0.025 / 2.0

    val elevatorEncoder = elevator.encoder.apply {
        position = 0.0
        velocityConversionFactor = 1.0 / 10.0 * PULLEY_RADIUS * (2 * PI)  // check this - gear ratio * circumfrance
        positionConversionFactor = 1.0 / 10.0 * PULLEY_RADIUS * (2 * PI)
    }

    val elevatorCtrl = ProfiledPIDController(100.0, 0.0, 2.0, TrapezoidProfile.Constraints(0.1, 0.1)).apply {
        this.setIntegratorRange(0.0,3.0)  // todo: tune this
    }

    const val elevatorPositionMin = -0.5
    const val elevatorPositionMax = 0.5  // 10 cm

    var elevatorSetpoint = elevatorPosition
    var elevatorPosition
        get() = elevatorEncoder.position
        set(value) {
            elevatorSetpoint = value.coerceIn(elevatorPositionMin, elevatorPositionMax)
        }

    override fun periodic() {
        val output = elevatorCtrl.calculate(elevatorPosition, elevatorSetpoint).coerceAtMost(10.0)
        SmartDashboard.putNumber("elevator output", output)
        SmartDashboard.putNumber("elevator pos", elevatorPosition)
        SmartDashboard.putNumber("elevator set", elevatorSetpoint)
        SmartDashboard.putNumber("elevator error", elevatorSetpoint- elevatorPosition)
        elevator.setVoltage(output)
    }

    // intake
    var intakeSpeed
        get() = feeder.appliedOutput
        set(value) {
            feeder.setVoltage(value)  // todo: tune this
        }
}