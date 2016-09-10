package data.feature;
import gui.AppDefaults;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 */

/**
 * @author Maha
 *
 */
public class FeatureStat implements Serializable {

	private static transient final Logger logger = Logger.getLogger( FeatureStat.class);
	double means[]=new double [AppDefaults.CATEGORY_SIZE];
	double avg[]=new double [AppDefaults.CATEGORY_SIZE];
	double std[]=new double [AppDefaults.CATEGORY_SIZE];
	double max[]=new double [AppDefaults.CATEGORY_SIZE];
	double min[]=new double [AppDefaults.CATEGORY_SIZE];
	//double []=new double [App.CATEGORY_SIZE];
	public String minFilename[]=new String[AppDefaults.CATEGORY_SIZE];
	public String maxFilename[] =new String[AppDefaults.CATEGORY_SIZE];  
	
	public String classLabelValues[]=null;
	
	String FeatureName="";
	public int FeatureIndex=0;
	
	public int getFeatureIndex() {
		return FeatureIndex;
	}

	public void setFeatureIndex(int featureIndex) {
		FeatureIndex = featureIndex;
	}
	public FeatureStat(){
		classLabelValues=new String  [AppDefaults.CATEGORY_SIZE];
	
		for (int i = 0; i < min.length; i++) {
			means[i]=0;
			avg[i]=0;
			std[i]=0;
			max[i]=Double.MIN_VALUE;
			min[i]=Double.MAX_VALUE;
			minFilename[i]="";
			maxFilename[i]="";
			classLabelValues[i]=i+"";
		}
		
		
		
	}
	public FeatureStat(int size){
		 means=new double [size];
	 avg=new double  [size];
		std=new double [size];
	 max=new double  [size];
	 min=new double  [size];
		//double []=new double [App.CATEGORY_SIZE];
minFilename=new String [size];
	 maxFilename =new String [size];
		classLabelValues=new String  [size];
		
		for (int i = 0; i < min.length; i++) {
			means[i]=0;
			avg[i]=0;
			std[i]=0;
			max[i]=Double.MIN_VALUE;
			min[i]=Double.MAX_VALUE;
			minFilename[i]="";
			maxFilename[i]="";
			classLabelValues[i]=i+"";
		}
		
		
		
	}
	
	public void changeSize(int size){
		 means=new double [size];
		 avg=new double  [size];
			std=new double [size];
		 max=new double  [size];
		 min=new double  [size];
			//double []=new double [App.CATEGORY_SIZE];
	minFilename=new String [size];
		 maxFilename =new String [size];
			classLabelValues=new String  [size];
			for (int i = 0; i < min.length; i++) {
				means[i]=0;
				avg[i]=0;
				std[i]=0;
				max[i]=Double.MIN_VALUE;
				min[i]=Double.MAX_VALUE;
				minFilename[i]="";
				maxFilename[i]="";
				classLabelValues[i]=i+"";
			}
			
	}
	
	public void setClassValue(String label, int d){
		classLabelValues[d]=label;
		
	}
	
	public FeatureStat(int size,String name){
		 means=new double [size];
	 avg=new double  [size];
		std=new double [size];
	 max=new double  [size];
	 min=new double  [size];
		//double []=new double [App.CATEGORY_SIZE];
minFilename=new String [size];
	 maxFilename =new String [size];
		
		for (int i = 0; i < min.length; i++) {
			means[i]=0;
			avg[i]=0;
			std[i]=0;
			max[i]=Double.MIN_VALUE;
			min[i]=Double.MAX_VALUE;
			minFilename[i]="";
			maxFilename[i]="";
		}
		
		
		FeatureName = name;
	}
	public FeatureStat(String featureName) {
	 this();
		FeatureName = featureName;
	}
	public double[] getMeans() {
		return means;
	}
	public void setMeans(double[] means) {
		this.means = means;
	}
	public void setMeanValue(double mean,int i) {
		this.means[i] = mean;
	}
	public double[] getAvg() {
		return avg;
	}
	public void setAvg(double[] avg) {
		this.avg = avg;
	}
	public void setAvg(double avg,int i ) {
		this.avg[i] = avg;
	}
	public double[] getStd() {
		return std;
	}
	public void setStd(double[] std) {
		this.std = std;
	}
	public void setStd(double  std,int i) {
		this.std[i] = std;
	}
	public double[] getMax() {
		return max;
	}
	public void setMax(double[] max) {
		this.max = max;
	}
	public void setMax(double max,int i) {
		this.max[i] = max;
	}
	public double[] getMin() {
		return min;
	}
	public void setMin(double[] min) {
		this.min = min;
	}
	public void setMin(double min,int i) {
		this.min[i] = min;
	}
	public String getFeatureName() {
		return FeatureName;
	}
	public void setFeatureName(String featureName) {
		FeatureName = featureName;
	}
	public void writeToTxt(String filename){
		try {
			FileWriter aFile = new FileWriter(new File(filename));
			
		    //use buffering
			 BufferedWriter output = new BufferedWriter( aFile);

			
			String datastring="";
			for (int i = 0; i < means.length; i++) {
				
				
				datastring=" - digit "+i+" - ";
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
				
				datastring=" mean = "+means[i];
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
				datastring=" avg = "+avg[i];
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
				
				
				datastring=" std = "+std[i];
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
				
				
				datastring=" max = "+max[i];
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
				
				
				datastring=" min = "+min[i];
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
			    
				datastring=" ---------------------------------------   ";
				
				
				output.write(datastring);
				output.newLine();
				logger.trace(datastring);
			}
			
			
			output.close();
			aFile.close();
			
		} catch (IOException e) {
		
				logger.error(e.getMessage(), e);
			e.printStackTrace();
			
		}
	}

	public void writeToxls(String filename){
		    HSSFWorkbook wb =   writeToXlsSheet (new HSSFWorkbook());
	
		    FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(filename);
				 wb.write(fileOut);
				    fileOut.close();
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		   
	}
	public HSSFWorkbook writeToXlsSheet ( HSSFWorkbook wb ){
		try{
		   HSSFSheet sheet = wb.createSheet(this.FeatureName);
		    HSSFRow row = sheet.createRow((short)0);
		    
		    row.createCell((short)0).setCellValue( new HSSFRichTextString ( "Digit"));
		    row.createCell((short)1).setCellValue( new HSSFRichTextString ( "Avg"));
		    row.createCell((short)2).setCellValue( new HSSFRichTextString ( "STD"));
		    row.createCell((short)3).setCellValue( new HSSFRichTextString ( "Max"));
		
		    row.createCell((short)4).setCellValue( new HSSFRichTextString ( "Min"));
		    
		    row.createCell((short)5).setCellValue( new HSSFRichTextString ( "File of Max Digit"));
		    row.createCell((short)6).setCellValue( new HSSFRichTextString ( "File of Min Digit"));
		    for (int i = 0; i < avg.length; i++) {
		    	row = sheet.createRow((short)(i+1));
		    //	logger.info( "  the message is ....    "+i+"   "+avg[i]+" std "+std[i]);
		    	row.createCell((short)0).setCellValue( new HSSFRichTextString(classLabelValues[i]));
		      	row.createCell((short)1).setCellValue(avg[i]);
		      	row.createCell((short)2).setCellValue(std[i]);
		      	row.createCell((short)3).setCellValue(max[i]);
		  
		      	row.createCell((short)4).setCellValue(min[i]);
		      	
		     
		    	int ic =maxFilename[i].lastIndexOf("\\");
		    	if (ic>0)
		      	maxFilename[i]=maxFilename[i].substring( ic);
		      	
		    	row.createCell((short)5).setCellValue( new HSSFRichTextString(maxFilename[i]));
		    	
		    	
		      	ic =minFilename[i].lastIndexOf("\\");
		    	if (ic>0)
		      	minFilename[i]=minFilename[i].substring( ic);
		      	row.createCell((short)6).setCellValue(new HSSFRichTextString(minFilename[i]));
		     	//row.createCell((short)5).setCellValue();
		      	//row.createCell((short)4).setCellValue();
		    	
		    	
			}
		}catch( Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			logger.error( " **************  "+this.FeatureName);
			
			
			
		}
		
		return wb;
	}

	public void setMinFilename(String[] minFilename) {
		this.minFilename = minFilename;
	}

	public void setMaxFilename(String[] maxFilename) {
		this.maxFilename = maxFilename;
	}
	public void setMinFilename(String minFilename,int d) {
		this.minFilename[d] = minFilename;
	}

	public void setMaxFilename(String maxFilename,int d) {
		this.maxFilename[d] = maxFilename;
	}

	public void setMaxFilenameIndex(int maxFilename,int d) {
		this.maxFilename[d] = maxFilename+"";
	}
	public void setMinFilenameIndex(int minFilename,int d) {
		this.minFilename[d] = minFilename+"";
	}

//	public void addValue(double d,int category) {
//	 if ( means.length>category){
//		 
//		 means[category]+=d;
//		 avg[category]+=d;
//		 if (max[category]>d){
//			 max[category]=d;
//		 }
//		 if (min[category]<d){
//			 min[category]=d;
//		 }
//		 
//		 
//	 }
//		
//	}
	 
}
