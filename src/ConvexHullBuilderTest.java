import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullBuilderTest {

    @Test
    public void testConvexHull_SinglePointInside() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(3000, 3000),
                new Point2D(3000, 4000),
                new Point2D(1000, 3000),
                new Point2D(1500, 2500) // Point inside the convex hull
        ));
        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();
        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(3000, 3000),
                new Point2D(3000, 4000),
                new Point2D(1000, 3000)
        );
        assertEquals(expectedHull, toList(hull));
    }
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

        // Convert the expected result to a List for easier comparison
        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(3000, 3000),
                new Point2D(3000, 4000),
                new Point2D(1000, 3000)
        );

        // Compare the expected hull and the actual hull
        assertEquals(expectedHull, toList(hull));
    }
    @Test
    public void testConvexHull_Square() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(2000, 3000),
                new Point2D(1000, 3000)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(2000, 3000),
                new Point2D(1000, 3000)
        );

        assertEquals(expectedHull, toList(hull));
    }

    @Test
    public void testConvexHull_CollinearPoints() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(3000, 2000),
                new Point2D(4000, 2000)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(4000, 2000)
        );

        assertEquals(expectedHull, toList(hull));
    }

    @Test
    public void testConvexHull_ThreePoints() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 1000),
                new Point2D(1500, 3000)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(1500, 3000)
        );

        assertEquals(expectedHull, toList(hull));
    }

    @Test
    public void testConvexHullWithCollinearPoints() {
        // Create an ArrayList of collinear points for testing
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(2000, 2000),
                new Point2D(3000, 2000),
                new Point2D(4000, 2000),
                new Point2D(5000, 2000),
                new Point2D(6000, 2000)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        // The convex hull of collinear points should be the endpoints only
        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(6000, 2000)
        );

        // Compare the expected hull and the actual hull
        assertEquals(expectedHull, toList(hull));
    }

    // Add more test cases as needed

    // Helper method to convert an Iterable<Point2D> to a List<Point2D>
    private List<Point2D> toList(Iterable<Point2D> iterable) {
        List<Point2D> list = new ArrayList<>();
        for (Point2D point : iterable) {
            list.add(point);
        }
        return list;
    }
}
