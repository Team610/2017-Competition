package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class A_Auton extends CommandGroup {
	public A_Auton() {
//		addSequential(new A_ResetSensors(1));
//		addSequential(new A_GearTurn(5));
//		addSequential(new A_PositionMove(24, 3));
		addSequential(new A_TurnOptical(5, .5));
	}
}
