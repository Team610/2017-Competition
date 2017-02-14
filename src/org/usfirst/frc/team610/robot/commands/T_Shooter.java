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

	
	private PID shooterPID;
	
	private boolean isLeft = false;
	
	private double rpm = 0;

	public T_Shooter() {
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		oi = OI.getInstance();
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
		shooterPID = new PID(PIDConstants.SHOOTER_P,PIDConstants.SHOOTER_I,PIDConstants.SHOOTER_D);
	}

	protected void initialize() {
		isTracking = false;
		isLeft = true;
		shooterPID.updatePID(PIDConstants.SHOOTER_P,PIDConstants.SHOOTER_I,PIDConstants.SHOOTER_D);
		rpm = 0;
	}

	protected void execute() {
		double speed = visionPID.getValue(server.getDouble(), 0, 0);
		double shooterSpeed = shooterPID.getValue(shooter.getShooterSpeed(), 3000, shooter.getFeedForward(3000));
		if(shooterSpeed<0)
			shooterSpeed = 0;
		// Displays to operator that turret is tracking
		SmartDashboard.putBoolean("Tracking", server.isTracking());
		
		
		SmartDashboard.putNumber("RPM", shooter.getShooterPeriod());
		
		
		// Operator can hold A until it starts tracking
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && !isAPressed) {
			isTracking = !isTracking;
			isAPressed = true;
		}

		if (!oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			isAPressed = false;
		}

		 if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_R2)) {
			 shooter.setPower(-shooterSpeed);
		 } else {
			 shooter.setPower(0);
		 }
		System.out.println(PIDConstants.SHOOTER_P + " and " + shooter.getShooterSpeed());
		
		if (server.isTracking() && isTracking) {
			shooter.setTurret(speed);
//			shooter.setLED(true);
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
//				shooter.setTurret(-0.2);
			} else if (!isLeft) {
//				shooter.setTurret(0.2);
			}
			shooter.setLED(false);
		} else {
//			shooter.setLED(true);
			rpm = PIDConstants.RPM;
			if (oi.getOperator().getPOV() == 90) {
//				shooter.setTurret(.5);
				if (shooter.getSensor()) {
					isLeft = true;
				}

			} else if (oi.getOperator().getPOV() == 270) {
//				shooter.setTurret(-.5);
				if (shooter.getSensor()) {
					isLeft = false;
				}
			} else {
//				shooter.setTurret(0);
			}
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
