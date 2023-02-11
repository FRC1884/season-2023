package frc.robot.subsystems;

import frc.robot.RobotMap.FrictionPadMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FrictionPad extends SubsystemBase {
  private static FrictionPad instance;

  public static FrictionPad getInstance() {
    if (instance == null)
      instance = new FrictionPad();
    return instance;
  }

  private DoubleSolenoid piston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, FrictionPadMap.piston1Channels[0],
      FrictionPadMap.piston1Channels[1]);
  private DoubleSolenoid piston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, FrictionPadMap.piston2Channels[0],
      FrictionPadMap.piston2Channels[1]);

  public void togglePistons() {
    piston1.toggle();
    piston2.toggle();
  }

  public Command pistonCommand() {
    return new InstantCommand(() -> togglePistons());
  }

}