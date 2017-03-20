package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_Shoot extends Command {

	private PID shooterPID;
	private double shooterSpeed;
	private Shooter shooter;
	private double rpm;
	private HopperFeeder feeder;
	private VisionServer server;
	private double shootingSpeed;
	private double hopperSpeed;
	
    public A_Shoot(double shootingSpeed, double hopperSpeed) {
    	shooterPID = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D,0,1);
    	shooterSpeed = 0;
    	rpm = 0;
    	shooter = Shooter.getInstance();
    	feeder = HopperFeeder.getInstance();
    	server = VisionServer.getInstance();
    	this.shootingSpeed = shootingSpeed;
    	this.hopperSpeed = hopperSpeed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
    	PIDConstants.Update();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rpm = shooter.getRPM();
    	shooterSpeed = shooterPID.getValue(rpm, server.getRPM(), shooter.getFeedForward(shootingSpeed));
    	if(shooterSpeed < 6000){
    		shooter.setPower(-shooterSpeed);
    	}
    	SmartDashboard.putNumber("Y-Dist", server.getHeight());
    	SmartDashboard.putNumber("RPM", rpm);
    	
    	if(server.isTracking()){
    		feeder.setSpeed(hopperSpeed);
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
