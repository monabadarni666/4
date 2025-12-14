package org.example.test.geo;

import org.example.geo.Point2D;
import org.example.geo.Polygon2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Finish this tests
class Polygon2DTest {
    Polygon2D p1;
    Polygon2D p2;
    Polygon2D p3;

    @BeforeEach
    void setUp() {
        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0, 0));
        pts.add(new Point2D(0, 1));
        pts.add(new Point2D(1, 1));
        pts.add(new Point2D(1, 0));
        p1 = new Polygon2D(pts);
        pts.clear();
        pts.add(new Point2D(0, 0));
        pts.add(new Point2D(0, 2));
        pts.add(new Point2D(2, 2));
        pts.add(new Point2D(2, 0));
        pts.add(new Point2D(7, 3));
        p2 = new Polygon2D(pts);
        pts.clear();
        pts.add(new Point2D(2, 2));
        pts.add(new Point2D(2, 4));
        pts.add(new Point2D(4, 4));
        pts.add(new Point2D(4, 2));
        pts.add(new Point2D(9, 7));
        p3 = new Polygon2D(pts);
    }

    @Test
    void contains() {
        assertTrue(p1.contains(new Point2D(0.5, 0.5)));
        assertTrue(p2.contains(new Point2D(1, 1)));
        assertFalse(p3.contains(new Point2D(4, 10)));
    }

    @Test
    void area() {
        assertEquals(2, p1.area());
        assertEquals(2, p2.area());
        assertEquals(2, p3.area());
    }

    @Test
    void perimeter() {
        assertEquals(4, p1.perimeter());
        assertEquals(19, p2.perimeter(), 0.5);
        assertEquals(21, p3.perimeter(), 1);
    }

    @Test
    void move() {
        p1.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), p1.getPoints()[0]);
        assertEquals(new Point2D(1, 2), p1.getPoints()[1]);
        assertEquals(new Point2D(2, 2), p1.getPoints()[2]);
        assertEquals(new Point2D(2, 1), p1.getPoints()[3]);
        p2.move(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), p2.getPoints()[0]);
        assertEquals(new Point2D(1, 3), p2.getPoints()[1]);
        assertEquals(new Point2D(3, 3), p2.getPoints()[2]);
        assertEquals(new Point2D(3, 1), p2.getPoints()[3]);
        assertEquals(new Point2D(8, 4), p2.getPoints()[4]);
        p3.move(new Point2D(1, 1));
        assertEquals(new Point2D(3, 3), p3.getPoints()[0]);
        assertEquals(new Point2D(3, 5), p3.getPoints()[1]);
        assertEquals(new Point2D(5, 5), p3.getPoints()[2]);
        assertEquals(new Point2D(5, 3), p3.getPoints()[3]);
        assertEquals(new Point2D(10, 8), p3.getPoints()[4]);
    }

    @Test
    void copy() {
        Polygon2D p1Copy = (Polygon2D) p1.copy();
        assertEquals(p1, p1Copy);
        Polygon2D p2Copy = (Polygon2D) p2.copy();
        assertEquals(p2, p2Copy);
        Polygon2D p3Copy =(Polygon2D) p3.copy();
        assertEquals(p3, p3Copy);
    }

    @Test
    void getPoints() {
        Point2D[] p1Points = p1.getPoints();
        assertEquals(new Point2D(0, 0), p1Points[0]);
        assertEquals(new Point2D(0, 1), p1Points[1]);
        assertEquals(new Point2D(1, 1), p1Points[2]);
        assertEquals(new Point2D(1, 0), p1Points[3]);

        Point2D[] p2Points = p2.getPoints();
        assertEquals(new Point2D(0, 0), p2Points[0]);
        assertEquals(new Point2D(0, 2), p2Points[1]);
        assertEquals(new Point2D(2, 2), p2Points[2]);
    }
}