// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap.TwoMotorOppMap;

public class TwoMotorOpp extends SubsystemBase {

  private static TwoMotorOpp instance;
    
    public static TwoMotorOpp getInstance() {
      if (instance == null) instance = new TwoMotorOpp();
      return instance;
    }

    SimpleMotorFeedforward feedForward;
    private CANSparkMax motor1, motor2;
  
  
  private TwoMotorOpp() 
  {
      feedForward = new SimpleMotorFeedforward(TwoMotorOppMap.kS, TwoMotorOppMap.kV, TwoMotorOppMap.kA);
      motor1 = new CANSparkMax(TwoMotorOppMap.MOTOR_ONE, MotorType.kBrushless);
      motor2 = new CANSparkMax(TwoMotorOppMap.MOTOR_TWO, MotorType.kBrushless);
      motor2.follow(motor1, true);

  }

  public void rotateMotor()
    {
        motor1.set(feedForward.calculate(1.0));
    }
}
