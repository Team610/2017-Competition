package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_Drive extends Command {

	private DriveTrain driveTrain;
	private boolean manual = false;
	private OI oi;
	private boolean isPressed = false;
	private boolean upShift = false;

	public T_Drive() {
		driveTrain = DriveTrain.getInstance();
		oi = OI.getInstance();
	}

	protected void initialize() {
		// driveTrain.compressor(true);
		driveTrain.resetEnc();
	}

	protected void execute() {
		driveTrain.drive();

		if (!upShift) {
			driveTrain.shiftDown();
		} else if (upShift) {
			driveTrain.shiftUp();
		}

		if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1) && !isPressed) {
			manual = true;
			upShift = !upShift;
			isPressed = true;
		}

		if (!oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1)) {
			isPressed = false;
		}

		if (oi.getDriver().getRawButton(LogitechF310Constants.BTN_R2)) {
			manual = !manual;
		}
		
		if(!manual){
			if(driveTrain.getRightRPM() < 110 && driveTrain.getLeftRPM() < 110){
				upShift = false;
			} else if(driveTrain.getRightRPM() > 115 && driveTrain.getLeftRPM() > 115){
				upShift = true;
			}
		}

		 SmartDashboard.putNumber("leftRPM", driveTrain.getLeftRPM());
		 SmartDashboard.putNumber("rightRPM", driveTrain.getRightRPM());
		// SmartDashboard.putNumber("leftDistance", driveTrain.getLeftInches());
		// SmartDashboard.putNumber("rightDistance",
		// driveTrain.getRightInches());
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
