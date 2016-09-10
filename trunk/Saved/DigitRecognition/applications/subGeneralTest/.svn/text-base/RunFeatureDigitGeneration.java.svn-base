/**
 * 
 */
package applications.subGeneralTest;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;


/**
 * @author TOSHIBA
 *
 */
public class RunFeatureDigitGeneration extends Observable implements RunnableTask {
	
	private static transient final Logger logger = Logger.getLogger(  RunFeatureDigitGeneration .class);
	//need to have data set connector 
	//MNISTDataSetGenerator  db;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		
		RunFeatureDigitGeneration test=new RunFeatureDigitGeneration();
		 DataBaseConnector.OS=DataBaseConnector.OS_LINUX;
		Thread th=new Thread(test);
		th.run();
		

	}
//	public void runTheDigitsFeatureAllDigits(int format, ArrayList<String> featuresNames,String datasetfilename){
//		logger.info("inside the rundigitfeature in main ");
//		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
//		for (int i = 0; i < 10; i++) {
//			digitsForTest.add(new Integer(i));
//		}
//		
//		//digitsForTest.add(new Integer(d2));
//		Digit.setFeaturesToCompute(featuresNames);
//		Digit.loadAllFeatureArray();
//		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
//		db.setStatus( DataBaseConnector.TRAIN);
//		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
//		dataset.ShuffleDataSet();
//	//dataset.setFeaturesToUse(featuresNames);
//	dataset.setFormat(format);
//	dataset.SaveToFile( datasetfilename);
//		
//		
//	}
	public   void runTheDigitsFeature(int d1,int d2, int format, ArrayList<String> featuresNames,String datasetfilename){
		logger.info("inside the rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		
		digitsForTest.add(new Integer(d1));
		digitsForTest.add(new Integer(d2));
		Digit.setFeaturesToCompute(featuresNames);
		Digit.loadAllFeatureArray();
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus( DataBaseConnector.TRAIN);
		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
	//dataset.setFeaturesToUse(featuresNames);
	dataset.setFormat(format);
	dataset.SaveToFile( datasetfilename);
	
	}


		
		
		/* (non-Javadoc)
		 * @see tasks.RunnableTask#addObserver(tasks.TaskController)
		 */
		@Override
		public void addObserver(TaskController taskController) {
		 
		 this.addObserver(taskController);

		}

		/* (non-Javadoc)
		 * @see tasks.RunnableTask#getSettings()
		 */
		@Override
		public TaskSettings getSettings() {
	 
			return null;
		}

		/* (non-Javadoc)
		 * @see tasks.RunnableTask#setSettings(tasks.TaskSettings)
		 */
		@Override
		public void setSettings(TaskSettings task) {
		 

		}

		public void createSingleClassifierFiles(){
			 //this.runTheDigitsFeature(0,1 , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features01.txt");
			 for (int i = 0; i < 10; i++) {
				 for (int j = i+1; j < 10; j++) {
					 this.runTheDigitsFeature(i,j , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features"+i+j+".txt");
				 }
			}
		
			
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			
			createSingleClassifierFiles();
			//this.runTheDigitsFeatureAllDigits( DataSet.FILE_INPUT_FORMAT_ARFF, null, "FeaturesDigits.txt");
		}

		/* (non-Javadoc)
		 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
		 */
		@Override
		public void update(Observable o, Object arg) {
			

		}

	

	}

	



