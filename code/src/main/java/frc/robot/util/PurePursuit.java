package frc.robot.util;

import java.util.List;

public class PurePursuit {
    private List<Double[]> path;
    private List<Double[]> finalPath;
    private double lookaheadDistance;

    public PurePursuit(List<Double[]> points, double lookaheadDistance) {
        path = points;
        this.lookaheadDistance = lookaheadDistance;
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void fillPoints(double minSpeed) {
        // this should take all of the points and fill in points on the lines in between them every 200 ms running at the given speed
        for (Double[] point : path) {
            finalPath.add(point);

            Double[] nextPoint = path.get(path.lastIndexOf(point) + 1);
            for (int i = 0; i < dist(point[0], point[1], nextPoint[0], nextPoint[1]) / 200 /** ??? */; i++) {
                // yeah I don't know what to do here goodbye
            }
        }
    }

    public double getHeading(double x, double y) {
        for (Double[] point : path) {
            if (dist(x, y, point[0], point[1]) > lookaheadDistance) {
                //the point we need is
                Double[] tempPoint = path.get(path.lastIndexOf(point) - 1);
                
                // some trig
                // we need the angle between adjacent and hypot, so cos
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            } else {
                // else we're at the end of the line, so get the last point
                Double[] tempPoint = path.get(path.size() - 1);
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            }
        }

        // we shouldn't have gotten here
        return 0.0;
    }
}
