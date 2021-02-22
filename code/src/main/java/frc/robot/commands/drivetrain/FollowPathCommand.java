package frc.robot.commands.drivetrain;

import frc.robot.util.Path;
import frc.robot.util.Point;
import frc.robot.util.PurePursuit;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class FollowPathCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private PurePursuit controller;
    private boolean reachedGoal = false;

    public FollowPathCommand(DrivetrainSubsystem drivetrain, Path path, double lookaheadDistance) {
        this.drivetrain = drivetrain;
        controller = new PurePursuit(path, lookaheadDistance);
        
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetAllPID();
        drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        //TODO
        reachedGoal = false;
    }

    @Override
    public void end(boolean interrupted) {
        // stop ourselves.
        drivetrain.driveTeleOp(0, 0, false);
    }

    @Override
    public boolean isFinished() {
        return reachedGoal;
    }
}