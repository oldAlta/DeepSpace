// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robots.subsystems;


import frc.robots.RobotMap;
import frc.robots.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_VictorSPX leftDriveVictor;
    private WPI_VictorSPX rightDriveVictor;
    private WPI_TalonSRX leftDriveTalon;
    private WPI_TalonSRX rightDriveTalon;
    private DifferentialDrive drive;
    private Faults _leftDriveFaults;
    private Faults _rightDriveFaults;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public DriveTrain() {

        leftDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_TALON_CAN_ID);
        leftDriveTalon.configFactoryDefault();
        leftDriveTalon.configNominalOutputForward(0.05);
        leftDriveTalon.setSensorPhase(false);
        leftDriveTalon.setInverted(false);
        
        leftDriveVictor = new WPI_VictorSPX(RobotMap.LEFT_DRIVE_VICTOR_CAN_ID);
        leftDriveVictor.follow(leftDriveTalon);
        
        rightDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_TALON_CAN_ID);
        rightDriveTalon.configFactoryDefault();
        rightDriveTalon.configNominalOutputForward(0.05);
        rightDriveTalon.setSensorPhase(true);
        rightDriveTalon.setInverted(false);

        rightDriveVictor = new WPI_VictorSPX(RobotMap.RIGHT_DRIVE_VICTOR_CAN_ID);
        rightDriveVictor.follow(rightDriveTalon);
        
        
        
        drive = new DifferentialDrive(leftDriveTalon, rightDriveTalon);
        addChild("Differential Drive 1",drive);
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(1.0);

        _leftDriveFaults = new Faults();
        _rightDriveFaults = new Faults();

       
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        this.setDefaultCommand(new DriveCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void curveDrive(double speed, double rotation, boolean isQuickTurn) {
        drive.curvatureDrive(speed, rotation, isQuickTurn);
    }

    public ErrorCode getLeftDriveTalonFaults() {
        return leftDriveTalon.getFaults(_leftDriveFaults);
    }

    public void printLeftDriveTalonValues() {
        TalonSubsystem.printTalonOutputs(leftDriveTalon);
    }

    public ErrorCode getRightDriveTalonFaults() {
        return rightDriveTalon.getFaults(_rightDriveFaults);
    }

    public void printRightDriveTalonValues() {
        TalonSubsystem.printTalonOutputs(rightDriveTalon);
    }

    

    


    


}

