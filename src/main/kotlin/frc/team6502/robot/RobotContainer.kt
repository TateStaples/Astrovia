package frc.team6502.robot

import edu.wpi.first.wpilibj.XboxController
import frc.team6502.robot.Subsystems.Drivetrain

/**
 * Initialize devices and subsystems here
 */
object RobotContainer {

    val joystick = XboxController(0)

    init {
        // initialize subsystems here:
        Drivetrain

    }

}