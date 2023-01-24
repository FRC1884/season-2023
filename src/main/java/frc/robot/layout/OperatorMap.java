package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.JoystickTwoMotors;
import frc.robot.subsystems.TwoMotorOpp;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class OperatorMap extends CommandMap {

  public OperatorMap(GameController controller) {
    super(controller);
  }

  public abstract void getElevatorUpButton();

  public abstract void getElevatorDownButton();

  public abstract void getElevatorMiddleButton();

  public abstract double getLeftXAxis();

  public abstract JoystickButton getTwoMotorButton();

  @Override
  public void registerCommands() {
    JoystickTwoMotors joystickTwoMotors = JoystickTwoMotors.getInstance();
    joystickTwoMotors.setDefaultCommand(new RunCommand(() -> joystickTwoMotors.rotateMotor(getLeftXAxis())));

    TwoMotorOpp twoMotorOpp = TwoMotorOpp.getInstance();
    getTwoMotorButton().onTrue(new InstantCommand(() -> twoMotorOpp.rotateMotor(), twoMotorOpp));
  }
}
