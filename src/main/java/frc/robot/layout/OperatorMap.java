package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Config;
import frc.robot.subsystems.ElevatorArm;
import frc.robot.subsystems.MotorIntake;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;

public abstract class OperatorMap extends CommandMap {

  public OperatorMap(GameController controller) {
    super(controller);
  }

  abstract JoystickButton getIntakeSwitchModeButton();

  abstract JoystickButton getStowButton();

  abstract JoystickButton getSingleSubstationButton();

  abstract JoystickButton getMiddleScoreButton();

  abstract JoystickButton getTopScoreButton();

  abstract JoystickButton getGroundButton();

  abstract JoystickButton getDefaultButton();

  abstract double getForwardIntakeValue();

  abstract double getReverseIntakeValue();

  abstract double getLeftXAxis();

  abstract double getLeftYAxis();

  abstract double getRightXAxis();

  abstract double getRightYAxis();

  abstract JoystickButton getElevatorResetButton();

  private void registerElevatorArm() {
    ElevatorArm elevatorArm = ElevatorArm.getInstance();

    elevatorArm.setDefaultCommand(
        new RepeatCommand(
            new RunCommand(() -> elevatorArm.moveElevatorAndPivot(-getLeftYAxis() * 0.5, -getRightYAxis() * 0.35),
                elevatorArm)));
    getStowButton().onTrue(elevatorArm.move(() -> SetPoint.STOW));
    getSingleSubstationButton().onTrue(elevatorArm.move(() ->
    SetPoint.SINGLE_SUBSTATION));
    getMiddleScoreButton().onTrue(elevatorArm.move(() ->
    SetPoint.MIDDLE));
    getTopScoreButton().onTrue(elevatorArm.move(() -> SetPoint.TOP));
    getGroundButton().onTrue(elevatorArm.move(() -> SetPoint.GROUND));
    getDefaultButton().onTrue(elevatorArm.move(() ->
    SetPoint.DEFAULT));
  }

  private void registerMotorIntake() {
    MotorIntake motorIntake = MotorIntake.getInstance();
    motorIntake.setDefaultCommand(
        new RepeatCommand(new RunCommand(() -> motorIntake.moveIntake(getForwardIntakeValue(), getReverseIntakeValue()),
            motorIntake)));
  }

  @Override
  public void registerCommands() {

    if (Config.Subsystems.INTAKE_MOTOR_ENABLED)
      registerMotorIntake();

    if (Config.Subsystems.ELEVATOR_ARM_ENABLED)
      registerElevatorArm();
  }
}
