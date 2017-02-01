package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_ResetSensors extends Command {

	double time;
	DriveTrain driveTrain;
	
    public A_ResetSensors(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.time = time;
    	driveTrain = DriveTrain.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	driveTrain.resetSensors();
    	driveTrain.setLeft(0);
		driveTrain.setRight(0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Left Enc", driveTrain.getLeftInches());
    	SmartDashboard.putNumber("Right Enc", driveTrain.getRightInches());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
