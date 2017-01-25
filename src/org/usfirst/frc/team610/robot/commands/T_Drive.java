package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class T_Drive extends Command{
	
	private DriveTrain driveTrain;
	
	public T_Drive(){
		driveTrain = DriveTrain.getInstance();
	}
	
	protected void initialize(){
		driveTrain.compressor(true);
	}
	
	protected void execute(){
		driveTrain.drive();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
