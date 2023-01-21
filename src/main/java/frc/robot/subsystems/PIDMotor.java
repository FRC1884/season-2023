// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.TwoMotorMap;

/** Add your docs here. */
public class PIDMotor extends SubsystemBase {
    
    private static PIDMotor instance;

    public static PIDMotor getInstance() {
        if (instance == null) instance = new PIDMotor();
        return instance;
      }
    
    private CANSparkMax motor;
    private PIDController pidController;
    private RelativeEncoder encoder;

    private PIDMotor(){
        motor = new CANSparkMax(TwoMotorMap.MOTOR_THREE, MotorType.kBrushless);
        encoder = motor.getEncoder();
        pidController = new PIDController(.1, 0, 0);
        pidController.setTolerance(5,10);
    }

    public void rotateToNinety(){
        motor.set(pidController.calculate(encoder.getPosition(),encoder.getCountsPerRevolution()/4.0));
    }

    public void joystickMotorRotation(double x, double y){
        if(x==0){
            if(y>0){motor.set(pidController.calculate(encoder.getPosition(),0.0));}
            else if(y<0){motor.set(pidController.calculate(encoder.getPosition(),encoder.getCountsPerRevolution()/2.0));}
        }
        else{
            motor.set(pidController.calculate(encoder.getPosition(),encoder.getCountsPerRevolution()*Math.toDegrees(Math.atan2(y, x))/360.0));
        }
    }

    public Command rotateToNinetyCommand(){
        return new FunctionalCommand(null, () -> instance.rotateToNinety(), null, () -> pidController.atSetpoint(), this);
    }

    public Command rotateWithJoystickCommand(double xInput, double yInput){
        return new FunctionalCommand(null, () -> instance.joystickMotorRotation(xInput, yInput), null, () -> pidController.atSetpoint(), this);
    }
}