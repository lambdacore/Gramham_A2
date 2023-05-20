//// Jonathan Harrington
//// May - 19 - 2023

// alg4
import edu.princeton.cs.algs4.Point2D;

// regular imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class ConvexHullBuilder {

    // varialbe to reference thr-out the program.
    private ArrayList<Point2D> points;

    public ConvexHullBuilder(ArrayList<Point2D> points) {
        this.points = points;
    }

    // Task 1 create a stack or "iterable" that has the points of the convex hull
    // Other notes here
    public Iterable<Point2D> hull() {
        if (points.size() < 3) {
            return points; // Return all points if there are > 3
        }

        // Task 1 find the lowest y value
        Point2D lowest = findLowestPoint();

        // Task 2 sort by polar angles referencing the lowest y value
        // It would be best to simply use a lambda function
        // doubles are needed since the calculation could have double results.
        Collections.sort(points, (a, b) -> {
            double angleA = Math.atan2(a.y() - lowest.y(), a.x() - lowest.x());
            double angleB = Math.atan2(b.y() - lowest.y(), b.x() - lowest.x());
            if (angleA < angleB) return -1;
            if (angleA > angleB) return 1;
            double distanceA = lowest.distanceTo(a);
            double distanceB = lowest.distanceTo(b);
            return Double.compare(distanceA, distanceB);
        });

        // Task 3 build the convex hull using a stack
        // You will need to be sure to add the lowest point first.
        Stack<Point2D> stack = new Stack<>();
        stack.push(points.get(0)); // lowest point from task 1
        stack.push(points.get(1)); // Usually we like to add the other point for referencing

        // Task 5 call the ccw method to find the correct points and then pop points that are
        // no longer part of the convex hull
        for (int i = 2; i < points.size(); i++) {
            Point2D top = stack.pop();
            while (!stack.isEmpty() && ccw(stack.peek(), top, points.get(i)) <= 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(points.get(i));
        }

        // return the "stack" to the GUI
        return stack;
    }
    // Helper method for task 1 to find the lowest point in the y direction
    private Point2D findLowestPoint() {
        Point2D lowest = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point2D current = points.get(i);
            // lowest should be smaller y value.
            // however if we get the same y we should then compare the x values
            if (current.y() < lowest.y() || (current.y() == lowest.y() && current.x() < lowest.x())) {
                lowest = current;
            }
        }
        return lowest;
    }
    // Helper method for task 5
    // Mostly from the video except we have to use Point2D
    // Just like the video if we have area we can figure out which way it turns.
    private int ccw(Point2D a, Point2D b, Point2D c) {
        double area = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
        if (area < 0) {
            return -1; // clockwise
        } else if (area > 0) {
            return 1; // counterclockwise
        } else {
            return 0; // collinear
        }
    }
}