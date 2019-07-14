/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robots.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robots.Robot;

public class EntireHabLiftUpDown extends TimedCommand {
  public EntireHabLiftUpDown(double timeout) {
    super(timeout);
    requires(Robot.habClimb);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.habClimb.isFrontHabClimbDown() && Robot.habClimb.isBackHabClimbDown()) {
      Robot.habClimb.setFrontSolenoid(Value.kForward);
      Robot.habClimb.setBackSolenoid(Value.kReverse);

      // Robot.habClimb.setFrontActuatorsDown();
      // Robot.habClimb.setBackActuatorsDown();
    } else if(!Robot.habClimb.isFrontHabClimbDown() && !Robot.habClimb.isBackHabClimbDown()){
      Robot.habClimb.setFrontSolenoid(Value.kReverse);
      Robot.habClimb.setBackSolenoid(Value.kForward);

      // Robot.habClimb.setFrontActuatorsUp();
      // Robot.habClimb.setBackActuatorsUp();
    } 

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Front climbers direction: " + Robot.habClimb.getFrontClimbersDirection());
    System.out.println("Rear climbers Direction: " + Robot.habClimb.getRearClimbersDirection());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}