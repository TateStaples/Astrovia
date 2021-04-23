package frc.team6502.robot

import frc.team6502.robot.subsystems.Drivetrain
import frc.team6502.kyberlib.input.controller.KXboxController
import frc.team6502.robot.commands.Intake.AdjustElevator
import frc.team6502.robot.commands.Intake.Flush
import frc.team6502.robot.commands.Intake.IntakeBalls
import frc.team6502.robot.commands.Intake.ReleaseIntake
import frc.team6502.robot.subsystems.Intake
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
        xButton.whenPressed(AdjustElevator(0.01))
        bButton.whenPressed(AdjustElevator(-0.01))
        yButton.whileActiveOnce(Flush())


    }

//    val pigeon = PigeonIMU(Constants.PIGEON_PORT);

    init {
        // initialize subsystems here:
        Drivetrain
        Intake
    }
}
