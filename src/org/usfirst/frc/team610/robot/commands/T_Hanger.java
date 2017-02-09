package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.Hanger;

import edu.wpi.first.wpilibj.command.Command;

public class T_Hanger extends Command {
	private Hanger hanger;
	private OI oi;

	public T_Hanger() {
		hanger = Hanger.getInstance();
		oi = OI.getInstance();
	}

	protected void execute() {
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_L1)) {
			hanger.setHanger(1);
		} else {
			hanger.setHanger(0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
