package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_Turret extends Command {

	private PID visionPID;
	private VisionServer server;
	private Shooter shooter;
	private double speed;
	private double counter;
	private int offset;
	
	
    public A_Turret(int offset) {
    	this.offset = offset;
    	visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
    	server = VisionServer.getInstance();
    	shooter = Shooter.getInstance();
    	speed = -.3;
    	counter = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	shooter.setLED(true);
    	SmartDashboard.putBoolean("Tracking", server.isTracking());
    	SmartDashboard.putNumber("Target", server.getDouble());
    	if(server.isTracking()){
    		speed = visionPID.getValue(server.getDouble() + offset, 0, 0);
    	} else {
    		if(counter > 40){
    			speed = -speed;
    			counter = 0;
    			
    		}
    		counter++;
    	}
    	
    	if(speed < 0 && shooter.getSensor()){
    		shooter.isLeft = false;
    	} else if(speed > 0 && shooter.getSensor()){
    		shooter.isLeft = true;
    	}
    	shooter.setTurret(speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.setLED(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
