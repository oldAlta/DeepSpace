// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robots;

import frc.robots.commands.*;
import frc.robots.controller.XboxButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robots.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private int port0 = RobotMap.XBOX_PORT_0;
    private int port1 = RobotMap.XBOX_PORT_1;
    XboxController controller = new XboxController(port0);
    XboxController controllerTwo = new XboxController(port1);
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        Button entireHabLiftUpAndDown = new XboxButton(controller, XboxButton.Button.X);
        Button frontHabLiftUpAndDown = new XboxButton(controller, XboxButton.Button.Y);
        Button backHabLiftUpAndDown = new XboxButton(controller, XboxButton.Button.B);

        // left bumper on first controller
        Button placeDiskButton = new XboxButton(controller, XboxButton.Button.BumperLeft);

        // right bumper on first controller
        Button liftActuatorButton = new XboxButton(controller, XboxButton.Button.BumperRight);

        Button driveStraightButton = new XboxButton(controller, XboxButton.Button.A);

        Button liftToLowPosButton = new XboxButton(controllerTwo, XboxButton.Button.A);

        Button frontHabIdle = new XboxButton(controllerTwo, XboxButton.Button.Start);

        Button liftToMidPosition = new XboxButton(controllerTwo, XboxButton.Button.X);
        Button lifToHighPositon = new XboxButton(controllerTwo, XboxButton.Button.Y);
        Button liftToStowedPosition = new XboxButton(controllerTwo, XboxButton.Button.B);

        Button seekLeftButton = new XboxButton(controllerTwo, XboxButton.Button.BumperLeft);

        // Button climbUpWithMotors = new XboxButton(controllerTwo, XboxButton.Button.BumperRight);
        // Button climbDownWithMotors = new XboxButton(controllerTwo, XboxButton.Button.BumperLeft);
        


        entireHabLiftUpAndDown.whenPressed(new EntireHabLiftUpDown(1.0));
        frontHabLiftUpAndDown.whenPressed(new HabLiftFrontUpDown(1.0));
        backHabLiftUpAndDown.whenPressed(new HabLiftBackUpDown(1.0));

        // liftToLowPosButton.whenPressed(new LiftToLowPos());
        liftToLowPosButton.whenPressed(new LiftToLowPosSingleButton());
        
        // liftToMidPosition.whenPressed(new LiftToMidPos());
        liftToMidPosition.whenPressed(new LiftToMidPosSingleButton());

        
        // lifToHighPositon.whenPressed(new LiftToHighPos());
        lifToHighPositon.whenPressed(new LiftToHighPosSingleButton());
        liftToStowedPosition.whenPressed(new LiftToStowedPos());

        driveStraightButton.whileHeld(new DriveStraight());
        // left bumper on first controller
        placeDiskButton.whenPressed(new PlaceDisk(1.0));

        // liftActuatorButton.whenPressed(new LiftActuatorInOut(1.0));
        // right bumper on first controller
        liftActuatorButton.toggleWhenPressed(new LiftActuatorSingleSolenoid(1.0));

        liftToLowPosButton.whenPressed(new LiftToLowPos());

        frontHabIdle.toggleWhenPressed(new FrontHabMotorIdle());

        seekLeftButton.toggleWhenPressed(new SeekLeft());

        // climbUpWithMotors.toggleWhenPressed(new ClimbUpWithMotors());
        // climbDownWithMotors.toggleWhenPressed(new ClimbDownWithMotors());
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS



        // SmartDashboard Buttons
        SmartDashboard.putData("LiftToLowPos", new LiftToLowPos());
        SmartDashboard.putData("LiftToMidPos", new LiftToMidPos());
        SmartDashboard.putData("LiftToHighPos", new LiftToHighPos());
        SmartDashboard.putData("ClawOpen", new ClawOpen());
        SmartDashboard.putData("ClawClose", new ClawClose());
        SmartDashboard.putData("PlaceDisk", new PlaceDisk(1.0));
        SmartDashboard.putData("CenterLeft", new SeekLeft());
        SmartDashboard.putData("CenterRight", new SeekRight());
        SmartDashboard.putData("PlaceBallLow", new PlaceBallLow());
        SmartDashboard.putData("PlaceBallMiddle", new PlaceBallMiddle());
        SmartDashboard.putData("PlaceBallHigh", new PlaceBallHigh());
        SmartDashboard.putData("PlaceDiskLow", new PlaceDiskLow());
        SmartDashboard.putData("PlaceDiskMiddle", new PlaceDiskMiddle());
        SmartDashboard.putData("PlaceDiskHigh", new PlaceDiskHigh());
        SmartDashboard.putData("HabLiftFrontUpDown", new HabLiftFrontUpDown(2.0));
        SmartDashboard.putData("LiftToStowedPos", new LiftToStowedPos());
        SmartDashboard.putData("HabLiftBackUpDown", new HabLiftBackUpDown(2.0));
        SmartDashboard.putData("HabLiftUpDown", new HabLiftUpDown());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    public XboxController getController() {
		return controller;
    }
    
    public XboxController getControllerTwo() {
        return controllerTwo;
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

