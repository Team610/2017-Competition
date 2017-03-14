package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRightFast extends CommandGroup {

    public G_GearRightFast() {
    	DriveTrain.getInstance().resetEnc(); 
//    	addSequential(new A_PositionMove(0, 0.5, 0));
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMoveFast(82, 3, 1));
    	addSequential(new A_TurnOpticalFast(10000, 1));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMoveFast(27,3, .5));
    	addParallel(new A_Turret(20));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-12, 2, 1));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.6));
    }
}
