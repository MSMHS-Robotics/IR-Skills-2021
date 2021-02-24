package frc.robot.autonomous;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class GalacticSearchAuto extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private ArrayList<Double[]> path = new ArrayList<>();

    public GalacticSearchAuto() {
    }    
}
