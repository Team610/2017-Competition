package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeft extends CommandGroup {

    public G_GearLeft() {
        
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(132, 1));
    	addSequential(new A_TurnOptical(5,.5));
    	//Add forward until Optical Sensor reads the tape
    	//Put gear on the peg
    	
    }
}
