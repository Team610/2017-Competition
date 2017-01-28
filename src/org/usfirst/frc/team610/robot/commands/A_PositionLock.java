package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team611.robot.constants.PIDConstants;
import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_PositionLock extends Command {

	private double leftSpeed;
	private double rightSpeed;
	private double error;
	private double lastError;
	private double differenceError;
	private double tAngle;
	private DriveTrain driveTrain;
	private double time;
	private boolean isFinished;
	private int tick;
	
	
    public A_PositionLock() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    	driveTrain = DriveTrain.getInstance();
    }
    public A_PositionLock(int time){
    	driveTrain = DriveTrain.getInstance();
    	this.time = time;
    	tAngle = 0;
    }
    public A_PositionLock(int time, double angle){
    	driveTrain = DriveTrain.getInstance();
    	this.time = time;
    	tAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetSensors();
    	setTimeout(time);
    	isFinished = false;
    	tick = 0;
    	error = 0;
    	lastError = 0;
    	differenceError = 0;
    	rightSpeed = 0;
    	leftSpeed = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	error = driveTrain.getYaw() - tAngle;
    	differenceError = error - lastError;
    	
    	
    	
    	
    	leftSpeed = error * PIDConstants.GYRO_Kp + differenceError * -PIDConstants.GYRO_Kd;
    	rightSpeed = error * PIDConstants.GYRO_Kp + differenceError * -PIDConstants.GYRO_Kd;
    	
    	if(Math.abs(error) < 0.5){
    		tick++;
    		if(tick > 50){
    			isFinished = true;
    		}
    	} else {
    		tick = 0;
    	}
    	
    	driveTrain.setLeft(leftSpeed);
    	driveTrain.setRight(-rightSpeed);
    	
    	if(isFinished){
        	driveTrain.setLeft(0);
        	driveTrain.setRight(0);

        }
    	
    	lastError = error;	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
