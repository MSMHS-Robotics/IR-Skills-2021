package frc.robot.util;

public  class RocketMath {
    public static double clamp(double val, double min, double max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        }     
        return val;
    }
}
