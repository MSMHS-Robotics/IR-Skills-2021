package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    // motors
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax left3;
    private CANSparkMax right1; // remember to invert these at some point
    private CANSparkMax right2;
    private CANSparkMax right3;

    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;

    private DifferentialDrive diffDrive;

    // encoders and other sensor-y bits
    private CANEncoder left1Encoder;
    private CANEncoder left2Encoder;
    private CANEncoder left3Encoder;
    private CANEncoder right1Encoder;
    private CANEncoder right2Encoder;
    private CANEncoder right3Encoder;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private AHRS gyro;

    // controllers
    private PIDController distancePID;
    
    public DrivetrainSubsystem() {
        leftSide = new SpeedControllerGroup(left1, left2, left3);
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        diffDrive = new DifferentialDrive(leftSide, rightSide);
    }

    /**
     * a drive method to be used in tele-op for driving
     * @param leftStickY the y-val of the left stick, for controlling speed
     * @param rightStickX the x-val of the right stick, for controlling rotation
     * @param quickTurn a boolean that controls whether we are in quick-turn mode (used to turn quickly)
     */
    public void driveTeleOp(double leftStickY, double rightStickX, boolean quickTurn) {
        diffDrive.curvatureDrive(leftStickY, rightStickX, quickTurn);
    }

    public boolean driveDistance(double distance, double angle) {
        diffDrive.arcadeDrive(distancePID.calculate(distance, getEncoderAverage()), 0.0, false); // false so no square inputs
        return distancePID.atSetpoint();
    }

    /**
     * A method that returns the average tick count of all left encoders
     * @return the average tick count of all the left encoders except it doesn't because I forgot about the through bore darn
     */

    /*

    crap I think I need to add in ticks per rev and some PI*Diameter stuff to get dist because of adding in the through-bore encoders
    maybe the through-bores should be weighted more (added into the average twice?)

    public double getLeftEncoderAverage() {
        return (left1Encoder.getPosition() + left2Encoder.getPosition() + left3Encoder.getPosition()) / 3;
    }

    public double getRightEncoderAverage() {
        return (right1Encoder.getPosition() + right2Encoder.getPosition() + right3Encoder.getPosition()) / 3;
    }

    public double getEncoderAverage() {
        return (getLeftEncoderAverage() + getRightEncoderAverage()) / 2;
    }
    */

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
