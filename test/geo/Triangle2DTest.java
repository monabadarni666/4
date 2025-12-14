package org.example.test.geo;

import org.example.geo.Point2D;
import org.example.geo.Triangle2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle2DTest {
    Triangle2D t1;
    Triangle2D t2;
    Triangle2D t3;

    @BeforeEach
    void setUp() {
        t1 = new Triangle2D(new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 0));
        t2 = new Triangle2D(new Point2D(0, 0), new Point2D(2, 2), new Point2D(4, 0));
        t3 = new Triangle2D(new Point2D(2, 2), new Point2D(4, 4), new Point2D(6, 2));
    }

    @Test
    void contains() {
        assertTrue(t1.contains(new Point2D(1, 0.5)));
        assertTrue(t2.contains(new Point2D(2, 1)));
        assertFalse(t3.contains(new Point2D(2, 6)));
    }

    @Test
    void area() {
        assertEquals(1, t1.area());
        assertEquals(4, t2.area());
        assertEquals(4, t3.area());
    }

    @Test
    void perimeter() {
        assertEquals(Math.sqrt(4) + 2 *Math.sqrt(2), t1.perimeter());
        // 2*2 + 2*2 -> Math.sqrt(8), 2*Math.sqrt(8) -> 4*Math.sqrt(2)
        assertEquals(Math.sqrt(16) + (2 * Math.sqrt(8)), t2.perimeter() , 0.01);
        assertEquals(Math.sqrt(16) + (2 * Math.sqrt(8)), t3.perimeter(), 0.01);
    }

    @Test
    void move() {
        t1.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), t1.getPoints()[0]);
        assertEquals(new Point2D(2, 2), t1.getPoints()[1]);
        assertEquals(new Point2D(3, 1), t1.getPoints()[2]);
        t2.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), t2.getPoints()[0]);
        assertEquals(new Point2D(3, 3), t2.getPoints()[1]);
        assertEquals(new Point2D(5, 1), t2.getPoints()[2]);
        t3.move(new Point2D(1, 1));
        assertEquals(new Point2D(3, 3), t3.getPoints()[0]);
        assertEquals(new Point2D(5, 5), t3.getPoints()[1]);
        assertEquals(new Point2D(7, 3), t3.getPoints()[2]);
    }

    @Test
    void copy() {
        Triangle2D t1Copy = (Triangle2D) t1.copy();
        assertEquals(t1, t1Copy);
        Triangle2D t2Copy = (Triangle2D) t2.copy();
        assertEquals(t2, t2Copy);
        Triangle2D t3Copy = (Triangle2D) t3.copy();
        assertEquals(t3, t3Copy);
    }

    @Test
    void scale() {
        t1.scale(new Point2D(0, 0),2);
        assertEquals(new Point2D(0, 0), t1.getPoints()[0]);
        assertEquals(new Point2D(2, 2), t1.getPoints()[1]);
        assertEquals(new Point2D(4, 0), t1.getPoints()[2]);
        t2.scale(new Point2D(0, 0),2);
        assertEquals(new Point2D(0, 0), t2.getPoints()[0]);
        assertEquals(new Point2D(4, 4), t2.getPoints()[1]);
        assertEquals(new Point2D(8, 0), t2.getPoints()[2]);
        t3.scale(new Point2D(0, 0),2);
        assertEquals(new Point2D(4, 4), t3.getPoints()[0]);
        assertEquals(new Point2D(8, 8), t3.getPoints()[1]);
        assertEquals(new Point2D(12, 4), t3.getPoints()[2]);
    }

    @Test
    void rotate() {
    }

    @Test
    void getPoints() {
        // t1
        Point2D[] pts = t1.getPoints();
        assertEquals(new Point2D(0, 0), pts[0]);
        assertEquals(new Point2D(1, 1), pts[1]);
        assertEquals(new Point2D(2, 0), pts[2]);
        // t2
        pts = t2.getPoints();
        assertEquals(new Point2D(0, 0), pts[0]);
        assertEquals(new Point2D(2, 2), pts[1]);
        assertEquals(new Point2D(4, 0), pts[2]);
        // t3
        pts = t3.getPoints();
        assertEquals(new Point2D(2, 2), pts[0]);
        assertEquals(new Point2D(4, 4), pts[1]);
        assertEquals(new Point2D(6, 2), pts[2]);
    }

    @Test
    void testToString() {
        assertEquals("0.0,0.0,1.0,1.0,2.0,0.0", t1.toString());
        assertEquals("0.0,0.0,2.0,2.0,4.0,0.0", t2.toString());
        assertEquals("2.0,2.0,4.0,4.0,6.0,2.0", t3.toString());
    }
}