package oldToDelete;

 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import General.AppDataSettings;

public class ClassifierApp {
	
	private static final String MyProgram = "ClassfiersCompare ";
	private  String ProgramSVMLight="svm-train -s 0 -t 2 -c 100  -g 0.01   "; // -c 100  -g 0.01  
	//-g 0.1 -c 200 
	private static final String ProgramSVMPredict="svm-predict  ";
	// -g 0.01  -c 100
	private  String commandsClassifiers="-a 1  -class 2  -g 0.01  -c 100 -s 1 -flags 0 0  ";

	 String  TrainFilename="train_a24.txt";
	    String TestFilename="test_a24.txt";
	    String note="a24";
	public static String ProgramFile="";
	
	

	private static transient final Logger logger = Logger.getLogger(  ClassifierApp.class);
	Logger logLibSvm = Logger.getLogger("AppLogging");
	
	Logger logCompare=Logger.getLogger("MyAppLogging");
	private String ModelFile="train.model";
	private String PerdictFile;
	
	
//	public void RunSVMClassifier(String train,String test){
//		
//		
//		
//	}


	public void RunMyClassifier(int alg, String train, String test) {
		// TODO Auto-generated method stub
		
	}


	public void RunLibSVMClassifier(int alg, String train, String test) {
		// TODO Auto-generated method stub
		int t=1;
		if (alg==5)
		{
			t=2;
			
			ProgramSVMLight="svm-train -s 0 -t "+t+" -c "+AppDataSettings.C+" -g  "+AppDataSettings.g+"  ";
			
		}
		if (alg==1)
		{
			t=0; 
		ProgramSVMLight="svm-train -s 0 -t "+t+" ";
		}
		
		RunSvmLightTrain(train);
		this.PerdictFile =test.replace(".txt", "Perdict.out");
		RunSvmLightTest(test);
		
		
		}
	public void RunSvmLightTrain(String Train){
		 Runtime r=Runtime.getRuntime();
		    Process p=null;
 
		//   String TestFilename="Formated_test_a24.txt";
		    
		    try
		    {
		  
		    	
		    	 logger.info("--------Training the svm light-------------- with "+Train);
		    	String s=ProgramSVMLight+"   "+Train+"   "+ModelFile;
		    	logger.info(s);
		    	
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
			      
             logger.info("---------------finihsed training now testing ");

		    }
		    catch(Exception e){
		      System.out.println("error==="+e.getMessage());
		      e.printStackTrace();
		    }
		  
		
	}
	public void RunSvmLightTest( String TestFi ){
		 Runtime r=Runtime.getRuntime();
		    Process p=null;
		    //String  TrainFilename="Formated_train_a24.txt";
		    //String TestFi="Formated_"+TestFilename;
		  
		    String perdictFilename=this.PerdictFile;//TestFi.replace(".txt", "Perdict.out");
		    try
		    {
		  
		    	String s=ProgramSVMPredict+"   "+TestFi+"  "+this.ModelFile+"  "+perdictFilename;
		    	logLibSvm.info(" Testing result for  "+TestFilename);
		    	logger.info(s);	
		      p=r.exec(s);
		      
		      InputStream in = p.getInputStream();
		      OutputStream out = p.getOutputStream();
		      InputStream err = p.getErrorStream();
		  
		      BufferedReader input =
		          new BufferedReader
		            (new InputStreamReader(in));
		        String line;
				while ((line = input.readLine()) != null) {
					 logLibSvm.info(line);
					 logger.info(line);
		        }
		        input.close();
		        out.write(4);
		       // appLogger.info(" [Settings]  inside run the settings is "+settings.toString() );
		    	  
           // logger.info("-----------------------------------finihsed training now testing ");

		    }
		    catch(Exception e){
		      System.out.println("error==="+e.getMessage());
		      e.printStackTrace();
		    }
		  
		
	}

	public void TestLibSVM(int alg, String test, String modelFile,String perdict) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int t=1;
		if (alg==5)
		{
			t=2;
			
	ProgramSVMLight="svm-train -s 0 -t "+t+" -c "+AppDataSettings.C+" -g  "+AppDataSettings.g+"  ";
			
		}
		if (alg==1||alg==0)
		{
			t=0; 
		ProgramSVMLight="svm-train -s 0 -t "+t+" "+" -c "+AppDataSettings.C;
		}
		
		this.ModelFile=modelFile;
		this.PerdictFile=perdict;
		//RunSvmLightTrain(datafile);
		RunSvmLightTest(test);
	}
	public void TrainLibSVM(int alg, String datafile, String modelFile) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int t=1;
		if (alg==5||alg==2)
		{
			t=2;
			
			ProgramSVMLight="svm-train -s 0 -t "+t+" -c "+AppDataSettings.C+" -g  "+AppDataSettings.g+"  ";
			
		}
		if (alg==1||alg==0)
		{
			t=0; 
			ProgramSVMLight="svm-train -s 0 -t "+t+"  "+" -c "+AppDataSettings.C;
		}
		
		this.ModelFile=modelFile;
		RunSvmLightTrain(datafile);
		//RunSvmLightTest(test);
	}
}
