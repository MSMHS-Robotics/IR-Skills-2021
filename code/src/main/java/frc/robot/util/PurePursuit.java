package frc.robot.util;

/**
 * A class to hold a Pure-Pursuit Controller.
 * What it does is look for the farthest point along the path that is <= the lookahead distance,
 * then gives the heading needed to go to that point. This has the affect of outputting smooth curves
 * where we pass it harsh corners. It doesn't do any velocity control though, so that will have to be PID'd
 */
public class PurePursuit {
    private Path path;
    private double lookaheadDistance;
    private boolean atEnd = false;

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
        Point lastPoint = path.get(path.getLength() - 1);

        //TODO tune value, 0.5 inches seems like quite a bit
        if (currentPoint.dist(lastPoint) < 0.5) {
            atEnd = true;
            return currentHeading;
        }

        // for every point in the list
        for (Point point : path) {
            // if the distance between here and the current point in the loop is farther than the lookahead distance
            if (currentPoint.dist(point) > lookaheadDistance) {
                // then we have gone too far and need to go back one, this is our point
                Point returnPoint = path.get(path.lastIndexOf(point) - 1);
                
                return Math.cos(returnPoint.x - currentPoint.x / currentPoint.dist(returnPoint));
            
            // else if the distance between the current point and the last one is small (ie we've reached the end)
            } else if (currentPoint.dist(lastPoint) < lookaheadDistance) {
                return Math.cos(lastPoint.x - currentPoint.x / currentPoint.dist(lastPoint));
            }
        }

        // otherwise just stay where we're at
        return currentHeading;
    }

    //TODO doc this
    public boolean endReached() {
        return atEnd;
    }
}