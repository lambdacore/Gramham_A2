import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
                new Point2D(1000, 3000),
                new Point2D(3000, 4000),
                new Point2D(3000, 3000),
                new Point2D(2000, 3000)
        ));

        // Compare the expected hull and the actual hull
        Iterator<Point2D> expectedIterator = expectedHull.iterator();
        Iterator<Point2D> actualIterator = hull.iterator();
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            Point2D expected = expectedIterator.next();
            Point2D actual = actualIterator.next();
            assertEquals(expected.x(), actual.x(), 0.0001);
            assertEquals(expected.y(), actual.y(), 0.0001);
        }
        // Ensure both lists have been fully traversed
        assertFalse(expectedIterator.hasNext());
        assertFalse(actualIterator.hasNext());
    }

}
