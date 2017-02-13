package org.usfirst.frc.team610.robot.subsystems;




import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem {
	
	private static NavX instance;
	 
	private AHRS navX;
	
	public static NavX getInstance(){
		if(instance == null){
			instance = new NavX();
		}
		return instance;
	}

	private NavX(){
		try{
			navX = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex){
			
		}
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void reset(){
    	navX.reset();
    }
    
    public double getVelocityY(){
    	return navX.getVelocityY();
    }
    
    public double getVelocityX(){
    	return navX.getVelocityX();
    }
    
    public double getVelocityZ(){
    	return navX.getVelocityZ();
    }
    
    public double getAngle(){
    	return navX.getYaw();
    }
    
    public void resetYaw(){
    	navX.zeroYaw();
    }
    
    
}

