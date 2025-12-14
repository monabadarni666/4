package org.example.test.shape_collection;

import org.example.GUIShape;
import org.example.GUI_Shapeable;
import org.example.ShapeCollection;
import org.example.ShapeCollectionable;
import org.example.geo.Circle2D;
import org.example.geo.Point2D;
import org.example.geo.Rect2D;
import org.example.geo.ShapeComp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {

    ShapeCollectionable sc;
    @BeforeEach
    void setUp() {
        sc = new ShapeCollection();
    }

    @Test
    void get() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());
        assertEquals(s1, sc.get(0));
    }

    @Test
    void size() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s2);
        assertEquals(2, sc.size());
    }

    @Test
    void removeElementAt() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s2);
        assertEquals(2, sc.size());

        sc.removeElementAt(0);
        assertEquals(1, sc.size());
        assertEquals(s2, sc.get(0));
    }

    @Test
    void addAt() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.addAt(s3, 0);
        assertEquals(3, sc.size());
        assertEquals(s3, sc.get(0));
        assertEquals(s1, sc.get(1));
        assertEquals(s2, sc.get(2));
    }

    @Test
    void add() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s3);
        assertEquals(3, sc.size());
        assertEquals(s1, sc.get(0));
        assertEquals(s2, sc.get(1));
        assertEquals(s3, sc.get(2));
    }

    @Test
    void copy() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s3);
        assertEquals(3, sc.size());

        ShapeCollectionable sc2 = sc.copy();
        assertEquals(3, sc2.size());
        assertEquals(s1.getShape(), sc2.get(0).getShape());
        assertEquals(s2.getShape(), sc2.get(1).getShape());
        assertEquals(s3.getShape(), sc2.get(2).getShape());
    }

    @Test
    void sort() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 2);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 1);
        sc.add(s3);
        assertEquals(3, sc.size());

        sc.sort(ShapeComp.CompByTag);
        assertEquals(3, sc.size());
        assertEquals(s1, sc.get(0));
        assertEquals(s2, sc.get(2));
        assertEquals(s3, sc.get(1));
    }

    @Test
    void removeAll() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 2);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 1);
        sc.add(s3);
        assertEquals(3, sc.size());

        sc.removeAll();
        assertEquals(0, sc.size());
    }

    @Test
    void save() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 1);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 2);
        sc.add(s3);
        assertEquals(3, sc.size());

        sc.save("testSave.txt");
        ShapeCollectionable sc2 = new ShapeCollection();
        sc2.load("testSave.txt");
        assertEquals(3, sc2.size());

        assertEquals(s1.toString(), sc2.get(0).toString());
        assertEquals(s2.toString(), sc2.get(1).toString());
        assertEquals(s3.toString(), sc2.get(2).toString());
    }

    @Test
    void load() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 1);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 2);
        sc.add(s3);
        assertEquals(3, sc.size());

        sc.save("testLoad.txt");
        ShapeCollectionable sc2 = new ShapeCollection();
        sc2.load("testLoad.txt");
        assertEquals(3, sc2.size());

        assertEquals(s1.toString(), sc2.get(0).toString());
        assertEquals(s2.toString(), sc2.get(1).toString());
        assertEquals(s3.toString(), sc2.get(2).toString());
    }

    @Test
    void getBoundingBox() {
        assertEquals(0, sc.size());

        GUI_Shapeable s1 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 0);
        sc.add(s1);
        assertEquals(1, sc.size());

        GUI_Shapeable s2 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 1);
        sc.add(s2);
        assertEquals(2, sc.size());

        GUI_Shapeable s3 = new GUIShape(new Circle2D(new Point2D(0, 0), 1), false, Color.WHITE, 2);
        sc.add(s3);
        assertEquals(3, sc.size());

        Rect2D r = sc.getBoundingBox();
        Point2D[] p = r.getPoints();
        assertEquals(0, p[0].x());
        assertEquals(0, p[0].y());
        assertEquals(0, p[1].x());
        assertEquals(1, p[1].y());
    }
}