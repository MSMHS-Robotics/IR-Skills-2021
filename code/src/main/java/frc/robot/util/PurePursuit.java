package frc.robot.util;

import java.util.ArrayList;

/**
 * A class to hold a Pure-Pursuit Controller.
 * What it does is look for the farthest point along the path that is <= the lookahead distance,
 * then gives the heading needed to go to that point. This has the affect of outputting smooth curves
 * where we pass it harsh corners. It doesn't do any velocity control though, so that will have to be PID'd
 */
public class PurePursuit {
    private Path path;
    private double lookaheadDistance;

    /**
     * Creates a new Pure-Pursuit Controller
     * @param path a list of (x, y) points that represent the path to follow. Pretty sure these need to be absolute and not relative
     * @param lookaheadDistance how far along the path we should look for the point we want to head to. If this is too small you get small curves.
     * too large and you might miss smaller turns
     */
    public PurePursuit(Path path, double lookaheadDistance) {
        this.path = path;
        this.lookaheadDistance = lookaheadDistance;
    }

    /**
     * Returns the next heading based off of the current position
     * @return the heading you should PID to next
     */
    public double getHeading(Point currentPoint, double currentHeading) {
        // for every point in the list
        for (Point point : path.getPath()) {
            // if the distance between here and the current point in the loop is farther than the lookahead distance
            if (currentPoint.dist(point) > lookaheadDistance) {
                // then we have gone too far and need to go back one, this is our point
                Point returnPoint = path.get(path.lastIndexOf(point) - 1);
                
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            } else {
                return Math.cos(tempPoint[0] - x / dist(x, y, tempPoint[0], tempPoint[1]));
            }
        }
        return 0.0;
    }
}