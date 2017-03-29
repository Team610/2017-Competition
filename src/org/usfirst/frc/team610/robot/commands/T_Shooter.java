package org.usfirst.frc.team610.robot.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.crescent.sixten.pid.PID;
import org.crescent.sixten.pid.PIDThread;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;
import org.usfirst.frc.team610.robot.subsystems.ShooterState;
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
	
	private double rpmTrim;
	
	private double shooterSpeed;
	
	private double setRPM;

	private double x;
	
	private double speed;

	public boolean isAuto = true;
	

	public T_Shooter() {
		rpms = new ArrayList<Double>();
		sortedRPMs = new ArrayList<Double>();
		shooter = Shooter.getInstance();
		server = VisionServer.getInstance();
		oi = OI.getInstance();
		shooterSpeed = 0;
		visionPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
		shooterPID = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D, 0, 1);
	}

	protected void initialize() {
		isTracking = false;
		shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
		trim = 0;
		rpmTrim = 0;
		setRPM = 0;
		speed = 0;
		isPOV = false;
		x = oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_LEFT_X);
	}

	@SuppressWarnings("unchecked")
	protected void execute() {
		PIDConstants.Update();
		
		SmartDashboard.putNumber("RPM", rpm);
		SmartDashboard.putNumber("ShooterSpeed", shooterSpeed);
		SmartDashboard.putNumber("RPMTrim", rpmTrim);
		SmartDashboard.putNumber("TurretTrim", trim);;
		
		//RPM Median filter
		if (rpms.size() < 5) {
			rpms.add(shooter.getRPM());
			rpm = shooter.getRPM();
		} else {
			rpms.remove(0);
			rpms.add(shooter.getRPM());
			sortedRPMs = (ArrayList<Double>) rpms.clone();
			Collections.sort(sortedRPMs);
			if (sortedRPMs.get(2) < 6000) {
				rpm = sortedRPMs.get(2);
			}
		}

//		// Displays to operator that turret is tracking
//		SmartDashboard.putBoolean("Tracking", server.isTracking());

		shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
		visionPID.updatePID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);

		speed = visionPID.getValue(server.getDouble() + trim, 0, 0);

		
		//RPM setters
		if(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_RIGHT_Y) < -0.9){
			setRPM = PIDConstants.RPM_DIAMOND;
			shooter.setState(ShooterState.FAR);
			SmartDashboard.putString("RPM_SetPoint", "Diamond");
			isAuto = false;
		} else if(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_RIGHT_Y) > 0.9){
			setRPM = PIDConstants.RPM_Center;
			shooter.setState(ShooterState.CENTER);
			SmartDashboard.putString("RPM_SetPoint", "Center");
			isAuto = false;
		} else if(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X) < - 0.9){
			setRPM = PIDConstants.RPM_LINE;
			shooter.setState(ShooterState.LINE);
			SmartDashboard.putString("RPM_SetPoint", "Line");
			isAuto = false;
		} else if(oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X) > 0.9){
			setRPM = PIDConstants.RPM_SIDE;
			shooter.setState(ShooterState.SIDE);
			SmartDashboard.putString("RPM_SetPoint", "Side");
			isAuto = false;
		} 
				
		if(oi.getOperator().getRawButton(LogitechF310Constants.BTN_RS)){
			isAuto = true;
		}
		if(isAuto && !oi.getOperator().getRawButton(LogitechF310Constants.BTN_R1)) {
			setRPM = server.getRPM();
			SmartDashboard.putString("RPM_SetPoint", "Auto");
		}
		
		
		shooterSpeed = shooterPID.getValue(rpm, setRPM + rpmTrim, shooter.getFeedForward(setRPM));
		shooter.setPower(shooterSpeed);

		// Operator press A to track
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && !isAPressed) {
			isTracking = !isTracking;
			isAPressed = true;
		}

		if (!oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			isAPressed = false;
		}

//
//		SmartDashboard.putNumber("Target", server.getDouble());
		x = oi.getOperator().getRawAxis(LogitechF310Constants.AXIS_LEFT_X);

		
		if (server.isTracking() && isTracking) { //Tracking and led on
			shooter.setLED(true);
			if(Math.abs(x) > 0.5){
				shooter.setTurret(x * 0.6);
			} else {
				shooter.setTurret(speed);
			}
			if (shooter.getSensor()) {
				if (speed > 0) {
					shooter.isLeft = true;
				} else {
					shooter.isLeft = false;
				}
			}
		} else if (!isTracking) { //Not tracking and led off
			if(Math.abs(x) > .5){
				shooter.setTurret(x * 0.6);
			}
			else if(shooter.isLeft && !shooter.getSensor()){
				shooter.setTurret(-0.15);
			} else if (!shooter.isLeft && !shooter.getSensor()){
				shooter.setTurret(0.15);
			} else {
				shooter.setTurret(0);
			}
			
			if(shooter.getSensor()){
				if(x > 0){
					shooter.isLeft = true;
				} else {
					shooter.isLeft = false;
				}
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
		
		// Turret trimming left and right. RPM trimming up and down
		if(oi.getOperator().getPOV()==90 && !isPOV){
			trim -= 10;
			isPOV = true;
		} else if(oi.getOperator().getPOV() == 270 && !isPOV){
			trim += 10;
			isPOV = true;
		} else if(oi.getOperator().getPOV() == 0 && !isPOV){
			rpmTrim += 10;
			isPOV = true;
		} else if(oi.getOperator().getPOV() == 180 && !isPOV){
			rpmTrim -= 10;
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
