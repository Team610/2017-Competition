package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class A_Auton extends CommandGroup{
	public A_Auton(boolean left){
		if(left){
			addSequential(new A_PositionMove(10,5));
			addSequential(new A_Turn(45,5));
			addSequential(new A_PositionMove(2,5));
		}
		else{
			addSequential(new A_PositionMove(10,5));
			addSequential(new A_Turn(-45,5));
			addSequential(new A_PositionMove(2,5));
		}
	}

}
