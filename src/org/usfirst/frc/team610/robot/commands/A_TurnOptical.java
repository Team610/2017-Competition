package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_TurnOptical extends Command {

	private boolean foundTape, done;
	private DriveTrain driveTrain;
	private GearIntake gearIntake;
	private double turnAngle, turnSpeed;
	private PID gyroPID;
	private int counter;
	private double angle;
	private boolean optical;

	public A_TurnOptical(double time, double turnSpeed, double angle, boolean optical) {
		setTimeout(time);
		this.turnSpeed = turnSpeed;
		counter = 0;
		this.optical = optical;
		this.angle = angle;
		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		done = false;
		counter = 0;
		driveTrain = DriveTrain.getInstance();
		gearIntake = GearIntake.getInstance();
		foundTape = false;
		driveTrain.resetSensors();
		System.out.println("Starting Turn");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if ((driveTrain.getLeftOptical() && driveTrain.getRightOptical() && !foundTape && optical) ||( Math.abs(driveTrain.getAngle()) > Math.abs(angle) && !foundTape)) {
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
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut() || done || (!optical && !gearIntake.isScored());
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setLeft(0);
		driveTrain.setRight(0);
		if(!optical && !gearIntake.isScored()){
			System.out.println("OpticaTurn Swagged");
		}
		System.out.println("A_TurnOptical Finished");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
