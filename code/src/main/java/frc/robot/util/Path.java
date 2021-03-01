package frc.robot.util;

import java.util.ArrayList;
import java.util.Iterator;

public class Path implements Iterable<Point> {
    private ArrayList<Point> prePath;
    private ArrayList<Point> path;

    public Path(Point... points) {
        for (Point point : points) {
            prePath.add(point);
        }
        fillPoints();
    }

    /**
     * Fills in the path given with intermediary points along the lines so curves are right
     * The distance between intermediary points is 0.01 inches, just slightly above the smallest distance we are able to measure,
     * and that's using only the through-bores. The NEO built-ins are less accurate (42 vs 1024 CPR), although the 7:125 gearing might help
     */
    public void fillPoints() {
        path.clear();

        for (Point point : prePath) {
            path.add(point);

            Point nextPoint = path.get(path.lastIndexOf(point) + 1);
            
            // TOA to get angle between the points. Doesn't use the other tring funcs because hypot changes down below
            // and because I, the Supreme Commander of This Line of Code, say so.
            double angle = Math.atan(point.y - nextPoint.y / point.x - nextPoint.y);

            for (double hypot = 0.01; hypot < point.dist(nextPoint); hypot += 0.01) {
                path.add(new Point(Math.sin(angle) * hypot, Math.cos(angle) * hypot));
            }
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return path.iterator();
    }

    public int getLength() {
        return path.size();
    }

    public int lastIndexOf(Point point) {
        return path.lastIndexOf(point);
    }

    public Point get(int index) {
        return path.get(index);
    }
}
