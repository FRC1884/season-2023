package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.util.controllers.GameController;
import frc.robot.util.controllers.ButtonMap.Button;

public class TwoJoystickTesterMap extends TesterMap{
    public TwoJoystickTesterMap(GameController controller) {
        super(controller);
    }

    @Override
    public JoystickButton getMoveElevatorButton() {
        return controller.getButton(Button.BUTTON_A);
    }

    @Override
    public JoystickButton getMovePivotButton() {
        return controller.getButton(Button.BUTTON_Y);
    }
    
    @Override
    public void registerCommands() {
        super.registerCommands();
    }

}
