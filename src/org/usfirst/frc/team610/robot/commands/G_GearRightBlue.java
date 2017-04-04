package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightBlue extends CommandGroup {

    public G_GearRightBlue() {
    	
    	
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_SetBallIntake(true));
    	addSequential(new A_PositionMoveFast(88, 3, .8, false));
    	addSequential(new A_TurnOpticalFast(10000, 0.4, 50, true));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMoveFast(27,3, .4, true));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-24, 2, 1, false));
    	addSequential(new A_TurnOpticalFast(2, -0.8, 50, false));
    	addSequential(new A_PositionMoveFast(150, 3, 1, false));
    	
    }
}
