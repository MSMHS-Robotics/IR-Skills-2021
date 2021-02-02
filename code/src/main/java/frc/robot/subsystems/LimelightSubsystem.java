package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {
    public boolean isZoomed = false;
    public boolean isAligned = false; 
    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    // Turns the LED's off and on
    private void ledsOff() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
    }

    private void ledsOn() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }
    
    // Zooms and unzooms
    private void zoom() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        isZoomed = true; 
    }

    private void unZoom() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        isZoomed = false;
    }

    // Tells whether we are zoomed/aligned 
    private boolean checkAlign() {
      return isAligned; 
    }

    private boolean checkZoom() {
        return isZoomed;
    }

    public void toggleAlign() {
        
    }
  public LimelightSubsystem() {

      
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
