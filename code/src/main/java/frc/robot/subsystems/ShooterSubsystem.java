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

  // Shuffleboard things commented out for my own sanity, uncomment this once constants are there
  /*private ShuffleboardTab tab1 = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry ShooterkP = tab1.addPersistent("ShooterkP", constants.ShooterkPdivide).getEntry();
  private NetworkTableEntry kPdivide = tab1.addPersistent("kPdivide", Constants.ShooterkPdivide).getEntry();
  private NetworkTableEntry ShooterkI = tab1.addPersistent("ShooterkI", Constants.ShooterkI).getEntry();
  private NetworkTableEntry kIdivide = tab1.addPersistent("kIdivide", Constants.ShooterkIdivide).getEntry();
  private NetworkTableEntry ShooterkD = tab1.addPersistent("ShooterkD", Constants.ShooterkD).getEntry();
  private NetworkTableEntry kDdivide = tab1.addPersistent("kDdivide", Constants.ShooterkDdivide).getEntry();
  private NetworkTableEntry ShooterkIz = tab1.addPersistent("ShooterkIz", Constants.ShooterkIz).getEntry();
  private NetworkTableEntry ShooterkFF = tab1.addPersistent("ShooterkFF", Constants.ShooterkFF).getEntry();
  private NetworkTableEntry kFFdivide = tab1.addPersistent("kFFdivide", Constants.ShooterkFFdivide).getEntry();
  private NetworkTableEntry ShooterkMaxOutput = tab1.addPersistent("ShooterkMaxOutput", Constants.ShooterkMaxOutput).getEntry();
  private NetworkTableEntry ShooterkMinOutput = tab1.addPersistent("ShooterkMinOutput", Constants.ShooterkMinOutput).getEntry();
  private NetworkTableEntry RPMTolerance = tab1.addPersistent("RPMTolerance", Constants.RPMTolerance).getEntry();
  private NetworkTableEntry ShooterRPM = tab1.addPersistent("ShooterRPM", 0).getEntry();
  private NetworkTableEntry neededRPM = tab1.addPersistent("Vision Needed RPM", 0).getEntry();
  private NetworkTableEntry ShotAcceleration = tab1.addPersistent("Shot Acceleration", 0).getEntry();
  private NetworkTableEntry AccelerationTolerance = tab1.addPersistent("Acceleration Tolerance",Constants.accelerationTolerance).getEntry();
  private NetworkTableEntry isShooterGood = tab1.addPersistent("is Shooter Good", false).withWidget("Boolean Box").withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "red")).getEntry();
  private NetworkTableEntry isShooting = tab1.addPersistent("Shooter is Shooting", false).withWidget("Boolean Box").withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "red")).getEntry();
  
  private ShuffleboardTab tab2 = Shuffleboard.getTab("Shooter Presets");
  private NetworkTableEntry TrenchRPM = tab2.addPersistent("TrenchRPM", Constants.TrenchRPM).getEntry();
  private NetworkTableEntry TenFootRPM = tab2.addPersistent("TenFootRPM", Constants.TenFootRPM).getEntry();
  private NetworkTableEntry LayupRPM = tab2.addPersistent("LayupRPM", Constants.LayupRPM).getEntry();

  private ShuffleboardTab toggleTab = Shuffleboard.getTab("Toggle Tab");
	private NetworkTableEntry toggleDiag = toggleTab.add("Comp Mode?", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
  */

    public ShooterSubsystem(int shooter1Port, int shooter2Port) {
      shootingFlag = false;
      shooterMotor = new CANSparkMax(7, MotorType.kBrushless);
      shooterMotor2 = new CANSparkMax(8, MotorType.kBrushless);

      if (shooterMotor != null) {
        shooterMotor.setInverted(true); // Inverted because it is opposite the other motor
        shooterPID = shooterMotor.getPIDController();
        encoder = shooterMotor.getEncoder();
      }

      if (shooterMotor2 != null){
        shooterMotor2.follow(shooterMotor, true);
      }
    }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
