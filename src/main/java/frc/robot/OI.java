package frc.robot;

import frc.robot.layout.TwoJoyStickDriverMap;
import frc.robot.layout.TwoJoyStickOperatorMap;
import frc.robot.layout.TwoJoystickTesterMap;
import frc.robot.util.controllers.GameController;
import frc.robot.util.controllers.Xbox;

public class OI {
  private static OI instance;

  public static OI getInstance() {
    if (instance == null) {
      instance = new OI();
    }
    return instance;
  }

  private GameController driver;

  public GameController getDriver() {
    return driver;
  }

  private GameController operator;

  public GameController getOperator() {
    return operator;
  }

  private GameController tester;

  public GameController getTester() {
    return tester;
  }
  public void registerCommands() {
    // new TwoJoyStickDriverMap(driver).registerCommands();
    // new TwoJoyStickOperatorMap(operator).registerCommands();
    new TwoJoystickTesterMap(tester).registerCommands();
  }

  private OI() {
    // driver =
    //     new GameController(
    //         RobotMap.ControllerMap.DRIVER_JOYSTICK,
    //         new Xbox());
    // operator = new GameController(RobotMap.ControllerMap.OPERATOR_JOYSTICK, new Xbox());
    tester = new GameController(RobotMap.ControllerMap.TESTER_JOYSTICK, new Xbox());
  }
}
