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


import frc.robots.Robot;
import frc.robots.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class CameraSubsystem extends Subsystem {

    private double driveWithCameraKp = 0.01;
    private double maxSeekingSpeed = 0.60;
    private double minCommand = 0.1;
    private double lowestTurnSpeed = 0.6;
    private double turnSpeed = 1.0;

    /* 
         * Trial and error calibration, you can get this ratio by setting an initial SEEK_MAX_SPEED (0.8 in this case),
         * then put the Robot perfectly at the spot where you want it to be at and get the value of the area (LLData#getData().area)
         * 
         * Equation: 
         * > y = speedConstant + (AREA_FACTOR_RATIO * #getData().area)
         * > y = -0.8 + (0.105 * #getData().area) -- when y=0, you are at target
         * In order to derive 0.105 => 0.8 = 0.105 * #getData().area
         * > #getData().area = 0.8/0.105, therefore the area ratio is 0.8/0.105
         */
    private double areaFactorRatio = 0.8/0.105; 

    public CameraSubsystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        setDriveCamMode();
    
    }

    public static class LLData {
        public final double xOffset, yOffset, area, targetExists, skew;

        public LLData(double xOffset, double yOffset, double area, double targetExists, double skew) {
            this.xOffset = xOffset;    
            this.yOffset = yOffset;
            this.area = area;
            this.targetExists = targetExists;     
            this.skew = skew;
        }
    }

    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tx = table.getEntry("tx"); //X-offset of target (Limelight 2: -29.8 to 29.8 deg.)
    private NetworkTableEntry ty = table.getEntry("ty"); //Y-offset of target (Limelight 2: -22.85 to 22.85 deg.)
    private NetworkTableEntry tv = table.getEntry("tv"); //Whether the limelight has a valid target (either 0 or 1)
    private NetworkTableEntry ta = table.getEntry("ta"); //Area of target in image (0-100%)
    private NetworkTableEntry ts = table.getEntry("ts"); //Skew or rotation of target (-90.0 to 0.0 deg.)
    private NetworkTableEntry tl = table.getEntry("tl"); //Limelight latency contribution (in ms.)


    // Gets camera modes and pipelines from Limelight
    private NetworkTableEntry ledMode = table.getEntry("ledMode"); //0-3
    private NetworkTableEntry camMode = table.getEntry("camMode"); //0-1
    private NetworkTableEntry pipeline = table.getEntry("pipeline"); //0-9
    
    /**
     * Sets the mode to the one applicable for tracking the reflective tape (low exposure).
     */
    public void setTrackingMode() {
        pipeline.setNumber(1);
        camMode.setNumber(0);
        ledMode.setNumber(3);
    }

    /**
     * Sets the mode to the one applicable for camera vision.
     */
    public void setDriveCamMode() {
        pipeline.setNumber(0);
        camMode.setNumber(1);
        ledMode.setNumber(1);
    }

    /**
     * Grabs the data of the current LLData instance.
     * @return The instance of LLData that has the data of the next iteration
     */
    public LLData getData() {
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double skew = ts.getDouble(0.0);
        double v = tv.getDouble(0.0);
        return new CameraSubsystem.LLData(x, y, area, v, skew);
    }

    public double getHeadingError() {
        var limelightData = this.getData(); //Java 10 'var' automatically creates new LLData object.
     
        double minDrive = DriveTrain.MIN_DRIVE_SPEED; //speed the motor will move the robot regardless of how miniscule the error is
        double kP = driveWithCameraKp;
        double xOffset = limelightData.xOffset;
        double heading = 0.0; //should be opposite of offset (in signs)

        if (xOffset > 1.0) {
            heading = -1.0 * ((kP * xOffset) + minDrive);
        } else { //xOffset less than or equal to 1.0
            heading = -1.0 * ((kP * xOffset) - minDrive);
        }
        return heading;
    }

    public double getSpeedCorrection() {
        double speedConstant = -1.0 * maxSeekingSpeed;
        double areaConstant = maxSeekingSpeed/ areaFactorRatio;
        double speedPorportion = speedConstant + (areaConstant * this.getData().area);
        
        return speedPorportion;
    }

    public void seek(double clockwise) {
        var limelightData = Robot.cameraSubsystem.getData(); //Java 10 'var' automatically creates new LLData object.
        if (limelightData.targetExists == 0.0) { //no target
            double meanSpeed = (lowestTurnSpeed + turnSpeed) / 2.0; //exactly half of lowest and highest speed
            // Robot.driveTrain.curvatureDrive(0, meanSpeed * clockwise, true); //spin in place, factor clockwise to determine if clockwise or not
            Robot.driveTrain.setArcadeDrive(0, meanSpeed * clockwise);
        } else {
            double speed = Robot.cameraSubsystem.getSpeedCorrection();
            double heading = Robot.cameraSubsystem.getHeadingError();

            // Robot.driveTrain.curvatureDrive(speed, heading, false);
            Robot.driveTrain.setArcadeDrive(speed, heading);
        }
    }
    



    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        // setDefaultCommand(new SeekLeft());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

