package org.usfirst.frc.team610.robot.commands;

import java.util.ArrayList;
import java.util.Collections;

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

	private double rpm;
	private ArrayList<Double> rpms;
	private ArrayList<Double> sortedRPMs;

	private PID shooterPID;
	
	private double trim;
	
	private boolean isPOV;

//	public boolean isLeft = false;

	public T_Shooter() {
		rpms = new ArrayList<Double>();
		sortedRPMs = new ArrayList<Double>();
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		oi = OI.getInstance();
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
		shooterPID = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D, 0, 1);
		
	}

	protected void initialize() {
		isTracking = false;
		shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
		trim = 0;
		isPOV = false;
	}

	@SuppressWarnings("unchecked")
	protected void execute() {
		PIDConstants.Update();
		if (rpms.size() < 5) {
			rpms.add(shooter.getShooterSpeed());
			rpm = shooter.getShooterSpeed();
		} else {
			rpms.remove(0);
			rpms.add(shooter.getShooterSpeed());
			sortedRPMs = (ArrayList<Double>) rpms.clone();
			Collections.sort(sortedRPMs);
			if (sortedRPMs.get(2) < 6000) {
				rpm = sortedRPMs.get(2);
			}
		}


		

		// Displays to operator that turret is tracking
		SmartDashboard.putBoolean("Tracking", server.isTracking());

		shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
		visionPID.updatePID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);

		double speed = visionPID.getValue(server.getDouble() + trim, 0, 0);
		double shooterSpeed = shooterPID.getValue(rpm, PIDConstants.RPM, shooter.getFeedForward(PIDConstants.RPM));

//		double shooterSpeed = shooterPID.getValue(rpm, server.getRPM(), shooter.getFeedForward(PIDConstants.RPM));
		
		SmartDashboard.putNumber("RPM", rpm);
		SmartDashboard.putNumber("Y-Dist", server.getHeight());
		SmartDashboard.putNumber("ShooterSpeed", shooterSpeed);
		// if(rpm < PIDConstants.RPM){
		// shooterSpeed = 1;
		// } else {
		// shooterSpeed = shooter.getFeedForward(PIDConstants.RPM);
		// }

		shooter.setPower(-shooterSpeed);

		// Operator press A to track
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && !isAPressed) {
			isTracking = !isTracking;
			isAPressed = true;
		}

		if (!oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			isAPressed = false;
		}


		SmartDashboard.putNumber("Target", server.getDouble());
		double x = oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_LEFT_X);

		if (server.isTracking() && isTracking) { //Tracking and led on
			
			shooter.setTurret(speed);
			shooter.setLED(true);
			if (shooter.getSensor()) {
				if (speed > 0) {
					shooter.isLeft = true;
				} else {
					shooter.isLeft = false;
				}
			}
		} else if (!isTracking) { //Not tracking and led off
		
//			shooter.setTurret(x * 0.2);
			if(shooter.isLeft && !shooter.getSensor()){
				shooter.setTurret(-0.15);
			} else if (!shooter.isLeft && !shooter.getSensor()){
				shooter.setTurret(0.15);
			} else {
				shooter.setTurret(0);
			}
			
			
			shooter.setPower(0);
			shooter.setLED(false);
		} else { //not tracking and led on
			shooter.setLED(true);
			if(shooter.getSensor()){
				if(x > 0){
					shooter.isLeft = true;
				} else {
					shooter.isLeft = false;
				}
			}
			shooter.setTurret(x * 0.6);
		}
		
		if(oi.getOperator().getPOV()==90 && !isPOV){
			trim += 5;
			isPOV = true;
		} else if(oi.getOperator().getPOV() == 270 && !isPOV){
			trim -= 5;
			isPOV = true;
		}
		if(oi.getOperator().getPOV() == -1){
			isPOV = false;
		}
		

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
