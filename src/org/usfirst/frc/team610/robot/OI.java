package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.Joystick;


public class OI {
	
	private static OI instance;
	private Joystick driver;
	private Joystick operator;
	
	private OI(){
		driver = new Joystick(0);
		operator = new Joystick(1);
	}
	
	public static OI getInstance(){
		if(instance == null){
			instance = new OI();
		}
		return instance;
	}
	
	public Joystick getDriver(){
		return driver;
	}
	
	public Joystick getOperator(){
		return operator;
	}
	
}
