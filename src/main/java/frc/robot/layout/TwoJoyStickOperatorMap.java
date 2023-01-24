package frc.robot.layout;

import frc.robot.util.controllers.GameController;
import frc.robot.util.controllers.ButtonMap.Axis;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.util.controllers.ButtonMap.Button;
import frc.robot.util.controllers.GameController;

public class TwoJoyStickOperatorMap extends OperatorMap {
    
    public TwoJoyStickOperatorMap(GameController controller) {
      super(controller);
    }

    @Override
    public void getElevatorUpButton() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getElevatorDownButton() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getElevatorMiddleButton() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public double getLeftXAxis()
    {
        return controller.getAxis(Axis.AXIS_LEFT_X);
    }
    @Override
    double getLeftYAxis() {
        return controller.getAxis(Axis.AXIS_LEFT_Y);
    }

    @Override
    double getRightYAxis() {
        return controller.getAxis(Axis.AXIS_RIGHT_Y);
    }
    
    @Override
    public JoystickButton getTwoMotorButton()
    {
        return controller.getButton(Button.BUTTON_B);
    }

    @Override
    public void registerCommands() {
        super.registerCommands();
    }
}
