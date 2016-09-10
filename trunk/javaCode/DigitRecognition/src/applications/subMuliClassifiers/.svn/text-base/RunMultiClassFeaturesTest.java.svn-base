package applications.subMuliClassifiers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import classifiers.MultiFeature.ImageRecognizier;
import classifiers.results.ClassifierResult;

import data.dataset.DataBaseConnector;
import data.image.FeaturedRegion;
import data.image.RegionCreater;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

public class RunMultiClassFeaturesTest  extends Observable implements
RunnableTask {
	private static transient final Logger logger = Logger.getLogger( RunMultiClassFeaturesTest.class);
	private static final String OutDir = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\stats\\";
	public static void main(String[] args) {
		  org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		 
		  RunMultiClassFeaturesTest test=new  RunMultiClassFeaturesTest();
		  DataBaseConnector.OS=DataBaseConnector.OS_WINDOWS;
			Thread th=new Thread(test);
			th.run();

	}
	
	
	public void RunTaskDetails()
	{
		ImageRecognizier  IR=new ImageRecognizier ();
	 
		FeaturedRegion.setFeaturesToCompute(null);
		
		
		
		
		
		//digit.add(new Integer (6));
//		ArrayList<String> featName=null;
//		
//		RegionCreater  reg=new  	RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//		
//		RegionCreater  reg1=new  	RegionCreater();
//		reg1.setMaxVerticalRegion(2);
//		reg1.setMaxHorizontalRegion(2);
//		reg1.setHorizonal(0);
//		reg1.setVertical(1);
//		
//		RegionCreater  reg2=new  	RegionCreater();
//		reg2.setMaxVerticalRegion(2);
//		reg2.setMaxHorizontalRegion(2);
//		reg2.setHorizonal(1);
//		reg2.setVertical(0);
//		
//		RegionCreater  reg3=new  	RegionCreater();
//		reg3.setMaxVerticalRegion(2);
//		reg3.setMaxHorizontalRegion(2);
//		reg3.setHorizonal(1);
//		reg3.setVertical(1);
////		
//		
		ArrayList<ArrayList <String>> featArrs=null;
		ArrayList<RegionCreater>  regions=null;//new ArrayList<RegionCreater>();
//		featName=fillFeauturesForRegion();
//		featArrs.add( featName);
//		featArrs.add( featName);
//		featArrs.add( featName);
//		featArrs.add( featName);
//		
//		regions.add(reg);
//		regions.add(reg1);
//		regions.add(reg2);
//		regions.add(reg3);
		
		IR.setMainOptions(ImageRecognizier.TRAIN_VALIDATE, ImageRecognizier.CLASSIFIER_LIBSVM );
		
		String str="";
		String newline = System.getProperty("line.separator");
		for (int i =0;i <10;i++){
		for (int k = i+1; k < 10; k++) {
			ArrayList<Integer> digit=new ArrayList<Integer>() ;
			digit.add(new Integer (i));
			digit.add(new Integer (k));
 
			RegionFeaturePair temp = setFeatureForRegionForDigits(i,k, featArrs,regions);
		
			//IR.setDataOptions(digit, featName, reg);
			
			IR.setDataOptions(digit, temp.feat, temp.regions);
	//		logger.info("the features size array is equal to "+featArrs.size() +"  and the region size array is = "+regions.size());
			
			ClassifierResult result = IR.TrainValidate();
		
			logger.info(  "Classifier  of "+i+" VS "+k+"  "+result.toString());
			str+= "Classifier  of "+i+" VS "+k+"  "+ result.toString();
			str+=newline;
		
		}
		}
		logger.info("@###################################################################333");
		logger.info(str);
//		IR.LoadData();
//		IR.BuildClassifier();
		
		
		
	}
 private 	 RegionFeaturePair   setFeatureForRegionForDigits( int d1, int d2, ArrayList<ArrayList<String>> feat, ArrayList<RegionCreater>  regions){
	 RegionFeaturePair temp=new RegionFeaturePair();
	 if (d1==0){
		 
		 
		  temp= addFeaturesForDigit0();
 
		 
	 }
	 if (d1==1){
		 
		 
		  temp= addFeaturesForDigit1();
			
		 
 
		 
		 
	 }
	 if (d1==2){
		 
		 
	  temp= addFeaturesForDigit2();
			
			
		 
		 
	 }
	 if (d1==3){
		 
		 
		 temp= addFeaturesForDigit3();
			
	 
		 
		 
	 }
	 if (d1==4){
		 
		 
	  temp= addFeaturesForDigit4();
			
	 
		 
		 
	 }
	 if (d1==5){
		 
		 
	  temp= addFeaturesForDigit5();
			
			
	 
		 
		 
	 }
	 if (d1==6){
		 
		  temp= addFeaturesForDigit6();
			
 
		 
		 
	 }
	 if (d1==7){
		 
		 
		  temp= addFeaturesForDigit7();
	 
		 
		 
	 }
	 if (d1==8){
		 
		 
  temp= addFeaturesForDigit8();
			
 
		 
		 
	 }
	 if (d1==9){
		 
		 
	temp= addFeaturesForDigit9();
			
			
 
		 
	 }
	 return temp;
 } 
 
 
 private  RegionFeaturePair addFeaturesForDigit0(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
		
		logger.info( " the regions sizeeeeeeeee is "+temp.regions.size());
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit1(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit2(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit3(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit4(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit5(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit6(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit7(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit8(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
 private  RegionFeaturePair addFeaturesForDigit9(){
	 
	 ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
	 ArrayList<RegionCreater>  regions=new ArrayList<RegionCreater>();
	//-------------------------------------------
	 ArrayList <String> AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
		RegionCreater  reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		 
		 
		//------------------------------------------ 
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(0);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(0);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		 AllFeatures2=new ArrayList<String>();
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");

			AllFeatures2.add("frright");
			
		 
		
	     reg=new  	RegionCreater();
		reg.setMaxVerticalRegion(2);
		reg.setMaxHorizontalRegion(2);
		reg.setHorizonal(1);
		reg.setVertical(1);
		
		
		 feat.add( AllFeatures2);
		 regions.add(reg);
		//-------------------------------------------
		
		RegionFeaturePair temp=new RegionFeaturePair();
		temp.feat=feat;
		temp.regions=regions;
	 return temp;
	 
	 
 }
 
	private ArrayList <String> fillFeauturesForRegion() {
		ArrayList <String> AllFeatures2=new ArrayList<String>();
	
		
	//	AllFeatures.add("wsb");
 

		AllFeatures2.add("frup");
		AllFeatures2.add("frdown");
		AllFeatures2.add("frleft");

		AllFeatures2.add("frright");
		
		
//		AllFeatures.add("dirMaxW");
//	 
//	////;
//		//// 0 1 2  3 4 5 6 7 8 9 
//		AllFeatures.add("PbinF4R");
//		AllFeatures.add("PbinL4R");
//		AllFeatures.add("PbinL4C");
//		AllFeatures.add("PbinF4C");
//		AllFeatures.add("PbinF4CinUpper");
		
		
//		AllFeatures.add("CountNegativeTransition");
//		AllFeatures.add("CountLargeNegativeTransition");
//	    AllFeatures.add("CountPositiveTransition");	
//		AllFeatures.add("CountZeroTransition");
		
 return AllFeatures2;
		
	}


	@Override
	public void addObserver(TaskController taskController) {
		 
		
	}

	@Override
	public TaskSettings getSettings() {
		 
		return null;
	}

	@Override
	public void setSettings(TaskSettings task) {
		 
		
	}

	@Override
	public void run() {
		RunTaskDetails();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		 
		
	}
    class RegionFeaturePair{
   	 ArrayList<ArrayList<String>> feat;
	 ArrayList<RegionCreater>  regions;
    }
}
