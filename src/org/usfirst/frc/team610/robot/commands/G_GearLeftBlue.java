package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeftBlue extends CommandGroup {

    public G_GearLeftBlue() {
    	
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_SetBallIntake(true));
    	addSequential(new A_PositionMoveFast(88, 3, .8));
    	addParallel(new A_Turret(0));
    	addSequential(new A_TurnOpticalFast(4, -0.6));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMoveFast(27,3, .5));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-12, 1, 1));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.8));
    	
    }
}
