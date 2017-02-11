
package org.usfirst.frc.team610.robot;

import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team610.robot.commands.G_CenterGear;
import org.usfirst.frc.team610.robot.commands.G_GearLeft;
import org.usfirst.frc.team610.robot.commands.G_GearRight;
import org.usfirst.frc.team610.robot.commands.G_Teleop;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
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
		auton = new G_GearRight();
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
		teleop.cancel();
		auton.cancel();
		visionServer = VisionServer.getInstance();

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		// teleop.cancel();
		
		if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_X) && oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_GearLeft();
			SmartDashboard.putString("Auton", "Gearleft_Blue");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_B) && oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_GearRight();
			SmartDashboard.putString("Auton", "GearRight_Blue");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A) && oi.getOperator().getRawButton(LogitechF310Constants.BTN_Y)) {
			auton = new G_CenterGear();
			SmartDashboard.putString("Auton", "Center_Blue");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_X)) {
			auton = new G_GearLeft();
			SmartDashboard.putString("Auton", "Gearleft_Red");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_B)) {
			auton = new G_GearRight();
			SmartDashboard.putString("Auton", "GearRight_Red");
		} else if (oi.getOperator().getRawButton(LogitechF310Constants.BTN_A)) {
			auton = new G_CenterGear();
			SmartDashboard.putString("Auton", "Center_Red");
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
		teleop.cancel();
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
		auton.cancel();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
