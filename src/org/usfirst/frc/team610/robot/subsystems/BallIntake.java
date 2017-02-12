package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.Xbox360Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntake extends Subsystem {
	
	private static BallIntake instance;
	
	private OI oi;

	private Victor intakeMotor;
	
	private DoubleSolenoid deploy;
	
	public static BallIntake getInstance(){
		if(instance == null){
			instance = new BallIntake();
		}
		return instance;
	}
	
	private BallIntake(){
		oi = OI.getInstance();
		intakeMotor = new Victor(ElectricalConstants.INTAKE);

		deploy = new DoubleSolenoid(ElectricalConstants.INTAKE_BALL_ONE, ElectricalConstants.INTAKE_BALL_TWO);
	}
	
	public void setIntake(double speed){
		intakeMotor.set(speed);
	}
	
	public void run(){
		if(oi.getDriver().getRawButton(Xbox360Constants.BTN_A)){
			
		}
	}
	
	public void deploy(boolean deployed){
		if(deployed){
			deploy.set(DoubleSolenoid.Value.kForward);
		} else if (!deployed){
			deploy.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

