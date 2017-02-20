package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightRed extends CommandGroup {

    public G_GearRightRed() {
    	DriveTrain.getInstance().resetEnc(); 
//    	addSequential(new A_PositionMove(0, 0.5, 0));
    	
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMove(98, 3, .75));
    	addSequential(new A_TurnOptical(10000, .6));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(28,3, .35));
    	addParallel(new A_Turret());
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMove(-12, 2, 1));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.6));
    	
    }
}
