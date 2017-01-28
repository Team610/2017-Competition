package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team611.robot.constants.PIDConstants;
import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_NewPositionMove extends Command {
	
	private DriveTrain driveTrain;
	private PID encPid;
	private PID gyroPid;
	private int finishCounter = 0;
	private double time;
	private double moveSpeed;
	private double rightSpeed;
	private double leftSpeed;
	private double turnSpeed;
	private double tDistance;
	private boolean isFinished;
	private double limit;
	private double tAngle;
	
	//Limit should be positive
    public A_NewPositionMove(double tDistance, double time, double limit) {
    	encPid = new PID(PIDConstants.ENCODER_Kp, 0, PIDConstants.ENCODER_Kd);
    	gyroPid = new PID(PIDConstants.GYRO_Kp, 0, PIDConstants.GYRO_Kd);
    	driveTrain = DriveTrain.getInstance();
    	this.tDistance = tDistance;
    	this.time = time;
    	this.limit = limit;
    	tAngle = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	isFinished = false;
    	tAngle = driveTrain.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	moveSpeed = encPid.getValue((driveTrain.getLeftInches() + driveTrain.getRightInches()) / 2, tDistance);
    	turnSpeed = gyroPid.getValue(driveTrain.getYaw(), tAngle);
    	
    	if(Math.abs(moveSpeed) > Math.abs(limit)){
    		if(moveSpeed < 0){
    			moveSpeed = -limit;
    		} else if (moveSpeed > 0){
    			moveSpeed = limit;
    		}
    	}
    	
    	isFinished = false;

    	leftSpeed = moveSpeed - turnSpeed;
    	rightSpeed = moveSpeed + turnSpeed ;
    	
    	
    	if(rightSpeed > 1){
    		leftSpeed -= rightSpeed - 1;
    		rightSpeed = 1;
    	} else if (rightSpeed < -1){
    		leftSpeed -= rightSpeed + 1;
    		rightSpeed = -1;
    	}
    	
    	if(leftSpeed > 1){
    		rightSpeed -= leftSpeed - 1;
    		leftSpeed = 1;
    	} else if (leftSpeed < -1){
    		rightSpeed -= leftSpeed + 1;
    		leftSpeed = -1;
    	}
    	
    	if((encPid.getError() + encPid.getError()) / 2 < 0.5){
    		finishCounter ++;
    	} else {
    		finishCounter = 0;
    	}
    	
    	isFinished = finishCounter > 25;
    	
    	if(isFinished){
    		driveTrain.setLeft(0);
    		driveTrain.setRight(0);
    	} else {
    		driveTrain.setLeft(leftSpeed);
    		driveTrain.setRight(rightSpeed);
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
