package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.Hanger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SC_Hanger extends Command {

	private Hanger hanger;
	private int counter;
	
    public SC_Hanger() {
    	hanger = Hanger.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    	hanger.setHanger(-.15);
    	System.out.println("Test: Hanger");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(counter > 75){
    		hanger.setHanger(-1);
    	}
    	counter ++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counter > 150;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hanger.setHanger(0);
    	System.out.println("Completed");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
