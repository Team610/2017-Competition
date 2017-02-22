package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.constants.Xbox360Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_Drive extends Command {

	private DriveTrain driveTrain;
	private boolean atMax = false;
	private double leftEncValue, rightEncValue;
	private PID pidLeft, pidRight;
	private OI oi;
	private boolean high;
	private Preferences prefs;

	public T_Drive() {
		driveTrain = DriveTrain.getInstance();
		oi = OI.getInstance();
		high = false;
		prefs = Preferences.getInstance();
	}

	protected void initialize() {
		// driveTrain.compressor(true);
		driveTrain.resetEnc();
		pidLeft = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		pidRight = new PID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
	}

	protected void execute() {
		
		PIDConstants.Update();
		pidRight.updatePID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		pidLeft.updatePID(PIDConstants.DRIVE_ENC_P, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);

		if (oi.getDriver().getRawButton(Xbox360Constants.BTN_R1)) {
			if (Math.abs(oi.getDriver().getRawAxis(Xbox360Constants.AXIS_LEFT_Y)) < 0.1
					&& Math.abs(oi.getDriver().getRawAxis(Xbox360Constants.AXIS_RIGHT_X)) < 0.11
					&& Math.abs(driveTrain.getVelocity()) < 1) {
				driveTrain.setLeft(pidLeft.getValue(driveTrain.getLeftInches(), leftEncValue, 0));
				driveTrain.setRight(pidRight.getValue(driveTrain.getRightInches(), rightEncValue, 0));
			} else {
				driveTrain.drive(0.5);
				leftEncValue = driveTrain.getLeftInches();
				rightEncValue = driveTrain.getRightInches();
			}

		} else {
			// Drive at full power
			driveTrain.drive(1);

			// Shifting
			if (oi.getDriver().getRawAxis(Xbox360Constants.AXIS_R2) > 0.5 && prefs.getBoolean("GoodEgg", true)) {
				high = true;
			} else if (Math.abs(driveTrain.getLeftRPM()) < 50 
					&& Math.abs(driveTrain.getRightRPM()) < 50 
					&& oi.getDriver().getRawAxis(Xbox360Constants.AXIS_R2) < 0.5) {
				high = false;
			}
			

			if (high) {
				driveTrain.shiftUp();
			} else {
				driveTrain.shiftDown();
			}

			if (Math.abs(driveTrain.getLeftRPM()) > 120 && Math.abs(driveTrain.getRightRPM()) > 120) {
				atMax = true;
			} else {
				atMax = false;
			}

			leftEncValue = driveTrain.getLeftInches();
			rightEncValue = driveTrain.getRightInches();
		}

		SmartDashboard.putBoolean("SHIFT!", atMax);
//		SmartDashboard.putNumber("leftRPM", Math.abs(driveTrain.getLeftRPM()));
//		SmartDashboard.putNumber("rightRPM", Math.abs(driveTrain.getRightRPM()));
//		SmartDashboard.putNumber("leftDistance", driveTrain.getLeftInches());
//		SmartDashboard.putNumber("rightDistance", driveTrain.getRightInches());
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
