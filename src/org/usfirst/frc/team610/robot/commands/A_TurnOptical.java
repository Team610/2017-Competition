package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_TurnOptical extends Command {

	private DigitalInput gearOptLeft, gearOptRight;
	private boolean foundTape;
	private DriveTrain driveTrain;
	private double turnDistance, turnSpeed;

	public A_TurnOptical(double time, double turnDistance, double turnSpeed) {
		setTimeout(time);
		this.turnDistance = turnSpeed;
		this.turnSpeed = turnSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain = DriveTrain.getInstance();
		gearOptLeft = new DigitalInput(ElectricalConstants.GEAR_OPTICAL_LEFT);
		gearOptRight = new DigitalInput(ElectricalConstants.GEAR_OPTICAL_RIGHT);
		foundTape = false;
		driveTrain.resetSensors();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (gearOptLeft.get() && gearOptRight.get()) {
			foundTape = true;
			driveTrain.drive(0);
		} else {
			if (driveTrain.getLeftInches() < turnDistance && driveTrain.getRightInches() > turnDistance) {
				driveTrain.setLeft(turnSpeed);
				driveTrain.setRight(-turnSpeed);
			} else {
				driveTrain.setLeft(-turnSpeed);
				driveTrain.setRight(turnSpeed);
			}
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut() || foundTape;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
