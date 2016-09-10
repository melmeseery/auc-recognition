package oldToDelete;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import org.apache.log4j.Logger;

import General.AppDataSettings;


import data.DataSet;
import data.FeatureSet;
import data.FeatureStat;

/**
 * 
 */

/**
 * @author Maha
 * 
 */
public class AppClassifiers implements Runnable {

	private static final String TrainingProg = null;
	private static final String MyProgram = "ClassfiersCompare ";

	private String ProgramSVMLight = "svm-train -s 0   "; // -
																				// c
																				// 100
	private String SVMCommands=" -t 2 -c 100  -g 0.01 ";																			// -
																				// g
																				// 0.01
	// -g 0.1 -c 200
	private static final String ProgramSVMPredict = "svm-predict  ";
	// -g 0.01 -c 100
	private String commandsClassifiers = "-a 1  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  ";

	String TrainFilename = "train_a24.txt";
	String TestFilename = "test_a24.txt";
	String note = "a24";
	String GenearalCommand="";
	boolean useAllFeatures=true;
	//String FeatureFile="feature.txt";
	public static String ProgramFile = "";

	private static transient final Logger logger = Logger
			.getLogger(AppClassifiers.class);
	Logger logLibSvm = Logger.getLogger("AppLogging");
	Logger logCompare = Logger.getLogger("MyAppLogging");
	Logger logCommand = Logger.getLogger("commandlog");
	private int D1, D2;
	private int formatType = 1; // 1 => libsvm 2==> csv 3 ==> arff

	public static int State = 1;
	protected static int Task = 1;
	protected static boolean UseZero = true;
	
	ArrayList<Integer> tasks=new 	ArrayList<Integer> ();
	private String featureFile="feature.txt";
	private boolean GenearteTrainFiles=true;
	private boolean GenearateTestFiles=true;
	private boolean Train=true;
	private boolean Test=true;
	
	private int fromFormat=1,toFormat=3;
	private String        FeatString;
	private int KernelUsed;

	/**
	 * 
	 */
	public AppClassifiers() {
		// TODO Auto-generated constructor stub
	}

	// //////////////////////////////////INIT the program.........
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		AppClassifiers runApp = new AppClassifiers();
		Thread th = new Thread(runApp);
		th.run();

	}

	// //////////////This code is to add threading
	public void run() {

		// formatType=1;
		// RunClassifier(3,7); //run 3 and 7 , 4, 7 , 6,7 6,8

		// TrainAllFilesTask();

		// GenerateTheFeatuerIndexFile();
		// RunClassifier(3,4);
		//GenerateTheFeatuerIndexFile();
		// TestOVOCode();
		
		//TestRun();
//		testMyCode06();
//		testMyCode09();
//		testMyCode24();
//		 testMyCode29();
//			testMyCode34();
//			testMyCode37();
//			testMyCode49();
		TrainFilename = "train_AllFeatures.txt";
		TestFilename = "test_AllFeatures.txt";
		//test();
		// runAllClassifiers();
	RunCommnads();

	}
	public void test(){
		LibSVMTest t = new LibSVMTest();
		//t.ReadFeatureFile(featureFile);
	//	t.setUseAllFeatures(useAllFeatures);
		t.TrainSystemOVA(TrainFilename);
		 t.TestSystemOVALibrary(TestFilename);
		
	}
      public void RunCommnads(){
    	  
    	  ReadCommands();
    	  for (int i = 0; i < tasks.size(); i++) {
			
    		  int thetask=tasks.get(i);
    		  switch (thetask) {
			case 1:  //my progarm with the commands 
				
				logCompare.info("***********************************My program*****:********************************");
				logCompare.info("-------------- using the commands-------------------------");
 

				RunMyProgram();
				
				break;
	        case 2:  //my  libsvm run with the commands 
				RunSvmLightTrain();
				RunSvmLightTest();
				break;
				
           case 3:  //format inputs to libsvm (train and test)
        	   formatType=1;
        	   RunFormat();
				break;
           case 4:  //train only libsvm
        		RunSvmLightTrain();
				break;			
				
           case 5:  //test only libsvm 
        	   RunSvmLightTest();
				break;	
				
           case 6:  //run a generaal command  
				RunGenearalCommand();
				
				break;
           case 7:  //generate the feature index files  
        	   GenerateTheFeatuerIndexFile();
				break;
				
           case 8:  //run lib svm ovo code. 
        	   TestOVOCode();
				break;		
				
           case 9: //run the veri code. 
        	   
        	   
        	  ReadVerificationCodes();
        	   break;
       
           case 10:
        	   TestOVACode();
        	   break;
           case 11:
        	   FormateUseDataSet();
        	   break;
        	   
           case 12:
        	   FormateUseTestSet();
			//default:
        	   	break;			
			case 14:
				TestOVOValidate();
				break;
				
			case 15:
				TestValidate();
				break;
				default :
					break;
 
			}
    		  
    		  
		}
    	  
      }
      
      private void TestValidate() {
  		// TODO Auto-generated method stub
  		// TODO Auto-generated method stub
  		LibSVMTest t = new LibSVMTest();
        t.setDataFormat(this.formatType);
  		//t.ReadFeatureFile(featureFile);
  		t.genearteTrainFiles=this.GenearteTrainFiles;
  		t.genearteTestFiles=this.GenearateTestFiles;
  		t.trainAll=this.Train;
  		t.testAll=this.Test;
  		t.setAlg(KernelUsed);
  	t.setUseAllFeatures(useAllFeatures);
  	
  	t.OptimizeC(TrainFilename);
  	t.OptimizeG(TrainFilename);
  	
  	
  	}
      
	private void TestOVOValidate() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		LibSVMTest t = new LibSVMTest();
	    t.setDataFormat(this.formatType);
		//t.ReadFeatureFile(featureFile);
		t.genearteTrainFiles=this.GenearteTrainFiles;
		t.genearteTestFiles=this.GenearateTestFiles;
		t.trainAll=this.Train;
		t.testAll=this.Test;
		t.setAlg(KernelUsed);
	t.setUseAllFeatures(useAllFeatures);
	
	t.CrossValidate(TrainFilename);
	
	}

	private void FormateUseTestSet() {
		DataSet data=new DataSet();
		data.setFormat(fromFormat);
		data.ReadFromFile(TestFilename);
		data.setFormat(toFormat);
		data.SaveToFile( "Format_"+toFormat+"_"+TestFilename);
		
	}

	private void FormateUseDataSet() {
		// TODO Auto-generated method stub
		DataSet data=new DataSet();
		data.setFormat(fromFormat);
		data.ReadFromFile(TrainFilename);
		data.setFormat(toFormat);
		data.SaveToFile( "Format_"+toFormat+"_"+TrainFilename);
		
		
		
	
		
		
		
	}

	private void TestOVACode() {
		// TODO Auto-generated method stub
		LibSVMTest t = new LibSVMTest();
		//t.ReadFeatureFile(featureFile);
		t.genearteTrainFiles=this.GenearteTrainFiles;
		t.genearteTestFiles=this.GenearateTestFiles;
		t.trainAll=this.Train;
		t.testAll=this.Test;
	    t.setDataFormat(this.formatType);
		t.setAlg(KernelUsed);
	t.setUseAllFeatures(useAllFeatures);
	 if (Train){
		t.TrainSystemOVA(TrainFilename);
	 }
	 if (Test){
		 t.TestSystemOVALibrary(TestFilename);
	 }
	}
	
	
	 
	
	public void CreateFeatFile(String featureST){
		
		
		
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream("feat.txt");

                  // Connect print stream to the output stream
                 out = new PrintStream(file);
                 String[] FeaturesSingle = featureST.split(",");
                  
                  for (int i = 0; i < FeaturesSingle.length; i++) {
					
                	   out.println(FeaturesSingle[i]);
                	     out.println(i);
                	   
                	  
			 
                 
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
          }
		
		    
	}

	public void ReadCommands() {
		try {
			System.out.println("reading the file................ wait");
			File afile = new File("commands.txt");
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			while (input.hasNext()) {
				// first
				String inputString = input.nextLine();
				//run my program with commands
				logger.trace(" Reading command "+inputString);
				if (inputString.trim().startsWith("myprogram")) {
					
					commandsClassifiers =input.nextLine(); 
					tasks.add(new Integer(1));

				}//general command ..............
				else if (inputString.trim().startsWith("com")) {
					GenearalCommand=input.nextLine(); 
					tasks.add(new Integer(6));

				}
				
				///run lib svm once. 
				else if (inputString.trim().startsWith("libsvm")) {
					SVMCommands=input.nextLine(); 
					tasks.add(new Integer(2));

				}
				//run specific taskkkkkkkkkkkkkkkk
				else if (inputString.trim().startsWith("task")) {
					tasks.add(new Integer(input.nextInt()));
				}
				///use a specific feature fie
				 else if (inputString.trim().startsWith("useFeat")) {
					 useAllFeatures=false;
	                     this.featureFile=input.nextLine();
	                 	tasks.add(new Integer(8)); 
					}//use all feature
                 else if (inputString.trim().startsWith("Feat")){
				       FeatString=input.next(); 	 
				       CreateFeatFile(FeatString);
				 }
				 else if (inputString.trim().startsWith("noFeat")){
					 
					 useAllFeatures=true;
					 	tasks.add(new Integer(8)); 
				 }
				else if (inputString.trim().startsWith("trainfile")) {
                   TrainFilename=input.nextLine();
				}

				else if (inputString.trim().startsWith("testfile")) {
                TestFilename=input.nextLine();
				}
				else if  (inputString.trim().startsWith("form")) {
					String  typetest=input.next();
					if (typetest.equals("test"))
						tasks.add(new Integer(12)); 
					else 
						tasks.add(new Integer(11)); 
					
					fromFormat=input.nextInt();
                   toFormat=input.nextInt();
                   logger.trace("  format from "+fromFormat+" to "+toFormat);
				}
				else if (inputString.trim().equals("C")){
					
					AppDataSettings.C=input.nextDouble();
					
				}
				else if (inputString.trim().equals("g")){
					
					AppDataSettings.g=input.nextDouble();
					
				}
				else if (inputString.trim().startsWith("val"))
				{
					tasks.add(new Integer(14)); 
					
				}
				else if (inputString.trim().startsWith("op"))
				{
					tasks.add(new Integer(15)); 
					
				}
				else if (inputString.trim().startsWith("flags")) {
					
					
					int temp=input.nextInt();
					
					this.formatType=temp;
					//add the format there 
					temp=input.nextInt();
					
					if (temp==0)
					{
						GenearteTrainFiles=false;
					}
					else 
						if (temp==1)
						{
							GenearteTrainFiles=true;
							
						}
					temp=input.nextInt();
					if (temp==0)
					{
						GenearateTestFiles=false;
					}
					else 
						if (temp==1)
						{
							GenearateTestFiles=true;
							
						}
					temp=input.nextInt();
					if (temp==0)
					{
						Train=false;
					}
					else 
						if (temp==1)
						{
							Train=true;
							
						}
					temp=input.nextInt();
					if (temp==0)
					{
						Test=false;
					}
					else 
						if (temp==1)
						{
							Test=true;
							
						}
                 
					temp=input.nextInt();
					
					KernelUsed=temp;
					
					
//                GenearateTestFiles = input.nextInt();
//                 Train =input.nextInt();
//                 Test=input.nextInt();
                 
				}
				

			}

			input.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void TestOVOCode() {

		//featureFile="features.txt";

		LibSVMTest t = new LibSVMTest();

		t.setUseAllFeatures(useAllFeatures);
		t.genearteTrainFiles=this.GenearteTrainFiles;
		t.genearteTestFiles=this.GenearateTestFiles;
	    t.setDataFormat(this.formatType);
		t.trainAll=this.Train;
		t.testAll=this.Test;
		
		t.setAlg(KernelUsed);
	t.setUseAllFeatures(useAllFeatures);
	 if (Train){
		//t.ReadFeatureFile(featureFile);
		t.TrainSystemOVO(TrainFilename);
	 }
	 if (Test){
		 t.TestSystemOVO( TestFilename);
	 }

	}

	public ArrayList<String> SetFeaturesStringL(String[] AllFeatures) {

		return null;
	}

	// @SuppressWarnings("unchecked")
	public void GenerateTheFeatuerIndexFile() {

		// ArrayList<ArrayList<String>> Features=new
		// ArrayList<ArrayList<String>>();

		FeatureSet allFeat = new FeatureSet();
		allFeat.ReadAllFeatures("feat.txt");

		// first read all features
		String line;
		try {
			System.out.println("reading the file................ wait");
			File afile = new File("features_old.txt");
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			FileOutputStream file;
			PrintStream out; // declare a print stream object
			file = new FileOutputStream("feat_index_beforeVerification.txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);
			// out.println(" " );

			// input.useDelimiter(",");
			String featuresString;
			String[] featuresArray;
			int[] FeaturesIndeces;
			int i;
			int j;
			String st = "";
			while (input.hasNext()) {
				st = "";
				// first input for the
				i = input.nextInt();
				j = input.nextInt();
				featuresString = input.next();
				logger.trace("classififer s " + i + "  " + j
						+ "  the input string is " + featuresString);

				featuresArray = featuresString.trim().split(",");
				// now i have the features
				allFeat.createFeatures(featuresArray);
				FeaturesIndeces = allFeat.getSelectedFeaturesIndeces();
				out.print(i);
				out.print("  ");
				out.print(j);
				out.print("  ");
				out.print(FeaturesIndeces.length);
				for (int j2 = 0; j2 < FeaturesIndeces.length; j2++) {
					out.print("  ");
					out.print(FeaturesIndeces[j2]);

					st += "  " + FeaturesIndeces[j2];
				}
				out.println();
				logger.trace(" output is " + st);
			}
			input.close();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
	
	public void ReadVerificationCodes(){
		
		try {
			System.out.println("reading the file................ wait");
			File afile = new File("verfeat.txt");
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			while (input.hasNext()) {
				// first
//				String inputString = input.nextLine();
//				if (inputString.trim().startsWith("feat")) {
					

                       int c1 =input.nextInt();
                       int c2 =input.nextInt();
                       
                       String allfeatures=input.nextLine();
                       
                       String [] feat=allfeatures.trim().split(",");
                       RunVerificationTest(c1,c2,feat);

				//}
				 

			}

			input.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void RunVerificationTest(int c1,int c2 , String[] features){
		
		//System.out.println("inside the test ");
		SingleClassTest c = new SingleClassTest();

		c.initTest(c1, c2, TrainFilename);

//		String[] features = new String[6];
//		features[0] = "im";
//		features[1] = "w2r";
//		features[2] = "wu";
//		features[3] = "sre";
//		features[4] = "u1";
//		features[5] = "wce";
		// features[0]="";

		c.setFeaturesToTest(features);
		c.TestAndGenearteFeatures();
		logger.info("finishe all test now save................");
		c.SaveResultsToFile("Result"+c1+"_"+c2+".txt");
		
	}
	
	
	public void TestRun() {
		//TrainFilename = "train_AllFeatures.txt";
	///	TestFilename = "test_AllFeatures.txt";

		// TrainFilename=null;
		logCompare
				.info("************************************ all features ********:********************************");
		logCompare
				.info("-------------- The RBF kernel for the svm -------------------------");

		// commandsClassifiers=
		// "  -a 5  -diffeat -imagemap feat_index.txt  -ovo   -g 0.01  -c 100 -s 1    "
		// ;
	//	commandsClassifiers = "-a 5  -r    -g 0.3  -c 50 -s 1  -th 0  ";

		RunMyProgram();

		//commandsClassifiers = "-a 6  -r    -g 0.01  -c 100 -s 1  -h 70   ";
		//			 
		//			 
		//RunMyProgram();
		//			 
		// commandsClassifiers="-a 2  -r    -g 0.01  -c 100 -s 1    ";
		//			 
		//			 
		// RunMyProgram();
		//				
		// RunC();

	}

	public void RunC() {

		logCompare
				.info("************************************ pair " + D1
						+ "  and  " + D2
						+ " ********:********************************");
		// RunFormatToCSV();
		// logCompare.info(
		// "-------------- The linear kernel for the svm -------------------------"
		// );

		//commandsClassifiers="-a 1  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  "
		// ;
		// RunMyProgram();

		logCompare
				.info("-------------- The RBF kernel for the svm -------------------------");

		//commandsClassifiers="-a 5  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  "
		// ;
		// RunMyProgram();

		RunFormat();
		// RunFormatToSvmLight();

		logLibSvm
				.info("************************************ pair " + D1
						+ "  and  " + D2
						+ " ********:********************************");
		logLibSvm
				.info("-------------- The linear kernel for the svm -------------------------");

		ProgramSVMLight = "svm-train -s 0 -t 0  ";
		RunSvmLightTrain();
		RunSvmLightTest();

		ProgramSVMLight = "svm-train -s 0 -t 2 -c 100  -g 0.01   ";
		logLibSvm
				.info("-------------- The RBF for the svm -------------------------");
		RunSvmLightTrain();
		RunSvmLightTest();

	}

	public void RunClassifier(int i, int j) {
		TrainFilename = "train_b" + i + j + ".txt";
		TestFilename = "test_b" + i + j + ".txt";
		note = "b" + i + j;

		D1 = i;
		D2 = j;
		RunC();
		//		
		// RunMyProgram();
		// RunFormatToSvmLight();
		// RunSvmLightTrain();
		// RunSvmLightTest();
	}

	public void runAllClassifiers() {
		for (int i = 0; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {

				if (i != j && i < j) {

					TrainFilename = "train_b" + i + j + ".txt";
					TestFilename = "test_b" + i + j + ".txt";
					note = "b" + i + j;
					D1 = i;
					D2 = j;
					RunC();

					// logCompare.info(
					// "************************************ pair "
					// +i+"  and  "+j
					// +" ********:********************************");
					// // RunFormatToCSV();
					// // logCompare.info(
					// "-------------- The linear kernel for the svm -------------------------"
					// );
					//					
					// // commandsClassifiers=
					// "-a 1  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  ";
					// // RunMyProgram();
					//				
					// logCompare.info(
					// "-------------- The RBF kernel for the svm -------------------------"
					// );
					//					
					// commandsClassifiers=
					// "-a 5  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  ";
					// RunMyProgram();
					//				
					// RunFormat();
					// //RunFormatToSvmLight();
					//				
					//logLibSvm.info("************************************ pair "
					// +
					// i+"  and  "+j+" ********:********************************"
					// );
					// logLibSvm.info(
					// "-------------- The linear kernel for the svm -------------------------"
					// );
					//				
					// ProgramSVMLight="svm-train -s 0 -t 0  ";
					// RunSvmLightTrain();
					// RunSvmLightTest();
					//				
					//				
					//				
					// ProgramSVMLight="svm-train -s 0 -t 2 -c 100  -g 0.01   ";
					// logLibSvm.info(
					// "-------------- The RBF for the svm -------------------------"
					// );
					// RunSvmLightTrain();
					// RunSvmLightTest();

				}

			}
		}

	}

	public void RunMyProgram() {
		Runtime r = Runtime.getRuntime();
		Process p = null;

		try {

			String s = MyProgram + "  ";
			if (TrainFilename != null)
				s += "  -train  " + TrainFilename;

			if (TestFilename != null)
				s += " -test " + TestFilename;

			s += "   " + commandsClassifiers;

			System.out.println(s);

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
			String line;
			boolean flag = false;
			int count1 = 0;
			int count2 = 0;
			String TheSummary = "[Summary]";
			while ((line = input.readLine()) != null) {

				logger.info(line);

				if (TheSummary.equals(line))
				// /then next time
				{
					// System.out.println(" line  ===== "+line);
					// count1++;
					// System.out.println("count1 ="+count1);
					// change the flag...........
					flag = !flag;// true;

				}
				if (flag) {

					// count2++;
					// System.out.println("count2 ="+count2);
					logCompare.info(line);
				}

			}
			
			while ((line = inputerr.readLine()) != null ){
				
				logger.error(line);
				
			}
			
			input.close();
			out.write(4);

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunFormat() {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		// String TrainFilename="train_a24.txt";
		// String TestFilename="test_a24.txt";

		try {

			String s = MyProgram + "  -train  " + TrainFilename + " -test "
					+ TestFilename + " " + "  -formate  " + formatType;

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				logger.info(line);
			}
			input.close();
			out.write(4);

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunFormatToCSV() {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		// String TrainFilename="train_a24.txt";
		// String TestFilename="test_a24.txt";

		try {

			String s = MyProgram + "  -train  " + TrainFilename + " -test "
					+ TestFilename + " " + "  -formate 2 ";

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				logger.info(line);
			}
			input.close();
			out.write(4);

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunFormatToSvmLight() {

		Runtime r = Runtime.getRuntime();
		Process p = null;
		// String TrainFilename="train_a24.txt";
		// String TestFilename="test_a24.txt";

		try {

			String s = MyProgram + "  -train  " + TrainFilename + " -test "
					+ TestFilename + " " + "  -formate 1 ";

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				logger.info(line);
			}
			input.close();
			out.write(4);

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunSvmLightTrain() {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		String Train = "Formated_" + TrainFilename;
		// String TestFilename="Formated_test_a24.txt";

		try {

			logger.info("--------Training the svm light-------------- with "
					+ Train);
			String s = ProgramSVMLight + " " +SVMCommands+ Train + "   train.model";
			System.out.println(s);

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				logger.info(line);
			}
			input.close();
			out.write(4);

			logger.info("---------------finihsed training now testing ");

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunSvmLightTest() {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		// String TrainFilename="Formated_train_a24.txt";
		String TestFi = "Formated_" + TestFilename;

		try {

			String s = ProgramSVMPredict + "   " + TestFi + "  train.model   "
					+ "  predict.out";
			logLibSvm.info(" Testing result for  " + TestFilename);

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				logLibSvm.info(line);
				logger.info(line);
			}
			input.close();
			out.write(4);
			//appLogger.info(" [Settings]  inside run the settings is "+settings
			// .toString() );

			// logger.info(
			// "-----------------------------------finihsed training now testing "
			// );

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void RunGenearalCommand(){
		Runtime r = Runtime.getRuntime();
		Process p = null;
		// String TrainFilename="Formated_train_a24.txt";
		 

		try {

			String s =  GenearalCommand;
			//logLibSvm.info(" Testing result for  " + TestFilename);
			logCommand.trace(s);
			p = r.exec(s);
			
			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
			logCommand.trace("  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^in genearal command ");
			String line;
			while ((line = input.readLine()) != null) {
				//logLibSvm.info(line);
				logCommand.trace(line);
				
				//if (inputerr )
				
			}
		//	BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
			while ((line = inputerr.readLine()) != null ){
				
				logCommand .error(line);
				
			}
			
			input.close();
			out.write(4);


		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
//	/************************* CODE FOR TEST OF FEATURES *******************************************/
//	public void testMyCode() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(3, 9, "train_AllFeatures.txt");
//
//		String[] features = new String[7];
//		features[0] = "w2r";
//		features[1] = "wrb";
//		features[2] = "wu";
//		features[3] = "w4";
//		features[4] = "mce69";
//		features[5] = "u1";
//		features[6] = "wce";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result39.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode37() {
//	
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode34() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(3, 4, "train_AllFeatures.txt");
//
//		String[] features = new String[6];
//		features[0] = "w4lft";
//		features[1] = "mxb";
//		features[2] = "wu";
//		features[3] = "u1";
//		features[4] = "im";
//		features[5] = "wce";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result34.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode49() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(4, 9, "train_AllFeatures.txt");
//
//		String[] features = new String[6];
//		features[0] = "w4lft";
//		features[1] = "wrb";
//		features[2] = "w2r";
//		features[3] = "w4";
//		features[4] = "mce69";
//		features[5] = "wce";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result49.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode24() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(2, 4, "train_AllFeatures.txt");
//
//		String[] features = new String[4];
//		features[0] = "w4lft";
//		features[1] = "mxb";
//		features[2] = "w2r";
//		features[3] = "wce";
//		// features[4]="im";
//		// features[5]="wce";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result24.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode29() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(2, 9, "train_AllFeatures.txt");
//
//		String[] features = new String[5];
//		features[0] = "w2r";
//		features[1] = "wrb";
//		features[2] = "mce69";
//		features[3] = "w4";
//		features[4] = "wce";
 
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result29.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode06() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(0, 6, "train_AllFeatures.txt");
//
//		String[] features = new String[6];
//		features[0] = "vol2";
//		features[1] = "wrb";
//		features[2] = "mce69";
//		features[3] = "mxb";
//		features[4] = "wce";
//		features[5] = "HW";
//		// features[5]="wce";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result06.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}
//
//	public void testMyCode09() {
//		System.out.println("inside the test ");
//		SingleClassTest c37 = new SingleClassTest();
//
//		c37.initTest(0, 9, "train_AllFeatures.txt");
//
//		String[] features = new String[7];
//		features[0] = "vol2";
//		features[1] = "wrb";
//		features[2] = "mce69";
//		features[3] = "mxb";
//		features[4] = "wce";
//		features[5] = "HW";
//		features[6] = "w4";
//		// features[0]="";
//
//		c37.setFeaturesToTest(features);
//		c37.TestAndGenearteFeatures();
//		logger.info("finishe all test now save................");
//		c37.SaveResultsToFile("Result09.txt");
//		// DataSet data=new DataSet();
//		// data.ReadFromFile();
//
//	}


}
