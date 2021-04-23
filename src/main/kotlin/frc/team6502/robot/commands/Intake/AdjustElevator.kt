package frc.team6502.robot.commands.Intake

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj2.command.InstantCommand
import frc.team6502.robot.subsystems.Intake

class AdjustElevator (change: Double) : InstantCommand() {
    val change = change

    init {
        addRequirements(Intake)
    }
    override fun execute() {
        Intake.elevatorPosition = Intake.elevatorSetpoint + change
    }
}