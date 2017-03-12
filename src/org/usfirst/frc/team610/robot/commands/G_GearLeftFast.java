package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeftFast extends CommandGroup {

    public G_GearLeftFast() {
    	DriveTrain.getInstance().resetEnc(); 
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMoveFast(88, 3, 1));
    	addSequential(new A_TurnOpticalFast(4, -1));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(30,3, .5));
    	addParallel(new A_Turret(0));
    	addSequential(new A_SetOuttake(true));
    	addSequential(new A_PositionMoveFast(-12, 1, 1));
    	addParallel(new A_Shoot(PIDConstants.RPM_SIDE, 0.6));
    }
}
