package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class D_SensorReadings extends Command {

	
	private DriveTrain driveTrain;
	private NavX navx;
	 
	
    public D_SensorReadings() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	navx = NavX.getInstance();
    	//intake = Intake.getInstance();
    	//hanger = Hanger.getInstance();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetSensors();
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("GyroAngle: ", navx.getAngle());
		SmartDashboard.putNumber("RightEncoder", driveTrain.getRightInches());
		SmartDashboard.putNumber("LeftEncoder", driveTrain.getLeftInches());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
