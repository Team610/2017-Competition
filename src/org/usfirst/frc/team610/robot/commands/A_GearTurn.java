package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_GearTurn extends Command {

	private DriveTrain driveTrain;
	private double time;
	private int detectedCounter;
	private boolean isDetected;
	
	
    public A_GearTurn(double time) {
    	driveTrain = DriveTrain.getInstance();
    	this.time = time;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Turning");
    	setTimeout(time);
    	detectedCounter = 0;
    	isDetected = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(detectedCounter < 1){
	    	driveTrain.setLeft(.5);
	    	driveTrain.setRight(-.5);
    	} else {
    		driveTrain.setLeft(.2);
    		driveTrain.setRight(-.2);
    	}
    	if(!driveTrain.getLeftEye() && !isDetected){
    		detectedCounter ++;
    		isDetected = true;
    		System.out.println(detectedCounter);
    	}
    	if(driveTrain.getLeftEye()){
    		isDetected = false;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return detectedCounter == 2 || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.setRight(0);
    	driveTrain.setLeft(0);
    	System.out.println("End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
