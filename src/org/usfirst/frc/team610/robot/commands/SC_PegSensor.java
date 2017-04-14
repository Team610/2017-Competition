package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SC_PegSensor extends Command {

	private DriveTrain driveTrain;
	private boolean left;
	private boolean right;
	private double time;
    public SC_PegSensor(double time) {
    	this.time = time;
        driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Test: Peg Sensors");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(driveTrain.getLeftOptical()){
    		left = true;
    	}
    	if(driveTrain.getRightOptical()){
    		right = true;
    	}
    	
    }

    // Called once after timeoutjjj
    protected void end() {
    	if(left && right){
    		System.out.println("Good");
    	} else if (left && !right){
    		System.out.println("Left Good, Right Bad");
    	} else if(!left && right){
    		System.out.println("Left Bad, Right Good");
    	} else {
    		System.out.println("Bad");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return (left && right) || isTimedOut();
	}
}
