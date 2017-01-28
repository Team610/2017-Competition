package org.usfirst.frc.team610.robot.subsystems;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	static Shooter instance;
	private Victor shooter;
	private Victor turret;
	private double RPMfactor;
	private double shooterPeriod;
	private PID shooterPID;
	static Counter shooterCounter;
	private DigitalInput positionSensor;
	private PID turretPID;
	private int counter;
	private boolean isOnPos;
	private int curPos;
	
	public static Shooter getInstance(){
		if(instance == null)
			instance = new Shooter();
		return instance;
	}
	
	public Shooter(){
		RPMfactor = 60; // min/s
		shooterCounter = new Counter(ElectricalConstants.SHOOTER_SENSOR);
		shooter = new Victor(ElectricalConstants.SHOOTER_MOTOR);
		turret = new Victor(ElectricalConstants.TURRET_MOTOR);
		shooterCounter.setMaxPeriod(.1);
		shooterCounter.setDistancePerPulse(1);
		shooterCounter.setSamplesToAverage(1);
		shooterCounter.reset();
		turretPID = new PID(PIDConstants.TURRET_P, PIDConstants.TURRET_I, PIDConstants.TURRET_D);
		shooterPeriod = 0;
		shooterPID = new PID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D); //change to PID Constants
		counter = 0;
		isOnPos = false;
		curPos = 0;
	}
	
	public void updatePID(){
		shooterPID.updatePID(PIDConstants.SHOOTER_P, PIDConstants.SHOOTER_I, PIDConstants.SHOOTER_D);
	}
	
	//Set shooter at certain RPM
	public void setShooter(double rpm){
		double shooterPower = 0;
		shooterPower = shooterPID.getValue(getShooterSpeed(), rpm, getFeedForward(4000)); //change to shooter constants
		setPower(shooterPower);
	}
	
	//Set power to motor
	public void setPower(double power){
		shooter.set(power);
	}
	
	//Get rpm of the shooter
	public double getShooterSpeed(){
		return RPMfactor/getShooterPeriod();
	}
	
	public void setTurret(double speed){
		turret.set(speed);
	}
	
	//Gets the time between counts
	public double getShooterPeriod(){
		double shooterPeriod = shooterCounter.getPeriod();
		if(shooterPeriod < 1.66e-4)
			return this.shooterPeriod;
		else{
			this.shooterPeriod = shooterPeriod;
			return shooterPeriod;
		}
	}
	
	//Gets feedforward
	public double getFeedForward(double rpm){
		return 1.4e-4 * rpm - 0.05; //change for this years feedforward
	}

	public boolean atPosition(){
//		if(positionSensor.get()){
//			isOnPos = false;
//		}
//		else{
//			if(isOnPos == false){
//				isOnPos = true;
//				counter++;
//			}
//		}
		return !positionSensor.get();
	}
	
	public void vision(){
		setTurret(turretPID.getValue(VisionServer.getInstance().getDouble(), 0, 0));
	}
	
	public void setShooterPos(int pos){ //one = home, two = red, three = blue
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
