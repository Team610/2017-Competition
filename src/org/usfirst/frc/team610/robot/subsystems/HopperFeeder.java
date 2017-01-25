package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Victor;

public class HopperFeeder {

	private Victor feeder;
	private boolean isRunning;
	static HopperFeeder instance;
	
	public static HopperFeeder getInstance(){
		if(instance == null)
			instance = new HopperFeeder();
		return instance;
	}
	
	public HopperFeeder(){
		feeder = new Victor(ElectricalConstants.FEEDER);
		isRunning = false;
	}
	
	public void setSpeed(double speed){
		feeder.set(speed);
		if(speed==0)
			isRunning = false;
		else
			isRunning = true;
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
}
