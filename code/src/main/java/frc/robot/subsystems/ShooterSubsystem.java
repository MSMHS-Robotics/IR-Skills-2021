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
  // Below this are variables for various things like motors, encoders, and things
  // to do with the shooter, i think
  // Motors
  private CANSparkMax shooterMotor;
  private CANSparkMax shooterMotor2;

  // Control
  private PIDController shooterPID;
  private CANEncoder encoder;

  private double RPMSetpoint;
  private Boolean shootingFlag = false; 

  /*private ShuffleboardTab tab1 = Shuffleboard.getTab("Shooter");
  // 
  pr
   * ivate NetworkTableEntry ShooterkP = tab1.addPersistent("Shoote private
   * NetworkTableEntry kPdivide = tab1.addPersistent("kPdivide", C
   * nstants.ShooterkPdivide).getEntry(); private NetworkTableEntry ShooterkI 
   *  tab1.addPersistent("ShooterkI", Constants.ShooterkI).getEntry(); private
   * NetworkTableEntry kIdivide = tab1.addPersistent("kIdivide", C
   * nstants.ShooterkIdivide).getEntr private NetworkTableEntry ShooterkD 
   *  tab1.addPersistent("ShooterkD", Constants.ShooterkD).getEntry(); private
   * NetworkTableEntry kDdivide = tab1.addPersistent("kDdivide", C
   * nstants.ShooterkDdivide).getEntr private NetworkTableEntry ShooterkIz
   * = tab1.addPersistent("ShooterkIz", Constants.ShooterkIz).getEntry(); private
   * NetworkTableEntry ShooterkFF = tab1.addPersistent("ShooterkFF",
   * Constants.ShooterkFF).getEntry(); private NetworkTableEntry kFFdivide = 
   * ab1.addPersistent("kFFdivide", Constants.ShooterkFFdivide).getEntr private
   * NetworkTableEntry ShooterkMaxOutput = tab1.addPersistent("Sho
   * terkMaxOutput", Constants.ShooterkMaxOu private NetworkTableEntry
   * ShooterkMinOutput = tab1.addPersistent("ShooterkMinOutput",
   * Constants.ShooterkMinOutput).getEntry(); private NetworkTableEntry
   * RPMTolerance = tab1.addPersistent("RPMTolerance", Constants
   * RPMTolerance).getEntry(); private NetworkTableEntry
   * ShooterRPM = tab1.addPersistent("ShooterRPM", 0).
   * etEntry(); private NetworkTableEntry neededRPM = 
   * ab1.addPersistent("Vision Needed RPM", 0).getEn private NetworkTableEntry
   * ShotAcceleration = tab1.addPersistent("Shot Acceleration", 0).getE private
   * NetworkTableEntry AccelerationTolerance = tab1.addPersistent("Acceleration T
   * lerance",Const private NetworkTableEntry isShooterGood = tab1.ad
   * Persistent("is Shooter Good", false).withWidget("Boolean Box").withProperties
   * (Map.of("co private NetworkTableEntry isShooting = ta
   * 1.addPersistent("Shooter is Shooting"
   *  false).withWidget("Boolean Box").withProperties(Map.of("colorWhenTrue"
   *  "green", "colorWhenFalse", "red")).getEntry() 
   * 
   * 
   * 
   * 
   * private NetworkTableEntry TrenchRPM = tab2.addPersistent("TrenchRPM", 
   * private NetworkTableEntry TenFootRPM = tab2.addPersistent("TenFootRPM
   * , Constants.TenFootRPM).getEntry private NetworkTableEntry LayupRPM = t
   * b2.addPersistent("LayupRPM", Constants.LayupRPM).getEntry(); 
   * 
   * 
   * 
   * rivate NetworkTableEntry toggleDiag = toggleTab.add("Comp Mode?", fals
   *  */
   
    shooterMotor = new CANSparkMax(7, MotorType.kBrushless)
    shooterMotor2 = new CANSparkMax(8, MotorType.kBrushless)
    
    encoder = shooterMotor.getEncoder();
    
  public void periodic() {
    

  
  @Override
  public void simulationPeriodic() {
    

  
