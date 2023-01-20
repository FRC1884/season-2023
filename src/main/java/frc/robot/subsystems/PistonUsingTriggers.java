// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap.PistonMap;

public class PistonUsingTriggers {
    private static PistonUsingTriggers instance;


  public static PistonUsingTriggers getInstance() {
    if (instance == null) instance = new PistonUsingTriggers();
    return instance;
  }

  private DoubleSolenoid piston;

  private PistonUsingTriggers(){
    piston = new DoubleSolenoid(PneumaticsModuleType.REVPH, PistonMap.FORWARD_CHANNEL, PistonMap.REVERSE_CHANNEL);
  }

  public void extendPiston(boolean shouldExtend)
  {
    if(shouldExtend){
      piston.set(Value.kForward);
    }
    else{
      piston.set(Value.kOff);
    }
  }
}
