package org.usfirst.frc.team610.robot.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem {
	
	private static NavX instance;
	 
	private IMUAdvanced navX;
	
	public static NavX getInstance(){
		if(instance == null){
			instance = new NavX();
		}
		return instance;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getAngle(){
    	return navX.getYaw();
    }
    
    public void reset(){
    	navX.zeroYaw();
    }
    
    
}

