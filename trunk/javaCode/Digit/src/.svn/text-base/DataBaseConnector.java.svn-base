import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
public class DataBaseConnector {
	
	private static final Logger logger = Logger.getLogger(DataBaseConnector.class);
// this class connect to the database 
	// for adbase it generate the file names and get the pathss 
	//it can create the madbase from the adbase then generate the file name
	//for minist it read the image from the main file.
	DecimalFormat formatw=new DecimalFormat("000");
	DecimalFormat formatp=new DecimalFormat("00");
	protected static String ADbaseBaseDir="F:\\AUC\\Databases\\Arabic Digits Databases\\AHDBase\\";
	protected static  String MADbaseBaseDir="F:\\AUC\\Databases\\Arabic Digits Databases\\MAHDBase\\";
	protected static   String MNISTbaseBaseDir=" ";
	
	
	
	protected static String curDir="";
	
	protected static final int MADBASE=0;
	protected static final int ADBASE=1;
	protected static final int MNIST=2;

	
	
	protected static final int TRAIN=0;
	protected static final int VALIDATE=1;
	protected static final int TEST=2;
	private static   int StartD = 0;
	 
	protected static int Status=TRAIN;
	
	
	int DatabaseType=MADBASE;
	
	
	 int MaxW=0;
	 int MinW=0;
	

	 
	 public String getFullPath(String filename){
		 this.setCurrentDatabaseDir();
		 
		 if(this.DatabaseType==this.MNIST){
			 
			 
			 String returnFile=this.curDir+"\\";
		
			 if (this.Status==this.TRAIN)
			 {
				 returnFile+="train-images.idx3-ubyte";
				 
			 }
			 else if (this.Status==this.TEST){
				 
				 returnFile+="t10k-images.idx3-ubyte";
				 
			 }
			 else {
				 returnFile="";
			 }
			 
			 return returnFile;
			 
			 
			 
			 
			 
			 
		 }
		 
		 
		 ////Part01\\"+"writer041_pass02_digit6.bmp
		 String temp= curDir+this.getSetName()+"\\";
		 
		 String writer="";
		 
		 logger.trace(filename);
		 int wi = filename.indexOf("writer");
		 String writerS="writer";
		 logger.trace(filename.substring(wi+writerS.length(),wi+writerS.length()+3) );
		  int Writernumber=Integer.parseInt(filename.substring(wi+writerS.length(),wi+writerS.length()+3));
		 logger.trace("   writernumber "+Writernumber); 
		  String part= this.getPartNo(Writernumber);
	
		  
		  temp+=part+"\\"+filename;
		 
		 
		 
		 
		 
		 return temp;
		 
	 }
	 
	 private void setMinMaxW()
	 {
		 
		 //// switch on the numbers of writers to get from the database. 
			switch (Status) {
			case TRAIN:
				MinW=1;
				MaxW=App.NO_OF_TRAIN_WRITERS+1;
				break;

			case VALIDATE:
				MinW=App.NO_OF_TRAIN_WRITERS+1;
				MaxW=App.NO_OF_VALIDATION_WRITERS+1;
				break;
			case TEST:
				MinW=App.NO_OF_VALIDATION_WRITERS+1;
				MaxW=App.NO_OF_TEST_WRITERS+1;
				break;
				
				
			default:
				MinW=0;
				MaxW=App.NO_OF_TRAIN_WRITERS+1;
				break;
			}
			 
		 
	 }
	 public void setStartD(int d){
		 StartD =d;
	 }
 	 private String getSetName()
 	 {
 		 String setname="";
 		 if (DatabaseType==ADBASE){
 		 if (Status==TRAIN ||Status==VALIDATE){
 			 
 			 //this is the training set. 
 			setname="AHDBase_TrainingSet"; 
 		 }
 		 else {
 			setname="AHDBase_TestingSet";
 			 
 		 }
 		 }
 		 else if (DatabaseType==MADBASE) {
 			 
 			 if (Status==TRAIN||Status==VALIDATE){
 	 			 
 	 			 //this is the training set. 
 	 			setname="MAHDBase_TrainingSet"; 
 	 		 }
 	 		 else {
 	 			setname="MAHDBase_TestingSet";
 	 			 
 	 		 }
 			 
 			 
 			 
 		 }
 		 
 		 
 		 
 		 return setname;
 	 }
	 private String getPartNo(int w){
		String part_no="";

        if(w<=50)
            part_no="Part01";
        else if(w<=100)
            part_no="Part02";
        else if(w<=150)
            part_no="Part03";
        else if(w<=200)
            part_no="Part04";
        else if(w<=250)
            part_no="Part05";
        else if(w<=300)
            part_no="Part06";
        else if(w<=350)
            part_no="Part07";
        else if(w<=400)
            part_no="Part08";
        else if(w<=450)
            part_no="Part09";
        else if(w<=500)
            part_no="Part10";
        else if(w<=550)
            part_no="Part11";
        else if(w<=600)
            part_no="Part12";
        else if(w<=650)//%%%%%%%%% they are in test set not training. 
            part_no="Part01";
        else
            part_no="Part02";
		return part_no;
	 }
	 
	 private String getWriterno(int w){
		return  formatw.format(w);
	 }
	 private String getPassNo(int p){
		 
			return  formatp.format(p);
		 
	 }  
	 public void setDataBaseType(int db){
		DatabaseType=db;
		
	}
		private void setCurrentDatabaseDir(){
			
			
			switch (DatabaseType) {
			case MADBASE:
				 curDir=MADbaseBaseDir;
				break;
			case MNIST:
				 curDir=MNISTbaseBaseDir;
				break;
				
			case  ADBASE:
				curDir=ADbaseBaseDir ;
				break;
			default:
				curDir=MADbaseBaseDir;
				break;
			}
			
		} 
	public void setDataBaseDir(String dir)
	{
		switch (DatabaseType) {
		case MADBASE:
			MADbaseBaseDir=dir;
			break;
		case MNIST:
			MNISTbaseBaseDir=dir;
			break;
			
		case  ADBASE:
			ADbaseBaseDir=dir;
			break;
		default:
			MADbaseBaseDir=dir;
			break;
		}
	//	this.curDir=dir;
		
		
	}
 
	
	public  ArrayList<String>   getImageFilesForWriter(int w){
		
		return null;
	}
	public   ArrayList<String> getImageFilesForPass(int p){
		
		return null;
	}
	public  ArrayList<String> getImageFilesForDigit(int d){
		
		 ArrayList<String> files=new ArrayList<String> ();
		
	 files.ensureCapacity( App.NO_OF_PASSES*App.NO_OF_TRAIN_WRITERS);
		
	 
	 this.setCurrentDatabaseDir();
	
	 String dir, filename,part="";
	 dir=curDir+this.getSetName()+"\\";
	 
	 this.setMinMaxW();
	
	 
	// System.out.println(" iiiiiiiiiiiiiiiinnnnnnnnnn the main ");
	 for (int w = MinW; w < MaxW; w++) {
		 part =getPartNo(w)+"\\";
	 for (int p = 1; p < App.NO_OF_PASSES+1; p++) {
		 
		 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
		 if (w<100)
		 logger.trace("   adding file     "+dir+part+filename);
		 files.add(dir+part+filename);
			
		}
		
	}
		
	
		return files;
	}
	public ArrayList<String> getImageFilesForDigitForPass(int d,int p){
		
		
		return null;
	}
	public  ArrayList<String> getImageFilesForAll() {
		
		 ArrayList<String> files=new ArrayList<String> ();
			
		 files.ensureCapacity( App.NO_OF_PASSES*App.NO_OF_TRAIN_WRITERS);
			
		 
		 this.setCurrentDatabaseDir();
		
		 String dir, filename,part="";
		 dir=curDir+this.getSetName()+"\\";
		 
		 this.setMinMaxW();
		
		 
		// System.out.println(" iiiiiiiiiiiiiiiinnnnnnnnnn the main ");
		 for (int w = MinW; w < MaxW; w++) {
			 part =getPartNo(w)+"\\";
		 for (int p = 1; p < App.NO_OF_PASSES+1; p++) {
			 
			 for (int d=StartD; d<App.CATEGORY_SIZE; d++){
			 
			 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
			 if (w<100)
			      logger.trace("   adding file     "+dir+part+filename);
			 files.add(dir+part+filename);
			 
			 }
				
			}
			
		}
			
		
			return files;
	}

	public int getStartD() {
		 
		return StartD;
	}
	
	
	
	
}
