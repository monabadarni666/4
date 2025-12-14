package org.example.test.geo;

import org.example.geo.Point2D;
import org.example.geo.Rect2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect2DTest {
    Rect2D r1;
    Rect2D r2;
    Rect2D r3;

    @BeforeEach
    void setUp() {
        r1 = new Rect2D(new Point2D(0, 0), new Point2D(1, 1));
        r2 = new Rect2D(new Point2D(0, 0), new Point2D(2, 2));
        r3 = new Rect2D(new Point2D(2, 2), new Point2D(4, 4));
    }

    @Test
    void contains() {
        assertTrue(r1.contains(new Point2D(0.5, 0.5)));
        assertTrue(r2.contains(new Point2D(1, 1)));
        assertFalse(r3.contains(new Point2D(2, 6)));
    }

    @Test
    void area() {
        assertEquals(1, r1.area());
        assertEquals(4, r2.area());
        assertEquals(4, r3.area());
    }

    @Test
    void perimeter() {
        assertEquals(4, r1.perimeter());
        assertEquals(8, r2.perimeter());
        assertEquals(8, r3.perimeter());
    }

    @Test
    void move() {
        r1.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), r1.getPoints()[0]);
        assertEquals(new Point2D(2, 2), r1.getPoints()[1]);
        r2.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), r2.getPoints()[0]);
        assertEquals(new Point2D(3, 3), r2.getPoints()[1]);
        r3.move(new Point2D(1, 1));
        assertEquals(new Point2D(3, 3), r3.getPoints()[0]);
        assertEquals(new Point2D(5, 5), r3.getPoints()[1]);
    }

    @Test
    void copy() {
        Rect2D r1Copy = (Rect2D) r1.copy();
        assertEquals(r1.getPoints()[0], r1Copy.getPoints()[0]);
        assertEquals(r1.getPoints()[1], r1Copy.getPoints()[1]);
        Rect2D r2Copy = (Rect2D) r2.copy();
        assertEquals(r2.getPoints()[0], r2Copy.getPoints()[0]);
        assertEquals(r2.getPoints()[1], r2Copy.getPoints()[1]);
        Rect2D r3Copy = (Rect2D) r3.copy();
        assertEquals(r3.getPoints()[0], r3Copy.getPoints()[0]);
        assertEquals(r3.getPoints()[1], r3Copy.getPoints()[1]);
    }

    @Test
    void scale() {
        r1.scale(new Point2D(0,0),2);
        assertEquals(new Point2D(0, 0), r1.getPoints()[0]);
        assertEquals(new Point2D(2, 2), r1.getPoints()[1]);
        r2.scale(new Point2D(0,0),2);
        assertEquals(new Point2D(0, 0), r2.getPoints()[0]);
        assertEquals(new Point2D(4, 4), r2.getPoints()[1]);
        r3.scale(new Point2D(0,0),2);
        assertEquals(new Point2D(4, 4), r3.getPoints()[0]);
        assertEquals(new Point2D(8, 8), r3.getPoints()[1]);
    }

    @Test
    void rotate() {
        // TODO: fix this test
//        r1.rotate(new Point2D(0,0),90);
//        assertEquals(new Point2D(0, 0), r1.getPoints()[0]);
//        assertEquals(new Point2D(-1, 1), r1.getPoints()[1]);
//        r2.rotate(new Point2D(0,0),90);
//        assertEquals(new Point2D(0, 0), r2.getPoints()[0]);
//        assertEquals(new Point2D(-2, 2), r2.getPoints()[1]);
//        r3.rotate(new Point2D(0,0),90);
//        assertEquals(new Point2D(2, 2), r3.getPoints()[0]);
//        assertEquals(new Point2D(-2, 4), r3.getPoints()[1]);
    }

    @Test
    void getPoints() {
        // r1
        assertEquals(new Point2D(0, 0), r1.getPoints()[0]);
        assertEquals(new Point2D(1, 1), r1.getPoints()[1]);

        // r2
        assertEquals(new Point2D(0, 0), r2.getPoints()[0]);
        assertEquals(new Point2D(2, 2), r2.getPoints()[1]);

        // r3
        assertEquals(new Point2D(2, 2), r3.getPoints()[0]);
        assertEquals(new Point2D(4, 4), r3.getPoints()[1]);


    }

    @Test
    void testToString() {
        assertEquals("0.0,0.0,0.0,1.0,1.0,1.0,1.0,0.0", r1.toString());
        assertEquals("0.0,0.0,0.0,2.0,2.0,2.0,2.0,0.0", r2.toString());
        assertEquals("2.0,2.0,2.0,4.0,4.0,4.0,4.0,2.0", r3.toString());
    }
}