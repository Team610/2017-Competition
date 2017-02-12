package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_Teleop extends CommandGroup {
	public G_Teleop() {
		addParallel(new T_Drive());
		addParallel(new T_GearIntake());
		addParallel(new T_Hanger());
		addParallel(new T_Feeder());
		addParallel(new T_Shooter());
		addParallel(new T_BallIntake());
	}
}
