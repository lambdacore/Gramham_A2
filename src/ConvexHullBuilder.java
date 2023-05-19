import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class ConvexHullBuilder {
    private ArrayList<Point2D> points;

    public ConvexHullBuilder(ArrayList<Point2D> points) {
        this.points = points;
    }

    public Iterable<Point2D> hull() {
        if (points.size() < 3) {
            return points; // Return all points if there are fewer than 3
        }

        // Find the lowest point
        Point2D lowest = findLowestPoint();

        // Sort the points by polar angle with respect to the lowest point
        Collections.sort(points, lowest.polarOrder());

        // Build the convex hull using Graham's scan algorithm
        Stack<Point2D> hullStack = new Stack<>();
        hullStack.push(points.get(0));

        // Find the outermost points in clockwise and counterclockwise directions
        ArrayList<Point2D> outermostClockwise = new ArrayList<>();
        ArrayList<Point2D> outermostCounterclockwise = new ArrayList<>();

        for (int i = 1; i < points.size(); i++) {
            Point2D currentPoint = points.get(i);

            while (hullStack.size() >= 2) {
                Point2D top = hullStack.pop();
                Point2D nextToTop = hullStack.peek();

                if (ccw(nextToTop, top, currentPoint) > 0) {
                    hullStack.push(top);
                    break;
                }
            }

            hullStack.push(currentPoint);
        }

        // Add the points in the clockwise direction to outermostClockwise list
        while (!hullStack.isEmpty()) {
            outermostClockwise.add(hullStack.pop());
        }

        // Reverse the order of points to scan counterclockwise
        Collections.reverse(points);

        // Clear the hull stack
        hullStack.clear();
        hullStack.push(points.get(0));

        for (int i = 1; i < points.size(); i++) {
            Point2D currentPoint = points.get(i);

            while (hullStack.size() >= 2) {
                Point2D top = hullStack.pop();
                Point2D nextToTop = hullStack.peek();

                if (ccw(nextToTop, top, currentPoint) > 0) {
                    hullStack.push(top);
                    break;
                }
            }

            hullStack.push(currentPoint);
        }

        // Add the points in the counterclockwise direction to outermostCounterclockwise list
        while (!hullStack.isEmpty()) {
            outermostCounterclockwise.add(hullStack.pop());
        }

        // Remove the common point (lowest) from the counterclockwise list
        outermostCounterclockwise.remove(0);

        // Concatenate the two lists to form the convex hull
        outermostClockwise.addAll(outermostCounterclockwise);

        return outermostClockwise;
    }

    private Point2D findLowestPoint() {
        Point2D lowest = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point2D current = points.get(i);
            if (current.y() < lowest.y() || (current.y() == lowest.y() && current.x() < lowest.x())) {
                lowest = current;
            }
        }
        return lowest;
    }

    private int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
        if (area2 < 0) {
            return -1; // clockwise
        } else if (area2 > 0) {
            return 1; // counterclockwise
        } else {
            return 0; // collinear
        }
    }
}
