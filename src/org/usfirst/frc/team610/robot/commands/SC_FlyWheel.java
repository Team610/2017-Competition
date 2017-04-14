package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SC_FlyWheel extends TimedCommand {
	
	private Shooter shooter;
	private PID pid;
	private double speed;
	private boolean complete;
	
    public SC_FlyWheel(double timeout) {
        super(timeout);
        shooter = Shooter.getInstance();
        pid = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D, 0, 1);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    	System.out.println("Test: FlyWheel");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = pid.getValue(shooter.getRPM(), PIDConstants.RPM_Center , shooter.getFeedForward(PIDConstants.RPM_Center));
    	if(speed < 6000){
    		shooter.setPower(speed);
    	}
    	if(PIDConstants.RPM_Center - 50 < shooter.getRPM() && shooter.getRPM() < PIDConstants.RPM_Center + 50){
    		complete = true;
    	}
    }

    // Called once after timeout
    protected void end() {
    	if(complete){
    		System.out.println("Good");
    	} else {
    		System.out.println("Bad");
    	}
    	shooter.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
