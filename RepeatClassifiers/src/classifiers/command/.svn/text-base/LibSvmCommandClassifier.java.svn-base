/**
 * 
 */
package classifiers.command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import oldToDelete.ClassifierApp;

import org.apache.log4j.Logger;



/**
 * @author Maha
 *
 */
public class LibSvmCommandClassifier extends CommandClassifier {

	private static transient final Logger logger = Logger.getLogger( LibSvmCommandClassifier.class);
	Logger logLibSvm = Logger.getLogger("svmLogging");

	/**
	 * 
	 */
	public LibSvmCommandClassifier() {
		// TODO Auto-generated constructor stub
		this.TrainCommand="svm-train ";
		this.TestCommand="svm-predict  ";
	}
	@Override
	public void SetOptions(String [] names,double [] options){
      //change from 
	}

	/* (non-Javadoc)
	 * @see classifiers.CommandClassifier#CrossValidate(java.lang.String)
	 */
	@Override
	public double CrossValidate(String filename) {
		// TODO Auto-generated method stub
		return 0;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Test(String filename, String modelfile,String perdictFilename) {
		 Runtime r=Runtime.getRuntime();
		    Process p=null;
		    //String  TrainFilename="Formated_train_a24.txt";
		    //String TestFi="Formated_"+TestFilename;
		  
		    //String perdictFilename=filename;//TestFi.replace(".txt", "Perdict.out");
		    try
		    {
		  
		    	String s=this.TestCommand+" "+filename+" "+modelfile+"  "+perdictFilename;
		    	logger.info(" Testing result for  "+filename);
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
					 //logger.info(line);
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

	@Override
	public void Train(String filename, String modelfile) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		 Runtime r=Runtime.getRuntime();
		    Process p=null;

		//   String TestFilename="Formated_test_a24.txt";
		    
		    try
		    {
		  
		    	
		    	 logger.info("--------Training the svm light-------------- with "+filename);
		    	String s=this.TrainCommand+" "+this.OptionString+"  "+filename+"  "+modelfile;
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
					 //logger.info(line);
					logLibSvm.info(line);
		        }
		        input.close();
		        out.write(4);
			      
               logger.info("---------------finihsed training-------------------- ");

		    }
		    catch(Exception e){
		      logger.error("error==="+e.getMessage());
		      e.printStackTrace();
		    }
	
	}

}
