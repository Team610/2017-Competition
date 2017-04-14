package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.BallIntake;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class T_Feeder extends Command {
	private HopperFeeder hopperFeeder;
	private BallIntake ballIntake;
	private Shooter shooter;
	private int counter;
	private boolean deploy;
	private OI oi;

	public T_Feeder() {
		hopperFeeder = HopperFeeder.getInstance();
		ballIntake = BallIntake.getInstance();
		shooter = Shooter.getInstance();
		oi = OI.getInstance();
		deploy = true;
	}

	protected void execute() {
		PIDConstants.Update();
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R1) && shooter.getRPM() > 1000) {
			hopperFeeder.setSpeed(PIDConstants.HOPPER_SPEED);
			counter++;
			
			ballIntake.deploy(true);
			ballIntake.setIntake(1);
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R2)) {
			hopperFeeder.setSpeed(-1);
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
