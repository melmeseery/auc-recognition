package data.image;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import data.dataset.ArabicDataBaseConnector;


public class ArabicImageHandler extends ImageReader{
		int Label=0;

	private static final Logger logger = Logger.getLogger(ArabicImageHandler.class);
	public void ReadImage(String filename){
	//logger.info( "  in arabic read handleer...................");
		  InputStream is;
			try {
				//logger.info( filename );
			    im=ImageIO.read(new File(filename));
			    //Digit=getDigitNo(filename);
			    setLabel(filename);
			   readSizeInfo(filename); 
		    	DataBufferByte buffer = (DataBufferByte) im.getRaster().getDataBuffer();
		    //	logger.info( "   h  "+im.getHeight()+"  width "+im.getWidth());
		    	
		    	byte[] pixel = buffer.getData();
		    	 Pixels=new int[im.getHeight()][im.getWidth()];
		    	
		    	int r=0;int c1=0;
		    for (int i = 0; i < pixel.length; i++) {
		    //	 Pixels[r][c1]=(byte)(((byte)255) -(pixel[i]));
		    	 Pixels[r][c1]=   255-(pixel[i]&  0xff) ;
		    	c1++;
			//	System.out.print( "   "+pixel[i]);
				if ((i+1)%im.getWidth()==0){
					
				//	logger.info();
					r++;
					c1=0;
				}
			}
			}catch (FileNotFoundException e) {
				logger.error("ReadImage(String) file not found ", e); //$NON-NLS-1$

				
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("ReadImage(String)    in io execption ", e);
				
				e.printStackTrace();
			}

		    
		 
		 
	}
	private void readSizeInfo(String filename) {
		 ArabicDataBaseConnector  db=new ArabicDataBaseConnector();
		 String filename2=db.getSizeInfoFile(filename);
		   BufferedImage im2;
		try {
			//logger.info("  The file of the ad base must bee  "+filename2);
			im2 = ImageIO.read(new File(filename2));
			realHeight=im2.getHeight();
			realWidth=im2.getWidth();
			//logger.info( "   real size is  h  "+im2.getHeight()+"  width "+im2.getWidth());
		} catch (IOException e) {
		 
			e.printStackTrace();
		}
		    
	    
	}
	private void setLabel(String filename) {
		Label=0;
		int extention = filename.indexOf(".");
		int lastt=filename.lastIndexOf( "t");
		
		
		String digitInwords=filename.substring(lastt+1, extention);
		
		
		Label=Integer.parseInt(digitInwords);
		
		
	}
	@Override
	public int getOffset() {
	 
		return 0;
	}
	
	
	@Override
	public int getLabel() {
	 
		return Label;
	}
	
}
