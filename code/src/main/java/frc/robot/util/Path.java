package frc.robot.util;

import java.util.ArrayList;

public class Path {
    private ArrayList<Point> prePath;
    private ArrayList<Point> path;
    private int lastPoint = 0;

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

            for (double hypot = 0.0; hypot < point.dist(nextPoint); hypot += 0.01) {
                path.add(new Point(Math.sin(angle) * hypot, Math.cos(angle) * hypot));
            }
        }
    }

    public ArrayList<Point> getPath() {
        return path;
    }
}
