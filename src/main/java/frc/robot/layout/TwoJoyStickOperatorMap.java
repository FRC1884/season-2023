package frc.robot.layout;

import frc.robot.util.controllers.GameController;
import frc.robot.util.controllers.ButtonMap.Axis;

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
    double getLeftYAxis() {
        return controller.getAxis(Axis.AXIS_LEFT_Y);
    }

    @Override
    double getRightYAxis() {
        return controller.getAxis(Axis.AXIS_RIGHT_Y);
    }
    @Override
    public void registerCommands() {
      super.registerCommands();
    }
    
}
