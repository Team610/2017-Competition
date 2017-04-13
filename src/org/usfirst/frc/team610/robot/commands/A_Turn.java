package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_Turn extends Command {

	private double angle;
	private double time;
	private DriveTrain driveTrain;
	private int counter;

	private PID gyroPID;

	public A_Turn(double angle, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.angle = angle;
		this.time = time;
		driveTrain = DriveTrain.getInstance();
		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.resetAngle();
		setTimeout(time);
		counter = 0;
		driveTrain = DriveTrain.getInstance();
		driveTrain.resetSensors();
		System.out.println("Starting Turn");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double value = gyroPID.getValue(driveTrain.getAngle(), angle, 0);
		driveTrain.setLeft(-value);
		driveTrain.setRight(value);
		if(Math.abs(angle) + .5 < Math.abs(driveTrain.getAngle()) && Math.abs(driveTrain.getAngle()) < Math.abs(angle) - .5 ){
			counter ++;
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return counter > 10 || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
