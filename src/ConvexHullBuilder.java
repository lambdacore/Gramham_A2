import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        Collections.sort(points, new PolarAngleComparator(lowest));

        // Build the convex hull using a stack
        Stack<Point2D> stack = new Stack<>();
        stack.push(points.get(0)); // Push the lowest point
        stack.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point2D top = stack.pop();
            while (!stack.isEmpty() && ccw(stack.peek(), top, points.get(i)) <= 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(points.get(i));
        }

        return stack;
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

    private static class PolarAngleComparator implements Comparator<Point2D> {
        private Point2D lowest;

        public PolarAngleComparator(Point2D lowest) {
            this.lowest = lowest;
        }

        public int compare(Point2D a, Point2D b) {
            if (a == lowest) return -1;
            if (b == lowest) return 1;
            double angleA = Math.atan2(a.y() - lowest.y(), a.x() - lowest.x());
            double angleB = Math.atan2(b.y() - lowest.y(), b.x() - lowest.x());
            if (angleA < angleB) return -1;
            if (angleA > angleB) return 1;
            double distanceA = lowest.distanceTo(a);
            double distanceB = lowest.distanceTo(b);
            if (distanceA < distanceB) return -1;
            if (distanceA > distanceB) return 1;
            return 0;
        }
    }
}
