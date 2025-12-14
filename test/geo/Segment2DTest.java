package org.example.test.geo;

import org.example.geo.Point2D;
import org.example.geo.Segment2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment2DTest {

    Segment2D s1;
    Segment2D s2;
    Segment2D s3;

    @BeforeEach
    void setUp() {
        s1 = new Segment2D(new Point2D(0, 0), new Point2D(1, 1));
        s2 = new Segment2D(new Point2D(0, 0), new Point2D(2, 2));
        s3 = new Segment2D(new Point2D(2, 2), new Point2D(4, 4));
    }

    @Test
    void contains() {
        assertTrue(s1.contains(new Point2D(0.5, 0.5)));
        assertTrue(s2.contains(new Point2D(1, 1)));
        assertFalse(s3.contains(new Point2D(2, 6)));
    }

    @Test
    void area() {
        assertEquals(0, s1.area());
        assertEquals(0, s2.area());
        assertEquals(0, s3.area());
    }

    @Test
    void perimeter() {
        assertEquals(Math.sqrt(2), s1.perimeter());
        assertEquals(2 * Math.sqrt(2), s2.perimeter());
        assertEquals(2 * Math.sqrt(2), s3.perimeter());
    }

    @Test
    void move() {
        s1.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), s1.getPoints()[0]);
        assertEquals(new Point2D(2, 2), s1.getPoints()[1]);
        s2.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), s2.getPoints()[0]);
        assertEquals(new Point2D(3, 3), s2.getPoints()[1]);
        s3.move(new Point2D(1, 1));
        assertEquals(new Point2D(3, 3), s3.getPoints()[0]);
        assertEquals(new Point2D(5, 5), s3.getPoints()[1]);
    }

    @Test
    void copy() {
        Segment2D s1Copy = (Segment2D) s1.copy();
        assertEquals(s1.getPoints()[0], s1Copy.getPoints()[0]);
        assertEquals(s1.getPoints()[1], s1Copy.getPoints()[1]);
        Segment2D s2Copy = (Segment2D) s2.copy();
        assertEquals(s2.getPoints()[0], s2Copy.getPoints()[0]);
        assertEquals(s2.getPoints()[1], s2Copy.getPoints()[1]);
        Segment2D s3Copy = (Segment2D) s3.copy();
        assertEquals(s3.getPoints()[0], s3Copy.getPoints()[0]);
        assertEquals(s3.getPoints()[1], s3Copy.getPoints()[1]);
    }

    @Test
    void scale() {
        s1.scale(new Point2D(1,1 ), 2);
        assertEquals(new Point2D(-1, -1), s1.getPoints()[0]);
        assertEquals(new Point2D(1, 1), s1.getPoints()[1]);
        s2.scale(new Point2D(2,2 ), 2);
        assertEquals(new Point2D(-2, -2), s2.getPoints()[0]);
        assertEquals(new Point2D(2, 2), s2.getPoints()[1]);
        s3.scale(new Point2D(6,6 ), 2);
        assertEquals(new Point2D(-2, -2), s3.getPoints()[0]);
        assertEquals(new Point2D(2, 2), s3.getPoints()[1]);
    }

//    @Test
    void rotate() {
        // TODO: Fix this test
        s1.rotate(new Point2D(1,1 ), 45);
        assertEquals(new Point2D(-1, 1), s1.getPoints()[0]);
        assertEquals(new Point2D(1, 3), s1.getPoints()[1]);
        s2.rotate(new Point2D(2,2 ), 90);
        assertEquals(new Point2D(0, 2), s2.getPoints()[0]);
        assertEquals(new Point2D(4, 6), s2.getPoints()[1]);
        s3.rotate(new Point2D(6,6 ), 90);
        assertEquals(new Point2D(4, 6), s3.getPoints()[0]);
        assertEquals(new Point2D(8, 10), s3.getPoints()[1]);
    }

    @Test
    void getPoints() {
        assertEquals(new Point2D(0, 0), s1.getPoints()[0]);
        assertEquals(new Point2D(1, 1), s1.getPoints()[1]);
        assertEquals(new Point2D(0, 0), s2.getPoints()[0]);
        assertEquals(new Point2D(2, 2), s2.getPoints()[1]);
        assertEquals(new Point2D(2, 2), s3.getPoints()[0]);
        assertEquals(new Point2D(4, 4), s3.getPoints()[1]);
    }
}