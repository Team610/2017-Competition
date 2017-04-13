package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearCenter extends CommandGroup {

    public G_GearCenter() {
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(77, 3, .5, true));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMove(-24, 2, 1, false));
    	addParallel(new A_Turret(0));
    	addParallel(new A_Shoot(PIDConstants.RPM_Center, 0.8));
    	
    }
}
