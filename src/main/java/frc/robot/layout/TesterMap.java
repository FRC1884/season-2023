package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Config;
import frc.robot.subsystems.ElevatorArm;
import frc.robot.subsystems.MotionProfile;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Vision;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import frc.robot.RobotMap.ElevatorPivotMap;
import frc.robot.RobotMap.MotionProfileMap.TestSetpoint;

public abstract class TesterMap extends CommandMap {

  public TesterMap(GameController controller) {
    super(controller);
  }

  abstract ChassisSpeeds getChassisSpeeds();

  abstract JoystickButton getTopButton();

  abstract JoystickButton getHalfButton();

  abstract JoystickButton getZeroButton();

  abstract JoystickButton getPrintButton();

  abstract double getOnButton();

  abstract double getLeftYAxis();

  abstract double getRightYAxis();

  private void registerSwerve() {
    var swerve = Swerve.getInstance();
    var vision = Vision.getInstance();
    // swerve.setDefaultCommand(swerve.driveCommand(this::getChassisSpeeds));
    // getTopButton().onTrue(swerve.alignWithGridCommand(Vision.Position.LEFT_CONE));
    // getHalfButton().onTrue(swerve.alignWithGridCommand(Vision.Position.CUBE));
    // getZeroButton().onTrue(swerve.alignWithGridCommand(Vision.Position.RIGHT_CONE));
    getPrintButton().onTrue(new InstantCommand(vision::printPose));
    
  }

  private void registerElevatorArm() {
    var elevatorArm = ElevatorArm.getInstance();
    elevatorArm.setDefaultCommand(
      new RepeatCommand(
          new RunCommand(() -> elevatorArm.moveElevatorAndPivot(-getLeftYAxis() * 0.5, -getRightYAxis() * 0.35),
                elevatorArm)));
    getTopButton().onTrue(elevatorArm.moveElevatorAndPivot(() -> ElevatorPivotMap.SetPoint.TOP));
    getZeroButton().onTrue(elevatorArm.moveElevatorAndPivot(() -> ElevatorPivotMap.SetPoint.ZERO));
  }
  
  @Override
  public void registerCommands() {

    if (Config.Subsystems.SWERVE_ENABLED)
      registerSwerve();
    
  }
}
