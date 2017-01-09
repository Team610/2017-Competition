package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team610.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */





public class OI {
	private static OI instance;
	
	public static OI getInstance(){
		if(instance == null){
			return new OI();
		} else {
			return instance;
		}
	}
	
	
}
