package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LimelightSubsystem extends SubsystemBase {
    public boolean isZoomed = false;
    public boolean isAligned = false; 
    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    // Shuffleboard this may or may not work i have no idea how shuffleboard works
    public ShuffleboardTab VisionTab = Shuffleboard.getTab("Vision");
    public NetworkTableEntry showXOffset = VisionTab.addPersistent("X Offset", 0).getEntry();
    public NetworkTableEntry showDist = VisionTab.addPersistent("Target Distance", 0).getEntry();
    
    // Should go in constants but is here for now
    public double[] visionThreshold = {0, 5};

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
    
    public void getXOffset() {
      ledsOn();

      // Don't know why its bad please tell me
      if (table.getEntry("tv").getDouble(0) == 1) {
        final double xOffset = table.getEntry("tx").getDouble(0); 
        if (xOffset < visionThreshold) {
          aligned = true; 
        }
      }
    }
    
    // equation for distance is: d = (h2-h1) / tan(a1+a2)
    // h = height, h2 is height of goal minus h1 height of the limelight (70.25)
    // a = angle of the limelight on robot (10), a2 is the angle offset just use "ty" to find it
    private double getDist() {
      ledsOn();
      unZoom();

      return 70.25 / Math.tan(10 + table.getEntry("ty").getDouble(-1));
    }
    
    // find the RPM needed for different distances and then use them to find a function that works
    public double getRPM() {
      final double d = this.getDist();
      
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
