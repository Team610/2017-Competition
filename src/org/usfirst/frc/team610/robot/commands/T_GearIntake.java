package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

public class T_GearIntake extends Command{
	private GearIntake gearIntake;
	private OI oi;
	
	public T_GearIntake(){
		gearIntake = GearIntake.getInstance();
		oi = OI.getInstance();
	}
	
	protected void execute(){
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_L2)
				|| oi.getDriver().getRawButton(LogitechF310Constants.BTN_L2)){
			gearIntake.setIntake(true);
			gearIntake.setOuttake(false);
		} else {
			gearIntake.setIntake(false);
		}
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_L1)
				|| oi.getDriver().getRawButton(LogitechF310Constants.BTN_L1)) {
			gearIntake.setOuttake(true);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
