package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeftBlue extends CommandGroup {

    public G_GearLeftBlue() {
    	DriveTrain.getInstance().resetEnc(); 
    	addSequential(new A_PositionMove(0, 0.5, 0));
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMove(90, 3, .75));
    	addSequential(new A_TurnOptical(4, -.6));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(26,3, .35));
    	addParallel(new A_Turret());
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_PositionMove(-12, 1, 1));
    	addSequential(new A_Shoot(PIDConstants.RPM_SIDE, 0.6));
    }
}
