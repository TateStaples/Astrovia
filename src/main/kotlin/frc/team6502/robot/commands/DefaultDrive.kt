package frc.team6502.robot.commands

import edu.wpi.first.wpilibj.SlewRateLimiter
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.team6502.robot.RobotContainer
import frc.team6502.robot.subsystems.Drivetrain

class DefaultDrive : CommandBase() {

    private val velFilter = SlewRateLimiter(10.0) // meters per second
    private val rotFilter = SlewRateLimiter(200.0)

    init {
        addRequirements(Drivetrain)
    }

    override fun initialize() {
    }

    override fun execute() {
        val fwd = -RobotContainer.joystick.leftY.value * 5  // todo: make this not bad
        val turn = -RobotContainer.joystick.rightX.value * 5
        val speeds = Drivetrain.kinematics.toWheelSpeeds(ChassisSpeeds(velFilter.calculate(fwd), 0.0, rotFilter.calculate(turn)))
//        val testSpeed = Drivetrain.kinematics.toWheelSpeeds(ChassisSpeeds(1.0, 0.0, 0.0))
//        println(RobotContainer.joystick.x)
//        if (RobotContainer.joystick.x > 0.5 || true) {
//            Drivetrain.drive(fwd*3, fwd*3)
//        Drivetrain.drive(speeds)
        Drivetrain.drive(speeds.leftMetersPerSecond, speeds.rightMetersPerSecond)
//        else Drivetrain.drive(0.0, 0.0)
    }

    override fun isFinished() = false
}
