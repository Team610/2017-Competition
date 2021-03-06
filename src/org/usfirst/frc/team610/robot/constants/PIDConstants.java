package org.usfirst.frc.team610.robot.constants;

import edu.wpi.first.wpilibj.Preferences;

public class PIDConstants {

	public static Preferences prefs = Preferences.getInstance();

	public static double SHOOTER_P = 3.4E-4;
	public static double SHOOTER_D = -6E-4;
	public static double SHOOTER_I = 0;

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
	public static double RPM_Center = 3600;
	public static double RPM_SIDE = 3475.0;
	//IN Update()
	public static double RPM_LINE = 3600;
	public static double RPM_DIAMOND = 3500;
	
	public static double HOPPER_SPEED = 0.5;
	
	public static double PEG_DRIVE = 27;
	
	public static double DELTAY_FAC = 0;
	
	public static double INIT_DRIVE_RED = 88;
	public static double INIT_DRIVE_BLUE = 85;

	public static void Update() {
		RPM_Center = prefs.getDouble("Center RPM", 3400);
	
		SHOOTER_P = prefs.getDouble("Shooter P", 3E-4);
		SHOOTER_I = prefs.getDouble("Shooter I", 0);
		SHOOTER_D = prefs.getDouble("Shooter D", -3E-4);

		DRIVE_ENC_P = prefs.getDouble("Enc P", -0.15);
		DRIVE_ENC_I = prefs.getDouble("Enc I", 0);
		DRIVE_ENC_D = prefs.getDouble("Enc D", 0.15);

		DRIVE_GYRO_P = prefs.getDouble("Gyro P", 0.042);
		DRIVE_GYRO_I = prefs.getDouble("Gyro I", 0.0002);
		DRIVE_GYRO_D = prefs.getDouble("Gyro D", -0.16);
		
		RPM = prefs.getDouble("RPM", 0);
		
		HOPPER_SPEED = prefs.getDouble("Hopper Speed", 0.7);
		
		TURRET_P = prefs.getDouble("Turret P", 0.0039);
		TURRET_I = prefs.getDouble("Turret I", 0);
		TURRET_D = prefs.getDouble("Turret D", -0.0032);
		
		DELTAY_FAC = prefs.getDouble("DeltaFactor", 0);
		
		INIT_DRIVE_RED = prefs.getDouble("Init Drive Red", 88);
		INIT_DRIVE_BLUE = prefs.getDouble("Init Drive Blue", 85);
		PEG_DRIVE = prefs.getDouble("Peg Drive", 27);
		
		
		
	}

}
