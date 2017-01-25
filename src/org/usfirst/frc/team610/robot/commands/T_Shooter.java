package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class T_Shooter extends Command {
	
	private Shooter shooter;
	private OI oi;
	
	public T_Shooter(){
		shooter = Shooter.getInstance();
		oi = oi.getInstance();
	}
	
	protected void initialize(){
		
	}
	
	protected void execute(){
		shooter.setTurret(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_LEFT_X));
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_L1))
			shooter.setShooter(4000);
		else
			shooter.setShooter(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
