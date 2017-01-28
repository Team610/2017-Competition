package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		SmartDashboard.putNumber("leftRPM", driveTrain.getLeftRPM());
		SmartDashboard.putNumber("rightRPM", driveTrain.getRightRPM());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		driveTrain.compressor(false);
    }

}
