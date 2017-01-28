package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team611.robot.constants.PIDConstants;
import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_NewPositionLock extends Command {
	
	PID pid;
	DriveTrain driveTrain;
	private int finishCounter = 0;
	private double time;
	private double leftSpeed;
	private double rightSpeed;
	private double turnSpeed;
	private double tAngle;
	private boolean isFinished;
	
    public A_NewPositionLock(double tAngle, double time) {
    	pid = new PID(PIDConstants.GYRO_Kp, 0, PIDConstants.GYRO_Kd);
    	driveTrain = DriveTrain.getInstance();
    	this.time = time;
    	this.tAngle = tAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetSensors();
    	setTimeout(time);
    	isFinished = false;
    	finishCounter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnSpeed = pid.getValue(driveTrain.getYaw(), tAngle);
    	leftSpeed = -turnSpeed;
    	rightSpeed = turnSpeed;
    	if(Math.abs(pid.getError()) < 0.5){
    		finishCounter ++;
    	} else {
    		finishCounter = 0;
    	}
    	isFinished = finishCounter > 25;
    	if(isFinished){
    		driveTrain.setRight(0);
    		driveTrain.setLeft(0);
    	} else {
    		driveTrain.setRight(rightSpeed);
    		driveTrain.setLeft(leftSpeed);
    	}
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
