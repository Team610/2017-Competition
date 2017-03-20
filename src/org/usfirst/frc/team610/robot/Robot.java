
package org.usfirst.frc.team610.robot;

import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team610.robot.commands.G_GearCenter;
import org.usfirst.frc.team610.robot.commands.G_GearLeftBlue;
import org.usfirst.frc.team610.robot.commands.G_GearLeftFast;
import org.usfirst.frc.team610.robot.commands.G_GearLeftRed;
import org.usfirst.frc.team610.robot.commands.G_GearMidFast;
import org.usfirst.frc.team610.robot.commands.G_GearRightBlue;
import org.usfirst.frc.team610.robot.commands.G_GearRightFast;
import org.usfirst.frc.team610.robot.commands.G_GearRightRed;
import org.usfirst.frc.team610.robot.commands.G_Teleop;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	CommandGroup teleop;
	VisionServer visionServer;
	CommandGroup auton;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = OI.getInstance();
		teleop = new G_Teleop();
		auton = new G_GearCenter();
		
		// pidTune = new DrivePID();
		RIOdroid.initUSB();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		SmartDashboard.putString("Auton", "Center");
		auton = new G_GearCenter();
		teleop.cancel();
		auton.cancel();
		visionServer = VisionServer.getInstance();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		// teleop.cancel();

		SmartDashboard.putNumber("rightDistance", DriveTrain.getInstance().getRightInches());
		SmartDashboard.putNumber("leftDistance", DriveTrain.getInstance().getLeftInches());
		SmartDashboard.putNumber("Gyro", DriveTrain.getInstance().getAngle());

		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_GearLeftBlue();
			SmartDashboard.putString("Auton", "GearLeft_Blue");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_GearRightBlue();
			SmartDashboard.putString("Auton", "GearRight_Blue");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_GearCenter();
			SmartDashboard.putString("Auton", "Center");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)) {
			auton = new G_GearLeftRed();
			SmartDashboard.putString("Auton", "Gearleft_Red");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)) {
			auton = new G_GearRightRed();
			SmartDashboard.putString("Auton", "GearRight_Red");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			auton = new G_GearCenter();
			SmartDashboard.putString("Auton", "Center");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_START)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)) {
			auton = new G_GearLeftFast();
			SmartDashboard.putString("Auton", "GearLeftFast");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_START)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			auton = new G_GearMidFast();
			SmartDashboard.putString("Auton", "GearMidFast");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_START)
				&& oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)) {
			auton = new G_GearRightFast();
			SmartDashboard.putString("Auton", "GearRightFast");
		}
	}

	@Override
	public void autonomousInit() {
		teleop.cancel();
		System.out.println("Good Meme");
		auton.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {
		teleop.start();
		auton.cancel();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
