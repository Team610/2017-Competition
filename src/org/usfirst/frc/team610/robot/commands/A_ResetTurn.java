package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team611.robot.constants.PIDConstants;
import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_ResetTurn extends Command {

	private DriveTrain driveTrain;
	private double differenceError = 0;
	private double lastError = 0;
	private double tAngle;
	private double time;
	private double error = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	
    public A_ResetTurn(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = DriveTrain.getInstance();
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	tAngle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = driveTrain.getYaw() - tAngle;
    	differenceError = error - lastError;
    		
    	leftSpeed = error * PIDConstants.GYRO_Kp + differenceError * PIDConstants.GYRO_Kd;
    	rightSpeed = error * PIDConstants.GYRO_Kp + differenceError * PIDConstants.GYRO_Kd;

    	driveTrain.setLeft(leftSpeed);
    	driveTrain.setRight(-rightSpeed);
    	
    	lastError = error;	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
