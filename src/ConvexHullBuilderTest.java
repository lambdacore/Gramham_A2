import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullBuilderTest {

    @Test
    public void testConvexHull() {
        // Create an ArrayList of points for testing
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(2000, 3000),
                new Point2D(3000, 3000),
                new Point2D(3000, 4000),
                new Point2D(1000, 3000)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        // Convert the expected result to an ArrayList for easier comparison
        ArrayList<Point2D> expectedHull = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(1000, 3000),
                new Point2D(3000, 3000),
                new Point2D(3000, 4000)
        ));

        // Sort the expected hull points lexicographically for consistent comparison
        Collections.sort(expectedHull);

        // Compare the expected hull and the actual hull
        assertEquals(expectedHull, toArrayList(hull));
    }

    // Helper method to convert an Iterable<Point2D> to an ArrayList<Point2D>
    private ArrayList<Point2D> toArrayList(Iterable<Point2D> iterable) {
        ArrayList<Point2D> list = new ArrayList<>();
        for (Point2D point : iterable) {
            list.add(point);
        }
        return list;
    }
}
