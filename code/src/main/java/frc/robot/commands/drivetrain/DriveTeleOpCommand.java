package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/** An example command that uses an example subsystem. */
public class DriveTeleOpCommand extends CommandBase {
     private final DrivetrainSubsystem drivetrain;
     private final Joystick gamepad1;

     /**
      * Creates a new DriveTeleOpCommand. This allows us to drive during tele-op
      *
      * @param drivetrain The drivetrain subsystem used by this command.
      * @param gamepad1 the 1st drive gamepad so we can access stick values
      */
     public DriveTeleOpCommand(DrivetrainSubsystem drivetrain, Joystick gamepad1) {
          this.drivetrain = drivetrain;
          this.gamepad1 = gamepad1;

          addRequirements(drivetrain);
     }

     @Override
     public void execute() {
          //TODO change these values cause I'm sure these are incorrect 
          drivetrain.driveTeleOp(gamepad1.getRawAxis(1), gamepad1.getRawAxis(2), gamepad1.getRawButtonPressed(4));
     }

     // I don't want this night to end
     // it's closing time so leave with me again
     @Override
     public boolean isFinished() {
          return false;
     }
}
