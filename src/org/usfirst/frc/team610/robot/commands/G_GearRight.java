package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearRight extends CommandGroup {

    public G_GearRight() {
    	DriveTrain.getInstance().resetEnc(); 
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(true));
    	addSequential(new A_PositionMove(88, 3, .75));
    	addSequential(new A_TurnOptical(10000, .4));
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(28,3, .35));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_PositionMove(-12, 2, 1));
    }
}
