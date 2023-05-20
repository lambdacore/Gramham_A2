//// Jonathan Harrington
//// May - 19 - 2023

// alg4
import edu.princeton.cs.algs4.Point2D;
// regular imports
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvexHullBuilderTest {

    // Simple test to make sure points inside are not in the convexhull
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

    // Relatively random points. make sure we get the right convex hull
    // One point is inside
    @Test
    public void testConvexHull_SinglePointInsidd_2() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 2000), // 1
                new Point2D(2000, 2000), // 2
                new Point2D(2000, 3000), // 3
                new Point2D(3000, 3000), // 4
                new Point2D(3000, 4000), // 5
                new Point2D(1000, 3000) // 6
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
    // reversed of previous test but points added in reverse order. expected should be the same
    @Test
    public void testConvexHullReverse_SinglePointInside_2() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(

                new Point2D(1000, 3000), // 6
                new Point2D(3000, 4000), // 5
                new Point2D(3000, 3000), // 4
                new Point2D(2000, 3000), // 3
                new Point2D(2000, 2000), // 2
                new Point2D(1000, 2000) // 1
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

    // Checks we can make a simple square
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

    // Simple test to make sure a line only as the two end points
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

    // Points inside should not be in the convex hull
    @Test
    public void testConvexHull_PointsInside() {
        ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(
                new Point2D(1000, 1000),
                new Point2D(1001, 1020),
                new Point2D(1005, 1005),
                new Point2D(1006, 1006),
                new Point2D(1010, 1010),
                new Point2D(1010, 1030),
                new Point2D(1009, 1010),
                new Point2D(1020, 1001),
                new Point2D(1020, 1020)
        ));

        ConvexHullBuilder builder = new ConvexHullBuilder(points);
        Iterable<Point2D> hull = builder.hull();

        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 1000),
                new Point2D(1020, 1001),
                new Point2D(1020, 1020),
                new Point2D(1010, 1030),
                new Point2D(1001, 1020)
        );

        assertEquals(expectedHull, toList(hull));
    }

    // This checks that if the points are in a line only the two endpoints are in
    // the convex hull
    @Test
    public void testConvexHullWithCollinearPoints() {
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

        List<Point2D> expectedHull = Arrays.asList(
                new Point2D(1000, 2000),
                new Point2D(6000, 2000)
        );

        assertEquals(expectedHull, toList(hull));
    }

    // Helper method to convert an Iterable to a List
    private List<Point2D> toList(Iterable<Point2D> iterable) {
        List<Point2D> list = new ArrayList<>();
        for (Point2D point : iterable) {
            list.add(point);
        }
        return list;
    }
}
