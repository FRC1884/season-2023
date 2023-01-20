package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.TwoMotorMap;

public class JoystickTwoMotors extends SubsystemBase {

  private static JoystickTwoMotors instance;
    
  public static JoystickTwoMotors getInstance() {
    if (instance == null) instance = new JoystickTwoMotors();
    return instance;
  }


  private CANSparkMax motor1, motor2;
  
  private JoystickTwoMotors() 
  {
    motor1 = new CANSparkMax(TwoMotorMap.MOTOR_ONE, MotorType.kBrushless);
    motor2 = new CANSparkMax(TwoMotorMap.MOTOR_TWO, MotorType.kBrushless);
    motor2.follow(motor1, true);
  }

  public void rotateMotor(double input)
    {
        motor1.set(input);
    }

  

  
}
