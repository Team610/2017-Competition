package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.BallIntake;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.GearIntake;
import org.usfirst.frc.team610.robot.subsystems.HopperFeeder;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_Setup extends Command {

	private GearIntake intake;
	private Shooter shooter;
	private DriveTrain driveTrain;
	private HopperFeeder feeder;
	private int counter;
	private BallIntake ballIntake;

	public A_Setup() {

		intake = GearIntake.getInstance();
		shooter = Shooter.getInstance();
		driveTrain = DriveTrain.getInstance();
		feeder = HopperFeeder.getInstance();
		ballIntake = BallIntake.getInstance();
		
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		counter = 0;
		driveTrain.resetEnc();
		driveTrain.resetAngle();
		driveTrain.setLeft(0);
		driveTrain.setRight(0);
		feeder.setSpeed(0);
		shooter.setLED(false);
		shooter.setPower(0);
		feeder.setAgitator(true);
		ballIntake.setIntake(1);
		setTimeout(.25);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.setTurret(0);
		if (counter < 50) {
			//intake.setIntake(true);
			intake.setOuttake(false);
		} else {
			intake.setIntake(false);
		}
		counter++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
