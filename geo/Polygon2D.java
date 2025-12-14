package org.example.geo;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{

	private ArrayList<Point2D> _points;



	// Constructors
	public Polygon2D(ArrayList<Point2D> points) {
		_points = new ArrayList<>();
		_points.addAll(points);
	}
	// This constructor is used to create a copy of a polygon
	public Polygon2D(ArrayList<Point2D> points, Point2D p) {
		_points = new ArrayList<>();
		_points.addAll(points);
		_points.add(p);
	}
	public Polygon2D(){
		_points = new ArrayList<>();
	}
	// deep copy constructor
	public Polygon2D(Polygon2D p) {
		_points = new ArrayList<>();
		for (Point2D point : p._points) {
			_points.add(new Point2D(point));
		}
	}

	@Override
	public boolean contains(Point2D ot) {
		int i, j;
		boolean c = false;
		int nvert = _points.size();
		for(i = 0, j = nvert-1; i < nvert; j = i++) {
			if( ((_points.get(i).y()>ot.y()) != (_points.get(j).y()>ot.y())) &&
					(ot.x() < (_points.get(j).x()-_points.get(i).x()) * (ot.y()-_points.get(i).y()) / (_points.get(j).y()-_points.get(i).y()) + _points.get(i).x()) )
				c = !c;
		}
		return c;
	}


	/**
	 * This method returns the area of the polygon.
	 * it is assumed that the polygon is simple (no self intersection).
	 * @return
	 */
	@Override
	public double area() {
		double area = 0; // Initialize result
		for (int i = 0; i < _points.size(); i++) { // Calculate value of shoelace formula
			Point2D p1 = _points.get(i); // Get the current point
			Point2D p2 = _points.get((i+1)%_points.size()); // Get the next point
			area += p1.x()*p2.y() - p2.x()*p1.y(); // Add the current edge
		}
		return Math.abs(area);
	}

	/**
	 * This method returns the perimeter of the polygon.
	 * it is assumed that the polygon is simple (no self intersection).
	 * @return
	 */
	@Override
	public double perimeter() {
		double perimeter = 0; // Initialize result
		for (int i = 0; i < _points.size(); i++) { // Calculate value of shoelace formula
			Point2D p1 = _points.get(i); // Get the current point
			Point2D p2 = _points.get((i+1)%_points.size()); // Get the next point
			perimeter += p1.distance(p2); // Add the current edge
		}
		return perimeter;
	}

	/**
	 * This method move the polygon by the vector (dx,dy).
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void move(Point2D vec) {
		for (Point2D point : _points) {
			point.move(vec);
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public GeoShapeable copy() {
		// copy constructor
		return new Polygon2D(this);
	}

	/**
	 * This scale method scales the polygon by the factor s.
	 *
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point2D center, double ratio) {
		for (Point2D point : _points) {
			point.scale(center, ratio);
		}
	}

	/**
	 * This method rotates the polygon by the angle (in degrees).
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (Point2D point : _points) {
			point.rotate(center, angleDegrees);
		}
	}

	/**
	 * This method returns an array of the points of the polygon.
	 * @return
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[_points.size()];
		for (int i = 0; i < _points.size(); i++) {
			ans[i] = _points.get(i);
		}
		return ans;
	}

	@Override
	public String toString() {
		String ans = "";
		for (Point2D point : _points) {
			ans += point.toString() + ", ";
		}
		return ans;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Polygon2D polygon2D)) return false;

		return Objects.equals(_points, polygon2D._points);
	}
}
