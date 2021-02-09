package frc.robot.util;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dist(Point point) {
        return Math.sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y));
    }

    public double getAngle(Point point) {
        return Math.cos(point.x - x / dist(point));
    }
}
