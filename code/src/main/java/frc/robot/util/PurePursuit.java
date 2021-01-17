package frc.robot.util;

import java.util.List;

public class PurePursuit {
    private List<Double[]> path;
    private double lookaheadDistance;

    public PurePursuit(List<Double[]> points, double lookaheadDistance) {
        path = points;
        this.lookaheadDistance = lookaheadDistance;
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public double getHeading(double x, double y) {
        for (Double[] point : path) {
            if (dist(x, y, point[0], point[1]) > lookaheadDistance) {
                //the point we need is
                Double[] tempPoint = path.get(path.lastIndexOf(point) - 1);
                
                // some trig
                // we need the angle between adjacent and hypot, so cos
                return Math.cos(point[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));

                // need to add something to detect if we've reached the end of the line
            } else {
                continue;
            }
        }

        // don't know how we got here but I get yelled at if we don't have a return outside the if
        return 0.0;
    }
}
