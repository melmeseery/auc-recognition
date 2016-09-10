package classifiers;

import org.apache.log4j.Logger;

import classifiers.results.Classification;
import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;
import data.dataset.DataSet;

public abstract class GeneralClassifier  {
	private static transient final Logger logger = Logger.getLogger( GeneralClassifier .class);
	transient protected ResultSetAcc DataResults;  
	 transient  protected ClassifierResult Result;
	 String classifierDetailedOutput="";
	public abstract  void setDefaultOptions();
	public	abstract void setOptions(String op);
	public	abstract void setTrainSet(DataSet dr);
	public	abstract void setTestSet(DataSet ts);
	public	abstract  void createClassifier();
	public 	abstract  void train();
	public 	abstract void test();
	public 	abstract void testProbability();
	public abstract void CrossValidate();
	//public  abstract ClassifierResult getResult();
	public  abstract Classification getInstanceDistribution(double[] featureVector);
	public  abstract String getInstanceClass(double[] featureVector);
	public abstract void saveModel(String string) ;
	public abstract void loadModel(String string) ;
	public ResultSetAcc getDataResults() {
		return DataResults;
	}
	public ClassifierResult getResult() {
		
		return Result;
	}
	public abstract void trainProbability();
	public  String getClassifierDetailedOutput() {
		 
		return  classifierDetailedOutput;
	}
}
