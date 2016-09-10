/**
 *
 */
package app;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import data.DoubleObjects;
import data.GeneraicDataSet;
import data.IntegerLabel;

import tasks.RunnableTask;
import tasks.TaskController;

/**
 * @author TOSHIBA
 *
 */
public class RunTestConverter implements RunnableTask{
	private static transient final Logger logger = Logger.getLogger(RunTestConverter.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemSettings.StartApplication();

	    RunTestConverter r=new RunTestConverter();
		Thread th=new Thread(r);
		th.run();

	}
	@Override
	public void run() {
		// read all files from format
		SystemSettings.ReadSetting("set.txt");
		ArrayList<String> FileName = SystemSettings.ConvertFiles;
		GeneraicDataSet< DoubleObjects, IntegerLabel> DataSet=new GeneraicDataSet< DoubleObjects, IntegerLabel>(new DoubleObjects(), new IntegerLabel());
		for (int i = 0; i < FileName.size(); i++) {
			DataSet=new GeneraicDataSet< DoubleObjects, IntegerLabel>(new DoubleObjects(), new IntegerLabel());
			logger.info("  Reading file   number "+i+"   name = "+FileName.get(i));
			DataSet.setFormat(SystemSettings.DataFormatIN);

			DataSet.ReadFromFile(FileName.get(i));

			logger.info("  writing file   number "+i+"   name = "+FileName.get(i));
			DataSet.setFormat(SystemSettings.DataFormatOut);
			DataSet.SaveToFile(FileName.get(i)+DataSet.getFormatExtention()) ;

		}


		SystemSettings.saveSettings("set.txt");
		//convert them to the format

	}
	@Override
	public void update(Observable arg0, Object arg1) {


	}
	@Override
	public void addObserver(TaskController taskController) {


	}


}
