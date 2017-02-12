package org.usfirst.frc.team610.robot.subsystems;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	static Shooter instance;
	private Victor shooter;
	private Victor turret;
	private double RPMfactor;
	private Counter shooterCounter;
	private DigitalInput positionSensor;
	private Relay spike;

	public static Shooter getInstance() {
		if (instance == null)
			instance = new Shooter();
		return instance;
	}

	public Shooter() {
		RPMfactor = 60; // min/s
		shooterCounter = new Counter(ElectricalConstants.SHOOTER_SENSOR);
		shooter = new Victor(ElectricalConstants.SHOOTER_MOTOR);
		turret = new Victor(ElectricalConstants.TURRET_MOTOR);
		positionSensor = new DigitalInput(ElectricalConstants.TURRET_SENSOR);
		spike = new Relay(ElectricalConstants.SPIKE);
	}

	public double getFeedForward(double rpm){
		return rpm / 6000.0;
	}
	
	// Set shooter at certain RPM

	public void setLED(boolean on) {
		if (on) {
			spike.set(Relay.Value.kForward);
		} else {
			spike.set(Relay.Value.kOff);
		}
	}

	// Set power to motor
	public void setPower(double power) {
		shooter.set(power);
	}

	// Get rpm of the shooter
	public double getShooterSpeed() {
		return RPMfactor / getShooterPeriod(); // getShooterPeriod()
	}

	public void setTurret(double speed) {
		turret.set(speed);
	}

	public boolean getSensor() {
		return !positionSensor.get();
	}

	// Gets the time between counts
	public double getShooterPeriod() {
		return shooterCounter.getPeriod();
	}

	public boolean atPosition() {
		// if(positionSensor.get()){
		// isOnPos = false;
		// }
		// else{
		// if(isOnPos == false){
		// isOnPos = true;
		// counter++;
		// }
		// }
		return !positionSensor.get();
	}

	// public void vision(){
	// setTurret(turretPID.getValue(VisionServer.getInstance().getDouble(), 0,
	// 0));
	// }

	public void setShooterPos(int pos) { // one = home, two = red, three = blue
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
