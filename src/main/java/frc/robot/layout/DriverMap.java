package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.MotorControl;
import frc.robot.subsystems.Swerve;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;

public abstract class DriverMap extends CommandMap {

  public DriverMap(GameController controller) {
    super(controller);
  }
  
  abstract ChassisSpeeds getChassisSpeeds();

  abstract JoystickButton getPathPlanningTestButton();

  abstract JoystickButton getMotorButtonY();

  abstract JoystickButton getMotorButtonX();

  @Override
  public void registerCommands() {
    var swerve = Swerve.getInstance();
    MotorControl controlMotor = MotorControl.getInstance();

    swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));

    getMotorButtonY().onTrue(new InstantCommand(() -> controlMotor.turnOnMotor()));


    getMotorButtonX().onTrue(new InstantCommand(() -> controlMotor.turnOffMotor()));
  }

}
