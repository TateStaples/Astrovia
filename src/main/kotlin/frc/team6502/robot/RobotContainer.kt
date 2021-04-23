package frc.team6502.robot

import com.ctre.phoenix.sensors.PigeonIMU
import frc.team6502.robot.subsystems.Drivetrain
 import edu.wpi.first.wpilibj.Joystick
import frc.team6502.kyberlib.input.controller.KXboxController
import frc.team6502.robot.Commands.Intake.IntakeBalls
import kotlin.math.PI

/**
 * Initialize devices and subsystems here
 */
object RobotContainer {

    val joystick = KXboxController(1).apply {
        // steering
        rightX.apply {
            rate = -5 * PI
            expo = 73.0
            deadband = 0.1
        }

        // throttle
        leftY.apply {
            rate = -12.0
            expo = 20.0
            deadband = 0.2
        }

        aButton.whileActiveOnce(IntakeBalls())
    }

//    val pigeon = PigeonIMU(Constants.PIGEON_PORT);

    init {
        // initialize subsystems here:
        Drivetrain
    }
}
