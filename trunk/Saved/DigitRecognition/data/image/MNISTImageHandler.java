package data.image;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;




public class MNISTImageHandler extends ImageReader{
	private static final int ImageStart=17;
	private static final int ImageSize=28*28;
	private static final int ImageW=28;
	 int  offset=0;
	
	private static final Logger logger = Logger.getLogger(MNISTImageHandler.class);
	
	
	
	
	
	public void ReadImage(String filename){
		if (logger.isDebugEnabled()) {
			logger.debug("ReadImage (String) - start"); //$NON-NLS-1$
		}

	    // Wrap the InputStream in a SeekableStream.
	    InputStream is;
	    
		try {

			logger.trace("  filename== "+filename);
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
		    	  if (dis.available()>0){
			      pixel[i]=(byte) (255-(dis.readByte()&  0xff));
		    	  }
		    	  
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
	    	
	    
	    	Pixels=new int[ ImageW][ ImageW];
	    	
	    	int r=0;int c1=0;
	    for (int i = 0; i < pixel.length; i++) {
	    //	 Pixels[r][c1]=(byte)(((byte)255) -(pixel[i]));
	    	 Pixels[r][c1]= 255- (pixel[i]&  0xff) ;
	    	c1++;
		//	System.out.print( "   "+pixel[i]);
			if ((i+1)% ImageW==0){
				
			//	logger.info();
				r++;
				c1=0;
			}
		}
		} catch (FileNotFoundException e) {
			logger.error("ReadImage(String)", e); //$NON-NLS-1$

			
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("ReadImage(String)", e);
			
			e.printStackTrace();
		}

	 
	}
	
	
	public void ReadImage(String filename,int offset){
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("ReadImage(String) - start"); //$NON-NLS-1$
		}

	    // Wrap the InputStream in a SeekableStream.
	    InputStream is;
	    
		try {

			logger.trace("  filename== "+filename);
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
		
		 
			DataBuffer dBuffer = new DataBufferByte(pixel,ImageSize);
		      WritableRaster wr = Raster.createInterleavedRaster(dBuffer,ImageW,ImageW,ImageW,1,new int[]{0},null);
		      
		      ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		      ColorModel cm = new ComponentColorModel(cs,false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		      im= new BufferedImage(cm, wr, false, null);
		      
	    
	    	 int [][] Pixels=new int[ ImageW][ ImageW];
	    	
	    	int r=0;int c1=0;
	    for (int i = 0; i < pixel.length; i++) {
	    	 Pixels[r][c1]= 255- (pixel[i]&  0xff) ;
	    	c1++;
			if ((i+1)% ImageW==0){
				
				r++;
				c1=0;
			}
		}
	    
		} catch (FileNotFoundException e) {
			logger.error("ReadImage(String)", e); //$NON-NLS-1$

			
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("ReadImage(String)", e);
			
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("ReadImage(String) - end"); //$NON-NLS-1$
		}
		
	}


	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}


	@Override
	public int getLabel() {
		
		return -1;
	}

}
