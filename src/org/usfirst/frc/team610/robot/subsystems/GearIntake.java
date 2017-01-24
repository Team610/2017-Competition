package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIntake extends Subsystem{
	
	static GearIntake instance;
	private DoubleSolenoid topIntake, botIntake;
	private boolean topIsClosed, botIsClosed;
	
	public static GearIntake getInstance(){
		if(instance == null)
			instance = new GearIntake();
		return instance;
	}
	
	public GearIntake(){
		topIntake = new DoubleSolenoid(ElectricalConstants.topGearOne,ElectricalConstants.topGearTwo);
		botIntake = new DoubleSolenoid(ElectricalConstants.botGearOne,ElectricalConstants.botGearTwo);
		topIsClosed = true;
		botIsClosed = true;
		
	}
	
	public void topIntake(boolean open){
		if(open)
			topIntake.set(DoubleSolenoid.Value.kForward);
		else
			topIntake.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void botIntake(boolean open){
		if(open)
			botIntake.set(DoubleSolenoid.Value.kForward);
		else
			botIntake.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean topIsClosed(){
		return topIsClosed;
	}
	
	public boolean botIsClosed(){
		return botIsClosed;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
