package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightRed extends CommandGroup {

    public G_GearRightRed() {
    	
    	addSequential(new A_Setup());
//    	addSequential(new A_SetIntake(true));
    	addSequential(new A_SetBallIntake(true));
    	addSequential(new A_PositionMove(PIDConstants.INIT_DRIVE_RED, 3, .8, false));
    	addParallel(new A_Turret(0));
    	addSequential(new A_TurnOptical(10000, 0.4, 50, true));
//    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(PIDConstants.PEG_DRIVE,3, .4, true));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMove(-12, 2, 1, false));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.8, true));
    	
    }
}
