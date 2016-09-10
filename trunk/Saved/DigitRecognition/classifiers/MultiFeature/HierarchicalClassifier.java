/**
 * 
 */
package classifiers.MultiFeature;

import gui.AppDefaults;

import java.awt.Point;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;
import classifiers.results.ResultSetAccuracyMulitClassifier;
import data.dataset.ArabicDataBaseConnector;
import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataBaseConnector;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;

import util.BinaryTree;
import util.BinaryTreeWithKey;
import util.lib;
import util.BinaryTreeWithKey.TreeIterator;

/**
 * @author maha
 *
 */
public class HierarchicalClassifier {

	private static transient final Logger logger = Logger.getLogger(  HierarchicalClassifier.class);
	private static final int classes = AppDefaults.CATEGORY_SIZE;
	private ClassifierResult Result;
	int TrainMode=ImageRecognizier.TRAIN_VALIDATE;
	int ClassifierType= ImageRecognizier.CLASSIFIER_WEKA;
 int DataBase=DataBaseConnector.MNIST;

	public int Interupt=20;
	
	public  int startTrainid=0;
 
	private int[][] classMatrix; 
	
	
	BinaryTreeWithKey<ClassifierData, Integer>  classifiers=null;
	BinaryTreeWithKey<ImageRecognizier, Integer>  IR=null;
	private ResultSetAccuracyMulitClassifier DataResults;
	/**
	 * 
	 */
	public HierarchicalClassifier() {
	 
	}
	public void setOptions(int trainMode2, int classifierType2, int dbType) {
		 
		this.ClassifierType=classifierType2;
		this.TrainMode=trainMode2;
		this.DataBase=dbType;
		
	}
	public void addClassifierData(ClassifierData d, Integer integer){
		if (classifiers==null){
			classifiers=new BinaryTreeWithKey<ClassifierData, Integer>();
		}
		if (IR==null){
			 IR=new BinaryTreeWithKey<ImageRecognizier, Integer>() ;
		 }
		
		
		classifiers.insert(d,integer);
		 ImageRecognizier temp = new ImageRecognizier();
		 temp.setMainOptions(TrainMode, ClassifierType);
		 IR.insert(temp, integer);
	
		 
	}

	 
	private DataSet LoadData(ClassifierData data , int Status){
	//	logger.info("inside the rundigitfeature in main ");
         // set the region to compute 
		
		
		
	  return lib.LoadData(data, Status, DataBase);
	 
	}
	public void TrainSystem(){
		// for llop on the classifier data
		DataSet trainData ;
		
		 if (logger.isInfoEnabled())
		 {
			 classifiers.displayTree(0);
			 
			 logger.info("   p&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&777");
		      IR.displayTree(1);
		 }
		ArrayList<Integer> keys = classifiers.keyList();
		
		for (int i = startTrainid; i < keys.size(); i++) {
			logger.info("   iam getting the  classifier number "+i );
			 ClassifierData classy = classifiers.find(keys.get(i));
			 if (classy==null )
				 continue;
			 
			 
			 logger.info("   iam getting the  classifier number   "+i+ "  that is   " +classy.digit+"   vs  "+classy.digitC2  ); 
			 if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
				trainData = LoadData(classy,DataBaseConnector.VALIDATE_TRAIN);
			else 
				trainData = LoadData(classy,DataBaseConnector.TRAIN);
			
		  		
				trainData=trainData.GenerateSplitClassesDataSet(classy.digit, classy.digitC2);
			 
			
			ImageRecognizier im = IR.find(keys.get(i));
		 im.setTrainData(trainData);
		im.BuildClassifier();
	    im.setUseProbability( classy.useProbability);
		im.Train();
		logger.info(" Finish train  of classifier "+i );
		im.SaveModel("Classifier"+classy.getName());
//	     for (int j = 0; j <   100 ; j++) {
//			logger.info("   delay      "+j+"   after classifier  "+i);
//		}
		if (i> Interupt){
			break;
		}
		
		
		} 
		
		
	}
	
	 private ArrayList<String> initSampleData(int status){
		 
	 if (DataBase==DataBaseConnector.MNIST){
		 
		 MNISTDataBaseConnector db=new MNISTDataBaseConnector();
			db.setDataBaseType(MNISTDataBaseConnector.MNIST);
			db.setStatus(status);
			ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits( null);
			ArrayList<String> offsets=new ArrayList<String>();
			for (int i = 0; i < mnistImages.size(); i++) {
		           offsets.add(mnistImages.get(i).y+"");
			}
			
			return offsets;
			
	 
		 
	 }	 
	 else if (DataBase==DataBaseConnector.MADBASE||DataBase==DataBaseConnector.ADBASE){
			ArabicDataBaseConnector db=new ArabicDataBaseConnector();
			db.setDataBaseType(DataBase);
			db.setStatus(status);
			return db.getImageFilesForAll();
			
	 }
	
		 return null;
	 }
	public void TestSystemHierarchial(){
		// first get 
		 DataResults=new	 ResultSetAccuracyMulitClassifier(); 
		//looping on all data 
		ArrayList<String> samples = null;
		int correct =0;
		int wrong=0;
 
		int [] ClassCorrect=new int [AppDefaults.CATEGORY_SIZE];
		int [] SamplesPerClass=new int [AppDefaults.CATEGORY_SIZE];
		
		for (int i = 0; i < ClassCorrect.length; i++) {
			ClassCorrect[i]=0;
			SamplesPerClass[i]=0;
			 
		}
	 
		int status= DataBaseConnector.VALIDATE_TEST;
		if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
		status=DataBaseConnector.VALIDATE_TEST;
			//fullList= LoadSample(tempClassifier,DataBaseConnector.VALIDATE_TEST);
		else 
			status= DataBaseConnector.TEST;
			//fullList = LoadSample(tempClassifier,DataBaseConnector.TEST);
		
		
		
		samples =initSampleData( status);
		
		if (samples!=null){
			int target;
			
		int finalPerdict;
		
			boolean recognized=false;
			// for each sample do the following....
			for (int i = 0; i < samples.size(); i++) {

				//// get the image 
				
				Digit image=LoadSample(samples.get(i), status);
	 			 
	 			// the target.... 
	 			target=image.getLabel();
	 			SamplesPerClass[target]++;
				/// now i have the image i need to cmpute feature b
				// and introduce to classifier...
		         TreeIterator  iterClass=classifiers.Iterator();
		         TreeIterator iter = IR.Iterator();
		         
		         recognized=false;
		          //nextClassifer
		         int nextClassifier=0;
		         ImageRecognizier ir=null ;
		         ClassifierData  classi=null;
		         
		 		while(  iter.hasNext() && !recognized) {
		 			
		 			    // get the first element in the list 
		 			if (nextClassifier==0){
		 			  ir =(ImageRecognizier) iter.next();
		 			  classi= (ClassifierData ) iterClass.next();
		 			}
		 			else if (nextClassifier==1){//   
		 				
		 				  ir =(ImageRecognizier) iter.getLeft();
				 	         classi= (ClassifierData ) iterClass.getLeft();
		 			
		 			}
		 			else {
		 				  ir =(ImageRecognizier) iter.getRight();
			 			  classi= (ClassifierData ) iterClass.getRight();
		 			}
		 			
		 			classi.noSamples++;
		 			Digit.setFeaturesToCompute(classi.feat.get(0));
		 			Digit.loadAllFeatureArray();
		 			image.computeAllFeatures();
	
		 			if (!ir.isLoaded()){
		 			ir.LoadModel("Classifier"+classi.getName());
		 			}
		 			
		 			String result=ir.TestSample(image.getFeaturesArray());
		 			
		 			
		 			int tempResult=Integer.parseInt(result);
		 			
		 			// the binary result 
		 			if (tempResult==1){  //positive for "digit "
		 			if (lib.isInArray( target,classi.digit)){
		 				// correct result... 
		 				
		 				// Now check if it is only one digit in clas sthen the recognition is found and contine tiwll next 
		 				if (classi.digit.size()<2){
		 					recognized=true;
		 					correct++;
		 					nextClassifier=0;
		 					 classi.Accuracy++;
		 					finalPerdict=classi.digit.get(0);
		 					DataResults.addResult(finalPerdict, target);
		 					ClassCorrect[target]++;
		 					
		 				}
		 				else {
		 					//classifiers[index]++;  wait.. 
		 					// now i am correct but i will have to go to left because 
		 					// i still need more classification.. 
		 				 nextClassifier=-1;  // go right... 
		 				 classi.Accuracy++;
		 					
		 				}
		 				
		 			}	// correct answer 
		 			else {
		 			// wrong decide...........
		 				//final result will be wrong... 
		 				finalPerdict=classi.digit.get(0);
	 					DataResults.addResult(finalPerdict, target);
				 			wrong++;
				 			DataResults.addSampleError(i, finalPerdict , " Error in Digit"+target+" Recognizied as  "+finalPerdict);
				 			break;
		 			}
		 			} 
		 			else {// value is 0 ( the left side of the classifer )
		 				// ok check if in digit c2 .. 
		 				if (lib.isInArray( target,classi.digitC2)){
			 				// correct result... 
			 				
			 				// Now check if it is only one digit in clas sthen the recognition is found and contine tiwll next 
			 				if (classi.digitC2.size()<2){
			 					recognized=true;
			 					correct++;
			 					finalPerdict=classi.digitC2.get(0);
			 					DataResults.addResult(finalPerdict, target);
			 					nextClassifier=0;
			 					ClassCorrect[target]++;
			 					 classi.Accuracy++;
			 				}
			 				else {
			 					//classifiers[index]++;  wait.. 
			 					
			 					// now i am correct but i will have to go to left because 
			 					// i still need more classification.. 
			 				 nextClassifier=1;  // go left.. 
			 				 classi.Accuracy++;
			 					
			 				}
		 				
		 			}// correct answer 
			 			else {
				 			// wrong decide...........
				 				//final result will be wrong... 
			 				finalPerdict=classi.digitC2.get(0);
		 					DataResults.addResult(finalPerdict, target);
				 			wrong++;
				 			DataResults.addSampleError(i, finalPerdict , " Error in Digit"+target+" Recognizied as  "+finalPerdict);
				 			break;
				 			
				 			}//wrong... 
		 			
		 		}// output 000 
				
				
			}/// while ... 
			
		}// for all samplessss.
			
			
		}
		
		    Result	=new ClassifierResult();
			Result.setDataResults(DataResults);
			Result.setNumberOfIncorrect(correct);
			Result.setNumberOfTestSamples(samples.size());
			double percentC= ((double)correct/(double)samples.size())*100.0;
			Result.setPercentCorrect(percentC );
			double percentE=((double)wrong/(double)samples.size())*100.0;
			Result.setPercentError( percentE);
			Result.setNumberOfIncorrect(wrong);
		 
			
			
			
			logger.info("******************************************** The acc is  "+percentC);
			double ac=0;;
			logger.info("##################################################################3 ");
			
			
			
		      TreeIterator  iterClass=classifiers.Iterator();
		       
		  
		         ClassifierData  classi=null;
		 		while(  iterClass.hasNext()  ) {
	
		        classi= (ClassifierData ) iterClass.next();			 
				ac=(classi.Accuracy/(double)classi.noSamples)*100;
				logger.info( " Result of classifier  "+classi.getName()+"  "+ac+" % by  "+classi.Accuracy+"  correct and total numberof samples is "+classi.noSamples);
				
				classi.Accuracy=ac;
			}
			logger.info("##################################################################3 ");
			for (int i = 0; i < classes; i++) {
				
				ac=(ClassCorrect[i]/(double)SamplesPerClass[i])*100;
				logger.info( " Result of class     "+i+" "+ac+" % by  "+ClassCorrect[i]+"  correct and total numberof samples is "+SamplesPerClass[i]);

				
			}
			logger.info("##################################################################3 ");	 
				logger.info("##################################################################3 ");
		
//		fullList.ConvertToArrays();
//		
//		targets=fullList.getAllTargets();
//		
//		testData=fullList.GenerateSplitClassesDataSet(tempClassifier.digit, tempClassifier.digitC2);
		
		
	}
	private Digit LoadSample(String im, int status) {
		 if (DataBase==DataBaseConnector.MNIST){
			 
			  
			 MNISTDataSetGenerator db=new MNISTDataSetGenerator();
				
				db.setStatus(status);
				 
				return db.getSingleImage(im);
		 
			 
		 }	 
		 else if (DataBase==DataBaseConnector.MADBASE||DataBase==DataBaseConnector.ADBASE){
			 ArabicDataSetGenerator db=new ArabicDataSetGenerator();
			 db.setArabicDB(DataBase);
			 db.setStatus(status );
			 return db.getSingleImage(im);
			 
				
		 }
		
			 return null;
	 
	}
//	public void TestSystemBinary(){
//		//testing system............
//		DataSet fullList,testData;// = LoadTestSet();
//	//	ClassifierData fullClassifierData = MergeClassifierData(classifiers);
//		ArrayList<Integer> targets=null;
//               TreeIterator  iterClass=classifiers.Iterator();
//       
//		for (  TreeIterator iter = IR.Iterator(); iter.hasNext();) {
//			    // get the first element in the list 
//			ImageRecognizier temp =(ImageRecognizier) iter.next();
//			ClassifierData  tempClassifier= (ClassifierData ) iterClass.next();
//			if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
//				fullList= LoadData(tempClassifier,DataBaseConnector.VALIDATE_TEST);
//			else 
//				fullList = LoadData(tempClassifier,DataBaseConnector.TEST);
//			
//			
//			fullList.ConvertToArrays();
//			
//			targets=fullList.getAllTargets();
//			
//			
//			
//			testData=fullList.GenerateSplitClassesDataSet(tempClassifier.digit, tempClassifier.digitC2);
//			
//			
//			 temp.LoadModel("Classifier"+tempClassifier.getName());
//		        //IR.BuildClassifier();
//				temp.setTestData(testData);
//			
//				temp.Test();
//				logger.info(" Finish test of  classifier    "+temp );
//			
//			// here i should chekck............
//		}
//		
//			
//		
//		ComputeMultiClassifierResult();	
//
//
//		 
//		
//	 
//	}
//	@Deprecated
//	private void ComputeMultiClassifierResult() {
//		int samples;
//		// first get 
//		if (IR.size()==0)
//			return;
//		double[] classifiersAcc; 
//		
//		ImageRecognizier minIR=IR.findMin();
//		
//		classifiersAcc=new double[IR.size()];	
//		int[] SamplesPerClassifier=new int[IR.size()];
//		double [] classesAcc=new double[classes];
//		int []   SamplesPerClass=new int[classes];
//
//		
//		samples=	minIR.getDetailsResult().getPerdicts().size();
//		ResultSetAcc result;
//		double perdict;
//		int target;
//		int correct=0;
//		int wrong=0;
//	   int[] Votes=new int[classes];
//	   for (int i = 0; i < classifiersAcc.length; i++) {
//
//			classifiersAcc[i]=0.0;
//			SamplesPerClassifier[i]=0;
//	}
//	   
//	   for (int i = 0; i <  classesAcc.length; i++) {
//		   classesAcc[i]=0;
//		   SamplesPerClass[i]=0;
//	}
//	   
//		logger.info("the p number of samples is "+samples);
//		for (int i = 0; i < samples; i++) {
//			
//			target=minIR.getDetailsResult().getTarget(i);
//		//	logger.info("  the target iss -------------------------   "+target);
//			for (int j = 0; j < Votes.length; j++) {
//				Votes[j]=0;
//			}
//			 TreeIterator  iterClass=classifiers.Iterator();
//		       int j=0;
//			//	TreeIterator iter = IR.Iterator(); iter.hasNext();
//			for (TreeIterator iter = IR.Iterator(); iter.hasNext(); j++) {
//				ImageRecognizier temp =(ImageRecognizier) iter.next();
//				ClassifierData  tempClassifier= (ClassifierData ) iterClass.next();
//				result = temp.getDetailsResult();
//				perdict=result.getPerdictOfSample(i);
//	//	 logger.info("  the perdict of classifier  "+j+"  is  "+perdict);
//					Votes[(int)perdict]++;
//					
//					/// if the target is one of the digit of classifier then count it and check if
//					// is perdicted correctly. 
//					if (tempClassifier.isInDigits(target)){
//						SamplesPerClassifier[j]++;
//						if (target==perdict){
//							
//							classifiersAcc[j]++;
//							
//						}
//						
//					}
//					
//					
//							
//					
//			 
//			 }
//			
//			int finalPerdict = getMaxIndex(Votes);
//			//if (i%1000==0)
//				
//		//	logger.info("  target was "+target+"  and the final perdict is "+finalPerdict+ "  with votes "+Votes[finalPerdict]);
//			SamplesPerClass[target]	++;
//		
//			if (finalPerdict==target){
//				correct++;
//				classesAcc[target]++;
//				
//			}
//			else {
//				wrong++;
//			}
//			
//		}
//		  Result	=new ClassifierResult();
//		Result.setNumberOfIncorrect(correct);
//		Result.setNumberOfTestSamples(samples);
//		double percentC= ((double)correct/(double)samples)*100.0;
//		Result.setPercentCorrect(percentC );
//		double percentE=((double)wrong/(double)samples)*100.0;
//		Result.setPercentError( percentE);
//		Result.setNumberOfIncorrect(wrong);
//	 
//		
//		
//		
//		logger.info("******************************************** The acc is  "+percentC);
//		double ac=0;;
//		logger.info("##################################################################3 ");
//		 TreeIterator  iterClass=classifiers.Iterator();
//		for (int i = 0 ; i < classifiers.size() && iterClass.hasNext(); i++) {
//			ClassifierData  tempClassifier= (ClassifierData ) iterClass.next();
//			ac=(classifiersAcc[i]/(double)SamplesPerClassifier[i])*100;
//			logger.info( " Result of classifier  "+tempClassifier.getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct and total numberof samples is "+SamplesPerClassifier[i]);
//		}
//		logger.info("##################################################################3 ");
//		for (int i = 0; i < classes; i++) {
//			
//			ac=(classesAcc[i]/(double)SamplesPerClass[i])*100;
//			logger.info( " Result of class     "+i+" "+ac+" % by  "+classesAcc[i]+"  correct and total numberof samples is "+SamplesPerClass[i]);
//
//			
//		}
//		logger.info("##################################################################3 ");
//	}
//	
//	private int getMaxIndex (int[] votes){
//		int index=-1;
//		int max=-1;
//		for (int i = 0; i < votes.length; i++) {
//			if (votes[i]>max){
//				max=votes[i];
//				index=i;
//			}
//		}
//		
//		return index;	
//		}
	public  ClassifierResult getResult() {
		 
		return Result;
	}
	public void saveResult(String Filename) {
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(Filename+"_Result.txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is a comment line Saving the final result ... ");
			out.println("Number of classifiers is "+classifiers.size());
			
			out.println("   Thre result is  ");
			out.println(Result);
		 
			out.println("## This is The end of file.......");
		
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.error("Error in writing to file");
	}
	logger.info("Finished writing the settings..........");
		
	}

 

}
