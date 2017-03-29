package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;

public class T_Feeder extends Command {
	private HopperFeeder hopperFeeder;
	private Shooter shooter;
	private OI oi;
	private VisionServer server;

	public T_Feeder() {
		hopperFeeder = HopperFeeder.getInstance();
		shooter = Shooter.getInstance();
		oi = OI.getInstance();
		server = VisionServer.getInstance();
	}

	protected void execute() {
		PIDConstants.Update();
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R1) && shooter.getRPM() > 1000) {
			hopperFeeder.setSpeed(shooter.getFeeder(server.getRPM()));

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
