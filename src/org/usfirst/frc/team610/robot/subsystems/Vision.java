package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {

	private static Vision instance;
	private NetworkTable table;
	
	
	public static Vision getInstance(){
		if(instance == null){
			instance = new Vision();
		}
		return instance;
	}
	
	private Vision() {
    	table = NetworkTable.getTable("datatable");
	}
	
	public double getValue(){
		return table.getNumber("xValue", 0);
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

