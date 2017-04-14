package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SC_Gear extends Command {

	private GearIntake gearIntake;
	private boolean scored;
    public SC_Gear() {
    	gearIntake = GearIntake.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearIntake.setIntake(true);
    	gearIntake.setOuttake(false);
    	System.out.println("Test: GearManip");
    	setTimeout(30);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(gearIntake.getPeg()){
    		gearIntake.setOuttake(true);
    		scored = true;
    		gearIntake.setLED(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return scored || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearIntake.setIntake(false);
    	gearIntake.setOuttake(true);
    	if(scored){
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
