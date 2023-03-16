package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ElevatorArm;
import frc.robot.util.controllers.CommandMap;
import frc.robot.util.controllers.GameController;

public abstract class TesterMap extends CommandMap {
    public TesterMap(GameController controller) {
        super(controller);
    }
    
    public abstract JoystickButton getMoveElevatorButton();

    public abstract JoystickButton getMovePivotButton();

    @Override
    public void registerCommands() {
        var elevatorArm = ElevatorArm.getInstance();
        getMoveElevatorButton().onTrue(new RunCommand(() -> elevatorArm.moveElevatorTemp())
                .handleInterrupt(() -> elevatorArm.moveElevator(0)));

        getMovePivotButton().onTrue(
                new RunCommand(() -> elevatorArm.movePivotTemp()).handleInterrupt(() -> elevatorArm.movePivot(0)));
        

        
    }
}
