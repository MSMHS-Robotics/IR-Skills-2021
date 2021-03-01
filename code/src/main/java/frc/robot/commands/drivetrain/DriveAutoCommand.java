package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/** An example command that uses an example subsystem. */
public class DriveAutoCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private double distance = 0;
    private double angle = 1000;
    private boolean reachedGoal = false;

    /**
     * Creates a new DriveAUtoCommand. Use this to drive in autons.
     *
     * @param drivetrain The drivetrain subsystem used by this command.
     * @param distance   the distance, in inches, you want to drive
     * @param angle      the angle you want to drive at (0 degrees is the start
     *                   angle of the robot)
     */
    public DriveAutoCommand(DrivetrainSubsystem drivetrain, double distance, double angle) {
        this.drivetrain = drivetrain;
        this.distance = distance;
        this.angle = angle;

        addRequirements(drivetrain);
    }

    /**
     * Creates a new DriveAUtoCommand. Use this to drive in autons. This command
     * will make the robot keep its heading.
     *
     * @param drivetrain The drivetrain subsystem used by this command.
     * @param distance   the distance, in inches, you want to drive
     */
    public DriveAutoCommand(DrivetrainSubsystem drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetAllPID();
        drivetrain.resetEncoders();

        // if the angle wasn't specified, then keep the current one
        if (angle == 1000) {
            angle = drivetrain.getHeading();
        }
    }

    @Override
    public void execute() {
        reachedGoal = drivetrain.driveDistance(distance, angle);
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
