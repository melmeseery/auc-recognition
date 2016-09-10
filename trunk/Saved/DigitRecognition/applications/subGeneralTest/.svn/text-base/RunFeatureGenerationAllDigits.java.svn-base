/**
 * 
 */
package applications.subGeneralTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import data.dataset.ArabicDataBaseConnector;
import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;
import data.image.FeaturedRegion;
import data.image.RegionCreater;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;


/**
 * @author TOSHIBA
 *
 */
public class RunFeatureGenerationAllDigits extends Observable implements RunnableTask {
	
	private static transient final Logger logger = Logger.getLogger(  RunFeatureGenerationAllDigits .class);
	private static final String OutDir = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\Comb\\";
	private static final int BasicFeature = 2;
	//need to have data set connector 
	//MNISTDataSetGenerator  db;
	private static int DataBaseType=DataBaseConnector.MADBASE; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		
		
		RunFeatureGenerationAllDigits test=new RunFeatureGenerationAllDigits();
		 DataBaseConnector.OS=DataBaseConnector.OS_WINDOWS;
		Thread th=new Thread(test);
		th.run();
		

	}
	public void runTheRegionFeatureAllDigits(int format, ArrayList<ArrayList <String>> featArrs,ArrayList<RegionCreater>  regions,String datasetfilename){
		logger.info("  inside the Region  rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		for (int i = 0; i < 10; i++) {
			digitsForTest.add(new Integer(i));
		}
		
		//digitsForTest.add(new Integer(d2));
		FeaturedRegion.setFeaturesToCompute(null);
		 ;
		 FeaturedRegion.loadAllFeatureArray();
		writeTofile(OutDir+"feat.txt",	FeaturedRegion.FeatureString());
		//Digit.DisplayFeatureString();
		//ArrayList<ArrayList <String>> featArrs=null;
		//ArrayList<RegionCreater>  regions=null;
		DataSet dataset;
		if ( DataBaseType==DataBaseConnector.MNIST){
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus(DataBaseConnector.TRAIN);
		 dataset = db.GetDataSetByDigits(digitsForTest,regions,featArrs);
		}
		else {
			
			
			ArabicDataSetGenerator db=new ArabicDataSetGenerator();
			db.setArabicDB(ArabicDataBaseConnector.MADBASE);
			//ArabicDataBaseConnector db=new ArabicDataBaseConnector();
			db.setStatus(DataBaseConnector.VALIDATE_TRAIN);
			 dataset = db.GetDataSetByDigits(digitsForTest,regions,featArrs);
			
		}
//		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
//		db.setStatus( DataBaseConnector.TRAIN);
//		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
//		dataset.ShuffleDataSet();
	//dataset.setFeaturesToUse(featuresNames);
		
		
	dataset.setFormat(format);
	dataset.SaveToFile( datasetfilename);
	dataset.getAllFeatureString();
	
	
	// now write the settings to the file 
	
	
	saveSettings(datasetfilename, dataset.getAllFeatureString(), dataset.getFeatureCount(), OutDir+"settingData.txt");
	
	
	
		
	}
	private void writeTofile(String filename, String featureString) {
		   try {
	    	   FileWriter outFile = new FileWriter(filename);
	    	   PrintWriter out = new PrintWriter(outFile);
	    	     out.println(featureString);
	    	       out.close();
	   		    
		    } catch (IOException e) {
				 
				e.printStackTrace();
			}
		  logger.info("Your file has been written");   
	}
	public void saveSettings(String datastore, String features, int numberOfFeatures, String StoreFile){
		 
		 
		    try {
		    	   FileWriter outFile = new FileWriter(StoreFile);
		    	   PrintWriter out = new PrintWriter(outFile);
		    	               
		    	               // Also could be written as follows on one line
		    	             // Printwriter out = new PrintWriter(new FileWriter(args[0]));
		    	       
		    	             // Write text to file
		    	             out.println(datastore);
		    	              out.println(features);
		    	              out.print(numberOfFeatures);
		    	             //out.println("this is line 3 part 2");
		    	            out.close();
		    
		    } catch (IOException e) {
				 
				e.printStackTrace();
			}
		  logger.info("Your file has been written");   
	}
	
	public void runTheDigitsFeatureAllDigits(int format, ArrayList<String> featuresNames,String datasetfilename){
		logger.info("inside the rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		for (int i = 0; i < 10; i++) {
			digitsForTest.add(new Integer(i));
		}
		FeaturedRegion.setFeaturesToCompute(featuresNames);
		//digitsForTest.add(new Integer(d2));
		FeaturedRegion.loadAllFeatureArray();
		//Digit.setFeaturesToCompute(featuresNames);
		//Digit.loadAllFeatureArray();
		//Digit.DisplayFeatureString();
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus( DataBaseConnector.TRAIN);
		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
		dataset.ShuffleDataSet();
	//dataset.setFeaturesToUse(featuresNames);
	dataset.setFormat(format);
	dataset.SaveToFile( datasetfilename);
	Digit.DisplayFeatureString();
		
		
	}
//	public   void runTheDigitsFeature(int d1,int d2, int format, ArrayList<String> featuresNames,String datasetfilename){
//		logger.info("inside the rundigitfeature in main ");
//		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
//		
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
//		Digit.setFeaturesToCompute(featuresNames);
//		Digit.loadAllFeatureArray();
//		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
//		db.setStatus( DataBaseConnector.TRAIN);
//		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
//	//dataset.setFeaturesToUse(featuresNames);
//	dataset.setFormat(format);
//	dataset.SaveToFile( datasetfilename);
//	
//	}


		
		
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

//		public void createSingleClassifierFiles(){
//			 //this.runTheDigitsFeature(0,1 , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features01.txt");
//			 for (int i = 0; i < 10; i++) {
//				 for (int j = i+1; j < 10; j++) {
//					 this.runTheDigitsFeature(i,j , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features"+i+j+".txt");
//				 }
//			}
//		
//			
//		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			RunTheRegionFeaturs();
		//	createClassifierFiles();
			//this.
			 //runTheDigitsFeatureAllDigits
		}

		
		private void RunTheRegionFeaturs(){
//			ArrayList<ArrayList <String>> featArrs=new ArrayList<ArrayList<String>>();
//			ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
			
			ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
			ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
			
			
			int MaxH=2;
			int MaxV=2;
			ArrayList<String> AllFeatures2;
			
			MaxH=1;
		     MaxV=1;
			for (int i = 0; i < MaxH; i++) {
				for (int j = 0; j < MaxV; j++) {
					AllFeatures2 = getGeneralFeatures(BasicFeature);
					feat.add(AllFeatures2);
					regions.add(getRegion(MaxH, MaxV, i, j));
				}
					
			}
			MaxH=1;
		     MaxV=2;
			for (int i = 0; i < MaxH; i++) {
				for (int j = 0; j < MaxV; j++) {
					AllFeatures2 = getGeneralFeatures(BasicFeature);
					feat.add(AllFeatures2);
					regions.add(getRegion(MaxH, MaxV, i, j));
				}
					
			}
			
			
			MaxH=2;
		     MaxV=1;
			for (int i = 0; i < MaxH; i++) {
				for (int j = 0; j < MaxV; j++) {
					AllFeatures2 = getGeneralFeatures(BasicFeature);
					feat.add(AllFeatures2);
					regions.add(getRegion(MaxH, MaxV, i, j));
				}
					
			}
			runTheRegionFeatureAllDigits( DataSet.FILE_INPUT_FORMAT_ARFF, feat,regions, OutDir+"FeaturesDigitAllRegion_1221.txt");
			
		}
		private void createClassifierFiles() {
			
			runTheDigitsFeatureAllDigits( DataSet.FILE_INPUT_FORMAT_ARFF, null, OutDir+"FeaturesDigitAllRegion.txt");
		}
		/* (non-Javadoc)
		 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
		 */
		@Override
		public void update(Observable o, Object arg) {
			

		}
		private RegionCreater getRegion(int maxh, int maxv, int h, int v) {
			RegionCreater reg = new RegionCreater();
			reg.setMaxHorizontalRegion(maxh);
			reg.setMaxVerticalRegion(maxv);
			reg.setHorizonal(h);
			reg.setVertical(v);
			return reg;
		}
	
		private ArrayList<String> getGeneralFeatures(int type) {
			ArrayList<String> AllFeatures2 = new ArrayList<String>();

			switch (type) {
			case 1:
				AllFeatures2.add("frup");
				AllFeatures2.add("frdown");
				AllFeatures2.add("frleft");
				AllFeatures2.add("frright");
				//AllFeatures2.add("pb");
				
				break;
			case 2:
				 AllFeatures2=null;
				
				//AllFeatures2.add("frup");
				//AllFeatures2.add("frdown");
				//AllFeatures2.add("pb");
				break;
			case 3:
				break;
			case 4:
				break;

			default:
				break;
			}

			return AllFeatures2;

		}
	}

	



