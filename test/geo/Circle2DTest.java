package org.example.test.geo;

import org.example.geo.Circle2D;
import org.example.geo.Point2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Circle2DTest {
    Circle2D c1;
    Circle2D c2;
    Circle2D c3;
    @BeforeEach
    void setUp() {
        c1 = new Circle2D(new Point2D(0, 0), 1);
        c2 = new Circle2D(new Point2D(0, 0), 2);
        c3 = new Circle2D(new Point2D(2, 2), 3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRadius() {
        assertEquals(1, c1.getRadius());
        assertEquals(2, c2.getRadius());
        assertEquals(3, c3.getRadius());
    }

    @Test
    void testToString() {
        assertEquals("0.0,0.0, 1.0", c1.toString());
        assertEquals("0.0,0.0, 2.0", c2.toString());
        assertEquals("2.0,2.0, 3.0", c3.toString());
    }

    @Test
    void contains() {
        assertTrue(c1.contains(new Point2D(0,1)));
        assertTrue(c2.contains(new Point2D(1,1)));
        assertFalse(c3.contains(new Point2D(2,6)));
    }

    @Test
    void area() {
        assertEquals(Math.PI, c1.area());
        assertEquals(4*Math.PI, c2.area());
        assertEquals(9*Math.PI, c3.area());
    }

    @Test
    void perimeter() {
        assertEquals(2*Math.PI, c1.perimeter());
        assertEquals(4*Math.PI, c2.perimeter());
        assertEquals(6*Math.PI, c3.perimeter());
    }

    @Test
    void move() {
        c1.move(new Point2D(1,1));
        assertEquals("1.0,1.0, 1.0", c1.toString());
        c2.move(new Point2D(1,1));
        assertEquals("1.0,1.0, 2.0", c2.toString());
        c3.move(new Point2D(1,1));
        assertEquals("3.0,3.0, 3.0", c3.toString());
    }

    @Test
    void copy() {
        Circle2D c1Copy = (Circle2D) c1.copy();
        assertEquals(c1.toString(), c1Copy.toString());
        Circle2D c2Copy = (Circle2D) c2.copy();
        assertEquals(c2.toString(), c2Copy.toString());
        Circle2D c3Copy = (Circle2D) c3.copy();
        assertEquals(c3.toString(), c3Copy.toString());
    }

    @Test
    void getPoints() {
        Point2D[] c1Points = c1.getPoints();
        assertEquals(2, c1Points.length);
        assertEquals("0.0,0.0", c1Points[0].toString());
        assertEquals("0.0,1.0", c1Points[1].toString());
        Point2D[] c2Points = c2.getPoints();
        assertEquals(2, c2Points.length);
        assertEquals("0.0,0.0", c2Points[0].toString());
        assertEquals("0.0,2.0", c2Points[1].toString());
        Point2D[] c3Points = c3.getPoints();
        assertEquals(2, c3Points.length);
        assertEquals("2.0,2.0", c3Points[0].toString());
        assertEquals("2.0,5.0", c3Points[1].toString());
    }

    @Test
    void scale() {
        c1.scale(c1.getPoints()[0], 2);
        assertEquals("0.0,0.0, 2.0", c1.toString());
        c2.scale(c2.getPoints()[0], 2);
        assertEquals("0.0,0.0, 4.0", c2.toString());
        c3.scale(c3.getPoints()[0], 2);
        assertEquals("2.0,2.0, 6.0", c3.toString());
    }

    @Test
    void rotate() {
        c1.rotate(c1.getPoints()[0], 90);
        assertEquals("0.0,0.0, 1.0", c1.toString());
        c2.rotate(c2.getPoints()[0], 90);
        assertEquals("0.0,0.0, 2.0", c2.toString());
        c3.rotate(c3.getPoints()[0], 90);
        assertEquals("2.0,2.0, 3.0", c3.toString());
    }
}