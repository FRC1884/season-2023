package frc.robot.layout;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.subsystems.Swerve;
import frc.robot.util.controllers.ButtonMap.Axis;
import frc.robot.util.controllers.ButtonMap.Button;
import frc.robot.util.controllers.ButtonMap.Trigger;
import frc.robot.util.controllers.GameController;

public class TwoJoyStickDriverMap extends DriverMap {

  public TwoJoyStickDriverMap(GameController controller) {
    super(controller);
  }

  @Override
  public ChassisSpeeds getChassisSpeeds() {
    var multiplier = (getRightTrigger() > 0 ) ? 0.3 : 1;
    double x = Math.pow(controller.getAxis(Axis.AXIS_LEFT_X), 1) * RobotMap.DriveMap.MAX_VELOCITY * multiplier;
    double y = Math.pow(controller.getAxis(Axis.AXIS_LEFT_Y), 1) * RobotMap.DriveMap.MAX_VELOCITY * multiplier;
    double rot = controller.getAxis(Axis.AXIS_RIGHT_X) * RobotMap.DriveMap.MAX_ANGULAR_VELOCITY * 0.7 * multiplier;

    return ChassisSpeeds.fromFieldRelativeSpeeds(-y, -x, -rot, new Rotation2d(0, 0));
  }

  @Override
  public JoystickButton getLeftConeButton(){
    return controller.getButton(Button.BUTTON_X);
  }

  @Override
  public JoystickButton getCubeButton(){
    return controller.getButton(Button.BUTTON_Y);
  }

  @Override
  public JoystickButton getRightConeButton(){
    return controller.getButton(Button.BUTTON_B);
  }

  @Override
  public double getRightTrigger(){
    return controller.getAxis(Axis.AXIS_RIGHT_TRIGGER);
  }

  @Override
  public void registerCommands() {
    super.registerCommands();
  }
}
