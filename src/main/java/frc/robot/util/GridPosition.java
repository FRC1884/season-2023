package frc.robot.util;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation;

public class GridPosition {
    private static GridPosition instance;

    public GridPosition getInstance() {
      if (instance == null)
        instance = new GridPosition();
      return instance;
    }


    private static final double CONE_OFFSET = 0.55;
    // Red has tags 1,2,3 
    // Blue has tags 6,7,8
    public enum GridSetpoint {
      BARRIER_LEFT(new Translation3d(7.24310, 0.41621 + CONE_OFFSET, 0.46272)),
      BARRIER_CUBE(new Translation3d(7.24310, 0.41621, 0.46272)),
      BARRIER_RIGHT(new Translation3d(7.24310, 0.41621 - CONE_OFFSET, 0.46272)),
      CHARGE_LEFT(new Translation3d(7.24310, -1.26019 + CONE_OFFSET, 0.46272)),
      CHARGE_CUBE(new Translation3d(7.24310, -1.26019, 0.46272)),
      CHARGE_RIGHT(new Translation3d(7.24310, -1.26019 - CONE_OFFSET, 0.46272)),
      BUMPER_LEFT(new Translation3d(7.24310, -2.93659 + CONE_OFFSET, 0.46272)),
      BUMPER_CUBE(new Translation3d(7.24310, -2.93659, 0.46272)),
      BUMPER_RIGHT(new Translation3d(7.24310, -2.93659 - CONE_OFFSET, 0.46272));

      private Translation3d absolutePositionOnField;

      private GridSetpoint(Translation3d pos) {
        absolutePositionOnField = pos;
      }

      public Translation3d getPosition() {
        return absolutePositionOnField;
      }
    }
    
    boolean isRedAlliance;

    private GridPosition() {
      isRedAlliance = DriverStation.getAlliance().equals(DriverStation.Alliance.Red);
    }

    public Translation3d getPosition(GridSetpoint gp) {
      Translation3d pos = gp.getPosition();
      if (!isRedAlliance) {
        pos = new Translation3d(-pos.getX(), pos.getY(), pos.getZ());
      }
      return pos;
    }


}
