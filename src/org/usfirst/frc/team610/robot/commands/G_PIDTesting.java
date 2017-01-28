package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class G_PIDTesting extends CommandGroup {
    public G_PIDTesting() {
        
    	DriveTrain.getInstance().resetEncoders();
//    	addSequential(new A_PositionLock(10,90));
//    	addSequential(new A_PositionLock(10,90));
    	addSequential(new A_PositionMove(250,0,1,7.5));
    	
    }
}
