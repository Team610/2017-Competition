package org.usfirst.frc.team610.robot.commands;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class T_Shooter extends Command {

	private Shooter shooter;
	private OI oi;
	private boolean isTracking;

	private boolean isYPressed = false;

	private PID visionPID;
	private VisionServer server;

	private boolean led = false;

	private boolean isLeft = false;

	public T_Shooter() {
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		oi = OI.getInstance();
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
	}

	protected void initialize() {
		isTracking = false;
	}

	protected void execute() {
		double speed = visionPID.getValue(server.getDouble(), 0, 0);

		if (server.isTracking()) {
			shooter.setTurret(speed);
			if (shooter.getSensor()) {
				if (speed > 0) {
					isLeft = true;
				} else {
					isLeft = false;
				}
			}
		} else {
			if (shooter.getSensor()) {
				shooter.setTurret(0);
			} else if (isLeft) {
				shooter.setTurret(-0.5);
			} else if (!isLeft) {
				shooter.setTurret(0.5);
			}
		}

		// Displays to operator that turret is tracking
		SmartDashboard.putBoolean("Tracking", server.isTracking());

		// Operator can hold A until it starts tracking
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && server.isTracking() && !isTracking) {
			isTracking = true;
		} else if (!server.isTracking()) {
			isTracking = false;
		}

		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y) && !isYPressed) {
			isYPressed = true;
			led = !led;
		}
		if (!oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			isYPressed = false;
		}

		shooter.setLED(led);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
