package org.usfirst.frc.team610.robot.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team610.robot.subsystems.PDB;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class D_CurrentWarning extends Command {

	private PDB pdb;
	private ArrayList<Double> feeder;
	private ArrayList<Double> sortedFeeder;
	private double feederMedian;
	private int feederCounter;
	
    public D_CurrentWarning() {
    	pdb = PDB.getInstance();
    	feeder = new ArrayList<Double>();
    	sortedFeeder = new ArrayList<Double>();
    	feederMedian = 0;
    	feederCounter = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("unchecked")
	protected void execute() {
    	if (feeder.size() < 11) {
			feeder.add(pdb.getFeeder());
			feederMedian = pdb.getFeeder();
		} else {
			feeder.remove(0);
			feeder.add(pdb.getFeeder());
			sortedFeeder = (ArrayList<Double>) feeder.clone();
			Collections.sort(sortedFeeder);
			feederMedian = sortedFeeder.get(5);
			if(pdb.getFeeder() - feederMedian > 5){
				feederCounter ++;
			} else {
				feederCounter = 0;
			}
		}
    	
    	SmartDashboard.putNumber("FeederCounter", feederCounter);
    	if(feederCounter > 5){
    		System.out.println("Current Warning: Feeder");
    		feederCounter = 0;
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
