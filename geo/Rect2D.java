package org.example.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	private Point2D _p4;

	// Constructors
	public Rect2D(Point2D p1, Point2D p2) {
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
		_p3 = new Point2D(p1.x(), p2.y());
		_p4 = new Point2D(p2.x(), p1.y());
	}
	// deep copy constructor
	public Rect2D(Rect2D ot) {
		this(ot._p1, ot._p2);
	}

	/**
	 * This method returns true if the given point is inside the rectangle.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point2D ot) {
		double minX = Math.min(_p1.x(), _p2.x());
		double maxX = Math.max(_p1.x(), _p2.x());
		double minY = Math.min(_p1.y(), _p2.y());
		double maxY = Math.max(_p1.y(), _p2.y());

		return ot.x() >= minX && ot.x() <= maxX && ot.y() >= minY && ot.y() <= maxY;
	}


	/**
	 * This method returns the area of the rectangle.
	 * @return the area of the rectangle
	 */
	@Override
	public double area() {
//		Area of rectangle = length * width
//		p1 and p2 are the opposite corners of the rectangle, so we need to find the height and width
		double width = Math.abs(_p1.x() - _p2.x()); // the width is the absolute value of the difference between the x coordinates of the two points
		double height = Math.abs(_p1.y() - _p2.y()); // the height is the absolute value of the difference between the y coordinates of the two points
		return height * width;
	}

	/**
	 * This method returns the perimeter of the rectangle.
	 * @return the perimeter of the rectangle
	 */
	@Override
	public double perimeter() {
		double width = Math.abs(_p1.x() - _p2.x()); // the width is the absolute value of the difference between the x coordinates of the two points
		double height = Math.abs(_p1.y() - _p2.y()); // the height is the absolute value of the difference between the y coordinates of the two points
		return 2 * (height + width); // perimeter = 2 * (height + width)
	}

	/**
	 * This method moves the rectangle by the given vector.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	/**
	 * This method returns a GeoShapeable object that represents the intersection of this shape with the given shape.
	 * @return a GeoShapeable object that represents the intersection of this shape with the given shape.
	 */
	@Override
	public GeoShapeable copy() {
		return new Rect2D(this);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
		_p4.rotate(center, angleDegrees);
	}


	@Override
	public Point2D[] getPoints() {
		Point2D[] points = new Point2D[]{_p1, _p2};
		return points;
	}
	/*
		p4--------------------------_p2
		|                        |
		|                        |
		|                        |
		|                        |
	 _p1--------------------------p3
	 */

	@Override
	public String toString() {
		return _p1 + "," + _p3 + "," + _p2 + "," + _p4;
	}
	/**
	 * This overridden method is to check if an object is equal to this object.
	 * by checking if the two objects are of the same type and if their fields are equal.
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Rect2D r)) return false;
		return _p1.equals(r._p1) && _p2.equals(r._p2);
	}
}
