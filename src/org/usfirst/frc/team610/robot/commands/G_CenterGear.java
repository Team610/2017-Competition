package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterGear extends CommandGroup{
    public G_CenterGear() {
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(0, 0.5, 0));
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(false));
    	addSequential(new A_PositionMove(69, 3, .75));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_PositionMove(-12, 2, 1));
    }
}
