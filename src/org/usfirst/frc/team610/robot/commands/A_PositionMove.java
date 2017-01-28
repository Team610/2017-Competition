package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves, using PID, to a certain distance in inches.
 */
public class A_PositionMove extends Command {

	// Distance
	private DriveTrain driveTrain;

	double distance;
	double time;

	public A_PositionMove(double distance, double time) {
		driveTrain = DriveTrain.getInstance();
		this.time = time;
		this.distance = -distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(time);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		driveTrain.driveForward(distance);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isTimedOut());
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
