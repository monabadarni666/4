package org.example.test.geo;

import org.example.geo.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {

    Point2D p1;
    Point2D p2;
    Point2D p3;

    @BeforeEach
    void setUp() {
        p1 = new Point2D(0, 0);
        p2 = new Point2D(1, 1);
        p3 = new Point2D(2, 2);
    }

    @Test
    void x() {
        assertEquals(0, p1.x());
        assertEquals(1, p2.x());
        assertEquals(2, p3.x());
    }

    @Test
    void y() {
        assertEquals(0, p1.y());
        assertEquals(1, p2.y());
        assertEquals(2, p3.y());
    }

    @Test
    void ix() {
        assertEquals(0, p1.ix());
        assertEquals(1, p2.ix());
        assertEquals(2, p3.ix());
    }

    @Test
    void iy() {
        assertEquals(0, p1.iy());
        assertEquals(1, p2.iy());
        assertEquals(2, p3.iy());
    }

    @Test
    void add() {
        assertEquals(new Point2D(1, 1), p1.add(p2));
        assertEquals(new Point2D(2, 2), p1.add(p3));
        assertEquals(new Point2D(3, 3), p2.add(p3));
    }

    @Test
    void testToString() {
        assertEquals("0.0,0.0", p1.toString());
        assertEquals("1.0,1.0", p2.toString());
        assertEquals("2.0,2.0", p3.toString());
    }

    @Test
    void distance() {
        assertEquals(1.4142135623730951, p1.distance(p2));
        assertEquals(2.8284271247461903, p1.distance(p3));
        assertEquals(1.4142135623730951, p2.distance(p3));
    }


    @Test
    void testEquals() {
        assertEquals(p1, new Point2D(0, 0));
        assertEquals(p2, new Point2D(1, 1));
        assertEquals(p3, new Point2D(2, 2));
    }

    @Test
    void vector() {
        assertEquals(new Point2D(1, 1), p1.vector(p2));
        assertEquals(new Point2D(2, 2), p1.vector(p3));
        assertEquals(new Point2D(1, 1), p2.vector(p3));
    }

    @Test
    void move() {
        p1.move(new Point2D(1, 1));
        p1.move(new Point2D(2, 2));
        p2.move(new Point2D(2, 2));

        assertEquals(new Point2D(3, 3), p1);
        assertEquals(new Point2D(3, 3), p2);
    }

    @Test
    void scale() {
        p1.scale(new Point2D(1, 1), 0.9);
        p2.scale(new Point2D(1, 1), 0.9);
        p3.scale(new Point2D(1, 1), 0.9);

        assertEquals(new Point2D(0.09999999999999998,0.09999999999999998), p1);
        assertEquals(new Point2D(1.0,1.0), p2);
        assertEquals(new Point2D(1.9,1.9), p3);
    }

    @Test
    void rotate() {
        p1.rotate(new Point2D(1, 1), 45);
        p2.rotate(new Point2D(1, 1), 45);
        p3.rotate(new Point2D(1, 1), 45);

        assertEquals(new Point2D(0.9999999999999999,-0.41421356237309503), p1);
        assertEquals(new Point2D(1.0,1.0), p2);
        assertEquals(new Point2D(1.0,2.414213562373095), p3);
    }

}