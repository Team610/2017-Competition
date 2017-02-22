package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_SmartDash extends Command {

	private DriveTrain driveTrain;
	private VisionServer server;
	
    public T_SmartDash() {
    	driveTrain = DriveTrain.getInstance();
    	server = VisionServer.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Displays to operator that turret is tracking
    	SmartDashboard.putBoolean("Tracking", server.isTracking());
		SmartDashboard.putNumber("leftRPM", Math.abs(driveTrain.getLeftRPM()));
		SmartDashboard.putNumber("rightRPM", Math.abs(driveTrain.getRightRPM()));
		SmartDashboard.putNumber("leftDistance", driveTrain.getLeftInches());
		SmartDashboard.putNumber("rightDistance", driveTrain.getRightInches());
		SmartDashboard.putNumber("Calculated RPM", server.getRPM());
		SmartDashboard.putNumber("Y-Dist", server.getHeight());
		SmartDashboard.putNumber("Target", server.getDouble());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
