package frc.robot.autonomous;

import frc.robot.util.Path;
import frc.robot.util.Point;
import frc.robot.util.PurePursuit;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

//TODO doc this
public class AutoNavAuto extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private Path finalPath;
    private PurePursuit controller;
    private boolean endReached = false;

    public AutoNavAuto(DrivetrainSubsystem drivetrain, Point... points) {
        finalPath = new Path(points);
        //TODO tune lookeahead distance, or make it part of constructor args
        controller = new PurePursuit(finalPath, 12);
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetAll();
    }

    @Override
    public void execute() {
        //TODO tune the speed param (speed is kinda misleading, it's just percentOutput)
        drivetrain.driveAuto(1, controller.getHeading(drivetrain.getPoint(), drivetrain.getHeading()));
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    @Override
    public boolean isFinished() {
        return controller.endReached();
    }
}