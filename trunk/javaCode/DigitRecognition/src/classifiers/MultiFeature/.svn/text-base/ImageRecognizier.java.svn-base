package classifiers.MultiFeature;

import gui.AppDefaults;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import classifiers.GeneralClassifier;
import classifiers.LibLinearSingleClassifier;
import classifiers.LibSVMSingleClassifier;
import classifiers.wekaClassifier;
import classifiers.results.ClassifierResult;
import classifiers.results.ImageErrors;
import classifiers.results.ResultSetAcc;

import applications.subMuliClassifiers.RunOVOLibLinear;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.DataSetGenerator;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;
import data.image.FeaturedRegion;
import data.image.RegionCreater;

public class ImageRecognizier extends AbstractRecognizier{
	private static transient final Logger logger = Logger.getLogger( ImageRecognizier .class);
	


//	// the input database 
//	DataSetGenerator db;
	
     // the selected region to use and input for the classifier;
	ArrayList<RegionCreater>  region;
	
	// selected the feature to compute 
	ArrayList<ArrayList<String>> feats;
	
	//digits to use in training;
	ArrayList<Integer> digits;



	private boolean loaded=false;
	
	private boolean useProbability =false;
     
	
	
	public void setDataOptions( ArrayList<Integer> digit, ArrayList<String> featName, RegionCreater reg){
		
		digits=digit;
		feats=new ArrayList<ArrayList<String>>();
			feats.add(featName);
		region=new ArrayList<RegionCreater>();
			region.add(reg);
		
	}
	public void setDataOptions(ArrayList<Integer> digit,
			ArrayList<ArrayList<String>> featArrs,
			ArrayList<RegionCreater> regions) {
		
		digits=digit;
		feats=featArrs;
		region=regions;
		
	}
	
	/// i will need the to first read the dataset
	// then select the digit to train 
	private DataSet LoadTrainData(){
		logger.info("inside the rundigitfeature in main ");
         // set the region to compute 
	 
		//digitsForTest.add(new Integer(d2));
		//FeaturedRegion.setFeaturesToCompute(feats);
		//FeaturedRegion.loadAllFeatureArray();
		
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus(DataBaseConnector.TRAIN);
		DataSet dataset = db.GetDataSetByDigits(digits,region,feats);
		return dataset; 
	}
	private  DataSet LoadTestData( ArrayList<Integer>    digitsForTest){
		logger.info("inside the rundigitfeature in main ");
		if (  digitsForTest==null) {
         // set the region to compute 
		  digitsForTest=new 	ArrayList<Integer> ();
		for (int i = 0; i < classes; i++) {
			digitsForTest.add(new Integer(i));
		}
		}
		//digitsForTest.add(new Integer(d2));
		//FeaturedRegion.setFeaturesToCompute(feats);
		//FeaturedRegion.loadAllFeatureArray();
		
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus(DataBaseConnector.TEST);
		DataSet dataset = db.GetDataSetByDigits(  digitsForTest,region,feats);
		return dataset; 
	}
	public void LoadData(){
		
		if (TrainMode==TRAIN_VALIDATE){
		DataSet dataset=LoadTrainData();
	 
			dataset.ConvertToArrays();
			dataset.setRandomizeData(true);
			dataset.doNoShuffle();
			dataset.CreateValidationSet();
			TrainData=dataset.GenerateTrainDataSet();
		    TrainData.ConvertToArrays();
			//TrainData
			TestData=dataset.GenerateValidationDataSet();
		
			TestData.ConvertToArrays();
		
		 
		}
		else if (TrainMode==TRAIN_TEST){
			
			DataSet dataset=LoadTrainData();
			 TrainData=dataset;
			    TrainData.ConvertToArrays();
			// TestData= LoadTestData(null);
		}
		
	}
	
	
	public void BuildClassifier(){
		createClassifier();
		classifier.setTrainSet(TrainData);
	    //classifier.setTestSet(TestData);
		
		  loaded = false;
			
	} 
	
	
	public void Train(){
		
		if (useProbability){
			
			classifier.trainProbability();
		}
		else {
		classifier.train();
		}
		
		  loaded = false;
	}
	
	public ClassifierResult TrainValidate(){
		   LoadData();
		   
	       BuildClassifier();
	       classifier.setTestSet(TestData);
	       classifier.train();
	       classifier.test();
	   return  classifier.getResult();
	}
	public void Test(){
		
		classifier.setTestSet(TestData);
		if (useProbability){
			classifier.testProbability();
		}		
		else {
		classifier.test();
		}
		
	}
	public String TestSample(double [] featureVector){
		
		return classifier.getInstanceClass(featureVector);
		 
	}
	public ClassifierResult getClassifierResult(){
		return classifier.getResult();
	}
	public  ResultSetAcc getDetailsResult(){
		return classifier.getDataResults();
	}
	public void DisplayClassifierResult(){
		
	}
	
	
//	public DataSet loadTestData(){
//		logger.info("inside the rundigitfeature in main ");
//        // set the region to compute 
//	
//		
//		//digitsForTest.add(new Integer(d2));
//		FeaturedRegion.setFeaturesToCompute(feats);
//		FeaturedRegion.loadAllFeatureArray();
//		
//		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
//		db.setStatus(DataBaseConnector.TEST);
//		DataSet dataset = db.GetDataSetByDigits(digits,region);
//		return dataset; 
//	}
	


	public void setMainOptions(int trainMode, int  classifierType) {
	 
		 TrainMode=trainMode; 
		 ClassifierType=classifierType;
	}
	public DataSet getTrainData() {
		return TrainData;
	}
	public void setTrainData(DataSet trainData) {
		trainData. FixArrayAndClassLabel();
		TrainData = trainData;
	}
	public DataSet getTestData() {
		return TestData;
	}
	public void setTestData(DataSet testData) {
		testData. FixArrayAndClassLabel();
		TestData = testData;
		
	}
	public void SaveModel(String string) {
		classifier.saveModel(string);
	}
	public void LoadModel(String string) {
		if (classifier==null){
			this.createClassifier();
		}
		
		classifier.loadModel(string);
	  loaded = true;
		
	}
	public boolean isLoaded() {
	 
		return loaded;
	}
	/**
	 * @return the useProbability
	 */
	public boolean isUseProbability() {
		return useProbability;
	}
	/**
	 * @param useProbability the useProbability to set
	 */
	public void setUseProbability(boolean useProbability) {
		this.useProbability = useProbability;
	}
	public String getClassifierDetailedOutput() {
 return classifier.getClassifierDetailedOutput();
		 
	}
	 


	
}
