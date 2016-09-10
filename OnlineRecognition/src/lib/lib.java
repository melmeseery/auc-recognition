package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;

import data.DataSet;

import app.SystemSettings;

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
			SystemSettings.OS=SystemSettings .OS_LINUX;
			return SystemSettings.OS_LINUX;

		}
		else {
			SystemSettings.OS=SystemSettings.OS_WINDOWS;
			return SystemSettings.OS_WINDOWS;
		}

	}

	public static void getCurrentDirectory(){
		 try {
					String path = new java.io.File(".").getCanonicalPath();
					SystemSettings.CurrentRunDir=path;

					logger.info(" current direcotry is "+path);
				} catch (IOException e) {

					e.printStackTrace();
				}
	}

	public static void getCurrentSeperator(){
		//AppDefaults.CurrentOSSeperator= System.getProperty("path.separator");
//
		if (SystemSettings.OS==SystemSettings.OS_LINUX)
			SystemSettings.CurrentOSSeperator=lib.SEPERATOR_LINUX;
		else
			SystemSettings.CurrentOSSeperator=lib.SEPERATOR_WINDOWS;

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



	public static String CorrectPath(String path){

		if (SystemSettings.OS == SystemSettings.OS_WINDOWS)
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
