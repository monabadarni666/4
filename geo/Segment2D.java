package org.example.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;

	public Segment2D(Point2D p1, Point2D p2) {
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
	}

	public Segment2D(Segment2D ot) {
		this(ot._p1, ot._p2);
	}

	@Override
	public boolean contains(Point2D ot) {
		if (ot == null)
			return false;
		if (ot.equals(_p1) || ot.equals(_p2))
			return true;
		if (ot.x() < Math.min(_p1.x(), _p2.x()) || ot.x() > Math.max(_p1.x(), _p2.x()))
			return false;
		if (ot.y() < Math.min(_p1.y(), _p2.y()) || ot.y() > Math.max(_p1.y(), _p2.y()))
			return false;
		if (Math.abs(_p1.distance(_p2) - _p1.distance(ot) - _p2.distance(ot)) < 0.01)
			return true;

		return false;
	}

	//    ----------ot-----------------
	@Override
	public double area() {
		// segment area is 0
		return 0;
	}

	@Override
	public double perimeter() {
		return _p1.distance(_p2);
	}

	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(this);
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
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[]{_p1, _p2};
	}
	
}