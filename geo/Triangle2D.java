package org.example.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
		_p3 = new Point2D(p3);
	}
	@Override
	public boolean contains(Point2D ot) {
		double totalArea = area();

		double area1 = new Triangle2D(_p1, _p2, ot).area();
		double area2 = new Triangle2D(_p1, _p3, ot).area();
		double area3 = new Triangle2D(_p2, _p3, ot).area();
		return (totalArea == area1 + area2 + area3);
	}

	/*		 p3
			 /\
			/  \
		   /    \
		  /      \
	   p1 ________p2
	 */

	@Override
	public double area() {
		// area of triangle based on 3 points
		// area = 1/2 * |(x1(y2-y3) + x2(y3-y1) + x3(y1-y2))|
		return 0.5 * Math.abs((_p1.x() * (_p2.y() - _p3.y())) + (_p2.x() * (_p3.y() - _p1.y())) + (_p3.x() * (_p1.y() - _p2.y())));
	}

	/**
	 * This method returns the perimeter of the triangle.
	 * @return the perimeter of the triangle.
	 */
	@Override
	public double perimeter() {
		return _p1.distance(_p2) + _p2.distance(_p3) + _p3.distance(_p1);
	}


	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] {_p1, _p2, _p3};
	}

	@Override
	public String toString() {
		return _p1 + "," + _p2 + "," + _p3;
	}

	/**
	 * This method is to check if the triangle is equilateral.
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Triangle2D t)) return false;
		return _p1.equals(t._p1) && _p2.equals(t._p2) && _p3.equals(t._p3);
	}
}
