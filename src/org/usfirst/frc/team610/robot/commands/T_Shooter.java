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

	private boolean isAPressed = false;

	private PID visionPID;
	private VisionServer server;

	private boolean isLeft = false;

	public T_Shooter() {
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		oi = OI.getInstance();
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
	}

	protected void initialize() {
		isTracking = false;
		isLeft = true;
	}

	protected void execute() {
		double speed = visionPID.getValue(server.getDouble(), 0, 0);

		// Displays to operator that turret is tracking
		SmartDashboard.putBoolean("Tracking", server.isTracking());

		// Operator can hold A until it starts tracking
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && !isAPressed) {
			isTracking = !isTracking;
			isAPressed = true;
		}

		if (!oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			isAPressed = false;
		}

		// if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R2)) {
		// shooter.setShooterRPM(60);
		// } else {
		// shooter.setShooterRPM(0);
		// }
		// shooter.setPower(0.2);
		System.out.println(shooter.getShooterPeriod());

		if (server.isTracking() && isTracking) {
			shooter.setTurret(speed);
			if (shooter.getSensor()) {
				if (speed > 0) {
					isLeft = true;
				} else {
					isLeft = false;
				}
			}
		} else if (!isTracking) {
			/*
			 * if (Math.abs(oi.getOperator().getRawAxis(LogitechF310Constants.
			 * AXIS_LEFT_X)) > 0.1) {
			 * shooter.setTurret(oi.getOperator().getRawAxis(
			 * LogitechF310Constants.AXIS_LEFT_X)); } else
			 *
			 */
			// shooter.setPower(0);
			if (shooter.getSensor()) {
				shooter.setTurret(0);
			} else if (isLeft) {
				shooter.setTurret(-0.2);
			} else if (!isLeft) {
				shooter.setTurret(0.2);
			}
		} else {
			if (oi.getOperator().getPOV() == 90) {
				shooter.setTurret(.5);
				if (shooter.getSensor()) {
					isLeft = true;
				}

			} else if (oi.getOperator().getPOV() == 270) {
				shooter.setTurret(-.5);
				if (shooter.getSensor()) {
					isLeft = false;
				}
			} else {
				shooter.setTurret(0);
			}
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
