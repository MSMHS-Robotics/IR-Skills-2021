package frc.robot.subsystems;

// REV stuff
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// Misc
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;

public class ShooterSubsystem extends SubsystemBase {
    // Motors
    private CANSparkMax shooterMotor;
    private CANSparkMax shooterMotor2;
    private CANSparkMax triggerMotor;

    // Control
    private PIDController shooterPID;
    private CANEncoder encoder;

    private boolean shootingFlag = false;

    private double pastTime = 0;
    private double pastVelocity = 0;
    private double shotAcceleration = 0;

    public ShooterSubsystem() {
        shooterMotor = new CANSparkMax(7, MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(8, MotorType.kBrushless);
        triggerMotor = new CANSparkMax(12, MotorType.kBrushless);
        shooterPID = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD, Constants.Shooter.kF);

        encoder = shooterMotor.getEncoder();
        shooterMotor.setInverted(true);
        shooterMotor2.follow(shooterMotor, true);
    }

    // Sets the flywheel to a set speed
    public void warmUp(double RPM) {
        shooterMotor.set(shooterPID.calculate(encoder.getVelocity(), RPM));
    }

    public void setShootingFlag(Boolean stillShooting) {
        shootingFlag = stillShooting;
    }

    // Tells whether we are shooting or not
    public boolean isShooting() {
        return shootingFlag;
    }

    // Runs the trigger wheel
    public void runTrigger(double power) {
        triggerMotor.set(power);
    }

    // Checks to see if the shooter is at the correct speed
    public boolean isWarmedUp() {
        if (shooterPID.atSetpoint() && Math.abs(shotAcceleration) < Constants.Shooter.accelerationTolerance) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void periodic() {
        double currentTime = Timer.getFPGATimestamp(); // get the time
        double currentVelocity = encoder.getVelocity(); // and get our current velocity

        shotAcceleration = (currentVelocity - pastVelocity) / ((currentTime - pastTime) * 60); // gets the change in velocity (acceleration) of the flywheel
        pastTime = currentTime;
        pastVelocity = currentVelocity;
    }

    @Override
    public void simulationPeriodic() {
    }
}