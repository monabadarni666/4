package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import org.example.geo.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class ShapeCollection implements ShapeCollectionable {
    private ArrayList<GUI_Shapeable> _shapes;

    public ShapeCollection() {
        _shapes = new ArrayList<GUI_Shapeable>();
    }

    @Override
    public GUI_Shapeable get(int i) {
        return _shapes.get(i);
    }

    @Override
    public int size() {
        if (_shapes == null) return 0;
        return _shapes.size();
    }

    /**
     * This method remove element from the collection and return it to the user.
     *
     * @param i - the index of the element to be removed.
     * @return the removed element.
     */
    @Override
    public GUI_Shapeable removeElementAt(int i) {
        return _shapes.remove(i);
    }

    /**
     * This method add a new element at the i'th index. If i is out of bounds, the element will be added at the end of the collection.
     *
     * @param s - the gui_shape
     * @param i - the location (index) in which s should be added
     */
    @Override
    public void addAt(GUI_Shapeable s, int i) {
        // if i is out of bounds from left it will throw an error
        if (i < 0) {
            throw new RuntimeException("Index out of bounds");
        }
        // if i is out of bounds from right it will add the element at the end of the collection
        if (i > _shapes.size()) {
            _shapes.add(s);
        }
        // if i is in bounds it will add the element at the i'th index
        else {
            _shapes.add(i, s);
        }
    }

    /**
     * This method add a new element at the end of the collection.
     *
     * @param s - the gui_shape
     */
    @Override
    public void add(GUI_Shapeable s) {
        if (s != null && s.getShape() != null) {
            _shapes.add(s);
        }
    }

    /**
     * This method copy the collection to a new collection and return it.
     *
     * @return a copy of the collection.
     */
    @Override
    public ShapeCollectionable copy() {

        // create new ShapeCollection
        ShapeCollection sc = new ShapeCollection();

        // copy all shapes from this collection to sc collection
        for (GUI_Shapeable s : _shapes) {
            sc.add(s.copy());
        }
        // return the new collection
        return sc;
    }

    /**
     * This method sort the collection according to the given comparator.
     *
     * @param comp a linear order over gui_sahpes as defined in java.util.Comparator
     */
    @Override
    public void sort(Comparator<GUI_Shapeable> comp) {
        _shapes.sort(comp);
    }

    /**
     * This method remove all the elements from the collection.
     */
    @Override
    public void removeAll() {
        _shapes.clear();
    }

    @Override
    public void save(String file) {
        //////////add your code below ///////////
        String info = "";
        for (GUI_Shapeable s : _shapes) {
            info += s.toString() + "\n";
        }

        try {
            FileWriter fw = new FileWriter(file); // create a file writer
            fw.write(info); // write the info to the file
            fw.close(); // close the file
        } catch (IOException i) {
            i.printStackTrace();
        }
        //////////////////////////////////////////
    }

    /**
     * This method is load a collection from a file.
     *
     * @param file - the name of the text file to create a gui shape file from.
     */
    @Override
    public void load(String file) {
        // read the file and create the shapes
        // add the shapes to _shapes
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = br.readLine()) != null) {
                System.out.println(row);
                String[] values = row.split(",");
                if (values[4].equals("Circle2D")) {
                    _shapes.add(new GUIShape(extractCircle2DFromArray(values)));
                } else if (values[4].equals("Rect2D")) {
                    _shapes.add(new GUIShape(extractRect2DFromArray(values)));
                } else if (values[4].equals("Segment2D")) {
                    _shapes.add(new GUIShape(extractSegment2DFromArray(values)));
                } else if (values[4].equals("Triangle2D")) {
                    _shapes.add(new GUIShape(extractTriangle2DFromArray(values)));
                } else if (values[4].equals("Polygon2D")) {
                    _shapes.add(new GUIShape(extractPolygon2DFromArray(values)));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //////////////////////////////////////////
    }

    @Override
    public Rect2D getBoundingBox() {
        Rect2D ans = null;
        //////////add your code below ///////////
        if (_shapes == null || _shapes.size() == 0 || _shapes.get(0) == null || _shapes.get(0).getShape() == null) {
            return null;
        }
        Point2D leftBottom = _shapes.get(0).getShape().getPoints()[0];
        Point2D rightTop = _shapes.get(0).getShape().getPoints()[0];
        Point2D currentPoint;
        // get left bottom point and right top point
        for (int i = 0; i < _shapes.size(); i++) {      // this loop go over all the shapes
            for (int j = 0; j < _shapes.get(i).getShape().getPoints().length; j++) { // this loop go over all the points of the current shape
                currentPoint = _shapes.get(i).getShape().getPoints()[j];
                if (currentPoint.x() < leftBottom.x()) {
                    leftBottom = _shapes.get(i).getShape().getPoints()[j];
                }
                if (currentPoint.y() > rightTop.y()) {
                    rightTop = _shapes.get(i).getShape().getPoints()[j];
                }
            }
        }
        ans = new Rect2D(leftBottom, rightTop);
        //////////////////////////////////////////
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < size(); i = i + 1) {
            ans += this.get(i);
        }
        return ans;
    }


    /* -------------------- PRIVATE METHODS ------------------------- */

    private GUIShape extractPolygon2DFromArray(String[] values) {
        int size = values.length - 5;
        ArrayList<Point2D> pts = new ArrayList<>();
        Point2D[] points = new Point2D[size];
        for (int i = 0; i < size; i += 2) {
            pts.add(new Point2D(Double.parseDouble(values[i + 5]), Double.parseDouble(values[i + 6])));
        }
        return new GUIShape(
                new Polygon2D(pts),
                Boolean.parseBoolean(values[2]),
                new Color(Integer.parseInt(values[1])),
                Integer.parseInt(values[3])
        );
    }

    private GUIShape extractTriangle2DFromArray(String[] values) {
        Point2D p1 = new Point2D(Double.parseDouble(values[5]), Double.parseDouble(values[6]));
        Point2D p2 = new Point2D(Double.parseDouble(values[7]), Double.parseDouble(values[8]));
        Point2D p3 = new Point2D(Double.parseDouble(values[9]), Double.parseDouble(values[10]));
        return new GUIShape(
                new Triangle2D(p1, p2, p3),
                Boolean.parseBoolean(values[2]),
                new Color(Integer.parseInt(values[1])),
                Integer.parseInt(values[3])
        );
    }

    private GUIShape extractSegment2DFromArray(String[] values) {
        Point2D p1 = new Point2D(Double.parseDouble(values[5]), Double.parseDouble(values[6]));
        Point2D p2 = new Point2D(Double.parseDouble(values[7]), Double.parseDouble(values[8]));
        return new GUIShape(
                new Segment2D(p1, p2),
                Boolean.parseBoolean(values[2]),
                new Color(Integer.parseInt(values[1])),
                Integer.parseInt(values[3])
        );
    }

    private GUIShape extractRect2DFromArray(String[] values) {
        Point2D p1 = new Point2D(Double.parseDouble(values[5]), Double.parseDouble(values[6]));
        Point2D p2 = new Point2D(Double.parseDouble(values[7]), Double.parseDouble(values[8]));
        Point2D p3 = new Point2D(Double.parseDouble(values[9]), Double.parseDouble(values[10]));
        Point2D p4 = new Point2D(Double.parseDouble(values[11]), Double.parseDouble(values[12]));
        Point2D[] pts = new Point2D[]{p1, p2, p3, p4};
        Point2D leftBottom = p1;
        Point2D rightTop = p1;
        Point2D currentPoint;
        for (Point2D pt : pts) {
            currentPoint = pt;
            if (currentPoint.x() < leftBottom.x() && currentPoint.y() < leftBottom.y()) {
                leftBottom = pt;
            }
            if (currentPoint.x() > rightTop.x() && currentPoint.y() > rightTop.y()) {
                rightTop = pt;
            }
        }
        return new GUIShape(
                new Rect2D(leftBottom, rightTop),
                Boolean.parseBoolean(values[2]),
                new Color(Integer.parseInt(values[1])),
                Integer.parseInt(values[3])
        );
    }

    private GUIShape extractCircle2DFromArray(String[] values) {
        return new GUIShape(
                new Circle2D(new Point2D(Double.parseDouble(values[5]), Double.parseDouble(values[6])), Double.parseDouble(values[7])),
                Boolean.parseBoolean(values[2]),
                new Color(Integer.parseInt(values[1])),
                Integer.parseInt(values[3])
        );
    }
}
