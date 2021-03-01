package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PassThroughSubsystem extends SubsystemBase {
    private CANSparkMAX leftMotor;
    private CANSparkMAX rightMotor;

    public PassThroughSubsystem() {
        leftMotor = new CANSparkMAX(Constants.PassThrough.leftMotor_p, MotorType.kBrushless);
        rightMotor = new CANSparkMAX(Constants.PassThrough.rightMotor_p, MotorType.kBrushless);

        leftMotor.setInverted(true);
    }

    public void setPower(double power) {
        leftMotor.set(power);
        rightMotor.set(power);
    }

    @Override
    public void periodic() {
        setPower(0.2);
    }
}
