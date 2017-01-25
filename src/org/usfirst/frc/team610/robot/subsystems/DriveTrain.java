package org.usfirst.frc.team610.robot.subsystems;

import org.crescent.sixten.pid.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private PID drivePID;
	private Victor leftFront,leftBack,rightFront,rightBack;
	
	private DoubleSolenoid solLeft, solRight;
	private Compressor compressor;
	
	private NavX navX;
	
	private OI oi;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private DriveTrain(){
		solLeft = new DoubleSolenoid(ElectricalConstants.SHIFTER_LEFT_ONE, ElectricalConstants.SHIFTER_LEFT_TWO);
		solRight = new DoubleSolenoid(ElectricalConstants.SHIFTER_RIGHT_ONE,ElectricalConstants.SHIFTER_RIGHT_TWO);
		compressor = new Compressor();
		leftFront = new Victor(ElectricalConstants.DRIVE_LEFT_FRONT);
		leftBack = new Victor(ElectricalConstants.DRIVE_LEFT_BACK);
		rightFront = new Victor(ElectricalConstants.DRIVE_RIGHT_FRONT);
		rightBack = new Victor(ElectricalConstants.DRIVE_RIGHT_BACK);
		oi = OI.getInstance();
		navX = NavX.getInstance();
//		drivePID = new PID(p, i, d)
		
		
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setRight(double power){
		rightFront.set(power);
		rightBack.set(power);;
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
	
	public void compressor(boolean start){
		if(start)
			compressor.start();
		else
			compressor.stop();
	}
	
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
		if(leftSpeed != 0 && rightSpeed != 0){
			if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_R1))
				shiftUp();
			else if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_R2))
				shiftDown();
		}
	}
	
}
