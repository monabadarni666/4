package org.example.geo;

import java.util.Comparator;

import org.example.Ex4_Const;
import org.example.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{

	/**
	 * These variables are used to determine the order of the comparison.
	 */
	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);

	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
	}


	/**
	 * This function compares between two GUI_Shapeable objects
	 * It returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
	 * @param o1 the first object to be compared.
	 * @param o2 the second object to be compared.
	 * @return
	 */
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;

		if(_flag == Ex4_Const.Sort_By_Tag){
			ans = o1.getTag() - o2.getTag();
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag){
			ans = o2.getTag() - o1.getTag();
		}
		if(_flag == Ex4_Const.Sort_By_Area){
			ans = (int) (o1.getShape().area() - o2.getShape().area());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area){
			ans = (int) (o2.getShape().area() - o1.getShape().area());
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter){
			ans = (int) (o1.getShape().perimeter() - o2.getShape().perimeter());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter){
			ans = (int) (o2.getShape().perimeter() - o1.getShape().perimeter());
		}
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_toString){
			ans = o2.toString().compareTo(o1.toString());
		}
		return ans;
	}

}
