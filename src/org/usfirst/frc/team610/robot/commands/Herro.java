package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Herro extends CommandGroup {

    public Herro() {
        // Add Commands here:
    	addSequential(new A_Setup());
		addSequential(new A_SetIntake(true));
		addSequential(new A_SetBallIntake(true));
		addSequential(new A_PositionMove(88, 3, .8, false));
		addSequential(new A_TurnOptical(10000, 0.4, 50, true));
		addSequential(new A_SetIntake(false));
		addSequential(new A_PositionMove(27, 3, .4, true));
		addSequential(new A_SetOuttake(true));
		addSequential(new A_PositionMove(-24, 2, 1, false));
		//I'm not sure if this is athing so this is teh first thing to test. Should make a backup just in case it doesn't work
		
		addSequential(new A_Turn(50, 2));
		addSequential(new A_ConditionalMove(-250, 3, 1, false));
//		addSequential(new A_TurnOptical(2, -0.4, 60, true));
//		addSequential(new A_SetOuttake(true));
//		addSequential(new A_PositionMove(-24, 2, 1, false));
    }
}
