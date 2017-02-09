package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRight extends CommandGroup {

    public G_GearRight() {
    	
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(76, 5));
    	addSequential(new A_Turn(5,.2));
    	//Add forward until Optical Sensor reads the tape
    	//Put gear on the peg
    }
}
