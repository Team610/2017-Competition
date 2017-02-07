package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

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
	private PID visionPID;
	
	public T_Shooter(){
		shooter = Shooter.getInstance();
		oi = OI.getInstance();
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
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
		double value = visionPID.getValue(VisionServer.getInstance().getDouble(), 0, 0);
		shooter.setTurret(value);
		System.out.println(VisionServer.getInstance().getDouble());
		
		if(shooter.getSensor()){
			if(value < 0){
				left = true;
			} else if(!shooter.getSensor()){
				left = false;
			}
		}
		if(VisionServer.getInstance().getDouble() == 0.0){

		}
		
//		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)){ //red
//			if(shooter.atPosition()){
//				isTracking = false;
//				shooter.setTurret(0);
//				left = true;
//			}else{
//				shooter.setTurret(-0.5);
//				isTracking = true;
//			}
//		} else if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)){ //blue
//			if(shooter.atPosition()){
//				isTracking = false;
//				shooter.setTurret(0);
//				right = true;
//			}else{
//				shooter.setTurret(0.5);
//				isTracking = true;
//			}
//				
//		} else if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)){ //home
//			if(!shooter.atPosition()){
//				if(right){
//					shooter.setTurret(-0.5);
//					right = false;
//				}else if(left){
//					shooter.setTurret(0.5);
//					left = false;
//				}
//			}
//			else{
//				shooter.setTurret(0);
//			}
//			
//		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
