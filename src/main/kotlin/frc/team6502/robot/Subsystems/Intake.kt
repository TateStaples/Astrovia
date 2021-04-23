package frc.team6502.robot.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import frc.team6502.robot.Constants

object Intake {
    private val elevator = CANSparkMax(Constants.LEFT_DRIVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kBrake
        setSmartCurrentLimit(40)
    }

    private val feeder = CANSparkMax(Constants.RIGHT_DRIVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kBrake
        setSmartCurrentLimit(40)
        inverted = true
    }
}