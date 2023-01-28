package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorControl extends SubsystemBase{
    private static MotorControl instance;
  public static MotorControl getInstance() {
    if (instance == null) instance = new MotorControl();
    return instance;
  }

  CANSparkMax motor;
  private MotorControl(){
    motor = new CANSparkMax(0, MotorType.kBrushless);
  }
  public void speed(){

    if (motor.get() != 0){
      motor.set(0);
    }
  }
  

public void turnOffMotor(){
  motor.stopMotor();
  }
public void turnOnMotor(){
    motor.set(1);
    }

}
