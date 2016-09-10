/**
 *
 */
package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import data.IntegerLabel;
import data.SimpleInkObject;

/**
 * @author TOSHIBA
 *
 */
public class StrokeGroup  {

	Integer id;
	ArrayList<SimpleInkObject>  strokes;
	Color c=Color.blue;
	private Rectangle2D box;
	Boolean accepted=new Boolean(false);
	String  Label;
	IntegerLabel lab=null;
	boolean validLabel=false;

	private boolean validBox;
	StrokeGroup(){
		 strokes=new ArrayList<SimpleInkObject>();
		 Label="";
		 id=-1;
//		 lab=new IntegerLabel();
//		 lab.setCat(id);
	}

	public void addStroke (SimpleInkObject s){
		strokes.add(s);

		validBox=false;

	}
	public void removeStroke(SimpleInkObject s){

		strokes.remove(s);

		validBox=false;

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		validLabel=false;
	}
	public ArrayList<SimpleInkObject> getStrokes() {
		return strokes;
	}
	public void setStrokes(ArrayList<SimpleInkObject> strokes) {
		this.strokes = strokes;
		validBox=false;
		accepted=false;
		validLabel=false;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public Rectangle2D getBox() {
		if(validBox)
		return box;

		else{
			box=strokes.get(0).getBox();
			if (strokes!=null)
			{
			for (int i = 0; i < strokes.size(); i++) {
			  box.add( strokes.get(i).getBox());
			}
              validBox=true;
			}

			  validBox=true;
			return box;


		}
	}
//	public void setBox(Rectangle2D box) {
//		this.box = box;
//	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	 public   void paint(Graphics2D g, double scalex, double scaley, double h){
		 	Color temp=g.getColor();
	    	g.setColor(c);

	    	DrawingDebugUtils.drawRectangle(g, box,scalex,scaley,h);
	    	//g.drawRect(box.getX(), arg1, arg2, arg3)


		 for (int i = 0; i < strokes.size(); i++) {
			 strokes.get(i).paint(g,scalex,scaley, h);

		}

	    	g.setColor(temp);

		 //list.get(i)


	 }
	public String getLabel() {

		return Label;
	}
	public void setLabel(String label) {

		Label = label;
		validLabel=false;
//		lab=new IntegerLabel();
//		lab.setIndex(id);
//		lab.setCat(Integer.parseInt(Label));
//		lab.setIntValue( lab.getCat());


	}
	public IntegerLabel getIntegerLabel(){
		if (!validLabel){

			lab=new IntegerLabel();
			lab.setIndex(id);
			lab.setCat(Integer.parseInt(Label));
			lab.setIntValue( lab.getCat());
			validLabel=true;
		}
		return lab;
	}

	public boolean SameLabel(StrokeGroup strokeGroup) {
		 return this.getLabel().equals(strokeGroup.getLabel());
		//return false;
	}

	public void merge(StrokeGroup cal) {

		 strokes.addAll(cal.getStrokes());
			validBox=false;
			accepted=false;
	}


}
