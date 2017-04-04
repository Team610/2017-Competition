package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_TurnOpticalFast extends Command {

	private boolean foundTape, done;
	private DriveTrain driveTrain;
	private double turnAngle, turnSpeed;
	private PID gyroPID;
	private int counter;
	private double angle;
	private boolean optical;

	public A_TurnOpticalFast(double time, double turnSpeed, double angle, boolean optical) {
		setTimeout(time);
		this.turnSpeed = turnSpeed;
		counter = 0;

		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		done = false;
		counter = 0;
		driveTrain = DriveTrain.getInstance();
		foundTape = false;
		driveTrain.resetSensors();
		System.out.println("Starting Turn");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if ((driveTrain.getLeftOptical() && driveTrain.getRightOptical() && !foundTape && optical) ||( Math.abs(driveTrain.getAngle()) > 50 && !foundTape)) {
			foundTape = true;
			if (turnSpeed < 0)
				turnAngle = driveTrain.getAngle();
			else
				turnAngle = driveTrain.getAngle();
		} else if (!foundTape) {
			driveTrain.setLeft(turnSpeed);
			driveTrain.setRight(-turnSpeed);
		}
		if(driveTrain.getLeftOptical() && driveTrain.getRightOptical()){
			System.out.println("Found Tape");
		}
		
		if (foundTape) {
			double value = gyroPID.getValue(driveTrain.getAngle(), turnAngle, 0);
			driveTrain.setLeft(-value);
			driveTrain.setRight(value);
			counter++;
		}
		
		if (counter >= 10) {
			done = true;
			System.out.println("A_TurnOptical Finished");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut() || done;
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
