import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 
 */

/**
 * @author Maha
 *
 */
public class App implements Runnable{
	
	
	protected static final String FILE_NAME ="F:\\AUC\\Databases\\Arabic Digits Databases\\MAHDBase\\MAHDBase_TrainingSet\\Part01\\"+"writer041_pass02_digit6.bmp";
	protected static final  int CATEGORY_SIZE=10;
	protected static final  int  NO_OF_PASSES=10;
	protected static final  int  NO_OF_TRAIN_WRITERS=500;	
	protected static final  int  NO_OF_VALIDATION_WRITERS=600;
	protected static final  int  NO_OF_TEST_WRITERS=700;
	protected static final  int  NO_OF_PARTS=10;
	protected static final  int  NO_OF_DIGITS=10;
	private static final String TrainingProg = null;
	private static final String TrainingProgramName = "Neural.exe";
	
	private static final String TestProgramName="Neuraltest.exe";
	public static String ProgramFile="";
	private static DatabaseFeatureManager data=new DatabaseFeatureManager();
	private static transient final Logger logger = Logger.getLogger( App.class);
	protected static final int TRAIN = 0;
	protected static final int TEST=1;
	protected static final int TRAIN_THEN_TEST = 2;
	
	public static int State=TRAIN;
	
	protected static    int  Task=1;
	protected static boolean UseZero=true;
	
	
	
	
	
	/*
	protected static final  int CATEGORY_SIZE=10;
	protected static final  int  NO_OF_PASSES=10;
	protected static final  int  NO_OF_TRAIN_WRITERS=500;
	
	protected static final  int  NO_OF_VALIDATION_WRITERS=600;
	protected static final  int  NO_OF_TEST_WRITERS=500;
	protected static final  int  NO_OF_PARTS=10;
	protected static final  int  NO_OF_DIGITS=10;
	*/
	/**
	 * 
	 */
	public App() {
		// TODO Auto-generated constructor stub
	}
////////////////////////////////////INIT the program.........
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		
		// TODO Auto-generated method stub
		ImageFrame frame=new ImageFrame(); 
		frame.setVisible(true);
//		DataBaseConnector db=new DataBaseConnector();
//		db.getImageFilesForDigit(9);
		
		
            /*DigitImage Digit=new DigitImage();
            String filename="F:\\AUC\\Databases\\Arabic Digits Databases\\MAHDBase\\MAHDBase_TrainingSet\\Part01\\"+"writer001_pass02_digit4.bmp";
            
           Digit.ReadImage(filename);*/
	}
	
////////////////This code is to add threading 
	@Override
	public void run() {
		if ( Task==1)  
			///first task is to compute all features and create statistic for
			//each feautre
			RunAllTask();
		if (Task==2)
		{
            /// compute the features then 
			//save all features in file that can be used in the classification.  
		
		   	RunTrainTask();
		}
		
		if (Task==3){
			// use the java  program to run the train exe. 
			if ( State==TRAIN_THEN_TEST)
			{
				State=TRAIN;
				TrainAllFilesTask();
				State=TEST;
				TrainAllFilesTask();
			}
			else {
				TrainAllFilesTask();
			}
		
		}
		
	}
	
	
	
	public static void RunAll(String dirName,int type) {
		Task=1;
		data.setDataBaseType(type);
		data.setDataBaseDir(dirName);
		App runApp=new App();
		Thread	th=new Thread(runApp);
		th.run();

	}
	
	
	
	private void RunAllTask(){
		data.generateStateForAllDigits();
		
		 Date d=new Date();
	     SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy_hh-mm");
	    // formatter.applyPattern("y");
	     formatter.format(d);
	     String dat=formatter.format(d);
		data.storeAllDataToFile("result_"+dat+".xls");
	}
	
	public static BufferedImage RunFile(String filename){
		DigitImage Digit=new DigitImage();
		 // String filename=App.FILE_NAME;
          
         Digit.ReadImage(filename);
         Digit.computeAllFeatures();
       
         return Digit.getImage();
		
	}
	
	

	public static void RunTrain(String dirName,int type) {
		// TODO Auto-generated method stub
	
		///set the database type 
		//MINIST , MADBAse, ADBASE
		data.setDataBaseType(type);
		//set the base directory of the database. 
		data.setDataBaseDir(dirName);
		
		//choose task to run. 
		Task=2;
		//now create the thread to run the task. 
		App runApp=new App();
		Thread	th=new Thread(runApp);
		
		//run the thread. 
		th.run();
		
	}
	
	private void RunTrainTask(){
		
		/// the test usually with or without zero digit. 
		//this codition is test here. 
		if (UseZero)
			//if use zero in testing start data set with zero digit. 
			data.setStartD(0);
		else 
			//if not using zero then start data set with 1 
			data.setStartD(1);
		
		///check if do train. 
		
		if (State==TRAIN || State==TRAIN_THEN_TEST)
		{
			//if training set database status to choose the train data set. 
		      // this is used the database connector to choose which data set 
			//and select the files used. 
			data.db.Status=data.db.TRAIN;
			
			//compute features and store to file. 
			data.compteFeaturesToTrainFileAllDigit();
		}
		//if doing testing
	    if (State==TEST || State==TRAIN_THEN_TEST)
	    {
	    	//if training set database status to choose the test data set
			data.db.Status=data.db.TEST;
			
			
			//compute features and store to file. 
	    	data.compteFeaturesToTrainFileAllDigit();
	    }
		
		
		
		 System.out.println("--------------------------------");
	}

	public static BufferedImage RunGetFile(String filename,String dirName,int type, String fullFileName) {
	
		String tempFile;
		 tempFile=filename;
		if (filename!=""){
		DataBaseConnector db=new DataBaseConnector();
		db.setDataBaseType(type);
		db.setDataBaseDir(dirName);
		if (State==TRAIN)
			db.Status=db.TRAIN;
		if (State==TEST)
			db.Status=db.TEST;
		
		
	//	logger.trace(" type  = "+type);
	//	logger.trace(" dir  ="+dirName);
		filename=db.getFullPath(filename);
	//	logger.trace(" the file name return is  = "+filename);
		
		
		}
		else 
			filename=App.FILE_NAME;
		
		//create image 
		DigitImage Digit=new DigitImage();
		
		if (type==DataBaseConnector.MNIST)
		{
			
			if (filename.equals(""))
				
				Digit.ReadImage(fullFileName, Integer.parseInt(tempFile));
			else 
				Digit.ReadImage(filename, Integer.parseInt(tempFile));
				
		}
		else{
		
		
			   Digit.ReadImage(filename);
		 // String filename=App.FILE_NAME;
         
 
		}
		
	    
	        Digit.computeAllFeatures();
		
        return Digit.getImage();
		
	}
	

	public static void StartTrainingClassifers(){
	Task=3;
	Thread th;
	
	App runApp=new App();
	th=new Thread(runApp);
	th.run();
	
	
} 
	
	private void TrainAllFilesTask(){
		
		
		
		  Runtime r=Runtime.getRuntime();
		    Process p=null;
		    try
		    {
		    	  String s;
		    	  if (ProgramFile.equals(""))
		    	  {
		    		  
		    	
		    	if (State==TRAIN)
		    		
		    			s=TrainingProgramName;
		      
		    	else {
		    		
		    		s=TestProgramName;
		    	}
		    	  }
		    	  else 
		    		  s=ProgramFile;
		    	
		      p=r.exec(s);
		      
		      InputStream in = p.getInputStream();
		      OutputStream out = p.getOutputStream();
		      InputStream err = p.getErrorStream();
		  
		      BufferedReader input =
		          new BufferedReader
		            (new InputStreamReader(in));
		        String line;
				while ((line = input.readLine()) != null) {
		          logger.info(line);
		        }
		        input.close();
		        out.write(4);
			      


		    }
		    catch(Exception e){
		      System.out.println("error==="+e.getMessage());
		      e.printStackTrace();
		    }
		  
		
		
	}
	
	




	

}
