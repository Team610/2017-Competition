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
	private boolean atMax = false;
	private double leftEncValue, rightEncValue;
	private PID pidLeft, pidRight;
	private OI oi;
	private boolean high;
	private boolean isR1Pressed;

	public T_Drive() {
		driveTrain = DriveTrain.getInstance();
		oi = OI.getInstance();
		high = false;
		isR1Pressed = false;
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

		if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R2)) {
			if (Math.abs(oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y)) < 0.05
					&& Math.abs(oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X)) < 0.05) {
				driveTrain.setLeft(pidLeft.getValue(driveTrain.getLeftInches(), leftEncValue, 0));
				driveTrain.setRight(pidRight.getValue(driveTrain.getRightInches(), rightEncValue, 0));
				System.out.println(driveTrain.getLeftInches());
				System.out.println(driveTrain.getRightInches());
			} else {
				driveTrain.drive(0.5);
				leftEncValue = driveTrain.getLeftInches();
				rightEncValue = driveTrain.getRightInches();
			}

		} else {
			// Drive at full power
			driveTrain.drive(1);

			// Shifting
			if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1) && !isR1Pressed) {
				high = !high;
				isR1Pressed = true;
			} else if (Math.abs(driveTrain.getLeftRPM()) < 50 && Math.abs(driveTrain.getRightRPM()) < 50) {
				high = false;
			}

			if(high){
				driveTrain.shiftUp();
			} else {
				driveTrain.shiftDown();
			}
			
			if (!oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1)) {
				isR1Pressed = false;
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
