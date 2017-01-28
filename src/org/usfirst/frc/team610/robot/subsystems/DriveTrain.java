package org.usfirst.frc.team610.robot.subsystems;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private PID encLeftPID;
	private PID encRightPID;
	private PID gyroPID;
	private Victor leftFront,leftBack,rightFront,rightBack;
	
	private DoubleSolenoid solLeft, solRight;
	//private Compressor compressor; //Todo: add this back in
	
	private NavX navX;
	
	private OI oi;
	
	private Encoder leftEnc;
	private Encoder rightEnc;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private DriveTrain(){
		solLeft = new DoubleSolenoid(ElectricalConstants.SHIFTER_LEFT_ONE, ElectricalConstants.SHIFTER_LEFT_TWO);
		solRight = new DoubleSolenoid(ElectricalConstants.SHIFTER_RIGHT_ONE,ElectricalConstants.SHIFTER_RIGHT_TWO);
//		compressor = new Compressor();
		leftFront = new Victor(ElectricalConstants.DRIVE_LEFT_FRONT);
		leftBack = new Victor(ElectricalConstants.DRIVE_LEFT_BACK);
		rightFront = new Victor(ElectricalConstants.DRIVE_RIGHT_FRONT);
		rightBack = new Victor(ElectricalConstants.DRIVE_RIGHT_BACK);
		oi = OI.getInstance();
		navX = NavX.getInstance();
		encLeftPID = new PID(0.01, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		encRightPID = new PID(0.01, PIDConstants.DRIVE_ENC_I, PIDConstants.DRIVE_ENC_D);
		gyroPID = new PID(PIDConstants.DRIVE_GYRO_P, PIDConstants.DRIVE_GYRO_I, PIDConstants.DRIVE_GYRO_I);
		leftEnc = new Encoder(ElectricalConstants.DRIVE_ENC_LEFT_A, ElectricalConstants.DRIVE_ENC_LEFT_B);
		rightEnc = new Encoder(ElectricalConstants.DRIVE_ENC_RIGHT_A, ElectricalConstants.DRIVE_ENC_RIGHT_B);
		leftEnc.setDistancePerPulse(4*Math.PI / 128.0);
		rightEnc.setDistancePerPulse(4*Math.PI / 128.0);
	}
	
	public void resetSensors(){
		leftEnc.reset();
		rightEnc.reset();
		navX.reset();
	}

	public double getLeftInches(){
		return -leftEnc.getDistance();
	}

	public double getRightInches(){
		return rightEnc.getDistance();
	}
	
	public double getRightRPM(){
		return rightEnc.getRate();
	}
	
	public double getLeftRPM(){
		return rightEnc.getRate();
	}
	
	public void resetEnc(){
		rightEnc.reset();
		leftEnc.reset();
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setRight(double power){
		rightFront.set(power);
		rightBack.set(power);
	}
	
	public void setLeft(double power){
		leftFront.set(power);
		leftBack.set(power);
	}
	
	public void shiftUp(){
		solLeft.set(DoubleSolenoid.Value.kForward);
		solRight.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shiftDown(){
		solLeft.set(DoubleSolenoid.Value.kReverse);
		solRight.set(DoubleSolenoid.Value.kReverse);
	}
	
	// Todo: put this back in
//	public void compressor(boolean start){
//		if(start)
//			compressor.start();
//		else
//			compressor.stop();
//	}
	
	public double getAngle(){
		return navX.getAngle();
	}
	
	public void resetAngle(){
		navX.reset();
	}
	
	public void drive(){
		double x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X);
		double y = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y);
		double leftSpeed, rightSpeed;
		leftSpeed = y - x;
		rightSpeed = y + x;
		setRight(rightSpeed);
		setLeft(leftSpeed);
//		if(leftSpeed != 0 && rightSpeed != 0){
//			if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1) || (getLeftRPM() > 1500 && getRightRPM() > 1500))
//				shiftUp();
//			else if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_R2)|| (getLeftRPM() < 1500 && getRightRPM() < 1500))
//				shiftDown();
//		}
	}
	
	public void setTurn(double angle){
		setLeft(gyroPID.getValue(getAngle(), angle, 0));
		setRight(-gyroPID.getValue(getAngle(), angle, 0));
	}
	
	public void driveForward(double inches){
		setLeft(encLeftPID.getValue(getLeftInches(), inches, 0) + gyroPID.getValue(getAngle(), 0, 0));
		setRight(encRightPID.getValue(getRightInches(), inches, 0) - gyroPID.getValue(getAngle(), 0, 0));
	}
	
}
