package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.PistonSystemOne;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.PixyCam;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import pixy2api.Pixy2CCC.Block;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.JoystickMotorRotation;

public abstract class DriverMap extends CommandMap {

  public DriverMap(GameController controller) {
    super(controller);
  }
  abstract JoystickButton getPistonButton();

  abstract ChassisSpeeds getChassisSpeeds();

  abstract JoystickButton getPathPlanningTestButton();

  abstract JoystickButton getPixyCamDistanceButton();

  abstract double getLeftYAxis();

  @Override
  public void registerCommands() {
    var swerve = Swerve.getInstance();
    PistonSystemOne instance = PistonSystemOne.getInstance();
    getPistonButton().onTrue(new InstantCommand(() -> instance.shootPiston()));
    
    JoystickMotorRotation joystickMotorRotation = JoystickMotorRotation.getInstance();
    swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));

    //pixyCam.setDefaultCommand(pixyCam.printCommand());
    
    getPixyCamDistanceButton().onTrue(swerve.AlignWithGameObject());
    joystickMotorRotation.setDefaultCommand(new RunCommand(() -> joystickMotorRotation.rotateMotor(getLeftYAxis())));
  }
}
