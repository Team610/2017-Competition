package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivePID extends Command {

	private PID gyroPID;
	private DriveTrain driveTrain;
	
    public DrivePID() {
    	driveTrain = DriveTrain.getInstance();
    	gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetAngle();
    	gyroPID.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double value = gyroPID.getValue(driveTrain.getAngle(), 45, 0);
    	driveTrain.setLeft(-value);
    	driveTrain.setRight(value);
    	gyroPID.updatePID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_D);
    	SmartDashboard.putNumber("Power", value);
    	SmartDashboard.putNumber("Angle", driveTrain.getAngle());
    	PIDConstants.Update();
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
