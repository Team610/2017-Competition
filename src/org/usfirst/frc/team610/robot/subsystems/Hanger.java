package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem{
	
	static Hanger instance;
	private Victor hanger;
	private OI oi;
	
	public static Hanger getInstance(){
		if(instance == null)
			instance = new Hanger();
		return instance;
	}
	
	public Hanger(){
		oi = OI.getInstance();
		hanger = new Victor(ElectricalConstants.HANGER_MOTOR);
	}
	
	public void setHanger(double power){
		hanger.set(power);
	}
	
	public void hang(){
		setHanger(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y));
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
