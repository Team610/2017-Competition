package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntake extends Subsystem {
	
	private static BallIntake instance;
	
	private OI oi;

	private Victor intakeMotor;
	
	public static BallIntake getInstance(){
		if(instance == null){
			instance = new BallIntake();
		}
		return instance;
	}
	
	private BallIntake(){
		oi = OI.getInstance();
		intakeMotor = new Victor(ElectricalConstants.INTAKE);
	}
	
	public void setIntake(double speed){
		intakeMotor.set(speed);
	}
	
	public void run(){
		if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_A)){
			
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

