// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.PinkMap;

public class PinkArm extends SubsystemBase {
  public static PinkArm instance;

  public static PinkArm getInstance(){
    if (instance == null){instance = new PinkArm();}
    return instance;
  }
  private CANSparkMax pivotMotor;
  private CANSparkMax teleMotor;
  private RelativeEncoder pivotEncoder;
  private RelativeEncoder teleEncoder;

  /** Creates a new PinkArm. */
  private PinkArm() {
    pivotMotor = new CANSparkMax(PinkMap.PIVOT_PORT, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getEncoder();
    teleMotor = new CANSparkMax(PinkMap.TELESCOPE_PORT,MotorType.kBrushless);
    teleEncoder = teleMotor.getEncoder();
  }

  public void PivotOperation(double input){
    if(pivotEncoder.getPosition()/PinkMap.PIVOT_RATIO/pivotEncoder.getCountsPerRevolution() <= PinkMap.PIVOT_MIN && input < 0){
      pivotMotor.set(0);
    }
    if(pivotEncoder.getPosition()/PinkMap.PIVOT_RATIO/pivotEncoder.getCountsPerRevolution() >= PinkMap.PIVOT_MAX && input > 0){
      pivotMotor.set(0);
    }
    else{
      pivotMotor.set(input*.3);
    }
  }

  public void TelescopeOperation(double input){
    if(teleEncoder.getPosition()/PinkMap.TELESCOPE_RATIO/teleEncoder.getCountsPerRevolution() <= PinkMap.TELESCOPE_MIN && input < 0){
      teleMotor.set(0);
    }
    if(teleEncoder.getPosition()/PinkMap.TELESCOPE_RATIO/teleEncoder.getCountsPerRevolution() >= PinkMap.TELESCOPE_MAX && input > 0){
      teleMotor.set(0);
    }
    else{
      teleMotor.set(input*.12);
    }
  }
}
