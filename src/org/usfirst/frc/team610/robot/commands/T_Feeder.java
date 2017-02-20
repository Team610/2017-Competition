package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class T_Feeder extends Command {
	private HopperFeeder hopperFeeder;
	private Shooter shooter;
	private OI oi;

	public T_Feeder() {
		hopperFeeder = HopperFeeder.getInstance();
		shooter = Shooter.getInstance();
		oi = OI.getInstance();
	}

	protected void execute() {
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R1) && shooter.getShooterSpeed() > 1000) {
			hopperFeeder.setSpeed(PIDConstants.hopperSpeed);
			
		} else {
			hopperFeeder.setSpeed(0);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
