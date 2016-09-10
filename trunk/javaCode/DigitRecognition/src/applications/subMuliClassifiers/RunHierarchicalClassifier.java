/**
 * 
 */
package applications.subMuliClassifiers;

import gui.AppDefaults;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.HierarchicalClassifier;
import classifiers.MultiFeature.ImageRecognizier;
import classifiers.MultiFeature.MultiClassFeatureRecognizier;
import classifiers.results.ClassifierResult;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.image.FeaturedRegion;
import data.image.ImageBase;
import data.image.RegionCreater;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import util.lib;

/**
 * @author TOSHIBA
 *
 */
public class RunHierarchicalClassifier implements RunnableTask {

	/**
	 * 
	 */
	private static transient final Logger logger = Logger.getLogger(RunHierarchicalClassifier.class);

	public RunHierarchicalClassifier() {
	
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#addObserver(tasks.TaskController)
	 */
	@Override
	public void addObserver(TaskController taskController) {
	

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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		AppDefaults.ReadSetting(AppDefaults.settingFilename);
	     setComputedFeatures();
		
		if (AppDefaults.Mode==AppDefaults.MODE_TRAIN_Validate)
		{
			RunSingleClassifierDetails();
		}
		
		else{
			RunTaskDetails();
		}
		
		AppDefaults.saveSettings(AppDefaults.settingFilename);


	}

	private void setComputedFeatures() {
		if (AppDefaults.ComputeOnlyArabic){
		 
		    ImageBase.setAddGeneral(true);
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

	
	private void RunTaskDetailsOVOO() {
		HierarchicalClassifier classifier = new HierarchicalClassifier();

	

		String str = "";
		String newline = System.getProperty("line.separator");

		 
		int k = 0;
		ArrayList<ClassifierData> dataArray = new ArrayList<ClassifierData>();
 
//		double ErrorsArray[][]=new double [1][ dataArray.size()];
//				String [] classifierNames=new String[dataArray.size()];
//		String [] FeaturesNames=new String[dataArray.size()];
		dataArray=ClassifierData.ReadClassifiersDetails(AppDefaults.classifiersFilename);

		for (int i = 0; i < dataArray.size(); i++) {
			classifier.addClassifierData(dataArray.get(i),dataArray.get(i).HierarchyKey);
		}
	 
		logger.info("-----------------------finihsed the settings now train and tst ");
		classifier.setOptions(AppDefaults.TrainMode,AppDefaults.classifierType, AppDefaults.dbType);
	
		classifier.startTrainid = AppDefaults.startTrainid;
		classifier.Interupt = AppDefaults.Interupt;
	 
		if (AppDefaults.Mode!=AppDefaults.MODE_TEST_ONLY){
			classifier.TrainSystem();
		}
		if (AppDefaults.Mode==AppDefaults.MODE_TEST_ONLY||AppDefaults.Mode==AppDefaults.MODE_TRAIN_TEST){
		classifier.TestSystemHierarchial();
		}
		logger.info(classifier.getResult());
		logger
				.info("@###################################################################333");
		logger.info(str);
		classifier.saveResult(AppDefaults.classifiersFilename);
 
       // 
		ClassifierData.SaveClassifiersDetails(dataArray, AppDefaults.classifiersFilename,false);
		ClassifierData.SaveClassifiersSummary(dataArray, AppDefaults.classifiersFilename, false, true);
	 
//		int oldtrail=TrialNo;
//		TrialNo++;
//		int newTrail=TrialNo;
		String SortedFilename= AppDefaults.classifiersFilename+"_Sorted";

		
		ClassifierData.SaveClassifiersDetails(dataArray,SortedFilename,AppDefaults.sort);
		ClassifierData.SaveClassifiersSummary(dataArray, SortedFilename, AppDefaults.sort, true);
		
  //	saveSettings(settingFilename);
		logger
				.info("##################################################################");
		logger.info(str);
		
	}
	
	
	private void RunTaskDetails() {
		HierarchicalClassifier classifier = new HierarchicalClassifier();

	

		String str = "";
		String newline = System.getProperty("line.separator");

		 
		int k = 0;
		ArrayList<ClassifierData> dataArray = new ArrayList<ClassifierData>();
 
//		double ErrorsArray[][]=new double [1][ dataArray.size()];
//				String [] classifierNames=new String[dataArray.size()];
//		String [] FeaturesNames=new String[dataArray.size()];
		dataArray=ClassifierData.ReadClassifiersDetails(AppDefaults.classifiersFilename);

		for (int i = 0; i < dataArray.size(); i++) {
			classifier.addClassifierData(dataArray.get(i),dataArray.get(i).HierarchyKey);
		}
	 
		logger.info("-----------------------finihsed the settings now train and tst ");
		classifier.setOptions(AppDefaults.TrainMode,AppDefaults.classifierType, AppDefaults.dbType);
	
		classifier.startTrainid = AppDefaults.startTrainid;
		classifier.Interupt = AppDefaults.Interupt;
	 
		if (AppDefaults.Mode!=AppDefaults.MODE_TEST_ONLY){
			classifier.TrainSystem();
		}
		if (AppDefaults.Mode==AppDefaults.MODE_TEST_ONLY||AppDefaults.Mode==AppDefaults.MODE_TRAIN_TEST){
		classifier.TestSystemHierarchial();
		}
		logger.info(classifier.getResult());
		logger
				.info("@###################################################################333");
		logger.info(str);
		classifier.saveResult(AppDefaults.classifiersFilename);
 
       // 
		ClassifierData.SaveClassifiersDetails(dataArray, AppDefaults.classifiersFilename,false);
		ClassifierData.SaveClassifiersSummary(dataArray, AppDefaults.classifiersFilename, false, true);
	 
//		int oldtrail=TrialNo;
//		TrialNo++;
//		int newTrail=TrialNo;
		String SortedFilename= AppDefaults.classifiersFilename+"_Sorted";

		
		ClassifierData.SaveClassifiersDetails(dataArray,SortedFilename,AppDefaults.sort);
		ClassifierData.SaveClassifiersSummary(dataArray, SortedFilename, AppDefaults.sort, true);
		
  //	saveSettings(settingFilename);
		logger
				.info("##################################################################");
		logger.info(str);
		
	}

	private void RunSingleClassifierDetails() {
		
		// create the ir recognizer 
		ImageRecognizier IR = new ImageRecognizier();
		FeaturedRegion.setFeaturesToCompute(null);

		ArrayList<ArrayList<String>> featArrs = null;
		ArrayList<RegionCreater> regions = null;

	

		String str = "";
		String newline = System.getProperty("line.separator");
		str += newline;

		int k = 0;
		ArrayList<ClassifierData> dataArray = new ArrayList<ClassifierData>();
		dataArray=ClassifierData.ReadClassifiersDetails(AppDefaults.classifiersFilename);
		double ErrorsArray[][]=new double [1][ dataArray.size()];
		String [] classifierNames=new String[dataArray.size()];
		String [] FeaturesNames=new String[dataArray.size()];
		for (int i = 0; i < classifierNames.length; i++) {
			classifierNames[i]=dataArray.get(i).toString();
			FeaturesNames[i]=dataArray.get(i).FeautureSummary;
			 ErrorsArray[0][i]=0;
		}
  
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
		
		for (int i = AppDefaults.startTrainid; i < dataArray.size() && i< AppDefaults.Interupt; i++) {
				ClassifierData data = dataArray.get(i);
			logger.info("  now working on classifier number "+i+"   "+data);
			//create it again to init the data and all settings  
			IR = new ImageRecognizier();
			IR.setMainOptions(AppDefaults.TrainMode,AppDefaults.classifierType);
			logger
			.info("##################################################################");
			
		
			logger.info(" Now testing  " + data.digit	+ "  VS    digit c2 " + data.digitC2);
			DataSet dataset = lib.LoadData(data,
					trainMod, AppDefaults.dbType,false);
			data.NumberTrainSamples=dataset.getNumOfSamples();
			
			if (data.useSplit){
			dataset = dataset.GenerateSplitClassesDataSet(data.digit,data.digitC2);
			}
			
			logger.info("  The nubmer of samples in data is "+ dataset.getNumOfSamples());
			logger.info("  The nubmer of classes is  "+ dataset.getClassValues().size()  +"   or from labels "+dataset.getClassLabels() );
			IR.setTrainData(dataset);
			// logger.info("the features size array is equal to "+featArrs.size()
			// +"  and the region size array is = "+regions.size());
			IR.BuildClassifier();
			IR.setUseProbability( data.useProbability);
			IR.Train();
			
			/******************** Testing **************************/			
			dataset = lib.LoadData(data, testMod,
					AppDefaults.dbType,AppDefaults.StoreErrorImage);
			
             if (data.useSplit){
		     	dataset = dataset.GenerateSplitClassesDataSet(data.digit,data.digitC2);		
            }
			logger.info("  The nubmer of samples in data is "+ dataset.getNumOfSamples());
			logger.info("  The nubmer of classes is  "+ dataset.getClassValues().size()  +"   or from labels "+dataset.getClassLabels() );
	
			IR.setTestData(dataset);
			IR.Test();
			
			ClassifierResult result = IR.getClassifierResult();
			String classifierDetailedOutput=IR.getClassifierDetailedOutput();
			
			// if 
			if (AppDefaults.StoreErrorImage){
				// get the data list of 
	                   ArrayList<String>  locations=dataset.getOrignalLocations();
	                   ArrayList<Integer> labels=dataset.getAllTargets();
	                   result.getDataResults().setDetaliedStringOutput( classifierDetailedOutput);
	                   result.getDataResults().setDataSetLocations(locations, labels,AppDefaults.dbType);
	                   data.createFeatureNameList();
	                   data.createClassifierName();
	                    result.setSmallString(data.getName()+" using Features "+data.FeautureSummary);
	                    String indexString="";
	                    if (AppDefaults.addIndexClassifierNames){
	                    	indexString="_"+i;
	                    }
	                   
	                   result.saveAll( AppDefaults.DefaultImageErrorDir+AppDefaults.CurrentOSSeperator+data.toString()+indexString+AppDefaults.CurrentOSSeperator);
	                 
				//dataset
			}
			  ErrorsArray[0][i]=result.getNumberOfIncorrect();
			 dataArray.get(i).Accuracy=result.getPercentCorrect();
			logger.info("Classifier  of " + i + "  " + result.toString());
			data.createFeatureNameList();
			str += "  Classifier  of  " + i + " Result ==> Percent Correct ="
					+ result.getPercentCorrect()
					+ "%   with number of Errors ="
					+ result.getNumberOfIncorrect() + " For classifier "
					+ data.getName() + "  With Features  "
					+ ArrayToString(data);
			k++;
			str += newline;

		}
		
		lib.SaveString("result.txt", str);
		lib.SaveXls("result",ErrorsArray, "Validation ", classifierNames, FeaturesNames);
		
	if (AppDefaults.trailMode==AppDefaults.MODE_TRAIL_INCREMENT){
		int oldtrail=AppDefaults.TrialNo;
		AppDefaults.TrialNo++;
		int newTrail=AppDefaults.TrialNo;
		if (AppDefaults.classifiersFilename.contains("_Try_")){
			
		    AppDefaults.classifiersFilename= AppDefaults.classifiersFilename.replace("_Try_"+oldtrail, "_Try_"+newTrail);
		}
		else {
			AppDefaults.classifiersFilename= AppDefaults.classifiersFilename+"_Try_"+newTrail;
			
		}
	ClassifierData.SaveClassifiersSummary(dataArray, AppDefaults.classifiersFilename, false, false);
		//saveSettings(settingFilename);
		 ClassifierData.SaveClassifiersDetails(dataArray, AppDefaults.classifiersFilename, AppDefaults.sort);
		// ClassifierData.SaveClassifiersDetails(dataArray, AppDefaults.classifiersFilename, false);
	}		
	ClassifierData.SaveClassifiersSummary(dataArray, AppDefaults.classifiersFilename+"_Sorted", AppDefaults.sort,false);
		logger.info("##################################################################");
		logger.info(str);

		
	}



	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
 

	}
	private String ArrayToString(ClassifierData data) {
		ArrayList<ArrayList<String>> arr = data.feat;
		String str = "[";
		for (int i = 0; i < arr.size(); i++) {// region
			// if (i!=0);
			str += " In Region " + data.regions.get(i).getRegionName() + " { ";

			if (arr.get(i) != null)
				for (int j = 0; j < arr.get(i).size(); j++) {
					str += arr.get(i).get(j) + " , ";
				}

			str += "} ,";
		}
		str += "]";
		return str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		 RunHierarchicalClassifier test = new  RunHierarchicalClassifier();
		DataBaseConnector.OS = DataBaseConnector.OS_WINDOWS;
		Thread th = new Thread(test);
		th.run();
	}

}
