package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeft extends CommandGroup {

    public G_GearLeft() {
        
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove((13 * 12 + 9.5) * 12/17, 1));
    	addSequential(new A_Turn(30, 1));
    	
    	
    }
}
