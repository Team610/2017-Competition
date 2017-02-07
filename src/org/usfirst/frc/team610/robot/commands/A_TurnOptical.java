package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_TurnOptical extends Command {

	private boolean foundTape, done = false, right;
	private DriveTrain driveTrain;
	private double turnAngle, turnSpeed;
	private GearIntake intake;
	private PID gyroPID;
	private int counter;

	public A_TurnOptical(double time, double turnSpeed, boolean right) {
		setTimeout(time);
		this.turnSpeed = turnSpeed;
		counter = 0;
		this.right = right;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain = DriveTrain.getInstance();
		foundTape = false;
		intake = GearIntake.getInstance();
		driveTrain.resetSensors();
		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		intake.setIntake(true);
		intake.setOuttake(true);
		if (driveTrain.getLeftOptical() && driveTrain.getRightOptical() && !foundTape) {
			foundTape = true;
			if (right)
				turnAngle = driveTrain.getAngle() - 5;
			else
				turnAngle = driveTrain.getAngle() + 5;
		} else if (!foundTape) {
			if (right) {
				driveTrain.setLeft(turnSpeed);
				driveTrain.setRight(-turnSpeed);
			} else {
				driveTrain.setLeft(-turnSpeed);
				driveTrain.setRight(turnSpeed);
			}
		}
		System.out.println(foundTape);
		if (foundTape) {
			double value = gyroPID.getValue(driveTrain.getAngle(), turnAngle, 0);
			if (right) {
				driveTrain.setLeft(-value);
				driveTrain.setRight(value);
			} else {
				driveTrain.setLeft(value);
				driveTrain.setRight(-value);
			}
		}
		if (driveTrain.getLeftOptical() && driveTrain.getRightOptical()) {
			counter++;
		} else {
			counter = 0;
		}
		if (counter >= 25) {
			done = true;
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
