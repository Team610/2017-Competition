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
		//R1 - Toggle shift
		//R2 - Lock Drive
		//L1 - Gear Outtake
		//L2 - Gear Load
	}
	
	public Joystick getOperator(){
		return operator;
		//A - Start Track && turn on LED
		//B - Ball Outtake
		//X - Stop ball intake and retract
		//Y - Start ball intake and deploy
		//R1 - Run Feeder
		//L1 - Climb
		//Left X - Turn shooter
	}
	
}
