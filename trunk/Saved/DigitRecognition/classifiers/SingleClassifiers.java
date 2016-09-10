/**
 * 
 */
package classifiers;

import gui.AppDefaults;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import classifiers.results.Classification;
import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;
import data.dataset.DataSet;

 

/**
 * @author TOSHIBA
 *
 */
public abstract class SingleClassifiers extends  GeneralClassifier implements Serializable {
	private static transient final Logger logger = Logger.getLogger( SingleClassifiers.class);
	
	// set the dataset... 
   transient protected     DataSet TrainData;
   transient protected     DataSet TestData;
   protected boolean _normalizeScale = true;
   protected int _numFeatures;
   protected double[] _minFeatureVals;
   protected double[] _maxFeatureVals;
   protected HashMap _labelToType = new HashMap();
   
  
   
   public void CopyClassifierData(SingleClassifiers copyClass){
	   
	   this._maxFeatureVals=copyClass._maxFeatureVals.clone();
	   this._minFeatureVals=copyClass._minFeatureVals.clone();
	   this._labelToType=(HashMap) copyClass._labelToType.clone();
	   
	   this._normalizeScale=copyClass._normalizeScale;
	   this._numFeatures=copyClass._numFeatures;
	   
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	   /**
     * Not an incremental classifier.  Calls to train will remove the
     * original training data and retrain the classifier with the new
     * set.
     */
    public boolean isIncremental(){
        return false;
    }
//	@Override
//	public void CrossValidate() {
//		 
//		
//	}
    protected int getByValue(HashMap< Integer, String>  map,String type){
    	
   	 
   	 for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
   		 Entry<Integer, String> e = (Entry<Integer, String>) iterator.next();
   		 if (e.getValue().equals(type)){
   			 return e.getKey();
   		 }
			
		}
   	 
   	return -1;
   }
    /**
     * Scale the given feature vector based on the scale obtained from
     * the training set.  This method is called by classify to scale
     * the test example.
     */
    public double[] scale(double[] fvals){
        double[] normVals = new double[fvals.length];
        //logger.info("  now the fvals length is "+fvals.length+"  the max feature length is  "+_maxFeatureVals.length);
        for(int i=0; i<fvals.length; i++){
            normVals[i] = (fvals[i]-_minFeatureVals[i])/(_maxFeatureVals[i]-_minFeatureVals[i]);
        }
        return  normVals;
     }
    
    
    /**
     * Scale the feature values in the training set to be in the
     * range of [min,max].
     */
    public DataSet scale(DataSet tset, int min, int max){
    	logger.info(" i am in scale of the libs .....");
    	_numFeatures=tset.getFeatureCount();
        _minFeatureVals = new double[_numFeatures];
        _maxFeatureVals = new double[_numFeatures];
        for(int i=0; i<_numFeatures; i++){
            _minFeatureVals[i]=Double.POSITIVE_INFINITY;
            _maxFeatureVals[i]=Double.NEGATIVE_INFINITY;
        }
		Double[] Sample ;
        
        for (int i = 0; i < tset.getNumOfSamples(); i++) {
		    Sample = tset.getSample(i);
        	for (int j = 0; j < Sample.length; j++) {
        	    _minFeatureVals[j]=Math.min(_minFeatureVals[j],Sample[j]);
                _maxFeatureVals[j]=Math.max(_maxFeatureVals[j],Sample[j]);
			}
        	
		}
        //figure out the minimum and maximum values of each feature

        //scale feature values to be in the range of [0,1]
        DataSet normalizedSet = new DataSet(tset);
        
        for (int i = 0; i < tset.getNumOfSamples(); i++) {
			Sample=tset.getSample(i);
            double[] vals = new double[Sample.length];
        	for (int j = 0; j < vals.length; j++) {
        	    vals[j] = (Sample[j]-_minFeatureVals[j])/(_maxFeatureVals[j]-_minFeatureVals[j]);
                
			}
        	normalizedSet.addSample(vals, tset.getTarget(i));
        	
        	
        
        }
    //    	logger.info(" i after the scale for the svm and now .."+normalizedSet.getClassLabels());
   // normalizedSet.ConvertToArrays();
        
        return normalizedSet;
    }
	@Override
	public
	void createClassifier() {

		
	}

//	@Override
//	public ClassifierResult getResult() {
//		
//		return Result;
//	}

	@Override
	public
	void setDefaultOptions() {
		
		
	}

	@Override
	public
	void setOptions(String op) {
		 
		
	}

//	@Override
//	public
//	void train() {
//		 
//		
//	}

    
   

	@Override
	public void setTestSet(DataSet ts) {
		TestData=ts;
		
	}

	@Override
	public void setTrainSet(DataSet dr) {
		TrainData=dr;
		
	}

	public void testProbability(){
		 classifierDetailedOutput="";
		 DataResults=new ResultSetAcc();
		 int SamplesCount = TestData.getNumOfSamples();
		 double[] sample;
		 int correct =0;
		 int wrong =0;
		 
		 for (int i = 0; i < SamplesCount; i++) {
			 if (i%1000==0){
				 logger.info(" testing sample  "+ i+ " of  "+SamplesCount+"   correct  "+  correct +"   error is "+wrong);
			 }
			 
			sample=new double[TestData.getSample(i).length];
			for (int j = 0; j < sample.length; j++) {
				sample[j]=TestData.getSample(i)[j];
			}
			 //String cat=getInstanceClass(sample);
			 Classification distribution = getInstanceDistribution(sample);
		 
			 String cat=distribution.getHighestConfidenceType();
			 int catInt;
			 //n now parse it to integer to get the target.. 
			 if (cat==null)
			 {
				catInt=-1; 
			 }
			 else  catInt = Integer.parseInt(cat);
			 if (AppDefaults.StoreErrorImage){
			 classifierDetailedOutput+="Sample "+i+  "    target is   "+TestData.getTarget(i)+"  the output of the classifiers is "+ distribution;
				// logger.info("Sample "+i+  "    target is   "+TestData.getTarget(i)+"  the output of the classifiers is "+ distribution);	 
			 DataResults.addResult( distribution, TestData.getTarget(i));	
			 }
			
			 

			 if (catInt==TestData.getTarget(i)){
				 
				 correct++;
				 
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
				 
			 }
			 else{
				 if (AppDefaults.StoreErrorImage){
					  classifierDetailedOutput+="=================EEEEEEEEEEEEEEEEEEEEEEEEEE";
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
					 DataResults.addSampleError(i, catInt , " Error in Digit"+TestData.getTarget(i)+" Recognizied as  "+catInt+"  and distribution was "+distribution);
			 }
				 wrong++;
			
			 }
			 
			 
			 
		}
		 
		Result=new ClassifierResult();
		Result.setDataResults(DataResults);
		Result.setNumberOfIncorrect(correct);
		Result.setNumberOfTestSamples(SamplesCount);
		double percentC= ((double)correct/(double)SamplesCount)*100.0;
		Result.setPercentCorrect(percentC );
		double percentE=((double)wrong/(double)SamplesCount)*100.0;
		Result.setPercentError( percentE);
		Result.setNumberOfIncorrect(wrong);
		logger.info("the result of classifier issssssssssssss            "+percentC+"    sample "+SamplesCount);
		DataResults.computeAcc();
		
	}
	@Override
	public void test() {
		 DataResults=new ResultSetAcc();
		 int SamplesCount = TestData.getNumOfSamples();
		 double[] sample;
		 int correct =0;
		 int wrong =0;
		 classifierDetailedOutput="";
	 
		 for (int i = 0; i < SamplesCount; i++) {
			 if (i%1000==0){
				 logger.info(" testing sample  "+ i+ " of  "+SamplesCount+"   correct  "+  correct +"   error is "+wrong);
			 }
			sample=new double[TestData.getSample(i).length];
			for (int j = 0; j < sample.length; j++) {
				sample[j]=TestData.getSample(i)[j];
			}
			 String cat=getInstanceClass(sample);
			 
			 
			 int catInt;
			 //n now parse it to integer to get the target.. 
			 if (cat==null)
			 {
				catInt=-1; 
			 }
			 else  catInt = Integer.parseInt(cat);
			 if (AppDefaults.StoreErrorImage){
				 
			 classifierDetailedOutput+="Sample "+i+  "    target is   "+TestData.getTarget(i)+"  the output of the classifiers is "+ cat;
			 }
			 if (AppDefaults.StoreErrorImage){
			// logger.info("Sample "+i+  "    target is   "+TestData.getTarget(i)+"  the output of the classifiers is "+ cat);
			 DataResults.addResult( catInt, TestData.getTarget(i));
			 }
			// logger.info(" the cat is "+catInt+"   target is "+TestData.getTarget(i));
			 if (catInt==TestData.getTarget(i)){
				 
				 correct++;
				 classifierDetailedOutput+=AppDefaults.LineSeperator;
				 
			 }
			 else{
				 if (AppDefaults.StoreErrorImage){
					  classifierDetailedOutput+="=================EEEEEEEEEEEEEEEEEEEEEEEEEE";
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
				 DataResults.addSampleError(i, catInt , " Error in Digit"+TestData.getTarget(i)+" Recognizied as  "+catInt);
			 }
				 wrong++;
 
			 }
			 
			 
			 
		}
		 
			Result=new ClassifierResult();
	     Result.setDataResults(DataResults);
		Result.setNumberOfIncorrect(correct);
		Result.setNumberOfTestSamples(SamplesCount);
		double percentC= ((double)correct/(double)SamplesCount)*100.0;
		Result.setPercentCorrect(percentC );
		double percentE=((double)wrong/(double)SamplesCount)*100.0;
		Result.setPercentError( percentE);
		Result.setNumberOfIncorrect(wrong);
		
		logger.info("   the result of classifier issssssssssssss            "+percentC+"    sample "+SamplesCount);
		DataResults.computeAcc();
	
	}

}
