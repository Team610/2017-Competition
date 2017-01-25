package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;

import edu.wpi.first.wpilibj.command.Command;

public class T_Feeder extends Command{
	private HopperFeeder hopperFeeder;
	private OI oi;
	
	public T_Feeder(){
		hopperFeeder = HopperFeeder.getInstance();
		oi = OI.getInstance();
	}
	
	protected void execute(){
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_R1))
			hopperFeeder.setSpeed(0.5);
		else if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_R2))
			hopperFeeder.setSpeed(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
