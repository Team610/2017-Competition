package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SC_SetZero extends TimedCommand {

	private DriveTrain driveTrain;
	
    public SC_SetZero(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain = DriveTrain.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.setLeft(0);
    	driveTrain.setRight(0);
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
