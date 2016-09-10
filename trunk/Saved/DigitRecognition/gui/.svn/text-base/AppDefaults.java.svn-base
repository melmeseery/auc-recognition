package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

import util.lib;

import classifiers.MultiFeature.ImageRecognizier;

import applications.subMuliClassifiers.RunHierarchicalClassifier;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;


public class AppDefaults {
	private static transient final Logger logger = Logger.getLogger(AppDefaults.class);
	protected static final String FILE_NAME ="D:\\AUC\\Databases\\Arabic Digits Databases\\MAHDBase\\MAHDBase_TrainingSet\\Part01\\"+"writer041_pass02_digit6.bmp";
	public static final  int CATEGORY_SIZE=10;
	public static final  int  NO_OF_PASSES=10;

	public static final  int  NO_OF_TRAIN_WRITERS=500;	
	public static final  int  NO_OF_VALIDATION_WRITERS=600;
	public static final  int  NO_OF_TEST_WRITERS=700;
	public static final  int  NO_OF_PARTS=10;
	public static final  int  NO_OF_DIGITS=10;
	
	public static  boolean StoreErrorImage=true;
	public static String DefaultImageErrorDir=null;
	public static  final int WRITE_ONLY_COMPUTED=0; 
	public static  final int WRITE_ONLY_COMPUTED_DETAILED=1; 
	public static  final int WRITE_ALL_DETAILED=2;
	public static  final int WRITE_ALL=3;
	
	public static int WriteFeaturesMode=WRITE_ONLY_COMPUTED_DETAILED;
	 public static final int OS_LINUX=0;
	 
	 public static final int OS_WINDOWS=1;
	 
	public static int OS;//=OS_LINUX;

	public static  int Train_Writer_Start=0;
	public static int Train_Writer_End=0;
	
	public static   int Validation_Writer_Start=0;
	public static   int  Validation_Writer_End=0;
	public static boolean 	useSampleSize=false;  
	public static  int  SampleSetSize=100;
	
	public static boolean MovingValidation=false;
	public static boolean addIndexClassifierNames=false;
	
	  public  static final int MaxNumberOfDigit = 10;
    public static final int BasicFeatures = 1;
    
	private static final String MODE_TRAIN = null;
	  public  static int MaxNumberOfClassifier = 30;
	// private boolean arabic=false;
	  public  static int dbType = DataBaseConnector.MADBASE;
	  public  static String settingFilename="set.txt";
	  public  static String classifiersFilename="classifier"+"_Try_"+0;
	  public static  int TrialNo=0;
	  public static final int MODE_TRAIL_INCREMENT=0;
		public static final int MODE_TRAIL_SINGLE=1;
		public static final int MODE_TRAIL_SINGLE_NOSAVE=2;
		
	  public static  int  trailMode=MODE_TRAIL_SINGLE;
	
	public static final int MODE_TRAIN_TEST=0;
	public static  final int MODE_TRAIN_Validate=1;
	//private final int MODE_TRAIN_Validate_TRAIN=2;
	//private final int MODE_TRAIN_Validate_TEST=3;
	public static  final int MODE_TRAIN_ONLY=4;
	public static  final int MODE_TEST_ONLY=5;
	public static final boolean ComputeOnlyArabic = true;
	public static  String	CurrentRunDir	= null;

	public static int Mode=MODE_TRAIN_TEST;

	public static  boolean sort=true;
	public static  int TrainMode=ImageRecognizier.TRAIN_VALIDATE;
	public static  int classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
	public static  int startTrainid=0;
	public static  int Interupt=50;
	public static boolean SaveDataSet=true;
	public static boolean LoadDataSet=true;
	public static int DataSetFormat=DataSet.FILE_INPUT_FORMAT_ARFF;
	
	public static String CurrentOSSeperator="/";
	public static boolean ShuffleDataFiles=false;

	
   public static String LineSeperator="";
public static boolean	NormalizeFeatures=false;
	
	public static  void ReadSetting(String filename) {
		 ///TrainMode=ImageRecognizier.TRAIN_VALIDATE
		//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
		
		lib.getCurrentOs();
	
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

				// now check for the following
				if (inputString.trim().startsWith("OS")) {
					inputInt = input.nextInt();
					//inputInt
					AppDefaults.OS=inputInt;
					DataBaseConnector.OS = inputInt;
					
					
				
					
				}			// now check for the following
				if (inputString.trim().startsWith("WriteFeaturesMode")) {
					inputInt = input.nextInt();
					AppDefaults.WriteFeaturesMode= inputInt;
				}
				// now check for the following
				if (inputString.trim().startsWith("db")) {
					inputInt = input.nextInt();
					AppDefaults.dbType = inputInt;
				}
			 
				// now check for the following
				if (inputString.trim().startsWith("ClassFile")) {
				 
					AppDefaults.classifiersFilename= input.nextLine();
				}
	 
		
		 		// now check for the following
				if (inputString.trim().startsWith("NormalizeFeatures")) {
			 
					AppDefaults.NormalizeFeatures = input.nextBoolean();
				}
				// now check for the following
				if (inputString.trim().startsWith("trailNo.")) {
					inputInt = input.nextInt();
					AppDefaults.TrialNo = inputInt;
				}
				
				 
				// now check for the following
				if (inputString.trim().startsWith("trailMode")) {
					inputInt = input.nextInt();
					AppDefaults.trailMode= inputInt;
				}
				
				// now check for the following
				if (inputString.trim().startsWith("Mode")) {
					inputInt = input.nextInt();
					AppDefaults.Mode = inputInt;
				}


				if (inputString.trim().startsWith("startTrain")) {
					inputInt = input.nextInt();
					AppDefaults.startTrainid = inputInt;
				}
				if (inputString.trim().startsWith("Interupt")) {
					inputInt = input.nextInt();
					AppDefaults.Interupt = inputInt;
				}
		 
			 
				

				if (inputString.trim().startsWith("DigitsOnly")) {
					String temp=input.nextLine();
					 //inputInt = input.nextInt();
					// MaxNumberOfDigitCombination = inputInt;
				}
		 		// now check for the following
				if (inputString.trim().startsWith("SaveDataSet")) {
			 
					AppDefaults.SaveDataSet = input.nextBoolean();
				}
				// now check for the following
				if (inputString.trim().startsWith("LoadDataSet")) {
			 
					AppDefaults.LoadDataSet = input.nextBoolean();
				}
				// now check for the following
				if (inputString.trim().startsWith("Sort")) {
			 
					AppDefaults.sort = input.nextBoolean();
				}	// now check for the following
				if (inputString.trim().startsWith("ShuffleDataFiles")) {
			 
					AppDefaults.ShuffleDataFiles = input.nextBoolean();
				}
				// now check for the following
				if (inputString.trim().startsWith("ImageErrorDir")) {
				 
					AppDefaults.DefaultImageErrorDir= input.nextLine();
				}
	 				if (inputString.trim().startsWith("addIndexClassifierNames")) {
					
					AppDefaults.addIndexClassifierNames= input.nextBoolean();
				}
				if (inputString.trim().startsWith("MovingValidation")) {
					
					AppDefaults.MovingValidation= input.nextBoolean();
				}
if (inputString.trim().startsWith("Train_Writer_Start")) {
					inputInt = input.nextInt();
					AppDefaults. Train_Writer_Start= inputInt;
				}
if (inputString.trim().startsWith("Train_Writer_End")) {
					inputInt = input.nextInt();
					AppDefaults.  Train_Writer_End= inputInt;
				} 

if (inputString.trim().startsWith("Validation_Writer_Start")) {
					inputInt = input.nextInt();
					AppDefaults. Validation_Writer_Start= inputInt;
				}
if (inputString.trim().startsWith("Validation_Writer_End")) {
					inputInt = input.nextInt();
					AppDefaults. Validation_Writer_End= inputInt;
				} 

if (inputString.trim().startsWith("useSampleSize")) {
			
					AppDefaults.useSampleSize=input.nextBoolean();
}	

 

if (inputString.trim().startsWith("SampleSetSize")) {
					inputInt = input.nextInt();
					AppDefaults.SampleSetSize= inputInt;
				}
				// now check for the following
				if (inputString.trim().startsWith("StoreErrorImage")) {
			 
					AppDefaults.StoreErrorImage = input.nextBoolean();
				}
	 
				// now check for the 
				if (inputString.trim().startsWith("TrainMode")) {
			 
					AppDefaults.TrainMode = input.nextInt();
				}
				// now check for the 
				if (inputString.trim().startsWith("DataSetFormat")) {
			 
					AppDefaults.DataSetFormat = input.nextInt();
				}
				//DataSetFormat
				//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
				
				 
				// now check for the 
				if (inputString.trim().startsWith("classifierType")) {
			 
					AppDefaults.classifierType = input.nextInt();
				}
				 
			}
          
			
			LineSeperator=System.getProperty("line.separator");
			
			input.close();
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		lib.getCurrentOs();
		lib.getCurrentDirectory();
		lib.getCurrentSeperator();
		AppDefaults.correctPathsToOs();
		logger.info("Finished reading settings.........");
	
	}
	
	
//private static void getCurrentDirectory(){
//	 try {
//				String path = new java.io.File(".").getCanonicalPath();
//				AppDefaults.CurrentRunDir=path;
//				
//				logger.info(" current direcotry is "+path);
//			} catch (IOException e) {
//			 
//				e.printStackTrace();
//			}
//}
    private static void correctPathsToOs(){
    	
    	// pathes need to change... 
    	
   
    	AppDefaults.classifiersFilename=      lib.CorrectPath( AppDefaults.classifiersFilename);
          if (DefaultImageErrorDir==null){
        	 DefaultImageErrorDir=CurrentRunDir+AppDefaults.CurrentOSSeperator+"output"+AppDefaults.CurrentOSSeperator+"error";
          }
          else {
    	AppDefaults.DefaultImageErrorDir=    lib.CorrectPath( AppDefaults.DefaultImageErrorDir);
          }
    	logger.info(     	AppDefaults.DefaultImageErrorDir     );
          
    	
  
    	AppDefaults.settingFilename=   lib.CorrectPath(AppDefaults.settingFilename );
    	
    	
    }
	public static   void saveSettings(String Filename) {

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
			out.println(AppDefaults.classifiersFilename);
			
//			out.println("##  Datafilename  ");
//			out.println("Datafilename");
//			out.println(Datafilename);
					out.println("addIndexClassifierNames.");
		 
			out.println(AppDefaults.addIndexClassifierNames);
			
			
			out.println("## trail number  ");
			out.println("trailNo.");
		 
			out.println(AppDefaults.TrialNo);
			
			
			out.println("## trail Mode  is either  Increment "+AppDefaults.MODE_TRAIL_INCREMENT+"  Single   "+AppDefaults.MODE_TRAIL_SINGLE+"   no save "+AppDefaults.MODE_TRAIL_SINGLE_NOSAVE); 
			out.println("trailMode");
			out.println(	AppDefaults.trailMode);
			
			out.println ( "#  OS can be windows  "+DataBaseConnector.OS_WINDOWS+"   or  linux "+ DataBaseConnector.OS_LINUX );
			out.println ( "OS" );
			out.println (AppDefaults.OS);
			
			
			out.println("## Train Mode  Train_Test "+ImageRecognizier.TRAIN_TEST+"  Train_Validate "+ImageRecognizier.TRAIN_VALIDATE);
			out.println("TrainMode");
			out.println(AppDefaults.TrainMode);
	
			
			out.println("## Classifier type SVM "+ImageRecognizier.CLASSIFIER_LIBSVM+
					"  liblinear "+ImageRecognizier.CLASSIFIER_LIBLINEAR+"  weka "+ImageRecognizier.CLASSIFIER_WEKA);
			out.println("classifierType");
			out.println(AppDefaults.classifierType);
			
			
			out.println("### the Type of database of  MNIST "+DataBaseConnector.MNIST+" MADBASE  "+DataBaseConnector.MADBASE);
			
			out.println("db");
			out.println(AppDefaults.dbType);
			
			out.println("### the Mode of operation run Single experiments "+MODE_TRAIN_Validate+" Train&test "+MODE_TRAIN_TEST+" Train only "+MODE_TRAIN_ONLY+"  Test only "+MODE_TEST_ONLY);
			
			out.println("Mode");
			out.println(AppDefaults.Mode);
			

			
	       out.println("###  Start Training at classifier   ");
			
			out.println("startTrain");
			out.println(AppDefaults.startTrainid);
			out.println("###   Interupt at  classifier ");
		    out.println("Interupt");
			out.println(AppDefaults.Interupt);
			out.println("NormalizeFeatures");
			out.println(AppDefaults.NormalizeFeatures);		
						out.println ("MovingValidation");

				out.println (MovingValidation);	
			out.println ( "Train_Writer_Start"); 
			 out.println ( Train_Writer_Start); 
				out.println ( "Train_Writer_End"); 
			 
				out.println ( Train_Writer_End); 
			
					out.println ( "Validation_Writer_Start"); 
			 	out.println ( Validation_Writer_Start);
					out.println ( "Validation_Writer_End"); 
					out.println ( Validation_Writer_End); 
					
				out.println ("useSampleSize");
				out.println (useSampleSize);	
				
			out.println ( "SampleSetSize");
			out.println ( SampleSetSize);
	

			//	TrainMode=ImageRecognizier.TRAIN_VALIDATE
			//classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
				out.println("##    WriteFeaturesMode  =>   detailed all"+WRITE_ALL_DETAILED+"   all "+WRITE_ALL+ "   computed detailed only "+WRITE_ONLY_COMPUTED_DETAILED+"   compted only "+WRITE_ONLY_COMPUTED);
				out.println("WriteFeaturesMode");
			out.println(	WriteFeaturesMode);
			out.println("## now writing error filess and error directory ");
			if (AppDefaults.DefaultImageErrorDir!=null){
				if (!AppDefaults.DefaultImageErrorDir.startsWith( AppDefaults.CurrentRunDir)){
						out.println("ImageErrorDir");
						out.println(AppDefaults.DefaultImageErrorDir);
				}
			}
			out.println("StoreErrorImage");
			out.println(AppDefaults.StoreErrorImage);
						out.println("###  loading and saving  of data  ");
			out.println("LoadDataSet");
			out.println(AppDefaults.LoadDataSet);
			out.println("SaveDataSet");
			out.println(AppDefaults.SaveDataSet);		
			out.println("###  DataFormat are =>  arff =  "+DataSet.FILE_INPUT_FORMAT_ARFF+"   libsvm "+DataSet.FILE_INPUT_FORMAT_LIBSVM+"   torch  "+DataSet.FILE_INPUT_FORMAT_TORCH);
			out.println("DataSetFormat");
			out.println(AppDefaults.DataSetFormat);
			out.println("### the Sort files (True or false) ");
			
			
			out.println("ShuffleDataFiles");
			out.println(AppDefaults.ShuffleDataFiles);
			out.println("Sort");
			out.println(AppDefaults.sort);
			out.println("### Finish....");
			out.println("## This is The end of file.......");
		

	} catch (Exception e) {
		e.printStackTrace();
		logger.error("Error in writing to file");
	}
	logger.info("Finished writing the settings..........");
	}



}
