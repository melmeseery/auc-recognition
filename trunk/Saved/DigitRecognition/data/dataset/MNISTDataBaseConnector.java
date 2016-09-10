/**
 * 
 */
package data.dataset;



import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import org.apache.log4j.Logger;


/**
 * @author TOSHIBA
 *
 */
public class MNISTDataBaseConnector  extends DataBaseConnector{
	
	private static final Logger logger = Logger.getLogger( MNISTDataBaseConnector.class);
	private static final int LabelStart =9;
	//private static final int ImageStart = 17;

	
	 public String getFilename(){
		 this.setCurrentDatabaseDir();
		 
	
			 
			 logger.trace("  in mnist database ..... ");
			 String returnFile=curDir;//+"\\";
		
			 if (Status==TRAIN||Status==VALIDATE_TEST||Status==VALIDATE_TRAIN)
			 {
				 returnFile+="train-images.idx3-ubyte";
				 
			 }
			 else if (Status==TEST){
				 
				 returnFile+="t10k-images.idx3-ubyte";
				 
			 }
			 else {
				 returnFile="";
			 }
			 
			 return returnFile;
			 
			 
	
	 }
	 private String getLabelFullPath(){
		 this.setCurrentDatabaseDir();
//		 logger.info("  in mnist database ..... ");
		 String returnFile=curDir;//+"\\";
	
		 if (Status==TRAIN||Status==VALIDATE_TEST||Status==VALIDATE_TRAIN)
		 {
			 returnFile+="train-labels.idx1-ubyte";
			 
		 }
		 else if (Status==TEST){
			 
			 returnFile+="t10k-labels.idx1-ubyte";
			 
		 }
		 else {
			 returnFile="";
		 }
		 
		 return returnFile;
		 
		 
		 
	 }
	

	 
	 public  ArrayList<Point>  getImagesOffsetLabelsDigits(ArrayList<Integer> digits){
		 
		 ArrayList<Point>  imagesLabels = getImagesOffsetLabels(getLabelFullPath());
		 
		 ArrayList<Point>  imagesLabelsOfDigit =new   ArrayList<Point> ();
		 int startLabel=0;
		 int endLabel=imagesLabels.size();
		 if (Status==VALIDATE_TRAIN){
			 
			 endLabel=(int)(imagesLabels.size()*ValidationPercent);
			 
		 }
		 if (Status==VALIDATE_TEST){
			startLabel=(int)(imagesLabels.size()*ValidationPercent)+1;
		 }
		 
		 for (int i = startLabel; i < endLabel; i++) {
			 ////////// i get all labels and all offfsets. 
			 
			 
			 for (int j = 0; j < digits.size(); j++) {
				 if (imagesLabels.get(i).x==digits.get(j)){
					 imagesLabelsOfDigit.add( imagesLabels.get(i)); 
					 break;
				 }
				
			}
			
			
		}
		 
		 return imagesLabelsOfDigit;
		 
	 }
	 
	 public  ArrayList<Point>  getImagesOffsetLabelsDigits(){
		 
		 ArrayList<Point>  imagesLabels = getImagesOffsetLabels(getLabelFullPath());
	 
		 int validationSplit=(int)(imagesLabels.size()*ValidationPercent);
		 ArrayList<Point>  imagesL;
		 
		 if (Status==VALIDATE_TRAIN){
			 imagesL=new ArrayList<Point>();
			 for (int i =0; i < validationSplit; i++) {
				 imagesL.add(imagesLabels.get(i));
			}
			 
		return imagesL;	  
			 
		 }
		 if (Status==VALIDATE_TEST){
			 imagesL=new ArrayList<Point>();
			 for (int i = validationSplit; i <imagesLabels.size(); i++) {
				 imagesL.add(imagesLabels.get(i));
			}
			 return imagesL;
		 }
		 return imagesLabels ;
		 
	 }
	 
	//get a list of offset for the digit ==x 
	public Point  getImagesOfsetLabel(int offset){
		
		return getImagesOfsetLabel(getLabelFullPath(), offset);
	}
	 
	 public Point getImagesOfsetLabel(String labelfile,int offset){
		 Point imageData=null;
		 int offset_count=0;

		  int numberofread=0;
		    
			try {

				logger.trace("  filename== "+labelfile);
				//logger.trace("  the offset  "+offset);
				/**opening the file label  */
				File file=new File(labelfile);
				FileInputStream fis = new FileInputStream(file); 
				BufferedInputStream bis = new BufferedInputStream(fis); 		
				DataInputStream dis=new DataInputStream(bis);
				
				
				int skipnumber=LabelStart+offset-1;
	
				logger.trace(" I am skippping about   "+skipnumber);
			    dis.skip(skipnumber);   
			    ///disimage.skip(ImageStart );
			    
			    
			    numberofread=skipnumber;
			    offset_count=0;
			    logger.trace("  number of read is "+offset);
			  if  (dis.available()>0)//> numberofread
			    {
			    	
			    int label=dis.readByte();
		 
			    imageData=new Point(label,offset);
			    
			 
			    }
			   
			  
			   
			}catch (FileNotFoundException e) {
				logger.error("ReadImage(String)", e); //$NON-NLS-1$

				
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("ReadImage(String)", e);
				
				e.printStackTrace();
			}
			
			
			
		 return imageData;
		 
		 
	 }
	 
	/// get randmom list of digit.. 
	
	 
	//read label files \
	 
	 public ArrayList<Point> getImagesOffsetLabels(String labelfile){
		 ArrayList<Point> images=new ArrayList<Point>();
		 Point imageData;
		 int offset_count=0;
			if (logger.isDebugEnabled()) {
				logger.debug("ReadImage(String) - start"); //$NON-NLS-1$
			}

		    // Wrap the InputStream in a SeekableStream.
		 //   InputStream is;
		    //int offset=0;
		    int numberofread=0;
		    
			try {

				logger.trace("  filename== "+labelfile);
				//logger.trace("  the offset  "+offset);
				/**opening the file label  */
				File file=new File(labelfile);
				FileInputStream fis = new FileInputStream(file); 
				BufferedInputStream bis = new BufferedInputStream(fis); 		
				DataInputStream dis=new DataInputStream(bis);
				
				
				int skipnumber=LabelStart;
	
				logger.trace(" i am skippping about   "+skipnumber);
			    dis.skip(skipnumber);   
			    ///disimage.skip(ImageStart );
			    
			    
			    numberofread=skipnumber;
			    offset_count=0;
			    logger.trace("  number of read is "+numberofread);
			   while (dis.available()>0)//> numberofread
			    {
			    	
			    int label=dis.readByte();
			    offset_count++;
			    numberofread++;
			    imageData=new Point(label,offset_count);
			    images.add(imageData);
			 
			    }
			   
			  
			   
			}catch (FileNotFoundException e) {
				logger.error("ReadImage(String)", e); //$NON-NLS-1$

				
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("ReadImage(String)", e);
				
				e.printStackTrace();
			}
			
			 return images;
	 }
	@Override
	public String getFullPath(String filename) {
	 
		return  getFilename();
	}
	
}
