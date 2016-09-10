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

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

/**
 * @author TOSHIBA
 *
 */
public class RunWekaClassifier implements RunnableTask {
	private static transient final Logger logger = Logger.getLogger( RunWekaClassifier .class);
	//need to have data set connector 
	//MNISTDataSetGenerator  db;
	ArrayList< ArrayList<ArrayList<ClassifierResult>>>  ResultSummary;//=ArrayList<ArrayList<ClassifierResult>> ();
 	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		
		RunWekaClassifier test=new RunWekaClassifier();
		Thread th=new Thread(test);
		th.run();
		

	}
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
	
	
	public void saveToExcel(){
		
//		for (int i = 0; i < ResultSummary.size(); i++) {
//			for (int j = 0; j < ResultSummary.get(i).size(); j++) {
//				for (int j2 = 0; j2 <  ResultSummary.get(i).get(j).size(); j2++) {
//					
//					logger.info("  "+ResultSummary.get(i).get(j).get(j2));
//					
//					
//					
//				}
//			}
//		}
		
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
				fileOut = new FileOutputStream("savetfile.xls");
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
    
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		logger.info("inside the  run of weka classifier ");
		 ResultSummary=new ArrayList<ArrayList<ArrayList<ClassifierResult>>> () ;
		 ClassifierResult result ;

		int maximumFeatures=0;

		int MaxFeat=11;
		 ArrayList<ClassifierResult> Temp;
		 ArrayList<ArrayList<ClassifierResult>> digitResult;
		 
		 
		for (int i = 1; i < 10; i++) {
			
			digitResult=new ArrayList<ArrayList<ClassifierResult>>();
		for (int j = 1; j < MaxFeat; j++) {
			ArrayList<Integer> featuresForTest=new 	ArrayList<Integer> ();
			  Temp=	 new ArrayList<ClassifierResult> ();
			// for each feature 
			for (int j2 = j; j2 < MaxFeat; j2++) {
				featuresForTest.add(new Integer(j2));	
		        result = createClassifier(i,featuresForTest);
		        result.setClassifierString("  Classifer digit "+i+" features are from "+j+" to feature "+j2);
		        result.setSmallString("C"+0+"Vs"+i+"F"+j+"F"+j2);
			    Temp.add(result);
			}// the second feature
			digitResult.add(Temp);
		}// features 
		ResultSummary.add(digitResult);
		}// digit 
	
		logger.info("######################################################################################################################");
		//String tempString;
		for (int i = 0; i < ResultSummary.size(); i++) {
		for (int j = 0; j < ResultSummary.get(i).size(); j++) {
			for (int j2 = 0; j2 <  ResultSummary.get(i).get(j).size(); j2++) {
				
				logger.info("  "+ResultSummary.get(i).get(j).get(j2));
				
			}
		}
	}	
		
		this.saveToExcel();
		
		logger.info("######################################################################################################################");
		
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
		
		
		
		
		
		
		
		
		
	}
	
	public ClassifierResult createClassifier(int digit, ArrayList<Integer> featuresForTest ){
		
	 
		logger.info(" classifier  0 versus  "+digit);
		
			 wekaClassifier  weka=new 	 wekaClassifier ();
			 
			 weka.ReadDataFile("D:\\AUC\\Programs\\testingWeka\\Latin\\digit0\\Features0"+digit+".txt.arff");
			 
			 weka.setDefaultOptions();
			 
			 
			 weka.removeAttrib( featuresForTest );
			 
			 
			 weka.createClassifier();
			 weka.CrossValidate();
			 
		return    weka.getResult() ;
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		

	}

}
