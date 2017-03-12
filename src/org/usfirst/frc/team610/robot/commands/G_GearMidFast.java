package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearMidFast extends CommandGroup {

    public G_GearMidFast() {
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(false));

    	addParallel(new A_Shoot(PIDConstants.RPM_Center, 0.4));
    	addSequential(new A_PositionMoveFast(80, 3, .75));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_Turret(0));
    	addSequential(new A_PositionMoveFast(-24, 2, 1));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.6));
    }
}
