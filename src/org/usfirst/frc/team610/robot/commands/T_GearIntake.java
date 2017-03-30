package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.Xbox360Constants;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

public class T_GearIntake extends Command{
	private GearIntake gearIntake;
	private OI oi;
	private boolean blink;
	private int blinkCounter;
	
	public T_GearIntake(){
		gearIntake = GearIntake.getInstance();
		oi = OI.getInstance();
		blinkCounter = 0;
		blink = false;
	}
	
	protected void execute(){
		if(oi.getDriver().getRawAxis(Xbox360Constants.AXIS_L2)>0.3){
			gearIntake.setIntake(true);
			
			if(!oi.getDriver().getRawButton(Xbox360Constants.BTN_L1)){
				gearIntake.setOuttake(false);
			}
			else if(oi.getDriver().getRawButton(Xbox360Constants.BTN_L1) || gearIntake.getPeg()){
				gearIntake.setOuttake(true);
			}
		} else {
			gearIntake.setIntake(false);
		}
		if(oi.getDriver().getRawButton(Xbox360Constants.BTN_L1)) {
			gearIntake.setOuttake(true);
		}
		
		if(gearIntake.getPeg()){
			gearIntake.setOuttake(true);
		}
		
		if(gearIntake.isOuttakeClosed() && gearIntake.getPeg()){
			gearIntake.setLED(true);
		} else if(!gearIntake.isOuttakeClosed() && gearIntake.getPeg()){
			blinkCounter ++;
			if(blinkCounter > 5){
				blink = !blink;
			}
			gearIntake.setLED(blink);
		} else if(!gearIntake.getPeg()){
			gearIntake.setLED(false);
		} else {
			gearIntake.setLED(false);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
