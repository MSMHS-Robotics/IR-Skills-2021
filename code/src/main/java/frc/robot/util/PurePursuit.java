package frc.robot.util;

import java.util.List;

/**
 * A class to hold a Pure-Pursuit Controller.
 * What it does is look for the farthest point along the path that is <= the lookahead distance,
 * then gives the heading needed to go to that point. This has the affect of outputting smooth curves
 * where we pass it harsh corners. It doesn't do any velocity control though, so that will have to be PID'd
 */
public class PurePursuit {
    private List<Double[]> prePath;
    private List<Double[]> path;
    private double lookaheadDistance;
    private double speed;

    /**
     * Creates a new Pure-Pursuit Controller
     * @param path a list of (x, y) points that represent the path to follow. Pretty sure these need to be absolute and not relative
     * @param lookaheadDistance how far along the path we should look for the point we want to head to. If this is too small you get small curves.
     * too large and you might miss smaller turns
     * @param speed this is needed to fill in the points along the path and stuff. //TODO rewrite the previous sentence
     */
    public PurePursuit(List<Double[]> prePath, double lookaheadDistance, double speed) {
        this.prePath = prePath;
        this.lookaheadDistance = lookaheadDistance;
        this.speed = speed;

        fillPoints();
    }

    /**
     * An implementation of the distance formula (using Pythagorean Theorem and stuff)
     * @param x1 the x of the first point
     * @param y1 the y of the first point
     * @param x2 the x of the second point
     * @param y2 the y of the second point
     * @return the straight-line distance between the two given points
     */
    private double dist(double x1, double y1, double x2, double y2) {
        // sqrt((x1-x2)^2 + (y1-y2)^2) but because you can't ** in Java, I just don't mess with Math.pow()
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Fills in the path given with intermediary points along the lines so curves are right
     * NOT SURE YET HOW TO DO THIS //TODO DO THIS
     */
    public void fillPoints() {
        // this should take all of the points and fill in points on the lines in between them every 200 ms running at the given speed
        for (Double[] point : prePath) {
            path.add(point);

            Double[] nextPoint = path.get(path.lastIndexOf(point) + 1);
            for (double i = 0.0; i < dist(point[0], point[1], nextPoint[0], nextPoint[1]); i += 0.01) {
                Double[] tempPoint = {0.0, 0.0};
                path.add(tempPoint);
            }
        }
    }

    /**
     * Returns the next heading based off of the current position
     * @param x the x value of where you currently are
     * @param y the y value of where you currently are
     * @return the heading you should PID to next
     */
    public double getHeading(double x, double y) {
        // for every point in the list
        for (Double[] point : path) {
            // if the distance between here and the current point in the loop is farther than the lookahead distance
            if (dist(x, y, point[0], point[1]) > lookaheadDistance) {
                // then we have gone too far and need to go back one, this is our poit
                Double[] tempPoint = path.get(path.lastIndexOf(point) - 1);
                
                // some trig
                // we need the angle between adjacent and hypot, so cos
                // gives the angle between the X-axis and a line between the current position and the next point we need to reach (aka the point in the loop)
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            } else {
                // else we're at the end of the line, so get the last point
                // so we don't start going backwards or something
                Double[] tempPoint = path.get(path.size() - 1);
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            }
        }

        // we shouldn't have gotten here, but if I don't put this here VSCode complains
        return 0.0;
    }
}
