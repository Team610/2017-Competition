package org.usfirst.frc.team610.robot.constants;

import edu.wpi.first.wpilibj.Preferences;

public class PIDConstants {
	
	public static Preferences prefs = Preferences.getInstance();
	
	public static double SHOOTER_P = 0;
	public static double SHOOTER_D = 0;
	public static double SHOOTER_I = 0;
	
	public static double DRIVE_ENC_P = 0;
	public static double DRIVE_ENC_I = 0;
	public static double DRIVE_ENC_D = 0;
	
	public static double DRIVE_GYRO_P = 0.042;
	public static double DRIVE_GYRO_I = 0.0002;
	public static double DRIVE_GYRO_D = -0.16;

	public static double TURRET_P = 0;
	public static double TURRET_I = 0;
	public static double TURRET_D = 0;
	
	public static void Update(){
		SHOOTER_P = prefs.getDouble("Shooter P", 0);
		SHOOTER_I = prefs.getDouble("Shooter I", 0);
		SHOOTER_D = prefs.getDouble("Shooter D", 0);
		
		DRIVE_ENC_P = prefs.getDouble("Enc P", 0);
		DRIVE_ENC_I = prefs.getDouble("Enc I", 0);
		DRIVE_ENC_D = prefs.getDouble("Enc D", 0);
		
		DRIVE_GYRO_P = prefs.getDouble("Gyro P", 0.042);
		DRIVE_GYRO_I = prefs.getDouble("Gyro I", 0.0002);
		DRIVE_GYRO_D = prefs.getDouble("Gyro D", -0.16);
	}
	
}
