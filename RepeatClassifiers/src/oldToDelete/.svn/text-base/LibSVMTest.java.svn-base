/**
 * 
 */
package oldToDelete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import libsvm.svm_problem;

import org.apache.log4j.Logger;

import classifiers.lib.Classification;
import classifiers.lib.SVMClassifier;


import General.AppDataSettings;

import data.DataSet;
import data.FeatureSet;
import data.ResultSetAcc;

/**
 * @author Maha
 *
 */
public class LibSVMTest {
	
	private static transient final Logger logger = Logger.getLogger( LibSVMTest.class);
	Logger summary=Logger.getLogger("summary");
	
	Logger summaryDetails=Logger.getLogger("MyAppLogging");
	DataSet FullData=new DataSet();
	int classes=10;
	ArrayList<String[]> featuresArrays;
	ArrayList<String> featStrings;
	int alg=0;
	public boolean genearteTrainFiles=true;
	public boolean genearteTestFiles=true;
	public boolean trainAll=false;
	public boolean testAll=true;
  boolean useAllFeatures=true;
  boolean OVO=false;
private int format=1;
  
  
	
	public boolean isUseAllFeatures() {
	return useAllFeatures;
}
public void setUseAllFeatures(boolean useAllFeatures) {
	this.useAllFeatures = useAllFeatures;
}
	/*******
	 * 
	 * Steps to do the libsvm 
	 * first read the full ovo features files using "dataset"
	  
	 * Generate 45 file to train each classifier based on the feature set...
	 * Genearte the classifier data from teh data set == > 
	 * train 45 classifier and genreate the model files .......
	 * 
	 * for test 
	 * sperate the features into 45 files 
	 * test each of the 45 classifer to get 45 perdict files
	 * read all 45 file which will gives 10 000 to   
	 *  loop on all 10000 samples for the 
	 *       get the classification of each of the 45 classifier and count the votes of this sample 
	 *       
	 * 
	 * 
	 * ********/
	public void ReadFeatureFile(String filename){
		
		FeatureSet allFeat=new FeatureSet();
		 allFeat.ReadAllFeatures("feat.txt");
		 featuresArrays=new ArrayList<String[]>();
		 featStrings=new ArrayList<String>();
		 //first read all features 
		 String line;
			try {
				System.out.println("reading the file................ wait");
				File afile = new File(filename);
				Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
				
				
				  FileOutputStream file; 
		         // PrintStream out; // declare a print stream object
		          //file = new FileOutputStream("feat_index.txt");

		          // Connect print stream to the output stream
		    //     out = new PrintStream(file);
		        //  out.println(" " );
				
				//input.useDelimiter(",");
				String   featuresString;
				String [] featuresArray;
				int[] FeaturesIndeces;
				int i;
				int j;
				String st="";
				   while (input.hasNext()) {
					   st="";
					   //first input for the 
					  i=input.nextInt();
					  j=input.nextInt();
					  featuresString=input.next();
					 
					logger.trace(" classififer s "+i+"  "+j+"  the input string is "+featuresString);
					  
					  featuresArray=featuresString.trim().split(",");
						 featuresArrays.add(featuresArray);
					  //now i have the features 
					   allFeat.createFeatures(featuresArray);
					  FeaturesIndeces = allFeat.getSelectedFeaturesIndeces();
					  //out.print(i);
					  //out.print("  ");
					  //out.print(j);
					  //out.print("  ");
					//  out.print(FeaturesIndeces.length);
					  String tt=" ";
					  //String stt="";
				  for (int j2 = 0; j2 < featuresArray.length; j2++) {
//						  out.print("  ");
//						  out.print(FeaturesIndeces[j2]);
//						  
					  st+="  +"+featuresArray[j2];
					  tt+=FeaturesIndeces[j2]+" "+featuresArray[j2]+",";
					}
				   featStrings.add(st);
				  // stt=st;
				  // System.out.println(stt);
					 // out.println();
					  logger.trace(" output is [ "+tt+" ] ");
				   }
			      input.close();
			  //    out.close();
			      
				     
			      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	public void TrainSystemOVO(String filename){
		//read data from the system
	logger.info(" in the train ..........");
		int k=0;
		ClassifierApp classifier = new ClassifierApp ();
		if (genearteTrainFiles){
			FullData.ReadFromFile(filename);
		//genearte the feature set of each classifier and datat fiel 
			DataSet TempData ;
		for (int i = 0; i <classes; i++) {
	      		for (int j = i+1; j < classes; j++) {
	      			if (useAllFeatures){
	      				
	      				logger.info(" now genareting data for pair "+i+" vs "+j+" using all  features ");
	    	      		
	      				 TempData = FullData.GenearteClassDataSetTwoClasses(i,j);
	      			}
	      			else {
	      				logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
	    	      		
					  TempData = FullData.GenearteClassDataSetTwoClasses(i,j,featuresArrays.get(k));
	      			}
				
					String Datafile="C"+i+"_"+j+".txt";
					String ModelFile="C"+i+"_"+j+".model";
				
					TempData.GenearteBinaryTargest(i, j);
					TempData.setFormat(2);
					TempData.SaveToFile( Datafile);
				//	logger.info("train the file "+Datafile+ " to produce model file "+ModelFile);
				//	classifier.TrainLibSVM(alg,Datafile,ModelFile);
					k++;
				}
		}
		
		}
		
		
 if (trainAll){	
		for (int i = 0; i <classes; i++) {
      		for (int j = i+1; j < classes; j++) {
      			//logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
			//	DataSet TempData = FullData.GenearteClassDataSetTwoClasses(i,j,featuresArrays.get(k));
				//TempData.setFormat(2);
				String Datafile="C"+i+"_"+j+".txt";
				String ModelFile="C"+i+"_"+j+".model";
			
		//		TempData.GenearteBinaryTargest(i, j);
	//			TempData.SaveToFile( Datafile);
				logger.info("train the file "+Datafile+ " to produce model file "+ModelFile);
				classifier.TrainLibSVM(alg,Datafile,ModelFile);
			//	k++;
			}
		}
	}
			
		
	}
	public void TestSystemOVO(String filename){
		//read data from the system
		FullData.ReadFromFile(filename);
		int k=0;
		ClassifierApp classifier = new ClassifierApp ();
		//genearte the feature set of each classifier and datat fiel 
		
		if (this.genearteTestFiles){
			DataSet TempData;
			for (int i = 0; i <classes; i++) {
		      		for (int j = i+1; j < classes; j++) {
		      			//logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
		      		 
		      			 if (useAllFeatures){
		      				 TempData =FullData;
		      				logger.info(" now genareting data for pair "+i+" vs "+j+" using all  features ");
		      				TempData.setFormat(2);
							String Datafile="C_Test_OVO.txt";
						 
							//TempData.GenearteBinaryTargest(i, j);
							TempData.SaveToFile(Datafile);
		      				
		      			        j=10;
		      			        i=10;
		      			        break;
		      				
		      			 }
		      			 else {
		      				 
		      				logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
		   	      		 
					       TempData = FullData.GenearteFeatureDataSet(featuresArrays.get(k));
							TempData.setFormat(2);
							String Datafile="C_Test"+i+"_"+j+".txt";
							String ModelFile="C"+i+"_"+j+".model";
							String PerdictFile="C"+i+"_"+j+".out";
							//TempData.GenearteBinaryTargest(i, j);
							TempData.SaveToFile(Datafile);
		      			 }
						 
						k++;
					}
			}
			}
		
		
		
		if (this.testAll){
		DataSet TempData;
		for (int i = 0; i <classes; i++) {
	      		for (int j = i+1; j < classes; j++) {
	      			//logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
//	      		 
//	      			 if (useAllFeatures){
//	      				 TempData =FullData;
//	      				logger.info(" now genareting data for pair "+i+" vs "+j+" using all  features ");
//	   	      		 
//	      			 }
//	      			 else {
//	      				 
//	      				logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
//	   	      		 
//				       TempData = FullData.GenearteFeatureDataSet(featuresArrays.get(k));
//	      			 }
                   String Datafile="";
//					TempData.setFormat(2);
	      			 if (useAllFeatures){
	      				Datafile="C_Test_OVO.txt";
	      			 }
	      			 else {
				          Datafile="C_Test"+i+"_"+j+".txt";
	      			 }
					String ModelFile="C"+i+"_"+j+".model";
					String PerdictFile="C"+i+"_"+j+".out";
//					//TempData.GenearteBinaryTargest(i, j);
//					TempData.SaveToFile(Datafile);
					logger.info("testing  the files "+Datafile+ " to produce model file "+ModelFile);
					
					classifier.TestLibSVM(alg,Datafile,ModelFile,PerdictFile);
					//ResultSetAcc
				//	k++;
				}
		}
		}
		//all the classifer has genearted their outputs now get the accuarcy 
		
		//reacd all perdicct files of all 
		
		
		
		ResultSetAcc[][] ClassifiersResults=new ResultSetAcc [classes][classes];
		
		for (int i = 0; i <classes; i++) {
      		for (int j = i+1; j < classes; j++) {
      			logger.info(" Reading the result of  C"+i+"_"+j+".out");
      			String PerdictFile="C"+i+"_"+j+".out";
      			
      			ResultSetAcc  results= new ResultSetAcc();
      			//results.setTargets(FullData.getAllTargets());
      			results.ReadFromLibSVMFile(PerdictFile );
      				//get the 
      			
      			ClassifiersResults[i][j]=results;
      		}
      		
		}
		
		
		logger.info("  After reading alll perdicts and now compute accuarcy.... .");
		ArrayList<Integer> Finalresults=new 	ArrayList<Integer>();
		double acc=0;
		double [][] classifieracc=new 	double [classes][classes];
		double [][] errors=new double [classes][classes];
		summaryDetails.info("  The feaures used in this test ..........................");
		summaryDetails.info(FullData.getAllFeatureString());
		int ErrorLeadTowrong=0;
		int ErrorLeadToNoWrong=0;
		boolean errorflag=false;;
		for (int f = 0; f < classifieracc.length; f++) {
			for (int i = 0; i < classifieracc[f].length; i++) {
				classifieracc[f][i]=0;
				errors[f][i]=0;
			}
		}
		ArrayList <Integer> Targets; 
	    Targets=FullData.getAllTargets();
	    
	    int [] votes;
	    int d; //target
	    double score;
	   ArrayList<String> classifiersWrong=new ArrayList<String>();
	    logger.trace("  start computing the scores...........");
		for (int s = 0; s < FullData.getNumOfSamples(); s++) {
		     d=Targets.get(s);  // the main target of this sample
		     
		     //init the votes 
		     votes=new int[classes];
		     for (int i = 0; i < votes.length; i++) {
				votes[i]=0;
			}
		     errorflag=false;
		     
		     classifiersWrong=new ArrayList<String>();
			//
		     
		     //check the result of each classifer. 
			for (int i = 0; i <classes; i++) {
	      		for (int j = i+1; j < classes; j++) {
	 
	      			
	      			 score=ClassifiersResults[i][j].getPerdicts().get(s);
	      			 
	      			 if (score>=1){
	      				 votes[i]++;}
	      				 else {votes[j]++;}
	      			 
	      			 //compute the accuarcy of the classifier 
	      			 if (d==i || d==j){
	      				 if (score==1)
	      				 {
	      					 if (d==i)
	      					 {
	      						 // correct in classifier 
	      						classifieracc[i][j]++;
	      					 }
	      					 else{
	      					   errorflag=true;
	      						//error in classifer .  
	      						errors[i][j]++;
	      					    classifiersWrong.add( " ["+i+" Vs. "+j+"] ");
	      						logger.warn("  sample  "+s+" error in classifier  "+i+"  vs.  "+j+"  target is "+d+"  classified as "+i);
	      					 }
	      					 
	      				 }else if (score<1)
	      				 {
	      					 if (d==j)
	      					 {
	      						 // correct in classifier 
	      						classifieracc[i][j]++;
	      					 }
	      					 else{
	      						 errorflag=true;
	      						//error in classifer .  
	      						errors[i][j]++;
	      					  classifiersWrong.add( " ["+i+" Vs. "+j+"] ");
	      						logger.warn("  sample  "+s+" error in classifier  "+i+"  vs.  "+j+"  target is "+d+"  classified as "+j);
	      					 }
	      					 
	      				 }//elses score 
	      				 
	      			 }//if j or i is d 
	      			 
	      		}//for j
	      		
			}//for i 
			    
		
			
			
			//get the best votes. 
			int finalvote=getMaxIndex(votes);
			Finalresults.add(new Integer(finalvote));
			if (d==finalvote){
				//correct 
				acc++;
				if (errorflag)
				{
					ErrorLeadToNoWrong++;
				
					
				}
			}
			else //wrong  
			{
				if (errorflag)
				{
					String classifierWrong="";

					  
					  for (int l = 0; l < classifiersWrong.size(); l++) {
						  classifierWrong+=" "+classifiersWrong.get(l);
					}
					summaryDetails.info("  Sample "+s+"  Error  classified as "+finalvote+" with "+votes[finalvote]+" votes supposed to be "+d+" but got "+votes[d]+"  votes (classifiers that made mistakes are ==>"+classifierWrong+" )  ");
					ErrorLeadTowrong++;
					
				}
			}//else 
			
			
			
			
			
			
		}// for no. of sample s
		int numberOfCorrect=(int) acc;
	acc/=((double)FullData.getNumOfSamples());
	acc=acc*100.0;
	String kernel="";
	if (alg==5||alg==2)
	{
		kernel=" SVM RBF ";
	}
	else if (alg==0||alg==1)
	{
		kernel="SVM Linear ";
	}
	summary.info("  This test uses "+kernel+" kernel and feaures used in this test are    ");
	summary.info(FullData.getAllFeatureString());
	summary.info("         ");
	summary.info("   the final accuracy is "+acc+" %  = ("+numberOfCorrect+"/ 10000)");
	summary.info("   The details of results are ... ");
 
	summary.info("  There are  "+ErrorLeadToNoWrong+ " errors lead to no wrong classification ");
	summary.info("  There are  "+ErrorLeadTowrong+ " errors lead to  wrong classification ");
	summary.info("   ");
	int numC=0;
	double TotalPercent=((1.0/2000.0)*100);
	double per=0;
		for (int i = 0; i <classes; i++) {
	      		for (int j = i+1; j < classes; j++) {
	      		per=TotalPercent*	 classifieracc[i][j];
	      		summary.info(" Classifier  "+i+" & "+j+"  = "+per+" % ("+classifieracc[i][j]+" /2000) [ "+errors[i][j]+" error] ");
	      		}
	      		
		
		}
	
		
		
	}//function
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%OVA%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5%%%
	
	
	
	public void TrainSystemOVA(String filename){
		//read data from the system
	logger.info(" in the train ..........");
		int k=0;
		ClassifierApp classifier = new ClassifierApp ();
		if (genearteTrainFiles){
			FullData.ReadFromFile(filename);
		//genearte the feature set of each classifier and datat fiel 
			DataSet TempData ;
		for (int i = 0; i <classes; i++) {
	      		 
	      			//logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
	      			//if (useAllFeatures)
	      				 TempData =   FullData.CreateOVADataset(i);
	      			 
					TempData.setFormat(2);
					String Datafile="C"+i+".txt";
					String ModelFile="C"+i+".model";
				
					//TempData.GenearteBinaryTargest(i, j);
					TempData.SaveToFile( Datafile);
				//	logger.info("train the file "+Datafile+ " to produce model file "+ModelFile);
				//	classifier.TrainLibSVM(alg,Datafile,ModelFile);
					//k++;
			 
		}
		
		}
 
				
			//	k++;
 if (trainAll){	
		for (int i = 0; i <classes; i++) {
  
			String Datafile="C"+i+".txt";
			String ModelFile="C"+i+".model";
			
		//		TempData.GenearteBinaryTargest(i, j);
	//			TempData.SaveToFile( Datafile);
				logger.info("train the file "+Datafile+ " to produce model file "+ModelFile);
				classifier.TrainLibSVM(alg,Datafile,ModelFile);
			//	k++;
	 
		}
	}
			
		
	}
	public void TestSystemOVALibrary(String filename){
		
		
		 
		 
		 FullData.ReadFromFile(filename);
			ClassifierApp classifier = new ClassifierApp ();
			//genearte the feature set of each classifier and datat fiel 
			
			DataSet TempData;
		 
			if (testAll){
				for (int i = 0; i <classes; i++) {
			      		//for (int j = i+1; j < classes; j++) {
			      			logger.info(" now genareting data for pair "+i+" vs all using features all features ");
			      		 
			      		//	 if (useAllFeatures)
			      				 TempData =FullData;
//			      			 else 
//						       TempData = FullData.GenearteFeatureDataSet(featuresArrays.get(k));
							
							TempData.setFormat(2);
							String Datafile="C_Test"+i+".txt";
							String ModelFile="C"+i+".model";
							String PerdictFile="C"+i+".out";
							//TempData.GenearteBinaryTargest(i, j);
							TempData.SaveToFile(Datafile);
							logger.info("testing  the files "+Datafile+ " to produce model file "+ModelFile);
							
							classifier.TestLibSVM(alg,Datafile,ModelFile,PerdictFile);
							//ResultSetAcc
							//k++;
						//}
				}//for i 
				}
		 
			int k=0;
		//	ClassifierApp classifier = new ClassifierApp ();
			//genearte the feature set of each classifier and datat fiel 
			
			//DataSet TempData;
			 SVMClassifier[] svmC=new  SVMClassifier[classes];
			for (int i = 0; i <classes; i++) {
				      String ModelFile="C"+i+".model";
				      svmC[i]=new  SVMClassifier(false);
						svmC[i].loadClassifier(ModelFile);
						
			}//for is 
			
			
			logger.info("  After reading alll perdicts and now compute accuarcy.... .");
			ArrayList<Integer> Finalresults=new 	ArrayList<Integer>();
			double acc=0;
			double [] classifieracc=new 	double [classes];
			double [] errors=new double [classes];
			int UndefinedClassification=0;
			
			int ErrorLeadTowrong=0;
			int ErrorLeadToNoWrong=0;
			boolean errorflag=false;;
			for (int f = 0; f < classifieracc.length; f++) {
				
					classifieracc[f]=0;
					errors[f]=0;
				
			}
			ArrayList <Integer> Targets; 
		    Targets=FullData.getAllTargets();
		    
		    double [] votes;
		    double[] types;
		    int d; //target
		    double score;
		    Double[] featuresSet;
		    logger.trace("  start computing the scores...........");
			for (int s = 0; s < FullData.getNumOfSamples(); s++) {
			     d=Targets.get(s);  // the main target of this sample
			     featuresSet=FullData.getSample(s);
			     //init the votes 
			     votes=new double[classes];
			     types=new double[classes];
			     for (int i = 0; i < votes.length; i++) {
					votes[i]=0.0;
					types[i]=0.0;
				}
			     errorflag=false;
				//
			     
			     //check the result of each classifer. 
				for (int i = 0; i <classes; i++) {
		      		//for (int j = i+1; j < classes; j++) {
		 
		      			
		      			 Classification scoreC = svmC[i].classifySingle(featuresSet);
		      			 votes[i]=scoreC.getHighestConfidence();
		      			// types[i]=scoreC.getHighestConfidenceType();
		      			 if (i==d){
		      			 if (votes[i]>0)
		      			 {
		      				classifieracc[i]++;
		    				
		      				 //correct classification..............
		      				 
		      			 }
		      			 else {
		      				 
		      				 //error 
		      				errors[i]++;
		      				errorflag=true;
		      				 
		      			 }//else 
		      			 }//if i==d
		      			 
		      			 
				}//for i 

			
				//get the best votes. 
				int firstindex=getMaxIndex(votes);
				
				
				
				Finalresults.add(new Integer(firstindex));
			//	logger.trace("  ---------------- " +d+"   classifier as "+firstindex);
				if (d==firstindex){
					//correct
					
					
					acc++;
					if (errorflag)
					{
						ErrorLeadToNoWrong++;
						
					}
				}
				else //wrong  
				{
					
					if (firstindex==-1){
						UndefinedClassification++;
						logger.warn("Sample "+s+" wrong classification between supposed be "+d+" classified as  none");
						
					}else{
					logger.warn("Sample "+s+" wrong classification between supposed be "+d+" classified as "+firstindex+" with value of "+votes[firstindex]);
					
					
					
					if (errorflag)
					{
						ErrorLeadTowrong++;
						
					}
					
					}
				}//else 
				
				
				
				
				
				
			}// for no. of sample s
			int numberOfCorrect=(int) acc;
		acc/=((double)FullData.getNumOfSamples());
		acc=acc*100.0;
		summary.info("  The feaures used in this test ..........................");
		summary.info(FullData.getAllFeatureString());
		summary.info("   the final accuracy is "+acc+" %  = ("+numberOfCorrect+"/ 10000)");
		summary.info("   The details results is ... ");

		summary.info("  there are  "+ErrorLeadToNoWrong+ " errors lead to no wrong classification ");
		summary.info("  there are  "+ErrorLeadTowrong+ " errors lead to  wrong classification ");
		summary.info(" there are "+UndefinedClassification+" sample undifined in classification ");
		int numC=0;
		double TotalPercent=((1.0/1000.0)*100);
		double per=0;
			for (int i = 0; i <classes; i++) {
		      	//	for (int j = i+1; j < classes; j++) {
		      		per=TotalPercent*	 classifieracc[i];
		      		summary.info("  classifier  "+i+"  = "+per+" % ("+classifieracc[i]+" /1000) [ "+errors[i]+" error] ");
		      		//}
		      		
			
			}
		
			
			 
		 
		 
		
		
	}
	public void TestSystemOVA(String filename){
		//read data from the system
		FullData.ReadFromFile(filename);
		int k=0;
		ClassifierApp classifier = new ClassifierApp ();
		//genearte the feature set of each classifier and datat fiel 
		
		DataSet TempData;
		if (testAll){
		for (int i = 0; i <classes; i++) {
	      		//for (int j = i+1; j < classes; j++) {
	      			logger.info(" now genareting data for pair "+i+" vs all using features all features ");
	      		 
	      		//	 if (useAllFeatures)
	      				 TempData =FullData;
//	      			 else 
//				       TempData = FullData.GenearteFeatureDataSet(featuresArrays.get(k));
					
					TempData.setFormat(2);
					String Datafile="C_Test"+i+".txt";
					String ModelFile="C"+i+".model";
					String PerdictFile="C"+i+".out";
					//TempData.GenearteBinaryTargest(i, j);
					TempData.SaveToFile(Datafile);
					logger.info("testing  the files "+Datafile+ " to produce model file "+ModelFile);
					
					classifier.TestLibSVM(alg,Datafile,ModelFile,PerdictFile);
					//ResultSetAcc
					//k++;
				//}
		}//for i 
		}
		//all the classifer has genearted their outputs now get the accuarcy 
		
		//reacd all perdicct files of all 
		
		
		
		ResultSetAcc[] ClassifiersResults=new ResultSetAcc [classes];
		
		for (int i = 0; i <classes; i++) {
      	//	for (int j = i+1; j < classes; j++) {
      			String PerdictFile="C"+i+".out";
      			
      			ResultSetAcc  results= new ResultSetAcc();
      			//results.setTargets(FullData.getAllTargets());
      			results.ReadFromLibSVMFile(PerdictFile );
      				//get the 
      			
      			ClassifiersResults[i]=results;
      		//}
      		
		}
		
		
		logger.info("  After reading alll perdicts and now compute accuarcy.... .");
		ArrayList<Integer> Finalresults=new 	ArrayList<Integer>();
		double acc=0;
		double [] classifieracc=new 	double [classes];
		double [] errors=new double [classes];
		int UndefinedClassification=0;
		
		int ErrorLeadTowrong=0;
		int ErrorLeadToNoWrong=0;
		boolean errorflag=false;;
		for (int f = 0; f < classifieracc.length; f++) {
			
				classifieracc[f]=0;
				errors[f]=0;
			
		}
		ArrayList <Integer> Targets; 
	    Targets=FullData.getAllTargets();
	    
	    double [] votes;
	    int d; //target
	    double score;
	    logger.trace("  start computing the scores...........");
		for (int s = 0; s < FullData.getNumOfSamples(); s++) {
		     d=Targets.get(s);  // the main target of this sample
		     
		     //init the votes 
		     votes=new double[classes];
		     for (int i = 0; i < votes.length; i++) {
				votes[i]=0.0;
			}
		     errorflag=false;
			//
		     
		     //check the result of each classifer. 
			for (int i = 0; i <classes; i++) {
	      		//for (int j = i+1; j < classes; j++) {
	 
	      			
	      			 score=ClassifiersResults[i].getPerdicts().get(s);
	      			 votes[i]=score;
	      			 if (i==d){
	      			 if (score>0)
	      			 {
	      				classifieracc[i]++;
	    				
	      				 //correct classification..............
	      				 
	      			 }
	      			 else {
	      				 
	      				 //error 
	      				errors[i]++;
	      				errorflag=true;
	      				 
	      			 }//else 
	      			 }//if i==d
	      			 
	      			 
			}//for i 

		
			//get the best votes. 
			int firstindex=getMaxIndex(votes);
			
			
			
			Finalresults.add(new Integer(firstindex));
			if (d==firstindex){
				//correct 
				acc++;
				if (errorflag)
				{
					ErrorLeadToNoWrong++;
					
				}
			}
			else //wrong  
			{
				
				if (firstindex==-1){
					UndefinedClassification++;
					logger.warn("Sample "+s+" wrong classification between supposed be "+d+" classified as  none");
					
				}else{
				logger.warn("Sample "+s+" wrong classification between supposed be "+d+" classified as "+firstindex+" with value of "+votes[firstindex]);
				
				
				
				if (errorflag)
				{
					ErrorLeadTowrong++;
					
				}
				
				}
			}//else 
			
			
			
		}// for no. of sample s
		int numberOfCorrect=(int) acc;
	acc/=((double)FullData.getNumOfSamples());
	acc=acc*100.0;
	summary.info("  The feaures used in this test ..........................");
	summary.info(FullData.getAllFeatureString());

	summary.info("");
	summary.info("   the final accuracy is "+acc+" %  = ("+numberOfCorrect+"/ 10000)");
	summary.info("   The details results is ... ");
 
	summary.info("  There are  "+ErrorLeadToNoWrong+ " Errors lead to no wrong classification ");
	summary.info("  There are  "+ErrorLeadTowrong+ " Errors lead to  wrong classification ");
	summary.info("");
//	summary.info(" there are "+UndefinedClassification+" sample undifined in classification ");
	int numC=0;
	double TotalPercent=((1.0/1000.0)*100);
	double per=0;
		for (int i = 0; i <classes; i++) {
	      		for (int j = i+1; j < classes; j++) {
	      		per=TotalPercent*	 classifieracc[i];
	      		summary.info("  classifier  "+i+" & "+j+"  = "+per+" % ("+classifieracc[i]+" /1000) [ "+errors[i]+" error] ");
	      		}
	      		
		
		}
	
		
		
	}//function
	
	
	
	
	
	
	
	
public void setAlg(int alg) {
		this.alg = alg;
	}
	//	private int getMaxVotes(double[] votes) {
//		int index=-1;
//		double max=-1;
//		for (int i = 0; i < votes.length; i++) {
//			if (votes[i]>max){
//				max=votes[i];
//				index=i;
//			}
//			return index;
//	}
	private int getMaxIndex (double[] votes){
		int index=0;
		double max=votes[0];
		for (int i = 0; i < votes.length; i++) {
			if (votes[i]>max){
				max=votes[i];
				index=i;
			}
		}
		return index;
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
	
	public void OptimizeC(String filename){
		 
		SVMClassifier svmc=setUpValidaton(filename);
		double [] C_array={100,200,400,600,150,250,222,50,10,1};
		 // now i need to change generate list of c  
		double  cStart,cEnd,cStep;
		double gStart,gEnd,gStep=0;
		 
	double max=Double.MIN_VALUE,maxg=0,maxc=0;
	double	 C=AppDataSettings.C;
	   double    gamma=AppDataSettings.g ;					
			int test=0;
//			 cStart=C/(100.0*Math.random());
//			 cEnd=C*(100.0*Math.random());
//			  gStart=-10;
//				 gEnd=10;
//			  gStart=gamma/(100.0*Math.random());
//			  gEnd=gamma*100.0*Math.random();
			  summary.info("-------------------------starting the cross validation-----------------------------------------");
			 double accurracy =  svmc.RunCross(C,gamma);
				 summary.info("  first  c = "+C+" g ="+gamma +  "  with accuraray   "+accurracy);
				 max=accurracy;
				 maxc=C;
				 maxg=gamma;				 
			 boolean maxchanged=false;
			 

		 for (int i = 0; i < C_array.length; i++) {
			
	//now try to get the cross validation 
			        
			C=C_array[i];
					//gamma=g+(Math.random());
//					if (c==136 && g==-10.5)
//						continue;
					logger.info(" now start test no. "+i+"  with C= "+C+" and gamma = "+gamma);
					 accurracy =	  svmc.RunCross(C,gamma);
				 summary.info(" i =  "+i+ "---- test no. = " + test + " c = "+C+"  g= "+gamma+"   ACu  "+ (accurracy*100));
			
					 if (accurracy>max)
					 {
						 maxchanged=true;
						 summary.info( "**************"+accurracy+"  ********************now i have the changed into  c = "+C+" g ="+gamma +  "  with accuraray   "+accurracy+"  in loop "+i+" test no. "+i);
						 max=accurracy;
						 maxc=C;
						 maxg=gamma;
					 }
				 

			 // i need to change start and end of c and g to start a new loop 
		 
			 
			 
		}
		 AppDataSettings.C=maxc;
		 AppDataSettings.g=maxg;
			summary.info(" After cross refrence better is  c = "+maxc+" g ="+maxg + " with accuracy "+max );
		       // logger.error( "accuracy   = "+ accuracy *100);
			  summary.info("---------------------------finish the ---------------------------------------");
			  //
	}
	public void OptimizeG(String filename){
		SVMClassifier svmc=setUpValidaton(filename);
		double [] g_array={0.00001,0.0000200,0.0000400,0.000000600,0.150,0.00250,0.000222,0.50,1.0,10};
		 // now i need to change generate list of c  
		double  cStart,cEnd,cStep;
		double gStart,gEnd,gStep=0;
		 
	double max=Double.MIN_VALUE,maxg=0,maxc=0;
	double	 C=AppDataSettings.C;
	   double    gamma=AppDataSettings.g ;					
			int test=0;

			  summary.info("-------------------------starting the cross validation-----------------------------------------");
			 double accurracy =  svmc.RunCross(C,gamma);
				 summary.info("  first  c = "+C+" g ="+gamma +  "  with accuraray   "+(accurracy*100));
				 max=accurracy;
				 maxc=C;
				 maxg=gamma;				 
			 boolean maxchanged=false;
			 

		 for (int i = 0; i < g_array.length; i++) {
			
	//now try to get the cross validation 
			        
			gamma=g_array[i];
					//gamma=g+(Math.random());
//					if (c==136 && g==-10.5)
//						continue;
					logger.info(" now start test no. "+i+"  with C= "+C+" and gamma = "+gamma);
					 accurracy =svmc.RunCross(C,gamma);
				 summary.info(" i =  "+i+ "---- test no. = " + test + " c = "+C+"  g= "+gamma+"   ACu  "+ (accurracy*100));
			
					 if (accurracy>max)
					 {
						 maxchanged=true;
						 summary.info( "**************"+(accurracy*100)+"  ********************now i have the changed into  c = "+C+" g ="+gamma +  "  with accuraray   "+accurracy+"  in loop "+i+" test no. "+i);
						 max=accurracy;
						 maxc=C;
						 maxg=gamma;
					 }
				 

			 // i need to change start and end of c and g to start a new loop 
		 
			 
			 
		}
		 AppDataSettings.C=maxc;
		 AppDataSettings.g=maxg;
			summary.info(" After cross refrence better is  c = "+maxc+" g ="+maxg + " with accuracy "+max );
		       // logger.error( "accuracy   = "+ accuracy *100);
			  summary.info("---------------------------finish the ---------------------------------------");
	
	}
	public SVMClassifier setUpValidaton(String filename){
		
		FullData.ReadFromFile(filename);
		//	String Datafile="OVOAllTrain.txt";
			String ModelFile="CValidate.model";
		
		    
			//FullData.setFormat(2);
			//FullData.SaveToFile( Datafile);
			 SVMClassifier svmc=new SVMClassifier(true);
			 svmc.initProblem (FullData);	
			 return svmc;
		
	}
	public   void CrossValidate(String filename){
		 
		FullData.ReadFromFile(filename);
	//	String Datafile="OVOAllTrain.txt";
		String ModelFile="CValidate.model";
	
	    
		//FullData.setFormat(2);
		//FullData.SaveToFile( Datafile);
		 SVMClassifier svmc=new SVMClassifier(true);
		 svmc.initProblem (FullData);	
		 
		 
		
		 int gridtime=5;
		 int loopTimes=5;
		 // now i need to change generate list of c  
		double  cStart,cEnd,cStep;
		double gStart,gEnd,gStep=0;
		 
		double max=Double.MIN_VALUE,maxg=0,maxc=0;
//		 cStart=-100;
//		  cEnd=1000;
		
		  
	double	 C=AppDataSettings.C;
	   double    gamma=AppDataSettings.g ;
			
		
			int test=0;
			 cStart=C/(100.0*Math.random());
			 cEnd=C*(100.0*Math.random());
//			  gStart=-10;
//				 gEnd=10;
			  gStart=gamma/(100.0*Math.random());
			  gEnd=gamma*100.0*Math.random();
			  summary.info("-------------------------starting the cross validation-----------------------------------------");
			 double accurracy =  svmc.RunCross(C,gamma);

				 summary.info("  first  c = "+C+" g ="+gamma +  "  with accuraray   "+(accurracy*100));
				 max=accurracy;
				 maxc=C;
				 maxg=gamma;

				 cStep=(double)(cEnd-cStart)/ (double)loopTimes;
				  gStep=(double)(gEnd-gStart)/ (double)loopTimes;

				 
				 
			 boolean maxchanged=false;
			 

		 for (int i = 0; i <  gridtime; i++) {
			
			 
			 cStep=(double)(cEnd-cStart)/ (double)loopTimes;
			 
			for (double c=cStart; c < cEnd; c+=cStep) {
//				if (c==136)
//					c=140;
		 //logger.error( "zzzzzzzzzzzz"+c);
//				 gStart=10;
//				 gEnd=1000;
				 gStep=(double)(gEnd-gStart)/ (double)loopTimes;
				for (double g = gStart; g < gEnd; g+=gStep) {
					
		            			//now try to get the cross validation 
			        
			C=c+(Math.random()*100);
				gamma=g+(Math.random());
//					if (c==136 && g==-10.5)
//						continue;
					logger.info(" waiting for result of test no.  =  "+test+ " c = "+C+"  g= "+gamma);
						
					 accurracy =	  svmc.RunCross(C,gamma);
				 summary.info(" i =  "+i+ "---- test no. = " + test + " c = "+C+"  g= "+gamma+"   ACu  "+ (accurracy*100));
			
					 if (accurracy>max)
					 {
						 maxchanged=true;
						 summary.info( "**************"+accurracy+"  ********************now i have the changed into  c = "+C+" g ="+gamma +  "  with accuraray   "+(accurracy*100)+"  in loop "+i+" test no. "+test);
						 max=accurracy;
						 maxc=C;
						 maxg=gamma;
					 }
					 test++;
						
				}//for the g
				
			}// for the c 

			 // i need to change start and end of c and g to start a new loop 
			
			cStart= (maxc -cStep)-(Math.random()*cStep);
//			if (cStart==136)
//				cStart--;
			cEnd=maxc+cStep+(Math.random()*cStep);
			 if (cStart<0){
				 
				 cStart= (maxc /cStep);
//					if (cStart==136)
//						cStart--;
					cEnd=maxc*cStep;
			 }
			
			gStart= maxg -gStep-(Math.random()*gStep);
			gEnd=maxg+gStep+(Math.random()*gStep);
			
	 if (gStart<0){
				 
				 gStart= (maxg /gStep);
//					if (cStart==136)
//						cStart--;
					gEnd=maxg*gStep;
			 }
			
			
			 
			 
		}
		 AppDataSettings.C=maxc;
		 AppDataSettings.g=maxg;
			summary.info(" After cross refrence better is  c = "+maxc+" g ="+maxg + " with accuracy "+max );
		       // logger.error( "accuracy   = "+ accuracy *100);
			  summary.info("---------------------------finish the cross validation---------------------------------------");
			  //
		     
}
	public void setDataFormat(int formatType) {
		this.format=formatType;
		if (FullData!=null)
		 FullData.setFormat(formatType);
		
	}
	
			
}// the class

