/**
 * 
 */
package applications.subMuliClassifiers;

import gui.AppDefaults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Scanner;

import org.apache.log4j.Logger;

import applications.subMuliClassifiers.RunMultiClassFeaturesTest.RegionFeaturePair;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.ImageRecognizier;
import classifiers.MultiFeature.MultiClassFeatureRecognizier;
import classifiers.results.ClassifierResult;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.image.FeaturedRegion;
import data.image.RegionCreater;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import util.lib;

/**
 * @author TOSHIBA
 * 
 */
public class RunECOCclassifiers implements RunnableTask {
	private static transient final Logger logger = Logger
			.getLogger(RunECOCclassifiers.class);
	private static final int MaxNumberOfDigit = 10;
	private static final int BasicFeatures = 1;
	private static final String MODE_TRAIN = null;
	int MaxNumberOfClassifier = 30;
	// private boolean arabic=false;
	private int dbType = DataBaseConnector.MADBASE;
	private String settingFilename="set.txt";
	private String classifiersFilename="classifier"+"_Try_"+0;
	private int TrialNo=0;
	private final int MODE_TRAIN_TEST=0;
	private final int MODE_TRAIN_Validate=1;
//	private final int MODE_TRAIN_Validate_TRAIN=2;
//	private final int MODE_TRAIN_Validate_TEST=3;
	private final int MODE_TRAIN_ONLY=4;
	private final int MODE_TEST_ONLY=5;
	
	private int Mode=MODE_TRAIN_TEST;

	private boolean sort=true;
	private int TrainMode=ImageRecognizier.TRAIN_VALIDATE;
	private int classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
	private int startTrainid=0;
	private int Interupt=50;

	/**
	 * 
	 */
	public RunECOCclassifiers() {
		//  
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		RunECOCclassifiers test = new RunECOCclassifiers();
		DataBaseConnector.OS = DataBaseConnector.OS_WINDOWS;
		Thread th = new Thread(test);
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
		ReadSetting(settingFilename);
		
		if (Mode==MODE_TRAIN_Validate)
		{
			RunSingleClassifierDetails();
		}
		
		else{
			RunTaskDetails();
		}
		
		saveSettings(settingFilename);

	}

	@Override
	public void update(Observable o, Object arg) {
		// ImageRecognizier IR=new ImageRecognizier ();
		//		 
		// FeaturedRegion.setFeaturesToCompute(null);

	}

	public void RunTaskDetails() {

		MultiClassFeatureRecognizier classifier = new MultiClassFeatureRecognizier();

		FeaturedRegion.setFeaturesToCompute(null);

		String str = "";
		String newline = System.getProperty("line.separator");

//		for (int i = 0; i < MaxNumberOfClassifier; i++) {
//
//			logger.info(" Classifier  " + i);
//			ClassifierData data = createClassifierData(i);
//
//			data.setType(data.Binary);
//			data.createFeatureNameList();
//			// data.setName(i+"Vs"+k);
//			classifier.addClassifierData(data);
//			logger.info(data);
//
//		}
		int k = 0;
		ArrayList<ClassifierData> dataArray = new ArrayList<ClassifierData>();
 
	//ReadSetting(settingFilename);
		
		
		//ArrayList<ClassifierData> dataArray2;
		dataArray=ReadClassifiersDetails(classifiersFilename);
	
		for (int i = 0; i < dataArray.size(); i++) {
			classifier.addClassifierData(dataArray.get(i));
		}
	 
		logger.info("-----------------------finihsed the settings now train and tst ");
		classifier.setOptions(TrainMode,classifierType, AppDefaults.dbType);
		classifier.initClassifiers();
		classifier.startTrainid = startTrainid;
		classifier.Interupt = Interupt;
		classifier.setType(MultiClassFeatureRecognizier.Binary);
		if (Mode!=MODE_TEST_ONLY){
			classifier.TrainSystem();
		}
		if (Mode==MODE_TEST_ONLY||Mode==MODE_TRAIN_TEST){
		classifier.TestSystemBinary();
		}
		logger.info(classifier.getResult());
		logger
				.info("@###################################################################333");
		logger.info(str);
		saveFinalResult(classifier,classifiersFilename);
		boolean sortSave=sort;
		sort=false;
		SaveClassifiersDetails(dataArray, classifiersFilename);
		sort=sortSave;
//		int oldtrail=TrialNo;
//		TrialNo++;
//		int newTrail=TrialNo;
//		 classifiersFilename= classifiersFilename.replace("_Try_"+oldtrail, "_Try_"+newTrail);
//		saveSettings(settingFilename);
		logger
				.info("##################################################################");
		logger.info(str);
	}

	private void saveFinalResult(MultiClassFeatureRecognizier classifier,
			String Filename) {
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(Filename+"_Result.txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is a comment line Saving the final result ... ");
			out.println("Number of classifiers is "+classifier.getNumberOfClassifiers());
			out.println(" Thre result is ");
			out.println(classifier.getResult());
		 
			out.println("## This is The end of file.......");
		
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.error("Error in writing to file");
	}
	logger.info("Finished writing the settings..........");
		
	}

	public void RunSingleClassifierDetails() {
		ImageRecognizier IR = new ImageRecognizier();
		FeaturedRegion.setFeaturesToCompute(null);

		ArrayList<ArrayList<String>> featArrs = null;
		ArrayList<RegionCreater> regions = null;

		IR.setMainOptions(TrainMode,classifierType);

		String str = "";
		String newline = System.getProperty("line.separator");
		str += newline;

		int k = 0;
		ArrayList<ClassifierData> dataArray = new ArrayList<ClassifierData>();
//		for (int i = 0; i < MaxNumberOfClassifier; i++) {
//			ClassifierData data = null;
//			if (dbType == DataBaseConnector.MADBASE) {
//				// ClassifierData data=createClassifierData(i);
//				data = createClassifierDataForArabic(i);
//			} else {
//
//				data = createClassifierData(i);
//			}
//			if (data != null)
//
//				dataArray.add(data);
//		}

	
		
		//ArrayList<ClassifierData> dataArray2;
		dataArray=ReadClassifiersDetails(classifiersFilename);
		//int[][] profile = CreateClassVFeatureProfile(dataArray);
  
		int trainMod;
		int testMod;
		if (TrainMode==ImageRecognizier.TRAIN_VALIDATE){
			trainMod=DataBaseConnector.VALIDATE_TRAIN;
			testMod=DataBaseConnector.VALIDATE_TEST;
		}
		else {
			trainMod=DataBaseConnector.TRAIN;
			testMod=DataBaseConnector.TEST;
		}
		
		for (int i = 0; i < dataArray.size(); i++) {

			ClassifierData data = dataArray.get(i);
			logger.info(" the data i sin digit c1 " + data.digit
					+ "  and in digit c2 " + data.digitC2);
			DataSet dataset = lib.LoadData(data,
					trainMod, dbType);

			dataset = dataset.GenerateSplitClassesDataSet(data.digit,
					data.digitC2);
			logger.info(" the nubmer of samples in data is "
					+ dataset.getNumOfSamples());
			IR.setUseProbability( data.useProbability);
			IR.setTrainData(dataset);
			// logger.info("the features size array is equal to "+featArrs.size()
			// +"  and the region size array is = "+regions.size());
			IR.BuildClassifier();
			IR.Train();
			dataset = lib.LoadData(data, testMod,
					dbType);

			dataset = dataset.GenerateSplitClassesDataSet(data.digit,
					data.digitC2);
			IR.setTestData(dataset);
			IR.Test();
			ClassifierResult result = IR.getClassifierResult();
			
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
		
	
		int oldtrail=TrialNo;
		TrialNo++;
		int newTrail=TrialNo;
		 classifiersFilename= classifiersFilename.replace("_Try_"+oldtrail, "_Try_"+newTrail);
		//saveSettings(settingFilename);
		 SaveClassifiersDetails(dataArray, classifiersFilename);
		logger
				.info("##################################################################");
		logger.info(str);

	}

	 

	private void saveSettings(String Filename) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(Filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

//			// wirte the type of database
//			out.println("## This is a comment line ");
//			out.println("ClassFile");
//			out.println(classifiersFilename);
//			out.println("trailNo.");
//		 
//			out.println(TrialNo);
//			
//			out.println("### the Type of database of  MNIST "+DataBaseConnector.MNIST+" MADBASE  "+DataBaseConnector.MADBASE);
//			
//			out.println("db");
//			out.println(dbType);
//			
//			out.println("### the Mode of operation run Single experiments "+MODE_TRAIN_Validate+" Train&test "+MODE_TRAIN_TEST+" Train only "+MODE_TRAIN_ONLY+"  Test only "+MODE_TEST_ONLY);
//			
//			out.println("Mode");
//			out.println(Mode);
//			
//			
//	    out.println("###  Start Training at classifier   ");
//			
//			out.println("startTrain");
//			out.println(startTrainid);
//			out.println("###   Interupt at  classifier ");
//		    out.println("Interupt");
//			out.println(Interupt);	
//		 
//			
//	     
//			
//			//	TrainMode=ImageRecognizier.TRAIN_VALIDATE
//			//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
//			
//			out.println("## Train Mode  Train_Test "+ImageRecognizier.TRAIN_TEST+"  Train_Validate "+ImageRecognizier.TRAIN_VALIDATE);
//			out.println("TrainMode");
//			out.println(TrainMode);
//			
//			
//			out.println("## Classifier type SVM "+ImageRecognizier.CLASSIFIER_LIBSVM+
//					"  liblinear "+ImageRecognizier.CLASSIFIER_LIBLINEAR+"  weka "+ImageRecognizier.CLASSIFIER_WEKA);
//			out.println("classifierType");
//			out.println(classifierType);
//			
//			
//			out.println("### the Sort files (True or false) ");
//			
//			out.println("Sort");
//			out.println(sort);
//			out.println("### Finish....");
//			out.println("## This is The end of file.......");
			// wirte the type of database
			out.println("## This is a comment line ");
			out.println("ClassFile");
			out.println(classifiersFilename);
			
//			out.println("##  Datafilename  ");
//			out.println("Datafilename");
//			out.println(Datafilename);
			
			out.println("## trail number  ");
			out.println("trailNo.");
		 
			out.println(TrialNo);
			
			out.println ( "#  OS can be windows  "+DataBaseConnector.OS_WINDOWS+"   or  linux "+ DataBaseConnector.OS_LINUX );
			out.println ( "OS" );
			out.println (DataBaseConnector.OS);
			
			
		
			
			
			out.println("### the Type of database of  MNIST "+DataBaseConnector.MNIST+" MADBASE  "+DataBaseConnector.MADBASE);
			
			out.println("db");
			out.println(dbType);
			
			out.println("### the Mode of operation run Single experiments "+MODE_TRAIN_Validate+" Train&test "+MODE_TRAIN_TEST+" Train only "+MODE_TRAIN_ONLY+"  Test only "+MODE_TEST_ONLY);
			
			out.println("Mode");
			out.println(Mode);
			
			
	       out.println("###  Start Training at classifier   ");
			
			out.println("startTrain");
			out.println(startTrainid);
			out.println("###   Interupt at  classifier ");
		    out.println("Interupt");
			out.println(Interupt);	
//			out.println ( "### GenerateCombinations  with max number of digit in combinations , and features" );
//			out.println ( "GenerateCombinations" );
//			out.println (GenerateCombinations);
//			out.println("startDigit");
//			out.println(startCombinationDigit);
//		
//			out.println ( "DigitCombination" );
//			out.println ( MaxNumberOfDigitCombination);
//		
//			out.println("startFeature");
//			out.println(	startCombinationFeature);	
//			out.println ( "FeaturesCombination" );
//			out.println (MaxNumberOfFeaturesCombination);
//			out.println("## the features fileee ");
//			out.println("featureFilename");
//			out.println(featureFilename);
			
	     
			
			//	TrainMode=ImageRecognizier.TRAIN_VALIDATE
			//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
			
			out.println("## Train Mode  Train_Test "+ImageRecognizier.TRAIN_TEST+"  Train_Validate "+ImageRecognizier.TRAIN_VALIDATE);
			out.println("TrainMode");
			out.println(TrainMode);
			
			
			out.println("## Classifier type SVM "+ImageRecognizier.CLASSIFIER_LIBSVM+
					"  liblinear "+ImageRecognizier.CLASSIFIER_LIBLINEAR+"  weka "+ImageRecognizier.CLASSIFIER_WEKA);
			out.println("classifierType");
			out.println(classifierType);
			
			
			out.println("### the Sort files (True or false) ");
			
			out.println("Sort");
			out.println(sort);
			out.println("### Finish....");
			out.println("## This is The end of file.......");
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.error("Error in writing to file");
	}
	logger.info("Finished writing the settings..........");
	}

	private void ReadSetting(String filename) {
		 ///TrainMode=ImageRecognizier.TRAIN_VALIDATE
		//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename);
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));
			String inputString = "", inputStringInner;
			 int inputInt;
			// boolean finishClassifier=false,finishRegion=false;

			while (input.hasNext()) {
				inputString = input.nextLine();
				if (inputString.startsWith("##"))
					continue;
//
//				// now check for the following
//				if (inputString.trim().startsWith("db")) {
//					inputInt = input.nextInt();
//					this.dbType = inputInt;
//				}
//			 
//				// now check for the following
//				if (inputString.trim().startsWith("ClassFile")) {
//				 
//					this.classifiersFilename= input.nextLine();
//				}
//
//				// now check for the following
//				if (inputString.trim().startsWith("trailNo.")) {
//					inputInt = input.nextInt();
//					this.TrialNo = inputInt;
//				}
//				// now check for the following
//				if (inputString.trim().startsWith("Mode")) {
//					inputInt = input.nextInt();
//					this.Mode = inputInt;
//				}
//
//
//				if (inputString.trim().startsWith("startTrain")) {
//					inputInt = input.nextInt();
//					startTrainid = inputInt;
//				}
//				if (inputString.trim().startsWith("Interupt")) {
//					inputInt = input.nextInt();
//					Interupt = inputInt;
//				}
//				
//				
//				// now check for the following
//				if (inputString.trim().startsWith("Sort")) {
//			 
//					this.sort = input.nextBoolean();
//				}
//  
//				
//	 
//				// now check for the 
//				if (inputString.trim().startsWith("TrainMode")) {
//			 
//					TrainMode = input.nextInt();
//				}
//				//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
//				
//				 
//				// now check for the 
//				if (inputString.trim().startsWith("classifierType")) {
//			 
//					classifierType = input.nextInt();
//				}
				// now check for the following
				if (inputString.trim().startsWith("OS")) {
					inputInt = input.nextInt();
					//inputInt
					DataBaseConnector.OS = inputInt;
				}
				// now check for the following
				if (inputString.trim().startsWith("db")) {
					inputInt = input.nextInt();
					this.dbType = inputInt;
				}
			 
				// now check for the following
				if (inputString.trim().startsWith("ClassFile")) {
				 
					this.classifiersFilename= input.nextLine();
				}
	 
		
			 
				// now check for the following
				if (inputString.trim().startsWith("trailNo.")) {
					inputInt = input.nextInt();
					this.TrialNo = inputInt;
				}
				// now check for the following
				if (inputString.trim().startsWith("Mode")) {
					inputInt = input.nextInt();
					this.Mode = inputInt;
				}


				if (inputString.trim().startsWith("startTrain")) {
					inputInt = input.nextInt();
					startTrainid = inputInt;
				}
				if (inputString.trim().startsWith("Interupt")) {
					inputInt = input.nextInt();
					Interupt = inputInt;
				}
		 
				
				
 
				if (inputString.trim().startsWith("DigitsOnly")) {
					String temp=input.nextLine();
					 //inputInt = input.nextInt();
					// MaxNumberOfDigitCombination = inputInt;
				}
		 
				// now check for the following
				if (inputString.trim().startsWith("Sort")) {
			 
					this.sort = input.nextBoolean();
				}
 
				
	 
				// now check for the 
				if (inputString.trim().startsWith("TrainMode")) {
			 
					TrainMode = input.nextInt();
				}
				//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
				
				 
				// now check for the 
				if (inputString.trim().startsWith("classifierType")) {
			 
					classifierType = input.nextInt();
				}
				 
			}
           
			input.close();
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		logger.info("Finished reading settings.........");
		
	}

	public void SaveClassifiersDetails(ArrayList<ClassifierData> ClassesData,
			String Filename) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			if(sort){
			Collections.sort(ClassesData,
			new Comparator<ClassifierData>() {
				    public int compare(ClassifierData o1, ClassifierData o2) {
				   return (int) (o2.Accuracy- o1.Accuracy);
				        //return o2.size() - o1.size();
				    }
			 });
			}
			// Create a new file output stream
			file = new FileOutputStream(Filename+".txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is a comment line ");
			out.println("## The First few Lines define the format");
			out.println("## The type of database === minist  "
					+ DataBaseConnector.MNIST + "or madbase "
					+ DataBaseConnector.MNIST);
			out.println("## Maximum number of classifiers..  ");
			out
					.println("## Now classifier Data  and actual number of classifier is "
							+ ClassesData.size());
			out.println("## Classifiers For each one write ");
			out.println("## Name  ");
			out.println("## Type (binary or multi class) ");
			out.println("## Digits DS1 (First digits set)");
			out.println("## Digits DS2 (Second digits set)");
			out.println("## Regions of the Classifier ");
			out.println("## Region maxh,maxv,h,v ");
			out.println("##--------------------------------- ");
			out.println("##The type of database ");
			out.println("db");
			out.println(this.dbType);
			out.println("## Maximum number of classifiers..  ");
			out.println("MaxNumber");
			out.println(this.MaxNumberOfClassifier);
			out
					.println("## Now classifier Data  and actual number of classifier is "
							+ ClassesData.size());
			ClassifierData temp;
			ArrayList<Integer> tempd;
			ArrayList<RegionCreater> tempR;
			ArrayList<ArrayList<String>> tempF;

			for (int i = 0; i < ClassesData.size(); i++) {
				out.println("## Classifier======================================================");
				out.println("## Classifier  " + i);
				logger.info("writing classifier  "+i);
				// get the digit
				temp = ClassesData.get(i);
				out.println("Classifier");

				out.println("Name");
				out.println(temp.getName());
				out.println("type");
				out.println(temp.getType());
				out.println("## This is previous result  ");
				out.println("result");
				out.println(temp.Accuracy);
				out.println("## Digits " + i);

				out.println("DS1");
				tempd = temp.digit;

				for (int j = 0; j < tempd.size(); j++) {
					out.print(tempd.get(j));
					out.print(" ");
				}//digit 
				out.println();
				out.println("## Vs digits" + i);

				out.println("DS2");
				tempd = temp.digitC2;

				for (int j = 0; j < tempd.size(); j++) {
					out.print(tempd.get(j));
					out.print(" ");
				}//digit 
				out.println();
				// //////////////now the Regions and featurs..
				// first region...
				//
				out.println("## Regions of the Classifier ");
				 out.println("Regions");
				tempR = temp.regions;
				tempF = temp.feat;

				for (int j = 0; j < tempR.size(); j++) {
					out.println("## Region number " + j);
					out.println("## Region maxh,maxv,h,v ");
					out.println("Region");
					out.println(tempR.get(j).getMaxHorizontalRegion()
							+ "," + tempR.get(j).getMaxVerticalRegion() + ","
							+ tempR.get(j).getHorizonal() + ","
							+ tempR.get(j).getVertical());

					// out.println("R:"+j);
					// out.print(tempR.get(j).getMaxHorizontalRegion());
					// out.print(" ");
					// out.print(tempR.get(j).getMaxVerticalRegion());
					// out.print(" ");
					// out.print(tempR.get(j).getHorizonal());
					// out.print(" ");
					// out.print(tempR.get(j).getVertical());
					// out.print(" ");
					// out.println();
					out.println("## Now featurs for the region " + j);
					out.println("Feat");
					for (int j2 = 0; j2 < tempF.get(j).size(); j2++) {
						if (j2 > 0) {
							out.print(",");
						}
						out.print(tempF.get(j).get(j2));
					}//features 
					out.println();
					out.println("Finish");
				}// region 

				out.println("Finish");
			
			}

			// String[] FeaturesSingle = featureST.split(",");
			//              
			// for (int i = 0; i < FeaturesSingle.length; i++) {
			//					
			// out.println(FeaturesSingle[i]);
			// out.println(i);
			//            	
			//             
			// }

			out.println("## This is The end of file.......");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in writing to file");
		}
		logger.info("Finished writing the properties..........");
	}

	public ArrayList<ClassifierData> ReadClassifiersDetails(String filename) {
		ArrayList<ClassifierData> dataArr =null;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename+".txt");
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));
			String inputString = "", inputStringInner;
			int inputInt;
			int inputDouble;
			ClassifierData data;
			dataArr = new ArrayList<ClassifierData>();
			// boolean finishClassifier=false,finishRegion=false;

			while (input.hasNext()) {
				inputString = input.nextLine();
				// skip any ## comment line..
				if (inputString.startsWith("##"))
					continue;

				// now check for the follwing
				if (inputString.trim().startsWith("db")) {
					inputInt = input.nextInt();
					this.dbType = inputInt;
				}
				if (inputString.trim().startsWith("MaxNumber")) {
					inputInt = input.nextInt();
					this.MaxNumberOfClassifier = inputInt;
				}
				if (inputString.trim().startsWith("Classifier")) {
					logger.info(" Reading a classsifer data  "+dataArr.size());
					// now read the data of classifier
					data = new ClassifierData();
					// now each classifier
					while (input.hasNext() ) {
						inputStringInner = input.nextLine();
						// skip any ## comment line..
						if (inputStringInner.startsWith("##"))
							continue;
//						if (inputStringInner.trim().startsWith("Name")) {
//
//							data.setName(input.nextLine().trim());
//							
//						}
						if (inputStringInner.trim().startsWith("type")) {

							data.setType(input.nextInt());
						}

						if (inputStringInner.trim().startsWith("result")) {

							data.Accuracy = input.nextDouble();
						}

						if (inputStringInner.trim().startsWith("DS")) {
							ArrayList<Integer> d1 = new ArrayList<Integer>();
							String str = input.nextLine();

							String[] digits = str.trim().split(" ");

							for (int i = 0; i < digits.length; i++) {
								d1.add(Integer.parseInt(digits[i]));
							}
							if (inputStringInner.equals("DS1")) {
								data.digit = d1;
							} else {
								data.digitC2 = d1;
							}
						}
						if (inputStringInner.trim().startsWith("Regions")) {
							ArrayList<RegionCreater> tempR = new ArrayList<RegionCreater>();
							ArrayList<ArrayList<String>> tempF = new ArrayList<ArrayList<String>>();

							while (input.hasNext()) {
								logger.info("Reading Regionsss  "+tempR.size());
								String inputStr = input.nextLine();
								// skip any ## comment line..
								if (inputStr.startsWith("##"))
									continue;
								
								if(inputStr.trim().startsWith("Region"))
								{
									String stReg=input.nextLine();
									String[] values=stReg.trim().split(",");
									 
									if (values.length==4){
									int maxh=Integer.parseInt( values[0]);
									int maxv=Integer.parseInt( values[1]);
									int h=Integer.parseInt( values[2]);
									int v=Integer.parseInt( values[3]);
									 RegionCreater reg= getRegion(maxh, maxv, h, v);
									 tempR.add(reg);
									}
								}
								
								if(inputStr.trim().startsWith("Feat"))
								{
									String st=input.nextLine();
									String[] features = st.trim().split(",");
									ArrayList<String> feat=new ArrayList<String>();
									for (int i = 0; i < features.length; i++) {
											feat.add( features[i]);
									}
								
								  tempF.add(feat);	
								}
								if(inputStr.trim().startsWith("Finish"))
								{
									break;
								}

							}// for the regionsss. 
							
							data.regions=tempR;
							data.feat=tempF;

						}

						
						if(inputStringInner.trim().startsWith("Finish"))
						{
							break;
						}
						
					}// while classifier 
                       data.createFeatureNameList();
                       data.createClassifierName();
					dataArr.add(data);
				}

				// finishClassifier=true;

				// GenearateTestFiles = input.nextInt();
				// Train =input.nextInt();
				// Test=input.nextInt();

			}
           
			input.close();
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		logger.info("Finished reading details.........");
		return dataArr;
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

	private ClassifierData createClassifierDataForArabic(int i) {
		ClassifierData dat = new ClassifierData();
		dat.setType(ClassifierData.Binary);
		ArrayList<Integer> digitC1 = new ArrayList<Integer>();
		ArrayList<Integer> digitC2 = new ArrayList<Integer>();
		String name = "";
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		ArrayList<String> AllFeatures2;

		RegionCreater Reg;
		switch (i) {
		case 0:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 9 || j == 6 || j == 1) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 1:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 9 || j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 2:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 4 || j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 3:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 8 || j == 6 || j == 3 || j == 9) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 4:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 3 || j == 7) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 5:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 8 || j == 7) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 6:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 0) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 7:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 1) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 8:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 1 || j == 3 || j == 8) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 2, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 9:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 3 || j == 4 || j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 10:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 4 || j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 11:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 1 || j == 0) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 12:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 4 || j == 6 || j == 9) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 13:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 3 || j == 4) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeaturesForArabic(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		default:
			return null;

		}

		dat.setDigits(digitC1);
		dat.setName(name);
		dat.digitC2 = digitC2;
		dat.feat = feat;
		dat.regions = regions;
		dat.createFeatureNameList();

		// logger.info(" the data i sin digit c1 "+digitC1.size()+"  and in digit c2 "+digitC2.size());
//		logger.info(" the data i  c1 " + dat.digit.size()
//				+ "  and in digit c2 " + dat.digitC2.size());
		return dat;
	}

	private ClassifierData createClassifierData(int i) {
		ClassifierData dat = new ClassifierData();
		dat.setType(ClassifierData.Binary);
		ArrayList<Integer> digitC1 = new ArrayList<Integer>();
		ArrayList<Integer> digitC2 = new ArrayList<Integer>();
		String name = "";
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		ArrayList<String> AllFeatures2;

		RegionCreater Reg;
		switch (i) {
		case 0:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 8 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// / R5

			Reg = getRegion(2, 1, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 1:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 8 || j == 9 || j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 2:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 4 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 3:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 1) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2=null;
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// R6 (0,0 in 1X2)
			//		
			// AllFeatures2 = getGeneralFeatures(i*10);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(2, 1, 1, 0);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);
			//		

			break;

		case 4:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 3 || j == 2) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			//		
			// AllFeatures2 = getGeneralFeatures(i*10);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(2, 2, 0, 1);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);
			//		

			break;

		case 5:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 5 || j == 3 || j == 9 || j == 7) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 6:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 1) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);

			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			AllFeatures2 = getGeneralFeatures(i);

			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			AllFeatures2 = getGeneralFeatures(i);

			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			AllFeatures2 = getGeneralFeatures(i);

			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 1, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 7:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);

			// R5 (1,0 in 2X1)
			Reg = getRegion(2, 2, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);
			//			
			// 0 1 in 1 X2

			break;
		case 9998:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 0 || j == 8) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			// 0 1 in 1 X2
			Reg = getRegion(2, 2, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 9:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 7) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 1, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 10:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 2 || j == 7 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 11:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 5) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// // region and featuressssssss
			// AllFeatures2 = getGeneralFeatures(i);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(2, 1, 0, 0);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);

			break;
		case 12:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 5 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i * 10);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 13:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 5 || j == 3) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 14:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 5 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 99915:
			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 0 || j == 8 || j == 6 || j == 2) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			// 0 1 in 1 X2
			Reg = getRegion(2, 2, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 16:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 4 || j == 5 || j == 6) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 2, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;
		case 17:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 3 || j == 4 || j == 9) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 18:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 3 || j == 2 || j == 5 || j == 7) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		case 19:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 3) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		// case 21:
		//	
		// /////////////digits ...
		// name="Clas_I(";
		// for (int j = 0; j < MaxNumberOfDigit; j++) {
		// if ( j==0||j==8){
		//		
		// digitC1.add(new Integer(j));
		// name+="_"+j;
		// }
		// else {
		// digitC2.add(new Integer(j));
		// }
		// }
		//	  
		// name+=")";
		//	 
		// // region and featuressssssss
		// AllFeatures2 = getGeneralFeatures(i);
		// //AllFeatures2.add("wsb");
		// feat.add(AllFeatures2);
		// Reg=getRegion(1, 1, 0, 0);
		// name+=" In "+Reg.getRegionName();
		// regions.add(Reg);
		//		
		//		  
		//		
		//	
		// break;
		// case 22:
		//	
		// /////////////digits ...
		// name="Clas_I(";
		// for (int j = 0; j < MaxNumberOfDigit; j++) {
		// if ( j==0 ||j==6||j==8||j==9 ){
		//		
		// digitC1.add(new Integer(j));
		// name+="_"+j;
		// }
		// else {
		// digitC2.add(new Integer(j));
		// }
		// }
		//	  
		// name+=")";
		//	 
		// // region and featuressssssss
		// AllFeatures2 = getGeneralFeatures(i);
		// //AllFeatures2.add("wsb");
		// feat.add(AllFeatures2);
		// Reg=getRegion(1, 1, 0, 0);
		// name+=" In "+Reg.getRegionName();
		// regions.add(Reg);
		//		
		//		  
		//		
		//	
		// break;
		// case 23:
		//	
		// /////////////digits ...
		// name="Clas_I(";
		// for (int j = 0; j < MaxNumberOfDigit; j++) {
		// if ( j==0||j==6||j==8 ){
		//		
		// digitC1.add(new Integer(j));
		// name+="_"+j;
		// }
		// else {
		// digitC2.add(new Integer(j));
		// }
		// }
		//	  
		// name+=")";
		//	 
		// // region and featuressssssss
		// AllFeatures2 = getGeneralFeatures(i);
		// //AllFeatures2.add("wsb");
		// feat.add(AllFeatures2);
		// Reg=getRegion(1, 2, 0, 0);
		// name+=" In "+Reg.getRegionName();
		// regions.add(Reg);
		//		
		//		  
		//		
		//	
		// break;

		case 21:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 0) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			//		
			// // region and featuressssssss
			// AllFeatures2 = getGeneralFeatures(i*10);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(2, 1, 0, 0);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);
			//		
			//		
			//		
			// // region and featuressssssss
			// AllFeatures2 = getGeneralFeatures(i*100);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(2, 1, 1, 0);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);
			//		
			//		
			// // region and featuressssssss
			// AllFeatures2 = getGeneralFeatures(i*1000);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(1, 2, 0, 0);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);
			// // region and featuressssssss
			// AllFeatures2 = getGeneralFeatures(i*10000);
			// //AllFeatures2.add("wsb");
			// feat.add(AllFeatures2);
			// Reg=getRegion(1, 2, 0, 1);
			// name+=" In "+Reg.getRegionName();
			// regions.add(Reg);

			break;

		case 26:

			// ///////////digits ...
			name = "Clas_I(";
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (j == 0) {

					digitC1.add(new Integer(j));
					name += "_" + j;
				} else {
					digitC2.add(new Integer(j));
				}
			}

			name += ")";

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i * 10);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i * 100);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(2, 1, 1, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i * 1000);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 2, 0, 0);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);
			// region and featuressssssss
			AllFeatures2 = getGeneralFeatures(i * 10000);
			// AllFeatures2.add("wsb");
			feat.add(AllFeatures2);
			Reg = getRegion(1, 2, 0, 1);
			name += " In " + Reg.getRegionName();
			regions.add(Reg);

			break;

		default:
			return null;

		}

		dat.setDigits(digitC1);
		dat.setName(name);
		dat.digitC2 = digitC2;
		dat.feat = feat;
		dat.regions = regions;
		dat.createFeatureNameList();

		// logger.info(" the data i sin digit c1 "+digitC1.size()+"  and in digit c2 "+digitC2.size());
		logger.info(" the data i  c1 " + dat.digit.size()
				+ "  and in digit c2 " + dat.digitC2.size());
		return dat;
	}

	private RegionCreater getRegion(int maxh, int maxv, int h, int v) {
		RegionCreater reg = new RegionCreater();
		reg.setMaxHorizontalRegion(maxh);
		reg.setMaxVerticalRegion(maxv);
		reg.setHorizonal(h);
		reg.setVertical(v);
		return reg;
	}

	private ArrayList<String> getGeneralFeaturesForArabic(int type) {
		ArrayList<String> AllFeatures2 = new ArrayList<String>();
		// / all features are ==>

		switch (type) {
		case 0: // / 6 9 1
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("CountZeroTransition");
			AllFeatures2.add("CountLowWide");
			AllFeatures2.add("frdown");
			break;

		case 1: // 9 5
			// AllFeatures2.add("fup");
			AllFeatures2.add("wsb");
			// AllFeatures2.add("frright");

			break;
		case 2:
			AllFeatures2.add("frdown");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("frleft");
			AllFeatures2.add("PbinF4C");
			AllFeatures2.add("srby3");
			break;
		case 3:
			AllFeatures2.add("frdown");
			AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("pb");

			break;
		case 4:
			AllFeatures2.add("fup");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountNegativeTransition");
			break;

		case 5:
			AllFeatures2.add("srby3");
			AllFeatures2.add("frright");
			AllFeatures2.add("frleft");
			AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountNegativeTransition");
			AllFeatures2.add("BlackWide");
			AllFeatures2.add("CountBigWide");
			break;
		case 6:
			AllFeatures2.add("hOw");
			AllFeatures2.add("pb");
			break;
		case 7:
			AllFeatures2.add("CountZeroTransition");
			AllFeatures2.add("hOw");
			AllFeatures2.add("pb");
			AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			break;
		case 8:
			AllFeatures2.add("frleft");
			// AllFeatures2.add("pb");
			AllFeatures2.add("PbinF4C");
			AllFeatures2.add("CountZeroTransition");
			break;

		case 9:
			AllFeatures2.add("srby3"); // surrond by 3
			AllFeatures2.add("frleft");

			AllFeatures2.add("PbinF4C");

			break;

		case 10:
			AllFeatures2.add("frleft");
			AllFeatures2.add("srby3");
			AllFeatures2.add("PbinF4C");
			break;

		case 11:

			AllFeatures2.add("pb");
			break;

		case 12:
			AllFeatures2.add("fup");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("AverageWideUp");
			break;
		case 13:
			AllFeatures2.add("frright");
			AllFeatures2.add("frleft");
			AllFeatures2.add("srby3");
			AllFeatures2.add("SrB3FromRight");
			break;
		default:

			break;
		}

		return AllFeatures2;

	}

	private ArrayList<String> getGeneralFeatures(int type) {
		ArrayList<String> AllFeatures2 = new ArrayList<String>();
		// / all features are ==>

		// AllFeatures2.add("wsb");
		// AllFeatures2.add("frup");
		// AllFeatures2.add("frdown");
		// AllFeatures2.add("frleft");
		// AllFeatures2.add("frright");
		// AllFeatures2.add("pb");
		// AllFeatures2.add("dirMaxW");
		// AllFeatures2.add("fromDownLeft");
		// AllFeatures2.add("PbinF4R");
		// AllFeatures2.add("PbinL4R");
		// AllFeatures2.add("PbinL4C");
		// AllFeatures2.add("PbinF4C");
		// AllFeatures2.add("PbinF4CinUpper");
		// AllFeatures2.add("CountNegativeTransition");
		// AllFeatures2.add("CountLargeNegativeTransition");
		// AllFeatures2.add("CountPositiveTransition");
		// AllFeatures2.add("CountZeroTransition");

		switch (type) {
		case 0:

			// AllFeatures2.add("pb");
			AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("hOw");
			// AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			AllFeatures2.add("cx");
			AllFeatures2.add("lhgi");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 1: // classifier,

			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountZeroTransition");
			//			
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("pb");
			break;
		case 2:
			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 3:
			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			AllFeatures2.add("hOw");// remove now (had lower ranke )
			// AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountZeroTransition");
			AllFeatures2.add("BlackWide");
			AllFeatures2.add("AverageWideUp");
			AllFeatures2.add("CountLowWide");
			AllFeatures2.add("CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("CountLowWideVertical");
			// AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			// AllFeatures2.add("cy");// remove now (had lower ranke )
			AllFeatures2.add("lhg");
			AllFeatures2.add("lvg");
			break;

		case 30:
			AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("hOw");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 4:

			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 40:

			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 5:
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			//	

			break;
		case 6:

			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			AllFeatures2.add("hOw");// remove now (had lower ranke )
			AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountZeroTransition");
			// //AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// // AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			// //AllFeatures2.add("cx");
			// //AllFeatures2.add("MCR"); //
			// //AllFeatures2.add("cy");// remove now (had lower ranke )
			// AllFeatures2.add("lhg");
			// AllFeatures2.add("lvg");
			//		
			break;
		case 7:

			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			// AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// // AllFeatures2.add("CountLowWideVertical");
			// // AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			// AllFeatures2.add("cy");// remove now (had lower ranke )
			// AllFeatures2.add("lhg");
			// AllFeatures2.add("lvg");
			break;
		case 8:

			AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");// remove now (had lower ranke )
			// AllFeatures2.add("lhg");
			// AllFeatures2.add("lvg");
			// AllFeatures2.add("frdown");
			break;

		case 9:

			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 10:

			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			AllFeatures2.add("CountNegativeTransition");
			AllFeatures2.add("CountLargeNegativeTransition");
			AllFeatures2.add("CountPositiveTransition");
			AllFeatures2.add("CountZeroTransition");
			break;

		case 11:

			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 12:

			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 120:

			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 13:

			AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures.add("SrB3FromRight");
			// AllFeatures.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 14:

			// AllFeatures2.add("srby3"); // surrond by 3
			AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 15:
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			break;

		case 150:
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			//		
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			// AllFeatures2.add("cy");// remove now (had lower ranke )
			// AllFeatures2.add("lhg");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 16:

			// AllFeatures2.add("srby3"); // surrond by 3

			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 17:

			// AllFeatures2.add("srby3"); // surrond by 3

			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 18:

			AllFeatures2.add("srby3"); // surrond by 3

			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;
		case 19:

			AllFeatures2.add("srby3"); // surrond by 3

			AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// / AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("pb");
			// AllFeatures2.add("dirMaxW");
			// AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("PbinF4CinUpper");
			// AllFeatures2.add("CountNegativeTransition");
			// AllFeatures2.add("CountLargeNegativeTransition");
			// AllFeatures2.add("CountPositiveTransition");
			// AllFeatures2.add("CountZeroTransition");
			break;

		case 20:
			// AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			break;

		case 233331:
			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			// AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// // AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWide");
			// //AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			// AllFeatures2.add("cy");// remove now (had lower ranke )
			break;
		case 24442:
			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			// AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			AllFeatures2.add("CountBigWide");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("MCR");
			// AllFeatures2.add("CountLowWideVertical");
			break;

		case 24443:
			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("CountLowWideVertical");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			break;
		case 24444:
			// AllFeatures2.add("wsb");
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			// AllFeatures2.add("hOw");// remove now (had lower ranke )
			// AllFeatures2.add("pb");// remove now (had lower ranke )
			// AllFeatures2.add("dirMaxW");
			// // AllFeatures2.add("fromDownLeft");
			// AllFeatures2.add("srby3"); // surrond by 3
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("CountLowWideVertical");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			break;
		case 21:
			AllFeatures2.add("wsb");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");

			// AllFeatures2.add("hOw");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			// AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("BlackWide");
			AllFeatures2.add("CountBigWide");
			// AllFeatures2.add("cx");
			// AllFeatures2.add("MCR"); //
			// AllFeatures2.add("cy");// remove now (had lower ranke )
			// AllFeatures2.add("lhg");
			AllFeatures2.add("lvg");

			// AllFeatures2.add("lhgi");
			// AllFeatures2.add("lvgi");
			break;

		case 26:
			AllFeatures2.add("wsb");
			// AllFeatures2.add("frup");
			// AllFeatures2.add("frdown");
			// AllFeatures2.add("frleft");
			// AllFeatures2.add("frright");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("BlackWideVertical");
			AllFeatures2.add("CountBigWideVertical");
			AllFeatures2.add("BlackWide");
			AllFeatures2.add("CountBigWide");
			AllFeatures2.add("cx");
			AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");// remove now (had lower ranke )
			AllFeatures2.add("lhg");
			AllFeatures2.add("lvg");
			// //
			// AllFeatures2.add("lhgi");
			// AllFeatures2.add("lvgi");
			break;
		case 260:
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			AllFeatures2.add("PbinF4R");
			// AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			AllFeatures2.add("cx");
			AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");
			break;
		case 2600:
			// AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			AllFeatures2.add("SrB3FromUp");
			// AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			AllFeatures2.add("cx");
			AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");
			break;

		case 26000:
			AllFeatures2.add("SrB3FromRight");
			// AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			// AllFeatures2.add("PbinL4C");
			AllFeatures2.add("PbinF4C");
			// AllFeatures2.add("BlackWide");
			// AllFeatures2.add("AverageWideUp");
			// AllFeatures2.add("CountLowWide");
			// AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			AllFeatures2.add("cx");
			AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");

			break;
		case 260000:
			// AllFeatures2.add("SrB3FromRight");
			AllFeatures2.add("SrB3FromLeft");
			// AllFeatures2.add("SrB3FromDown");
			// AllFeatures2.add("SrB3FromUp");
			AllFeatures2.add("PbinF4R");
			AllFeatures2.add("PbinL4R");
			AllFeatures2.add("PbinL4C");
			// AllFeatures2.add("PbinF4C");
			// //AllFeatures2.add("BlackWide");
			// //AllFeatures2.add("AverageWideUp");
			// //AllFeatures2.add("CountLowWide");
			// //AllFeatures2.add( "CountBigWide");
			// AllFeatures2.add("BlackWideVertical");
			// AllFeatures2.add("CountBigWideVertical");
			// AllFeatures2.add("CountLowWideVertical");
			AllFeatures2.add("cx");
			AllFeatures2.add("MCR"); //
			AllFeatures2.add("cy");

			break;

		default:
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			break;
		}

		return AllFeatures2;

	}
}
