/**
 * 
 */
package applications.subMuliClassifiers;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

 
import classifiers.MultiClassClassifier;
import classifiers.command.CommandClassifierController;
import classifiers.results.ResultSetAcc;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;

/**
 * @author TOSHIBA
 *
 */
public class RunOVOLibLinear  extends Observable implements RunnableTask {
	private static transient final Logger logger = Logger.getLogger(  RunOVOLibLinear .class);
	private static transient final Logger summary=Logger.getLogger("summary");
	private static transient final Logger summaryDetails=Logger.getLogger("SummaryDetails");
	
	private static final String OutDir = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\";
	private static final String OutDir2 = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\ovo\\";
	private static   final String	ErrorDir="D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\error\\";

	
	String[] FeaturesString={"wsb","cx","MCR","cy","lhg","lvg","lhgi","AvgVGapLength","hOw","srby2","frdown","MaxHBlackLengthLocation","MaxNumberOfHBlackBlocks","MaxNumberOfVBlackBlocks","AvgVerticalBlockLengthInRight","wsbInLower","PBinUpVsDown","PbCountU","BlackWide","AverageWideUp","CountLowWide","CountBigWide","SrB3FromLeft","FromRightUp","PbinL4R","PbinL4C","PbinF4C","CountNegativeTransition","CountLargeNegativeTransition","CountZeroTransition","AverageLastinUpper","BorLocationDownLength","CountLowWideVertical","AverageWideRightVertical","CountBigWideVertical"};
	
	
	CommandClassifierController singleClass;
	boolean generateMinistFiles=false;
   boolean CreateValidate=true;
private int classes=10;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		DataBaseConnector.OS=DataBaseConnector.OS_WINDOWS;
			
		RunOVOLibLinear test=new RunOVOLibLinear();
			Thread th=new Thread(test);
			th.run();

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
	 // create the ovo feature files
	if (generateMinistFiles){
		generateDatabaseFiles();
	}
	 // use classifier for each 
		trainDataSet();
		testDataSet();
		
	 // get the prefict 
		// match predict to the files 
		// save confusion files ... 
		
		
	}
	
	
	public void testDataSet(){
		 MultiClassClassifier mult=new 	 MultiClassClassifier ();
		 mult.genearteTrainFiles=false;
		 mult.trainAll=false;
		 mult.initClassifier(CommandClassifierController.TASK_LIB_LIBLINEAR, CommandClassifierController.TASK_SVM_KERNEL_LINEAR);
		 mult.setInputFormat(DataSet.FILE_INPUT_FORMAT_LIBSVM);
		 mult.setUseAllFeatures(true);
		 
		 mult.testAll=true;
		 mult.genearteTestFiles=false;
		 mult.setStoreErrors(true);
		 if(CreateValidate){
		 mult.TestOVOCommand(OutDir+"Features"+ "_validation.txt",OutDir2); 
		 if (mult.getImageEr()!=null){
			 mult.getImageEr().ReadOffsets( OutDir+"Features"+ "_validation_offset.txt");
		     mult.getImageEr().StoreImages(ErrorDir);
		      }
		 }
		 else {
		 mult.TestOVOCommand(OutDir+"Features_test"+".txt",OutDir2);
		 if (mult.getImageEr()!=null){
			 mult.getImageEr().ReadOffsets( OutDir+"Features"+ "_test_offset.txt");
		     mult.getImageEr().StoreImages(ErrorDir);
		      }
		 }
		
	}
	
	public void trainDataSet(){
		logger.info("START THE TRAIN TRAIN TRAIN   TRAIN TRAIN TRAIN  TRAIN TRAIN TRAIN  TRAIN TRAIN TRAIN ");
		 MultiClassClassifier mult=new 	 MultiClassClassifier ();
		 mult.genearteTrainFiles=generateMinistFiles;
		 mult.trainAll=false;
		 
		 mult.initClassifier(CommandClassifierController.TASK_LIB_LIBLINEAR, CommandClassifierController.TASK_SVM_KERNEL_LINEAR);
		 mult.setInputFormat(DataSet.FILE_INPUT_FORMAT_LIBSVM);
		 mult.setUseAllFeatures(true);
		 
		 mult.testAll=true;
		 mult.genearteTestFiles=false;
		 
		 if(CreateValidate){
		 
		 mult.TrainOVOCommand(OutDir+"Features"+"_train.txt",OutDir2);
		 }
		 else {
		 mult.TrainOVOCommand(OutDir+"Features"+".txt",OutDir2);
		 }
		 
	}
	
//	public void trainDataSet(){
//		
//		 
//				for (int i = 0; i <classes; i++) {
//		      		for (int j = i+1; j < classes; j++) {
//		      			//logger.info(" now genareting data for pair "+i+" vs "+j+" using features "+featStrings.get(k));
//					//	DataSet TempData = FullData.GenearteClassDataSetTwioClasses(i,j,featuresArrays.get(k));
//						//TempData.setFormat(2);
//						String Datafile=OutDir+"Features"+i+"v"+j+".txt";
//						if (CreateValidate){
//							
//							Datafile=Datafile.replace(".txt", "_train.txt");
//							 
//						}
//						
//						
//						String ModelFile=OutDir+"C"+i+"v"+j+".model";
//					
//				//		TempData.GenearteBinaryTargest(i, j);
//			//			TempData.SaveToFile( Datafile);
//						logger.info(" train the file "+Datafile+ " to produce model file "+ModelFile);
//						singleClass.Train(Datafile, ModelFile);
//						
//						//classifier.TrainLibSVM(alg,Datafile,ModelFile);
//					//	k++;
//					}
//				}
//			 
//	}
 
	
	public void generateDatabaseFiles(){
		// chose training file s
		// read digits from minist ..
		// save libsvm format file...
		createTrainFile();
//		createSingleTrainFiles();
//		if (!CreateValidate){
//			createSingleTestFiles();
//		}
		//choose test file 
		// read digit form minist 
	}
	public   void runTheDigitsFeature(int format, ArrayList<String> featuresNames,String datasetfilename, int Status){
		logger.info("inside the rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		for (int i = 0; i < classes; i++) {
			digitsForTest.add(new Integer(i));
		}
		
		//digitsForTest.add(new Integer(d2));
		Digit.setFeaturesToCompute(featuresNames);
		Digit.loadAllFeatureArray();
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus(Status);
		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
		if (CreateValidate){
	
			dataset.ConvertToArrays();
			dataset.setRandomizeData(true);
			dataset.doNoShuffle();
			dataset.CreateValidationSet();
		}
	//dataset.setFeaturesToUse(featuresNames);
	dataset.setFormat(format);
	dataset.setStoreSampleOffset(true);
	dataset.SaveToFile( datasetfilename);

	
	}
	public void createTrainFile(){
	
		
		ArrayList<String> feats=null;
		if (FeaturesString!=null){
			feats=new ArrayList<String>();
			for (int i = 0; i < FeaturesString.length; i++) {
				feats.add(FeaturesString[i]);
			}
		}
		
		 this.runTheDigitsFeature( DataSet.FILE_INPUT_FORMAT_LIBSVM, feats, OutDir+"Features"+".txt",DataBaseConnector.TRAIN);
		 
			logger.info( " creating the test filesssssssssssssssssv       ");
		 this.runTheDigitsFeature( DataSet.FILE_INPUT_FORMAT_LIBSVM, feats, OutDir+"Features_test"+".txt",DataBaseConnector.TEST);
			logger.info( " creating feature file       ");
			Digit d=new Digit();
			d.setFeaturesToCompute(feats);
			ArrayList<String> featsfinal = d.getComputedFeatures();
			
			StoreFeatFile( "feat.txt", featsfinal);
		 
		 
		
	}
	 public	 ArrayList<String>  getFeaturesNames(int d1,int d2){
		 ArrayList<String> feats=new ArrayList<String>();
		 
		 
//		 feats.add("wsb"); // remove now (had lower ranke )	 
//		 feats.add("lhg"); // remove now (had lower ranke )
//			feats.add("lvg");// remove now (had lower ranke )	 	 
//		 feats.add("lhgi"); // remove now (had lower ranke )
//			feats.add("lvgi");// remove now (had lower ranke )
//			feats.add("hOw");// remove now (had lower ranke )
//			feats.add("cy");// remove now (had lower ranke )
//			feats.add("pb");// remove now (had lower ranke )
//			//feats.add("srby1");		  //surrond by 1 
//			feats.add("srby2");	 //surrond by 2 
//			feats.add("srby3");	 //surrond by 3 
//		//	feats.add("srby4");	 //surrond by 4 	
//			feats.add("frup");
//			feats.add("frdown");
//			feats.add("frleft");
//			feats.add("frright");
//			feats.add("dirMaxW");
//			feats.add("MaxHBlackLength");
//			feats.add("MaxHBlackLengthLocation");
//			feats.add("MaxNumberOfHBlackBlocks");		
//			feats.add("MaxNumberOfHBlackBlocksLocation");
//			feats.add("MaxVBlackLength");
//			feats.add("MaxVBlackLengthLocation");
//			feats.add("MaxNumberOfVBlackBlocks");		
//			feats.add("MaxNumberOfVBlackBlocksLocation");
//			feats.add("wsbInLower");
//			feats.add("wsbInUpper");
//			feats.add("PBinLeftVsRight");
//			feats.add("PBinUpVsDown");
//			feats.add("PbCountD");
//			feats.add("PbCountU");
//			feats.add("PbCountL");
//			feats.add("PbCountR");
//			feats.add("BlackWide");
//			feats.add("SrB3FromRight");
//			feats.add("FromRightUp");
//	      feats.add("FromLeftDown");	 
//			feats.add("PbinF4R");
//			feats.add("PbinL4R");	
//		 feats.add("PbinL4C");
//			feats.add("PbinF4CinUpper");
//			feats.add("CountNegativeTransition");
//		    feats.add("CountLargeNegativeTransition");
//			 feats.add("CountZeroTransition");
//			 feats.add("SudenChangeFRight");
//			 feats.add("SudenChangeFRightLocation");
//			 feats.add("SudenChangeFLeft");
//			 feats.add("SudenChangeFLeftLocation");
		 
//		 
		 if (d1==0||d2==0){
			feats.add( "wsb");
			feats.add("lhg");
			feats.add("lvg");
			feats.add("BlackWide");
//			feats.add("SrB3FromRight");
//			feats.add("FromRightUp");
			//feats.add("cx");		 
		//	feats.add("MCR");		//
		 }
		 
		 
		 if (d1==1||d2==1){
				feats.add( "pb");
				feats.add("hOw");		//
				 feats.add("CountZeroTransition");
				 feats.add("lhg");
					feats.add("MaxVBlackLength");
					feats.add("BlackWide");
			 }
		 
		 
		 if (d1==2||d2==2){
				feats.add( "frright");
				feats.add("MaxHBlackLength");		 
				feats.add("MaxHBlackLengthLocation");		//
				feats.add("lvgi");// remove now (had lower ranke )
				feats.add("lvg");
				feats.add("lhgi");// remove now (had lower ranke )
				feats.add("lhg");
				feats.add("frleft");
				feats.add("dirMaxW");
				feats.add("PbCountR");
				feats.add("PbCountD");
				feats.add("PbCountL");
			    feats.add("SrB3FromRight");
				feats.add("BlackWide");
				feats.add("frdown");
				//feats.add("PbinF4R");
//				feats.add("PbinF4R");
				feats.add("PbinL4R");
//				feats.add("PbinF4CinUpper");
				feats.add("CountNegativeTransition");
//			    feats.add("CountLargeNegativeTransition");
//				 feats.add("CountZeroTransition");
				 feats.add("SudenChangeFRight");
				 feats.add("SudenChangeFRightLocation");
				 feats.add("SudenChangeFLeft");
			     feats.add("SudenChangeFLeftLocation");
			     feats.add("FromLeftDown");	
			     
			 }
		 
		 
		 if (d1==3||d2==3){
			//	feats.add( "wsb");
				feats.add("frleft");
				feats.add("PbCountR");
				feats.add("PbCountL");
				feats.add("srby3");
				feats.add("pb");
//				feats.add("lhg");
//				feats.add("lvg");
				feats.add("dirMaxW");		 
				feats.add("PBinLeftVsRight");
				 feats.add("SrB3FromRight");
				feats.add("PBinUpVsDown");
				feats.add("BlackWide");
				feats.add("SrB3FromRight");
				 feats.add("CountNegativeTransition"); 
//				feats.add("FromRightUp");
			//	feats.add("wsbInLower");		//
			 }
		 if (d1==4||d2==4){
				feats.add("wsb");
//				feats.add("lhg");
//				feats.add("lvg");
				feats.add("frup");
				feats.add("frdown");
				feats.add("frleft");
				feats.add("frright");
				//feats.add("srby3");	 //surrond by 3 ;
				feats.add("MaxVBlackLength");
				feats.add("MaxVBlackLengthLocation");
				feats.add("dirMaxW");		//
				
//				feats.add("cx");		 
//				feats.add("MCR");		////	
			 feats.add("CountZeroTransition");
				 feats.add("SudenChangeFRight");
				 feats.add("SudenChangeFRightLocation");
//				 feats.add("SudenChangeFLeft");
//				 feats.add("SudenChangeFLeftLocation");
			 }
		 
		 
		 if (d1==5||d2==5){
				feats.add("BlackWide");
				feats.add("SrB3FromRight");
				feats.add("FromRightUp");
				feats.add("PbinF4R");
//				feats.add("PbinL4R");
				feats.add("PbinF4CinUpper");
//				feats.add("CountNegativeTransition");
			    feats.add("CountLargeNegativeTransition");
				//feats.add( "wsb");
//				feats.add("lhg");
//				feats.add("lvg");
//				feats.add("cx");		 
				feats.add("dirMaxW");		//
				feats.add("MaxHBlackLength");		 
				feats.add("MaxHBlackLengthLocation");		//
				feats.add("PBinLeftVsRight");
				feats.add("PbCountR");
				feats.add("PbCountL");
				feats.add("PbCountU");
				feats.add("PbCountD");
				 feats.add("CountZeroTransition");
				 feats.add("SudenChangeFRight");
				 feats.add("SudenChangeFRightLocation");
				 feats.add("SudenChangeFLeft");
				 feats.add("SudenChangeFLeftLocation");
//				feats.add("PBinUpVsDown");
			 }
		 
		 
		 if (d1==6||d2==6){
				feats.add( "wsb");
				feats.add("frleft");
				feats.add("frright");
				feats.add("wsbInLower");	
				feats.add("FromRightUp");
				feats.add("dirMaxW");		//
				feats.add("PBinLeftVsRight");
				feats.add("PBinUpVsDown");
//				feats.add("PbCountR");
				feats.add("PbCountL");
				feats.add("PbCountU");
				 feats.add("CountNegativeTransition"); 
				feats.add("PbCountD");
//				 feats.add("CountZeroTransition");
				 feats.add("SudenChangeFRight");
				 feats.add("SudenChangeFRightLocation");
//				 feats.add("SudenChangeFLeft");
//				 feats.add("SudenChangeFLeftLocation");
//				feats.add("lhg");
//				feats.add("lvg");
//				feats.add("cx");		 
//				feats.add("MCR");		//
			 }
		 
		 
		 
		 if (d1==7||d2==7){
				//feats.add( "wsb");
				feats.add("MaxHBlackLength");		 
				feats.add("MaxHBlackLengthLocation");		//
				feats.add("MaxVBlackLength");
				feats.add("MaxVBlackLengthLocation");
				feats.add("frdown");
				feats.add("frleft");
				feats.add("frup");
				feats.add("PbCountU");
				feats.add("PbinF4R");
//				feats.add("PbinL4R");
//				feats.add("PbinF4CinUpper");
				feats.add("CountNegativeTransition");
			    feats.add("CountLargeNegativeTransition");
			    feats.add("BlackWide");
//				 feats.add("CountZeroTransition");
//				 feats.add("SudenChangeFRight");
//				 feats.add("SudenChangeFRightLocation");
				 feats.add("SudenChangeFLeft");
				 feats.add("SudenChangeFLeftLocation");
//				feats.add("lhg");
//				feats.add("lvg");
//				feats.add("cx");		 
//				feats.add("MCR");		//
			 }
		 
		 
		 if (d1==8||d2==8){
				feats.add( "wsb");
				feats.add("pb");
//				feats.add("lhg");
//				feats.add("lvg");
//				feats.add("cx");
//				feats.add("dirMaxW");		//
				feats.add("BlackWide");
				feats.add("PbCountR");
				feats.add("PbCountL");
				feats.add("PbCountU");
				feats.add("PbCountD");
				feats.add("wsbInUpper");
				feats.add("wsbInLower");		//
				feats.add("PBinLeftVsRight");
				feats.add("PBinUpVsDown");
				feats.add("frup");
				feats.add("frdown");
				feats.add("PbinF4R");
				feats.add("PbinL4R");
				feats.add("PbinF4CinUpper");
//				feats.add("CountNegativeTransition");
//			    feats.add("CountLargeNegativeTransition");
			 }
		 
		 
		 if (d1==9||d2==9){
				feats.add( "wsb");
				feats.add("frdown");
				feats.add("frleft");
				feats.add("frright");
				feats.add("dirMaxW");		//
//				feats.add("lhg");
				feats.add("lhg");
//				feats.add("cx");	
				feats.add("PBinLeftVsRight");
				feats.add("PBinUpVsDown");
				feats.add("wsbInUpper");
				feats.add("MaxVBlackLength");
				feats.add("MaxVBlackLengthLocation");
				feats.add("PbCountR");
//				feats.add("PbCountL");
				feats.add("PbCountU");
				feats.add("PbCountD");
				feats.add("PbinF4R");
				feats.add("PbinL4R");
//				feats.add("PbinF4CinUpper");
				 feats.add("PbinL4C");
			 feats.add("CountNegativeTransition"); 
			 feats.add("CountLargeNegativeTransition");
//			 feats.add("CountZeroTransition");
//			 feats.add("SudenChangeFRight");
//			 feats.add("SudenChangeFRightLocation");
			 feats.add("SudenChangeFLeft");
	         feats.add("SudenChangeFLeftLocation");
			//	feats.add("wsbInLower");		//
			 }
		 
		 
		 
		 ArrayList<String> featsUnrepeated=new ArrayList<String>();
	   boolean found=false;
		 
		 for (int i = 0; i < feats.size(); i++) {
			 found=false;
			 //look for repeated 
			for (int j = 0; j < featsUnrepeated.size(); j++) {
			 if (	feats.get(i).equals(featsUnrepeated.get(j))){
				 
				 found=true;
				 break;
			 }
			}
			
			if (!found){	
				featsUnrepeated.add( new String (feats.get(i)));
			}
			
			
		}
		 
		Digit d=new Digit();
		d.loadAllFeatureArray();
		
		 
		 return  new ArrayList<String> (d.getComputedFeatures());// return featsUnrepeated;
	 }
	public void StoreFeatFile(String filename,ArrayList<String> feats){
	
		  FileOutputStream file; 
	        PrintStream out; // declare a print stream object
	        try {
	         // Create a new file output stream
	        file = new FileOutputStream(filename);

	                // Connect print stream to the output stream
	               out = new PrintStream(file);

	               // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
	                //double dataw; 
	               
	               for (int i = 0; i < feats.size(); i++) {
					out.println(feats.get(i));
					out.println(i);
				} 
	      
	              	  
	              	  
	              	 
						
					 
	    
	                
	        }
	        catch (Exception e){
	                System.err.println (" Error in writing to file");
	        }
		
		
	}
	
//	public void createSingleTrainFiles(){
//		 //this.runTheDigitsFeature(0,1 , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features01.txt");
//		 for (int i = 0; i < classes; i++) {
//			 for (int j = i+1; j < classes; j++) {
//			//	 this.runTheDigitsFeature(i,j , DataSet.FILE_INPUT_FORMAT_LIBSVM, null, OutDir+"Features"+i+"v"+j+".txt",DataBaseConnector.TRAIN);
//			 }
//		}
//	
//		
//	}
	
//	public void createSingleTestFiles(){
//		 //this.runTheDigitsFeature(0,1 , DataSet.FILE_INPUT_FORMAT_ARFF, null, "Features01.txt");
//		 for (int i = 0; i < classes; i++) {
//			 for (int j = i+1; j < classes; j++) {
//				 this.runTheDigitsFeature(i,j , DataSet.FILE_INPUT_FORMAT_LIBSVM, null, OutDir+"Features_Test_"+i+"v"+j+".txt",DataBaseConnector.TEST);
//			 }
//		}
//	
//		
//	}
	@Override
	public void update(Observable o, Object arg) {
		 
		
	}

}
