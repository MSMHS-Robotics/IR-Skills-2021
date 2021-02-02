package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TurnAutoCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DrivetrainSubsystem drivetrain;
    private double angle = 0;
    private boolean reachedGoal = false;

    /**
     * Creates a new DriveAUtoCommand. Use this to drive in autons.
     *
     * @param drivetrain The drivetrain subsystem used by this command.
     * @param angle      the angle you want to drive at (0 degrees is the start
     *                   angle of the robot)
     */
    public TurnAutoCommand(DrivetrainSubsystem drivetrain, double angle) {
        this.drivetrain = drivetrain;
        this.angle = angle;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetAllPID();
        drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        reachedGoal = drivetrain.turnToAngle(angle);
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
