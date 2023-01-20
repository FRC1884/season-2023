package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.FrictionPad;
import frc.robot.subsystems.Swerve;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import frc.robot.subsystems.JoystickMotorRotation;
import frc.robot.subsystems.PIDMotor;


public abstract class DriverMap extends CommandMap {

  public DriverMap(GameController controller) {
    super(controller);
  }
  abstract JoystickButton getPistonButton();

  abstract ChassisSpeeds getChassisSpeeds();

  abstract JoystickButton getPathPlanningTestButton();

  abstract double getLeftYAxis();

  abstract double getLeftXAxis();

  //abstract JoystickButton getPistonTriggers(); 

  abstract JoystickButton getNinetyButton();

  @Override
  public void registerCommands() {
    var swerve = Swerve.getInstance();
    PIDMotor pidMotor = PIDMotor.getInstance();
    getNinetyButton().onTrue(pidMotor.rotateToNinetyCommand());

    pidMotor.setDefaultCommand(pidMotor.rotateWithJoystickCommand(getLeftXAxis(), -getLeftYAxis()));

    FrictionPad frictionPad = FrictionPad.getInstance();
    getPistonButton().onTrue(new InstantCommand(() -> frictionPad.extendPiston(true)));
    swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));
    JoystickMotorRotation joystickMotorRotation = JoystickMotorRotation.getInstance();
    joystickMotorRotation.setDefaultCommand(new RunCommand(() -> joystickMotorRotation.rotateMotor(getLeftYAxis())));
  }
}
