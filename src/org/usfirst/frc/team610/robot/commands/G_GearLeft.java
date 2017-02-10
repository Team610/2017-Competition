package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_GearLeft extends CommandGroup {

    public G_GearLeft() {
    	GearIntake.getInstance().setOuttake(false); //closes gear manip
    	GearIntake.getInstance().setIntake(true); //opens intake portion to let gear fall in & centers manip
    	DriveTrain.getInstance().resetEnc(); 
    	addSequential(new A_PositionMove(76, 5));
    	addSequential(new A_TurnOptical(5, -.2));
    	GearIntake.getInstance().setIntake(false); //lets intake float
    	addSequential(new A_PositionMove(36,5));
    	GearIntake.getInstance().setOuttake(true); //shoots gear
    }
}
