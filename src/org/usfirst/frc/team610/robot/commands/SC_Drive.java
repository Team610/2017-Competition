package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SC_Drive extends TimedCommand {

	private DriveTrain driveTrain;
	private boolean shift;
	private double left;
	private double right;
	private boolean complete;
	
    public SC_Drive(double timeout, boolean shift, double left, double right) {
        super(timeout);
        this.shift = shift;
        this.left = left;
        this.right = right;
        driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    	System.out.println("Test: DriveTrain");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(shift){
    		driveTrain.shiftUp();
    		if(Math.abs(driveTrain.getLeftRPM()) > 125 && Math.abs(driveTrain.getRightRPM()) > 125){
    			complete = true;
    		}
    	} else {
    		driveTrain.shiftDown();
    		if(Math.abs(driveTrain.getLeftRPM()) >= 120 && Math.abs(driveTrain.getRightRPM()) >= 120){
    			complete = true;
    		}
    	}
    	driveTrain.setLeft(left);
    	driveTrain.setRight(right);
    	
    }

    // Called once after timeout
    protected void end() {
    	if(complete){
    		System.out.println("Good");
    	} else {
    		System.out.println("Bad");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
