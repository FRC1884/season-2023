package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import frc.robot.RobotMap.ArmMap;

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

  private CANSparkMax motor1 = new CANSparkMax(ArmMap.motor1Port, null);
  private CANSparkMax motor2 = new CANSparkMax(ArmMap.motor2Port, null);

  private DigitalInput topLine = new DigitalInput(ArmMap.topLimitPort);
  private DigitalInput topLimit = new DigitalInput(ArmMap.topLightPort);
  private DigitalInput bottomLine = new DigitalInput(ArmMap.bottomLimitPort);
  private DigitalInput bottomLimit = new DigitalInput(ArmMap.bottomLightPort);

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
