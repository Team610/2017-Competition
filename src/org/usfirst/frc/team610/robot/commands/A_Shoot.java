package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_Shoot extends Command {

	PID shooterPID;
	double shooterSpeed;
	Shooter shooter;
	double rpm;
	HopperFeeder feeder;
	VisionServer server;
	
    public A_Shoot() {
    	shooterPID = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D,0,1);
    	shooterSpeed = 0;
    	rpm = 0;
    	shooter = Shooter.getInstance();
    	feeder = HopperFeeder.getInstance();
    	server = VisionServer.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rpm = shooter.getShooterSpeed();
    	shooterSpeed = shooterPID.getValue(rpm, PIDConstants.RPM, shooter.getFeedForward(PIDConstants.RPM));
   
    	if(Math.abs(rpm - PIDConstants.RPM) < 50 && server.isTracking()){
    		feeder.setSpeed(0.5);
    	}
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
