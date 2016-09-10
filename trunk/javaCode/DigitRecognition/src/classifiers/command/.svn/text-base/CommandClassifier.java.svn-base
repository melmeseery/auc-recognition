/**
 * 
 */
package classifiers.command;

import java.util.Observable;

/**
 * @author Maha
 *
 */
public abstract class CommandClassifier extends Observable {

	
	protected String OptionString="";
	protected 	String TrainCommand="";
   	protected  String TestCommand="";
   	
   	

	 //protected String  TrainFilename="train_a24.txt";
	 //protected String TestFilename="test_a24.txt";
	/**
	 * 
	 */
//	public CommandClassifier() {
//		
//	}

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		
//
//	}
	
 public abstract void  Train(String filename,String modelfile);
 public abstract void Test(String filename, String modelfile,String perdictFilename);
 public  void SetOptionString(String options){
	 OptionString=options;
 }
 public abstract void SetOptions(String [] names,double [] options);
 
 
 public abstract double CrossValidate(String filename);
 
}
