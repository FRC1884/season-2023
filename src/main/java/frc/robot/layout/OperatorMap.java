package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.PinkArm;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;

public abstract class OperatorMap extends CommandMap {

  public OperatorMap(GameController controller) {
    super(controller);
  }

  public abstract void getElevatorUpButton();

  public abstract void getElevatorDownButton();

  public abstract void getElevatorMiddleButton();

  abstract double getLeftYAxis();

  abstract double getRightYAxis();

  @Override
  public void registerCommands(){
    PinkArm pinkArm = PinkArm.getInstance();

    pinkArm.setDefaultCommand(new RunCommand(() -> pinkArm.PivotOperation(-getLeftYAxis()), pinkArm));
    pinkArm.setDefaultCommand(new RunCommand(() -> pinkArm.TelescopeOperation(-getRightYAxis()), pinkArm));
  }
}
