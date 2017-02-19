package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_GearCenter extends CommandGroup{
    public G_GearCenter() {
    	addSequential(new A_Setup());
    	addSequential(new A_SetIntake(false));

    	addParallel(new A_Shoot());
    	addSequential(new A_PositionMove(75, 3, .5));
    	addSequential(new A_SetOuttake(true));
    	addParallel(new A_Turret());
    	addSequential(new A_PositionMove(-12, 2, 1));
    	
    }
}
