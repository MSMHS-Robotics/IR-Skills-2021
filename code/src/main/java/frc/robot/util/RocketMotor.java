package frc.robot.util;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public class RocketMotor implements SpeedController {
    public static enum RocketMotorType {
        NONE,
        TALON,
        NEO,
        NEO550
    }
    
    //TODO organize this mess
    private RocketMotorType motorType = RocketMotorType.NONE;
    private SpeedController motor;
    private boolean isReal; // NOTE: note the country located in the middle east
    private boolean isConnected = false;
    private double speed = 0;
    private int canId;
    private boolean isInverted;
    private boolean isDisabled = false;

    public RocketMotor(int canId, boolean isReal, RocketMotorType type) {
        this.canId = canId;
        this.isReal = isReal;
        if (isReal) {
            if (type == RocketMotorType.TALON) {
                motor = new TalonSRX(canId);
                Logger.getInstance().log("Initializing as Talon SRX");
            } else if (type == RocketMotorType.NEO) {
                motor = new CANSparkMax(canId, MotorType.kBrushless);
                Logger.getInstance().log("Initializing as NEO");
            } else if (type == RocketMotorType.NEO550) {
                motor = new CANSparkMax(canId, MotorType.kBrushless);
                Logger.getInstance().log("Initializing as NEO550 motor");
            } else {
                isConnected = false;
            }
        } else {
            Logger.getInstance().log("Initializing as simulated motor");
            isConnected = true;
        }
    }

    @Override
    public void pidWrite(double output) {
        Logger.getInstance().log("Who the freak called pidWrite?");
    }

    @Override
    public void set(double speed) {
        if (isReal) {
            if (isConnected) {
                motor.set(speed);
            } else {
                Logger.getInstance().log("Motor disconnected");
            }
        } else {
            if (!isDisabled) {
                this.speed = RocketMath.clamp(speed, -1, 1);
            }
        }
    }

    @Override
    public double get() {
        if (isReal) {
            return motor.get();
        } else {
            return speed;
        }
    }

    @Override
    public void setInverted(boolean isInverted) {
        if (isReal) {
            motor.setInverted(isInverted);
        } else {            
            this.isInverted = isInverted;
        }
    }

    @Override
    public boolean getInverted() {
        if (isReal) {
            return motor.getInverted();
        } else {
            return isInverted;
        }
    }

    @Override
    public void disable() {
        if (isReal) {
            motor.disable();
        } else {
            isDisabled = true;
        }
    }

    @Override
    public void stopMotor() {
        if (isReal) {
            motor.stopMotor();
        } else {
            speed = 0;
        }
    }
    
}
