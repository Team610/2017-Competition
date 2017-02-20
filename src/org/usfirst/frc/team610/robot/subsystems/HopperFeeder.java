package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Victor;

public class HopperFeeder {

	private Victor feeder, agitator;
	private boolean isRunning;
	static HopperFeeder instance;
	
	public static HopperFeeder getInstance(){
		if(instance == null)
			instance = new HopperFeeder();
		return instance;
	}
	
	public HopperFeeder(){
		feeder = new Victor(ElectricalConstants.FEEDER);
		agitator = new Victor(ElectricalConstants.VIBRATOR);
		isRunning = false;
	}
	
	public void setSpeed(double speed){
		feeder.set(speed);
		if(speed==0){
			isRunning = false;
			agitator.set(0);
		}
		
		else{
			isRunning = true;
			agitator.set(0.4);
		}
	}
	
	public void setAgitator(boolean run){
		if(run){
			agitator.set(0.4);
		} else {
			agitator.set(0);
			
		}
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
}
