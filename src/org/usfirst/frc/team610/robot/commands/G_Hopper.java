package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_Hopper extends CommandGroup {

    public G_Hopper() {
    	System.out.println("Starting: Hopper");
    	GearIntake.getInstance().setOuttake(false); //closes gear manip
    	GearIntake.getInstance().setIntake(true); //opens intake portion to let gear fall in & centers manip
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(132, 5, 1));
    	DriveTrain.getInstance().resetAngle();
    	addSequential(new A_Turn(30,2));
    	addSequential(new A_Turn(-120,2));
    	DriveTrain.getInstance().resetEnc();
    	addSequential(new A_PositionMove(84,5, 1));
    	addSequential(new A_TurnOptical(5,-0.2));
    	DriveTrain.getInstance().resetEnc();
    	GearIntake.getInstance().setIntake(false); //lets intake float
    	addSequential(new A_PositionMove(36,5,1));
    	GearIntake.getInstance().setOuttake(true); //shoots gear
    }
}
