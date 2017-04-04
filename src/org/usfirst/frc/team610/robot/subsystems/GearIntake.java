package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIntake extends Subsystem {

	static GearIntake instance;
	private DoubleSolenoid intake, outtake;
	private boolean isIntakeClosed, isOuttake;
	private DigitalInput peg;
	private DigitalOutput light, lightA;

	public static GearIntake getInstance() {
		if (instance == null)
			instance = new GearIntake();
		return instance;
	}

	public GearIntake() {
		intake = new DoubleSolenoid(ElectricalConstants.GEAR_FLAP_ONE, ElectricalConstants.GEAR_FLAP_TWO);
		outtake = new DoubleSolenoid(ElectricalConstants.GEAR_SCORER_ONE, ElectricalConstants.GEAR_SCORER_TWO);
		peg = new DigitalInput(ElectricalConstants.PEG_OPTICAL);
		light = new DigitalOutput(ElectricalConstants.LED);
		lightA = new DigitalOutput(ElectricalConstants.LED_A);
		isIntakeClosed = true;
		isOuttake = true;

	}

	public void setIntake(boolean open) {
		if (open) {
			intake.set(DoubleSolenoid.Value.kReverse);
			isIntakeClosed = false;
		} else {
			intake.set(DoubleSolenoid.Value.kForward);
			isIntakeClosed = true;
		}
	}

	public void setOuttake(boolean open) {
		if (open) {
			outtake.set(DoubleSolenoid.Value.kReverse);
			isOuttake = false;
		} else {
			outtake.set(DoubleSolenoid.Value.kForward);
			isOuttake = true;
		}
	}

	public boolean isIntakeClosed() {
		return isIntakeClosed;
	}

	public boolean isOuttakeClosed() {
		return isOuttake;
	}

	public boolean getPeg() {
		return !peg.get();
	}

	public void setLED(boolean on) {
		light.set(on);
		lightA.set(on);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
