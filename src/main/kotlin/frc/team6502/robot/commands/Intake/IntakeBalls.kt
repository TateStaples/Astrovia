package frc.team6502.robot.commands.Intake

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.team6502.robot.subsystems.Intake

class IntakeBalls : CommandBase() {
    init {
        addRequirements(Intake)
    }

    override fun execute() {
        Intake.intakeSpeed = 1.0  // todo: tune
    }

    override fun end(interrupted: Boolean) {
        Intake.intakeSpeed = 0.0
    }

    override fun isFinished(): Boolean {
        return false
    }
}