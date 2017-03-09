package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightFast extends CommandGroup {

    public G_GearRightFast() {
    	DriveTrain.getInstance().resetEnc(); 
//    	addSequential(new A_PositionMove(0, 0.5, 0));
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMoveFast(90, 3, 1));
    	addSequential(new A_TurnOpticalFast(10000, 1));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMoveFast(22,3, .5));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_PositionMoveFast(-12, 2, 1));
    }
}
