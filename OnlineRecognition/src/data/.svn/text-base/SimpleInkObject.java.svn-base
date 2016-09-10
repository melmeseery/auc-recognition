package data;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import lib.ComputationsGeometry;

import org.apache.log4j.Logger;

import app.SystemSettings;


/**
 * @author melmeseery
 *
 */
public class SimpleInkObject extends DataObject implements Serializable,InkInterface  {
	/**
	 *
	 */
	private static final long serialVersionUID = 9113296089440810093L;
	/**
	 * Logger for this class
	 */

	private static final double DivideStrokePercent = SystemSettings.DivideStrokePercent;
	private static final Logger logger = Logger.getLogger(SimpleInkObject.class);

	/**
	 * @uml.property name="points"
	 */
	protected ArrayList<PointData> points = null;
	protected boolean closed=false,open=true;
	private Rectangle2D box;
	private boolean box_valid=false;
	double length;
	boolean lengthComputed=false;
	private boolean sumsComputed=false;
	// CurveFitData  sums;
		private double rotation;
		boolean rotationComputed=false;
		double revolution;
		double minX,minY, maxY,maxX;
	public double getMinX() {
			return minX;
		}

		public double getMinY() {
			return minY;
		}

		public double getMaxY() {
			return maxY;
		}

		public double getMaxX() {
			return maxX;
		}

	/**
	 * @return the closed
	 */
	public boolean isClosed() {
		return closed;
	}
	protected double getBoxArea() {
		return getBox().getHeight()*getBox().getWidth();
		//return 0;
	}
	/**
	 * @param closed the closed to set
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 *
	 */
	public SimpleInkObject() {

	 points=new ArrayList<PointData>();
	}

	public ArrayList<PointData> getPoints() {
		return points;
	}

	/**
	 * getPointsCount
	 */
	public int getPointsCount() {
		int returnint = points.size();
		return returnint;
	}

	public void addPoint(PointData point) {
		// / adding point to the array
		points.add(point);
		box_valid=false;
	}

	public void paint(Graphics2D g ) {
		//
		if (points != null)
			for (int i = 0; i < points.size() - 1; i++) {

				PointData p=points.get(i);
				int x=(int) p.getX();
				int y=(int) p.getY();

				PointData p1=points.get(i+1);
				int x1=(int) p1.getX();
				int y1=(int) p1.getY();

				g.drawLine(x,y,x1,y1);

				g.drawRect(x, y, 2, 2);
				g.fillRect(x, y, 2, 2);

				g.drawRect(x1, y1, 2, 2);
				g.fillRect(x1, y1, 2, 2);
			}// for
	}

	public InkInterface createSubInkObject(int start, int end) {
		//
		//
		//
		InkInterface ink = new SimpleInkObject();
		ArrayList<PointData> po = new ArrayList<PointData>();
		if (this.points != null) {
			for (int i = start; (i < this.points.size()) && (i < end); i++) {
				po.add(this.points.get(i));
			}

		}
		ink.setPoints(po);
		// return a segment from this stroke that will contain the points of the
		// stroke.
		return ink;
		//
	}

	public void deletePoint(int index) {
		this.points.remove(index);
	}

	public PointData getPoint(int index) {
		if (points.size()>index && points.size()>0)
		{
			PointData returnPointData = (PointData) points.get(index);

		return returnPointData;
		}
		else return null;
	}

	public void setPoints(int index, PointData point) {
		this.points.set(index, point);
		box_valid=false;
	}

	/**
	 * @param points
	 *            the points to set
	 * @uml.property name="points"
	 */
	public void setPoints(ArrayList<PointData> points) {
		this.points = points;
		box_valid=false;
	}

	public void setParam(ArrayList Param) {
		//

	}

	public Rectangle2D getBox() {
		if (box_valid)
			return box;
		else {
		if (points!=null){
			if (points.size()>0){
			PointData point = points.get(0);
			box = new Rectangle2D.Double(point.getPointLocation().getX(), point
					.getPointLocation().getY(), 0, 0);
			for (int i = 1; i < points.size() - 1; i++) {

				box.add(points.get(i).getPointLocation());
				}
			box_valid=true;
			minX=box.getMinX();
			minY=box.getMinY();
			maxX=box.getMaxX();
			maxY=box.getMaxY();


			return box;
			}
		}
			Rectangle2D returnRectangle2D = box = new Rectangle2D.Double(0, 0, 0, 0);
		return 	returnRectangle2D;
		}
	}
    public void transform (AffineTransform at) {

		if (logger.isDebugEnabled()) {
			//  logger.debug("transform(AffineTransform) -   transform using the tx  (" + this.getClass().getSimpleName() + "    " + (new Throwable()).getStackTrace()[0].getLineNumber() + "  )  "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
        int n =points.size()*2;
        double[] coords = new double[n];
        int k=0;
        for(int i=0; i<points.size(); i++){
            coords[k++]=points.get(i).getX();
            coords[k++]=points.get(i).getY();
        }
        at.transform(coords,0,coords,0,points.size());
        k=0;
        for(int i=0; i<points.size(); i++){
        	double x=coords[k++];
        	double y=coords[k++];
        	points.get(i).setPointLocation(new Point2D.Double(x,y));
//            [i]=coords[k++];
//            _yvals[i]=;
        }
    }
    public double getLength() {
		if (lengthComputed)
			return length;
		else {
			if (points!=null)
			{
				length=0;
				for (int i = 0; i < points.size()-1; i++) {
					length+=points.get(i).distance(points.get(i+1));
				}
				lengthComputed=true;
				return length;
			}


		return 0;
		}
	}
    /**
     * Translate the polyline with the given distance.
     */
    public void translate (double x, double y) {
        for (int i=0; i<points.size(); i++) {
        	double xp=points.get(i).getX()+x;
        	double yp=points.get(i).getY()+y;

        	points.get(i).setPointLocation(new Point2D.Double(xp,yp));


        }
    }

	public boolean canIntersect(InkInterface end) {
		if (end instanceof SimpleInkObject) {
			SimpleInkObject new_e = (SimpleInkObject) end;
			 return this.getBox().intersects(new_e.getBox() );

		}
		else {
			// get the box for end
			if (end.getPoints().size()>0){
				PointData point = end.getPoints().get(0);
				Double boxe = new Rectangle2D.Double(point.getPointLocation().getX(), point
						.getPointLocation().getY(), 0, 0);
				for (int i = 1; i < end.getPoints().size() - 1; i++) {

					boxe.add(end.getPoints().get(i).getPointLocation());
					}
				return getBox().intersects(boxe);

			}


		}
		return false;
	}

	public double TotalRotation(){
		if(!rotationComputed){
			rotation=0;
		ArrayList<PointData> points;
		if (getPoints() != null) {
        double rot;
			points =  getPoints();
			if (points.size() > 3) {
			 rotation = 0.0;
				for (int i = 0; i < points.size() - 2; i++) {
					PointData p1 = points.get(i);
					PointData p2 = points.get(i + 1);
					PointData p3 = points.get(i + 2);
					rot= ComputationsGeometry
					.computeChangeRotation(p1, p2, p3);
					rotation +=  ComputationsGeometry
							.computeChangeRotation(p1, p2, p3);

				}

	}
		}
		revolution=rotation/(2.0*Math.PI);
		rotationComputed=true;
		}



return rotation;
}

    public double[] getPointsYArray(){



    	return getPointsArray()[1];


    }

    public double[] getPointsXArray(){

    	return getPointsArray()[0];

    }
    public double[][] getPointsArray(){

    	double[] y=new double[getPoints().size()];
    	double[] x=new double[getPoints().size()];
    	for (int i = 0; i < getPoints().size(); i++) {

    		y[i]=getPoints().get(i).getY();
    		x[i]=getPoints().get(i).getX();

		}

    	double[][] xy=new double [2][];

    	xy[0]=x;
    	xy[1]=y;

    	return xy;
    }

	public ArrayList<InkInterface> divideDirection() {

		return null;
	}

	@Override
	public double[][] getDataArray() {
		return this.getPointsArray();
		//return null;
	}
	public Object clone(){
		try {
			SimpleInkObject clone=(SimpleInkObject)super.clone();
			clone.points=(ArrayList<PointData>) points.clone();

			for (int i = 0; i < clone.points.size(); i++) {
				clone.points.set(i, (PointData) points.get(i).clone());
			}

			clone.box=(Rectangle2D) box.clone();


				return clone;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // make the shallow copy of the object of type Department


		return null;



	}

	public void drawStroke(Graphics graphics) {


	}

	@Override
	DataObject ReadObjectTxt(Scanner input, int NumOfFeatures) {
		SimpleInkObject d=new SimpleInkObject();

		for (int i = 0; (i < (NumOfFeatures/2.0)) && input.hasNextDouble(); i++) {
					PointData p=new PointData();
					p.x=input.nextDouble();
                   p.y=input.nextDouble();
			      d.addPoint(p );
				 }


			return d;

	}

	@Override
	void write(PrintStream out) {


		for (int j = 0; j < points.size(); j++) {



			out.print(points.get(j).x);

			// }

			out.print(",");
			out.print("  ");
			out.print(points.get(j).y);

			out.print(",");
			out.print("  ");

		}
	}

	public void paint(Graphics2D g, double sx, double sy, double h) {
		logger.info(" painting the strokess.....................");
		if (points != null)
			for (int i = 0; i < points.size() - 1; i++) {

				PointData p=points.get(i);
				int x=(int) (p.getX()/sx);
				int y=(int) (h-(p.getY()/sy));

				PointData p1=points.get(i+1);
				int x1=(int) (p1.getX()/sx);
				int y1=(int) (h-( p1.getY()/sy));

				g.drawLine(x,y,x1,y1);

				g.drawRect(x, y, 2, 2);
				g.fillRect(x, y, 2, 2);

				g.drawRect(x1, y1, 2, 2);
				g.fillRect(x1, y1, 2, 2);
			}// for

	}


}
