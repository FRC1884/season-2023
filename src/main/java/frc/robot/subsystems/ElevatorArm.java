package frc.robot.subsystems;

import java.util.function.Supplier;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.ElevatorMap;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorArm extends SubsystemBase {
    private static ElevatorArm instance;

    public static ElevatorArm getInstance() {
        if (instance == null) {
            instance = new ElevatorArm();
        }
        return instance;
    }

    public enum SetPoint {
        GROUND(ElevatorPosition.GROUND, PivotPosition.GROUND),
        MIDDLE(ElevatorPosition.MID, PivotPosition.MID),
        SINGLE_SUBSTATION(ElevatorPosition.SUBSTATION, PivotPosition.SUBSTATION),
        STOW(ElevatorPosition.STOW, PivotPosition.STOW),
        TOP(ElevatorPosition.TOP, PivotPosition.TOP),
        DEFAULT(ElevatorPosition.DEFAULT, PivotPosition.DEFAULT);

        private final ElevatorPosition elevatorPosition;
        private final PivotPosition pivotPosition;

        SetPoint(ElevatorPosition ePos, PivotPosition pPos) {
            this.elevatorPosition = ePos;
            this.pivotPosition = pPos;
        }

        public ElevatorPosition getElevatorPosition() {
            return elevatorPosition;
        }

        public PivotPosition getPivotPosition() {
            return pivotPosition;
        }

    }

    public enum ElevatorPosition {
        TOP(95.1),
        MID(60.0),
        GROUND(22.6),
        SUBSTATION(40.45),
        STOW(40.05),
        DEFAULT(0);

        private final double encoderValue;

        ElevatorPosition(double encoderValue) {
            this.encoderValue = encoderValue;
        }

        public double getEncoderPos() {
            return encoderValue;
        }
    }

    public enum PivotPosition {
        TOP(-20),
        MID(-18.5),
        GROUND(-49),
        SUBSTATION(-10),
        STOW(5.2),
        DEFAULT(0);

        private final double encoderValue;

        PivotPosition(double encoderValue) {
            this.encoderValue = encoderValue;
        }

        public double getEncoderPos() {
            return encoderValue;
        }

    }

    private CANSparkMax elevatorMotor, pivotMotor;
    private SparkMaxLimitSwitch forwardLimit, reverseLimit;
    private double elevatorP, elevatorI, elevatorD, elevatorFF;
    private double pivotP, pivotI, pivotD, pivotFF;

    private double elevatorTempSetpoint, pivotTempSetpoint;
    private double elevatorMaxAccel, elevatorMaxVel;
    private double pivotMaxAccel, pivotMaxVel;

    private ElevatorArm() {
        elevatorP = 5;
        elevatorI = 0;
        elevatorD = 0;
        elevatorFF = 0.05;
        elevatorMaxAccel = 3000;
        elevatorMaxVel = 3000;
        pivotP = 0.1;
        pivotI = 0.001;
        pivotD = 0;
        pivotFF = 0.05;
        pivotMaxAccel = 3000;
        pivotMaxVel = 3000; 

        elevatorMotor = new CANSparkMax(ElevatorMap.ELEVATOR_MOTOR_ID, MotorType.kBrushless);
        pivotMotor = new CANSparkMax(ElevatorMap.PIVOT_MOTOR_ID, MotorType.kBrushless);
        elevatorMotor.restoreFactoryDefaults();
        pivotMotor.restoreFactoryDefaults();

        forwardLimit = elevatorMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        forwardLimit.enableLimitSwitch(true);
        reverseLimit = elevatorMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        reverseLimit.enableLimitSwitch(true);

        setMotorPID(elevatorMotor, elevatorP, elevatorI, elevatorD, elevatorFF);
        elevatorMotor.getPIDController().setIZone(0);
        elevatorMotor.getPIDController().setFF(0.000156);
        elevatorMotor.getPIDController().setOutputRange(-1, 1);

        setMotorPID(pivotMotor, pivotP, pivotI, pivotD, pivotFF);
        elevatorMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.setIdleMode(IdleMode.kBrake);

        elevatorMotor.getPIDController().setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
        elevatorMotor.getPIDController().setSmartMotionMaxAccel(elevatorMaxAccel, 0);
        elevatorMotor.getPIDController().setSmartMotionMaxVelocity(elevatorMaxVel, 0);
        elevatorMotor.getPIDController().setSmartMotionAllowedClosedLoopError(1, 0);
        elevatorMotor.getPIDController().setSmartMotionMinOutputVelocity(1, 0);

        pivotMotor.getPIDController().setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
        pivotMotor.getPIDController().setSmartMotionMaxAccel(pivotMaxAccel, 0);
        pivotMotor.getPIDController().setSmartMotionMaxVelocity(pivotMaxVel, 0);
        pivotMotor.getPIDController().setSmartMotionAllowedClosedLoopError(1, 0);
        pivotMotor.getPIDController().setSmartMotionMinOutputVelocity(1, 0);

        elevatorMotor.burnFlash();
        pivotMotor.burnFlash();
        SmartDashboard.putNumber("Elevator velocity limit", 3000);
        SmartDashboard.putNumber("Elevator acceleration limit", 3000);
        SmartDashboard.putNumber("Pivot velocity limit", 3000);
        SmartDashboard.putNumber("Pivot acceleration limit", 3000);
        //used to change accel and velo limits on the fly

        SmartDashboard.putNumber("Elevator P", elevatorP);
        SmartDashboard.putNumber("Elevator I", elevatorI);
        SmartDashboard.putNumber("Elevator D", elevatorD);
        SmartDashboard.putNumber("Elevator FF", elevatorFF);

        SmartDashboard.putNumber("Pivot P", elevatorP);
        SmartDashboard.putNumber("Pivot I", elevatorI);
        SmartDashboard.putNumber("Pivot D", elevatorD);
        SmartDashboard.putNumber("Pivot FF", elevatorFF);


        //used to change P and FF on the fly

        SmartDashboard.putNumber("Elevator setpoint", 0);
        SmartDashboard.putNumber("Pivot setpoint", 0);

    }

    public void setMotorPID(CANSparkMax motor, double kP, double kI, double kD, double FF) {
        motor.getPIDController().setP(kP);
        motor.getPIDController().setI(kI);
        motor.getPIDController().setD(kD);
        motor.getPIDController().setFF(FF);
    }

    public void getEncoderPosition() {
        SmartDashboard.putNumber("Elevator encoder pos", elevatorMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("Pivot encoder pos", pivotMotor.getEncoder().getPosition());
    }

    public void moveElevator(ElevatorPosition setPoint) {
        elevatorMotor.getPIDController().setReference(setPoint.getEncoderPos(), ControlType.kSmartMotion);
    }

    // Elevator Functionality
    public void moveElevator(double input) {
        elevatorMotor.set(input);
    }

    public Command moveElevatorCommand(Supplier<ElevatorPosition> elevatorPos) {
        return new RunCommand(() -> moveElevator(elevatorPos.get()))
                .until(() -> Math
                        .abs(elevatorMotor.getEncoder().getPosition() - elevatorPos.get().getEncoderPos()) < 5);
    }

    public void movePivot(PivotPosition setPoint) {
        pivotMotor.getPIDController().setReference(setPoint.getEncoderPos(), ControlType.kSmartMotion);
    }

    public void moveElevatorTemp() {
        elevatorMotor.getPIDController().setReference(elevatorTempSetpoint, ControlType.kSmartMotion);
    }

    public void movePivotTemp() {
        pivotMotor.getPIDController().setReference(pivotTempSetpoint, ControlType.kSmartMotion);
    }


    public void movePivot(double input) {
        pivotMotor.set(input);
    }

    public Command movePivotCommand(Supplier<PivotPosition> pivotPos) {
        return new RunCommand(
                () -> {
                    movePivot(pivotPos.get().getEncoderPos() < pivotMotor.getEncoder().getPosition() ? -0.7 : 0.7);
                }).until(() -> (Math.abs(pivotMotor.getEncoder().getPosition() - pivotPos.get().getEncoderPos()) < 2));
    }

    public void moveElevatorAndPivot(double elevatorInput, double pivotInput) {
        moveElevator(elevatorInput);
        movePivot(pivotInput);
    }

    public Command moveToSetPoint(Supplier<SetPoint> setPoint) {
        return movePivotCommand(() -> PivotPosition.MID)
                .andThen(moveElevatorCommand(() -> setPoint.get().getElevatorPosition()))
                .andThen(movePivotCommand(() -> setPoint.get().getPivotPosition()));
    }

    public Command resetElevatorMotor() {
        return new InstantCommand(() -> {
            elevatorMotor.getEncoder().setPosition(ElevatorPosition.DEFAULT.getEncoderPos());
            pivotMotor.getEncoder().setPosition(PivotPosition.DEFAULT.getEncoderPos());
        });

    }

    public boolean getTopSwitch() {
        return forwardLimit.isPressed();
    }

    public boolean getBottomSwitch() {
        return reverseLimit.isPressed();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("top switch", getTopSwitch());
        SmartDashboard.putBoolean("bottom switch", getBottomSwitch());
        SmartDashboard.putNumber("Pivot Encoder", pivotMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("Elevator Encoder", elevatorMotor.getEncoder().getPosition());
        getEncoderPosition();

        elevatorP = SmartDashboard.getNumber("Elevator P", elevatorP);
        elevatorI = SmartDashboard.getNumber("Elevator I", elevatorI);
        elevatorD = SmartDashboard.getNumber("Elevator D", elevatorD);
        pivotP = SmartDashboard.getNumber("Pivot P", pivotP);
        pivotI = SmartDashboard.getNumber("Pivot I", pivotI);
        pivotD = SmartDashboard.getNumber("Pivot D", pivotD);
        elevatorFF = SmartDashboard.getNumber("Elevator FF", elevatorFF);
        pivotFF = SmartDashboard.getNumber("Pivot FF", pivotFF);
        setMotorPID(elevatorMotor, elevatorP, elevatorI, elevatorD, elevatorFF);
        setMotorPID(pivotMotor, pivotP, pivotI, pivotD, pivotFF);

        elevatorTempSetpoint = SmartDashboard.getNumber("Elevator setpoint", 0);
        pivotTempSetpoint = SmartDashboard.getNumber("Pivot setpoint", 0);
        
        elevatorMotor.getPIDController()
                .setSmartMotionMaxAccel(SmartDashboard.getNumber("Elevator acceleration limit", elevatorMaxAccel), 0);
        elevatorMotor.getPIDController()
                .setSmartMotionMaxVelocity(SmartDashboard.getNumber("Elevator velocity limit", elevatorMaxVel), 0);

        pivotMotor.getPIDController()
                .setSmartMotionMaxAccel(SmartDashboard.getNumber("Pivot acceleration limit", pivotMaxAccel), 0);   
        pivotMotor.getPIDController()
                .setSmartMotionMaxVelocity(SmartDashboard.getNumber("Pivot velocity limit", pivotMaxVel), 0);
        


        
    }

}