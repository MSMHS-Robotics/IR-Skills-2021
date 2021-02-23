package frc.robot.subsystems;

// Motor Stuff
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private WPI_TalonSRX positionMotor1;
    private WPI_TalonSRX positionMotor2;
    private WPI_TalonSRX intakeMotor;
    private CANSparkMax indexMotor1;
    private CANSparkMax indexMotor2;
    private CANSparkMax triggerMotor;
    
  public IntakeSubsystem() {
    /**
    Put all the PID stuff I don't understand here
     
     **/

    public void extendIntake() {

    }

    public void retractIntake() {

    }
    
    public void runIntake() {

    }


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}