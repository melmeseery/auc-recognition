
import org.apache.log4j.Logger;

import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.BMPDescriptor;

import com.sun.media.jai.codec.SeekableStream;

/**
 * 
 */

/**
 * @author Maha
 *
 */
public class DigitImage {
	/**
	 * Logger for this class
	 */
	
	private static final Logger logger = Logger.getLogger(DigitImage.class);
	private static final int ImageStart=17;
	private static final int ImageSize=28*28;
	private static final int ImageW=28;

	final static  int THRESHOLD = 25; //255-230
	final static byte BLACK= (byte)1;
	final static byte WHITE=  (byte)0;
	final static double SidePersent=0.25;

	private static   int Digit = 0;
//	final static boolean BLACK= true;
//	final static boolean WHITE=  false;
	int top;
    int left;
    int bottom;
    int right;
    int topleft;
    int topright;
    int bottomleft;
    int bottomright;
    int cwcontour[ ]=new int [8];
    int ccwcontour[ ]=new int [8];
    int cwconlen;
    int ccwconlen;
 
    //float leftsplit[4];
    //float rightsplit[4];
    float lowerleftsplit;
    float upperleftsplit;
    float lowerrightsplit;
    float upperrightsplit;
    float upcornerblkpixels;
    float downcornerblkpixels;
    float leftcornerblkpixels;
    float rightcornerblkpixels;
    float cornerblkpixelsavg;
	float lowerleftcornersplit4white;
    float upperleftcornersplit4white;
    float lowerrightcornersplit4white;
    float upperrightcornersplit4white;
	float uppersplitblkfromcenter;
	float downsplitblkfromcenter;
	float leftsplitblkfromcenter;
	float rightsplitblkfromcenter;
	float cornersplit4whiteavg;
    float topsplit[ ]=new float[4];
    float bottomsplit[ ]=new float[4];
    boolean reversed;
   
    
    
    
    ///finished computing 
    int whitepixels;
    int blackpixels;
    int whitesurrbyblk;
   int  height,width;
   double  heightoverwidth;
    double widthoverheight;
    double leftBottonCornerWhitePixelCountPercent;
    Tangent tanpoints;
    int BoundingBoxFirstRow,BoundingBoxFirstColumn,BoundingBoxLastRow,BoundingBoxLastColumn;

    double TopWallPixel , ButtonWallPixel ,LeftWallPixel ,RigthWallPixel ;

    BufferedImage im;
    // ImageBits 
   int[][] PixelsBounded;
   byte [][] BinaryOrg;
   byte[][]BinaryBounded;

private double[] Features;
 //  boolean[][]Binary;
	/**
	 * 
	 */
public void ReadImage(String filename,int offset){
	
	
	if (logger.isDebugEnabled()) {
		logger.debug("ReadImage(String) - start"); //$NON-NLS-1$
	}

    // Wrap the InputStream in a SeekableStream.
    InputStream is;
    
	try {
		
		
		logger.trace("filename== "+filename);
		logger.trace("  the offset  "+offset);
		/**opening the file */
		File file=new File(filename);
		FileInputStream fis = new FileInputStream(file); 
		BufferedInputStream bis = new BufferedInputStream(fis); 		
		DataInputStream dis=new DataInputStream(bis);
		int skipnumber=ImageStart;
	//	int imagesize=28*28;
		skipnumber+= offset *ImageSize;
		
		//image starts 
		//leeep till offset image. 
		//image_start+28*28-1
		logger.trace(" i am skippping about   "+skipnumber);
	      dis.skip(skipnumber);
	      
	  	byte[] pixel = new byte[ImageSize ];
	      for (int i = 0; i < ImageSize; i++) {
		      pixel[i]=(byte) (255-(dis.readByte()&  0xff));
		}
	
		///
		
	      
	      
	 
		DataBuffer dBuffer = new DataBufferByte(pixel,ImageSize);
	      WritableRaster wr = Raster.createInterleavedRaster(dBuffer,ImageW,ImageW,ImageW,1,new int[]{0},null);
	      
	      ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	      ColorModel cm = new ComponentColorModel(cs,false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	      im= new BufferedImage(cm, wr, false, null);
	      
	      
	      
	      
	      
	      
	      
	  // im=ImageIO.read(file);
	  //  Digit=getDigitNo(filename);
    //	DataBufferByte buffer = (DataBufferByte) im.getRaster().getDataBuffer();
    //	logger.trace( "   h  "+im.getHeight()+"  width "+im.getWidth());
    	
    
    	 int [][] Pixels=new int[ ImageW][ ImageW];
    	
    	int r=0;int c1=0;
    for (int i = 0; i < pixel.length; i++) {
    //	 Pixels[r][c1]=(byte)(((byte)255) -(pixel[i]));
    	 Pixels[r][c1]= 255- (pixel[i]&  0xff) ;
    	c1++;
	//	System.out.print( "   "+pixel[i]);
		if ((i+1)% ImageW==0){
			
		//	System.out.println();
			r++;
			c1=0;
		}
	}
    
 
    String StringTrace="";

    BinaryOrg =new byte[Pixels.length][Pixels[0].length];
    
    for (int i = 0; i < Pixels.length; i++) {
		for (int j = 0; j < Pixels[i].length; j++) {
			
			
		
			
			if (Pixels[i][j]< THRESHOLD)
		    BinaryOrg [i][j]=(byte) 0;
		    else 
		        BinaryOrg [i][j]=(byte) 1;
			
			
//			   Binary[i][j]=(byte) 0;
//			    else 
//			        Binary[i][j]=(byte) 1;
			
				StringTrace+=  "   "+  BinaryOrg [i][j];
			
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    
    computeBoundingBox(BinaryOrg);
    
    logger.trace( "-----------------------------------------------------------------------");
	logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
	  
    
    
BinaryBounded=new byte[BoundingBoxLastRow-BoundingBoxFirstRow+1][BoundingBoxLastColumn-BoundingBoxFirstColumn+1];
PixelsBounded=new int  [BinaryBounded.length][ BinaryBounded[0].length];


    for (int i = 0; i <   BinaryBounded.length; i++) {
		for (int j = 0; j <   BinaryBounded[i].length; j++) {
			
			
		
			
			if (Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j]< THRESHOLD)
			{
				PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
		    BinaryBounded[i][j]=(byte) 0;
		    
			}
		    else{ 
		        BinaryBounded[i][j]=(byte) 1;
				PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
		    }
			
			
//			   Binary[i][j]=(byte) 0;
//			    else 
//			        Binary[i][j]=(byte) 1;
			
	
				StringTrace+=  "   "+  BinaryBounded[i][j];
			
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    logger.trace( "-----------------------------------------------------------------------");
	 
    
    StringTrace="";
    for (int i = 0; i <   BinaryBounded.length; i++) {
		for (int j = 0; j <   BinaryBounded[i].length; j++) {
			StringTrace+=  "   "+  PixelsBounded[i][j];
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    computeBoundingBox(BinaryBounded);
    logger.trace( "-----------------------------------------------------------------------");
	 
    
    logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
	 
    //		ActualString += " "+B
    if (logger.isTraceEnabled()){
    	
    	this.computeAllFeatures();
    }
    
    
    
    
    
    
    
   
    
	} catch (FileNotFoundException e) {
		logger.error("ReadImage(String)", e); //$NON-NLS-1$

		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("ReadImage(String)", e);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	if (logger.isDebugEnabled()) {
		logger.debug("ReadImage(String) - end"); //$NON-NLS-1$
	}
	
}


public void ReadImage(String filename){
	if (logger.isDebugEnabled()) {
		logger.debug("ReadImage(String) - start"); //$NON-NLS-1$
	}

    // Wrap the InputStream in a SeekableStream.
    InputStream is;
	try {
		logger.trace(filename );
	    im=ImageIO.read(new File(filename));
	    Digit=getDigitNo(filename);
    	DataBufferByte buffer = (DataBufferByte) im.getRaster().getDataBuffer();
    	logger.trace( "   h  "+im.getHeight()+"  width "+im.getWidth());
    	
    	byte[] pixel = buffer.getData();
    	 int [][] Pixels=new int[im.getHeight()][im.getWidth()];
    	
    	int r=0;int c1=0;
    for (int i = 0; i < pixel.length; i++) {
    //	 Pixels[r][c1]=(byte)(((byte)255) -(pixel[i]));
    	 Pixels[r][c1]=   255-(pixel[i]&  0xff) ;
    	c1++;
	//	System.out.print( "   "+pixel[i]);
		if ((i+1)%im.getWidth()==0){
			
		//	System.out.println();
			r++;
			c1=0;
		}
	}
    
 
    String StringTrace="";

    BinaryOrg =new byte[Pixels.length][Pixels[0].length];
    
    for (int i = 0; i < Pixels.length; i++) {
		for (int j = 0; j < Pixels[i].length; j++) {
			
			
		
			
			if (Pixels[i][j]< THRESHOLD)
		    BinaryOrg [i][j]=(byte) 0;
		    else 
		        BinaryOrg [i][j]=(byte) 1;
			
			
//			   Binary[i][j]=(byte) 0;
//			    else 
//			        Binary[i][j]=(byte) 1;
			
				StringTrace+=  "   "+  BinaryOrg [i][j];
			
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    
    computeBoundingBox(BinaryOrg);
    
    logger.trace( "-----------------------------------------------------------------------");
	logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
	  
    
    
BinaryBounded=new byte[BoundingBoxLastRow-BoundingBoxFirstRow+1][BoundingBoxLastColumn-BoundingBoxFirstColumn+1];
PixelsBounded=new int  [BinaryBounded.length][ BinaryBounded[0].length];


    for (int i = 0; i <   BinaryBounded.length; i++) {
		for (int j = 0; j <   BinaryBounded[i].length; j++) {
			
			
		
			
			if (Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j]< THRESHOLD)
			{
				PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
		    BinaryBounded[i][j]=(byte) 0;
		    
			}
		    else{ 
		        BinaryBounded[i][j]=(byte) 1;
				PixelsBounded[i][j]=Pixels[BoundingBoxFirstRow+i][BoundingBoxFirstColumn+j];
		    }
			
			
//			   Binary[i][j]=(byte) 0;
//			    else 
//			        Binary[i][j]=(byte) 1;
			
	
				StringTrace+=  "   "+  BinaryBounded[i][j];
			
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    logger.trace( "-----------------------------------------------------------------------");
	 
    
    StringTrace="";
    for (int i = 0; i <   BinaryBounded.length; i++) {
		for (int j = 0; j <   BinaryBounded[i].length; j++) {
			StringTrace+=  "   "+  PixelsBounded[i][j];
		}
		logger.trace("  "+StringTrace );
		StringTrace="";
	}
    computeBoundingBox(BinaryBounded);
    logger.trace( "-----------------------------------------------------------------------");
	 
    
    logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
	 
    //		ActualString += " "+B
    if (logger.isTraceEnabled()){
    	
    	this.computeAllFeatures();
    }
    
    
    
    
    
    
    
   
    
	} catch (FileNotFoundException e) {
		logger.error("ReadImage(String)", e); //$NON-NLS-1$

		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("ReadImage(String)", e);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	if (logger.isDebugEnabled()) {
		logger.debug("ReadImage(String) - end"); //$NON-NLS-1$
	}
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
				
					if ( Binary [i][j]==BLACK){
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
		
		blackpixels=cb;
		firstrow=true;
		//now loop on the column first to get the first column count
		for (int j = 0; j <  Binary [0].length; j++) {
			for (int i = 0; i <  Binary .length; i++) {
				//
				if ( Binary [i][j]==BLACK){
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
    public void  computeImageDim(){
    	int cw,cb;
   cw=0;
   cb=0;
   whitesurrbyblk=0;
   Point BoxFirstCorner;
   Point BoxLastCorner;
 
    	//check the binary array exist 
    	if (BinaryBounded!=null){
    		// loop on the image to calculate the dimension
    		for (int i = BoundingBoxFirstRow; i < BoundingBoxLastRow+1; i++) {
				for (int j = BoundingBoxFirstColumn; j < BoundingBoxLastColumn+1; j++) {
					// i need to count the white pixels 
	        				if ( BinaryBounded[i][j]==WHITE) // if they are a like
	        				{
	        					cw++;
	        					if (checkSuroundByBlack(i,j)){
	        						 whitesurrbyblk++;
	        					}
	        				}//pixel is white 	        			 
				}//for loop in column j
			}//for loops on rows i 
    		    		
    		whitepixels=cw;    		
    	    height=BoundingBoxLastRow-BoundingBoxFirstRow+1;
     	    width=BoundingBoxLastColumn-BoundingBoxFirstColumn+1;
    	    heightoverwidth=(double)height/(double)width;
    	    widthoverheight=(double)width/(double)height;
    	
    		
    		
    		logger.debug("  cw "+cw+"   cb   "+cb+"  white surround by black  "+ whitesurrbyblk);
    		
    		logger.debug("   now the bounding box coordinate is  r1 "+BoundingBoxFirstRow+" c1  "+BoundingBoxFirstColumn+"   r2   "+BoundingBoxLastRow+"   c2  "+BoundingBoxLastColumn);
    		logger.debug("   the height = "+height+"  width = "+width  );
    	}//check if binary is null ;
    } 
	public void computeTangents(){
		

		tanpoints=new Tangent();
		//store the known tangents 
		tanpoints.r1=0;
		tanpoints.c2=0;
		tanpoints.c3=BinaryBounded[0].length-1;
		tanpoints.r4=BinaryBounded.length-1;
		
		
		int r=0,c=0;
		
		//compute the reamining unknown tangents
		//get the top tangent
		r=BoundingBoxFirstRow;
		for ( c = BoundingBoxFirstColumn; c < BinaryBounded[r].length&& c < BoundingBoxLastColumn+1; c++) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.c1b=c;
				break;
			}
		}
		
		for ( c = BoundingBoxLastColumn; c >-1; c--) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.c1e=c;
				break;
			}
		}
		
		
		
		
		
		//get the bottom tangent
		r= BoundingBoxLastRow;
		for ( c = BoundingBoxFirstColumn; c < BoundingBoxLastColumn+1; c++) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.c4b=c;
				break;
			}
		}
		
		for ( c =BoundingBoxLastColumn; c > -1; c--) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.c4e=c;
				break;
			}
		}
		
		 
		
		//get the left tangent
		c=BoundingBoxFirstColumn;
		for ( r = BoundingBoxFirstRow; r < BinaryBounded.length&&r<BoundingBoxLastRow+1; r++) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.r2b=r;
				break;
			}
		}
		for ( r = BoundingBoxLastRow; r > -1; r--) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.r2e=r;
				break;
			}
		}
		
		
		
		//get the left tangent
		c= BoundingBoxLastColumn;
			for ( r = BoundingBoxFirstRow; r < BinaryBounded.length&&r<BoundingBoxLastRow+1; r++) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.r3b=r;
				break;
			}
		}
		for ( r =BoundingBoxLastRow; r > -1; r--) {
			if ( BinaryBounded[r][c]==BLACK){
				// this is the first tangent. 
				tanpoints.r3e=r;
				break;
			}
		}
		
		if (logger.isDebugEnabled())
		{
			
			logger.trace( tanpoints);
			
		}
		
		
	}
    private int getIntFromDouble(double d){
    	
    	 return (int) Math.round (d);
    }
	
	public void computeWallFeatures(){
    	if (BinaryBounded!=null){
    		// i need to get the % of the top wall 
    		
    		 
    		// int rfirst,rlast;
    		 
    		 int WallHeight= getIntFromDouble(height *SidePersent);
    		 logger.trace( " the wall hight "+WallHeight);
    		 
    		 int rbutton=height-WallHeight;
    		
    		 int TopWallBlackPixelCount=0;
    		 int ButtonWallBlackPixelCount=0;
    		 int LeftWallBlackPixelCount=0;
    		 int RigthWallBlackPixelCount=0;
    		    int TopWallPixelCount, ButtonWallPixelCount,LeftWallPixelCount,RigthWallPixelCount;
    		 TopWallPixelCount= ButtonWallPixelCount=LeftWallPixelCount=RigthWallPixelCount=0;
    		 
    		for (int r =0; r < (WallHeight)&&r< BinaryBounded.length ; r++) {
				for (int c = 0;  c<BinaryBounded[r].length  ; c++) {
					//count 
					TopWallPixelCount++;
					if (BinaryBounded[r][c]==BLACK){
						TopWallBlackPixelCount++;
						
					}
				}
			}
    
    		//----------------------------------------now cont the button pixels 
    		
    	
    		for (int r = BinaryBounded.length-1; r >rbutton-1&&r>-1; r--) {
				for (int c = 0; c <BinaryBounded[r].length; c++) {
					ButtonWallPixelCount++;	
					if (BinaryBounded[r][c]==BLACK){
						//ButtonWallPixelCount++;	
						ButtonWallBlackPixelCount++;						
					}
				}
			}
    		
    		
    		//-------------------now count columnet 
    		
    	int 	wallWidth=getIntFromDouble(width*SidePersent);
    	
    	logger.trace(" The width of the wall is "+wallWidth);
    	int cright=width-wallWidth;
    	for (int c = 0; c < BinaryBounded[0].length&&c<wallWidth+1; c++) {
    		
    		
 		for (int r = 0; r < BinaryBounded.length; r++) {
		LeftWallPixelCount++;
				if (BinaryBounded[r][c]==BLACK){
					LeftWallBlackPixelCount++;
					
				}
			}
		}
    		
    	
    	
    	
       	for (int c =BinaryBounded[0].length-1; c >-1&&c>cright-1; c--) {
     		for (int r = 0; r <BinaryBounded.length; r++) {
     			RigthWallPixelCount++;
    		
    				if (BinaryBounded[r][c]==BLACK){
    					RigthWallBlackPixelCount++;
    					
    				}
    			}
    		}
    	
       	if (TopWallPixelCount==0)
       	    TopWallPixel=0;
       	else
        TopWallPixel= (double)TopWallBlackPixelCount/(double)TopWallPixelCount;
       	
       	
		logger.trace("   The Top wall pixel count is "+TopWallPixelCount+" The number of black pixels is "+TopWallBlackPixelCount  +"  The % = "+TopWallPixel);
        if (ButtonWallPixelCount==0)
        	  ButtonWallPixel=0;
        else
        ButtonWallPixel=(double)ButtonWallBlackPixelCount/(double) ButtonWallPixelCount;
        logger.trace("   The Button wall pixel count is "+ButtonWallPixelCount+" The number of black pixels is "+ButtonWallBlackPixelCount  +"  The % = "+ButtonWallPixel);
        if (LeftWallPixelCount==0)
        {
        	LeftWallPixel=0;
        }else
        	LeftWallPixel =(double)LeftWallBlackPixelCount/(double) LeftWallPixelCount;
        
        
        logger.trace("   The Left wall pixel count is "+LeftWallPixelCount+" The number of black pixels is "+LeftWallBlackPixelCount  +"  The % = "+LeftWallPixel);
        
        if (RigthWallPixelCount==0)
          	RigthWallPixel=0;
        else 
        	RigthWallPixel  =(double)RigthWallBlackPixelCount/(double)RigthWallPixelCount ;
        
        
        logger.trace("   The Rigth wall pixel count is "+RigthWallPixelCount+" The number of black pixels is "+RigthWallBlackPixelCount  +"  The % = "+RigthWallPixel);

    	}
    	

    }
	private boolean checkSuroundByBlack(int i, int j) {
	// now i need to check if pixel i,j is surrounded by black pixel in all 4 direction 
		
		int suroundby=0;
		
		
		//check up. 
		for (int itemp = i; itemp > -1; itemp--) {
			if (BinaryBounded[itemp][j]==BLACK)
			{
				suroundby++;
				break;
			}
			
		}
		//check down 
		for (int itemp = i; itemp < BinaryBounded.length; itemp++) {
			if (BinaryBounded[itemp][j]==BLACK)
			{
				suroundby++;
				break;
			}
			
		}
		//check right 
		for (int jtemp = j; jtemp < BinaryBounded[i].length; jtemp++) {
			if (BinaryBounded[i][jtemp]==BLACK)
			{
				suroundby++;
				break;
			}
			
		}
		//check left
		for (int jtemp = j; jtemp >-1; jtemp--) {
			if (BinaryBounded[i][jtemp]==BLACK)
			{
				suroundby++;
				break;
			}
			
		}
		
		
		if (suroundby>3)
		{
			return true;
		}
		
		
	return false;
}
	public DigitImage() {
		// TODO Auto-generated constructor stub
	}
  
	private int getDigitNo(String filename) {
		int index=filename.indexOf(".bmp", 0);
		char number=filename.charAt(index-1);
		int dignum=Integer.parseInt(""+number );
		logger.trace("Got the digit  "+dignum);
		return dignum;
	}
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return im;
	}
	public int computeAllFeatures() {
		// computeBoundingBox();
		 computeImageDim();
		 computeTangents();
		 computeCornerWhiteCount();
		 computeWallFeatures();
		 Features=new double[9];
		 Features[0]=this.heightoverwidth;
		 //Features[1]=this.widthoverheight;
	
		 
		 Features[1]=(double)this.whitesurrbyblk/(double)this.whitepixels;
		 if (this.whitepixels==0)
			 Features[1]=0;
	
		//now compute the third features 
		  int buttonLetftHeight=tanpoints.r4-tanpoints.r2e;
		  
			 //get the feature 
			 Features[2]=(double)buttonLetftHeight/(double)height;
		
		 
		 int leftButtonWidth=tanpoints.c4b-tanpoints.c2;
		 
		 Features[3]= (double)leftButtonWidth/(double)width;
		 
		 Features[4]=leftBottonCornerWhitePixelCountPercent;
		 
		 //countTopPixels, countButtonPixels,countLeftPixels,CountRigthPixels
		 Features[5]=(double)TopWallPixel ;
		 Features[6]=(double)ButtonWallPixel ;
		 Features[7]=(double)LeftWallPixel ;
		 Features[8]=(double)RigthWallPixel  ;
		 
		 
		 
		 for (int i = 0; i < Features.length; i++) {
			 logger.trace("   feature  "+this.getFeatureName(i)+"  =  "+ Features[i]);	
		}
		 
	//	 logger.info( Features[0]+"   feature1= "+Features[1]+"   "+"  
		 //featur 3=    "+Features[2]+" feature 4 =   "+Features[3]);	 
		 
		return Features.length;
	}
	private void computeCornerWhiteCount() {

//this function count the number of white pixels till the first black pixel  in each row from row 
		//r2e  (2nd tangent on the second wall )
		// the last row.
		//first row i will count pixels on it.
		int count=0; 
		int rowcount=0;
	
		
		int r=tanpoints.r2e;
		int rlast=BoundingBoxLastRow;
		
		if (r==rlast){
			   leftBottonCornerWhitePixelCountPercent=0.0;
			return ;
		}
		
		boolean rowflag=true;
		
    for ( ; r < BinaryBounded.length; r++) {
    	 rowflag=true;
    
		for (int c = 0; c < BinaryBounded[r].length; c++) {
			if (BinaryBounded[r][c]==BLACK)
			{//if black break and continue to the next row 
				break;
			}
			else {
				if (rowflag)
				{
					rowcount++;
					rowflag=false;
				}
				//if white count it 
				count++;
				
			}
			
			
		}
    	
	}
    
    logger.trace("  the count of white pixel in the corner is "+count +"  and divide by rows "+rowcount+"   = "+(count/rowcount) );
    //now i have the number of white pixel 
    //compute the feature itself 
    leftBottonCornerWhitePixelCountPercent=((double)count/(double)rowcount)*((double)1.0/(double)width);
		
	}
	public double getFeature(int k) {
		
		return Features[k];
	}
	static public int getComputedFeatures(){
		return 9;
	}
	static public String getFeatureName(int k)
	{
		switch (k) {
		case 0:
			return "height over width";
		 
//		case 1:
//			  return     "width over heigth";
		case 1 : return "white sur. by black";
		case 2 : return "ratio r4-r2e to hieght";
		case 3 : return "ratio c4b-c2 to width";
		case 4 : return "left Bot Cor White Pixels";
		case 5 : return " Top Wall Pixel Count ";
		case 6 : return "Button Wall Pixel Count";
		case 7 : return "Left Wall Pixel Count";
		case 8 : return "Rigth Wall Pixels Count";
		default:
			return "";
		}
	}
	public String getFeatureString() {
		String data="";
		for (int i = 0; i < Features.length; i++) {
			data+=Features[i]+"  ";
		}
		
		if (Digit==5){
			data+=1+" ";
		//data+=Digit+" ";
		}
		else {
			
			
			data+= -1+" ";
			
		}
		logger.trace(data);
		return data;
	}
}
