package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeftFast extends CommandGroup {

    public G_GearLeftFast() {
    	DriveTrain.getInstance().resetEnc(); 
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMoveFast(90, 3, 1));
    	addSequential(new A_TurnOpticalFast(4, -1));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(26,3, .5));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_PositionMoveFast(-12, 1, 1));
    }
}
