/**
 * 
 */
package General.tasks;

import java.util.ArrayList;

/**
 * @author Maha
 *
 */
public class TaskSettings {
	///"Train","Test","Formate","Validate","Train-Test","Verify","Command"

	public static final int TASK_TRAIN=0;
	public static final int TASK_TEST=1;
	public static final int TASK_FORMAT=2;
	public static final int TASK_VALIDATE=3;
	public static final int TASK_TRAIN_THEN_TEST=4;
	public static final int TASK_OPTIMIZE=5;
	public static final int TASK_COMMAND=6;
	
	
	public static final int TASK_LIB_LIBSVM=0;
	public static final int TASK_LIB_TORCH=1;
	public static final int TASK_LIB_WEKA=2;
	
	
	public static final int TASK_MULTI_ALG_SINGLE=0;
	public static final int TASK_MULTI_ALG_OVO=1;
	public static final int TASK_MULTI_ALG_OVA=2;
	
	
	public static final int TASK_ALG_SVM=0;
	public static final int TASK_ALG_SVM_LINEAR=1;
	public static final int TASK_ALG_SVM_RBF=2;
	public static final int TASK_ALG_LINEAR=3;
	public static final int TASK_ALG_GAUSSIAN=4;
	public static final int TASK_ALG_NEURAL=5;
	
	
	public static final int TASK_SVM_KERNEL_LINEAR=0;
	public static final int TASK_SVM_KERNEL_RBF=2;
 

	
	
	public TaskSettings(TaskSettings task2) {  //copy info from this task. 
		// TODO Auto-generated constructor stub
	}
	public TaskSettings(){  //generate the default paramters 
           //get the default settings 
		
		
	}
	public int CATEGORY_SIZE=10;
public double C=100;
public double g=0.01;
	 public int h=50;
 public double s=0.1;
	



	public   int State = 1;
	

	public   String ProgramFile = "";
 
	public   String featureFile="feature.txt";
	public   boolean GenearteTrainFiles=true;
	public   boolean GenearateTestFiles=true;
	public   boolean Train=true;
	public   boolean Test=true;
	
	public   int fromFormat=1,toFormat=3;
	public   String        FeatString;
	public   int KernelUsed;
	
	
	//public   final String TrainingProg = null;
 

	protected   int Task = 1;
	//public int taskType=0;
	public String taskSmallString="";
	
	public String taskDetailString="";

	public   String TrainFilename ="";// "train_a24.txt";
	public   String TestFilename = "";//"test_a24.txt";
	public   String note = "a24";
	public   String GenearalCommand="";
	public   boolean useAllFeatures=true;
	//String FeatureFile="feature.txt";
	
	public   String DirName=".";
	public   String FormatFilename="";
	public String OptionFilename=null;
	public boolean RunOptimize=false;
	int algorithm=0;
	String algorithmString="";
	int lib;
	String libString="";
	int MultiClassType=0;
	String MultiClassTypeString="";
	public  String FeatFile="feat.txt";
	
	public void setAlgorithm(int alg,String algs){
		algorithm=alg;
		algorithmString=algs;
	}
	public void setLibrary(int liba,String libs){
		lib=liba;
		libString=libs;
	}
	
	public void setFromFormat(int selectedIndex) {
		fromFormat=selectedIndex;
		
	}
	public void setToFormat(int selectedIndex) {
		toFormat=selectedIndex;
		
	}
	public void seMultiClassTypes(int selectedIndex, String selectedItem) {
		MultiClassType=selectedIndex;
		MultiClassTypeString=selectedItem;
		
		
	}
	public void setTask(int selectedIndex, String selectedItem) {
		this.Task=selectedIndex;
		this.taskSmallString=selectedItem;
		
	}
	public String getTaskString() {
		// TODO Auto-generated method stub
		return this.taskSmallString;
	}
	public void generateTheFullString() {
		 this.taskDetailString=this.taskSmallString;
		 if (!TrainFilename.equals(""))
		 this.taskDetailString+= " Train = "+TrainFilename;
		 if (!TestFilename.equals(""))
			 this.taskDetailString+= " Test = "+TestFilename;
		 if (!FormatFilename.equals(""))
			 this.taskDetailString+= " Train = "+FormatFilename;
//		 if (Task)
//			 this.taskDetailString+= " Train = "+TrainFilename;
		if(Task==this.TASK_FORMAT){
			
			 this.taskDetailString+="From "+this.fromFormat+" to"+this.toFormat;
			
		} 
		if (Task==TASK_COMMAND){
			
			 this.taskDetailString+=" "+GenearalCommand;
		}
		
	}
	public int getTaskType() {
		// TODO Auto-generated method stub
		return Task;
	}
	public int getKernel() {
		
		if (getAlgorithm()==TaskSettings.TASK_ALG_SVM)
		{
			// TODO Auto-generated method stub
			if (algorithm==TaskSettings.TASK_ALG_SVM_LINEAR)
			{
				 return TaskSettings.TASK_SVM_KERNEL_LINEAR;
				
			}
			else if (algorithm==TaskSettings.TASK_ALG_SVM_RBF)
				return TaskSettings.TASK_SVM_KERNEL_RBF;	
			
		}
		// TODO Auto-generated method stub
		return -1;
	}
	public int getAlgorithm() {
		// TODO Auto-generated method stub
		if (algorithm==TaskSettings.TASK_ALG_SVM_LINEAR)
		{
			 return TaskSettings.TASK_ALG_SVM;
			
		}
		else if (algorithm==TaskSettings.TASK_ALG_SVM_RBF)
			return TaskSettings.TASK_ALG_SVM;
		else
			return algorithm;
//		return 0;
	}
//	public String getFormatString(int toFormat2) {
//		
//		if (toFormat2==)
//		// TODO Auto-generated method stub
//		return null;
//	}
}
