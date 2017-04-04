package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.Xbox360Constants;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_GearIntake extends Command{
	private GearIntake gearIntake;
	private OI oi;
	private Shooter shooter;
	private boolean strobe;
	private boolean isStartPressed;
	private int strobeCounter;
	private boolean on;
	private boolean open;
	private int blinkCounter;
	private boolean legit;
	
	public T_GearIntake(){
		gearIntake = GearIntake.getInstance();
		oi = OI.getInstance();
		shooter = Shooter.getInstance();
		blinkCounter = 0;
		strobe = false;
		isStartPressed = false;
		on = false;
		legit = true;
	}
	
	protected void execute(){
		if(oi.getDriver().getRawAxis(Xbox360Constants.AXIS_L2)>0.3 && !open){
			gearIntake.setIntake(true);
			legit = true;
			
			if(oi.getDriver().getRawButton(Xbox360Constants.BTN_L1) || gearIntake.getPeg()){
				gearIntake.setOuttake(true);
				legit = true;
			} else if(blinkCounter > 75 ){
				gearIntake.setOuttake(false);
				blinkCounter = 0;
				legit = true;
			}
		} else {
			gearIntake.setIntake(false);
		}
		
		if(oi.getDriver().getRawButton(Xbox360Constants.BTN_L1)) {
			gearIntake.setOuttake(true);
			legit = true;
		} 
		
		if(!gearIntake.getPeg()){
			blinkCounter ++;
		}
		
		
		if(gearIntake.getPeg()){
			gearIntake.setOuttake(true);
		}
		
		if(gearIntake.isOuttakeClosed() && gearIntake.getPeg()){
			gearIntake.setLED(true);
			legit = true;
		} else if(!gearIntake.isOuttakeClosed() && gearIntake.getPeg()){
			gearIntake.setLED(true);
			legit = true;
		} else if(!gearIntake.getPeg()){
			gearIntake.setLED(false);
		} else {
			gearIntake.setLED(false);
		}
		
		SmartDashboard.putBoolean("Peg", gearIntake.getPeg());
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_START) 
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_BACK)
				&& !isStartPressed){
			strobe = !strobe;
			isStartPressed = true;
			legit = false;
		}
		if(!oi.getOperator().getRawButton(LogitechF310Constants.BTN_START)){
			isStartPressed = false;
		}
		if(strobe && !legit) {
			strobeCounter++;
			if(strobeCounter > 10){
				on = !on;
				strobeCounter = 0;
			}
			gearIntake.setLED(on);
			shooter.setLED(on);
			
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
