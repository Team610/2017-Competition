package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightBlue extends CommandGroup {

	public G_GearRightBlue() {

		
		addSequential(new A_Setup());
//		addSequential(new A_SetIntake(true));
		addSequential(new A_SetBallIntake(true));
		addSequential(new A_PositionMove(PIDConstants.INIT_DRIVE_BLUE, 3, .8, false));
		addSequential(new A_TurnOptical(10000, 0.4, 50, true));
//		addSequential(new A_SetIntake(false));
		addSequential(new A_PositionMove(PIDConstants.PEG_DRIVE, 3, .4, true));
		addSequential(new A_SetOuttake(true));
		addSequential(new A_PositionMove(-24, 2, 1, false));
		addSequential(new A_TurnOptical(2, -0.8, 40, false));
		addSequential(new A_ConditionalMove(250, 3, 1, false));
	}
}
