package org.usfirst.frc.team610.robot.subsystems;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	
	static Shooter instance;
	private Victor shooter;
	private double RPMfactor;
	private double shooterPeriod;
	private PID shooterPID;
	static Counter shooterCounter;
	private OI oi;
	
	public static Shooter getInstance(){
		if(instance == null)
			instance = new Shooter();
		return instance;
	}
	
	public Shooter(){
		RPMfactor = 60;
		shooterCounter = new Counter(ElectricalConstants.shooterEncoder);
		shooter = new Victor(ElectricalConstants.shooterMotor);
		
		shooterCounter.setMaxPeriod(.1);
		shooterCounter.setDistancePerPulse(1);
		shooterCounter.setSamplesToAverage(1);
		shooterCounter.reset();
		
		shooterPeriod = Double.POSITIVE_INFINITY;
		shooterPID = new PID(0,0,0); //change to PID Constants
		oi = OI.getInstance();
	}
	
	public void setShooter(double rpm){
		double curSpeed = getShooterSpeed();
		double target = rpm;
		double shooterPower = 0;
		shooterPower = shooterPID.getValue(getShooterSpeed(), 4000) + getFeedForward(4000); //change to shooter constants
		if(shooterPower > 0)
			setPower(shooterPower);
	}
	
	public void setPower(double power){
		shooter.set(power);
	}
	
	public double getShooterSpeed(){
		return RPMfactor/getShooterPeriod();
	}
	
	public double getShooterPeriod(){
		double shooterPeriod = shooterCounter.getPeriod();
		if(shooterPeriod < 1.66e-4)
			return this.shooterPeriod;
		else{
			this.shooterPeriod = shooterPeriod;
			return shooterPeriod;
		}
	}
	
	public double getFeedForward(double rpm){
		return 1.4e-4 * rpm - 0.05; //change for this years feedforward
	}
}
