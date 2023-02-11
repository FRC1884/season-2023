package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private static Arm instance;

  public static Arm getInstance() {
    if (instance == null)
      instance = new Arm();
    return instance;
  }

  private CANSparkMax motor1 = new CANSparkMax(0, null);
  private CANSparkMax motor2 = new CANSparkMax(0, null);

  private DigitalInput topLine = new DigitalInput(0);
  private DigitalInput topLimit = new DigitalInput(0);
  private DigitalInput bottomLine = new DigitalInput(0);
  private DigitalInput bottomLimit = new DigitalInput(0);

  private static void moveMotor(CANSparkMax motor, DigitalInput top, DigitalInput bottom, double speed) {
    if ((speed < 0) && (bottom.get()))
      motor.set(0);
    else if (top.get())
      motor.set(0);
    else
      motor.set(1);
  }

  public Command getMotor1Command(double speed) {
    return new InstantCommand(() -> moveMotor(motor1, topLine, bottomLine, speed));
  }

  public Command getMotor2Command(double speed) {
    return new InstantCommand(() -> moveMotor(motor2, topLimit, bottomLimit, speed));
  }
}
