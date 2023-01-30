package frc.robot.layout;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.PinkArm;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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

  public abstract JoystickButton getTwoMotorButton(); 

  public abstract JoystickButton getIntakeButton();
  
  public abstract double getElevatorSpeed();
  
  public abstract double getLeftXAxis();

  public abstract double getLeftYAxis();

  public abstract double getRightYAxis();

  @Override
  public void registerCommands(){
    Intake intake = Intake.getInstance();
    getIntakeButton().onTrue(intake.IntakeOpenCommand());
    
    var twoMotors = JoystickTwoMotors.getInstance();

    twoMotors.setDefaultCommand(new RunCommand(() -> twoMotors.rotateMotor(getElevatorSpeed())));
    
    PinkArm pinkArm = PinkArm.getInstance();

    pinkArm.setDefaultCommand(new RunCommand(() -> pinkArm.PivotOperation(-getLeftYAxis()), pinkArm));
    pinkArm.setDefaultCommand(new RunCommand(() -> pinkArm.TelescopeOperation(-getRightYAxis()), pinkArm));

    TwoMotorOpp twoMotorOpp = TwoMotorOpp.getInstance();
    getTwoMotorButton().onTrue(new InstantCommand(() -> twoMotorOpp.rotateMotor(), twoMotorOpp));
  }
}
