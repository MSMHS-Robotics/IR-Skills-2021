// IGNORE THE WARNINGS THEY DONT FEEL LOVE
package frc.robot.subsystems;

// Motors
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// Encoders, PID's, and other things 
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

// Shuffleboard
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

// Other
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import java.util.Map; 


public class ShooterSubsystem extends SubsystemBase {
  // Motors
  private CANSparkMax shooterMotor;
  private CANSparkMax shooterMotor2;

  // Control
  private CANPIDController shooterPID;
  private CANEncoder encoder;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOUtput;
  private double RPMSetpoint;
  private Boolean shootingFlag;

  private double pastTime = 0;
  private double pastVelocity = 0;
  private double shotAcceleration = 0; 

    public ShooterSubsystem() {
    }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
