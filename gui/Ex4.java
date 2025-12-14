package org.example.gui;

import org.example.*;
import org.example.geo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

/**213971955_213817638
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private ShapeCollectionable _shapes = new ShapeCollection();
    private GUI_Shapeable _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point2D _p1;
    private Point2D _p2;                    // a helper variable for the "triangle" mode.
    private ArrayList<Point2D> _polyPoints; // a helper variable for the "polygon" mode.

    private static Ex4 _winEx4 = null;

    private Ex4() {
        init(null);
    }

    public void init(ShapeCollectionable s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s.copy();
        }
        GUI_Shapeable _gs = null;
        Polygon2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point2D _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    /**
     * This function draws a shape on the screen.
     *
     * @param g
     */
    private static void drawShape(GUI_Shapeable g) {
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex4.setPenColor(Color.gray);
        }
        GeoShapeable gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle2D) {
            Circle2D c = (Circle2D) gs;
            Point2D cen = c.getPoints()[0];
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }
        // ------------------------------- my code from here: -------------------------------


        if (gs instanceof Rect2D) {
            Rect2D r = (Rect2D) gs;
            Point2D[] pts = r.getPoints();
            /*
             x – the x-coordinate of the center of the rectangle
             y – the y-coordinate of the center of the rectangle
             halfWidth – one half the width of the rectangle
             halfHeight – one half the height of the rectangle
             */
            double x = (pts[0].x() + pts[1].x()) / 2;
            double y = (pts[0].y() + pts[1].y()) / 2;
            double halfWidth = Math.abs(pts[0].x() - pts[1].x()) / 2;
            double halfHeight = Math.abs(pts[0].y() - pts[1].y()) / 2;
            if (isFill) {
                StdDraw_Ex4.filledRectangle(x, y, halfWidth, halfHeight);
            } else {
                StdDraw_Ex4.rectangle(x, y, halfWidth, halfHeight);
            }
        }

        if (gs instanceof Segment2D) {
            Segment2D s = (Segment2D) gs;
            Point2D p1 = s.getPoints()[0];
            Point2D p2 = s.getPoints()[1];
            StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
        }


        if (gs instanceof Triangle2D) {
            Triangle2D t = (Triangle2D) gs;
            Point2D[] pts = t.getPoints();
            double[] xs = new double[]{pts[0].x(), pts[1].x(), pts[2].x()};
            double[] ys = new double[]{pts[0].y(), pts[1].y(), pts[2].y()};
            if (isFill) {
                StdDraw_Ex4.filledPolygon(xs, ys);
            } else {
                StdDraw_Ex4.polygon(xs, ys);
            }
        }
        if (gs instanceof Polygon2D) {
            Polygon2D p = (Polygon2D) gs;
            Point2D[] points = p.getPoints();
            double[] xs = new double[points.length];
            double[] ys = new double[points.length];
            for (int i = 0; i < points.length; i++) {
                xs[i] = points[i].x();
                ys[i] = points[i].y();
            }
            if (isFill) {
                StdDraw_Ex4.filledPolygon(xs, ys);
            } else {
                StdDraw_Ex4.polygon(xs, ys);
            }
        }

    }

    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    public void actionPerformed(String p) {
        _mode = p;
        // file section
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("Load")) {
            loadFile();
        }
        if (p.equals("Save")) {
            saveFile();
        }
        // select section
        if (_mode.equals("All")) {
            selectAll();
        }
        if (_mode.equals("None")) {
            selectNone();
        }
        if (_mode.equals("Anti")) {
            selectAnti();
        }
        if (_mode.equals("Info")) {
            getInfo();
        }
        // color section
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        // sort section
        if (p.equals("ByArea")) {
            _shapes.sort(ShapeComp.CompByArea);
        }
        if (p.equals("ByAntiArea")) {
            _shapes.sort(ShapeComp.CompByAntiArea);
        }
        if (p.equals("ByPerimeter")) {
            _shapes.sort(ShapeComp.CompByPerimeter);
        }
        if (p.equals("ByAntiPerimeter")) {
            _shapes.sort(ShapeComp.CompByAntiPerimeter);
        }
        if (p.equals("ByToString")) {
            _shapes.sort(ShapeComp.CompByToString);
        }
        if (p.equals("ByAntiToString")) {
            _shapes.sort(ShapeComp.CompByAntiToString);
        }
        if (p.equals("ByTag")) {
            _shapes.sort(ShapeComp.CompByTag);
        }
        if (p.equals("ByAntiTag")) {
            _shapes.sort(ShapeComp.CompByAntiTag);
        }

        // edit section
        if (p.equals("Remove")) {
            removeSelected();
        }

        drawShapes();

    }


    public void mouseClicked(Point2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        /* --------------------------- SHAPES -------------------------------*/
        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Triangle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else if (_p2 == null) {
                _p2 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
            }
        }
        if (_mode.equals("Polygon")) {
            // use 'polyPoints'
            // First click - create new point _p1, and new array _polyPoints
            if (_gs == null) {
                _p1 = new Point2D(p);
                _polyPoints = new ArrayList<>();
                _polyPoints.add(_p1);
            } else {
//                _gs.setColor(_color);
//                _gs.setFilled(_fill);
//                _shapes.add(_gs);
//                _gs = null;
//                _p1 = null;
                System.out.println("add point:" + p);
                _polyPoints.add(p);
            }
        }

        /*---------------------------- EDIT SECTION ----------------------------*/
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                _p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                _p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                double angle = Math.atan2(p.y() - _p1.y(), p.x() - _p1.x());
                double angleDegrees = angle * 180 / Math.PI;
                System.out.println("Angle: " + angleDegrees + " degrees");
                rotate(angleDegrees);
                _p1 = null;
            }
        }
        if (_mode.equals("Point")) {
            select(p);
        }


        if (_mode.equals("Scale_90%")) {
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()) {
                    _shapes.get(i).getShape().scale(p, 0.9);
                }
            }
        }
        if (_mode.equals("Scale_110%")) {
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()) {
                    _shapes.get(i).getShape().scale(p, 1.1);
                }
            }
        }
        drawShapes();
    }

    public void mouseRightClicked(Point2D p) {
//        System.out.println("right click!");
//        var rect = this._shapes.getBoundingBox();
//        if (rect != null) {
//            System.out.println(Arrays.toString(rect.getPoints()));
//        }

        if (_mode.equals("Polygon") && !_polyPoints.isEmpty()) {
            _gs.setColor(_color);
            _gs.setFilled(_fill);
            _shapes.add(_gs);
            _gs = null;
            _p1 = null;
            drawShapes();
            _polyPoints.clear();
        }
        System.out.println(getInfo());
    }

    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShapeable gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point2D p = new Point2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment2D(_p1, p);
            }
            if (_mode.equals("Triangle")) {
                if (_p2 != null) {
                    gs = new Triangle2D(_p1, _p2, p);
                } else {
                    gs = new Segment2D(_p1, p);
                }
            }
            if (_mode.equals("Polygon")) {
                if (_p1 != null) {
                    gs = new Polygon2D(_polyPoints, p);
                } else {
                    gs = new Segment2D(_p1, p);
                }
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    @Override
    public ShapeCollectionable getShape_Collection() {
        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }

    @Override
    public String getInfo() {
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }

// ------------------------------ PRIVATE METHODS ------------------------------

    /**
     * This method loads the shapes from the file.
     * I found this method in the internet
     */
    private void loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to load");

        String currentWorkingDirectory = System.getProperty("user.dir");
        File projectDirectory = new File(currentWorkingDirectory);
        fileChooser.setCurrentDirectory(projectDirectory);

        int userSelection = fileChooser.showSaveDialog(fileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            _shapes.removeAll();
            _shapes.load(fileToLoad.getAbsolutePath());
        }
    }
    /**
     * This method saves the current state of the GUI to a file.
     * I found this method in the internet
     */
    private void saveFile() {
        // This object is to open a file chooser window
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        // Set the current directory to the project directory
        String currentWorkingDirectory = System.getProperty("user.dir");
        File projectDirectory = new File(currentWorkingDirectory);
        fileChooser.setCurrentDirectory(projectDirectory);

        int userSelection = fileChooser.showSaveDialog(fileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile(); // get the file
            System.out.println("Save as file: " + fileToSave.getAbsolutePath()); // print the path
            _shapes.save(fileToSave.getAbsolutePath());
        }
    }

    /**
     * This is helper function to anti select all shapes
     */
    private void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }

    /**
     * This is helper function to unselect all shapes
     */
    private void selectNone() {
        for (int i = 0; i < _shapes.size(); i++) {
            if (_shapes.get(i).isSelected()) { // check if the shape is selected
                _shapes.get(i).setSelected(false);
            }
        }
    }

    /**
     * This is helper function to select all the shapes that aren't selected
     */
    private void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            if (!_shapes.get(i).isSelected()) { // check if the shape is not selected
                _shapes.get(i).setSelected(true);
            }
        }
    }

    private void select(Point2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (s.isSelected() && g != null) {
                g.move(_p1);
            }
        }
    }

    private void rotate(double angle) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (s.isSelected() && g != null) {
                g.rotate(_p1, angle);
            }
        }
    }

    /**
     * This is helper function to copy all the selected shapes
     */
    private void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected() && s.getShape() != null) {             // check if the shape is selected
                _shapes.add(s.copy());                                // copy the shape
                _shapes.get(_shapes.size() - 1).getShape().move(_p1); // move the copy
            }
        }
    }

    /**
     * This is helper function to remove all the selected shapes
     */
    private void removeSelected() {
        for (int i = 0; i < _shapes.size(); i++) {
            if (_shapes.get(i).isSelected()) {             // check if the shape is selected
                _shapes.removeElementAt(i);                // remove the shape
                i--;                                       // decrement the index
            }
        }
    }
}
