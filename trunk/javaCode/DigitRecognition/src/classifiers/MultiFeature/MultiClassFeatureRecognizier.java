/**
 * 
 */
package classifiers.MultiFeature;

import gui.AppDefaults;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.log4j.Logger;

import util.lib;

import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;
import classifiers.results.ResultSetAccuracyMulitClassifier;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.image.RegionCreater;

/**
 * @author TOSHIBA
 *
 */
public class MultiClassFeatureRecognizier {
 
	private static transient final Logger logger = Logger.getLogger(  MultiClassFeatureRecognizier.class);
	private static final int classes = AppDefaults.CATEGORY_SIZE;
	ArrayList<ClassifierData>  classifiers=null;
	ArrayList<ImageRecognizier>  IR=null;
	private ClassifierResult Result;
	int TrainMode=ImageRecognizier.TRAIN_VALIDATE;
	int ClassifierType= ImageRecognizier.CLASSIFIER_WEKA;
	 static public int Binary=0;
	 static public int MultiClass=1;
	 int type=MultiClass; 
		Logger summary=Logger.getLogger("summary");
		
		Logger summaryDetails=Logger.getLogger("SummaryDetails");
	 
	 int DataBase=DataBaseConnector.MADBASE;
	
	
	public int Interupt=20;
	
	public  int startTrainid=0;
	private ArrayList<int[]> ClassProfile;
	private int[][] classMatrix;
	private ResultSetAccuracyMulitClassifier DataResults;
	private String Name=""; 
 
	public void addClassifierData(ClassifierData d){
		if (classifiers==null){
			classifiers=new ArrayList<ClassifierData>();
		}
		classifiers.add(d);
	}

	public void initClassifiers() {
		 if (IR==null){
			 IR=new ArrayList<ImageRecognizier>();
		 }
		 for (int i = 0; i < classifiers.size(); i++) {
			 logger.info("creating the IR "+ i);
			 ImageRecognizier temp = new ImageRecognizier();
			 temp.setMainOptions(TrainMode, ClassifierType);
			 IR.add(temp);
		}
	}
	public ClassifierData MergeClassifierData(ArrayList<ClassifierData> d){
		ClassifierData FullFeatureSet;
		 FullFeatureSet=new ClassifierData();
		 ArrayList<ClassifierData>	 NewClassifiers=new ArrayList<ClassifierData>();
		 for (int i = 0; i < d.size(); i++) {
			 NewClassifiers.add((ClassifierData) d.get(i).clone());
		}
		 FullFeatureSet=null;
		 for (int i = 0; i <  NewClassifiers.size(); i++) {
			 //class1= NewClassifiers.get(i);
			 FullFeatureSet = lib.mergeClassifier(FullFeatureSet, NewClassifiers.get(i));	 
		}
		 if (FullFeatureSet.getName().length()>20){
			 FullFeatureSet.setName(  this.Name);
		 }
		 return FullFeatureSet;
	}
	 private ClassifierData mergeAllClassifier(ArrayList<ClassifierData> d){
		 ClassifierData FullFeatureSet;
		 FullFeatureSet=new ClassifierData();
		 RegionCreater reg,reg2;
		 ClassifierData class1,class2;
		 boolean regionAddedBefore;
		 ArrayList<String> finalFeatures=new ArrayList<String>();
		 ArrayList<Integer> mergedDigits=new ArrayList<Integer>();
		 
		 // for each classifier in my list 
		 for (int i = 0; i < d.size(); i++) {
			 class1=d.get(i);
			 class1.createFeatureNameList();
			 // check each region 
			 for (int j = 0; j < class1.regions.size(); j++) {
				 reg=	class1.regions.get(j);
				 
				   finalFeatures=class1.feat.get(j);
				 //// loop in all classifiers that has not been visited till now.......
				 for (int j2 = i+1; j2 < d.size(); j2++) {
					class2=d.get(j2);
					 // get regions is this classifier to check if they are equal to my test reg, 
					 
					 for (int k = 0; k <class2.regions.size(); k++) {
						
						 reg2=class2.regions.get(k);
						 if (reg.equals(reg2)){
							 //if i found a same region then i need to merge the feature then add the 
							 // this is incremental add, ass region can be in more then one classifier
						    finalFeatures=lib.mergeString(finalFeatures,class2.feat.get(k));
							 // add the region and the features to the class new
							 
							
							break; 
						 }
					}/////////finished the classes and either found a region and merged or no region in 
					 // all classifier
					 
				}
				  FullFeatureSet.addFeatureRegion(finalFeatures, reg);
				 
			}
			 
			 
			 // merge digits
		    boolean found=false;
			 for (int j = 0; j < class1.digit.size(); j++) {
				 for (int dd = 0; dd < mergedDigits.size(); dd++) {
						if (class1.digit.get(j)==mergedDigits.get(dd))
						{
							found=true;
						
						break;
							
						}
//						else {
//						
//						}
				}
				 if (!found){
					mergedDigits.add(class1.digit.get(j));
				 }
				 found=false;
			
			}
		}
		 FullFeatureSet.digit=mergedDigits;
		 FullFeatureSet.createFeatureNameList();
		 
		 logger.info("  the combined  features list size  is "+FullFeatureSet.FeatureNames.length);
		 
		 for (int i = 0; i < FullFeatureSet.FeatureNames.length; i++) {
			logger.info( " Features   "+i+"  is  "+ FullFeatureSet.FeatureNames );
		}
		 return FullFeatureSet;
	 }
		private DataSet LoadData(ClassifierData data , int Status){
			logger.info("inside the rundigitfeature in main ");
	         // set the region to compute 
		  return lib.LoadData(data, Status, DataBase);
		}

	public void TrainSystem(){
		// for llop on the classifier data
		DataSet trainData ;
		for (int i = startTrainid; i < classifiers.size(); i++) {
			if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
				trainData = LoadData(classifiers.get(i),DataBaseConnector.VALIDATE_TRAIN);
			else 
				trainData = LoadData(classifiers.get(i),DataBaseConnector.TRAIN);
			
		 //train IR
			if (type==Binary||classifiers.get(i).useSplit ){
				
				trainData=trainData.GenerateSplitClassesDataSet(classifiers.get(i).digit, classifiers.get(i).digitC2);
			}
		IR.get(i).setTrainData(trainData);
	    IR.get(i).setUseProbability( classifiers.get(i).useProbability);
		IR.get(i).BuildClassifier();
		IR.get(i).Train();
		logger.info(" Finish train  of classifier "+i );
		IR.get(i).SaveModel("Classifier"+classifiers.get(i).getName());
		if (i> Interupt){
			break;
		}
		} 
	}
	public void TestSystemBinary(){
		//testing system............
		DataSet fullList,testData;// = LoadTestSet();
	//	ClassifierData fullClassifierData = MergeClassifierData(classifiers);
		ArrayList<Integer> targets=null;
		for (int i = 0; i < classifiers.size(); i++) {
			if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
				fullList= LoadData(classifiers.get(i),DataBaseConnector.VALIDATE_TEST);
			else 
				fullList = LoadData(classifiers.get(i),DataBaseConnector.TEST);
			
			fullList.ConvertToArrays();
			
			targets=fullList.getAllTargets();
			
		 //train IR
			if (type==Binary||classifiers.get(i).useSplit ){
				
				testData=fullList.GenerateSplitClassesDataSet(classifiers.get(i).digit, classifiers.get(i).digitC2);
			}
			else {
				testData=fullList;
			}
			 IR.get(i).LoadModel("Classifier"+classifiers.get(i).getName());
		        //IR.BuildClassifier();
				IR.get(i).setTestData(testData);
				IR.get(i).Test();
				logger.info(" Finish test of  classifier "+i );
		} 
	     ComputeMultiBinaryClassifierResult(targets);	
	}
	public void TestSystem(){
		//testing system............
		DataSet fullList;// = LoadTestSet();
		ClassifierData fullClassifierData = MergeClassifierData(classifiers);
		logger.info("  total number of features are  "+fullClassifierData.FeatureNames.length);
		logger.info( " all classifier has  "+  fullClassifierData.FeautureSummary);
		
		if (TrainMode==ImageRecognizier.TRAIN_VALIDATE)
			 
			fullList = LoadData(fullClassifierData,DataBaseConnector.VALIDATE_TEST);
		else 
			fullList = LoadData(fullClassifierData,DataBaseConnector.TEST);
		
		
     logger.info("  ****************************  Finished read data now testing  *****************************************");
     
     logger.info( "  number of samples is  "+fullList.getNumOfSamples());
     
		DataSet testData ;
		// FIRST LOAD MODELS 
		// then comptue the data set 
		for (int i = 0; i < classifiers.size(); i++) {
			logger.info("testing the classifier   "+i);
			logger.info("  the classifier features names is "+classifiers.get(i).FeatureNames);
			//if (type!=Binary){
	         testData = getClassifierDataset(classifiers.get(i),fullList);
			//}
	         logger.info( "    test data of  "+i+"   = "+  testData.getAllFeatureString());
	         logger.info(   "  now the classifier  (testData.getFeatureCount()) number of features is     "+testData.getFeatureCount());
	         logger.info(" nubmer of samples is    "+testData.getNumOfSamples());
	   //  	IR.get(i).BuildClassifier();
			 //train IR	  
	        
	        IR.get(i).LoadModel("Classifier"+classifiers.get(i).getName());	
	        IR.get(i).setUseProbability( classifiers.get(i).useProbability);
	        //IR.get(i).setUseProbability( classifiers.get(i).useProbability);
	        //IR.BuildClassifier();
	    	logger.info(" Setting the test data  "+i );
			IR.get(i).setTestData(testData);
			logger.info(" Do the test of classifier...  "+i );
			IR.get(i).Test();
			logger.info(" Finish test of  classifier "+i );
			
			} 
	     ComputeMultiClassifierResult();	
		
	}
	
	
	private void ComputeMultiBinaryClassifierResult(ArrayList<Integer> samples){
		//int samples;
		// first get 
		 DataResults=new	 ResultSetAccuracyMulitClassifier(); 
		
		if (IR.size()==0)
			return;
		double[] classifiersAcc; 
		CreateClassVFeatureProfile(classifiers);
		
		classifiersAcc=new double[IR.size()];	
		int[] SamplesPerClassifier=new int[IR.size()];
		double [] classesAcc=new double[classes];
		int []   SamplesPerClass=new int[classes];
		
		ResultSetAcc result;
		double perdict;
		int target;
		int correct=0;
		int wrong=0;
	   int[] Votes=new int[IR.size()];
	   for (int i = 0; i < classifiersAcc.length; i++) {

			classifiersAcc[i]=0.0;
			SamplesPerClassifier[i]=0;
	}
	   
	   for (int i = 0; i <  classesAcc.length; i++) {
		   classesAcc[i]=0;
		   SamplesPerClass[i]=0;
	}
	   
//		logger.info("the p number of samples is "+samples);
		for (int i = 0; i < samples.size(); i++) {
			target=samples.get(i);
			//target=IR.get(0).getDetailsResult().getTarget(i);
		//	logger.info("  the target iss -------------------------   "+target);
			for (int j = 0; j < Votes.length; j++) {
				Votes[j]=0;
			}
//			
			for (int j = 0; j < IR.size(); j++) {
				result = IR.get(j).getDetailsResult();
//				if (result==null){
//					logger.error("Classifier "+j+"  has error ");
//					continue;
//				}
				perdict=result.getPerdictOfSample(i);
				Votes[j]=(int) perdict;
			 
				if (perdict==1){
				 //
					if (lib.isInArray(target,classifiers.get(j).digit)){
						classifiersAcc[j]++;
					}
					
				}
				 
	 
 
//					
										
			 
			 }
			
			int finalPerdict = getClassFromBinaryPerdict(Votes);
			//if (i%1000==0)
				
		//	logger.info("  target was "+target+"  and the final perdict is "+finalPerdict+ "  with votes "+Votes[finalPerdict]);
			SamplesPerClass[target]	++;
			DataResults.addResult(finalPerdict, target);
			if (finalPerdict==target){
				correct++;
				classesAcc[target]++;
				
			}
			else {
		
 
	 			DataResults.addSampleError(i, finalPerdict , " Error in Digit"+target+" Recognizied as  "+finalPerdict);
				wrong++;
			}
			
		}
		  Result	=new ClassifierResult();
		Result.setNumberOfIncorrect(correct);
		Result.setDataResults(DataResults);
		Result.setNumberOfTestSamples(samples.size());
		double percentC= ((double)correct/(double)samples.size())*100.0;
		Result.setPercentCorrect(percentC );
		double percentE=((double)wrong/(double)samples.size())*100.0;
		Result.setPercentError( percentE);
		Result.setNumberOfIncorrect(wrong);
	 
		
		
		
		logger.info("******************************************** The acc is  "+percentC);
		summary.info("******************************************** The acc is  "+percentC);
		double ac=0;;
		logger.info("##################################################################");
		summary.info("##################################################################");
		for (int i = 0; i < classifiers.size(); i++) {
			ac=(classifiersAcc[i]/(double)SamplesPerClassifier[i])*100;
			logger.info( " Result of classifier  "+classifiers.get(i).getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct "+(SamplesPerClassifier[i]-classifiersAcc[i]) +"error of total numberof samples is "+SamplesPerClassifier[i]);
			summary.info( " Result of classifier  "+classifiers.get(i).getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct"+(SamplesPerClassifier[i]-classifiersAcc[i]) +"error of  total numberof samples is "+SamplesPerClassifier[i]);

		}
		logger.info("##################################################################3 ");
		summary.info("##################################################################3 ");
		
		for (int i = 0; i < classes; i++) {
			
			ac=(classesAcc[i]/(double)SamplesPerClass[i])*100;
			logger.info( " Result of class     "+i+" "+ac+" % by  "+classesAcc[i]+" correct and "+(SamplesPerClass[i]-classesAcc[i])+" error  and total numberof samples is "+SamplesPerClass[i]);
			summary.info( " Result of class     "+i+" "+ac+" % by  "+classesAcc[i]+" correct and "+(SamplesPerClass[i]-classesAcc[i])+"  error  and total numberof samples is "+SamplesPerClass[i]);

			
		}
		logger.info("##################################################################3 ");	 
		logger.info("##################################################################3 ");
	}
	private int getClassFromBinaryPerdict(int[] votes) {
		int[] distance=new int[classMatrix.length];
	 
		 for (int i = 0; i < classMatrix.length; i++) {
			distance[i]=hamming(votes,classMatrix[i] );
		}
		return getMinIndex(distance);
	 
	} 
	private int getMinIndex(int[] votes) {
		int index=-1;
		int min=100000;
		for (int i = 0; i < votes.length; i++) {
			if (votes[i]<min){
				min=votes[i];
				index=i;
			}
		}
		
		return index;	
		  
	}

	int[] getClassProfile(int classLabel){
		return ClassProfile.get(classLabel);
		//this must not be hard codded 
		//return null;
	}

	int hamming (int[] s1, int[] s2) {

	  if(s1.length != s2.length) return -1;// not sure whether there is someting better to do

	  int counter = 0;

	  for (int k = 0; k < s1.length;++k)

	    if(s1[k] != s2[k]) ++counter;

	  return counter;

	}

	private void ComputeMultiClassifierResult() {
		int samples;
		// first get 
		 DataResults=new	 ResultSetAccuracyMulitClassifier(); 
		if (IR.size()==0)
			return;
		double[] classifiersAcc; 
		
		boolean errorInperdict=false;
		int countErrorToWrong=0;
		int countErrorToCorrect=0;
		classifiersAcc=new double[IR.size()];	
		int[] SamplesPerClassifier=new int[IR.size()];
		double [] classesAcc=new double[classes];
		int []   SamplesPerClass=new int[classes];
		samples=	IR.get(0).getDetailsResult().getPerdicts().size();
		ResultSetAcc result;
		double perdict;
		int target;
		int correct=0;
		int wrong=0;
	   int[] Votes=new int[classes];
	   for (int i = 0; i < classifiersAcc.length; i++) {

			classifiersAcc[i]=0.0;
			SamplesPerClassifier[i]=0;
	}
	   
	   for (int i = 0; i <  classesAcc.length; i++) {
		   classesAcc[i]=0;
		   SamplesPerClass[i]=0;
	}
	   
		logger.info("the p number of samples is "+samples);
		for (int i = 0; i < samples; i++) {
			
			target=IR.get(0).getDetailsResult().getTarget(i);
		//	logger.info("  the target iss -------------------------   "+target);
			for (int j = 0; j < Votes.length; j++) {
				Votes[j]=0;
			}
					 errorInperdict=false;
			for (int j = 0; j < IR.size(); j++) {
		
				result = IR.get(j).getDetailsResult();
				perdict=result.getPerdictOfSample(i);
	//	 logger.info("  the perdict of classifier  "+j+"  is  "+perdict);
					Votes[(int)perdict]++;
					
					/// if the target is one of the digit of classifier then count it and check if
					// is perdicted correctly. 
					if (classifiers.get(j).isInDigits(target)){
						SamplesPerClassifier[j]++;
						if (target==perdict){
							
							classifiersAcc[j]++;
							 //errorInperdict=false;
						}
						else {
							 errorInperdict=true;
						}
						
					}
					
										
			 
			 }
			
			int finalPerdict = getMaxIndex(Votes);
			//if (i%1000==0)
				
		//	logger.info("  target was "+target+"  and the final perdict is "+finalPerdict+ "  with votes "+Votes[finalPerdict]);
			SamplesPerClass[target]	++;
			DataResults.addResult( finalPerdict, target);
			if (finalPerdict==target){
				correct++;
				classesAcc[target]++;
				if (errorInperdict){
					countErrorToCorrect++;
					summaryDetails.info(" this is error in perdict that result a correct classification in sample "+i+"  final perdict is "+finalPerdict+"  target was "+target+"  because votes are "+				lib.ToString( Votes));

				}
				
			}
			else {
			
			//	summaryDetails.info("  Wrong classification in sample "+i +"  with final perdict is "+finalPerdict+"  target was "+target+"  because votes are "+				lib.ToString( Votes));
				
				if (errorInperdict){
					countErrorToWrong++;
					summaryDetails.info(" this is error in perdict that result in error in  classification in sample "+i+"  final perdict is "+finalPerdict+"  target was "+target+"  because votes are "+				lib.ToString( Votes));
					
				}
				DataResults.addSampleError(i, finalPerdict , " Error in Digit "+target+" Recognizied as  "+finalPerdict);
				wrong++;
			}
			
		}
		Result	=new ClassifierResult();
		Result.setDataResults(DataResults);
		Result.setNumberOfIncorrect(correct);
		Result.setNumberOfTestSamples(samples);
		double percentC= ((double)correct/(double)samples)*100.0;
		Result.setPercentCorrect(percentC );
		double percentE=((double)wrong/(double)samples)*100.0;
		Result.setPercentError( percentE);
		Result.setNumberOfIncorrect(wrong);
	 
		
		logger.info("********************* The final acc is  "+percentC);
		summary.info("****************** The final  acc is  "+percentC);
		double ac=0;;
		logger.info("##################################################################");
		summary.info("##################################################################");
		for (int i = 0; i < classifiers.size(); i++) {
		ac=(classifiersAcc[i]/(double)SamplesPerClassifier[i])*100;
		logger.info( " Result of classifier  "+classifiers.get(i).getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct "+(SamplesPerClassifier[i]-classifiersAcc[i]) +"  error of total numberof samples is "+SamplesPerClassifier[i]);
	    summary.info( " Result of classifier  "+classifiers.get(i).getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct "+(SamplesPerClassifier[i]-classifiersAcc[i]) +"  error of  total numberof samples is "+SamplesPerClassifier[i]);

		}
		logger.info("##################################################################3 ");
		summary.info("##################################################################3 ");
		
		for (int i = 0; i < classes; i++) {
			
			ac=(classesAcc[i]/(double)SamplesPerClass[i])*100;
			logger.info( " Result of class     "+i+" "+ac+" % by  "+classesAcc[i]+"  correct and "+(SamplesPerClass[i]-classesAcc[i])+"  error  and total numberof samples is "+SamplesPerClass[i]);
			summary.info( " Result of class     "+i+" "+ac+" % by  "+classesAcc[i]+" correct and "+(SamplesPerClass[i]-classesAcc[i])+"  error  and total numberof samples is "+SamplesPerClass[i]);

			
		}
		logger.info("##################################################################3 ");	 
		logger.info("##################################################################3 ");
		
//		logger.info("******************************************** The acc is  "+percentC);
//		double ac=0;;
//		logger.info("##################################################################3 ");
//		for (int i = 0; i < classifiers.size(); i++) {
//			ac=(classifiersAcc[i]/(double)SamplesPerClassifier[i])*100;
//			logger.info( " Result of classifier  "+classifiers.get(i).getName()+" "+ac+" % by  "+classifiersAcc[i]+"  correct and total numberof samples is "+SamplesPerClassifier[i]);
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
	}
	
	private int getMaxIndex (int[] votes){
		int index=-1;
		int max=-1;
		for (int i = 0; i < votes.length; i++) {
			if (votes[i]>max){
				max=votes[i];
				index=i;
			}
		}
		
		return index;	
		}

	private DataSet getClassifierDataset(ClassifierData data, DataSet fulldata){
		
		logger.info("  full data "+fulldata);
		
		// now i need to do this function...........
	return	fulldata.GenearteFeatureDataSet(data.FeatureNames);
	 
	}

	public ClassifierResult  getResult() {
 
		return Result;
	}

	public void setType(int binary2) {
		 this.type=binary2;
	}

	public ArrayList<int[]> getClassProfile() {
		return ClassProfile;
	}

	public void setClassProfile(ArrayList<int[]> classProfile) {
		ClassProfile = classProfile;
	}

	public int getNumberOfClassifiers() {
		 
		return this.classifiers.size();
	}

	public int[][]  CreateClassVFeatureProfile(ArrayList<ClassifierData> dataArr) {
		
		int profilesize=dataArr.size();
		classMatrix=new int[classes][profilesize];
		for (int i = 0; i < classMatrix.length; i++) {
			for (int j = 0; j < classMatrix[i].length; j++) {
				classMatrix[i][j]=0;
			}
		}
		
		ArrayList<Integer> d1;
		for (int i = 0; i < dataArr.size(); i++) {
		
			d1=dataArr.get(i).digit;
			for (int j = 0; j < d1.size(); j++) {
				
				classMatrix[d1.get(j)][i]=1;
				
			}
			
			
			
		}
		
			return classMatrix;
			

		}

	public void setOptions(int trainMode2, int classifierType2, int i) {
	 
		this.ClassifierType=classifierType2;
		this.TrainMode=trainMode2;
		//this.dbType=i;
		this.DataBase=i;
		
	}

	public void saveResult(String  Filename) {
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

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}


	
	
}
