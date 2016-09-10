/**
 * 
 */
package data.image;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import org.apache.log4j.Logger;

import classifiers.SingleClassifiers;

/**
 * @author TOSHIBA
 *
 */
public class RegionCreater implements Cloneable {
	private static transient final Logger logger = Logger.getLogger(RegionCreater .class);
	public static int  REGION_SIZE_BASED=0;
	public static int  REGION_PIXEL_BASED=1;
	
    int MaxHorizontalRegion;
    int MaxVerticalRegion;
    int Horizonal;
    int Vertical;
    int RegionType=REGION_SIZE_BASED;
String Name=null;
    Rectangle reg;
    
    
	public byte[][] creatRegion(byte[][] image) {
		
		if (image!=null){
//	wrong values 	int wi=image.length; // change to the correct values at 28 jan
//		int hi=image[0].length; // 
	 
		
		int wi=image[0].length; // change to the correct values at 28 jan
		int hi=image.length; // 
         if (RegionType==REGION_PIXEL_BASED)
         {
        	 return null;
         }
         
//         if (MaxVerticalRegion==0)
//         {
//        	 MaxVerticalRegion=1;
//        	 logger.error(" ERROOOOOOOOOOOOO RRRRRRRRrin region creation");
//         }
//         if (MaxHorizontalRegion==0)
//         {
//        	 MaxHorizontalRegion=1;
//         }
		// compute the size of the new region  ( size of image / number of regions  )
		int NewW=  (int)Math.floor((double)wi/(double)MaxVerticalRegion); // ||||
              
		int NewH=  (int)Math.floor((double)hi/(double)MaxHorizontalRegion); //==
		
		byte[][] newRegion=new byte[NewH][NewW];
		
		
		// now i will compute the start and end of each 
		// ||
		// first index is 0 and (NewW-1)
		
		int wStart=0;
		int wEnd=NewW-1;
		int hStart=0;
		int hEnd=NewH-1;  //7/2 =0 3,4 7
		
		wStart+=NewW*Vertical; 
		wEnd+=NewW*Vertical; 
		
		hStart+=NewH*Horizonal; 
		hEnd+=NewH*Horizonal; 
		
		
		reg=new Rectangle(wStart,hStart, NewW,NewH);
		int nX,nY;
		logger.trace("The image has"+hi+"  rowss  "+"  and "+wi+"  columns  this is wi/hhi");
		logger.trace("The image has"+image.length+"  rowss  "+"  and "+image[0].length+"  columns");
		logger.trace( "  I am converting a new regiotn with new coordiantes  column "+wStart+"  to  "+wEnd+" with width of "+NewW);
		logger.trace("  and start from row of "+hStart+"  to  "+hEnd+"  with hight of "+NewH);
		for (int i = 0; i < newRegion.length; i++) {
			for (int j = 0; j < newRegion[i].length; j++) {
				nX=j+wStart;
			   nY=i+hStart;
			   //logger.info("  the nX "+nX+" nY "+nY);
			   if (hEnd<image.length&&wEnd<image[i].length&&nX<image[i].length&&nY<image.length)
			   {
				  // logger.trace(" moving "+image[nX][nY]);
				   newRegion[i][j]=image[nY][nX];
			   }
			 //  else 
				   
				//   newRegion[i][j]=0;
				
				
			}
		}
		return newRegion;
		}
		return null;
	}




	public int getMaxHorizontalRegion() {
		return MaxHorizontalRegion;
	}




	public void setMaxHorizontalRegion(int maxHorizontalRegion) {
		MaxHorizontalRegion = maxHorizontalRegion;
	}




	public int getMaxVerticalRegion() {
		return MaxVerticalRegion;
	}




	public void setMaxVerticalRegion(int maxVerticalRegion) {
		MaxVerticalRegion = maxVerticalRegion;
	}




	public int getHorizonal() {
		return Horizonal;
	}




	public void setHorizonal(int horizonal) {
		Horizonal = horizonal;
	}




	public int getVertical() {
		return Vertical;
	}




	public void setVertical(int vertical) {
		Vertical = vertical;
	}




	public int getRegionType() {
		return RegionType;
	}




	public void setRegionType(int regionType) {
		RegionType = regionType;
	}




	public Rectangle getReg() {
		return reg;
	}




	@Override
	public boolean equals(Object obj) {
		 if (obj instanceof RegionCreater) {
			RegionCreater com = (RegionCreater) obj;
			
			if(com.Horizonal==Horizonal&&com.Vertical==Vertical){
				if (com.MaxHorizontalRegion==MaxHorizontalRegion){
					if(com.MaxVerticalRegion==MaxVerticalRegion){
						
						return true;
					}
				}
				
			}
			
		}
		return false;
	}




	public String getRegionName() {
		 if (Name==null){
			 
			 Name="R_HV("+Horizonal+"_"+Vertical+")In"+MaxHorizontalRegion+"X"+MaxVerticalRegion+"";
			 
		 }
		return  Name;
	}




	@Override
	public Object clone() {
	 
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			 
			e.printStackTrace();
			return this;
		}
	}

	public static  RegionCreater getRegion(int maxh, int maxv, int h, int v) {
		RegionCreater reg = new RegionCreater();
		reg.setMaxHorizontalRegion(maxh);
		reg.setMaxVerticalRegion(maxv);
		reg.setHorizonal(h);
		reg.setVertical(v);
		return reg;
	}

	 
 

}
