// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.BeamBreakMap;

public class BeamBreakMotors extends SubsystemBase {
  private static BeamBreakMotors instance;
  public static BeamBreakMotors getInstance(){
    if (instance == null){instance = new BeamBreakMotors();}
    return instance;
  }
  private CANSparkMax motor;
  private DigitalInput beam;

  private BeamBreakMotors() {
    motor = new CANSparkMax(BeamBreakMap.MOTOR, MotorType.kBrushless);
    beam = new DigitalInput(BeamBreakMap.CHANNEL);
  }

  public void activateMotor()
  {
    if(beam.get())
    {
      motor.set(0.5);
    }
    else
    {
      motor.set(0.0);
    }
  }

}
