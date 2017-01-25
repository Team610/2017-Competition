package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem{
	
	static Hanger instance;
	private Victor hanger;
	
	public static Hanger getInstance(){
		if(instance == null)
			instance = new Hanger();
		return instance;
	}
	
	public Hanger(){
		hanger = new Victor(ElectricalConstants.HANGER_MOTOR);
	}
	
	public void setHangerPower(double power){
		hanger.set(power);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
