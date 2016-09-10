/**
 * 
 */
package General.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import oldToDelete.AppClassifiers;

import org.apache.log4j.Logger;

import General.AppDataSettings;

import classifiers.MultiClassClassifier;
import data.DataSet;




/**
 * @author Maha
 *
 */
public class RunnableTask extends Observable implements Runnable,Observer {
	private static transient final Logger logger = Logger.getLogger(RunnableTask.class);
	Logger logLibSvm = Logger.getLogger("AppLogging");
	Logger logTorch = Logger.getLogger("MyAppLogging");
	Logger logCommand = Logger.getLogger("commandlog");
    String StatusString=null;

 
	TaskSettings settings;

	/**
	 * 
	 */
	public RunnableTask(TaskSettings s) {
		// TODO Auto-generated constructor stub
		settings=s;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
	
	   switch (settings.getTaskType()) {
	case TaskSettings .TASK_TRAIN:
		RunTaskTrain();
		break;
	case TaskSettings.TASK_TEST:
		RunTaskTest();
		break;
		
	case  TaskSettings.TASK_FORMAT:
		RunTaskFormat();
		break;
	case  TaskSettings.TASK_VALIDATE:
		RunTaskValidate();
		break;
	case  TaskSettings.TASK_TRAIN_THEN_TEST:
		RunTaskTrainTest();
		break;
	case  TaskSettings.TASK_OPTIMIZE:
		RunTaskVerify();
		break;
	case  TaskSettings.TASK_COMMAND:
		RunTaskCommand();
		break;
	default:
		break;
	}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
      public void RunTaskCommand(){
    	  
    	  // run progr
    	  Runtime r = Runtime.getRuntime();
  		Process p = null;
  		// String TrainFilename="Formated_train_a24.txt";
  		 

  		try {

  			String s =  settings.GenearalCommand;
  			StatusString=" Running the command : "+s;
  			this.setChanged();
  			this.notifyObservers();
  			//logLibSvm.info(" Testing result for  " + TestFilename);
  			logCommand.trace(s);
  			p = r.exec(s);
  			
  			InputStream in = p.getInputStream();
  			OutputStream out = p.getOutputStream();
  			InputStream err = p.getErrorStream();

  			BufferedReader input = new BufferedReader(new InputStreamReader(in));
  			BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
  			logCommand.trace("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
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
 			StatusString=" Command finished.... ";
  			this.setChanged();
  			this.notifyObservers();
  			input.close();
  			out.write(4);


  		} catch (Exception e) {
  			logger.error("error===" + e.getMessage());
  			e.printStackTrace();
  		}
    	  
      }
      
      
      public void RunTaskFormat(){
    	  
    	  logger.trace(" in the format cooooooode. ");
    	  
    	  
    	  DataSet data=new DataSet();
    	  data.addObserver(this);
  		data.setFormat(settings.fromFormat);
  		data.ReadFromFile(settings.FormatFilename);
  		StatusString=" Finsih reading file";
  		setChanged();
  	    notifyObservers();
  		data.setFormat(settings.toFormat);
  		String filename=settings.FormatFilename;
  		if (settings.FormatFilename.contains("\\")){
  			
  			int lastindexSlash=settings.FormatFilename.lastIndexOf("\\");
  		String filnameonly=settings.FormatFilename.substring(lastindexSlash+1 );
  		String Dirname=settings.FormatFilename.substring(0, lastindexSlash+1);
  		
  		filename=Dirname+ "Format_"+data.getFormatString(settings.toFormat)+filnameonly;
  		}
  		data.SaveToFile( filename);
  		StatusString=" Finsih save formated file";
  		setChanged();
  	    notifyObservers();
    	  
      }
      public void RunTaskValidate(){
    	  
    	  logger.trace(" in the validat ecooooooode. ");
    	  
      }
      public void RunTaskVerify(){
    	  logger.trace(" in the verify  cooooooode. ");
      }
      public void RunTaskTrainTest(){
    	  logger.trace(" in the task tarin  cooooooode. ");
    	  RunTaskTrain();
    	  RunTaskTest();
      }
      public void RunTaskTrain(){
    	  logger.trace(" in the train  cooooooode. ");
    	  //check if task is train ovo system 
    	  if (settings.MultiClassType!=TaskSettings.TASK_MULTI_ALG_SINGLE){
    		  //not single classifier..............
    		  
    		  
    		  MultiClassClassifier multi;
    		  multi=new MultiClassClassifier();
    		  multi.addObserver(this);	 
    		  multi.initClassifier(settings.getAlgorithm(), settings.getKernel());
    		  multi.setInputFormat(settings.fromFormat);
    		  multi.ReadFeatureFile(settings.FeatFile);
   		
    		  if(settings.MultiClassType==TaskSettings.TASK_MULTI_ALG_OVO){
    				StatusString=" Start OVO train of  : "+settings.TrainFilename;
    	  			this.setChanged();
    	  			this.notifyObservers();		  
    			  multi.TrainOVOCommand(settings.TrainFilename);
    		  }else if  (settings.MultiClassType==TaskSettings.TASK_MULTI_ALG_OVA)
    		  {
    			  //multi.TrainOVACommand(settings.TestFilename);
    			  
    		  }
    		  
    		  
    	  }
    	  else{
    		  
    		  
    	  }
    	  
      }
      public void RunTaskTest(){
    	  logger.trace(" in the test cooooooode. ");
    	  if (settings.MultiClassType!=TaskSettings.TASK_MULTI_ALG_SINGLE){
    		  //not single classifier..............
    		  
    		 
    		  
    		  MultiClassClassifier multi;
    		  multi=new MultiClassClassifier();
    		  multi.addObserver(this);
    		  AppDataSettings.setSettings(settings);
    		  multi.initClassifier(settings.getAlgorithm(), settings.getKernel());
    		  multi.ReadFeatureFile(settings.FeatFile);
    		  multi.setInputFormat(settings.fromFormat);
    		  if(settings.MultiClassType==TaskSettings.TASK_MULTI_ALG_OVO){
    				StatusString=" Start OVO test of  : "+settings.TestFilename;
    	  			this.setChanged();
    	  			this.notifyObservers();
    			  multi.TestOVOCommand(settings.TestFilename);
    		  }
    		  else if  (settings.MultiClassType==TaskSettings.TASK_MULTI_ALG_OVA)
    		  {
    			  //multi.TestOVACommand(settings.TestFilename);
    			  
    		  }
    		  
    		  
    	  }
    	  else{
    		  //single classifer only.............. do the rest   
    		  
    	  }
    	
      }

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//if (o instanceof MultiClassClassifier ){
			
			if (o instanceof MultiClassClassifier) {
				MultiClassClassifier m = (MultiClassClassifier) o;
                  //  get info for display  of status
				
				// update the oberserver to make the  update to the intefcase. 
				this.setChanged();
				this.notifyObservers();
				
				
				
			}
			
		
		
	}
}
