package org.example.test.gui;

import org.example.Ex4_Const;
import org.example.GUIShape;
import org.example.GUI_Shapeable;
import org.example.ShapeCollectionable;
import org.example.geo.Circle2D;
import org.example.geo.Point2D;
import org.example.geo.ShapeComp;
import org.example.gui.Ex4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Ex4Test {

    Ex4 ex4;

    @BeforeEach
    void setUp() {
        ex4 = Ex4.getInstance();
    }

    @Test
    void init() {
        ex4.init(null);
    }

    @Test
    void fullTestOnEx4() {
        ShapeCollectionable shapes = ex4.getShape_Collection();
        Point2D p1 = new Point2D(3, 4);
        Point2D p2 = new Point2D(6, 8);
        Circle2D c1 = new Circle2D(p1, 2);
        Circle2D c2 = new Circle2D(p2, 4);
        GUI_Shapeable gs1 = new GUIShape(c1, true, Color.black, 1);
        GUI_Shapeable gs2 = new GUIShape(c2, false, Color.blue, 2);
        shapes.add(gs1);
        shapes.add(gs2);
        ex4.init(shapes);
        ex4.show();
        System.out.print(ex4.getInfo());
//        Tests
        assertEquals(2, shapes.size());
        assertEquals(2, ex4.getShape_Collection().size());
        assertEquals(2, ex4.getShape_Collection().size());
        assertEquals(2, ex4.getShape_Collection().get(0).getShape().getPoints().length);
        assertEquals(2, ex4.getShape_Collection().get(1).getShape().getPoints().length);
        assertEquals(3, ex4.getShape_Collection().get(0).getShape().getPoints()[0].x(), 0.0001);
        assertEquals(4, ex4.getShape_Collection().get(0).getShape().getPoints()[0].y(), 0.0001);
        assertEquals(6, ex4.getShape_Collection().get(1).getShape().getPoints()[0].x(), 0.0001);
        assertEquals(8, ex4.getShape_Collection().get(1).getShape().getPoints()[0].y(), 0.0001);
        assertEquals(3, ex4.getShape_Collection().get(0).getShape().getPoints()[1].x(), 0.0001);
        assertEquals(6, ex4.getShape_Collection().get(0).getShape().getPoints()[1].y(), 0.0001);
        assertEquals(6, ex4.getShape_Collection().get(1).getShape().getPoints()[1].x(), 0.0001);
        assertEquals(12, ex4.getShape_Collection().get(1).getShape().getPoints()[1].y(), 0.0001);
        assertEquals(3, ex4.getShape_Collection().get(0).getShape().getPoints()[1].x(), 0.0001);
        assertEquals(6, ex4.getShape_Collection().get(0).getShape().getPoints()[1].y(), 0.0001);


//        Tests on comparators
        shapes.sort(ShapeComp.CompByPerimeter);
        assertEquals(gs1, shapes.get(0));
        assertEquals(gs2, shapes.get(1));
        shapes.sort(ShapeComp.CompByAntiPerimeter);
        assertEquals(gs2, shapes.get(0));
        assertEquals(gs1, shapes.get(1));
        shapes.sort(ShapeComp.CompByArea);
        assertEquals(gs1, shapes.get(0));
        assertEquals(gs2, shapes.get(1));
        shapes.sort(ShapeComp.CompByAntiArea);
        assertEquals(gs2, shapes.get(0));
        assertEquals(gs1, shapes.get(1));
        shapes.sort(ShapeComp.CompByToString);
        assertEquals(gs2, shapes.get(0));
        assertEquals(gs1, shapes.get(1));
        shapes.sort(ShapeComp.CompByAntiToString);
        assertEquals(gs1, shapes.get(0));
        assertEquals(gs2, shapes.get(1));
        shapes.sort(ShapeComp.CompByTag);
        assertEquals(gs1, shapes.get(0));
        assertEquals(gs2, shapes.get(1));
        shapes.sort(ShapeComp.CompByAntiTag);
        assertEquals(gs2, shapes.get(0));
        assertEquals(gs1, shapes.get(1));
    }
}