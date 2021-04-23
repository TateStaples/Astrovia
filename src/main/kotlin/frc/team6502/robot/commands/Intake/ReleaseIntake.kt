package frc.team6502.robot.Commands.Intake

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.team6502.robot.subsystems.Intake

class ReleaseIntake : CommandBase() {
    var stage = 0

    init {
        addRequirements(Intake)
    }

    override fun execute() {  // todo: tune all these values
        if (stage == 0) {
            Intake.elevatorPosition = 3.0
            stage += 1
        } else if (stage == 1 && Intake.elevatorPosition >= 2.5) {
            stage += 1
            Intake.elevatorPosition = 1.0
        }
    }

    override fun isFinished(): Boolean {
        val done = stage >= 2
        if (done) {
            stage = 0
        }
        return done
    }
}