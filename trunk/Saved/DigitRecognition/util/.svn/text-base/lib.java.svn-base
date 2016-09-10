package util;

import gui.AppDefaults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.MultiClassFeatureRecognizier;
import data.dataset.ArabicDataBaseConnector;
import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.image.RegionCreater;

public class lib {
	private static transient final Logger logger = Logger.getLogger(lib.class);
	private static final long RandomSEED = 0;
	
	public  static final String SEPERATOR_WINDOWS="\\";
	public static final String SEPERATOR_LINUX="/";	
	public static int getCurrentOs(){
		
		
		   String nameOS = "os.name";        
		   String osName =System.getProperty(nameOS);
		   
		if (osName.startsWith("Lin"))
		{
			AppDefaults.OS=AppDefaults .OS_LINUX;
			return AppDefaults.OS_LINUX;
			
		}
		else {
			AppDefaults.OS=AppDefaults.OS_WINDOWS;
			return AppDefaults.OS_WINDOWS;
		}
		
	}
	
	public static void getCurrentDirectory(){
		 try {
					String path = new java.io.File(".").getCanonicalPath();
					AppDefaults.CurrentRunDir=path;
					
					logger.info(" current direcotry is "+path);
				} catch (IOException e) {
				 
					e.printStackTrace();
				}
	}
	
	public static void getCurrentSeperator(){
		//AppDefaults.CurrentOSSeperator= System.getProperty("path.separator");
//		
		if (AppDefaults.OS==AppDefaults.OS_LINUX)
			AppDefaults.CurrentOSSeperator=lib.SEPERATOR_LINUX;
		else 
			AppDefaults.CurrentOSSeperator=lib.SEPERATOR_WINDOWS;
		
	}
	public static boolean makeDirs(String dir){
		
		File f=new File(dir);
		if (!f.exists()){
			  if  (!f.mkdirs()){
				  return false;
				  
			  }
		}
		return true;
	}
	
	public static void convertFileFormat(String filename, int From, int To){
		
		DataSet inData=new DataSet();
		inData.setFormat(From);
		inData.ReadFromFile(filename);
		
	inData.setFormat(To);
	inData.SaveToFile(filename+"_modified");
		
		
	}
	
	public static void splitDataClasses(String filename, int Format,ArrayList<Integer> c1,ArrayList<Integer> c2){
		
		
		DataSet inData=new DataSet();
		inData.setFormat(Format);
		inData.ReadFromFile(filename);
		
		DataSet outData=inData.GenerateSplitClassesDataSet(c1, c2);
	outData.setFormat(Format);
	outData.SaveToFile(filename+"Split_"+c1+"_V"+c2);
		
	}
	
	public static String CorrectPath(String path){
		
		if (	DataBaseConnector.OS == DataBaseConnector.OS_WINDOWS)
		{
			return toWindows(path);
		}
		else {
			return toLinux(path);
		}
	}
	
	public static String toWindows(String path){
		// check if is already windows 
		// get the letter path... 
		if (path.contains(SEPERATOR_WINDOWS)& path.contains(":"))
		{
			
			//D:\\AUC\\Databases\\Arabic Digits Databases\\AHDBase\\
			return path;
		}
		else {
			
			String temp=path.replaceAll( SEPERATOR_LINUX,SEPERATOR_WINDOWS);
			temp=temp.replace("/windows/","");
			int index=temp.indexOf(SEPERATOR_WINDOWS);
			String temp2=temp;
		    if (index>0){
		    	temp2="";
		    	temp2=temp.substring(0, index-1)+":";
		    	temp2+=temp.substring(index);
			///windows/D/AUC/Databases/Arabic Digits Databases/AHDBase/
			
			
			
		}	
		    
		    return temp2;
		}
		    
	}
	public static String toLinux(String path){
		
		if (path.contains(SEPERATOR_LINUX)& (!path.contains(":")))
		{
			
			//D:\\AUC\\Databases\\Arabic Digits Databases\\AHDBase\\
			return path;
		}
		else {
			if (!path.contains( SEPERATOR_WINDOWS))
			{
				return path;
				
			}
			String temp=path.replaceAll(SEPERATOR_WINDOWS+ SEPERATOR_WINDOWS, SEPERATOR_LINUX);
			temp="/windows/"+temp.replace(":","");
		
			return temp;
		    
		    }
	
		
	}
	
	
	public static ArrayList<String> mergeString(ArrayList<String> arr1,
			ArrayList<String> arr2) {
			ArrayList<String> returnArr=new  ArrayList<String>();
			
			for (int i = 0; i < arr1.size(); i++) {
				returnArr.add(arr1.get(i));
			}
			
			 // merge digits
		    boolean found=false;
			 for (int j = 0; j < arr2.size() ; j++) {
				 for (int dd = 0; dd < returnArr.size(); dd++) {
						if (returnArr.get(dd).equals( arr2.get(j)))
						{
							found=true;
						
						break;
							
						}
//						else {
//						
//						}
				}
				 if (!found){
					returnArr.add(arr2.get(j));
				 }
				 found=false;
			
				 
				 
				 
				 
			}
			 
		return returnArr;
			
			
			
//			 // merge digits
//		    boolean found=false;
//			 for (int j = 0; j < arr1.size() ; j++) {
//				 for (int dd = 0; dd < arr2.size(); dd++) {
//						if (arr1.get(j).equals( arr2.get(dd)))
//						{
//							found=true;
//						
//						break;
//							
//						}
////						else {
////						
////						}
//				}
//				 if (!found){
//					returnArr.add(arr1.get(j));
//				 }
//				 found=false;
//			
//			}
//			 
//		return returnArr;
	}
	
	
	public static ArrayList<Integer> merge(ArrayList<Integer> arr1,
			ArrayList<Integer> arr2) {
			ArrayList<Integer> returnArr=new  ArrayList<Integer>();
			
			if (arr2==null)
			{
				return arr1;
			}
			
			if (arr1==null)
			{
				return arr2;
			}
			// now copy return array from arr1 
			for (int i = 0; i < arr1.size(); i++) {
				returnArr.add(arr1.get(i));
			}
			
			 // merge digits
		    boolean found=false;
			 for (int j = 0; j < arr2.size() ; j++) {
				 for (int dd = 0; dd < returnArr.size(); dd++) {
						if (returnArr.get(dd).equals( arr2.get(j)))
						{
							found=true;
						
						break;
							
						}
//						else {
//						
//						}
				}
				 if (!found){
					returnArr.add(arr2.get(j));
				 }
				 found=false;
			
				 
				 
				 
				 
			}
			 
		return returnArr;
	}
	
	
	
	
	
	
	
	
	public static   ClassifierData  mergeClassifier (ClassifierData c1C,ClassifierData c2C ){
		 ClassifierData merged=new ClassifierData();
		 ArrayList<String> finalFeatures ;


		 
		 ClassifierData c1,c2 ;
		 if (c1C==null)
		 {
			 
	///		 logger.info( " c1 is empty now clone and return c2");
			 return (ClassifierData) c2C.clone();
		 }
		 c1=(ClassifierData) c1C.clone();
		 
		  //ClassifierData cTest = (ClassifierData) c2.clone();
		 if (c2C==null){
			 
			// logger.info( " c2 is empty now clone and return c1");
			 return (ClassifierData) c1C.clone();
		 }
		 c2=(ClassifierData) c2C.clone();
		// logger.info( "  no null now merging both types...........  ");
		 
		 
//		 logger.info("  Merging c1 "+c1);
		 //logger.info("  Merging c2 "+c2);
		 
		 
		 for (int i = 0; i < c1.regions.size(); i++) {
			
			 RegionCreater reg = c1.regions.get(i);
			 
			 for (int j = 0; j <  c2.regions.size(); j++) {
				 if (reg.equals( c2.regions.get(j))){
				 //if i found a same region then i need to merge the feature then add the 
				 // this is incremental add, ass region can be in more then one classifier
			   finalFeatures =lib.mergeString(c1.feat.get(i),c2.feat.get(j));
				 // add the region and the features to the class new
				 merged.feat.add(finalFeatures);
				 merged.regions.add(c2.regions.get(j));
				 
				 c2.regions.remove(j);
				 c2.feat.remove(j);
				 c1.regions.remove(i);
				 c1.feat.remove(i);
				i--;
				j--;
				break; 
			 }/////
			}/// after break.........
			
		}/// after the c1 loop 
		 
		 
		// logger.info(" now after merge the number of  regionsss is "+c2.regions.size());
		 if (c2.regions.size()>0){
			 
			 for (int i = 0; i < c2.regions.size(); i++) {
				 merged.feat.add(c2.feat.get(i));
				 merged.regions.add(c2.regions.get(i));
			}
			 
			 
		 }
		// logger.info(" now after merge the number of  regionsss is "+c1.regions.size());
	 if (c1.regions.size()>0){
			 
			 for (int i = 0; i < c1.regions.size(); i++) {
				 merged.feat.add(c1.feat.get(i));
				 merged.regions.add(c1.regions.get(i));
			}
			 
			 
		 }
 
	// logger.info("  Merged     "+merged);
		 
		// logger.info("  number of merged regions "+merged.regions.size());
		 
		 ArrayList<Integer> mergedDigits=lib.merge(c1.digit, c2.digit);
			//logger.info( "  merged digitsssssssssss   "+mergedDigits.size());
			merged.digit=mergedDigits;
			
			 if (c1.digitC2!=null){
				 merged.digitC2=lib.merge(c1.digitC2, c2.digitC2);
				 }
		 merged.setName( "Merged_"+c1.getName()+"_A_"+c2.getName());
		 
		 
		 //logger.info("  the number of merged digits ...  " + merged.digit.size());
		 merged.createFeatureNameList();
		 
		 return  merged;
		 
		 
	 }
	
	
	static public  ArrayList<String> shuffleFiles(
			ArrayList<String> files) {
		  Random  r1Random=new Random(RandomSEED);
		//create an emypt array to hold the shuffeld files 
		ArrayList<String>  RandomSet=new ArrayList<String>(files.size());
		RandomSet.ensureCapacity(files.size());
		
	ArrayList<Integer> indeces=new ArrayList<Integer>(files.size());
	
	
	for (int i = 0; i < files.size(); i++) {
		indeces.add(new Integer(i));
		
	}
	int randomi=(int) Math.floor(r1Random.nextDouble()*files.size());
	int index;
	for (int i = 0; i < indeces.size(); i++) {
		
		randomi=(int) Math.floor(r1Random.nextDouble()*indeces.size());
	// now move files from he input to the ouptut array . 
		index=indeces.get(randomi);
		
		RandomSet.add( files.get(index));
		indeces.remove(randomi);
		
	}
	 
		 //check that all indeces are added 
	if(indeces.size()>0)
	{
		for (int i = 0; i <indeces.size(); i++) {
			index=indeces.get(i);
			RandomSet.add( files.get(index));
		}
	}
	
	
//	if (logger.isTraceEnabled()){
//		
//		for (int i = 0; i < 100 && i<RandomSet.size(); i++) {
//			logger.trace(" -- "+RandomSet.get(i));
//		}
//	}
		
		
		
		
		
		return RandomSet;
	}


	public static boolean isInArray(int i, ArrayList<Integer> c1) {
	 for (int j = 0; j < c1.size(); j++) {
		if (i==c1.get(j))
			return true;
	}
		return false;
	}
	
	public static DataSet LoadData(ClassifierData data , int Status, int DB){
		
		return lib.LoadData(data, Status, DB, false);
	}

	public static DataSet LoadData(ClassifierData data , int Status, int DB, boolean StoreLocations){
	//	logger.info(" Inside the Load  in main ");
         // set the region to compute 
		DataSet dataset=null;
		
		if (AppDefaults.LoadDataSet){
			
			dataset=new DataSet();
			 
			dataset=dataset.loadDataSet( data.getName()+"_"+Status+"_DB_"+DB);
			if (dataset!=null){
			
					return dataset;
			}
		}
		
		//digitsForTest.add(new Integer(d2));
		//FeaturedRegion.setFeaturesToCompute(feats);
		//FeaturedRegion.loadAllFeatureArray();
		if (DB==DataBaseConnector.MNIST){
		
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		
		db.setStatus(Status);
		dataset = db.GetDataSetByDigits(data,StoreLocations);
		dataset.FixArrayAndClassLabel();
		
		}
		
		
		else if (DB==DataBaseConnector.MADBASE||DB==DataBaseConnector.ADBASE){
			
			ArabicDataSetGenerator db=new ArabicDataSetGenerator();
			db.setArabicDB(DB);
			db.setStatus(Status);
			dataset = db.GetDataSetByDigits(data,StoreLocations);
			dataset.FixArrayAndClassLabel();
		}
		
		if (AppDefaults.SaveDataSet)
		{
			dataset.setFormat(AppDefaults.DataSetFormat);
			dataset.SaveToFileAll(data.getName()+"_"+Status+"_DB_"+DB);
		
		}
		
		return dataset;
	}
	
public static  ArrayList<ArrayList<Integer>> getCompinations( Integer [] source , int size,int starti){
		
		if (size==1)
		{
			ArrayList<ArrayList<Integer>> compinations=new ArrayList<ArrayList<Integer>>();
			 
			for (int i = starti; i < source.length; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(source[i]);
				compinations.add(temp);
			
			}
			return compinations;
			
		}
//		if (size==2){
//			
//			
//			
//		}
		else { 
			size--;
			//logger.info(" The size is  "+size);
			ArrayList<ArrayList<Integer>> AllCompinations=new ArrayList<ArrayList<Integer>>(); 
			for (int i = starti; i < source.length; i++) {
		 
				  ArrayList<ArrayList<Integer>> comp = getCompinations(source,size,i+1);
				  for (int j = 0; j < comp.size(); j++) {
					  comp.get(j).add(source[i]);
					  
					  AllCompinations.add(comp.get(j));
					
				}
							
			}
				return  AllCompinations;
			
			
		}
		
//		
//		for (int i = 0; i < source.length; i++) {
//			
//			
//			
//			
//		}
		
		
	}
public static  ArrayList<ArrayList<String>> getCompinations( ArrayList<String> source , int size,int starti){
	
	if (size==1)
	{
		ArrayList<ArrayList<String>> compinations=new ArrayList<ArrayList<String>>();
		 
		for (int i = starti; i < source.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(source.get(i));
			compinations.add(temp);
		
		}
		return compinations;
		
	}
//	if (size==2){
//		
//		
//		
//	}
	else { 
		size--;
		//logger.info(" The size is  "+size);
		ArrayList<ArrayList<String>> AllCompinations=new ArrayList<ArrayList<String>>(); 
		for (int i = starti; i < source.size(); i++) {
	 
			  ArrayList<ArrayList<String>> comp = getCompinations(source,size,i+1);
			  for (int j = 0; j < comp.size(); j++) {
				  comp.get(j).add(source.get(i));
				  
				  AllCompinations.add(comp.get(j));
				
			}
						
		}
			return  AllCompinations;
		
		
	}
	
	

//	
//	for (int i = 0; i < source.length; i++) {
//		
//		
//		
//		
//	}
	
	
}

//private ArrayList<String[] > getTwoCompination ( String[] source  ) {
//	 ArrayList<String[] > compinations=new ArrayList<String[] >();
//	
//	 String[]  temp=new String[2];
//	
//	
//	for (int i = 0; i < source.length; i++) {
//		for (int j = i; j < source.length; j++) {
//			temp[0] = source[i];
//			temp[1]=source[j];
//			compinations.add(temp);
//		}
//	}
//	
//	
//	return compinations;
//	
//	
//	
//	
//}
//
//private ArrayList<Integer[]> getTwoCompination ( Integer[] source  ) {
//	 ArrayList<Integer[]> compinations=new ArrayList<Integer []>();
//	
//	 Integer[] temp=new Integer[2];
//	
//	
//	for (int i = 0; i < source.length; i++) {
//		for (int j = i; j < source.length; j++) {
//			temp[0] = source[i];
//			temp[1]=source[j];
//			compinations.add(temp);
//		}
//	}
//	
//	
//	return compinations;
//	
//	
//	
//	
//}	
public static void SaveFeatureFile(String filename  ,ArrayList<String> feats){
	
	  FileOutputStream file; 
      PrintStream out; // declare a print stream object
      try {
       // Create a new file output stream
      file = new FileOutputStream(filename);

              // Connect print stream to the output stream
             out = new PrintStream(file);

             // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
              //double dataw; 
             
             for (int i = 0; i < feats.size(); i++) {
				out.println(feats.get(i));
				out.println(i);
			} 
    
            	  
            	  
            	 
					
				 
  
              
      }
      catch (Exception e){
              System.err.println (" Error in writing to file");
      }
	
	
}
public static void Save(String filename  ,ArrayList<String> feats){
	
	  FileOutputStream file; 
    PrintStream out; // declare a print stream object
    try {
     // Create a new file output stream
    file = new FileOutputStream(filename);

            // Connect print stream to the output stream
           out = new PrintStream(file);

           // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
            //double dataw; 
       	out.println( );
           for (int i = 0; i < feats.size(); i++) {
				out.print(feats.get(i)+",");
				 
			} 
  
       	out.println( );
          	  
          	 
					
				 

            
    }
    catch (Exception e){
            System.err.println (" Error in writing to file");
    }
	
	
}
public static void SaveString(String filename , String feats){
	
	  FileOutputStream file; 
  PrintStream out; // declare a print stream object
  try {
   // Create a new file output stream
  file = new FileOutputStream(filename);

          // Connect print stream to the output stream
         out = new PrintStream(file);

         // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
          //double dataw; 
     	out.println( );
        
				out.print(feats);
	 

     	out.println( );
        	  
        	 
					
				 

          
  }
  catch (Exception e){
          System.err.println (" Error in writing to file");
  }
	
	
}

public static String ToString(int[] lre6) {
	String st="";
	st+="[";
	 for (int i = 0; i < lre6.length; i++) {
		if (i>=0){
			st+=",";
		}
		 
			st+=""+lre6[i];
	
	}
	 st+="]";
	 
	return st;
}

public static String ToString(double[] lre6) {
	String st="";
	st+="[";
	 for (int i = 0; i < lre6.length; i++) {
		if (i>=0){
			st+=",";
		}
		 
			st+=""+lre6[i];
	
	}
	 st+="]";
	 
	return st;
}
public static String ToString(Object[] lre6) {
	String st="";
	st+="[";
	 for (int i = 0; i < lre6.length; i++) {
		if (i>=0){
			st+=",";
		}
		 
			st+=""+lre6[i].toString();
	
	}
	 st+="]";
	 
	return st;
}
public static void writeFile(String st, String filename){
	
	
	
}

public static void SaveXls(String Filename, double[][] errorsArray, String rName, String[] cName, String[] featuresNames) {
	 
	 HSSFWorkbook wb = new HSSFWorkbook();
	 HSSFSheet sheet = wb.createSheet("results");
	 int rows=errorsArray.length;
	 int columns=errorsArray[0].length;
	 
	 
	 		    HSSFRow row = sheet.createRow((short)0);
	 		 
	 
	 row.createCell((short)(0)).setCellValue( new HSSFRichTextString (" Features "));
	 for (int i = 0; i < cName.length; i++) { //for classifier digit 
			
         row.createCell((short)(i+1)).setCellValue( new HSSFRichTextString (featuresNames[i]));
	 }
	 
	 row = sheet.createRow((short)1);
	 row.createCell((short)(0)).setCellValue( new HSSFRichTextString (" Classifier "));
	 for (int i = 0; i < cName.length; i++) { //for classifier digit 
		String temp= cName[i].replace("Classifier", "");
		if (temp!=null)
          row.createCell((short)(i+1)).setCellValue( new HSSFRichTextString (temp));
		else 
			 row.createCell((short)(i+1)).setCellValue( new HSSFRichTextString (cName[i]));
			
	 }
	 
	 for (int j = 0; j < errorsArray.length; j++) {
		    HSSFRow row2 = sheet.createRow((short)(j+2));
	 		   row2.createCell((short)(0)).setCellValue( new HSSFRichTextString (rName+j));
	 		   
	 		   for (int k = 0; k < errorsArray[j].length; k++) {
				row2.createCell((short)(k+1)).setCellValue(errorsArray[j][k]  );
			}
	 		   
	 		  
	 		   
	 		   
	 		   
	}
	 
	 
	
	 
		 
	 
	 
	 
	 
	  FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(Filename+".xls");
			 wb.write(fileOut);
			    fileOut.close();
			    
			    
			    
			    
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
		}
	   

	 
	 
//	    HSSFSheet sheet = wb.createSheet("digit"+i);
	//    HSSFRow row = sheet.createRow((short)0);
	    
	
	    

	//    row.createCell((short)0).setCellValue( new HSSFRichTextString (resultListVertices.get(i).TestName));

	
	
}



}
