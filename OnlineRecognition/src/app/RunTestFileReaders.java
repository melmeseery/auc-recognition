/**
 *
 */
package app;



import io.DHWhandler;
import io.MatHandler;
import io.StrokeToMat;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import data.GeneraicDataSet;
import data.IntegerLabel;
import data.SimpleInkObject;

import tasks.RunnableTask;
import tasks.TaskController;

/**
 * @author TOSHIBA
 *
 */
public class RunTestFileReaders implements RunnableTask{
	private static transient final Logger logger = Logger.getLogger(RunTestFileReaders.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemSettings.StartApplication();


		RunTestFileReaders r=new RunTestFileReaders();
		Thread th=new Thread(r);
		th.run();

	}


	public void SimpleTrial(){


		//----------get strokes from the dhw files
		String infile="PAGE_004.DHW";

		DHWhandler dhw=new DHWhandler();
		dhw.readTheFile(infile);
        ArrayList<SimpleInkObject> Strokes = dhw.getStrokes();
		logger.info(Strokes);

		//-----------create a mat files...




		////-------------------------Label the stroke and add to

		logger.info("start writing mat file..... ");
		StrokeToMat mat=new StrokeToMat();

		mat.addStroke(Strokes);
		mat.writeMatFile("strokes.mat");
		logger.info("finisheedeeeeeddd..... ");
//		 MatHandler mat=new MatHandler();
//
//
//		  mat.addDoubleMatrix("Stroke", Strokes.get(0).getPointsArray());
//		  mat.writeFile("Strokes.mat");




	}

	public void addingToDataSetTrail(){
		//----------get strokes from the dhw files
		String infile="PAGE_010.DHW";

		ArrayList<String>  files=new ArrayList<String>();
		files.add("PAGE_008.DHW");
		files.add("PAGE_008.DHW");
        files.add("PAGE_008.DHW");
        files.add("PAGE_008.DHW");
        files.add("PAGE_010.DHW");


		SystemSettings.DEBUG=false;
		  ArrayList<SimpleInkObject> AllStrokes=new ArrayList<SimpleInkObject>();
		for (int i = 0; i < files.size(); i++) {
        logger.info("  ---- now in file "+files.get(i));
		DHWhandler dhw=new DHWhandler();
		dhw.readTheFile(files.get(i));
        ArrayList<SimpleInkObject> Strokes = dhw.getStrokes();


		AllStrokes.addAll(Strokes);
		logger.info(Strokes.size()+"  total size of strokes is "+AllStrokes.size());
	}
		//-----------create a mat files...


		ArrayList<IntegerLabel> Labels=new ArrayList<IntegerLabel>();


		logger.info("there is  ......"+AllStrokes.size()+"  storkes...");
		for (int i = 0; i < AllStrokes.size(); i++) {
			logger.info("Stroke numbber "+i);

			SimpleInkObject s = AllStrokes.get(i);

			IntegerLabel l =new IntegerLabel ();

			l=GetLabel(s);
			Labels.add(l);
		}





		////-------------------------Label the stroke and add to data set...




		logger.info("Creating a data set....... ");

		GeneraicDataSet<SimpleInkObject,IntegerLabel >  dataSet=new GeneraicDataSet<SimpleInkObject, IntegerLabel>( new SimpleInkObject(), new IntegerLabel());


		for (int i = 0; i < AllStrokes.size(); i++) {

			dataSet.addSample(AllStrokes.get(i), Labels.get(i));

		}

		dataSet.setFormat(GeneraicDataSet.FILE_INPUT_FORMAT_MATLAB);
		dataSet.SaveToFile("data.mat");


		logger.info("finisheedeeeeeddd..... ");
//		 MatHandler mat=new MatHandler();
//
//
//		  mat.addDoubleMatrix("Stroke", Strokes.get(0).getPointsArray());
//		  mat.writeFile("Strokes.mat");




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

		if ( max<=1600){

			l.setCat(0);

		}
		if ( max<=2300 & max>1600){

			l.setCat(9);

		}
		if ( max <= 2850 & max>2300){

			l.setCat(8);

		}

		if ( max <= 3400 & max > 2850  ) {

			l.setCat(7);

		}
		if ( max <=4000 & max > 3400){

			l.setCat(6);

		}


		if ( max<=4500 & max > 4000){

			l.setCat(5);

		}

		if ( max<=5100& max > 4500){

			l.setCat(4);

		}

		if (max<=5700  & max > 5100){

			l.setCat(3);

		}

		if ( max<=6300 & max > 5700){

			l.setCat(2);

		}
		if ( max<=7500 & max > 6300){

			l.setCat(1);

		}


		logger.error("this stroke is max in "+max+"  get min  y "+min + "  it set as category.... "+l.getCat());
		return l;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		 addingToDataSetTrail();
	}

	@Override
	public void update(Observable arg0, Object arg1) {


	}

	@Override
	public void addObserver(TaskController taskController) {


	}

}
