package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team611.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearForwardLeft extends CommandGroup {

    public G_GearForwardLeft() {
       
    	DriveTrain.getInstance().resetEncoders();
    	addSequential(new A_PositionMove(7*12, 3, 3));
    	addSequential(new A_PositionLock(1, -45));
    	addSequential(new A_PositionMove(24, 0, 4));
    }
}
