package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SC_SystemCheck extends CommandGroup {

    public SC_SystemCheck() {
    	addParallel(new T_SmartDash());
    	addSequential(new SC_Drive(3, false, 1, 1));
    	addSequential(new SC_Drive(3, true, 1, 1));
    	addSequential(new SC_SetZero(1));
    	addSequential(new SC_Drive(3, false, -1, -1));
    	addSequential(new SC_Drive(3, true, -1, -1));
    	addSequential(new SC_SetZero(1));
    	addSequential(new SC_Gear());
    	addSequential(new SC_FlyWheel(6));
    	addSequential(new SC_PegSensor(30));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
