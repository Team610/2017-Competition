package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.PDBConstants;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PDB extends Subsystem {
	
	private static PDB instance;
	
	private PowerDistributionPanel pdb;
	
	public static PDB getInstance(){
		if(instance == null){
			instance = new PDB();
		}
		return instance;
	}

	private PDB(){
		pdb = new PowerDistributionPanel();
	}
	
	public double getDriveLeftA(){
		return pdb.getCurrent(PDBConstants.DRIVE_LEFT_BACK_0);
	}
	
	public double getDriveLeftB(){
		return pdb.getCurrent(PDBConstants.DRIVE_LEFT_FRONT_1);
	}
	
	public double getDriveRightA(){
		return pdb.getCurrent(PDBConstants.DRIVE_RIGHT_BACK_2);
	}
	
	public double getDriveRightB(){
		return pdb.getCurrent(PDBConstants.DRIVE_RIGHT_FRONT_3);
	}
	
	public double getShooter(){
		return pdb.getCurrent(PDBConstants.SHOOTER_4);
	}
	
	public double getVibrator(){
		return pdb.getCurrent(PDBConstants.VIBRATOR_5);
	}
	
	public double getHanger(){
		return pdb.getCurrent(PDBConstants.HANGER_6);
	}
	
	public double getIntake(){
		return pdb.getCurrent(PDBConstants.INTAKE_7);
	}
	
	public double getFeeder(){
		return pdb.getCurrent(PDBConstants.FEEDER_8);
	}
	
	public double getTurret(){
		return pdb.getCurrent(PDBConstants.TURRET_9);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

