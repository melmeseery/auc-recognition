/**
 * 
 */
package data.image;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import data.feature.Feature;

/**
 * @author TOSHIBA
 * 
 */
public class FeaturedRegion extends RegionOfImage {
	private static transient final Logger logger = Logger
			.getLogger(FeaturedRegion.class);

	// private boolean computed;
	// static ArrayList<String> ComputedFeatures=null;
	// static ArrayList<String> AllFeatures;
	// protected ArrayList<Feature> features;

	public FeaturedRegion(Digit image, Rectangle digitImages) {
		super(image, digitImages);
		// logger.setLevel(Level.ALL);
		// this.SourceImage=image;
		//		 
		//		
		//		 
		// size = new Dimension( digitImages.width ,digitImages.height);
		// location=new Point(digitImages.x,digitImages.y);

	}

	public FeaturedRegion(Digit image, RegionCreater r) {

		super(image, r);

	}

	// public int addingArabicFeatures() {
	// computeAllArabicFeatures();
	// Feature feat;
	// //
	// Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
	// // //////////////////////////////////
	// feat = new Feature();
	// feat.setName(" height over width ");
	// feat.setSmallName("HW");
	// feat.setValue(heightoverwidth);
	// featureHW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,brights.add(feat);
	//
	// //
	// //////////////////////////////////HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// // feat = new Feature();
	// // feat.setName("vol2");
	// // feat.setSmallName("vol2");
	// // feat.setValue(vol2);
	// // features.add(feat);
	// // // //////////////////////////////////
	// // feat = new Feature();
	// // feat.setName("Vol(1) ");
	// // feat.setSmallName("Vol(1)");
	// // feat.setValue(vol1);
	// // features.add(feat);
	// // // //////////////////////////////////
	// // feat = new Feature();
	// // feat.setName("Vol(2) ");
	// // feat.setSmallName("Vol(2)");
	// // feat.setValue(Vol_2);
	// // features.add(feat);
	// // //////////////////////////////////
	// feat = new Feature();
	// feat.setName("w2r");
	// feat.setSmallName("w2r");
	// feat.setValue(w2r);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// feat = new Feature();
	// feat.setName("wu");
	// feat.setSmallName("wu");
	// feat.setValue(wu);
	// features.add(feat);
	// // //////////////////////////////////
	// feat = new Feature();
	// feat.setName("w4lft");
	// feat.setSmallName("w4lft");
	// feat.setValue(w4lft);
	// features.add(feat);
	// // //////////////////////////////////
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wrb");
	// feat.setSmallName("wrb");
	// feat.setValue(wrb);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("mxb");
	// feat.setSmallName("mxb");
	// feat.setValue(mxb);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wd");
	// feat.setSmallName("wd");
	// feat.setValue(wd);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("u1");
	// feat.setSmallName("u1");
	// feat.setValue(u1);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("smallwd");
	// feat.setSmallName("smallwd");
	// feat.setValue(smallwd);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("sre");
	// feat.setSmallName("sre ");
	// feat.setValue(sre);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("mce69");
	// feat.setSmallName("mce69");
	// feat.setValue(mce69);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("sr");
	// feat.setSmallName("sr");
	// feat.setValue(sr);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("im");
	// feat.setSmallName("im");
	// feat.setValue(im2);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wce");
	// feat.setSmallName("wce");
	// feat.setValue(wce);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wcb");
	// feat.setSmallName("wcb");
	// feat.setValue(wcb);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wre");
	// feat.setSmallName("wre");
	// feat.setValue(wre);
	// features.add(feat);
	//
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("btop");
	// feat.setSmallName("btop");
	// feat.setValue(PbinF4C);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("bbot");
	// feat.setSmallName("bbot");
	// feat.setValue(PbinL4C);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("blft");
	// feat.setSmallName("blft");
	// feat.setValue(PbinF4R);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("bright");
	// feat.setSmallName("bright");
	// feat.setValue(PbinL4R);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("sle3");
	// feat.setSmallName("sle3");
	// feat.setValue(sle3);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("snv");
	// feat.setSmallName("snv");
	// feat.setValue(snv);
	// features.add(feat);
	// // //////////////////////////////////
	// //
	// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("cbe");
	// feat.setSmallName("cbe");
	// feat.setValue(cbe);
	// features.add(feat);
	//
	// // //wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("wcbe");
	// feat.setSmallName("wcbe");
	// feat.setValue(wcbe);
	// features.add(feat);
	//
	// // ///wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
	// feat = new Feature();
	// feat.setName("mxlre6");
	// feat.setSmallName("mxlre6");
	// feat.setValue(mxlre6);
	// features.add(feat);
	//
	// // //////////////////////////////////////////////
	// feat = new Feature();
	// feat.setName("lxre6");
	// feat.setSmallName("lxre6");
	// feat.setValue(lxre6);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("lxrb");
	// feat.setSmallName("lxrb");
	// feat.setValue(lxrb);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("mxce");
	// feat.setSmallName("mxce");
	// feat.setValue(mxce);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("lxce");
	// feat.setSmallName("lxce");
	// feat.setValue(lxce);
	// features.add(feat);
	//
	// return 0;
	// }

	public int addingMyFeaures() {
		Feature feat;

		addingTransitionFeatures();
		addingPercentInRows();
		addingGabFeatures();
		addingSurrondFeatures();
		addingWideFeatures();
		addingWhiteTillBlack();
		addingBlockFeatures();

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

		//	
		return 0;
	}

	// public int addingTransitionFeatures() {
	// Feature feat;
	//
	// computeNegativeTransition();
	// feat = new Feature();
	// feat.setName("Count number of Negative Transition");
	// feat.setSmallName("CountNegativeTransition");
	// feat.setValue(CountNegativeTransition);
	// features.add(feat);
	// // /-------------------------------------------------------------
	// // CountLargeNegativeTransition
	//
	// // computeNegativeTransition();
	// feat = new Feature();
	// feat.setName("Count Large Negative Transition");
	// feat.setSmallName("CountLargeNegativeTransition");
	// feat.setValue(CountLargeNegativeTransition);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("Count Positive Transition");
	// feat.setSmallName("CountPositiveTransition");
	// feat.setValue(CountPositiveTransition);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("Count Zero Transition");
	// feat.setSmallName("CountZeroTransition");
	// feat.setValue(CountZeroTransition);
	// features.add(feat);
	// return 0;
	// }

	// public int addingSurrondFeatures() {
	// Feature feat;
	//
	// CountSurrondsNumber();
	//
	// feat = new Feature();
	// feat.setName("White pixels that are Surrond by 3 black pixel");
	// feat.setSmallName("srby3");
	// feat.setValue(countSurr3);
	// features.add(feat);
	//
	// computeSurrondRight();
	// feat = new Feature();
	// feat
	// .setName("Number of white pixels that are Srround by three black pixel from the right till first black pixel. ");
	// feat.setSmallName("SrB3FromRight");
	//
	// feat.setValue(SrB3FromRight);
	//
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Number of white pixels that are Srround by three black pixel from the left till first black pixel.");
	// feat.setSmallName("SrB3FromLeft");
	//
	// feat.setValue(SrB3FromLeft);
	//
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Number of white pixels that are Srround by three black pixel from the left till first black pixel.");
	// feat.setSmallName("SrB3FromUp");
	//
	// feat.setValue(SrB3FromUp);
	//
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Number of white pixels  SrB3FromDown that are Srround by three black pixel from the left till first black pixel.");
	// feat.setSmallName("SrB3FromDown");
	//
	// feat.setValue(SrB3FromDown);
	//
	// features.add(feat);
	//
	// return 0;
	// }
	//
	// public int addingGabFeatures() {
	// // //////////////////////////////////
	// Feature feat;
	//
	// computeLhg();
	//
	// feat = new Feature();
	// feat
	// .setName("The length of the largest Horizontal Gap (A gab is white space between black pixels [- -])");
	// feat.setSmallName("lhg");
	//
	// feat.setValue(lhg);
	//
	// features.add(feat);
	//
	// // ///////////////////////////
	// // //////////////////////////////////
	// computeLvg();
	//
	// feat = new Feature();
	// feat.setName("The length of the Largest vertical Gap");
	// feat.setSmallName("lvg");
	//
	// feat.setValue(lvg);
	//
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("The location of the Largest Horizontal Gap ");
	// feat.setSmallName("lhgi");
	// // computeLhg();
	// feat.setValue(lhgi);
	//
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("The location of the Largest vertical Gap ");
	// feat.setSmallName("lvgi");
	//
	// feat.setValue(lvgi);
	//
	// features.add(feat);
	// return 0;
	// }
	//
	// public int addingBlockFeatures() {
	// Feature feat;
	//
	// computeLargestHorizentalBlackBlock();
	//
	// feat = new Feature();
	// feat
	// .setName(" Max Horizontal Black Block (continous black pixel in a direction) Length ");
	//
	// feat.setSmallName("MaxHBlackLength");
	// feat.setValue(MaxHorizentalBlackBlockLength);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName(" Average Horizental Black Block Length ");
	//
	// feat.setSmallName("AvgHBlackLength");
	// feat.setValue(AvgHorizentalBlackBlockLength);
	// features.add(feat);
	//
	// // //////////////////////
	// feat = new Feature();
	// feat.setName(" Max Horizental Black Block Length Row Location ");
	//
	// feat.setSmallName("MaxHBlackLengthLocation");
	// feat.setValue(MaxHorizentalBlackBlockLengthRowLocation);
	// features.add(feat);
	// // ///////////////////////////////////
	//
	// feat = new Feature();
	// feat.setName(" Max count Of Horizontal Black Blocks in a Row ");
	//
	// feat.setSmallName("MaxNumberOfHBlackBlocks");
	// feat.setValue(MaxNumberOfHorizontalBlackBlockCount);
	// features.add(feat);
	//
	// // //////////////////////////////////////
	//
	// feat = new Feature();
	// feat
	// .setName("The Row location of the  Max count Of Horizontal Black Block in a row  ");
	//
	// feat.setSmallName("MaxNumberOfHBlackBlocksLoc");
	// feat.setValue(MaxNumberOfHorizontalBlackBlockCountRowLocation);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	//
	// //
	// // ////////////////////////////////////////////
	// computeLargestVerticalBlackBlock();
	// feat = new Feature();
	// feat.setName("  Max Vertical Black Block Length ");
	//
	// feat.setSmallName("MaxVBlackLength");
	// feat.setValue(MaxVerticalBlackBlockLength);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	// feat = new Feature();
	// feat.setName(" Max Vertical Black Block Length Col Location ");
	//
	// feat.setSmallName("MaxVBlackLengthLocation");
	// feat.setValue(MaxVerticalBlackBlockLengthColLocation);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	//
	// feat = new Feature();
	// feat.setName(" Max Number Of Vertical BlackBlocks  ");
	//
	// feat.setSmallName("MaxNumberOfVBlackBlocks");
	//
	// feat.setValue(MaxVerticalblocksCount);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName(" Max Vertical blocks Count Col Location ");
	//
	// feat.setSmallName("MaxNumberOfVBlackBlocksLoc");
	// feat.setValue(MaxVerticalblocksCountColLocation);
	// features.add(feat);
	// computeLargestHorizentalBlackBlock();
	//
	// feat = new Feature();
	// feat
	// .setName(" Max Horizontal Black Block (continous black pixel in a direction) Length ");
	//
	// feat.setSmallName("MaxHBlackLength");
	// feat.setValue(MaxHorizentalBlackBlockLength);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName(" Average Horizental Black Block Length ");
	//
	// feat.setSmallName("AvgHBlackLength");
	// feat.setValue(AvgHorizentalBlackBlockLength);
	// features.add(feat);
	//
	// // //////////////////////
	// feat = new Feature();
	// feat.setName(" Max Horizental Black Block Length Row Location ");
	//
	// feat.setSmallName("MaxHBlackLengthLocation");
	// feat.setValue(MaxHorizentalBlackBlockLengthRowLocation);
	// features.add(feat);
	// // ///////////////////////////////////
	//
	// feat = new Feature();
	// feat.setName(" Max count Of Horizontal Black Blocks in a Row ");
	//
	// feat.setSmallName("MaxNumberOfHBlackBlocks");
	// feat.setValue(MaxNumberOfHorizontalBlackBlockCount);
	// features.add(feat);
	//
	// // //////////////////////////////////////
	//
	// feat = new Feature();
	// feat
	// .setName("The Row location of the  Max count Of Horizontal Black Block in a row  ");
	//
	// feat.setSmallName("MaxNumberOfHBlackBlocksLoc");
	// feat.setValue(MaxNumberOfHorizontalBlackBlockCountRowLocation);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	//
	// //
	// // ////////////////////////////////////////////
	// computeLargestVerticalBlackBlock();
	// feat = new Feature();
	// feat.setName("  Max Vertical Black Block Length ");
	//
	// feat.setSmallName("MaxVBlackLength");
	// feat.setValue(MaxVerticalBlackBlockLength);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	// feat = new Feature();
	// feat.setName(" Max Vertical Black Block Length Col Location ");
	//
	// feat.setSmallName("MaxVBlackLengthLocation");
	// feat.setValue(MaxVerticalBlackBlockLengthColLocation);
	// features.add(feat);
	//
	// // ////////////////////////////////////////////
	//
	// feat = new Feature();
	// feat.setName(" Max Number Of Vertical BlackBlocks  ");
	//
	// feat.setSmallName("MaxNumberOfVBlackBlocks");
	//
	// feat.setValue(MaxVerticalblocksCount);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName(" Max Vertical blocks Count Col Location ");
	//
	// feat.setSmallName("MaxNumberOfVBlackBlocksLoc");
	// feat.setValue(MaxVerticalblocksCountColLocation);
	// features.add(feat);
	//
	// // AvgVerticalBlockLengthInRight
	// feat = new Feature();
	// feat.setName("AvgVerticalBlockLengthInRight");
	//
	// feat.setSmallName("AvgVerticalBlockLengthInRight");
	// feat.setValue(AvgVerticalBlockLengthInRight);
	// features.add(feat);
	//
	// // AvgVerticalBlockLengthInRight
	// feat = new Feature();
	// feat.setName("AvgVerticalBlockLengthInRight");
	//
	// feat.setSmallName("AvgVerticalBlockLengthInRight");
	// feat.setValue(AvgVerticalBlockLengthInRight);
	// features.add(feat);
	// return 0;
	// }
	//
	// public int addingWhiteTillBlack() {
	// Feature feat;
	// countWhiteBlockTillBlack();
	//
	// feat = new Feature();
	// feat
	// .setName("Count of White pixels from upper border till the first black pixel ");
	// feat.setSmallName("frup");
	// feat.setValue(fromUp);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Count of White pixels from lower border (down) till the first black pixel ");
	// feat.setSmallName("frdown");
	// feat.setValue(fromDown);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Count of White pixels from left border till the first black pixel");
	// feat.setSmallName("frleft");
	// feat.setValue(fromLeft);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Count of White pixels from right border till the first black pixel");
	// feat.setSmallName("frright");
	// feat.setValue(fromRight);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Direction of Maximum count of white pixels (up,down,left..)");
	// feat.setSmallName("dirMaxW");
	// feat.setValue(LocationOfMaxWhite);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Count of White pixels from lower border (down) till the first black pixel in the Left half of the digit");
	// feat.setSmallName("fromDownLeft");
	// feat.setValue(fromDownLeft);
	// features.add(feat);
	//
	// return 0;
	// }
	//
	// public int addingWideFeatures() {
	// Feature feat;
	// // /------------------------------------------------------------
	// computeWideFeatures();
	// feat = new Feature();
	// feat.setName("Average width of the digit");
	// feat.setSmallName("BlackWide");
	//
	// feat.setValue(BlackWide);
	//
	// features.add(feat);
	// // ---------------------------------------------------
	// feat = new Feature();
	// feat.setName("Average Width of the digit in the upper half ");
	// feat.setSmallName("AverageWideUp");
	// feat.setValue(AverageWideUp);
	// features.add(feat);
	//
	// //
	// --------------------------------------------------------------------------------------------
	// feat = new Feature();
	// feat.setName("Count number of row that has smaller than 1/4 width");
	// feat.setSmallName("CountLowWide");
	// feat.setValue(CountLowWide);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Count number of rows that has width larger than 1/2 width ");
	// feat.setSmallName("CountBigWide");
	// feat.setValue(CountBigWide);
	// features.add(feat);
	//
	// computeVerticalLength();
	// // AllFeatures.add("BlackWideVertical");
	// // AllFeatures.add("CountBigWideVertical");
	// // AllFeatures.add("CountLowWideVertical");
	// // AllFeatures.add("AverageWideLeftVertical");
	//
	// //
	// //--------------------------------------------------------------------------------------------
	//
	// feat = new Feature();
	// feat.setName("BlackWideVertical");
	// feat.setSmallName("BlackWideVertical");
	//
	// feat.setValue(BlackWideVertical);
	//
	// features.add(feat);
	// // ---------------------------------------------------
	//
	// feat = new Feature();
	// feat.setName("CountBigWideVertical");
	// feat.setSmallName("CountBigWideVertical");
	// feat.setValue(CountBigWide);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("CountLowWideVertical");
	// feat.setSmallName("CountLowWideVertical");
	// feat.setValue(CountLowWide);
	// features.add(feat);
	//
	// return 0;
	// }
	//
	// public int addingPercentInRows() {
	// Feature feat;
	// // --------------------------------------------------------------
	// computePercentInRows();
	//
	// feat = new Feature();
	// feat.setName("Percent of black pixel in the first four rows");
	// feat.setSmallName("PbinF4R");
	// feat.setValue(PbinF4R);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("Percent of black pixel in the last four rows ");
	// feat.setSmallName("PbinL4R");
	// feat.setValue(PbinL4R);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat.setName("Percent of black pixel in the last four columns ");
	// feat.setSmallName("PbinL4C");
	// feat.setValue(PbinL4C);
	// features.add(feat);
	//
	// //
	// --------------------------------------------------------------------------------------------
	// // see previous
	// feat = new Feature();
	// feat.setName("Percent of black pixel in the first four columns");
	// feat.setSmallName("PbinF4C");
	// feat.setValue(PbinF4C);
	// features.add(feat);
	//
	// feat = new Feature();
	// feat
	// .setName("Percent of black pixel in the first four columns in the upper half");
	// feat.setSmallName("PbinF4CinUpper");
	// feat.setValue(PbinF4CinUpper);
	// features.add(feat);
	// return 0;
	// }

	public int computeAllFeatures() {
		features = new ArrayList<Feature>();
		// logger.setLevel(Level.ALL);
		logger
				.trace(" --------------------------------------------------------------------------------");
		// logger.trace("   displaye features of digit   " + Label
		// + "  that has width of " + width + " and heith " + height);
		// logExample.trace( "   displaye features of digit   " + Label
		// + "  that has width of " + width + " and heith " + height);
		// logExample2.trace( "   displaye features of digit   " + Label
		// + "  that has width of " + width + " and heith " + height);
		computeBasicStates();
		Feature feat;

		addingMyFeaures();
		if (addArabic)
			addingArabicFeatures();

		if (logger.isTraceEnabled()) {
			logger
					.trace(" --------------------------------------Final features are ------------------------------------------");
			String str;
			logger
					.trace("----------------------------------------------------------------------------");
			for (int i = 0; i < digitImage.length; i++) {
				str = "";
				for (int j = 0; j < digitImage[i].length; j++) {
					str += "|" + digitImage[i][j];
				}
				logger.trace(str + " |");
			}
			logger
					.trace("-------------------------------------------------------------------------");
			for (int i = 0; i < features.size(); i++) {
				logger.trace(features.get(i));
				// / logExample2.trace(features.get(i) );
			}
			// logger.trace(toString());
			logger
					.trace(" --------------------------------------------------------------------------------");
			// logExample2.trace("##################################################################################"
			// );
		}
		// logExample.trace("##################################################################################"
		// );

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

	public static void loadAllFeatureArray() {
		AllFeatures = new ArrayList<String>();

		// AllFeatures.add("cx");
		// AllFeatures.add("MCR"); //
		// AllFeatures.add("cy");// remove now (had lower ranke )
		// AllFeatures.add("lhg");
		// AllFeatures.add("lvg");
		// AllFeatures.add("lhgi"); // remove now (had lower ranke )
		// AllFeatures.add("lvgi");// remove now (had lower ranke )
		// AllFeatures.add("Count1VG");
		// AllFeatures.add("AvgVGapLength");
		// // Count1VG
		// // AvgVGapLength
		// AllFeatures.add("hOw");// remove now (had lower ranke )
		//

		//
		// // AllFeatures.add("srby1"); //surrond by 1
		// AllFeatures.add("srby2"); // surrond by 2

		// // AllFeatures.add("srby4"); //surrond by 4
		AllFeatures.add("wsb");
		AllFeatures.add("frup");
		AllFeatures.add("frdown");
		AllFeatures.add("frleft");

		AllFeatures.add("frright");
		AllFeatures.add("hOw");// remove now (had lower ranke )

		AllFeatures.add("pb");// remove now (had lower ranke )
		AllFeatures.add("dirMaxW");
		AllFeatures.add("fromDownLeft");

		AllFeatures.add("srby3"); // surrond by 3
		AllFeatures.add("SrB3FromRight");
		AllFeatures.add("SrB3FromLeft");
		AllFeatures.add("SrB3FromUp");
		AllFeatures.add("SrB3FromDown");

		// // 0 1 2 3 4 5 6 7 8 9
		AllFeatures.add("PbinF4R");
		AllFeatures.add("PbinL4R");
		AllFeatures.add("PbinL4C");
		AllFeatures.add("PbinF4C");
		AllFeatures.add("PbinF4CinUpper");

		AllFeatures.add("CountNegativeTransition");
		AllFeatures.add("CountLargeNegativeTransition");
		AllFeatures.add("CountPositiveTransition");
		AllFeatures.add("CountZeroTransition");

		AllFeatures.add("BlackWide");
		AllFeatures.add("AverageWideUp");

		AllFeatures.add("CountLowWide");
		AllFeatures.add("CountBigWide");

		AllFeatures.add("BlackWideVertical");
		AllFeatures.add("CountBigWideVertical");
		AllFeatures.add("CountLowWideVertical");
		AllFeatures.add("cx");
		AllFeatures.add("MCR"); //
		AllFeatures.add("cy");// remove now (had lower ranke )
		AllFeatures.add("lhg");
		AllFeatures.add("lvg");
		AllFeatures.add("lhgi"); // remove now (had lower ranke )
		AllFeatures.add("lvgi");// remove now (had lower ranke )
		AllFeatures.add("MaxHBlackLength");
		AllFeatures.add("AvgHBlackLength");
		AllFeatures.add("MaxHBlackLengthLocation");
		AllFeatures.add("MaxNumberOfHBlackBlocks");
		AllFeatures.add("MaxNumberOfHBlackBlocksLoc");
		AllFeatures.add("MaxVBlackLength");
		AllFeatures.add("MaxVBlackLengthLocation");
		AllFeatures.add("MaxNumberOfVBlackBlocks");
		AllFeatures.add("MaxNumberOfVBlackBlocksLoc");
		AllFeatures.add("AvgVerticalBlockLengthInRight");
		AllFeatures.add("HW");
//		AllFeatures.add("vol2");
//		AllFeatures.add("Vol(1)");
//		AllFeatures.add("Vol(2)");
		AllFeatures.add("w2r");
		AllFeatures.add("wu");
		AllFeatures.add("w4lft");
		AllFeatures.add("w4");
		AllFeatures.add("wrb");
		AllFeatures.add("mxb");
		AllFeatures.add("wd");
		AllFeatures.add("u1");
		AllFeatures.add("smallwd");
		AllFeatures.add("sre");
		AllFeatures.add("mce69");
		AllFeatures.add("sr");
		AllFeatures.add("im");
		AllFeatures.add("wce");
		AllFeatures.add("wcb");
		AllFeatures.add("wre");
		AllFeatures.add("btop");
		AllFeatures.add("bbot");
		AllFeatures.add("sle3");
		AllFeatures.add("sle");
		AllFeatures.add("snv");
		AllFeatures.add("cbe");
		AllFeatures.add("wcbe");
		AllFeatures.add("szero");
		AllFeatures.add("mxlre6");
		AllFeatures.add("lxre6");
		AllFeatures.add("lxrb");
		AllFeatures.add("mxce");
		AllFeatures.add("lxce");
		AllFeatures.add("blft");
		AllFeatures.add("bright");
		// AllFeatures.add("MaxHBlackLength");
		// AllFeatures.add("AvgHBlackLength");
		// AllFeatures.add("MaxHBlackLengthLocation");
		// AllFeatures.add("MaxNumberOfHBlackBlocks");
		// AllFeatures.add("MaxNumberOfHBlackBlocksLoc");
		//
		// AllFeatures.add("MaxVBlackLength");
		// AllFeatures.add("MaxVBlackLengthLocation");
		// AllFeatures.add("MaxNumberOfVBlackBlocks");
		// AllFeatures.add("MaxNumberOfVBlackBlocksLoc");
		// AllFeatures.add("AvgVerticalBlockLengthInRight");
		// AllFeatures.add("wsbInLower");
		// AllFeatures.add("wsbInUpper");
		//
		// AllFeatures.add("PBinLeftVsRight");
		// AllFeatures.add("PBinUpVsDown");
		//
		// AllFeatures.add("PbCountD");
		// AllFeatures.add("PbCountU");
		// AllFeatures.add("PbCountL");
		// AllFeatures.add("PbCountR");
		// AllFeatures.add("PbCountLOverCountInRight");
		//	
		//	
		// AllFeatures.add("BlackWide");
		// AllFeatures.add("AverageWideUp");
		//	
		// AllFeatures.add("CountLowWide");
		// AllFeatures.add( "CountBigWide");
		//	
		//	

		// AllFeatures.add("FromRightUp");
		//	
		// AllFeatures.add("FromLeftDown");
		// AllFeatures.add("FromLeftUp");
		// //;
		AllFeatures.add("HW");
		AllFeatures.add("vol2");
		AllFeatures.add("Vol(1)");
		AllFeatures.add("Vol(2)");
		AllFeatures.add("w2r");
		AllFeatures.add("wu");
		AllFeatures.add("w4lft");
		AllFeatures.add("w4");
		AllFeatures.add("wrb");
		AllFeatures.add("mxb");
		AllFeatures.add("wd");
		AllFeatures.add("u1");
		AllFeatures.add("smallwd");
		AllFeatures.add("sre");
		AllFeatures.add("mce69");
		AllFeatures.add("sr");
		AllFeatures.add("im");
		AllFeatures.add("wce");
		AllFeatures.add("wcb");
		AllFeatures.add("wre");
		AllFeatures.add("btop");
		AllFeatures.add("bbot");
		AllFeatures.add("sle3");
		AllFeatures.add("sle");
		AllFeatures.add("snv");
		AllFeatures.add("cbe");
		AllFeatures.add("wcbe");
		AllFeatures.add("szero");
		AllFeatures.add("mxlre6");
		AllFeatures.add("lxre6");
		AllFeatures.add("lxrb");
		AllFeatures.add("mxce");
		AllFeatures.add("lxce");
		AllFeatures.add("blft");
		AllFeatures.add("bright");
		// AllFeatures.add("SudenChangeFRight");
		// AllFeatures.add("SudenChangeFRightLocation");
		// AllFeatures.add("SudenChangeFLeft");
		// AllFeatures.add("SudenChangeFLeftLocation");
		// //average number of gaps (horizonatal or vertical... )
		// //AllFeatures.add("");
		//
		// AllFeatures.add("AverageLastInlower");
		// AllFeatures.add("AverageLastinUpper");
		// AllFeatures.add("BorLocationLengthInLastCol");
		//
		// AllFeatures.add("BorLocationDownEnd");
		// AllFeatures.add("BorLocationLastColumn");
		// AllFeatures.add("BorLocationDownLength");
		//   
		// AllFeatures.add("BorderLocationUpLength");
		// AllFeatures.add("BorderLocationFColLength");
		// AllFeatures.add("BorderLocationUpEnd");
		// AllFeatures.add("BorderLocationFColEnd");
		// AllFeatures.add("BlackWideVertical");
		// AllFeatures.add("CountBigWideVertical");
		// AllFeatures.add("CountLowWideVertical");
		// AllFeatures.add("AverageWideLeftVertical");
		// AllFeatures.add("AverageWideRightVertical");
		// 
		//  
		//   
		// AllFeatures.add("FromDownAfterBorDown");
		// AllFeatures.add("FromLeftBeforeBorLeft");
		// AllFeatures.add("FromLeftAfterBorLeft");

		// / average
		// AllFeatures.add("AverageVerticalLenRight");

		// SudenChangeFLeftLocation
		// count of zero transitions
		// sudden change from right (down )
		//	
		//	
		// wsb Sudden change from left
		// zero transition
		//		
		// sudden change from right up

		// percent of pb in first four rows
		// percent of pb in upper first four columns
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

	@Override
	public void ReadImage(ImageReader im) {

	}

	@Override
	public String toString() {
		String str = "";
		String newline = System.getProperty("line.separator");
		str += " Label is =  " + SourceImage.getLabel() + " width  "
				+ size.width + " and height " + size.height;
		str += newline;

		str += " Count of white = " + countWhite + "  count of black is  "
				+ countBlack;
		str += newline;

		for (int i = 0; i < features.size(); i++) {
			str += " Feature " + features.get(i).getSmallName() + "  = "
					+ features.get(i).getValue();
			str += newline;
		}

		return str;
	}

	// public static ArrayList<String> getComputedFeatures() {
	// if (ComputedFeatures == null)
	// return AllFeatures;
	// else
	// return ComputedFeatures;
	// }

}
