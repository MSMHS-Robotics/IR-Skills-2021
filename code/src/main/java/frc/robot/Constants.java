package frc.robot;

/**
 * A class to hold all of our constant values
 */
public final class Constants {
    public final class Drivetrain { // you can have classes inside other classes
        public static final double NEO_TPR = 42; // a REV NEO gets ~42 (I think) ticks per revolution
        public static final double BORE_TPR = 2048;

        public static final int left1_p = 1;
        public static final int left2_p = 2;
        public static final int left3_p = 3;
        public static final int right1_p = 4;
        public static final int right2_p = 5;
        public static final int right3_p = 6;

		public static final int leftEncoder1_p = 2;
		public static final int leftEncoder2_p = 3;
		public static final int rightEncoder1_p = 4;
        public static final int rightEncoder2_p = 5;
        
        public static final double dKp = 0.1;
        public static final double dKi = 0.001;
        public static final double dKd = 0.001;

        public static final double hKp = 0.1;
        public static final double hKi = 0.001;
        public static final double hKd = 0.001;

        public static final double tKp = 0.1;
        public static final double tKi = 0.001;
        public static final double tKd = 0.001;
    }
}
