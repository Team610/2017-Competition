package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_Drive extends Command {

	private DriveTrain driveTrain;
	private boolean turbo = false;
	private boolean atMax = false;
	private OI oi;


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

		if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1)){
			driveTrain.shiftUp();
			turbo = true;
		} else if(Math.abs(driveTrain.getLeftRPM()) < 5 && Math.abs(driveTrain.getRightRPM()) < 5){
			driveTrain.shiftDown();
			turbo = false;
		}
		
		if(Math.abs(driveTrain.getLeftRPM()) > 120 && Math.abs(driveTrain.getRightRPM()) > 120){
			atMax = true;
		} else {
			atMax = false;
		}
		
		SmartDashboard.putBoolean("SHIFT!", atMax);
		SmartDashboard.putNumber("leftRPM", driveTrain.getLeftRPM());
		SmartDashboard.putNumber("rightRPM", driveTrain.getRightRPM());
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
