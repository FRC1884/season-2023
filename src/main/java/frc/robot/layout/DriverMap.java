package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.PistonSystemOne;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.TwoMotorOpp;
import frc.robot.subsystems.PixyCam;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import pixy2api.Pixy2CCC.Block;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.BeamBreakMotors;
import frc.robot.subsystems.JoystickMotorRotation;
import frc.robot.subsystems.PIDMotor;


public abstract class DriverMap extends CommandMap {

  public DriverMap(GameController controller) {
    super(controller);
  }
  abstract JoystickButton getPistonButton();

  abstract ChassisSpeeds getChassisSpeeds();

  abstract JoystickButton getPathPlanningTestButton();

  abstract JoystickButton getAlingmentButton();

  abstract double getLeftYAxis();

  abstract double getLeftXAxis();

  //abstract JoystickButton getPistonTriggers(); 

  abstract JoystickButton getNinetyButton();
  
  abstract JoystickButton getPixyCamDistanceButton();

  public abstract JoystickButton getTwoMotorButton();

  @Override
  public void registerCommands() {
    var swerve = Swerve.getInstance();
    PIDMotor pidMotor = PIDMotor.getInstance();
    getNinetyButton().onTrue(pidMotor.rotateToNinetyCommand());

    pidMotor.setDefaultCommand(pidMotor.rotateWithJoystickCommand(getLeftXAxis(), -getLeftYAxis()));

    swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));
    getAlingmentButton().onTrue(swerve.ChargingStationCommand());
    JoystickMotorRotation joystickMotorRotation = JoystickMotorRotation.getInstance();
    PistonSystemOne instance = PistonSystemOne.getInstance();
    getPistonButton().onTrue(new InstantCommand(() -> instance.shootPiston()));
    
    // JoystickMotorRotation joystickMotorRotation = JoystickMotorRotation.getInstance();
    // swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));

    // pixyCam.setDefaultCommand(pixyCam.printCommand());
    
    // getPixyCamDistanceButton().onTrue(swerve.AlignWithGameObject());
    //joystickMotorRotation.setDefaultCommand(new RunCommand(() -> joystickMotorRotation.rotateMotor(getLeftYAxis()), joystickMotorRotation));
    //TwoMotorOpp twoMotorOpp = TwoMotorOpp.getInstance();
    //getTwoMotorButton().onTrue(new InstantCommand(() -> twoMotorOpp.rotateMotor(), twoMotorOpp));
    //getTwoMotorButton().onFalse(new InstantCommand(() -> twoMotorOpp.stopMotor(), twoMotorOpp));
    BeamBreakMotors beamBreakMotors = BeamBreakMotors.getInstance();
    beamBreakMotors.setDefaultCommand(new RunCommand(() -> beamBreakMotors.activateMotor(), beamBreakMotors));
  }
}
