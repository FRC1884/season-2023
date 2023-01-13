package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.MotorMap;

public class JoystickMotorRotation extends SubsystemBase
{
    private static JoystickMotorRotation instance;
    private CANSparkMax motor;


    public static JoystickMotorRotation getInstance() {
        if (instance == null) instance = new JoystickMotorRotation();
        return instance;
      }

    private JoystickMotorRotation()
    {
      motor = new CANSparkMax(MotorMap.MOTOR, MotorType.kBrushless);
    }

    public void rotateMotor(double input)
    {
        motor.set(input);
    }
}