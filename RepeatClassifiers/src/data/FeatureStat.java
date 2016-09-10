package data;
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

import General.AppDataSettings;


/**
 * 
 */

/**
 * @author Maha
 *
 */
public class FeatureStat implements Serializable ,Cloneable{

	private static transient final Logger logger = Logger.getLogger( FeatureStat.class);
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	double means[]=new double [AppDataSettings.CATEGORY_SIZE];
	double avg[]=new double [AppDataSettings.CATEGORY_SIZE];
	double std[]=new double [AppDataSettings.CATEGORY_SIZE];
	double max[]=new double [AppDataSettings.CATEGORY_SIZE];
	double min[]=new double [AppDataSettings.CATEGORY_SIZE];
	//double []=new double [App.CATEGORY_SIZE];
	String minFilename[]=new String[AppDataSettings.CATEGORY_SIZE];
	String maxFilename[] =new String[AppDataSettings.CATEGORY_SIZE];  
	
	
	
	String FeatureName="";
	int FeatureIndex=0;
	
	public int getFeatureIndex() {
		return FeatureIndex;
	}

	public void setFeatureIndex(int featureIndex) {
		FeatureIndex = featureIndex;
	}

	public FeatureStat(){
		
	
		for (int i = 0; i < min.length; i++) {
			means[i]=0;
			avg[i]=0;
			std[i]=0;
			max[i]=Double.MIN_VALUE;
			min[i]=Double.MAX_VALUE;
			minFilename[i]="";
			maxFilename[i]="";
		}
		
		
		
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
		   HSSFSheet sheet = wb.createSheet("Feat_"+this.FeatureName+"");
		    HSSFRow row = sheet.createRow((short)0);
		    
		    row.createCell((short)0).setCellValue( new HSSFRichTextString ( "Digit"));
		    row.createCell((short)1).setCellValue( new HSSFRichTextString ( "Avg"));
		    row.createCell((short)2).setCellValue( new HSSFRichTextString ( "STD"));
		    row.createCell((short)3).setCellValue( new HSSFRichTextString ( "Max"));
		
		    row.createCell((short)4).setCellValue( new HSSFRichTextString ( "Min"));
		    
		    row.createCell((short)5).setCellValue( new HSSFRichTextString ( "File of Max Digit"));
		    row.createCell((short)6).setCellValue( new HSSFRichTextString ( "File of Min Digit"));
		    for (int i = 0; i < means.length; i++) {
		    	row = sheet.createRow((short)(i+1));
		    	
		    	row.createCell((short)0).setCellValue( i);
		      	row.createCell((short)1).setCellValue(avg[i]);
		      	row.createCell((short)2).setCellValue(std[i]);
		      	row.createCell((short)3).setCellValue(max[i]);
		  
		      	row.createCell((short)4).setCellValue(min[i]);
		      	
		      	
		    	int ic =maxFilename[i].lastIndexOf("\\");
		      	maxFilename[i]=maxFilename[i].substring( ic);
		    	row.createCell((short)5).setCellValue( new HSSFRichTextString(maxFilename[i]));
		    	
		    	
		      	ic =minFilename[i].lastIndexOf("\\");
		      	minFilename[i]=minFilename[i].substring( ic);
		      	row.createCell((short)6).setCellValue(new HSSFRichTextString(minFilename[i]));
		     	//row.createCell((short)5).setCellValue();
		      	//row.createCell((short)4).setCellValue();
		    	
		    	
			}
		}catch( Exception ex){
			//ex.printStackTrace();
			System.out.println( " **************88  "+this.FeatureName);
			
			
			
		}
		
		return wb;
	}
}
