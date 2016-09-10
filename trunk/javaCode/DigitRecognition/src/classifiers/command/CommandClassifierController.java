/**
 * 
 */
package classifiers.command;

import org.apache.log4j.Logger;

import data.dataset.DataSet;

 



/**
 * @author Maha
 *
 */
public class CommandClassifierController {
	private static transient final Logger logger = Logger.getLogger( CommandClassifierController.class);

	public static final int TASK_LIB_LIBSVM = 0;

	private static final int TASK_LIB_TORCH = 1;

	public static final int TASK_LIB_LIBLINEAR = 2;
	
	

	private static final int TASK_ALG_SVM = 0;

	public static final int TASK_SVM_KERNEL_RBF = 1;

	public static final int TASK_SVM_KERNEL_LINEAR = 2;

	private static final int TASK_ALG_NEURAL = 3;
	
  CommandClassifier  classifier;
  int alg;
  int kernel;
  int lib_type;
  double c,gamma,s;
  int hiddenlayers,degree;
  double error;
  
  String algorithmString="";
  
  String param=null;
	public int getAlg() {
	return alg;
}
public void setAlg(int alg) {
	this.alg = alg;
}
public int getKernel() {
	return kernel;
}
public void setKernel(int kernel) {
	this.kernel = kernel;
}

public double getC() {
	return c;
}
public void setC(double c) {
	this.c = c;
}
public double getGamma() {
	return gamma;
}
public void setGamma(double gamma) {
	this.gamma = gamma;
}
public double getS() {
	return s;
}
public void setS(double s) {
	this.s = s;
}
public int getHiddenlayers() {
	return hiddenlayers;
}
public void setHiddenlayers(int hiddenlayers) {
	this.hiddenlayers = hiddenlayers;
}
public int getDegree() {
	return degree;
}
public void setDegree(int degree) {
	this.degree = degree;
}
public double getError() {
	return error;
}
public void setError(double error) {
	this.error = error;
}
public String getParam() {
	return param;
}
public void setParam(String param) {
	this.param = param;
}
	/**
	 * 
	 */
	public CommandClassifierController() {
		c=Double.NaN;
		s=Double.NaN;
		gamma=Double.NaN;
		error=Double.NaN;
		
	}
	public void Train(String filename,String modelfile){
		//set the options accourding to classifier 
		
		 classifier.SetOptionString( CreateOptionString());
		 classifier.Train(filename, modelfile);
	}
	public void Test(String filename, String modelfile,String perdictFilename){
		 classifier.SetOptionString( CreateOptionString());
		 classifier.Test(filename, modelfile,perdictFilename);
		
	}
	public void TrainTest(String filename,String perdictFilename ){
		
		 classifier.SetOptionString( CreateOptionString());
		 classifier.Train(filename, filename+".model");
		 classifier.Test(filename,  filename+".model",perdictFilename);
		 
	}
	
	public void createClassifier(int type){
	//new type
		lib_type=type;
		if (lib_type==TASK_LIB_LIBSVM){
			classifier=new LibSvmCommandClassifier();
			
		}
		else  if (lib_type==TASK_LIB_TORCH)
		{
			classifier=new TorchCommandClassifier ();
		}
		else  if (lib_type==TASK_LIB_LIBLINEAR)
		{
			classifier=new   LibLinearCommandClassifier();
		}
			
			
	}
	private String CreateTorchOptions(){
		
		String op="";
		algorithmString="";
		if (alg==TASK_ALG_SVM)
		{
		  if (kernel==TASK_SVM_KERNEL_RBF)
		  {
			  op+=" -a 5";
			  algorithmString+= " SVM_ RBF kernel";
		  }
		  if (kernel==TASK_SVM_KERNEL_LINEAR)
		  {
			  op+=" -a 1";
			  algorithmString+= " SVM_ linear kernel";
		  }
 
			
		}
		if (alg==TASK_ALG_NEURAL)
		{
			 op+=" -a 6";
			  algorithmString+= "  Neural network ";
			  
				 if (!Double.isNaN(hiddenlayers))
					 	op+=" -h "+hiddenlayers; 
			  
		}
		 if (!Double.isNaN(c)){
			 	op+=" -c "+c;
			 	  algorithmString+=" c "+c;
			 	}
		 if (!Double.isNaN(gamma)){
			 	op+=" -g "+gamma;
			 	  algorithmString+=" gamma "+gamma;
		 }
		 
	
		 
		 if (!Double.isNaN(s))
			 	op+=" -s "+s; 
			if (param!=null)
				op+=param;
//		 if (!Double.isNaN(hiddenlayers))
//			 	op+=" -h "+hiddenlayers; 
		return op;
	}
	private String CreateLibSVMOptions(){
		String op=" -s 0 ";
		algorithmString="";
		if (alg==TASK_ALG_SVM)
		{
		  if (kernel==TASK_SVM_KERNEL_RBF)
		  {
			  op+=" -t 2";
			  algorithmString+= " SVM_ RBF kernel";
		  }
		  if (kernel==TASK_SVM_KERNEL_LINEAR)
		  {
			  op+=" -t 0";
			  algorithmString+= " SVM_ linear kernel";
		  }
 
			
		}
		
		 if (!Double.isNaN(c)){
			 	op+=" -c "+c;
			 	  algorithmString+=" c "+c;
			 	}
		 if (!Double.isNaN(gamma)){
			 	op+=" -g "+gamma;
			 	  algorithmString+=" gamma "+gamma;
		 }

		 // ;
		 if (!Double.isNaN(error))
			 	op+=" -e "+error; 
		
		if (param!=null)
			op+=param;
		return op;
	} 
	
	
	private String CreateLibLinearOptions(){
		String op=" -s 3 ";
		algorithmString="";
		if (alg==TASK_ALG_SVM)
		{
//		  if (kernel==TaskSettings.TASK_SVM_KERNEL_RBF)
//		  {
//			  op+=" -t 2";
//			  algorithmString+= " SVM_ RBF kernel";
//		  }
//		  if (kernel==TaskSettings.TASK_SVM_KERNEL_LINEAR)
//		  {
//			  op+=" -t 0";
//			  algorithmString+= " SVM_ linear kernel";
//		  }
 
			
		}
		
		 if (!Double.isNaN(c)){
			 	op+=" -c "+c;
			 	  algorithmString+=" c "+c;
			 	}
//		 if (!Double.isNaN(gamma)){
//			 	op+=" -g "+gamma;
//			 	  algorithmString+=" gamma "+gamma;
//		 }

		 // ;
		 if (!Double.isNaN(error))
			 	op+=" -e "+error; 
		
		if (param!=null)
			op+=param;
		return op;
	} 
	
	
	private String CreateOptionString(){
		String ops="";
		if (lib_type==TASK_LIB_LIBSVM){
			ops= CreateLibSVMOptions();
			
		}
		else  if (lib_type==TASK_LIB_TORCH)
		{
		ops= CreateTorchOptions();
		}
		else if (lib_type==TASK_LIB_LIBLINEAR){
			
			ops= CreateLibLinearOptions();
			
		}
		logger.info(" Option String...  "+ops);
		return ops;
		
	}
	public int getClassifierInputFormat() {
		if (lib_type==TASK_LIB_LIBSVM||lib_type==TASK_LIB_LIBLINEAR){
			return DataSet.FILE_INPUT_FORMAT_LIBSVM;
			
		}
		else  if (lib_type==TASK_LIB_TORCH)
		{
			return DataSet.FILE_INPUT_FORMAT_TORCH;
		}
		
		return 0;
	}
	public String getAlgorithmString() {
		
		return this.algorithmString;
	}
 

}
