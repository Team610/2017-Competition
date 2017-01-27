package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIntake extends Subsystem{
	
	static GearIntake instance;
	private DoubleSolenoid intake, outtake;
	private boolean isIntakeClosed, isOuttake;
	
	public static GearIntake getInstance(){
		if(instance == null)
			instance = new GearIntake();
		return instance;
	}
	
	public GearIntake(){
		intake = new DoubleSolenoid(ElectricalConstants.GEAR_FLAP_ONE,ElectricalConstants.GEAR_FLAP_TWO);
		outtake = new DoubleSolenoid(ElectricalConstants.GEAR_SCORER_ONE,ElectricalConstants.GEAR_SCORER_TWO);
		isIntakeClosed = true;
		isOuttake = true;
		
	}
	
	public void setIntake(boolean open){
		if(open){
			intake.set(DoubleSolenoid.Value.kForward);
			isIntakeClosed = false;
		}
		else{
			intake.set(DoubleSolenoid.Value.kReverse);
			isIntakeClosed = true;
		}
	}
	
	public void setOuttake(boolean open){
		if(open){
			outtake.set(DoubleSolenoid.Value.kForward);
			isOuttake = false;
		}
		else{
			outtake.set(DoubleSolenoid.Value.kReverse);
			isOuttake = true;
		}
	}
	
	public boolean isIntakeClosed(){
		return isIntakeClosed;
	}
	
	public boolean isOuttakeClosed(){
		return isOuttake;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
