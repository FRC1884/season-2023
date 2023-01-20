package frc.robot.layout;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.util.controllers.ButtonMap.Axis;
import frc.robot.util.controllers.ButtonMap.Button;
import frc.robot.util.controllers.GameController;

public class TwoJoyStickOperatorMap extends OperatorMap{

    public TwoJoyStickOperatorMap(GameController controller) {
        super(controller);
      }

    @Override
    public void getElevatorDownButton()
    {

    }

    @Override
    public void getElevatorMiddleButton()
    {

    }
    
    @Override
    public void getElevatorUpButton()
    {

    }

    @Override
    public double getLeftXAxis()
    {
        return controller.getAxis(Axis.AXIS_LEFT_X);
    }

    @Override
    public void registerCommands() {
        super.registerCommands();
    }
}
