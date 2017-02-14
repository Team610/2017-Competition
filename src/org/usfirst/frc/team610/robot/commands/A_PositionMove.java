package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
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
	private PID leftDrivePID, rightDrivePID;

	private double distance;
	private double time;

	public A_PositionMove(double distance, double time, double max) {
		driveTrain = DriveTrain.getInstance();
		this.time = time;
		this.distance = distance;
		leftDrivePID = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D, -max, max);
		rightDrivePID = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D, -max, max);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		driveTrain.resetEnc();
		
		PIDConstants.Update();
		driveTrain.resetEnc();
		setTimeout(time);
		driveTrain.setLeft(0);
		driveTrain.setRight(0);
		leftDrivePID.resetPID();
		rightDrivePID.resetPID();
		leftDrivePID.updatePID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		rightDrivePID.updatePID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
	
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftPower = leftDrivePID.getValue(driveTrain.getLeftInches(), distance, 0);
		double rightPower = rightDrivePID.getValue(driveTrain.getRightInches(), distance, 0);
		SmartDashboard.putNumber("Left Enc", driveTrain.getLeftInches());
		SmartDashboard.putNumber("Right Enc", driveTrain.getRightInches());
		SmartDashboard.putNumber("LeftPower", leftPower);
		SmartDashboard.putNumber("RightPower", rightPower);
		driveTrain.setLeft(leftPower);
		driveTrain.setRight(rightPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isTimedOut());
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setLeft(0);
		driveTrain.setRight(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
