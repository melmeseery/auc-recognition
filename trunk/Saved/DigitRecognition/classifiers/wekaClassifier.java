/**
 * 
 */
package classifiers;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.*;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import gui.AppDefaults;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import liblinear.Linear;

import org.apache.log4j.Logger;

import classifiers.results.Classification;
import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;
import data.dataset.DataSet;
import data.feature.FeatureSet;
import data.feature.FeatureStat;


/**
 * @author TOSHIBA
 *
 */
public class wekaClassifier extends GeneralClassifier {

	 Instances data;
	 Instances Testdata;
	 Instances unFilterData;
		String[] ClassifierOptions;
		 weka.classifiers.Classifier classifier;
		
	  private final int  TYPE_LIB_LINEAR=0;
	  private final int  TYPE_LIB_SVM=1; 
	  private final int  TYPE_SMO=2; 
	  
	  int type=  TYPE_LIB_LINEAR; 
	  
	  Evaluation eval;
	  double [] SortedTP;
	  String [] SortedLabels;
		 
	private static transient final Logger logger = Logger.getLogger( wekaClassifier.class);
	Logger loggerWeka = Logger.getLogger("WekaApp");	
	private static final Logger logResult=Logger.getLogger("result");
	private boolean SortValues=false;
	private FastVector fvWekaAttributes;	 
	public void ReadDataFile(String filename){

		
		
		try {
	
			//"/some/where/data.arff"
			data = new Instances(
			                        new BufferedReader(
			                              new FileReader(filename)));
			 data.setClassIndex(data.numAttributes() - 1);
			 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 // setting class attribute
		
		
		
	}
	
	
	public void setDefaultOptions(){
		
	try {
		 if (type==TYPE_LIB_SVM){
		ClassifierOptions = weka.core.Utils.splitOptions(" -S 0 -K 0 -D 3 -G 0.01 -R 0.0 -N 0.5 -M 40.0 -C 100.0 -E 1.0E-4 -P 0.1 -V  ");
		 }
		 if (type==TYPE_LIB_LINEAR){
		ClassifierOptions = weka.core.Utils.splitOptions(" -S 3 -C 100.0 -E 0.010 -B 1.0 -Z ");
		 }
		 if (type==TYPE_SMO){
				ClassifierOptions = weka.core.Utils.splitOptions(" -no-checks -C 100.0 -L 0.0010 -P 1.0E-12 -N 2 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.PolyKernel -no-checks -C 250007 -E 1.0\"");
				 }
	} catch (Exception e) {
		
		e.printStackTrace();
	}	
	}
	public void removeAttrib(ArrayList<Integer> FeaturesToTest){
		unFilterData=  new Instances(data);
		
		
		String options = new String();
		 options = "-V -R ";                                    // "range"
		int k=1;
		 for (int i = 0; i < FeaturesToTest.size(); i++) {
			 options+= (FeaturesToTest.get(i)+1)+"," ;
			 k++;
		}
		 options+="last";
		                                   // first attribute
		 loggerWeka.info("  the options string is "+options);
		 Remove remove = new Remove();                         // new instance of filter
		 try {
			remove.setOptions(weka.core.Utils.splitOptions(options));	
			remove.setInputFormat(unFilterData);     
			// inform filter about dataset **AFTER** setting options
	 data = Filter.useFilter(unFilterData, remove);   // apply filter
	 
	 data.setClassIndex(data.numAttributes() - 1);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}                           // set options
	
	} 
	
	public void createClassifier(){
		
		
		//weka.classifiers.functions.LibLINEAR -S 3 -C 10.0 -E 0.0010 -B 1.0 -Z
		 try {
			 if (type==TYPE_LIB_LINEAR){
			 // create new instance of scheme
			 classifier = new LibLINEAR();
	
			 }
			 if (type==TYPE_LIB_SVM){
				 // create new instance of scheme
				 classifier = new LibSVM();
		
			}
			 if (type==TYPE_SMO){
				 // create new instance of scheme
				 classifier = new SMO();
		
			}
				 // set options
		 classifier.setOptions(ClassifierOptions);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	public void train(){
		
		
		//weka.classifiers.functions.LibLINEAR -S 3 -C 10.0 -E 0.0010 -B 1.0 -Z
		 try {
			 // create new instance of scheme
			// classifier = new LibLINEAR();
		 // set options
			 setDefaultOptions();
			 createClassifier();
			 //classifier.setOptions(ClassifierOptions);
			 classifier.buildClassifier(data);
		 
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}


    public void CrossValidate(){
    	
    	
   	 try {
	 
     eval = new Evaluation(data);
   		 eval.crossValidateModel(classifier, data, 10, new Random(1));
   		 loggerWeka.info("-----------------------------------------------------------------------------");
   		 loggerWeka.info("  Number of instances  "+eval.numInstances() +"  percent corrrect "+	 eval.pctCorrect() +"   and wrong number is "+ eval.incorrect() );
   		 loggerWeka.info("-----------------------------------------------------------------------------");
   		 loggerWeka.info(eval.toSummaryString());
   		 loggerWeka.info("---------------------------------------------------------------------------");   		 
   		 
   		 
   		 
		} catch (Exception e) {
	 
			e.printStackTrace();
		}                           // set options
	
    	
    }


	public 	  Evaluation getClassifierEvaluations() {
	 return eval
	 ;
		
	}
	
	public double getPercentCorrect(){
		
		return eval.pctCorrect();
		
	}
	
	public double getNumberOfInstances(){
		
		return eval.numInstances();
	}
	public double getIncorrectNumber(){
		return eval.incorrect();
		
	}
	@Override
	public  ClassifierResult getResult(){
		
		if (eval!=null){
		
		 ClassifierResult r = new  ClassifierResult();
		 r.setNumberOfIncorrect(eval.incorrect());
		 r.setPercentCorrect(eval.pctCorrect());
		 r.setNumberOfTestSamples(eval.numInstances());
		 r.setPercentError(eval.pctIncorrect());
		 // sorrting the evals. 
		 
		 if (SortValues){
		 SortEvals();
		 
		 if (SortedTP!=null)
		 {
			 if (SortedTP.length>3){
				  r.setFirstDigitTp( SortedTP[0]);
				  r.setFirstDigitTpLabel(SortedLabels[0]);
				  r.setSecondDigitTp(SortedTP[1]);
				  r.setSecondDigitTpLabel(SortedLabels[1]);
				  r.setThirdDigitTp(SortedTP[2]);
				  r.setThirdDigitTpLabel(SortedLabels[2]);
			 }
		 }
		 
		 }
		return r;
		}else 
			return Result;
		
	}

    
	private void SortEvals() {
		double[][] conv = eval.confusionMatrix();
		int numberOfClasses=conv.length;
		ArrayList<Double> sorted=new ArrayList<Double>();
		ArrayList<String>  SortedLabel=new ArrayList<String>();
		//sorted.ensureCapacity(numberOfClasses);
		double value;
	boolean added=false;	
	logger.info(" numberOfClasses "+numberOfClasses );
		for (int i = 0; i < numberOfClasses; i++) {
			
			added=false;
			value=eval.truePositiveRate(i);
			for (int j = 0; j < sorted.size(); j++) {
				if (sorted.get(j)>value)
				{
					added=true;
					logger.info("adding  "+value+" in location "+j);
					sorted.add(j, new Double(value));//(j, )
					SortedLabel.add(j,""+i);
					break;
					
				}
			}
			if (added==false){
				logger.info( "  addding  "+value+"  at end of "+sorted.size());
				sorted.add(new Double(value));
				SortedLabel.add(""+i);
			}
			
		}
		SortedTP=new double[sorted.size()];
		SortedLabels=new String[sorted.size()];
		
		logger.info( "  size of "+sorted.size());
		int k=0;
		for (int i = sorted.size()-1; i > 0; i--) {
			SortedTP[k]=sorted.get(i);
			SortedLabels[k]=SortedLabel.get(i);
			k++;
		}
		
		logger.info( "  the first label is "+SortedLabels[0]);
	}


	public void SaveResult(String string) {
		 
		;
		
			
		    try {	
		    
             File aFile=new File(string);
		    Writer output = new BufferedWriter(new FileWriter(aFile));;
		    //use buffering
	
	      output.write( eval.toSummaryString()  ); 
	      output.write("-------------------------------------");
		      //FileWriter always assumes default encoding is OK!
		      output.write( eval.toClassDetailsString()  ); 
		      output.write("-------------------------------------");
		      output.write( eval.toMatrixString()  ); 
		      output.write("-------------------------------------");
		      output.write(eval.toString());
		    	output.close();
		    } catch (IOException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		  
	}


	public boolean isSortValues() {
		return SortValues;
	}


	public void setSortValues(boolean sortValues) {
		SortValues = sortValues;
	}


	@Override
	public
	void setOptions(String op) {
		 
		
	}


	@Override
	public
	void setTestSet(DataSet dr) {
		
		
//		 at weka.core.Instance.setValue(Instance.java:692)
//		 at weka.core.Instance.setClassValue(Instance.java:590)
//		 at classifiers.wekaClassifier.setTestSet(wekaClassifier.java:417)
//		 at classifiers.MultiFeature.ImageRecognizier.Test(ImageRecognizier.java:168)
		
		
		
	    ArrayList<FeatureStat> features = dr.getFeatures().getAllFeaturesRead();
        ArrayList<Integer> categories = dr.getClassValues();
       logger.info("  the categories are "+categories);
        logger.info( " dataset is  "+dr.getNumOfSamples()+"  samples each has "+dr.getFeatureCount()+" features ");
        // Declare the feature vector
       fvWekaAttributes = new FastVector( features.size()+1);
        
        // Declare all numeric attributes
        for (int i = 0; i < features.size(); i++) {
			   
          Attribute Attribute1 = new Attribute(features.get(i).getFeatureName());
          fvWekaAttributes.addElement(Attribute1);
      
		}

      
      // Declare the class attribute along with its values
      FastVector fvClassVal = new FastVector(categories.size());
      for (int i = 0; i < categories.size(); i++) {
   	   fvClassVal.addElement(""+categories.get(i));
	}
      Attribute ClassAttribute = new Attribute("Cat", fvClassVal);
  
      
      
      fvWekaAttributes.addElement(ClassAttribute);
      
      
   ////////////////////////////////////////////Setting the data features and attribust 
      
      // now adding the instancesss...................
      // Create an empty training set
    Testdata = new Instances("Rel", fvWekaAttributes, dr.getNumOfSamples());           
      // Set class index
      Testdata.setClassIndex(features.size());
      int size=features.size()+1;
      int samples=dr.getNumOfSamples();
      Instance iExample;
      Double [] dataVector;
      int dataTarget;
      
      for (int i = 0; i < samples ; i++) { 
   	   // Create the instance
   	   iExample = new Instance(size);
	   iExample.setDataset(  Testdata);
   	   dataVector=dr.getSample(i);
   	   dataTarget=dr.getTarget(i);
   	   for (int j = 0; j < features.size(); j++) {
   		   iExample.setValue((Attribute)fvWekaAttributes.elementAt(j), dataVector[j] );
		}
   	   
   	   /// add the target..............
   	   //iExample.setValue((Attribute)fvWekaAttributes.elementAt(features.size()), dataTarget );
   	 iExample.setClassValue( dr.getTarget(i)+"");
    // add the instance
      Testdata.add(iExample); 
	}
     
    
	}


	@Override
	public
	void setTrainSet(DataSet dr) {

             ArrayList<FeatureStat> features = dr.getFeatures().getAllFeaturesRead();
             ArrayList<Integer> categories = dr.getClassValues();
             
             logger.info("  the categories are "+categories);
             logger.info( " dataset is  "+dr.getNumOfSamples()+"  samples each has "+dr.getFeatureCount()+" features ");
           
             // Declare the feature vector
             FastVector fvWekaAttributes = new FastVector( features.size()+1);
             
             // Declare all numeric attributes
             for (int i = 0; i < features.size(); i++) {
				   
               Attribute Attribute1 = new Attribute(features.get(i).getFeatureName());
               fvWekaAttributes.addElement(Attribute1);
           
			}
     
           
           // Declare the class attribute along with its values
           FastVector fvClassVal = new FastVector(categories.size());
           for (int i = 0; i < categories.size(); i++) {
        	   fvClassVal.addElement(""+categories.get(i));
		}
           Attribute ClassAttribute = new Attribute("Cat", fvClassVal);
       
           
   
           fvWekaAttributes.addElement(ClassAttribute);
           
           
        ////////////////////////////////////////////Setting the data features and attribust 
           
           // now adding the instancesss...................
           // Create an empty training set
         data = new Instances("Rel", fvWekaAttributes, dr.getNumOfSamples());           
           // Set class index
           data.setClassIndex(features.size());
           int size=features.size()+1;
           int samples=dr.getNumOfSamples();
           Instance iExample;

           Double [] dataVector;
           int dataTarget;
           
         
           for (int i = 0; i < samples ; i++) { 
        	   // Create the instance
        	   iExample = new Instance(size);
        	   iExample.setDataset(data);
        	   dataVector=dr.getSample(i);
        	  // dataTarget=dr.getTarget(i);
        	   for (int j = 0; j < features.size()&&j< dataVector.length; j++) {
        		   iExample.setValue((Attribute)fvWekaAttributes.elementAt(j), dataVector[j] );
			}
        	   
        	   /// add the target..............
        	  // iExample.setValue((Attribute)fvWekaAttributes.elementAt(features.size()), dataTarget );
        	//   iExample.setClassValue( dr.getTarget(i));
        	//   logger.info("  number of Sample ");
        	 
        	   iExample.setClassValue( dr.getTarget(i)+"");
        	   
         // add the instance
           data.add(iExample); 
		}
          
         
       
           
           
		
	}



	public
	void testEval() {
	   	
	   	 try {
		 
	     eval = new Evaluation(Testdata);
	   		 eval.evaluateModel(classifier, Testdata);
	   		 loggerWeka.info("-----------------------------------------------------------------------------");
	   		 loggerWeka.info("  Number of instances  "+eval.numInstances() +"  percent corrrect "+	 eval.pctCorrect() +"   and wrong number is "+ eval.incorrect() );
	   		 loggerWeka.info("-----------------------------------------------------------------------------");
	   		 loggerWeka.info(eval.toSummaryString());
	   		 loggerWeka.info("---------------------------------------------------------------------------");   		 
	   		 
	   		 
	   		 
	   		 
	   		 
	   		 
			} catch (Exception e) {
		 
				e.printStackTrace();
			}                           // set options
		
	    	
		
	}
	public Classification getInstanceDistribution(	 Instance iExample){
		
	 iExample.setDataset(Testdata);
		 
		 // Get the likelihood of each classes 
		 // fDistribution[0] is the probability of being �positive� 
		 // fDistribution[1] is the probability of being �negative� 
		 double[] fDistribution=null;
			String [] types=null;
		try {
//			logger.info("  info for the  weka is "+iExample.isMissing(1));
//			logger.info(" info for the weka "+iExample);
//			logger.info("  info for the weka is "+iExample.attribute(1));
		
			fDistribution = classifier.distributionForInstance(iExample);
			types=new String[fDistribution.length];
			for (int i = 0; i < types.length; i++) {
				types[i]=iExample.classAttribute().value((int)i);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
		
		
		 Classification c=new Classification( types,fDistribution);
				return   c ;
	}

	public Classification getInstanceDistribution(double[] featureVector) {
		 //clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel)
		
		 // Create the instance
		 Instance iExample = new Instance( fvWekaAttributes.size());
		 
		 for (int i = 0; i < featureVector.length&&i<fvWekaAttributes.size(); i++) {
			 iExample.setValue((Attribute)fvWekaAttributes.elementAt(i), featureVector[i]);
			 
		}
		 
		 // Specify that the instance belong to the training set 
		 // in order to inherit from the set description
		 iExample.setDataset(data);
		 
		 // Get the likelihood of each classes 
		 // fDistribution[0] is the probability of being �positive� 
		 // fDistribution[1] is the probability of being �negative� 
		 double[] fDistribution=null;
			String [] types=null;
		try {
			fDistribution = classifier.distributionForInstance(iExample);
			types=new String[fDistribution.length];
			for (int i = 0; i < types.length; i++) {
				types[i]=iExample.classAttribute().value((int)i);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
			
		
			
			
 Classification c=new Classification( types,fDistribution);
		return   c ;
	}


	public String getInstanceClass( Instance iExample) {
		///  clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel)
//		 // Create the instance
//		 Instance iExample = new Instance( fvWekaAttributes.size());
//		 
//		 for (int i = 0; i < featureVector.length&&i<fvWekaAttributes.size(); i++) {
//			 iExample.setValue((Attribute)fvWekaAttributes.elementAt(i), featureVector[i]);
//			 
//		}
		 
		 // Specify that the instance belong to the training set 
		 // in order to inherit from the set description
		 iExample.setDataset(Testdata);
			double labelIndex =-1;
			String label="";
		  try { 
//			  if(Math.random()>0.8){
//				 
//			 // logger.info(" The example "+iExample);
//			  }
//			  if(iExample.hasMissingValue()){
//				//  logger.info(" this exmaple has missing valuesss.. "+iExample);
//			  }
		 labelIndex = classifier.classifyInstance(iExample);
		
		 //logger.info(" the  label is  "+labelIndex);
		   label=iExample.classAttribute().value((int)labelIndex);
		 
		} catch (Exception e) {
		
			logger.error(e);
			logger.error(" the error in testdat "+Testdata.numAttributes()+"  inst "+Testdata.numInstances()+"  test "+Testdata.numClasses());
		//	logger.error(" test is "+Testdata);
			e.printStackTrace();
		}
		 
		 
		
		return label;
	}
	@Override
	public String getInstanceClass(double[] featureVector) {
		///  clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel)
		 // Create the instance
		 Instance iExample = new Instance( fvWekaAttributes.size());
		 
		 for (int i = 0; i < featureVector.length&&i<fvWekaAttributes.size(); i++) {
			 iExample.setValue((Attribute)fvWekaAttributes.elementAt(i), featureVector[i]);
			 
		}
		 
		 // Specify that the instance belong to the training set 
		 // in order to inherit from the set description
		 iExample.setDataset(data);
			double labelIndex =-1;
			String label="";
		  try {
		 labelIndex = classifier.classifyInstance(iExample);
		 
		   label=iExample.classAttribute().value((int)labelIndex);
		 
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		 
		 
		
		return label;
	}


	@Override
	public void loadModel(String string) {
		try{
	 
			// deserialize model
			classifier = (Classifier) weka.core.SerializationHelper.read(string);
			//classifier.
		//	libModel= Linear.loadModel( new File(string));
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
	}


	@Override
	public void saveModel(String string) {
		 
		 // serialize model
		 try {
			weka.core.SerializationHelper.write(string, classifier);
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
	}
	public void testProbability(){
		 classifierDetailedOutput=""; 
		
		 DataResults=new ResultSetAcc();
		 int SamplesCount = Testdata.numInstances();
		 double[] sample;
		 int correct =0;
		 int wrong =0;
		 
		 
		 for (int i = 0; i < SamplesCount; i++) {
			
//			sample=new double[ Testdata.numAttributes()];
//			for (int j = 0; j < sample.length; j++) {
//				sample[j]=TestData.getSample(i)[j];
//			}
			 //String cat=getInstanceClass(sample);
				if (logger.isTraceEnabled()){
			   logger.info("sample "+i);
				}
			 Classification distribution = getInstanceDistribution(Testdata.instance(i));
			 
	
			 
			 String cat=distribution.getHighestConfidenceType();
			 int catInt;
			 //n now parse it to integer to get the target.. 
			 if (cat==null)
			 {
				catInt=-1; 
			 }
			 else  catInt = Integer.parseInt(cat);
			 
			 int target=	  (int) Testdata.instance(i).classValue();
			String labeltarget=Testdata.instance(i).stringValue( Testdata.instance(i).classAttribute());;
			if (logger.isTraceEnabled()){
			 logger.trace("Sample "+i+" target is "+target+"  perdict is "+cat+"  the output of the classifiers is "+ distribution);
			 logResult.trace("Sample "+i+" target is "+target+"  perdict is "+cat+"  the output of the classifiers is "+ distribution);
		 		 classifierDetailedOutput+="Sample "+i+" target is "+target+"  perdict is "+cat+"  the output of the classifiers is "+ distribution;
			}
 			 DataResults.addResult( distribution, target);
			 if (cat.equals(labeltarget)){
			 //if (catInt==Testdata.instance(i).classIndex()){
			//if (catInt==target){	 
				 correct++;
		
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
			 }
			 else{
				 DataResults.addSampleError(i, catInt , " Error in Digit"+target+" Recognizied as  "+catInt);
				 wrong++;
				  classifierDetailedOutput+="=================EEEEEEEEEEEEEEEEEEEEEEEEEE";
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
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
		 classifierDetailedOutput=""; 
		 DataResults=new ResultSetAcc();
		 int SamplesCount =  Testdata.numInstances();
		 double[] sample;
		 int correct =0;
		 int wrong =0;
		 
		 for (int i = 0; i < SamplesCount; i++) {
//			sample=new double[TestData.getSample(i).length];
//			for (int j = 0; j < sample.length; j++) {
//				sample[j]=TestData.getSample(i)[j];
//			}
			// logger.info(" example nubmer "+i);
			 String cat=getInstanceClass(Testdata.instance(i));
			 
			 int catInt;
			 //n now parse it to integer to get the target.. 
			 if (cat==null)
			 {
				catInt=-1; 
			 }
			 else  catInt = Integer.parseInt(cat);
			 
	 
			 
			 int target=	  (int) Testdata.instance(i).classValue();
			String labeltarget=Testdata.instance(i).stringValue( Testdata.instance(i).classAttribute());;
			if (logger.isTraceEnabled()){
			 logger.trace("Sample "+i+  "   target is  "+target+"   the output of the classifiers is "+ cat);
			 logResult.trace("Sample "+i+  "   target is  "+target+"   the output of the classifiers is "+ cat);
			}
			   
				 classifierDetailedOutput+= "Sample "+i+  "   target is  "+target+"   the output of the classifiers is "+ cat;
 			 DataResults.addResult(  catInt, target);
 			 if (cat.equals(labeltarget)){ 
			// logger.info(" the cat is "+catInt+"   target is "+TestData.getTarget(i));
			// if (catInt==target){
				 
				 correct++;
				 	 classifierDetailedOutput+=AppDefaults.LineSeperator;
			 }
			 else{
				 wrong++;
				 if (AppDefaults.StoreErrorImage){		 DataResults.addSampleError(i, catInt , " Error in Digit"+target+"  Recognizied as  "+catInt);
		
				  classifierDetailedOutput+="=================EEEEEEEEEEEEEEEEEEEEEEEEEE";
					 classifierDetailedOutput+=AppDefaults.LineSeperator;
				 }
			 }
			 
			 
			 
		}
		 
		Result=new ClassifierResult();
		Result.setNumberOfIncorrect(correct);
	     Result.setDataResults(DataResults);
	 
		Result.setNumberOfTestSamples(SamplesCount);
		double percentC= ((double)correct/(double)SamplesCount)*100.0;
		Result.setPercentCorrect(percentC );
		double percentE=((double)wrong/(double)SamplesCount)*100.0;
		Result.setPercentError( percentE);
		Result.setNumberOfIncorrect(wrong);
		
		logger.info("the result of classifier is            "+percentC+"    sample "+SamplesCount);
		DataResults.computeAcc();
	
	}
    /**
     * 
     * 
     * 
     * -------------------------------------------------------------------
The train and then test      
 import weka.core.Instances;
 import weka.classifiers.Evaluation;
 import weka.classifiers.trees.J48;
 ...
 Instances train = ...   // from somewhere
 Instances test = ...    // from somewhere
 // train classifier
 Classifier cls = new J48();
 cls.buildClassifier(train);
 // evaluate classifier and print some statistics
 Evaluation eval = new Evaluation(train);
 eval.evaluateModel(cls, test);
 logger.info(eval.toSummaryString("\nResults\n======\n", false));
     * 
     * **/


	@Override
	public void trainProbability() {
		train();
		
	}
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * 
   
   Statistics
Some methods for retrieving the results from the evaluation:
nominal class
correct() - number of correctly classified instances (see also incorrect())
pctCorrect() - percentage of correctly classified instances (see also pctIncorrect())
kappa() - Kappa statistics
numeric class
correlationCoefficient() - correlation coefficient
general
meanAbsoluteError() - the mean absolute error
rootMeanSquaredError() - the root mean squared error
unclassified() - number of unclassified instances
pctUnclassified() - percentage of unclassified instances

If you want to have the exact same behavior as from command line, use this call:
 import weka.classifiers.trees.J48;
 import weka.classifiers.Evaluation;
 ...
 String[] options = new String[2];
 options[0] = "-t";
 options[1] = "/some/where/somefile.arff";
 logger.info(Evaluation.evaluateModel(new J48(), options));
     * */
    
    

}
