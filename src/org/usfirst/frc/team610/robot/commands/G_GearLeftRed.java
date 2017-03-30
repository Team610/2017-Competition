package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeftRed extends CommandGroup {

	public G_GearLeftRed() {
		
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_SetBallIntake(true));
    	addSequential(new A_PositionMoveFast(82, 3, 1));
    	addSequential(new A_TurnOpticalFast(4, -0.75));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(27,3, .5));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-12, 1, 1));
    	
    }
}
