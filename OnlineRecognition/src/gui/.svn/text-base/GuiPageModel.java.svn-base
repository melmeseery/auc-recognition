package gui;

import io.DHWhandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.apache.log4j.Logger;

import app.RunTestFileReaders;
import app.SystemSettings;

import data.GeneraicDataSet;
import data.IntegerLabel;
import data.SimpleInkObject;

public class GuiPageModel extends Observable {//implements ComboBoxModel

	private static transient final Logger logger = Logger.getLogger( GuiPageModel.class);

	StrokeGroupTableModel  strokeGroupData;
//
//	ArrayList<SimpleInkObject>  UnGrouptedData;
//
//
//	ArrayList<SimpleInkObject>  deletedData;


	ArrayList<SimpleInkObject> Strokes ;
	//ArrayList<IntegerLabel> Labels;
	// GeneraicDataSet<SimpleInkObject, IntegerLabel> dataset;

	private int PaperWdith;

	private int paperHeight;

	private String filenamePath;
	private String dir;
	private String filename;

	private DHWhandler dhw;
     private boolean Accepted=false;
     private boolean AddedToDataSet=false;

	 public GuiPageModel(){
		// create the stroke groups....
 	strokeGroupData=new StrokeGroupTableModel();


	 }



	public StrokeGroupTableModel getStrokeGroupData() {
		return strokeGroupData;
	}



	public void setStrokeGroupData(StrokeGroupTableModel strokeGroupData) {
		this.strokeGroupData = strokeGroupData;
	}



//	public ArrayList<SimpleInkObject> getDeletedData() {
//		return UnGrouptedData;
//	}
//
//
//
//	public void setDeletedData(ArrayList<SimpleInkObject> deletedData) {
//		UnGrouptedData = deletedData;
//	}



//	public ArrayList<Page> getPages() {
//		return pages;
//	}
//
//
//
//	public void setPages(ArrayList<Page> pages) {
//		this.pages = pages;
//	}



//	public GeneraicDataSet<SimpleInkObject, IntegerLabel> getDataset() {
//		return dataset;
//	}
//
//
//
//	public void setDataset(GeneraicDataSet<SimpleInkObject, IntegerLabel> dataset) {
//		this.dataset = dataset;
//	}

    public void getIntialLabels(){



    	if (Strokes==null){
    		return ;
    	}
    	else{
    		// adding the intial... labels....
    		//ArrayList<IntegerLabel>  Labels=new ArrayList<IntegerLabel>();
    		//  Labels=new ArrayList<IntegerLabel>();
    		GeneraicDataSet<SimpleInkObject, IntegerLabel> dataset=new GeneraicDataSet<SimpleInkObject, IntegerLabel>(new SimpleInkObject(), new IntegerLabel());


    	for (int i = 0; i < Strokes.size(); i++) {
    		logger.info("Stroke numbber "+i);

			SimpleInkObject s =  Strokes.get(i);

			IntegerLabel l =new IntegerLabel ();

			l=GetLabel(s);
			//Labels.add(l);
			dataset.addSample(Strokes.get(i), l);

		}

    	dataset.fixClassArray();

    	// now i  have a inital data by labels...

    	ArrayList<IntegerLabel> Labels = dataset.getClassValues();

    	StrokeGroup  tempGroup=new StrokeGroup();
    	for (int i = 0; i < Labels.size(); i++) {
    	    tempGroup=new StrokeGroup();
    		ArrayList<SimpleInkObject>  st=dataset.getDataByLabel(Labels.get(i));
    		tempGroup.setStrokes(st);
    		tempGroup.setId(Labels.get(i).getCat());
    		tempGroup.setC( getColor(i) );
    		tempGroup.setLabel(Labels.get(i).getCat()+"");

    		strokeGroupData.addStrokeGroup(tempGroup);
    		// now all strokes belogn to groups with bxo

		}


            /// all groups and strokes has labellssss.....

    	}
    	Strokes=null;
         notifyObservers();
    }
    private Color getColor(int i){

    	if(i==0){
    		return Color.black;
    	}
    	if (i==1){
    		return Color.blue;
    	}
    	  if (i==2){
    		return Color.cyan;
    	}
 	if (i==3){
    		return Color.DARK_GRAY;
    	}



	  if (i==4){
  		return Color.GRAY;
  	}
	if (i==5){
  		return Color.GREEN;
  	}


	  if (i==6){
  		return Color.MAGENTA;
  	}
	if (i==7){
  		return Color.ORANGE;
  	}



	if (i==8){
  		return Color.RED;
  	}


	if (i==9){
  		return Color.PINK;
  	}
	return Color.YELLOW;

    	//return Color.getHSBColor((float) (0.1f*Math.random()*i),0.5f, 0.5f);
    }


    private IntegerLabel GetLabel(SimpleInkObject s) {
		 IntegerLabel l=new IntegerLabel();
		 l.setCat(-1);
		 s.getBox();
		double max=s.getMaxY();
		double min=s.getMinY();

	//	logger.error("this is max in stroke is "+max+"  get min y "+min);
//		if (min> 1000 & max<1600){
//
//			l.setCat(0);
//
//		}
//		if (min > 1600 & max<2200){
//
//			l.setCat(9);
//
//		}
//		if (min>2200  & max < 2800){
//
//			l.setCat(8);
//
//		}
//
//		if (min>2800 & max < 3400) {
//
//			l.setCat(7);
//
//		}
//		if (min>3400 & max <3900){
//
//			l.setCat(6);
//
//		}
//
//
//		if (min>3900 & max<4500){
//
//			l.setCat(5);
//
//		}
//
//		if (min>4500 & max<5000){
//
//			l.setCat(4);
//
//		}
//
//		if (min>5000 & max<5600){
//
//			l.setCat(3);
//
//		}
//
//		if (min>5600 & max<6300){
//
//			l.setCat(2);
//
//		}
//		if (min>6300 & max<7500){
//
//			l.setCat(1);
//
//		}

		if ( max<=1800){

			l.setCat(0);

		}
		if ( max<=2300 & max>1800){

			l.setCat(9);

		}
		if ( max <= 2850 & max>2300){

			l.setCat(8);

		}

		if ( max <= 3500 & max > 2850  ) {

			l.setCat(7);

		}
		if ( max <=4000 & max > 3500){

			l.setCat(6);

		}


		if ( max<=4700 & max > 4000){

			l.setCat(5);

		}

		if ( max<=5300& max > 4700){

			l.setCat(4);

		}

		if (max<=5900  & max > 5300){

			l.setCat(3);

		}

		if ( max<=6500 & max > 5900){

			l.setCat(2);

		}
		if ( max<=7500 & max > 6500){

			l.setCat(1);

		}



		logger.error("this stroke is max in "+max+"  get min  y "+min + "  it set as category.... "+l.getCat());
		return l;
	}

	public void openDHWFile(String infile) {
		filenamePath = infile;
		seperateFileDir(filenamePath);
        openDHWFile();
//	 dhw=new DHWhandler();
//		dhw.readTheFile(infile);
//
//		Strokes = dhw.getStrokes();
//		PaperWdith=dhw.getPaperWidth();
//		paperHeight=dhw.getPaperHeight();

	}
	private void seperateFileDir(String f) {

		int i =f.lastIndexOf(SystemSettings.CurrentOSSeperator);
		if (i>0){
		dir=f.substring(0, i);
		filename=f.substring(i+1);
		}


	}



	public void openDHWFile(String dir, String file) {

		this.dir=dir;
        filename =  file;
        filenamePath=dir+file;

        openDHWFile();

//	    dhw=new DHWhandler();
//		dhw.readTheFile( filenamePath);
//
//		Strokes = dhw.getStrokes();
//		PaperWdith=dhw.getPaperWidth();
//		paperHeight=dhw.getPaperHeight();

	}


	public void paint(Graphics2D g, Dimension dimension) {
		double sx;
		double sy;

	  sx=(double)PaperWdith/(double)dimension.width;
	  sy=(double)paperHeight/(double)dimension.height;

	  logger.info("   I am scalling down with sx "+sx+"  sy = "+sy);
		//AffineTransform tx = new AffineTransform();

		//tx.scale(sx, sy);
          double h=dimension.height;
		 // draw all stroke in the data
		strokeGroupData.paint(g, sx, sy, h);
		//g.transform(tx);
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	@Override
	public String toString() {
		return " [ " + filename+ "]";

//		return "GuiPageModel [strokeGroupData=" + strokeGroupData
//				+ ", Strokes=" + Strokes + ", PaperWdith=" + PaperWdith
//				+ ", paperHeight=" + paperHeight + ", filename=" + filename
//				+ ", dhw=" + dhw + "]";
	}



	public void openDHWFile() {

		 dhw=new DHWhandler();
			dhw.readTheFile( filenamePath);

			Strokes = dhw.getStrokes();
			PaperWdith=dhw.getPaperWidth();
			paperHeight=dhw.getPaperHeight();
	}



	public void setAccepted(boolean selected) {
		this.Accepted=selected;

	}



	public void setAdded(boolean b) {
	  this.AddedToDataSet=b;

	}



//	@Override
//	public void addListDataListener(ListDataListener arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//	@Override
//	public Object getElementAt(int arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	@Override
//	public int getSize() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//
//	@Override
//	public void removeListDataListener(ListDataListener arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//	@Override
//	public Object getSelectedItem() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	@Override
//	public void setSelectedItem(Object arg0) {
//		// TODO Auto-generated method stub
//
//	}




}
