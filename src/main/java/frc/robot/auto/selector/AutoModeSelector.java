package frc.robot.auto.selector;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoModeSelector implements AutoModeList {
  private static AutoModeSelector instance;

  public static AutoModeSelector getInstance() {
    if (instance == null) {
      instance = new AutoModeSelector();
    }
    return instance;
  }

  private final SendableChooser<Command> modeChooserRed;
  private final SendableChooser<Command> modeChooserBlue;

  private AutoModeSelector() {
    modeChooserRed = new SendableChooser<>();
    modeChooserBlue = new SendableChooser<>();
    updateAutoModeSelector();

    SmartDashboard.putData(modeChooserRed);
  }

  public void updateAutoModeSelector() {
    modeChooserRed.setDefaultOption("DONOTHING", AutoModeListRed.DONOTHING.getAuto());
    modeChooserBlue.setDefaultOption("DONOTHING", AutoModeListBlue.DONOTHING.getAuto());

    for (AutoModeListRed auto : AutoModeListRed.values()) {
      modeChooserRed.addOption(auto.name(), auto.getAuto());
    }

    for (AutoModeListBlue auto : AutoModeListBlue.values()) {
      modeChooserBlue.addOption(auto.name(), auto.getAuto());
    }
  }

  public SendableChooser<Command> getRedChooser() {
    return modeChooserRed;
  }

  public SendableChooser<Command> getBlueChooser() {
    return modeChooserBlue;
  }
}
