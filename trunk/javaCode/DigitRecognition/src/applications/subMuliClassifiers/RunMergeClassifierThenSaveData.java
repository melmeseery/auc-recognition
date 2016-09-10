/**
 * 
 */
package applications.subMuliClassifiers;

import gui.AppDefaults;

import java.util.ArrayList;
import java.util.Observable;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.ImageRecognizier;
import classifiers.MultiFeature.MultiClassFeatureRecognizier;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import util.lib;

/**
 * @author TOSHIBA
 *
 */
public class RunMergeClassifierThenSaveData implements RunnableTask {

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#addObserver(tasks.TaskController)
	 */
	@Override
	public void addObserver(TaskController taskController) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#getSettings()
	 */
	@Override
	public TaskSettings getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#setSettings(tasks.TaskSettings)
	 */
	@Override
	public void setSettings(TaskSettings task) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		AppDefaults.ReadSetting(AppDefaults.settingFilename);	    
			RunTaskDetails();
	 
	 
		
	
		
		AppDefaults.saveSettings(AppDefaults.settingFilename);
	
	}

	private void RunTaskDetails() {
	  // get the class data 
		// then do the train and test data... 
		
		ArrayList<ClassifierData> dataArray = ClassifierData.ReadClassifiersDetails(AppDefaults.classifiersFilename);
		MultiClassFeatureRecognizier  classifier2=new MultiClassFeatureRecognizier () ;
		classifier2.setName("classifiersFilename");
		ClassifierData fullClassifierData =  classifier2.MergeClassifierData(dataArray);
		
		DataSet trainData,fullList ;
		if (AppDefaults.TrainMode==ImageRecognizier.TRAIN_VALIDATE)
			trainData = LoadData(fullClassifierData,DataBaseConnector.VALIDATE_TRAIN);
		else 
			trainData = LoadData(fullClassifierData,DataBaseConnector.TRAIN);
		
		
		
		
		trainData.SaveToFile(AppDefaults.classifiersFilename+"_Save_training");
		if (AppDefaults.TrainMode==ImageRecognizier.TRAIN_VALIDATE)
			 
			fullList = LoadData(fullClassifierData,DataBaseConnector.VALIDATE_TEST);
		else 
			fullList = LoadData(fullClassifierData,DataBaseConnector.TEST);
		
		fullList.SaveToFile(AppDefaults.classifiersFilename+"_Save_testing" );
		
		
	}
	
	
	private DataSet LoadData(ClassifierData data , int Status){
		 
         // set the region to compute 
	  return lib.LoadData(data, Status, AppDefaults.dbType);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		RunMergeClassifierThenSaveData test = new  RunMergeClassifierThenSaveData();
		DataBaseConnector.OS = DataBaseConnector.OS_WINDOWS;
		Thread th = new Thread(test);
		
		th.run();
	}

}
