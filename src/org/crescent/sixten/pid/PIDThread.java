package org.crescent.sixten.pid;

import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

public class PIDThread implements Runnable {

	private static PIDThread instance;

	private PID pid;
	private Shooter shooter;
	private double output;
	private double input;
	private VisionServer server;
	private double rpm;

	public static PIDThread getInstance() {
		if (instance == null) {
			instance = new PIDThread();
		}
		return instance;
	}

	private PIDThread() {
		pid = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		rpm = 0;
		new Thread(this).start();
	}

	public double getOutput() {
		return output;
	}

	@Override
	public void run() {
		System.out.println("PID Thread started");
		while (true) {
			switch (shooter.getState()) {
			case AUTO:
				rpm = server.getRPM();
				break;
			case CENTER:
				rpm = PIDConstants.RPM_Center;
				break;
			case SIDE:
				rpm = PIDConstants.RPM_SIDE;
				break;
			case FAR:
				rpm = PIDConstants.RPM_DIAMOND;
				break;
			default:
				rpm = 0;
				break;
			}
			input = shooter.getRPM();
			output = pid.getValue(rpm, input, shooter.getFeedForward(input));
		}
	}

}
