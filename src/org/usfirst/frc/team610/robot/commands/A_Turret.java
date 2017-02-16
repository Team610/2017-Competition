package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_Turret extends Command {

	PID visionPID;
	VisionServer server;
	Shooter shooter;
	double speed;
	double counter;
	
    public A_Turret() {

    	visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
    	server = VisionServer.getInstance();
    	shooter = Shooter.getInstance();
    	speed = .5;
    	counter = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	shooter.setLED(true);
    	if(server.isTracking()){
    		speed = visionPID.getValue(server.getDouble(), 0, 0);
    	} else {
    		if(counter < 50){
    			speed = -speed;
    		} else {
    			counter = 0;
    		}
    		counter++;
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
