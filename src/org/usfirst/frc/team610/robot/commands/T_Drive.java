package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_Drive extends Command {

	private DriveTrain driveTrain;
	private boolean turbo = false;
	private boolean atMax = false;
	private boolean pidLock = false;
	private double leftEncValue, rightEncValue;
	private PID pidLeft, pidRight;
	private OI oi;

	public T_Drive() {
		driveTrain = DriveTrain.getInstance();
		oi = OI.getInstance();
	}

	protected void initialize() {
		// driveTrain.compressor(true);
		driveTrain.resetEnc();
		pidLeft = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		pidRight = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
	}

	protected void execute() {
		if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R2)) {
			if(!pidLock){
				leftEncValue = driveTrain.getLeftInches();
				rightEncValue = driveTrain.getRightInches();
			}
			
			driveTrain.setLeft(pidLeft.getValue(driveTrain.getLeftInches(),leftEncValue,0));
			driveTrain.setRight(pidRight.getValue(driveTrain.getRightInches(),rightEncValue,0));
			
			pidLock = true;
		} else {
			driveTrain.drive();

			if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1)) {
				driveTrain.shiftUp();
				turbo = true;
			} else if (Math.abs(driveTrain.getLeftRPM()) < 50 && Math.abs(driveTrain.getRightRPM()) < 50) {
				driveTrain.shiftDown();
				turbo = false;
			}

			if (Math.abs(driveTrain.getLeftRPM()) > 120 && Math.abs(driveTrain.getRightRPM()) > 120) {
				atMax = true;
			} else {
				atMax = false;
			}
			pidLock = false;
		}

		SmartDashboard.putBoolean("SHIFT!", atMax);
		SmartDashboard.putNumber("leftRPM", Math.abs(driveTrain.getLeftRPM()));
		SmartDashboard.putNumber("rightRPM", Math.abs(driveTrain.getRightRPM()));
		SmartDashboard.putNumber("leftDistance", driveTrain.getLeftInches());
		SmartDashboard.putNumber("rightDistance", driveTrain.getRightInches());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// driveTrain.compressor(false);
	}

}
