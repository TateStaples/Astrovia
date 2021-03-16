package frc.team6502.robot.Commands.Drive


import edu.wpi.first.wpilibj.SlewRateLimiter
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.team6502.robot.RobotContainer
import frc.team6502.robot.Subsystems.Drivetrain

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
        val fwd = RobotContainer.joystick.
        val turn = RobotContainer.joystick.rightX.value.radiansPerSecond
        Drivetrain.
//        val speeds = Drivetrain..toWheelSpeeds(ChassisSpeeds(velFilter.calculate(fwd.metersPerSecond), 0.0, rotFilter.calculate(turn.radiansPerSecond)))
        Drivetrain.drive(speeds)
    }

    override fun isFinished() = false
}
