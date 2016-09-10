package app;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import lib.lib;

import org.apache.log4j.Logger;

import data.GeneraicDataSet;




public class SystemSettings {
	private static transient final Logger logger = Logger.getLogger(SystemSettings .class);

	public  static final double DivideStrokePercent = 0.1;

	public static final double  collinearRange=200;
	public static final double EQUAL_LIMIT = 0.1;
	////non changable these are the sta
	public static final int OS_WINDOWS=1;
	public static final int OS_LINUX=2;
   public static   int  DataFormatIN = GeneraicDataSet.FILE_INPUT_FORMAT_TORCH;
	public static   int  DataFormatOut = GeneraicDataSet.FILE_INPUT_FORMAT_TORCH;


	public static final String DefaultSettingsFileName="set.txt";
	public static int	OS=OS_WINDOWS;



	 public static int DEFAULT_ZERNIKE_ORDER=10;

	public static String CurrentRunDir;

	public static String CurrentOSSeperator;

	private static int dbType;

	private static boolean SaveDataSet;

	private static String LineSeperator;

	private static int TrainMode;

	private static String settingFilename="set.txt";

	public static ArrayList<String> ConvertFiles=null;

		public static boolean  DEBUG=false;

		public static void StartApplication(){
			org.apache.log4j.PropertyConfigurator.configure("log4j.properties");


		}


		public static  void ReadSetting(String filename) {


			if (filename==null){
				filename=DefaultSettingsFileName;

			}


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
						SystemSettings .OS=inputInt;

					}			// now check for the following

					// now check for the following
					if (inputString.trim().startsWith("db")) {
						inputInt = input.nextInt();
						SystemSettings .dbType = inputInt;
					}

			 		// now check for the following
					if (inputString.trim().startsWith("SaveDataSet")) {

						SystemSettings .SaveDataSet = input.nextBoolean();
					}

					if(inputString.trim().startsWith("ConvertFiles")){
						ConvertFiles=new ArrayList<String>();

						while (input.hasNext()){

							inputStringInner=input.nextLine();

							if (inputStringInner.trim().startsWith("end"))
								break;
						 ConvertFiles.add(inputStringInner);
						}

					}

					if (inputString.trim().startsWith("DataFormatIN")) {
						inputInt = input.nextInt();
						SystemSettings .DataFormatIN = inputInt;
					}

					if (inputString.trim().startsWith("DataFormatOut")) {
						inputInt = input.nextInt();
						SystemSettings .DataFormatOut = inputInt;
					}


//
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
			SystemSettings .correctPathsToOs();
			logger.info("Finished reading settings.........");

		}



	    private static void correctPathsToOs(){

	    	// pathes need to change...


	    //	SystemSettings .classifiersFilename=      lib.CorrectPath( AppDefaults.classifiersFilename);
//	          if (DefaultImageErrorDir==null){
//	        	 DefaultImageErrorDir=CurrentRunDir+SystemSettings .CurrentOSSeperator+"output"+SystemSettings .CurrentOSSeperator+"error";
//	          }
//	          else {
//	    	AppDefaults.DefaultImageErrorDir=    lib.CorrectPath( AppDefaults.DefaultImageErrorDir);
//	          }
	    //	logger.info(     	AppDefaults.DefaultImageErrorDir     );



	    	SystemSettings .settingFilename=   lib.CorrectPath(SystemSettings .settingFilename );


	    }
		public static   void saveSettings(String Filename) {

			if (Filename==null){
				Filename=DefaultSettingsFileName;

			}


			FileOutputStream file;
			PrintStream out; // declare a print stream object
			try {
				// Create a new file output stream
				file = new FileOutputStream(Filename);

				// Connect print stream to the output stream
				out = new PrintStream(file);

//				// wirte the type of database
				out.println("## This is a comment line ");


				out.println ( "#  OS can be windows  "+SystemSettings .OS_WINDOWS+"   or  linux "+ SystemSettings .OS_LINUX );
 				out.println ( "OS" );
 				out.println (SystemSettings .OS);
//
//
//				out.println("## Train Mode  Train_Test "+ImageRecognizier.TRAIN_TEST+"  Train_Validate "+ImageRecognizier.TRAIN_VALIDATE);
				out.println("TrainMode");
				out.println(SystemSettings .TrainMode);
				out.println("SaveDataSet");
				out.println(SystemSettings .SaveDataSet);
				if(ConvertFiles!=null){
			    out.println ( "#  List of files to change or operate on... ");
				out.println("ConvertFiles");
				for (int i = 0; i < ConvertFiles.size(); i++) {
					out.println(ConvertFiles.get(i));
				}

				out.println("end");
				}


				out.println("###  DataFormat are =>  arff =  "+GeneraicDataSet.FILE_INPUT_FORMAT_ARFF+"   libsvm "+GeneraicDataSet.FILE_INPUT_FORMAT_LIBSVM);
				out.println("###     torch  "+GeneraicDataSet.FILE_INPUT_FORMAT_TORCH+"   mat "+GeneraicDataSet.FILE_INPUT_EXTENTION_MATLAB);

				out.println("DataFormatIN");
				out.println(DataFormatIN);
				out.println("DataFormatOut");
				out.println(DataFormatOut);
//				out.println(AppDefaults.DataSetFormat);
				out.println("### Finish....");
				out.println("## This is The end of file.......");


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in writing to file");
		}
		logger.info("Finished writing the settings..........");
		}



}
