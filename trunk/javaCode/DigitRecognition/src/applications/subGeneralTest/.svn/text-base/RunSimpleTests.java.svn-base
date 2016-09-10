/**
 * 
 */
package applications.subGeneralTest;

import gui.AppDefaults;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.ImageRecognizier;


import data.dataset.ArabicDataBaseConnector;
import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataBaseConnector;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;
import data.image.ImageBase;
import data.image.ImageReader;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import util.lib;

/**
 * @author TOSHIBA
 *
 */
public class RunSimpleTests  extends Observable implements RunnableTask {
	private static transient final Logger logger = Logger.getLogger(  RunFeatureDigitGeneration .class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
	DataBaseConnector.OS=DataBaseConnector.OS_LINUX;
		
	RunSimpleTests test=new RunSimpleTests();
		Thread th=new Thread(test);
		th.run();
	}
	public   void runTheDigitsFeature(int d1,int d2,int size, ArrayList<String> featuresNames){
		logger.info("inside the rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		
		digitsForTest.add(new Integer(d1));
		digitsForTest.add(new Integer(d2));
		Digit.loadAllFeatureArray();
	    ArrayList<String> feats = Digit.getComputedFeatures();
        lib.Save("SavingFeatuers.txt", feats);
        lib.SaveFeatureFile("features.txt", feats);
		Digit.setFeaturesToCompute(featuresNames);
		if (AppDefaults.dbType==DataBaseConnector.MNIST){
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();

		db.setStatus( DataBaseConnector.TRAIN);
		
	    db.getImageDisplayed(digitsForTest,size);
		}
		else {
		ArabicDataSetGenerator db=new ArabicDataSetGenerator();
		db.setStatus(DataBaseConnector.TRAIN);
		db.setArabicDB(AppDefaults.dbType);
		db.DisplayImages(digitsForTest,size);
		
		}
	
	}
	@Override
	public void addObserver(TaskController taskController) {
		
	}

	@Override
	public TaskSettings getSettings() {
		
		return null;
	}

	@Override
	public void setSettings(TaskSettings task) {
	
	}

	@Override
	public void run() { 
		// 
 
		AppDefaults.ReadSetting("set.txt");
//		convert();
//	compare();	
//		
          TestDigits();		
      	AppDefaults.saveSettings("set.txt");
		
	}
public void compare(){
	
	//read create the filrst data from file 
	
	ArrayList<ClassifierData> cs = ClassifierData.ReadClassifiersDetails(AppDefaults.classifiersFilename);
	if (cs.size()>0){
       ClassifierData data = cs.get(0);
       int trainMod;
		int testMod;
		if (AppDefaults.TrainMode==ImageRecognizier.TRAIN_VALIDATE){
			trainMod=DataBaseConnector.VALIDATE_TRAIN;
			testMod=DataBaseConnector.VALIDATE_TEST;
		}
		else {
			trainMod=DataBaseConnector.TRAIN;
			testMod=DataBaseConnector.TEST;
		}
		DataSet dataset = lib.LoadData(data,
				trainMod, AppDefaults.dbType,false);
		
		DataSet d2=new DataSet();
		d2.setFormat(AppDefaults.DataSetFormat);
		d2.ReadFromFile(AppDefaults.DefaultImageErrorDir);
		
		d2.compareDataSet(dataset);
		
	}	
	
}


	
	public void convert(){
		
		lib.convertFileFormat(AppDefaults.classifiersFilename, DataSet.FILE_INPUT_FORMAT_TORCH, DataSet.FILE_INPUT_FORMAT_ARFF);
		
		lib.convertFileFormat(AppDefaults.DefaultImageErrorDir, DataSet.FILE_INPUT_FORMAT_TORCH, DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
	}
	
	
	
	private void setComputedFeatures() {
		if (AppDefaults.ComputeOnlyArabic){
		 
		    ImageBase.setAddGeneral(false);
		    ImageBase.setAddArabic(true);
		    
		    ImageBase.setAddTransition(false);
		    ImageBase.setAddBlock(false);
		    ImageBase.setAddBorder(false);
		    ImageBase.setAddGabs(false);
            ImageBase.setAddSround(false);
            ImageBase.setAddPercent(false);
            ImageBase.setAddSudden(false);
            ImageBase.setAddWhite(false);
			ImageBase.setAddWide(false);
		}
		
	}
	public void TestDigits(){
		setComputedFeatures();
		runTheDigitsFeature(0,1,1,null);
		runTheDigitsFeature(3,9,1,null);
		runTheDigitsFeature(4,2,1,null);
		runTheDigitsFeature(6,7,1,null);	
		runTheDigitsFeature(5,8,1,null);
//		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		
	}
	

}
