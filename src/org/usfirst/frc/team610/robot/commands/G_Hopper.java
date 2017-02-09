package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_Hopper extends CommandGroup {

    public G_Hopper() {
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(132, 5));
    	DriveTrain.getInstance().resetAngle();
    	addSequential(new A_Turn(30,2));
    	addSequential(new A_Turn(-120,2));
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(84,5));
    }
}
