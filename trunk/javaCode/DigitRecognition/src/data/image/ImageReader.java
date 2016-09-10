package data.image;


import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;

import data.dataset.DataBaseConnector;

public abstract class ImageReader {
	private static final Logger logger = Logger.getLogger(ImageReader.class);
	private static final Logger logExample=Logger.getLogger("Example");
	private static final Logger logExample2=Logger.getLogger("FullExample");
    BufferedImage im;
	protected  int realHeight;
	protected  int realWidth;
    // ImageBits 
   int[][] PixelsBounded;
   byte [][] BinaryOrg;
   byte[][]BinaryBounded;
   protected int [][] Pixels; 
   int Threshold =Digit.THRESHOLD ;
   
   String Filename="";
   
   
   int BoundingBoxFirstRow,BoundingBoxFirstColumn,BoundingBoxLastRow,BoundingBoxLastColumn;
//	public abstract  void ReadBoundedBinary(String filename);
	public abstract void ReadImage(String filename);
	/**
	 * @return the realHeight
	 */
	public int getRealHeight() {
		return realHeight;
	}
	/**
	 * @return the realWidth
	 */
	public int getRealWidth() {
		return realWidth;
	}
	public BufferedImage getIm() {
		return im;
	}
	public byte[][] getBinaryOrg() {
		return BinaryOrg;
	}
	public byte[][] getBinaryBounded() {
		return BinaryBounded;
	}
	public int[][] getPixelsBounded() {
		return PixelsBounded;
	}
	public void setThreshold(int threshold) {
		Threshold = threshold;
	}
	public int getThreshold() {
		return Threshold;
	}
	public void computeBoundingBox( byte[][] Binary ){
		
		int cw,cb;
		cb=0;
		   BoundingBoxFirstRow=BoundingBoxFirstColumn=BoundingBoxLastRow=BoundingBoxLastColumn=0;
		   
		   boolean firstrow=true;
	   	//check the binary array exist 
	   	if (Binary !=null){
	   		
	   		// loop on the image to calculate the dimension
			for (int i = 0; i < Binary .length; i++) {
				for (int j = 0; j < Binary [i].length; j++) {
					
						if ( Binary [i][j]==Digit.BLACK){
						// the first pixel that a black pixel appear in is 
						if ( BoundingBoxFirstRow==0&&firstrow)
						{
							BoundingBoxFirstRow=i;
							firstrow=false;
					 
						}
							// the last pixel that a black pixel appear in is the 
							//rlast
							BoundingBoxLastRow=i;
							cb++;
						}
				}
				
				}
			
			//blackpixels=cb;
			firstrow=true;
			//now loop on the column first to get the first column count
			for (int j = 0; j <  Binary [0].length; j++) {
				for (int i = 0; i <  Binary .length; i++) {
					//
					if ( Binary [i][j]==Digit.BLACK){
						// the first pixel that a black pixel appear in is 
						if ( BoundingBoxFirstColumn==0&&firstrow)
						{
							BoundingBoxFirstColumn=j;
							firstrow=false;
						}
						
						// the last pixel that a black pixel appear in is the 
						//rlast
						BoundingBoxLastColumn=j;
					}
				}
			}
			
	   		
	   	}
		
	}
	
	public void ImageToBinary(){
	    String StringTrace="";
   //logger.info("  the pixel image is   "+Pixels);
	    BinaryOrg =new byte[Pixels.length][Pixels[0].length];
	    
	    for (int i = 0; i < Pixels.length; i++) {
			for (int j = 0; j < Pixels[i].length; j++) {
				
				
			
				
				if (Pixels[i][j]<  Threshold)
			    BinaryOrg [i][j]=(byte) 0;
			    else 
			        BinaryOrg [i][j]=(byte) 1;
				
				
//				   Binary[i][j]=(byte) 0;
//				    else 
//				        Binary[i][j]=(byte) 1;
				
					StringTrace+=  "|"+  BinaryOrg [i][j];
				
			}
			//logger.debug("|"+StringTrace );
		 
			StringTrace="";
		}
	}
public void BinaryImageToBoundedBinary(){
		
		String StringTrace="";
		 computeBoundingBox(BinaryOrg);
		    
		  //  logger.trace( "-----------------------------------------------------------------------");
		 //  logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
			  
		    
		    
		BinaryBounded=new byte[BoundingBoxLastRow-BoundingBoxFirstRow+1][BoundingBoxLastColumn-BoundingBoxFirstColumn+1];
		PixelsBounded=new int  [BinaryBounded.length][ BinaryBounded[0].length];


		    for (int i = 0; i <   BinaryBounded.length; i++) {
		 
				for (int j = 0; j <   BinaryBounded[i].length; j++) {
					
					
				
					
					if (Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j]< Threshold)
					{
						PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
				    BinaryBounded[i][j]=(byte) 0;
				    
					}
				    else{ 
				        BinaryBounded[i][j]=(byte) 1;
						PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
				    }
						
			
						StringTrace+=  "|"+  BinaryBounded[i][j];
					
				}
				if (i==0)
				{
					String stD="";
					for (int j = 0; j <   BinaryBounded[0].length; j++) {
						if (j<10)
						   stD+=  "|"+  j;
						else 
							stD+= "|"+  (j-10);
					}
					
					logger.trace("  c   |"+stD);
				}
				
				if (i>=10)
					logger.trace("  "+i+"  |"+StringTrace );
				else
			  	logger.trace("  "+i+"   |"+StringTrace );
				
				logExample.trace("  "+StringTrace+" |" );
				String stt="_";
				for (int j = 0; j < StringTrace.length(); j++) {
					stt+="_";
				}
				
			//	logExample.trace(stt);
				logExample2.trace("  "+StringTrace );
				StringTrace="";
			}
		    logger.trace( "-----------------------------------------------------------------------");
			logExample.trace("-----------------------------------------------------------------------" );
			logExample2.trace("-----------------------------------------------------------------------" );
			
		    StringTrace="";
		    String st;
		    
		    for (int i = 0; i <   BinaryBounded.length; i++) {
				for (int j = 0; j <   BinaryBounded[i].length; j++) {
					st=PixelsBounded[i][j]+"";
					if(st.length()==1){
						st+="  ";
					}else 
						if(st.length()==2){
							st+=" ";
						}
						else 
							if(st.length()==3){
								st+="";
							}
					 
					StringTrace+="|"+  st;
				}
				//logger.trace("  "+StringTrace );
				logExample.trace("  "+StringTrace+" | ");
				//logExample.trace("-----------------------------------------------------------------------------");
				String stt="_";
				for (int j = 0; j < StringTrace.length(); j++) {
					stt+="_";
				}
				
				logExample.trace(stt);
				logExample2.trace("  "+StringTrace );
				
				StringTrace="";
			}
		    
		    logger.trace( "-----------------------------------------------------------------------");
			logExample.trace("-----------------------------------------------------------------------" );
			logExample2.trace("-----------------------------------------------------------------------" );
			
	}
public void ReadBoundedBinary(String filename){
	ReadImage(filename);
	ImageToBinary();
	BinaryImageToBoundedBinary();
}

public void ReadBoundedBinary(){
	//logger.info(" in read iamge and file name is "+Filename);
	ReadImage( Filename);
//	logger.info("  now the image is read  i will convert to binar");
	//
	ImageToBinary();
//	logger.info(" i am converting to bounded image... ");
	BinaryImageToBoundedBinary();
}


public static ImageReader CreateImageReader(String filename,int databaseType) {
	ImageReader im;
	if (databaseType==DataBaseConnector.MNIST) {
		
		im=new MNISTImageHandler();
		
	}
	else {
		
		im=new ArabicImageHandler();
	}
	
	
	im.Filename=filename;
	return im;
}

public static ImageReader CreateImageReader(int databaseType) {
	ImageReader im;
	if (databaseType==DataBaseConnector.MNIST) {
		
		im=new MNISTImageHandler();
		
	}
	else {
		
		im=new ArabicImageHandler();
	}
	
	
	return im;
}
public static ImageReader CreateImageReader(int databaseType, int offset) {
	ImageReader im;
	if (databaseType==DataBaseConnector.MNIST) {
		
		im=new MNISTImageHandler();
		
		( (MNISTImageHandler) im).offset=offset;
	}
	else {
		
		im=new ArabicImageHandler();
	}
	
	
	return im;
}
public void setFile(String string) {
	
	 Filename=string;
}
abstract public int getOffset();

abstract public int getLabel();

	
}
