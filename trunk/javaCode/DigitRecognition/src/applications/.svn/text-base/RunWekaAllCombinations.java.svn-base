/**
 * 
 */
package applications;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import classifiers.wekaClassifier;
import classifiers.results.ClassifierResult;
import data.dataset.DataBaseConnector;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

/**
 * @author TOSHIBA
 *
 */
public class RunWekaAllCombinations  implements RunnableTask{
	private static transient final Logger logger = Logger.getLogger( RunWekaAllCombinations.class);

	static int MaxFeat=62;
	static int MaxDigits=10;
	static boolean saveInside=true;
	static int MaxIterationWithoutSave=15;
	static String DataFileLocation="FeaturesAll.txt.arff";
	static String DigitDataFileLocation="Features";
	
	//static String DataFileLocation="D:\\AUC\\Programs\\testingWeka\\Latin\\All\\FeaturesAll.txt.arff";
	//static String DigitDataFileLocation="D:\\AUC\\Programs\\testingWeka\\Latin\\digits\\Features";
	
	static String[] FeatStrings= {   "wsb","lhg","lvg","cx","MCR","lhgi","lvgi","hOw","cy","pb","srby2","srby3","frup","frdown","frleft","frright","dirMaxW","MaxHBlackLength","AvgHBlackLength","MaxHBlackLengthLocation","MaxNumberOfHBlackBlocks","MaxNumberOfHBlackBlocksLocation","MaxVBlackLength","MaxVBlackLengthLocation","MaxNumberOfVBlackBlocks","MaxNumberOfVBlackBlocksLocation","wsbInLower","wsbInUpper","PBinLeftVsRight","PBinUpVsDown","PbCountD","PbCountU","PbCountL","PbCountR","PbCountLOverCountInRight","BlackWide","AverageWideUp","CountLowWide","CountBigWide","SrB3FromRight","SrB3FromLeft","FromRightUp","FromLeftDown","PbinF4R","PbinL4R","PbinL4C","PbinF4C","PbinF4CinUpper","CountNegativeTransition","CountLargeNegativeTransition","CountPositiveTransition","CountZeroTransition","SudenChangeFRight","SudenChangeFRightLocation","SudenChangeFLeft","SudenChangeFLeftLocation","AverageLastInlower","AverageLastinUpper","BorderLocationLengthInLastColumn","BorderLocationDownEnd","BorderLocationLastColumn","BorderLocationDownLength"  };
	
	//"wsb","lhg","lvg","cx","MCR","srby1","srby2","srby3","srby4","frup","frdown","frleft","frright","MaxHBlackLength","MaxHBlackLengthLocation","MaxNumberOfHBlackBlocks","MaxNumberOfHBlackBlocksLocation","MaxVBlackLength","MaxVBlackLengthLocation","MaxNumberOfVBlackBlocks","MaxNumberOfVBlackBlocksLocation" 
	ArrayList< ArrayList< ArrayList<ClassifierResult>>>  ResultSummary;
	ArrayList<ArrayList<Integer>> allTest;
	 ArrayList<ClassifierResult> FullDigitResults;

	private int TestMode=1;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	 
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		 DataBaseConnector.OS=DataBaseConnector.OS_LINUX;
		RunWekaAllCombinations test=new RunWekaAllCombinations();
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

	
	private void initTestCombinations(){
		
		
		Integer[] FeaturesToTest=new Integer[MaxFeat];
		for (int i = 0; i < FeaturesToTest.length; i++) {
			FeaturesToTest[i]=i;
		}
		
		allTest=new ArrayList<ArrayList<Integer>>(); 
				for (int Test = 0; Test <MaxFeat; Test++) {
					if (Test==1||Test==2)
						{
			//for the length of the features i need to test .......
			//do the following 
			logger.info(" generating compination using  "+Test+" features  ");
			///start creating the combination for by 0 , 1 , 2 , 3, n features
			ArrayList<ArrayList<Integer>> TestCompinations=this.getCompinations(FeaturesToTest, Test, 0);
			logger.info("found "+ TestCompinations.size() + " compinationssssss  ");
			for (int i = 0; i < TestCompinations.size(); i++) {
				
				allTest.add(TestCompinations.get(i));
				if (i%200==0)
				if (logger.isInfoEnabled())
				{
					
				display(TestCompinations.get(i));	
				}
			}
						}
			
			//now i have all test compinations........
			//this loop is for the 
			//CurrentFeatures=new ArrayList(); //creat the set of feature to test 
			
			
		}
				
					logger.info(" There are  "+allTest.size());
		
					logger.info(" **********************finished generating combinations Now will remove most of the generation and will remain only  ******************************");
					
					
//					
//					for (int i = 0; i < allTest.size(); i++) {
//						if (i%200==0)
//							logger.info(" iteration of i "+i);
//						if (allTest.get(i).size()>2 ){
//							
//							if (allTest.get(i).size()<15){
//								if (allTest.get(i).size()!=10)
//								 allTest.remove(i);
//							}
//							if (allTest.get(i).size()>=15){
//								if (allTest.get(i).size()!=15||allTest.get(i).size()!=20)
//								 allTest.remove(i);
//							}
//						
//						}
//					}
					
					
					logger.info(" There are   "+allTest.size()+"   after the removal of unwanted test ");
					
					logger.info(" ********************** The combination that will be test are  ******************************");
				
					
//					for (int i = 0; i < allTest.size(); i++) {	
//					
//					if (logger.isInfoEnabled())
//					{
//					display(allTest.get(i));	
//					}
//					}
					logger.info(" There are   "+allTest.size()+"   after the removal of unwanted test ");
					
					logger.info(" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@finished combinations @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					
					
	}
	private void SaveResults(){
		
		if (TestMode==1){
			saveToExcelAllDigit();
			
		}
		else {
			saveToExcel();
			
		}
	
		
		/*
		String tempString;
		for (int i = 0; i < ResultSummary.size(); i++) {
			logger.info("  dddddddddddddddddddddddddddddddddddddd "+i+" "+i+" "+i+" "+i+" "+i+" "+i+" "+i+" "+"dddddddddddddddddddddddddddddddddddddddddd");
		for (int j = 0; j < ResultSummary.get(i).size(); j++) {
			tempString="";
			for (int j2 = 0; j2 <  ResultSummary.get(i).get(j).size(); j2++) {
				tempString+="  "+ResultSummary.get(i).get(j).get(j2);	
			//	logger.info("  "+ResultSummary.get(i).get(j).get(j2));
				
				
			}
			logger.info(tempString);
		}
	}	
		*/
		
		
	}
	private void saveToExcelAllDigit(){
		 HSSFWorkbook wb = new HSSFWorkbook(); 
		 HSSFSheet sheet = wb.createSheet("digit");
		 
		    HSSFRow row = sheet.createRow((short)0);
		    row.createCell((short)0).setCellValue( new HSSFRichTextString ( " Feature set "));
		    row.createCell((short)1).setCellValue( new HSSFRichTextString ( " Percent  Correct  "));
		    row.createCell((short)2).setCellValue( new HSSFRichTextString ( " Number of error  "));
		 
		 
		 for (int i = 0; i <FullDigitResults.size(); i++) { //for classifier digit 
			

			   HSSFRow row2 = sheet.createRow((short)(i+1));
 
			    	
			    	 row2.createCell((short)(0)).setCellValue( new HSSFRichTextString (FullDigitResults.get(i).getFeatureString()));	
			   
			    		row2.createCell((short)(1)).setCellValue(FullDigitResults.get(i).getPercentCorrect());	
			    	
			    		row2.createCell((short)(2)).setCellValue(FullDigitResults.get(i).getNumberOfIncorrect());	
				    	
			    		
					 
			    	
			  
				}
			    
			    
			  
		 
		 
		 
		 
		 
		 
		  FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("savetfile_AllS"+FullDigitResults.size()+".xls");
				 wb.write(fileOut);
				    fileOut.close();
				    
				    
				    
				    
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		   

		 
		 
	//	    HSSFSheet sheet = wb.createSheet("digit"+i);
		//    HSSFRow row = sheet.createRow((short)0);
		    
		
		    
	
		//    row.createCell((short)0).setCellValue( new HSSFRichTextString (resultListVertices.get(i).TestName));

		
		
	}
	private void saveToExcel() {
		
		 HSSFWorkbook wb = new HSSFWorkbook();
		 for (int i = 0; i < ResultSummary.size(); i++) { //for classifier digit 
			 HSSFSheet sheet = wb.createSheet("digit"+i);
			    HSSFRow row = sheet.createRow((short)0);
			    row.createCell((short)0).setCellValue( new HSSFRichTextString ( " Start feature=> "));
			     // create the header of the sheet // number of features 
                  for(int j = 0; j < ResultSummary.get(i).size(); j++){
                	  row.createCell((short)(j+1)).setCellValue( new HSSFRichTextString ("Feature "+j));
                  }		
                  
                  int r=1;
			    for (int j = 0; j < ResultSummary.get(i).size(); j++) {  // for each Features  
			    	
			    	HSSFRow  row2= sheet.createRow(r);
			    	HSSFRow  row3= sheet.createRow(r+1);
			    	
			    	row2.createCell((short)0).setCellValue(new HSSFRichTextString (" to F "+j));
			    	row3.createCell((short)0).setCellValue(new HSSFRichTextString (" "));
			    	for (int k = 0; k < ResultSummary.get(i).get(j).size(); k++) {
			    		row2.createCell((short)(j+k+1)).setCellValue(ResultSummary.get(i).get(j).get(k).getPercentCorrect());	
			    	
			    		row3.createCell((short)(j+k+1)).setCellValue(ResultSummary.get(i).get(j).get(k).getNumberOfIncorrect());	
				    	
			    		
					}
			    	
			    	
				 	r+=2;
				}
			    
			    
			    
		}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		  FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("savetfile"+ResultSummary.size()+".xls");
				 wb.write(fileOut);
				    fileOut.close();
				    
				    
				    
				    
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		   

		 
		 
	//	    HSSFSheet sheet = wb.createSheet("digit"+i);
		//    HSSFRow row = sheet.createRow((short)0);
		    
		
		    
	
		//    row.createCell((short)0).setCellValue( new HSSFRichTextString (resultListVertices.get(i).TestName));

		
		
	}

	
	
private void GeneratClassifiers(){

		
		logger.info("inside the  run of weka classifier ");
		// ResultSummary=new ArrayList<ArrayList<ArrayList<ClassifierResult>>> () ;
		 ClassifierResult result ;

		int maximumFeatures=0;

	//	int MaxFeat=11;
		
		int iter=0;
		 
	 
		 FullDigitResults=new ArrayList<ClassifierResult> ();
		for (int j = 0; j < allTest.size(); j++) {
			ArrayList<Integer> featuresForTest=allTest.get(j);
			 // 
			// for each feature 
		 
				//featuresForTest.add(new Integer(j2));	
		        result = createClassifier(featuresForTest);
		        result.setClassifierString("  Classifer all features are from "+FeatToString(allTest.get(j)));
		        result.setSmallString("C"+FeatureIdToSmallString(allTest.get(j)));
		        result.setFeatString(FeatToString(allTest.get(j)) );
		        FullDigitResults.add(result);
		        if(saveInside){
		        iter++;
		       
		        if (iter>MaxIterationWithoutSave){
		        	
		        SaveResults();
		        iter=0;
		        }
		        }
		 
			
		}// features 
	 
	
		 
		logger.info("######################################################################################################################");
		//String tempString;

		for (int i = 0; i < FullDigitResults.size(); i++) {
			
					
					logger.info("  "+FullDigitResults.get(i));
			
		}
		logger.info("######################################################################################################################");
		
	
	}
	
	
	private void GenerateDigitClassifiers(){

		
		logger.info("inside the  run of weka classifier ");
		 ResultSummary=new ArrayList<ArrayList<ArrayList<ClassifierResult>>> () ;
		 ClassifierResult result ;

		int maximumFeatures=0;
int iter=0;
	//	int MaxFeat=11;
		 ArrayList<ClassifierResult> Temp;
		 ArrayList<  ArrayList<ClassifierResult>> digitResult;
		 
		 
		for (int i = 0; i < MaxDigits; i++) {
			digitResult=new ArrayList<ArrayList<ClassifierResult>>();
			for (int d2 = i+1; d2 < MaxDigits; i++) {
			
			Temp=	 new ArrayList<ClassifierResult> ();
		for (int j = 1; j < allTest.size(); j++) {
			ArrayList<Integer> featuresForTest=allTest.get(j);
			 // 
			// for each feature 
		 
				//featuresForTest.add(new Integer(j2));	
		        result = createSingleClassifier(i,d2,featuresForTest);
		        result.setClassifierString("  Classifer digit "+i+" features are from "+FeatToString(allTest.get(j)));
		        result.setSmallString("C"+i+"Vs"+d2+FeatureIdToSmallString(allTest.get(j)));
		        result.setFeatString(FeatToString(allTest.get(j)) );
			   Temp.add(result);
			   if(saveInside){
			        iter++;
			       
			        if (iter>MaxIterationWithoutSave){
			        	
			        SaveResults();
			        iter=0;
			        }
			        }
			
		}// features 
		digitResult.add(Temp);
	
		}// digit 
		ResultSummary.add(digitResult);
		}
		logger.info("######################################################################################################################");
		//String tempString;

		for (int i = 0; i < ResultSummary.size(); i++) {
			for (int j = 0; j < ResultSummary.get(i).size(); j++) {
				for (int j2 = 0; j2 <  ResultSummary.get(i).get(j).size(); j2++) {
					
					logger.info("  "+ResultSummary.get(i).get(j).get(j2));
					
				}
			}
		}	
		logger.info("######################################################################################################################");
		
	}
	private String FeatureIdToSmallString(ArrayList<Integer> st) {
		String str="";
		for (int i = 0; i < st.size(); i++) {
			str+=st.get(i);
			if (i<st.size()-1)
				str+="  , ";
		}
		return str;
	}

	private String FeatToString(ArrayList<Integer> st) {
		String str="";
		for (int i = 0; i < st.size(); i++) {
			str+=FeatIDString(st.get(i));
			if (i<st.size()-1)
				str+="  , ";
		}
		return str;
	}

	private String FeatIDString(Integer index) {
		
		if (index<FeatStrings.length)
		
	return	this.FeatStrings[index];
		else 
			return ""+index;
	
	}

	public ClassifierResult createSingleClassifier(int digit1, int digit2, ArrayList<Integer> featuresForTest ){
		
		 
		logger.info(" classifier  "+digit1+" versus  "+digit2);
		
			 wekaClassifier  weka=new 	 wekaClassifier ();
			 
			 weka.ReadDataFile(DigitDataFileLocation+digit1+digit2+".txt.arff");
			 
			 weka.setDefaultOptions();
			 
			 
			 weka.removeAttrib( featuresForTest );
			 
			 
			 weka.createClassifier();
			 weka.CrossValidate();
			 
		return    weka.getResult() ;
		
	}
	
	public ClassifierResult createClassifier( ArrayList<Integer> featuresForTest ){
		
		 
		//logger.info(" classifier  "+digit1+" versus  "+digit2);
		
			 wekaClassifier  weka=new 	 wekaClassifier ();
			 
			 weka.ReadDataFile(DataFileLocation);
			 
			 weka.setDefaultOptions();
			 
			 
			 weka.removeAttrib( featuresForTest );
			 
			 
			 weka.createClassifier();
			 weka.CrossValidate();
			 
		return    weka.getResult() ;
		
	}
	@Override
	public void run() {
		
		initTestCombinations();
		if (TestMode==1){
			GeneratClassifiers();
			
		}
		else {
			GenerateDigitClassifiers();
			
		}
		SaveResults();
	}
	
private ArrayList<ArrayList<Integer>> getCompinations( Integer [] source , int size,int starti){
		
		if (size==1)
		{
			ArrayList<ArrayList<Integer>> compinations=new ArrayList<ArrayList<Integer>>();
			 
			for (int i = starti; i < source.length; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(source[i]);
				compinations.add(temp);
			
			}
			return compinations;
			
		}
//		if (size==2){
//			
//			
//			
//		}
		else { 
			size--;
			//logger.info(" The size is  "+size);
			ArrayList<ArrayList<Integer>> AllCompinations=new ArrayList<ArrayList<Integer>>(); 
			for (int i = starti; i < source.length; i++) {
		 
				  ArrayList<ArrayList<Integer>> comp = getCompinations(source,size,i+1);
				  for (int j = 0; j < comp.size(); j++) {
					  comp.get(j).add(source[i]);
					  
					  AllCompinations.add(comp.get(j));
					
				}
							
			}
				return  AllCompinations;
			
			
		}
		
//		
//		for (int i = 0; i < source.length; i++) {
//			
//			
//			
//			
//		}
		
		
	}
private ArrayList<Integer[]> getTwoCompination ( Integer[] source  ) {
	 ArrayList<Integer[]> compinations=new ArrayList<Integer []>();
	
	 Integer[] temp=new Integer[2];
	
	
	for (int i = 0; i < source.length; i++) {
		for (int j = i; j < source.length; j++) {
			temp[0] = source[i];
			temp[1]=source[j];
			compinations.add(temp);
		}
	}
	
	
	return compinations;
	
	
	
	
}	
	

	@Override
	public void update(Observable o, Object arg) {
		

	}
	private void display(ArrayList<Integer> st){
		String str="";
		for (int i = 0; i < st.size(); i++) {
			str+=FeatIDString( st.get(i));
			if (i<st.size()-1)
				str+="  , ";
		}
	//	this.FeatureString=str.replace(" , ", "_");
		logger.info(str);
		
	}
}
