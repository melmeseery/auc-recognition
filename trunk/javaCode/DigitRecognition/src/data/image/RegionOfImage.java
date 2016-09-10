/**
 * 
 */
package data.image;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import data.feature.Feature;

/**
 * @author TOSHIBA
 *
 */
public abstract class RegionOfImage extends  ImageBase{
	private static transient final Logger logger = Logger
	.getLogger(RegionOfImage .class);
	protected Digit SourceImage;

	protected  Dimension size; // size of region , width and hight 
	protected  Point location;  // location of start rows, coloumn in orginal image. 
	//protected  byte[][] digitImage;
	//protected ArrayList<Feature> features;
	

	/**
	 * @param args
	 */
	
	public RegionOfImage(Digit image, Rectangle reg) {
		 this.SourceImage=image;
		 
		if (reg==null){
			size=new Dimension(SourceImage.getWidth(),SourceImage.getHeight());
			location = new Point(0,0);
			digitImage=SourceImage.getSubRegion(reg);	
			 height=reg.height;
			 width=reg.width;
		}
		else {
		 size = new Dimension( reg.width ,reg.height);
		 location=new Point(reg.x,reg.y);
		 
		 digitImage = SourceImage.getSubRegion(reg);
		 height=reg.height;
		 width=reg.width;
		 
		}
		 
		 
		 
	}
	
	public RegionOfImage(Digit image, RegionCreater r) {
		 this.SourceImage=image;
		 
			if (r==null){
				size=new Dimension(SourceImage.getWidth(),SourceImage.getHeight());
				location = new Point(0,0);
		 
			digitImage=SourceImage.getSubRegion(r);	
			 height=SourceImage.getHeight();
			 width=SourceImage.getWidth();
			}
			else {
		
			 
			 digitImage = SourceImage.getSubRegion(r);	
			 height=r.getReg().getHeight();
			 width=r.getReg().getWidth();
			 size = new Dimension( r.getReg().getSize());
			 location=new Point(r.getReg().getLocation());
			}
	}

	public void SaveImage(String filename){
		try {
			
			
		    BufferedImage bi =  SourceImage.getImage().getSubimage(location.x, location.y, size.width, size.height); // retrieve image
		    File outputfile = new File(filename+"ROI_X"+location.x+"_Y_"+location.y+"_W_"+size.width+"_H_"+size.height+".png");
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		   logger.error("  Could not write the message  ",e);
		   }
	}






	

}
