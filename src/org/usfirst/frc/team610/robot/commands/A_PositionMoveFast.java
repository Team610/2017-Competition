package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_PositionMoveFast extends Command {
	private DriveTrain driveTrain;
	private GearIntake gearIntake;
	private PID leftDrivePID, rightDrivePID;
	private PID gyroPID;

	private double distance;
	private double time;
	private boolean isFinished;
	private int counter;

	public A_PositionMoveFast(double distance, double time, double max) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		driveTrain = DriveTrain.getInstance();
		gearIntake = GearIntake.getInstance();
		this.time = time;
		this.distance = distance;
		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);

		leftDrivePID = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D, -max, max);
		rightDrivePID = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D, -max,
				max);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.resetAngle();
		counter = 0;
		isFinished = false;
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
		double value = gyroPID.getValue(driveTrain.getAngle(), 0, 0);

		double leftPower = leftDrivePID.getValue(driveTrain.getLeftInches(), distance, 0);
		double rightPower = rightDrivePID.getValue(driveTrain.getRightInches(), distance, 0);
		SmartDashboard.putNumber("Left Enc", driveTrain.getLeftInches());
		SmartDashboard.putNumber("Right Enc", driveTrain.getRightInches());
		SmartDashboard.putNumber("LeftPower", leftPower);
		SmartDashboard.putNumber("RightPower", rightPower);
		driveTrain.setLeft(leftPower - value);
		driveTrain.setRight(rightPower + value);
		if (Math.abs(leftDrivePID.getError()) < 1 && Math.abs(rightDrivePID.getError()) < 1) {
			counter++;
		} else {
			counter = 0;
		}
		if (counter >= 20) {
			isFinished = true;
			System.out.println("A_PositionMove Finished");
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isTimedOut()) || isFinished || gearIntake.getPeg();
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
