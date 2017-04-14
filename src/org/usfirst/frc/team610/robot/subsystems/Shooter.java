package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

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
	public boolean isLeft;
	private ShooterState state;


	public static Shooter getInstance() {
		if (instance == null)
			instance = new Shooter();
		return instance;
	}

	public Shooter() {
		RPMfactor = 60; // min/s
		shooterCounter = new Counter(ElectricalConstants.SHOOTER_SENSOR);
		shooterCounter.setSamplesToAverage(1);
		shooterCounter.setMaxPeriod(0.1);
		shooter = new Victor(ElectricalConstants.SHOOTER_MOTOR);
		turret = new Victor(ElectricalConstants.TURRET_MOTOR);
		positionSensor = new DigitalInput(ElectricalConstants.TURRET_SENSOR);
		spike = new Relay(ElectricalConstants.SPIKE);
		isLeft = false;
		state = ShooterState.AUTO;
	}

	public double getFeedForward(double rpm){
		return 0.0001666666666666666 * rpm ;

	}
	
	// Set shooter at certain RPM

	public void setLED(boolean on) {
		if (on) {
			spike.set(Relay.Value.kForward);
		} else {
			spike.set(Relay.Value.kReverse);
		}
	}

	// Set power to motor
	public void setPower(double power) {
		shooter.set(-power);
	}

	// Get rpm of the shooter
	public double getRPM() {
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
	
	public double getFeeder(double rpm){
		return -3e-6 * rpm * rpm + 0.227 * rpm -37.5;
	}
	
	//y = -3E-06x2 + 0.0227x - 37.5


	// public void vision(){
	// setTurret(turretPID.getValue(VisionServer.getInstance().getDouble(), 0,
	// 0));
	// }
	
	public ShooterState getState(){
		return state;
	}
	
	public void setState(ShooterState state){
		this.state = state;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
