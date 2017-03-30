package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.BallIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_SetBallIntake extends Command {

	private BallIntake intake;
	private boolean open;
	private boolean finish;
	
    public A_SetBallIntake(boolean open) {
    	intake = BallIntake.getInstance();
    	this.open = open;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finish = false;
    	if(open){
    		intake.setIntake(1);
    		intake.deploy(true);
    	} else {
    		intake.setIntake(0);
    		intake.deploy(false);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finish = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finish;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
