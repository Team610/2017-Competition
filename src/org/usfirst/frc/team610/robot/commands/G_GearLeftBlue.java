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
    	addSequential(new A_PositionMoveFast(88, 3, .8, false));
    	addParallel(new A_Turret(0));
    	addSequential(new A_TurnOpticalFast(4, -0.4, 50, true));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMoveFast(27,3, .4, true));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-12, 1, 1, false));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.8));
    	
    	
    	//Model

//    	addSequential(new A_Setup());
//    	addSequential(new A_SetIntake(true));
//    	addSequential(new A_SetBallIntake(true));
//    	addSequential(new A_PositionMoveFast(88, 3, 1, false));
//    	addSequential(new A_TurnOpticalFast(4, -0.4));
//    	addSequential(new A_SetIntake(false));
//    	addSequential(new A_PositionMoveFast(27,3, .3, true));
//    	addSequential(new A_SetOuttake(true));
//    	addSequential(new A_PositionMoveFast(-12, 1, 1, false));
    	
    }
}
