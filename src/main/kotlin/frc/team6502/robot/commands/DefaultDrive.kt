package frc.team6502.robot.commands

import edu.wpi.first.wpilibj.SlewRateLimiter
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.team6502.robot.subsystems.Drivetrain

class DefaultDrive : CommandBase() {

    private val velFilter = SlewRateLimiter(10.0) // meters per second
    private val rotFilter = SlewRateLimiter(200.0)

    init {
        addRequirements(Drivetrain)
    }

    override fun initialize() {
    }

    // TODO implement proper cheesy drive
    override fun execute() {
//        val fwd = RobotContainer.joystick.
//        val turn = RobotContainer.joystick.
//        val speeds = Drivetrain.kinematics.toWheelSpeeds(ChassisSpeeds(velFilter.calculate(fwd.metersPerSecond), 0.0, rotFilter.calculate(turn.radiansPerSecond)))
//        val testSpeed = Drivetrain.kinematics.toWheelSpeeds(ChassisSpeeds(0.5, 0.0, 0.0))
//        if (RobotContainer.joystick.x > 0.5)
        Drivetrain.drive(0.1, 0.1)
    }

    override fun isFinished() = false
}
