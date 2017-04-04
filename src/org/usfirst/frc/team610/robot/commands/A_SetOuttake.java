package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_SetOuttake extends Command {

	private GearIntake intake;
	private boolean outtake;

	public A_SetOuttake(boolean outtake) {
		intake = GearIntake.getInstance();
		this.outtake = outtake;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(.5);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (intake.getPeg()) {
			intake.setOuttake(outtake);
			intake.setLED(true);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
