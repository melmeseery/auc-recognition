package data.image;

import gui.AppDefaults;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import data.feature.Feature;

public class Digit extends  ImageBase {
	private static transient final Logger logger = Logger
			.getLogger(Digit.class);
	private static final Logger logExample=Logger.getLogger("Example");
	private static final Logger logExample2=Logger.getLogger("FullExample");
	public final static int THRESHOLD = 25; // 255-230

	ImageReader im;
	//byte[][] digitImage;

	int Label;
	int offset;
	
	public void setLabel(int x) {
		
		Label = x;
	}

	public int getLabel() {
		return Label;
	}
public void SaveImage(String filename){
	try {
	    BufferedImage bi =  im.getIm(); // retrieve image
	    File outputfile = new File(filename+".png");
	    ImageIO.write(bi, "png", outputfile);
	} catch (IOException e) {
	   logger.error("could not write the message",e);
	   }
}
	public void ReadImage(ImageReader im) {
		//logger.info("  the image readi is "+im);
		
		im.ReadBoundedBinary();
		this.im=im;
		digitImage = im.getBinaryBounded();
		// logger.info("-------------------------------------Reading the image of orginally ----------------------------");
		// displayImage(im.getBinaryOrg());

		height = digitImage.length;
		width = digitImage[0].length;
		realHeight=im.getRealHeight();
		realWidth=im.getRealWidth();
		offset=im.getOffset();
		Label=im.getLabel();
		computed = false;
	}

 

//	static public String getFeatureString() {
//		String str = " { ";
//		for (int i = 0; i < AllFeatures.size(); i++) {
//			if (i != 0) {
//				str += ",";
//			}
//			str += "\"" + AllFeatures.get(i) + "\"";
//
//		}
//		str += "  } ";
//		return str;
//	}





	public static void loadAllFeatureArray() {
		AllFeatures = new ArrayList<String>();
//		AllFeatures.add("wsb");
//		AllFeatures.add("cx");
//		AllFeatures.add("MCR"); //
//		AllFeatures.add("cy");// remove now (had lower ranke )
//		AllFeatures.add("lhg");
//		AllFeatures.add("lvg");
//		AllFeatures.add("lhgi"); // remove now (had lower ranke )
//		AllFeatures.add("lvgi");// remove now (had lower ranke )
//		AllFeatures.add("Count1VG");
//		AllFeatures.add("AvgVGapLength");
////		Count1VG
////		AvgVGapLength
//		AllFeatures.add("hOw");// remove now (had lower ranke )
//	
//		AllFeatures.add("pb");// remove now (had lower ranke )
//
//		// AllFeatures.add("srby1"); //surrond by 1
//		AllFeatures.add("srby2"); // surrond by 2
//		AllFeatures.add("srby3"); // surrond by 3
//		// AllFeatures.add("srby4"); //surrond by 4
//
//		AllFeatures.add("frup");
//		AllFeatures.add("frdown");
//		AllFeatures.add("frleft");
//
//		AllFeatures.add("frright");
//		
//		
//		AllFeatures.add("dirMaxW");
//		AllFeatures.add("fromDownLeft");
//		
//
//		AllFeatures.add("MaxHBlackLength");
//		AllFeatures.add("AvgHBlackLength");
//		AllFeatures.add("MaxHBlackLengthLocation");
//		AllFeatures.add("MaxNumberOfHBlackBlocks");
//		AllFeatures.add("MaxNumberOfHBlackBlocksLoc");
//
//		AllFeatures.add("MaxVBlackLength");
//		AllFeatures.add("MaxVBlackLengthLocation");
//		AllFeatures.add("MaxNumberOfVBlackBlocks");
//		AllFeatures.add("MaxNumberOfVBlackBlocksLoc");
//		AllFeatures.add("AvgVerticalBlockLengthInRight");
//		AllFeatures.add("wsbInLower");
//		AllFeatures.add("wsbInUpper");
//
//		AllFeatures.add("PBinLeftVsRight");
//		AllFeatures.add("PBinUpVsDown");
//
//		AllFeatures.add("PbCountD");
//		AllFeatures.add("PbCountU");
//		AllFeatures.add("PbCountL");
//		AllFeatures.add("PbCountR");
//		AllFeatures.add("PbCountLOverCountInRight");
//		
//		
//		AllFeatures.add("BlackWide");
//		AllFeatures.add("AverageWideUp");
//		
//		AllFeatures.add("CountLowWide");
//		AllFeatures.add( "CountBigWide");
//		
//		
//		AllFeatures.add("SrB3FromRight");
//		AllFeatures.add("SrB3FromLeft");
//		AllFeatures.add("FromRightUp");
//		
//		AllFeatures.add("FromLeftDown");
//		AllFeatures.add("FromLeftUp");
//	//;
//		//// 0 1 2  3 4 5 6 7 8 9 
//		AllFeatures.add("PbinF4R");
//		AllFeatures.add("PbinL4R");
//		AllFeatures.add("PbinL4C");
//		AllFeatures.add("PbinF4C");
//		AllFeatures.add("PbinF4CinUpper");
//		
//		
//		AllFeatures.add("CountNegativeTransition");
//		AllFeatures.add("CountLargeNegativeTransition");
//	    AllFeatures.add("CountPositiveTransition");	
//		AllFeatures.add("CountZeroTransition");
//		
//		AllFeatures.add("SudenChangeFRight");
//		AllFeatures.add("SudenChangeFRightLocation");
//		AllFeatures.add("SudenChangeFLeft");
//		AllFeatures.add("SudenChangeFLeftLocation");
//		//average number of gaps (horizonatal or vertical... )
//		//AllFeatures.add("");
//
//		AllFeatures.add("AverageLastInlower");
//		AllFeatures.add("AverageLastinUpper");
//		AllFeatures.add("BorLocationLengthInLastCol");
//	
//	   AllFeatures.add("BorLocationDownEnd");
//	   AllFeatures.add("BorLocationLastColumn");
//	   AllFeatures.add("BorLocationDownLength");
//	   
//	   AllFeatures.add("BorderLocationUpLength");
//	   AllFeatures.add("BorderLocationFColLength");
//	   AllFeatures.add("BorderLocationUpEnd");
//	   AllFeatures.add("BorderLocationFColEnd");
//	   AllFeatures.add("BlackWideVertical");
//	   AllFeatures.add("CountBigWideVertical");
//	   AllFeatures.add("CountLowWideVertical");
//	   AllFeatures.add("AverageWideLeftVertical");
//	   AllFeatures.add("AverageWideRightVertical");
//	 
//	  
//	   
//	   AllFeatures.add("FromDownAfterBorDown");
//	   AllFeatures.add("FromLeftBeforeBorLeft");
//	   AllFeatures.add("FromLeftAfterBorLeft");
	   //HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,wd,im,cbe,wcbe 
		AllFeatures.add("HW");
		AllFeatures.add("vol2");
		AllFeatures.add("Vol(1)");
		AllFeatures.add("Vol(2)");
		AllFeatures.add("w2r");
		AllFeatures.add("wu");
		AllFeatures.add("w4lft");
		AllFeatures.add("w4");
		AllFeatures.add("wrb");
		AllFeatures.add("wd");
		AllFeatures.add("im");
		AllFeatures.add("cbe");
		AllFeatures.add("wcbe");
		
		//,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		AllFeatures.add("wce");
		AllFeatures.add("wcb");
		AllFeatures.add("wre");
		

		AllFeatures.add("sre");
		AllFeatures.add("smallwd");
		AllFeatures.add("sr");
;

		AllFeatures.add("sle3");
		AllFeatures.add("sle");
		AllFeatures.add("snv");
		AllFeatures.add("szero");
		
		AllFeatures.add("mxlre6");
		AllFeatures.add("lxre6");
		AllFeatures.add("lxrb");
		AllFeatures.add("mxb");
		AllFeatures.add("mxce");
		AllFeatures.add("lxce");
		
		
		AllFeatures.add("blft");
		AllFeatures.add("bright");
		AllFeatures.add("btop");
		AllFeatures.add("bbot");
		
		AllFeatures.add("u1");
		AllFeatures.add("mce69");
		
		
		 AllFeatures.add("wsbLeft"); 
	
		 AllFeatures.add("dlrightleft"); 
		 AllFeatures.add("pbTop"); 
		 AllFeatures.add("w2left"); 
		 AllFeatures.add("dmwf"); 
		 AllFeatures.add("dmwl"); 
		 AllFeatures.add("dmwfl"); 
		 AllFeatures.add("dmwll"); 
		 AllFeatures.add("nHgab");
		AllFeatures.add("pbRight");
		AllFeatures.add("pbLeft");
		
		
		AllFeatures.add("wwlhl");
		AllFeatures.add("wwlhr");
		AllFeatures.add("mtwnr");
		AllFeatures.add("ltwnr");
		AllFeatures.add("wwuhr");
		AllFeatures.add("wwuhl");
		
		
		
		 AllFeatures.add("bplxrb");
		 AllFeatures.add("w4Alxrb");
		 AllFeatures.add("w2rAlxrb");
		 AllFeatures.add("w4lftlxrb");
		 AllFeatures.add("wCupLeft");
		 AllFeatures.add("wwulxrb");
		 AllFeatures.add("wwr");
		 AllFeatures.add("mbe");
		 AllFeatures.add("lbe");
		 AllFeatures.add("mxb2");
		 AllFeatures.add("lxrb2");
		 AllFeatures.add("wwlhllxrb");
		 AllFeatures.add("wwlhrlxrb");
		 AllFeatures.add("maxBlock");
		 AllFeatures.add("maxBlockLocation");
		 AllFeatures.add("maxBlocklxrb");
		 AllFeatures.add("maxBlockLocationlxrb");
		 AllFeatures.add("BWBWBCount");
		 AllFeatures.add("pbArea");
		 AllFeatures.add("mnb");
		 AllFeatures.add("lnrb");
		 AllFeatures.add("turn4");
	 
		 AllFeatures.add("wwuhrlxrb");
		 AllFeatures.add("lxrbNormalized");
		 AllFeatures.add("minBlock");
		 AllFeatures.add("maxOminBlock");
		 AllFeatures.add("rowsFive");
		 AllFeatures.add("maxBlockUpperQuarter");
		 AllFeatures.add("maxBlockUpperHalf");
		
		 
		 AllFeatures.add("nHgabLower");
		 AllFeatures.add("nVgab");
		 AllFeatures.add("imNorm");
		 AllFeatures.add("mbeUp");
		 AllFeatures.add("lbeUp");
		 AllFeatures.add("lbeNormalized");
		 AllFeatures.add("turn5left");
		 AllFeatures.add("turn5right");
		
		 
		 AllFeatures.add("avgBlockSize");
		 AllFeatures.add("bplxre6");
		 AllFeatures.add("w4Blxre6");
		 
	   /// average  
	 //  AllFeatures.add("AverageVerticalLenRight");
		
		//SudenChangeFLeftLocation
		// count of  zero transitions 
//		sudden change from right (down )	
//		
//		
//		wsb	Sudden change from left 
//		zero transition 	
//			
//		sudden change from right up 	

		// percent of pb in first four rows 
		//percent of pb in upper first four columns
		// percent of pb in last four rows. 
		
		// number of transition / 
		
		// private double MaxHorizentalBlackBlockLength;
		// private int MaxHorizentalBlackBlockLengthRowLocation;
		// private int MaxNumberOfHorizontalBlackBlockCount;
		// private double MaxNumberOfHorizontalBlackBlockCountRowLocation;
		// private double MaxVerticalBlackBlockLength;
		// private double MaxVerticalBlackBlockLengthColLocation;
		// private double MaxVerticalblocksCount;
		// private int MaxVerticalblocksCountColLocation;

		// AllFeatures.add("srby4");

	}
//	public int addingArabicFeatures() {
//		computeAllArabicFeatures();
//		Feature feat;
//		// Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
//		// //////////////////////////////////
//		feat = new Feature();
//		feat.setName(" height over width ");
//		feat.setSmallName("HW");
//		feat.setValue(heightoverwidth);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// feat = new Feature();
//		// feat.setName("vol2");
//		// feat.setSmallName("vol2");
//		// feat.setValue(vol2);
//		// features.add(feat);
//		// // //////////////////////////////////
//		// feat = new Feature();
//		// feat.setName("Vol(1) ");
//		// feat.setSmallName("Vol(1)");
//		// feat.setValue(vol1);
//		// features.add(feat);
//		// // //////////////////////////////////
//		// feat = new Feature();
//		// feat.setName("Vol(2) ");
//		// feat.setSmallName("Vol(2)");
//		// feat.setValue(Vol_2);
//		// features.add(feat);
//		// //////////////////////////////////
//		feat = new Feature();
//		feat.setName("w2r");
//		feat.setSmallName("w2r");
//		feat.setValue(w2r);
//		features.add(feat);
//
//		// //////////////////////////////////
//		feat = new Feature();
//		feat.setName("wu");
//		feat.setSmallName("wu");
//		feat.setValue(wu);
//		features.add(feat);
//		// //////////////////////////////////
//		feat = new Feature();
//		feat.setName("w4lft");
//		feat.setSmallName("w4lft");
//		feat.setValue(w4lft);
//		features.add(feat);
//		// //////////////////////////////////
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wrb");
//		feat.setSmallName("wrb");
//		feat.setValue(wrb);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("mxb");
//		feat.setSmallName("mxb");
//		feat.setValue(mxb);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wd");
//		feat.setSmallName("wd");
//		feat.setValue(wd);
//		features.add(feat);
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("u1");
//		feat.setSmallName("u1");
//		feat.setValue(u1);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("smallwd");
//		feat.setSmallName("smallwd");
//		feat.setValue(smallwd);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("sre");
//		feat.setSmallName("sre ");
//		feat.setValue(sre);
//		features.add(feat);
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("mce69");
//		feat.setSmallName("mce69");
//		feat.setValue(mce69);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("sr");
//		feat.setSmallName("sr");
//		feat.setValue(sr);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("im");
//		feat.setSmallName("im");
//		feat.setValue(im2);
//		features.add(feat);
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wce");
//		feat.setSmallName("wce");
//		feat.setValue(wce);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wcb");
//		feat.setSmallName("wcb");
//		feat.setValue(wcb);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wre");
//		feat.setSmallName("wre");
//		feat.setValue(wre);
//		features.add(feat);
//
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("btop");
//		feat.setSmallName("btop");
//		feat.setValue(PbinF4C);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("bbot");
//		feat.setSmallName("bbot");
//		feat.setValue(PbinL4C);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("blft");
//		feat.setSmallName("blft");
//		feat.setValue(PbinF4R);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("bright");
//		feat.setSmallName("bright");
//		feat.setValue(PbinL4R);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("sle3");
//		feat.setSmallName("sle3");
//		feat.setValue(sle3);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("snv");
//		feat.setSmallName("snv");
//		feat.setValue(snv);
//		features.add(feat);
//		// //////////////////////////////////
//		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("cbe");
//		feat.setSmallName("cbe");
//		feat.setValue(cbe);
//		features.add(feat);
//
//		// //wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("wcbe");
//		feat.setSmallName("wcbe");
//		feat.setValue(wcbe);
//		features.add(feat);
//
//		// ///wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
//		feat = new Feature();
//		feat.setName("mxlre6");
//		feat.setSmallName("mxlre6");
//		feat.setValue(mxlre6);
//		features.add(feat);
//
//		// //////////////////////////////////////////////
//		feat = new Feature();
//		feat.setName("lxre6");
//		feat.setSmallName("lxre6");
//		feat.setValue(lxre6);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("lxrb");
//		feat.setSmallName("lxrb");
//		feat.setValue(lxrb);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("mxce");
//		feat.setSmallName("mxce");
//		feat.setValue(mxce);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("lxce");
//		feat.setSmallName("lxce");
//		feat.setValue(lxce);
//		features.add(feat);
//
//		return 0;
//	}

	
	public int addingMyFeatures(){
		Feature feat;

		// /////////////////////////////////
		if (addGeneral)
    	addingGeneralFeatures();
		if (addTransition)
		addingTransitionFeatures();
		if (addPercent)
		addingPercentInRows();
		if (addGabs)
		addingGabFeatures();
		if (addSround)
		addingSurrondFeatures();
		if (addWide)
		addingWideFeatures();
		if (addWhite)
		addingWhiteTillBlack();
		if (addBlock)
		addingBlockFeatures();
		if(addSudden)
		addingSuddenFeatures();
		if(addBorder)
		addingBorderFeatures();
	
		
		
		
		
		return 0;
	}
//
//	public int addingTransitionFeatures() {
//		Feature feat;
//		 computeNegativeTransition();
//		 feat=new Feature();
//		 feat.setName("Count number of Negative Transition");
//		 feat.setSmallName("CountNegativeTransition");
//		 feat.setValue(CountNegativeTransition);								
//		 features.add(feat);
//		 ///-------------------------------------------------------------
////		 CountLargeNegativeTransition
//		 
//		 computeNegativeTransition();
//		 feat=new Feature();
//		 feat.setName("Count Large Negative Transition");
//		 feat.setSmallName("CountLargeNegativeTransition");
//		 feat.setValue(CountLargeNegativeTransition);								
//		 features.add(feat);
//		 
//		 feat=new Feature();
//		 feat.setName("Count Positive Transition");
//		 feat.setSmallName("CountPositiveTransition");
//		 feat.setValue(CountPositiveTransition );								
//		 features.add(feat);
//	 
//		 feat=new Feature();
//		 feat.setName("Count Zero Transition");
//		 feat.setSmallName("CountZeroTransition");
//		 feat.setValue(CountZeroTransition);								
//		 features.add(feat);
//		 
//	
//		return 0;
//	}
//	public int addingSuddenFeatures(){
//		Feature feat;
//	//////------------------------------------------------------------	
//		 computeSuddenChange();
//		 feat=new Feature();
//		 feat.setName("Sudden Change From Right (large length between col of r1 and col or r2)");
//		 feat.setSmallName("SudenChangeFRight");
//		 feat.setValue(SudenChangeFRight);								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("SudenChangeFRightLocation");
//		 feat.setSmallName("SudenChangeFRightLocation");
//		 feat.setValue(SudenChangeFRightLocation);								
//		 features.add(feat);
//		 
//		 feat=new Feature();
//		 feat.setName("SudenChangeFLeft");
//		 feat.setSmallName("SudenChangeFLeft");
//		 feat.setValue(SudenChangeFLeft);								
//		 features.add(feat);
//		 
//		 feat=new Feature();
//		 feat.setName("SudenChangeFLeftLocation");
//		 feat.setSmallName("SudenChangeFLeftLocation");
//		 feat.setValue(SudenChangeFLeftLocation);								
//		 features.add(feat);
//		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	
//		return 0;
//	}
//
//	public int addingSurrondFeatures() {
//		Feature feat;
//		CountSurrondsNumber();
//
//		feat = new Feature();
//		feat.setName("White pixels that are Surrond by 3 black pixel");
//		feat.setSmallName("srby3");
//		feat.setValue(countSurr3);
//		features.add(feat);
//		
//		
//		feat = new Feature();
//		feat.setName("White pixels that are Surrond by 2 black pixel");
//		feat.setSmallName("srby2");
//		feat.setValue(countSurr2);
//		features.add(feat);
//
//	
//		computeSurrondRight();
//		 feat=new Feature();
//		 feat.setName("Number of white pixels that are Srround by three black pixel from the right till first black pixel. ");
//		 feat.setSmallName("SrB3FromRight");
//																		
//		 feat.setValue(SrB3FromRight);
//																			
//		 features.add(feat);
//		 
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Number of white pixels that are Srround by three black pixel from the left till first black pixel.");
//		 feat.setSmallName("SrB3FromLeft");
//																		
//		 feat.setValue(SrB3FromLeft);
//																			
//		 features.add(feat);
//		// feat=new Feature();
//		// feat.setName("Surrond by 1 pixel");
//		// feat.setSmallName("srby1");
//		// feat.setValue(countSurr1);
//		// features.add(feat);
//		// feat=new Feature();
//		// feat.setName("Surrond by 4 pixel");
//		// feat.setSmallName("srby4");
//		// feat.setValue(countSurr4);
//		// features.add(feat);
//
//		// //////////////////////////////////////////////////////
//
//
//		return 0;
//	}
//
//	public int addingGabFeatures() {
//		// //////////////////////////////////
//		Feature feat;
//		computeLhg();
//
//		feat = new Feature();
//		feat.setName("The length of the largest Horizontal Gap (A gab is white space between black pixels [- -])");
//		feat.setSmallName("lhg");
//
//		feat.setValue(lhg);
//
//		features.add(feat);
//
//		// ///////////////////////////
//		// //////////////////////////////////
//		computeLvg();
//
//		feat = new Feature();
//		feat.setName("The length of the Largest vertical Gap");
//		feat.setSmallName("lvg");
//
//		feat.setValue(lvg);
//
//		features.add(feat);
//		
//		
//		
//		feat = new Feature();
//		feat.setName("The location of the Largest Horizontal Gap ");
//		feat.setSmallName("lhgi");
//		// computeLhg();
//		feat.setValue(lhgi);
//
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("The location of the Largest vertical Gap ");
//		feat.setSmallName("lvgi");
//
//		feat.setValue(lvgi);
//
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Count number of Vertical Gaps");
//		feat.setSmallName("Count1VG");
//
//		feat.setValue(Count1VG);
//
//		features.add(feat);
//		
//		
//		feat = new Feature();
//		feat.setName(" Average length of vertical gaps");
//		feat.setSmallName("AvgVGapLength");
//
//		feat.setValue(AvgVGapLength);
//
//		features.add(feat);
//		
//		return 0;
//	}
//
//	public int addingBlockFeatures() {
//		Feature feat;
//
//	// //////////////////////////////////////
//
//		
//
//		
//		// ////////////////////////////////////////////////////////////////////////////////////////
//		computeLargestHorizentalBlackBlock();
//
//		feat = new Feature();
//		feat.setName(" Max Horizontal Black Block (continous black pixel in a direction) Length ");
//
//		feat.setSmallName("MaxHBlackLength");
//		feat.setValue(MaxHorizentalBlackBlockLength);
//		features.add(feat);
//		
//		
//		feat = new Feature();
//		feat.setName(" Average Horizental Black Block Length ");
//
//		feat.setSmallName("AvgHBlackLength");
//		feat.setValue(AvgHorizentalBlackBlockLength);
//		features.add(feat);
//		
//		// //////////////////////
//		feat = new Feature();
//		feat.setName(" Max Horizental Black Block Length Row Location ");
//
//		feat.setSmallName("MaxHBlackLengthLocation");
//		feat.setValue(MaxHorizentalBlackBlockLengthRowLocation);
//		features.add(feat);
//		// ///////////////////////////////////
//
//		feat = new Feature();
//		feat.setName(" Max count Of Horizontal Black Blocks in a Row ");
//
//		feat.setSmallName("MaxNumberOfHBlackBlocks");
//		feat.setValue(MaxNumberOfHorizontalBlackBlockCount);
//		features.add(feat);
//
//		// //////////////////////////////////////
//
//		feat = new Feature();
//		feat
//				.setName("The Row location of the  Max count Of Horizontal Black Block in a row  ");
//
//		feat.setSmallName("MaxNumberOfHBlackBlocksLoc");
//		feat.setValue(MaxNumberOfHorizontalBlackBlockCountRowLocation);
//		features.add(feat);
//
//		// ////////////////////////////////////////////
//
//		//							
//		// ////////////////////////////////////////////
//		computeLargestVerticalBlackBlock();
//		feat = new Feature();
//		feat.setName("  Max Vertical Black Block Length ");
//
//		feat.setSmallName("MaxVBlackLength");
//		feat.setValue(MaxVerticalBlackBlockLength);
//		features.add(feat);
//
//		// ////////////////////////////////////////////
//		feat = new Feature();
//		feat.setName(" Max Vertical Black Block Length Col Location ");
//
//		feat.setSmallName("MaxVBlackLengthLocation");
//		feat.setValue(MaxVerticalBlackBlockLengthColLocation);
//		features.add(feat);
//
//		// ////////////////////////////////////////////
//
//		feat = new Feature();
//		feat.setName(" Max Number Of Vertical BlackBlocks  ");
//
//		feat.setSmallName("MaxNumberOfVBlackBlocks");
//
//		feat.setValue(MaxVerticalblocksCount);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName(" Max Vertical blocks Count Col Location ");
//
//		feat.setSmallName("MaxNumberOfVBlackBlocksLoc");
//		feat.setValue(MaxVerticalblocksCountColLocation);
//		features.add(feat);
//
//		
//		//AvgVerticalBlockLengthInRight
//		feat = new Feature();
//		feat.setName("AvgVerticalBlockLengthInRight");
//
//		feat.setSmallName("AvgVerticalBlockLengthInRight");
//		feat.setValue(AvgVerticalBlockLengthInRight);
//		features.add(feat);
//		
//		// //////////////////////////////////////////////////////////////
//		return 0;
//	}
//
//	public int addingWhiteTillBlack() {
//		Feature feat;
//	
//		countWhiteBlockTillBlack();
//
//		feat = new Feature();
//		feat.setName("Count of White pixels from upper border till the first black pixel ");
//		feat.setSmallName("frup");
//		feat.setValue(fromUp);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Count of White pixels from lower border (down) till the first black pixel ");
//		feat.setSmallName("frdown");
//		feat.setValue(fromDown);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Count of White pixels from left border till the first black pixel");
//		feat.setSmallName("frleft");
//		feat.setValue(fromLeft);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Count of White pixels from right border till the first black pixel");
//		feat.setSmallName("frright");
//		feat.setValue(fromRight);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Direction of Maximum count of white pixels (up,down,left..)");
//		feat.setSmallName("dirMaxW");
//		feat.setValue(LocationOfMaxWhite);
//		features.add(feat);
//
//		feat = new Feature();
//		feat.setName("Count of White pixels from lower border (down) till the first black pixel in the Left half of the digit");
//		feat.setSmallName("fromDownLeft");
//		feat.setValue(fromDownLeft);
//		features.add(feat);
//		
//		 //---------------------------------------------------
//		 computeWhiteSides();
//		 feat=new Feature();
//		 feat.setName("FromRightUp");
//		 feat.setSmallName("FromRightUp");
//																		
//		 feat.setValue(FromRightUp);
//																			
//		 features.add(feat);	
//		 
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("FromLeftDown");
//		 feat.setSmallName("FromLeftDown");
//																		
//		 feat.setValue(FromLeftDown);
//																			
//		 features.add(feat);	
//		 
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("FromLeftUp");
//		 feat.setSmallName("FromLeftUp");
//																		
//		 feat.setValue(FromLeftUp);
//																			
//		 features.add(feat);	
//		
//		return 0;
//	}
//
//	public int addingWideFeatures() {
//		Feature feat;
//		computeWideFeatures();
//		 feat=new Feature();
//		 feat.setName("Average width of the digit");
//		 feat.setSmallName("BlackWide");
//																		
//		 feat.setValue(BlackWide);
//																			
//		 features.add(feat);
//		 //---------------------------------------------------
//		 feat=new Feature();
//		 feat.setName("Average Width of the digit in the upper half ");
//		 feat.setSmallName("AverageWideUp");
//		 feat.setValue(AverageWideUp );								
//		 features.add(feat);
//			 
//			 
//		 //--------------------------------------------------------------------------------------------
//		 feat=new Feature();
//		 feat.setName("Count number of row that has smaller than 1/4 width");
//		 feat.setSmallName("CountLowWide");
//		 feat.setValue(CountLowWide );								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Count number of rows that has width larger than 1/2 width ");
//		 feat.setSmallName("CountBigWide");
//		 feat.setValue(CountBigWide );								
//		 features.add(feat);
//		 computeVerticalLength();
////		   AllFeatures.add("BlackWideVertical");
////		   AllFeatures.add("CountBigWideVertical");
////		   AllFeatures.add("CountLowWideVertical");
////		   AllFeatures.add("AverageWideLeftVertical");
//	
////		 //--------------------------------------------------------------------------------------------
//		 
//
//		 feat=new Feature();
//		 feat.setName("BlackWideVertical");
//		 feat.setSmallName("BlackWideVertical");
//																		
//		 feat.setValue(BlackWideVertical);
//																			
//		 features.add(feat);
//		 //---------------------------------------------------
//		 
//		
//		 
//		 feat=new Feature();
//		 feat.setName("CountBigWideVertical");
//		 feat.setSmallName("CountBigWideVertical");
//		 feat.setValue(CountBigWide );								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("CountLowWideVertical");
//		 feat.setSmallName("CountLowWideVertical");
//		 feat.setValue(CountLowWide );								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("AverageWideLeftVertical");
//		 feat.setSmallName("AverageWideLeftVertical");
//		 feat.setValue(AverageWideLeftVertical);								
//		 features.add(feat);
//		 
//		 feat=new Feature();
//		 feat.setName("AverageWideRightVertical");
//		 feat.setSmallName("AverageWideRightVertical");
//		 feat.setValue( AverageWideRightVertical);								
//		 features.add(feat);
//
////		 feat=new Feature();
////		 feat.setName("BlackWideVertical");
////		 feat.setSmallName("BlackWideVertical");
////																		
////		 feat.setValue(BlackWideVertical);
////																			
////		 features.add(feat);
////		 //---------------------------------------------------
////		 
////		
////		 
////		 feat=new Feature();
////		 feat.setName("CountBigWideVertical");
////		 feat.setSmallName("CountBigWideVertical");
////		 feat.setValue(CountBigWide );								
////		 features.add(feat);
////		 
////		 
////		 feat=new Feature();
////		 feat.setName("CountLowWideVertical");
////		 feat.setSmallName("CountLowWideVertical");
////		 feat.setValue(CountLowWide );								
////		 features.add(feat);
////		 
////		 
////		 feat=new Feature();
////		 feat.setName("AverageWideLeftVertical");
////		 feat.setSmallName("AverageWideLeftVertical");
////		 feat.setValue(AverageWideLeftVertical);								
////		 features.add(feat);
////		 
////		 feat=new Feature();
////		 feat.setName("AverageWideRightVertical");
////		 feat.setSmallName("AverageWideRightVertical");
////		 feat.setValue( AverageWideRightVertical);								
////		 features.add(feat);
//		return 0;
//	}
//
//	public int addingPercentInRows() {
//		Feature feat;
//		// //////////////////////////////////////
//		computePercentBlackInDirection();
//
//		feat = new Feature();
//		feat.setName(" Percent of Black in Left half / Percent of black in Right half");
//		feat.setSmallName("PBinLeftVsRight");
//
//		feat.setValue(PBinLeftVsRight);
//
//		features.add(feat);
//		// ------------------------------------------------------
//		feat = new Feature();
//		feat.setName("Percent of Black in upper half / Percent of black in lower half");
//		feat.setSmallName("PBinUpVsDown");
//
//		feat.setValue(PBinUpVsDown);
//
//		features.add(feat);
//		// ------------------------------------------------------
//		feat = new Feature();
//		feat.setSmallName("PbCountD");
//
//		feat.setName("Percent of black in lower half");
//
//		feat.setValue(PbCountD);
//
//		features.add(feat);
//		// ------------------------------------------------------
//		feat = new Feature();
//		feat.setName("Percent of black in upper half");
//		feat.setSmallName("PbCountU");
//
//		feat.setValue(PbCountU);
//
//		features.add(feat);
//		// ------------------------------------------------------
//		feat = new Feature();
//		feat.setName("Percent of black in left half");
//		feat.setSmallName("PbCountL");
//
//		feat.setValue(PbCountL);
//
//		features.add(feat);
//		// ------------------------------------------------------
//		feat = new Feature();
//		feat.setName("Percent of black in right half");
//		feat.setSmallName("PbCountR");
//
//		feat.setValue(PbCountR);
//
//		features.add(feat);
//		
//		
//		feat = new Feature();
//		feat.setName("Percent of black in the right over the total number of black pixels");
//		feat.setSmallName("PbCountLOverCountInRight");
//
//		feat.setValue(PbCountLOverCountInRight);
//
//		features.add(feat);
//
//		// ///////////////////////////////////////////////////////////////
//		//								
//		
//	
//		 
//		 
//		 
//		 
//		 
//		 
//		
//		 
//			
//		 
//		 //--------------------------------------------------------------
//		 computePercentInRows();
//		 
//		 feat=new Feature();
//		 feat.setName("Percent of black pixel in the first four rows");
//		 feat.setSmallName("PbinF4R");
//		 feat.setValue(PbinF4R);								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Percent of black pixel in the last four rows ");
//		 feat.setSmallName("PbinL4R");
//		 feat.setValue(PbinL4R);								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Percent of black pixel in the last four columns ");
//		 feat.setSmallName("PbinL4C");
//		 feat.setValue(PbinL4C);								
//		 features.add(feat);
//		 
//		 //--------------------------------------------------------------------------------------------
//		 //see previous 
//		 feat=new Feature();
//		 feat.setName("Percent of black pixel in the first four columns");
//		 feat.setSmallName("PbinF4C");
//		 feat.setValue(  PbinF4C);								
//		 features.add(feat);	 
//		 
//		 feat=new Feature();
//		 feat.setName("Percent of black pixel in the first four columns in the upper half");
//		 feat.setSmallName("PbinF4CinUpper");
//		 feat.setValue(PbinF4CinUpper);								
//		 features.add(feat);
//		return 0;
//	}
//	public int addingGeneralFeatures() {
//		Feature feat;
//		
//		 computeHW();
//			feat = new Feature();
//			feat.setName("Height over width");
//			feat.setSmallName("hOw");
//			
//			feat.setValue(heightoverwidth);
//
//			features.add(feat);
//			computeWsb();   
//			
//			// //////////////////////////////////
//			feat = new Feature();
//			feat.setName("white pixels surrond by black from four directions");
//			feat.setSmallName("wsb");
//			feat.setValue(wsb);
//			features.add(feat);
//			// //////////////////////////////////
//
//
//			// ///////////////////////////
//
//			// ///////////////////////////
//			computeMcr();
//			// //////////////////////////////////
//			feat = new Feature();
//			feat.setName(" Center of gravity (centroid) in x direction");
//			feat.setSmallName("cx");
//
//			feat.setValue(cx);
//
//			features.add(feat);
//
//			// //////////////////////////////////
//
//			// //////////////////////////////////
//			feat = new Feature();
//			feat.setName("Mean of the radius from each point to the center of gravity (centroid)");
//			feat.setSmallName("MCR");
//			feat.setValue(mcr);
//			features.add(feat);
//			
//			
//			feat = new Feature();
//			
//			feat.setName(" Center of gravity (centroid) in x direction ");
//			feat.setSmallName("cy");
//
//			feat.setValue(cy);
//
//			features.add(feat);
//
//			feat = new Feature();
//			feat.setName("Percent of black pixels with respect to all pixels.");
//			feat.setSmallName("pb");
//			computePb();
//			feat.setValue(pb);
//
//			features.add(feat);
//
//		
//
//			whiteInLowerAndInUpper();
//
//			feat = new Feature();
//			feat.setName("white surrond  by black in lower half of the digit");
//			feat.setSmallName("wsbInLower");
//
//			feat.setValue(countWsbLower);
//
//			features.add(feat);
//
//			feat = new Feature();
//			feat.setName("white surrond  by black in upper in the upper half of the digit");
//			feat.setSmallName("wsbInUpper");
//
//			feat.setValue(countWsbUppper);
//
//			features.add(feat);
//			
//			
//			///
//			 //--------------------------------------------------------------------------------------------
//			 computeAverageOfLast();
//			 feat=new Feature();
//			 feat.setName("Average location of the Last in a rows  In lower");
//			 feat.setSmallName("AverageLastInlower");
//			 feat.setValue(AverageLastInlower );								
//			 features.add(feat);
//			 //--------------------------------------------------------------------------------------------
//	 
//			 feat=new Feature();
//			 feat.setName("Average location of the Last in a rows in Upper");
//			 feat.setSmallName("AverageLastinUpper");
//			 feat.setValue( AverageLastinUpper);								
//			 features.add(feat);
//
//		return 0;
//	}
//	public int addingBorderFeatures() {
//		Feature feat;
//		 //--------------------------------------------------------------------------------------------
//		 computeBorderFeatures();
//		 feat=new Feature();
//		 feat.setName("Length of difference between first and last location of right border");
//		 feat.setSmallName("BorLocationLengthInLastCol");
//		 feat.setValue(BorderLocationLengthInLastColumn );								
//		 features.add(feat);
//		 
//		 feat=new Feature();
//		 feat.setName("Location of last pixel (column wise) in lower border");
//		 feat.setSmallName("BorLocationDownEnd");
//		 feat.setValue(BorderLocationDownEnd );								
//		 features.add(feat);
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Location of last pixel (row wise) in right border");
//		 feat.setSmallName("BorLocationLastColumn");
//		 feat.setValue(BorderLocationLastColumn );								
//		 features.add(feat);
//
//		 
//		 
//		 feat=new Feature();
//		 feat.setName("Length of lower border in row direction");
//		 feat.setSmallName("BorLocationDownLength");
//		 feat.setValue( BorderLocationDownLength );								
//		 features.add(feat);
//		 
//		   //AllFeatures.add("BorderLocationUpLength");
//		
//		 
//		 feat=new Feature();
//		 feat.setName("Length of  upper border in row direction");
//		 feat.setSmallName("BorderLocationUpLength");
//		 feat.setValue( BorderLocationUpLength );								
//		 features.add(feat);
//	 
//		 //  AllFeatures.add("BorderLocationFColLength");
////		   AllFeatures.add("BorderLocationUpEnd");
////		   AllFeatures.add("BorderLocationFColEnd");
//
//		 feat=new Feature();
//		 feat.setName("BorderLocationFColLength");
//		 feat.setSmallName("BorderLocationFColLength");
//		 feat.setValue( BorderLocationFColLength);								
//		 features.add(feat);
//		 
//	  
//		 
//		 feat=new Feature();
//		 feat.setName("BorderLocationUpEnd");
//		 feat.setSmallName("BorderLocationUpEnd");
//		 feat.setValue( BorderLocationUpEnd );								
//		 features.add(feat);
//		 
//			;
//			
//			 
//			 feat=new Feature();
//			 feat.setName("BorderLocationFColEnd");
//			 feat.setSmallName("BorderLocationFColEnd");
//			 feat.setValue(  BorderLocationFColEnd );								
//			 features.add(feat);
//			 
//			 
//
//			 //--------------------------------------------------------------------------------------------
//			computeCountWhiteWithBorder();
////			   AllFeatures.add("FromDownAfterBorDown");
////			   AllFeatures.add("FromLeftBeforeBorLeft");
////			   AllFeatures.add("FromLeftAfterBorLeft");
////			   
//				 feat=new Feature();
//				 feat.setName("FromDownAfterBorDown");
//				 feat.setSmallName("FromDownAfterBorDown");
//				 feat.setValue( FromDownAfterBorDown);								
//				 features.add(feat);
//				 
//				 feat=new Feature();
//				 feat.setName("FromLeftBeforeBorLeft");
//				 feat.setSmallName("FromLeftBeforeBorLeft");
//				 feat.setValue( FromLeftBeforeBorLeft);								
//				 features.add(feat);
//				 
//				 feat=new Feature();
//				 feat.setName("FromLeftAfterBorLeft");
//				 feat.setSmallName("FromLeftAfterBorLeft");
//				 feat.setValue( FromLeftAfterBorLeft);								
//				 features.add(feat);
//			 
//			 
//		return 0;
//	}
	public int computeAllFeatures() {
		features = new ArrayList<Feature>();

		logger
				.trace(" --------------------------------------------------------------------------------");
		logger.trace("   displaye features of digit   " + Label
				+ "  that has width of " + width + " and heith " + height);
		logExample.trace( "   displaye features of digit   " + Label
				+ "  that has width of " + width + " and heith " + height);
		logExample2.trace( "   displaye features of digit   " + Label
				+ "  that has width of " + width + " and heith " + height);
		computeBasicStates();
		Feature feat;
     	
          addingMyFeatures();
		if (addArabic)
			 addingArabicFeatures();
/////////////////////////////////////////////////////////////////////////////////////////////////		 


			
		//	

		if (logger.isTraceEnabled()) {
			logger
					.trace(" --------------------------------------Final features are ------------------------------------------");

			for (int i = 0; i < features.size(); i++) {
				logger.trace(features.get(i));
				logExample2.trace(features.get(i) );
			}
			logger.trace(" --------------------------------------------------------------------------------");
			logExample2.trace("##################################################################################" );
		}
		logExample.trace("##################################################################################" );

		// for (int i = 0; i < 3; i++) {
		// Feature feat=new Feature();
		// feat.setName("The  "+ i + " features");
		// feat.setSmallName("f"+i);
		//			
		// feat.setValue(Math.random());
		// features.add(feat);
		// }
		computed = true;
		return 0;
	}

	
	private void displayImage(int[][] im) {
		String StringTrace = "";
		for (int k = 0; k < im.length; k++) {
			for (int k2 = 0; k2 < im[k].length; k2++) {
				StringTrace += "   " + im[k][k2];
			}
			logger.trace("  " + StringTrace);
			StringTrace = "";
		}
	}

	private void displayImage(byte[][] im) {
		String StringTrace = "";
		for (int k = 0; k < im.length; k++) {
			for (int k2 = 0; k2 < im[k].length; k2++) {
				StringTrace += "   " + im[k][k2];
			}
			logger.trace("  " + StringTrace);
			StringTrace = "";
		}
	}





	public static void DisplayFeatureString() {

		Level TempLevel = logger.getLevel();
		logger.setLevel(Level.INFO);

		logger.info(" number of feature computed is " + AllFeatures.size());
		String str=getFeatureString();
		logger.info(" Features are " + str);
		
		logExample.trace( " Features are " + str);

		logExample.trace( "####################################################################");
		
		logger.setLevel(TempLevel);

	}
	

	public BufferedImage getImage() {
		
		return im.getIm();
	}

	public String toStringComputedFeatures(){
		String str="";
		String newline = System.getProperty("line.separator");
    str+=" Label is = "+ Label +"  offset is  "+offset+"   width  "+width + " and height " + height;
	str+=newline;
	
	 str+=" Count of white = "+ countWhite+"  count of black is  "+countBlack  ;
		str+=newline;
		str+=newline;
	 for (int i = 0; i < getFeatures().size(); i++) {
		 
	str+=" Feature "+i+"  "+getFeatures().get(i).getSmallName()+"  = "+getFeatures().get(i).getValue();
	str+=newline;
	}
	 
		return str;

	}
	 	public String toStringComputedFeaturesDetailed(){
	 		String str="";
			String newline = System.getProperty("line.separator");
	    str+=" Label is = "+ Label +"  offset is  "+offset+"   width  "+width + " and height " + height;
		str+=newline;
		
		 str+=" Count of white = "+ countWhite+"  count of black is  "+countBlack  ;
			str+=newline;
			 str+="  Using only  "+getFeatures().size()+"   From the computed  "+features.size();
				str+=newline;
				str+=newline;
		 for (int i = 0; i < getFeatures().size(); i++) {
		str+=" Feature "+i+"  "+getFeatures().get(i).getSmallName()+"  = "+getFeatures().get(i).getValue()+"   ====   "+getFeatures().get(i).getName();
		str+=newline;
		}
		 
			return str;

	}
	public String toStringDetails() {
		String str="";
		String newline = System.getProperty("line.separator");
    str+=" Label is = "+ Label +"  offset is  "+offset+"   width  "+width + " and height " + height;
	str+=newline;
	
	 str+=" Count of white = "+ countWhite+"  count of black is  "+countBlack  ;
		str+=newline;
 
	 for (int i = 0; i < features.size(); i++) {
	str+=" Feature "+i+"  "+features.get(i).getSmallName()+"  = "+features.get(i).getValue()+"   ====   "+features.get(i).getName();
	str+=newline;
	}
	 
		return str;
	}
	@Override
	public String toString() {
		String str="";
		String newline = System.getProperty("line.separator");
    str+=" Label is = "+ Label +"  offset is  "+offset+"   width  "+width + " and height " + height;
	str+=newline;
	
	 str+=" Count of white = "+ countWhite+"  count of black is  "+countBlack  ;
		str+=newline;
 
	 for (int i = 0; i < features.size(); i++) {
	str+=" Feature "+i+"  "+features.get(i).getSmallName()+"  = "+features.get(i).getValue();
	str+=newline;
	}
	 
		return str;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void WriteTextImage(String filename) {
		
		  FileOutputStream file; 
	        PrintStream out; // declare a print stream object
	        try {
	         // Create a new file output stream
	        file = new FileOutputStream(filename+"_TextFeat.txt");
                
	                // Connect print stream to the output stream
	               out = new PrintStream(file);
	               out.println();
	               
	               
	               if (!computed)
	            	   this.computeAllFeatures();
                  byte[][] image = im.getBinaryBounded();
                  
              String StringTrace="";
              	out.println("------------------------------------------------------------------------------");
              	
                  for (int i = 0; i < image.length; i++) {
                	  StringTrace="";
                	  
          			if (i==0)
    				{
    					String stD="";
    					for (int j = 0; j <   image[i].length; j++) {
    						if (j<10)
    						   stD+=  "|"+  j;
    						else 
    							stD+= "|"+  (j-10);
    					}
    					
    					out.println("  c   |"+stD);
    				}
                	  
                	  
					for (int k = 0; k < image[i].length; k++) {
							StringTrace+=  "|"+  image[i][k];
					}
					 //  out.println("  "+StringTrace+" |");
					   
						if (i>=10)
							out.println("  "+i+"  |"+StringTrace );
						else
							out.println("  "+i+"   |"+StringTrace );
				}
               
                  out.println("----------------------------------------------------------------------------------------");
               int[][] image2 = im.getPixelsBounded();   
                  String st;
                  for (int i = 0; i < image2.length; i++) {
                	  StringTrace="";
					for (int k = 0; k < image2[i].length; k++) {
						
						st=image2[i][k]+"";
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
						
							StringTrace+=  "|"+ st;
					}
					   out.println("  "+StringTrace+" |");
				}    
                  
                  out.println("-------------------------------------------------------------------------------------------------");

	            if (AppDefaults.WriteFeaturesMode==AppDefaults.WRITE_ALL_DETAILED){
	              out.print( toStringDetails() );
	            }
	            else if (AppDefaults.WriteFeaturesMode==AppDefaults.WRITE_ALL){
	            	out.print( toStringDetails() );
	            }
	            else if (AppDefaults.WriteFeaturesMode==AppDefaults.WRITE_ONLY_COMPUTED){
	            
	            	        	out.print( toStringComputedFeatures() );
	            }
	            else if (AppDefaults.WriteFeaturesMode==AppDefaults.WRITE_ONLY_COMPUTED_DETAILED){
	            	
	            	        	out.print( toStringComputedFeaturesDetailed() );
	            }
	                
	        }
	        catch (Exception e){
	               logger.error(" Error in writing to file",e);
	        }
		
		
	}

	public byte[][] getSubRegion(Rectangle reg) {

		
		if (reg==null)
		{
			return digitImage;
		}
		
		byte[][] newRegion=new byte[reg.width][reg.height];
		int nX,nY;
		
		for (int i = 0; i < newRegion.length; i++) {
			for (int j = 0; j < newRegion[i].length; j++) {
				nX=i+reg.x;
			   nY=j+reg.y;
			   
			   if (nX<digitImage.length&&nY<digitImage[i].length)
				   newRegion[i][j]=digitImage[nX][nY];
			   else 
				   
				   newRegion[i][j]=0;
				
				
			}
		}
		
		
		return   newRegion;
	}
public byte[][] getSubRegion(RegionCreater  reg) {

		
		if (reg==null)
		{
			return digitImage;
		}
		
		return reg.creatRegion(digitImage);
		
	 
	}
	public int getHeight() {
		return (int) this.height;
		//return 0;
	}
	public int getWidth(){
		return (int) this.width;
	}
}
