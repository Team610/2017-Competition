package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class T_Shooter extends Command {
	
	private Shooter shooter;
	private OI oi;
	private boolean isTracking;
	private boolean sensing;
	private boolean isBlue;
	private boolean left;
	private boolean right;
	private int positionCounter;
	private String position;
	private boolean onPos;
	
	public T_Shooter(){
		shooter = Shooter.getInstance();
		oi = OI.getInstance();
	}
	
	protected void initialize(){
		isTracking = false;
		sensing = false;
		left = false;
		right = false;
		positionCounter = 0;
		position = "base";
	}	
	
	protected void execute(){
		
		
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)){ //red
			if(shooter.atPosition()){
				isTracking = false;
				shooter.setTurret(0);
				left = true;
			}else{
				shooter.setTurret(-0.5);
				isTracking = true;
			}
		} else if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)){ //blue
			if(shooter.atPosition()){
				isTracking = false;
				shooter.setTurret(0);
				right = true;
			}else{
				shooter.setTurret(0.5);
				isTracking = true;
			}
				
		} else if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)){ //home
			if(!shooter.atPosition()){
				if(right){
					shooter.setTurret(-0.5);
					right = false;
				}else if(left){
					shooter.setTurret(0.5);
					left = false;
				}
			}
			else{
				shooter.setTurret(0);
			}
			
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
