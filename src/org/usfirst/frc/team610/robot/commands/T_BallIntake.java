package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.BallIntake;

import edu.wpi.first.wpilibj.command.Command;

public class T_BallIntake extends Command {

	private OI oi;
	private BallIntake ballIntake;

	public T_BallIntake() {
		oi = OI.getInstance();
		ballIntake = BallIntake.getInstance();
	}

	protected void execute() {
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			ballIntake.setIntake(1);
			ballIntake.deploy(true);
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)) {
			ballIntake.setIntake(-1);
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)) {
			ballIntake.setIntake(0.5);
			ballIntake.deploy(false);
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
