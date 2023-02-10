package frc.robot.layout;

import java.util.HashMap;

import edu.wpi.first.math.kinematics.ChassisSpeeds;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
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

  abstract JoystickButton getAlingmentButton();

  abstract JoystickButton getAprilTagAlignmentButton();

  public abstract JoystickButton getTwoMotorButton();

  @Override
  public void registerCommands() {
    var swerve = Swerve.getInstance();
    PistonSystemOne instance = PistonSystemOne.getInstance();
    
    getPistonButton().onTrue(new InstantCommand(() -> instance.shootPiston()));
    HashMap<String, Command> oneMeterEventMap = new HashMap<String, Command>();
    oneMeterEventMap.put("I mean it's alright like", new PrintCommand("I'm here"));
    oneMeterEventMap.put("finishedPath", new PrintCommand("This works"));
    
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

    getAlingmentButton().onTrue(swerve.chargingStationCommand());

    getPathPlanningTestButton().onTrue(swerve.chargingStationPPAndBalance(oneMeterEventMap));

    getAprilTagAlignmentButton().onTrue(swerve.alignWithAprilTag(true));

    // pixyCam.setDefaultCommand(pixyCam.printCommand());

    getPixyCamDistanceButton().onTrue(swerve.alignWithGameObject());
    BeamBreakMotors beamBreakMotors = BeamBreakMotors.getInstance();
    beamBreakMotors.setDefaultCommand(new RunCommand(() -> beamBreakMotors.activateMotor(), beamBreakMotors));
  }
}
