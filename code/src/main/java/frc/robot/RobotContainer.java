// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.DriveTeleOpCommand;
import frc.robot.autonomous.AutoNavAuto;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // joysticks
    private Joystick gamepad1 = new Joystick(0);
    private Joystick gamepad2 = new Joystick(1);

    // buttons
    private JoystickButton aButton = new JoystickButton(gamepad1, 1);
    private JoystickButton bButton = new JoystickButton(gamepad1, 2);
    private JoystickButton xButton = new JoystickButton(gamepad1, 3);
    private JoystickButton yButton = new JoystickButton(gamepad1, 4);
    private JoystickButton leftBumper = new JoystickButton(gamepad1, 5);
    private JoystickButton rightBumper = new JoystickButton(gamepad1, 6);

    private JoystickButton aButton2 = new JoystickButton(gamepad2, 1);
    private JoystickButton bButton2 = new JoystickButton(gamepad2, 2);
    private JoystickButton xButton2 = new JoystickButton(gamepad2, 3);
    private JoystickButton yButton2 = new JoystickButton(gamepad2, 4);
    private JoystickButton leftBumper2 = new JoystickButton(gamepad2, 5);
    private JoystickButton rightBumper2 = new JoystickButton(gamepad2, 6);

    // subsystems
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();

    // commands
    private final DriveTeleOpCommand driveTele = new DriveTeleOpCommand(drivetrain, gamepad2);

    // autos
    private final AutoNavAuto barrelAuto = new AutoNavAuto(drivetrain);
    private final AutoNavAuto slalomAuto = new AutoNavAuto(drivetrain);
    private final AutoNavAuto bounceAuto = new AutoNavAuto(drivetrain);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        aButton.whenPressed(driveTele);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return auto;
    }
}
