package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.Xbox360Constants;
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
		if(oi.getDriver().getRawAxis(Xbox360Constants.AXIS_L2)>0.3){
			gearIntake.setIntake(true);
			if(!oi.getDriver().getRawButton(Xbox360Constants.BTN_L1)){
				gearIntake.setOuttake(false);
			}
		} else {
			gearIntake.setIntake(false);
		}
		if(oi.getDriver().getRawButton(Xbox360Constants.BTN_L1)) {
			gearIntake.setOuttake(true);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
