package frc.robot.subsystems;

import frc.robot.RobotMap.IntakeMap;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private static Intake instance;

  public static Intake getInstance() {
    if (instance == null)
      instance = new Intake();
    return instance;
  }

  private CANSparkMax motor = new CANSparkMax(IntakeMap.intakePort, null);

  public Command intakeCommand(double speed) {
    return new InstantCommand(() -> {
      motor.set(speed);
    });
  }

}
