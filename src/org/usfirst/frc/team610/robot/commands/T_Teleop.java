package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class T_Teleop extends CommandGroup{
	public T_Teleop(){
		addParallel(new T_Drive());
		addParallel(new T_GearIntake());
	}
}
