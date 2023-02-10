// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
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
    private double setpoint;
    private RelativeEncoder encoder;
    private PIDController pid;
    
  
  
  private TwoMotorOpp() 
  {
      feedForward = new SimpleMotorFeedforward(TwoMotorOppMap.kS, TwoMotorOppMap.kV, TwoMotorOppMap.kA);
      motor1 = new CANSparkMax(TwoMotorOppMap.MOTOR_ONE, MotorType.kBrushless);
      motor2 = new CANSparkMax(TwoMotorOppMap.MOTOR_TWO, MotorType.kBrushless);
      motor2.follow(motor1, true);
      encoder = motor1.getEncoder();
      setpoint = 800;
      pid = new PIDController(TwoMotorOppMap.kP, TwoMotorOppMap.kI, TwoMotorOppMap.kD);
  }

  public void rotateMotor()
    {
        motor1.setVoltage(pid.calculate(encoder.getVelocity(), setpoint)+ feedForward.calculate(800));
        //motor1.setVoltage(feedForward.calculate(800));
    }

    public void stopMotor()
    {
        motor1.setVoltage(0);
    }

    @Override
    public void periodic()
    {
      SmartDashboard.putNumber("motor1_velocity", motor1.getEncoder().getVelocity());
      SmartDashboard.putNumber("motor2_velocity", motor2.getEncoder().getVelocity());
    }
}
