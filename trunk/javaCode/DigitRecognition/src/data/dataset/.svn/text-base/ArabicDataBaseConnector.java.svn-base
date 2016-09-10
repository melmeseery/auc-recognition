package data.dataset;


import gui.AppDefaults;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import data.feature.Feature;
import data.image.Digit;
import data.image.FeaturedRegion;
import data.image.ImageReader;
import data.image.RegionCreater;

public class ArabicDataBaseConnector extends  DataBaseConnector{
	
	private static final Logger logger = Logger.getLogger(ArabicDataBaseConnector.class);
// this class connect to the database 
	// for adbase it generate the file names and get the pathss 
	//it can create the madbase from the adbase then generate the file name
	//for minist it read the image from the main file.
	DecimalFormat formatw=new DecimalFormat("000");
	DecimalFormat formatp=new DecimalFormat("00");

	public   int Train_Writer_Start=0;
	public int  Train_Writer_End=0;
	
	public    int Validation_Writer_Start=0;
	public    int  Validation_Writer_End=0;
	public  boolean 	useSampleSize=false;  
	public   int  SampleSetSize=100;
	
	public  boolean MovingValidation=false;
	
	
	 int MaxW=0;
	 int MinW=0;
	
	 protected void getCurrentSettings(){
		   Train_Writer_Start=AppDefaults.Train_Writer_Start;
		Train_Writer_End=AppDefaults.Train_Writer_End;
			
			    Validation_Writer_Start=AppDefaults.Validation_Writer_Start;
			     Validation_Writer_End=AppDefaults.Validation_Writer_End;
		 useSampleSize=AppDefaults.useSampleSize;  
		    SampleSetSize=AppDefaults.SampleSetSize;
			
			MovingValidation=AppDefaults.MovingValidation;
		 
		 
	 }
	 
		protected  static   int StartD = 0;
		 public void setStartD(int d){
			 StartD =d;
		 }
		 public int getStartD() {
			 
				return StartD;
			}
		 
	 
	 
	 public String getFullPath(String filename){
		 if (filename.contains( AppDefaults.CurrentOSSeperator))
           return filename;		 
		 
		 this.setCurrentDatabaseDir();		 
		 ////Part01\\"+"writer041_pass02_digit6.bmp
		 
		 
		 String temp= curDir+this.getSetName()+DefaultSeperator;
		 
		 String writer="";
		 
		 logger.trace(filename);
		 int wi = filename.indexOf("writer");
		 String writerS="writer";
		 logger.trace(filename.substring(wi+writerS.length(),wi+writerS.length()+3) );
		  int Writernumber=Integer.parseInt(filename.substring(wi+writerS.length(),wi+writerS.length()+3));
		 logger.trace("   writernumber "+Writernumber); 
		  String part= this.getPartNo(Writernumber);
	
		  
		  temp+=part+DefaultSeperator+filename;
		 
	
		 return temp;
		 
	 }
	 
	 
	 private void setMovingMinMaxW()
	 {
	 
		 //// switch on the numbers of writers to get from the database. 
			switch (Status) {
			
			
			case TRAIN:
				MinW= Train_Writer_Start;
				MaxW=Train_Writer_End;
				break;
			case  VALIDATE_TRAIN:
				MinW= Train_Writer_Start;
				MaxW=Train_Writer_End;
				break;
				
				
			case VALIDATE_TEST:
				
				MinW= Validation_Writer_Start;
				MaxW=Validation_Writer_End;

				
				break;

			case TEST:
				MinW= Validation_Writer_Start;
				MaxW=Validation_Writer_End;
				break;
				
				
			default:
	 
				break;
			}
			
			if ( useSampleSize){
				// old 
				int old=MaxW;
				// make maxWi  the min of both .. 
				int newM=MinW+SampleSetSize;
				// this if will make sure that the maximum
				//number of new will not exceed and accept old pattern.. 
				if (old>newM)
							MaxW=newM;
				else 
					MaxW=old;
			}
			 
		 
	 }
	 
	 
	 private void setMinMaxW()
	 {
	 
		 //// switch on the numbers of writers to get from the database. 
			switch (Status) {
			
			
			case TRAIN:
				MinW=1;
				MaxW=AppDefaults.NO_OF_VALIDATION_WRITERS+1;
				break;
			case  VALIDATE_TRAIN:
				MinW=1;
				MaxW=AppDefaults.NO_OF_TRAIN_WRITERS+1;
				break;
				
				
			case VALIDATE_TEST:
				
				MinW=AppDefaults.NO_OF_TRAIN_WRITERS+1;
				MaxW=AppDefaults.NO_OF_VALIDATION_WRITERS+1;
				
				//ogger.info("inside validate test status...  "+MinW+"  to "+MaxW  );
				
				break;
				
//			case VALIDATE:  /// this is redundant just for sake of backtrack 
//				MinW=AppDefaults.NO_OF_TRAIN_WRITERS+1;
//				MaxW=AppDefaults.NO_OF_VALIDATION_WRITERS+1;
//				break;		
			case TEST:
				MinW=AppDefaults.NO_OF_VALIDATION_WRITERS+1;
				MaxW=AppDefaults.NO_OF_TEST_WRITERS+1;
				break;
				
				
			default:
				MinW=0;
				MaxW=AppDefaults.NO_OF_TEST_WRITERS+1;
				break;
			}
			
			if ( useSampleSize){
				// old 
				int old=MaxW;
				// make maxWi  the min of both .. 
				int newM=MinW+SampleSetSize;
				// this if will make sure that the maximum
				//number of new will not exceed and accept old pattern.. 
				if (old>newM)
			MaxW=newM;
				else 
					MaxW=old;
			}
			 
		 
	 }

 	 private String getSetName()
 	 {
 		 String setname="";
 		 if (DatabaseType==ADBASE){
 			 
 			// if (Status==TRAIN ||Status==VALIDATE||Status==VALIDATE_TEST||Status==VALIDATE_TRAIN){
 		 		
 		 if (Status==TRAIN ||Status==VALIDATE_TEST||Status==VALIDATE_TRAIN){
 			 
 			 //this is the training set. 
 			setname="AHDBase_TrainingSet"; 
 		 }
 		 else {
 			setname="AHDBase_TestingSet";
 			 
 		 }
 		 }
 		 else if (DatabaseType==MADBASE) {
 			 
 			 if (Status==TRAIN||Status==VALIDATE_TEST||Status==VALIDATE_TRAIN){
 	 			 
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
	
	
 
	
	public  ArrayList<String>   getImageFilesForWriter(int w){
		
		return null;
	}
	public   ArrayList<String> getImageFilesForPass(int p){
		
		return null;
	}
	public  ArrayList<String> getImageFilesForDigit(int d){
		this.getCurrentSettings();
		 ArrayList<String> files=new ArrayList<String> ();
		
	 files.ensureCapacity( AppDefaults.NO_OF_PASSES*AppDefaults.NO_OF_TRAIN_WRITERS);
		
	 
	 this.setCurrentDatabaseDir();
	
	 String dir, filename,part="";
	 dir=curDir+this.getSetName()+DefaultSeperator;
	
	 
	 
	 if(MovingValidation ){
		 setMovingMinMaxW();
		 // now states in validations ....
		 
		 if (Status==VALIDATE_TRAIN||Status==TRAIN){
			 
			 if (Validation_Writer_Start<MaxW && Validation_Writer_Start>MinW){
				 
				//// first sections... before validations...  
				 for (int w = MinW; w < Validation_Writer_Start; w++) {
					 part =getPartNo(w)+DefaultSeperator;
				 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
					 
					 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
					// if (w<100)
					// logger.trace("   adding file     "+dir+part+filename);
					 files.add(dir+part+filename);
						
					}
					
				}
				 
				/// second part after the validations... 
				 for (int w = Validation_Writer_End; w < MaxW; w++) {
					 part =getPartNo(w)+DefaultSeperator;
				 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
					 
					 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
					// if (w<100)
					// logger.trace("   adding file     "+dir+part+filename);
					 files.add(dir+part+filename);
						
					}
					
				}
				 
				 
			
				 
				 
				 // now return becuase the rest is not wanted... 
				 return files;
				 
			 }// only if the vaidation is in the middle of training....
			 
			 // the run the min max 
		 }// end of if status... 
		 
		 
	 }// end if moving... 
	 else {
        setMinMaxW();
	
	 }	 
	 //////////////////in validation or  old trainign... 
	 logger.info( "  the minw "+MinW+"   and max w is "+MaxW);
	 for (int w = MinW; w < MaxW; w++) {
		 part =getPartNo(w)+DefaultSeperator;
	 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
		 
		 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
		// if (w<100)
		// logger.trace("   adding file     "+dir+part+filename);
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
			
		 files.ensureCapacity( AppDefaults.NO_OF_PASSES*AppDefaults.NO_OF_TRAIN_WRITERS);
			
		 getCurrentSettings();
		 this.setCurrentDatabaseDir();
		
		 String dir, filename,part="";
		 dir=curDir+this.getSetName()+DefaultSeperator;
		 
//		 this.setMinMaxW();
//		 
//		 for (int w = MinW; w < MaxW; w++) {
//			 part =getPartNo(w)+DefaultSeperator;
//		 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
//			 
//			 for (int d=StartD; d<AppDefaults.CATEGORY_SIZE; d++){
//			 
//			 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
//			 if (w<100)
//			      logger.trace("   adding file     "+dir+part+filename);
//			 files.add(dir+part+filename);
//			 
//			 }
//				
//			}
//			
//		}
			
		 
		 if(MovingValidation ){
			 setMovingMinMaxW();
			 // now states in validations ....
			 
			 if (Status==VALIDATE_TRAIN||Status==TRAIN){
				 
				 if (Validation_Writer_Start<MaxW){
					 
					//// first sections... before validations...  
					 for (int w = MinW; w < Validation_Writer_Start; w++) {
						 part =getPartNo(w)+DefaultSeperator;
					 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
						 for (int d=StartD; d<AppDefaults.CATEGORY_SIZE; d++){
						 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
						// if (w<100)
						// logger.trace("   adding file     "+dir+part+filename);
						 files.add(dir+part+filename);
						 }
						}
						
					}
					 
					/// second part after the validations... 
					 for (int w = Validation_Writer_End; w < MaxW; w++) {
						 part =getPartNo(w)+DefaultSeperator;
					 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
						 for (int d=StartD; d<AppDefaults.CATEGORY_SIZE; d++){
						 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
						// if (w<100)
						// logger.trace("   adding file     "+dir+part+filename);
						 files.add(dir+part+filename);
						 }
						}
						
					}
					 
					 
				
					 
					 
					 // now return becuase the rest is not wanted... 
					 return files;
					 
				 }// only if the vaidation is in the middle of training....
				 
				 // the run the min max 
			 }// end of if status... 
			 
			 
		 }// end if moving... 
		 else {
	        setMinMaxW();
		
		 }	 
		 //////////////////in validation or  old trainign... 
		 logger.info( "  the minw "+MinW+"   and max w is "+MaxW);
		 for (int w = MinW; w < MaxW; w++) {
			 part =getPartNo(w)+DefaultSeperator;
		 for (int p = 1; p < AppDefaults.NO_OF_PASSES+1; p++) {
			 for (int d=StartD; d<AppDefaults.CATEGORY_SIZE; d++){
			 filename="writer"+getWriterno(w)+"_pass"+getPassNo(p)+"_digit"+d+".bmp";
			// if (w<100)
			// logger.trace("   adding file     "+dir+part+filename);
			 files.add(dir+part+filename);
			 }
				
			}
			
		}	
			return files;
	}
	public int getDigitFromFileName(String filename) {
		  
		int index=filename.indexOf(".");
		  // the digit is before this . 
		String dig=""+filename.charAt(index-1);
		
		  return Integer.parseInt(dig);
		//return 0;
	}
	public String getSizeInfoFile(String filename) {
		 // now i need  to locate from this file name the following info
		String file=""+filename;
		// if there is a madbase change to adbase other wise leav ethe same 
		if (filename.contains( "MAHD")){
			
			file =file.replaceAll("MAHDBase", "AHDBase");
		}
		
		
		
		return file;
	}
 


	
	
}
