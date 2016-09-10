/**
 * 
 */
package applications.subMuliClassifiers;

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
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.ImageRecognizier;
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
public class RunCombinationEcocClassifier implements RunnableTask {
	private static transient final Logger logger = Logger
	.getLogger(RunCombinationEcocClassifier.class);
private static final int MaxNumberOfDigit = 10;
 
private   int MaxNumberOfDigitCombination = 5;
private   int MaxNumberOfFeaturesCombination = 4;
int MaxNumberOfClassifier = 30;
// private boolean arabic=false;
private int dbType = DataBaseConnector.MADBASE;
private String settingFilename="D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\Comb\\set.txt";
private String classifiersFilename="D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\Comb\\classifierCombinations";
private int TrialNo=0;
private final int MODE_TRAIN_TEST=0;
private final int MODE_TRAIN_Validate=1;
//private final int MODE_TRAIN_Validate_TRAIN=2;
//private final int MODE_TRAIN_Validate_TEST=3;
private final int MODE_TRAIN_ONLY=4;
private final int MODE_TEST_ONLY=5;

private int Mode=MODE_TRAIN_TEST;

private boolean sort=true;
private int TrainMode=ImageRecognizier.TRAIN_VALIDATE;
private int classifierType=	ImageRecognizier.CLASSIFIER_WEKA;
private int startTrainid=0;
private int Interupt=50;
private String Datafilename="D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\Comb\\FeaturesDigitAllRegion_1221.txt";
private boolean GenerateCombinations=true;
private String featureFilename="D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\Comb\\feat.txt";
private int startCombinationDigit=1;
private int startCombinationFeature=1;
	/* (non-Javadoc)
	 * @see tasks.RunnableTask#addObserver(tasks.TaskController)
	 */
	@Override
	public void addObserver(TaskController taskController) {
		

	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#getSettings()
	 */
	@Override
	public TaskSettings getSettings() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#setSettings(tasks.TaskSettings)
	 */
	@Override
	public void setSettings(TaskSettings task) {
		

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ReadSetting(settingFilename);
		
		RunTaskDetails();
		
		saveSettings(settingFilename);
	}
private void RunTaskDetails() {
	logger.info( "inside the run taske..........");
	ArrayList<ClassifierData> dataArr ;
	if (GenerateCombinations)
	{
		dataArr = initClassifierData();
		logger.info( "  Total  number of combination is   "+dataArr.size() );
		 sort=false;
			//for future use
		SaveClassifiersDetails(dataArr, classifiersFilename );
	}
	 
	dataArr=ReadClassifiersDetails(classifiersFilename);
	ImageRecognizier IR = new ImageRecognizier();
	FeaturedRegion.setFeaturesToCompute(null);
	IR.setMainOptions(TrainMode,classifierType);
 	DataSet Alldataset;
	Alldataset=new DataSet();
	Alldataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
	Alldataset.ReadFromFile(Datafilename);
	
	String str = "";
	String newline = System.getProperty("line.separator");
	str += newline;

	// for startid to interuptid 
	for (int i = startTrainid; i < Interupt && i<dataArr.size(); i++) {
		ClassifierData data = dataArr.get(i);
		data.createFeatureNameList();
		logger.info(" the data i sin digit c1 " + data.digit
		+ "  and in digit c2 " + data.digitC2);
		DataSet dataset =Alldataset.GenerateDataSetFromSettings(data);		
		DataSet train=null;
		DataSet test=null;
		train=dataset.GenerateTrainDataSet();
		test=dataset.GenerateValidationDataSet();
		IR.setUseProbability( data.useProbability);
		IR.setTrainData(train);	
	
		IR.BuildClassifier();
	
		IR.Train();
		IR.setTestData(test);
		IR.Test();
		ClassifierResult result = IR.getClassifierResult();
		data.Result=result;
		str += "  Classifier  of  " + i + " Result ==> Percent Correct = "
				+ result.getPercentCorrect()
				+ "%   with number of Errors = "
				+ result.getNumberOfIncorrect() + " For classifier "
				+ data.getName() + "  With Features  "
				+ ArrayToString(data);
	
		str += newline;
	} 	
	 sort=false;
	//for future use
	 SaveClassifiersDetails(dataArr, classifiersFilename );
	
	 
	 
	 sort=true;
	 SaveClassifiersDetails(dataArr, classifiersFilename+"_Sort_i_"+startTrainid+"_To_"+Interupt );
	 ArrayList<ClassifierData>  best=new ArrayList<ClassifierData>();
	 //save best 20 in a single fiel 
	 for (int i = 0; i < 20; i++) {
		best.add( dataArr.get(i));
	}
	 SaveClassifiersDetails(best, classifiersFilename+"_Sort_i_"+startTrainid+"_To_"+Interupt+"_best20" );
	 SaveClassifierDetailsXls(dataArr, classifiersFilename);
	logger.info("##################################################################");
	logger.info(str);
	}



 
private void SaveClassifierDetailsXls(ArrayList<ClassifierData> dataArr,
		String filename) {
	 HSSFWorkbook wb = new HSSFWorkbook(); 
	 HSSFSheet sheet = wb.createSheet("classifier");
	 
	    HSSFRow row = sheet.createRow((short)0);
	    
	    row.createCell((short)0).setCellValue( new HSSFRichTextString ( " Classifier name  "));
	    row.createCell((short)1).setCellValue( new HSSFRichTextString ( " Digits   "));
	    row.createCell((short)2).setCellValue( new HSSFRichTextString ( " Featuers "));
	    row.createCell((short)3).setCellValue( new HSSFRichTextString ( " Region "));
	    row.createCell((short)4).setCellValue( new HSSFRichTextString ( " Results "));
	    row.createCell((short)5).setCellValue( new HSSFRichTextString ( " Detailed result ..."));
		
		for (int i = 0; i < dataArr.size(); i++) {
		
			   HSSFRow row2 = sheet.createRow((short)(i+1));
			   row2.createCell((short)(0)).setCellValue( new HSSFRichTextString ( dataArr.get(i).getName() ));	
			   row2.createCell((short)(1)).setCellValue( new HSSFRichTextString ( dataArr.get(i).digit.toString() ));	
			   row2.createCell((short)(2)).setCellValue( new HSSFRichTextString ( dataArr.get(i).feat.get(0).toString() ));	
			   row2.createCell((short)(3)).setCellValue( new HSSFRichTextString ( dataArr.get(i).regions.get(0).getRegionName() ));
			   
				
			   
	    		row2.createCell((short)(4)).setCellValue( dataArr.get(i).Accuracy);	
	    	
	    		//row2.createCell((short)(2)).setCellValue(FullDigitResults.get(i).getNumberOfIncorrect());	
		   
	    		
	    		if (dataArr.get(i).Result!=null){
	    			
	    			   row2.createCell((short)(3)).setCellValue( new HSSFRichTextString (dataArr.get(i).Result.toString() ));
	    			   
	    				
	    		}
			
		}
	 
		  FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream( filename+".xls");
				 wb.write(fileOut);
				    fileOut.close();
				    
				    
				    
				    
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		   
	 
	 
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

//private ArrayList<String> getFeatures(DataSet data){
//	return null;
//}
private ArrayList<String> getFeatures(String filename){
	 
	
	  ArrayList<String> feats=new ArrayList<String>();
	 try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
//        out.println(datastore);
//        out.println(features);
//        out.print(numberOfFeatures);
       
        if (scanner.hasNext())
        {
       	 String featuresString=scanner.nextLine();
       	featuresString=featuresString.trim();
       	 int lastcomma = featuresString.lastIndexOf(",");
       	 if (lastcomma>featuresString.length()-3){
       		 featuresString= featuresString.substring(0, lastcomma );
       	 }
       	 String[] FeatStrings = featuresString.split(",");
       	 
       	 for (int i = 0; i < FeatStrings.length; i++) {
			
       		 feats.add(FeatStrings[i].trim());
		}
       	 
        }
  
        scanner.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
	 return feats;
	
}

 private  ArrayList< ClassifierData>  initClassifierData(){	 
	 //get features
	 ArrayList<String> features;
	 features=getFeatures(featureFilename);
	 Integer[] digits=new Integer[MaxNumberOfDigit];
	 for (int i = 0; i < digits.length; i++) {
		digits[i]=new Integer(i); 
	}
	 
	 ArrayList<ArrayList<Integer>> combDigits=new ArrayList<ArrayList<Integer>>() ;
	 
	 for (int i = startCombinationDigit; i < MaxNumberOfDigitCombination; i++) {
	combDigits.addAll( lib.getCompinations(digits, i, 0));
	}
     logger.info( "  after getting all digit combinations "+combDigits.size())  ;
	 ArrayList<ArrayList<String>> combFeatures=new ArrayList<ArrayList<String>>();
	 
	 for (int i = startCombinationFeature; i < MaxNumberOfFeaturesCombination; i++) {
		 combFeatures.addAll( lib.getCompinations(features,i,0));
	}
	
     logger.info( "  after getting all featuers combinations "+combFeatures.size())  ;
	 ArrayList<RegionCreater>  combRegions=getCombRegions(); 
	 // now i have all combination of all digits and all features.. 
	 // create the data digits
     logger.info( "  after getting all regions combinations "+ combRegions.size())  ;
	 
	 ArrayList< ClassifierData>  data=new ArrayList<ClassifierData>();
	 ArrayList<Integer> digitC2 ;
	 //for all regions combinations
	 //for all features combinations 
	// for digit combinations 
	 for (int i = 0; i < combDigits.size(); i++) {
		 digitC2 = new ArrayList<Integer>();
			for (int j = 0; j < MaxNumberOfDigit; j++) {
				if (! lib.isInArray(j, combDigits.get(i)) ) {
					digitC2.add(new Integer(j));
				}
			}

		 logger.info( " in digit combination no. "+i+"  which is  "+combDigits.get(i)+"  vs. "+digitC2);
		 logger.info(" the array hold about  "+data.size());
		for (int j = 0; j < combRegions.size(); j++) {
			for (int j2 = 0; j2 < combFeatures.size(); j2++) {
				//
				data.add( getClassifierData(combDigits.get(i),digitC2,combFeatures.get(j2),combRegions.get(j)));
			}
		}
	}
	////////////////////
	 logger.info( "  after getting all  combinations "+ data.size())  ;
	 return data;
	 
 }
 private  ArrayList< RegionCreater> getCombRegions(){
	 
	 
	 ArrayList< RegionCreater>  regions=new ArrayList<RegionCreater>();
		int MaxH=1;
		int MaxV=1;
 
		for (int i = 0; i < MaxH; i++) {
			for (int j = 0; j < MaxV; j++) {
				regions.add(getRegion(MaxH, MaxV, i, j));
			}
				
		}
		
		
		MaxH=1;
	     MaxV=2;
		for (int i = 0; i < MaxH; i++) {
			for (int j = 0; j < MaxV; j++) {
				regions.add(getRegion(MaxH, MaxV, i, j));
			}
				
		}
		
		MaxH=2;
	     MaxV=1;
		for (int i = 0; i < MaxH; i++) {
			for (int j = 0; j < MaxV; j++) {
				regions.add(getRegion(MaxH, MaxV, i, j));
			}
				
		}
		
		return regions;
 }
 private ClassifierData getClassifierData(ArrayList<Integer> digit,ArrayList<Integer> digitC2,ArrayList<String> features,RegionCreater reg ){
	 
	 ClassifierData dat = new ClassifierData();
		dat.setType(ClassifierData.Binary);
		ArrayList<Integer> digitC1 =digit;
	
	
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		ArrayList<String> AllFeatures2;

		

	
		// AllFeatures2.add("wsb");
		feat.add(features);

		
		regions.add( reg);
		
		dat.setDigits(digitC1);
		 
		dat.digitC2 = digitC2;
		dat.feat = feat;
		dat.regions = regions;
		dat.createClassifierName();
	//	dat.createFeatureNameList();

		// logger.info(" the data i sin digit c1 "+digitC1.size()+"  and in digit c2 "+digitC2.size());
//		logger.info(" the data i  c1 " + dat.digit.size()
//				+ "  and in digit c2 " + dat.digitC2.size());

	 
	 return dat;
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
				if (inputString.trim().startsWith("Datafilename")) {
				 
					this.Datafilename= input.nextLine();
				}
				// now check for the following
				if (inputString.trim().startsWith("featureFilename")) {
				 
					this.featureFilename= input.nextLine();
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
				//GenerateCombinations
				if (inputString.trim().startsWith("GenerateCombinations")) {
					 
						GenerateCombinations= input.nextBoolean();
				 
					 
				}
				
				
				 
					if (inputString.trim().startsWith(" startDigit")) {
						inputInt = input.nextInt();
						 startCombinationDigit = inputInt;
					}
					
				if (inputString.trim().startsWith("DigitCombination")) {
					inputInt = input.nextInt();
					 MaxNumberOfDigitCombination = inputInt;
				}
				if (inputString.trim().startsWith("DigitsOnly")) {
					String temp=input.nextLine();
					 //inputInt = input.nextInt();
					// MaxNumberOfDigitCombination = inputInt;
				}
				if (inputString.trim().startsWith("FeaturesCombination")) {
					inputInt = input.nextInt();
					MaxNumberOfFeaturesCombination = inputInt;
				}
					if (inputString.trim().startsWith("startFeature")) {
						inputInt = input.nextInt();
						startCombinationFeature= inputInt;
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
	private void saveSettings(String Filename) {
logger.info("saving the settings.............");
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(Filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is a comment line ");
			out.println("ClassFile");
			out.println(classifiersFilename);
			
			out.println("##  Datafilename  ");
			out.println("Datafilename");
			out.println(Datafilename);
			
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
			out.println ( "### GenerateCombinations  with max number of digit in combinations , and features" );
			out.println ( "GenerateCombinations" );
			out.println (GenerateCombinations);
			out.println("startDigit");
			out.println(startCombinationDigit);
		
			out.println ( "DigitCombination" );
			out.println ( MaxNumberOfDigitCombination);
		
			out.println("startFeature");
			out.println(	startCombinationFeature);	
			out.println ( "FeaturesCombination" );
			out.println (MaxNumberOfFeaturesCombination);
			out.println("## the features fileee ");
			out.println("featureFilename");
			out.println(featureFilename);
			
	     
			
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
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
	

	}
	public void SaveClassifiersDetails(ArrayList<ClassifierData> ClassesData,
			String Filename) {
logger.info(" Saving classifiers details.   ");
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
					+ DataBaseConnector.MADBASE);
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
					//			logger.info("Reading Regionsss  "+tempR.size());
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


	private RegionCreater getRegion(int maxh, int maxv, int h, int v) {
		RegionCreater reg = new RegionCreater();
		reg.setMaxHorizontalRegion(maxh);
		reg.setMaxVerticalRegion(maxv);
		reg.setHorizonal(h);
		reg.setVertical(v);
		return reg;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		RunCombinationEcocClassifier test = new RunCombinationEcocClassifier();
		DataBaseConnector.OS = DataBaseConnector.OS_WINDOWS;
		Thread th = new Thread(test);
		th.run();
	}

}
