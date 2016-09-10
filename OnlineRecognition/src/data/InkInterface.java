/**
 *
 */
package data;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * @author maha
 *
 */
public interface InkInterface  {

	// private Rectangle box = null;

	/**
	 * @return Returns the points.
	 * @uml.property name="points"
	 */
	public ArrayList<PointData> getPoints();

	public int getPointsCount();

	public void addPoint(PointData point);

	public void deletePoint(int index);

	public void setPoints(ArrayList<PointData> po);

	public PointData getPoint(int index);

	public void setPoints(int index, PointData point);

	public InkInterface createSubInkObject(int start, int end);

	public boolean canIntersect(InkInterface end);

//	public  ArrayList<PointData> IntersectionPoints(Line l);
//
//	public 	 ArrayList<Line> toLines();

	public 	 ArrayList< InkInterface > divideDirection();



	//public double getBox();

}
