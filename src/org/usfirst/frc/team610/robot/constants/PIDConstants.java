package org.usfirst.frc.team610.robot.constants;

import edu.wpi.first.wpilibj.Preferences;

public class PIDConstants {

	public static Preferences prefs = Preferences.getInstance();

	public static double SHOOTER_P = 5.3E-4;
	public static double SHOOTER_D = -5.5E-4;
	public static double SHOOTER_I = 1E-6;

	public static double DRIVE_ENC_P = 0.625;
	public static double DRIVE_ENC_I = 0;
	public static double DRIVE_ENC_D = 0.85; // 0.65

	public static double DRIVE_GYRO_P = 0.042;
	public static double DRIVE_GYRO_I = 0.0002;
	public static double DRIVE_GYRO_D = -0.16;

	public static double TURRET_P = 0.0039;
	public static double TURRET_I = 0;
	public static double TURRET_D = -0.0032;
	
	public static double RPM = 0;
	public static double RPM_Center = 4200;
	public static double RPM_SIDE = 3875.0;
	public static double RPM_LINE = 3350;
	public static double RPM_DIAMOND = 3500;
	
	public static double hopperSpeed = 0.3;

	public static void Update() {
		RPM_SIDE = prefs.getDouble("Side RPM", 3800);
		RPM_Center = prefs.getDouble("Center RPM", 4250);
		SHOOTER_P = prefs.getDouble("Shooter P", 5.3E-4);
		SHOOTER_I = prefs.getDouble("Shooter I", 1E-6);
		SHOOTER_D = prefs.getDouble("Shooter D", -5.5E-4);

		DRIVE_ENC_P = prefs.getDouble("Enc P", -0.15);
		DRIVE_ENC_I = prefs.getDouble("Enc I", 0);
		DRIVE_ENC_D = prefs.getDouble("Enc D", 0.15);

		DRIVE_GYRO_P = prefs.getDouble("Gyro P", 0.042);
		DRIVE_GYRO_I = prefs.getDouble("Gyro I", 0.0002);
		DRIVE_GYRO_D = prefs.getDouble("Gyro D", -0.16);
		
		RPM = prefs.getDouble("RPM", 0);
		//Peg center RPM: 4250
		
		hopperSpeed = prefs.getDouble("Hopper Speed", 0.3);
		
		TURRET_P = prefs.getDouble("Turret P", 0.0039);
		TURRET_I = prefs.getDouble("Turret I", 0);
		TURRET_D = prefs.getDouble("Turret D", -0.0032);
		
		
		
	}

}
