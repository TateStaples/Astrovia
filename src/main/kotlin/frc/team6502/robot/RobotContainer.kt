package frc.team6502.robot

import com.ctre.phoenix.sensors.PigeonIMU
import frc.team6502.robot.subsystems.Drivetrain
 import edu.wpi.first.wpilibj.Joystick

/**
 * Initialize devices and subsystems here
 */
object RobotContainer {

     val joystick = Joystick(1)

    val pigeon = PigeonIMU(Constants.PIGEON_PORT);

    init {
        // initialize subsystems here:
        Drivetrain
    }
}
