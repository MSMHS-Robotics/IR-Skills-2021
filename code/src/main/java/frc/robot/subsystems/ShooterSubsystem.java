// IGNORE THE WARNINGS THEY DONT FEEL LOVE
// Also Daniel ignore most of the comments they are here so im not dumb
package frc.robot.subsystems;

// Importing libraries and things so the code actually knows what its doing even if i dont
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
  // Below this are variables for various things like motors, encoders, and things to do with the shooter, i think
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
    
  }
}
