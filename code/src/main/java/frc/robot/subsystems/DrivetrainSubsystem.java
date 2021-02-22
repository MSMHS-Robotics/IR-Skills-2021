package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.opencv.core.Point;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
    // motors
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax left3;
    private CANSparkMax right1;
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
    private double lastHeading = 0;

    private Point location = new Point(0, 0);

    // controllers
    private PIDController distancePID;
    private PIDController headingPID;
    private PIDController turnPID;
    //private PIDController velocityPID;

    // simulation
    private EncoderSim leftEncoder_s;
    private EncoderSim rightEncoder_s;
    
    public DrivetrainSubsystem() {
        // init the motors
        left1 = new CANSparkMax(Constants.Drivetrain.left1_p, MotorType.kBrushless);
        left2 = new CANSparkMax(Constants.Drivetrain.left2_p, MotorType.kBrushless);
        left3 = new CANSparkMax(Constants.Drivetrain.left3_p, MotorType.kBrushless);
        right1 = new CANSparkMax(Constants.Drivetrain.right1_p, MotorType.kBrushless);
        right2 = new CANSparkMax(Constants.Drivetrain.right2_p, MotorType.kBrushless);
        right3 = new CANSparkMax(Constants.Drivetrain.right3_p, MotorType.kBrushless);

        // init the drivetrain
        leftSide = new SpeedControllerGroup(left1, left2, left3);
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        rightSide.setInverted(true); // invert the right side
        diffDrive = new DifferentialDrive(leftSide, rightSide);

        // get the encoders
        left1Encoder = left1.getEncoder();
        left2Encoder = left2.getEncoder();
        left3Encoder = left3.getEncoder();
        right1Encoder = right1.getEncoder();
        right2Encoder = right2.getEncoder();
        right3Encoder = right3.getEncoder();
        
        // our gearing (7:125) * ticks per rev
        left1Encoder.setPositionConversionFactor(7 * 42 / 125);
        left2Encoder.setPositionConversionFactor(7 * 42 / 125);
        left3Encoder.setPositionConversionFactor(7 * 42 / 125);
        right1Encoder.setPositionConversionFactor(7 * 42 / 125);
        right2Encoder.setPositionConversionFactor(7 * 42 / 125);
        right3Encoder.setPositionConversionFactor(7 * 42 / 125);

        // get the through-bore encoders
        leftEncoder = new Encoder(Constants.Drivetrain.leftEncoder1_p, Constants.Drivetrain.leftEncoder2_p, false, EncodingType.k1X);
        rightEncoder = new Encoder(Constants.Drivetrain.rightEncoder1_p, Constants.Drivetrain.rightEncoder2_p, true, EncodingType.k1X);

        // this is apparently out of bounds?
        //leftEncoder.setDistancePerPulse(1 / 2048);
        //rightEncoder.setDistancePerPulse(1 / 2048);

        gyro = new AHRS();

        // init our PIDs
        distancePID = new PIDController(Constants.Drivetrain.dKp, Constants.Drivetrain.dKi, Constants.Drivetrain.dKd);
        headingPID = new PIDController(Constants.Drivetrain.hKp, Constants.Drivetrain.hKi, Constants.Drivetrain.hKd);
        turnPID = new PIDController(Constants.Drivetrain.tKp, Constants.Drivetrain.tKi, Constants.Drivetrain.tKd);
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

    /**
     * Drives the robot on a heading to a distance, porportionally
     * @param distance the distance, in inches, you want to drive
     * @param angle the angle, in degrees, to drive on
     * @return if we've reached the distance or not. Does not take in to account the angle error
     */
    public boolean driveDistance(double distance, double angle) {
        diffDrive.arcadeDrive(distancePID.calculate(distance, getEncoderAverage()), headingPID.calculate(lastHeading, getHeading()), false); // false so no square inputs
        return distancePID.atSetpoint();
    }

    /**
     * Gets the current heading of the robot using the gyro
     * @return the heading of the robot, in degrees(?)
     */
    public double getHeading() {
        return gyro.getYaw();
    }

    /**
     * Turns in place to the given angle, using a PID
     * @param angle the angle you want to turn to
     * @return if we've reached that angle or not
     */
    public boolean turnToAngle(double angle) {
        diffDrive.arcadeDrive(0, turnPID.calculate(lastHeading, getHeading()), false); // false so no square inputs
        return turnPID.atSetpoint();
    }

    /**
     * A method that returns the average tick count of all left encoders
     * @return the average tick count of all the left encoders
     */
    public double getLeftEncoderAverage() {
        return (left1Encoder.getPosition() + left2Encoder.getPosition() + left3Encoder.getPosition() + leftEncoder.getDistance() * 2) / 5;
    }

    /**
     * A method that returns the average tick count of all right encoders
     * @return the average tick count of all the right encoders
     */
    public double getRightEncoderAverage() {
        return (right1Encoder.getPosition() + right2Encoder.getPosition() + right3Encoder.getPosition() + leftEncoder.getDistance() * 2) / 5;
    }

    /**
     * A method that returns the average tick count of all encoders
     * @return the average tick count of all the encoders
     */
    public double getEncoderAverage() {
        return (getLeftEncoderAverage() + getRightEncoderAverage()) / 2;
    }

    /**
     * A method that returns the number of inches a given number of rotations represents
     * @param rotations the number of rotations
     * @return how many inches that number of rotations is
     */
    public double getInches(double rotations) {
        return rotations * 6 * Math.PI;
    }

    /**
     * Resets everything there is to reset in the Drivetrain Subsystem
     */
    public void resetAll() {
        resetGyro();
        resetEncoders();
        resetAllPID();
    }

    /**
     * Sets the heading for the robot to drive on. Call this before calling any auton driving functions
     */
    public void setHeading() {
        lastHeading = getHeading();
    }

    /**
     * Resets the gyro
     */
    public void resetGyro() {
        gyro.reset();
    }

    /**
     * Resets all the encoders
     */
    public void resetEncoders() {
        left1Encoder.setPosition(0);
        left2Encoder.setPosition(0);
        left3Encoder.setPosition(0);
        right1Encoder.setPosition(0);
        right2Encoder.setPosition(0);
        right3Encoder.setPosition(0);

        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Resets the heading PID
     */
    public void resetHeadingPID() {
        headingPID.reset();
    }

    /**
     * Resets the distance PID
     */
    public void resetDistancePID() {
        distancePID.reset();
    }

    /**
     * Resets the turning PID
     */
    public void resetTurnPID() {
        turnPID.reset();
    }

    /**
     * Resets the all the PIDs
     */
    public void resetAllPID() {
        distancePID.reset();
        headingPID.reset();
        turnPID.reset();
    }

    @Override
    public void periodic() {
        location.x += 0;
        location.y += 0;
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
