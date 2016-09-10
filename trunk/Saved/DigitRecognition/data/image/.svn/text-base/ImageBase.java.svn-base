/**
 * 
 */
package data.image;

import gui.AppDefaults;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import util.lib;

import data.feature.Feature;

/**
 * @author TOSHIBA
 */
public abstract class ImageBase {
	private static transient final Logger logger = Logger
			.getLogger(ImageBase.class);
	protected double wsb = 0; // white
	// surronded
	// by
	// black
	protected double lvg = 0; // largest
	// vertical
	// gap
	protected double lvgi; // largest
	// vertical
	// gap
	// location
	protected double lhg = 0; // largest
	// horizontal
	// gap
	protected double lhgi = 0; // laregest
	// horizontal
	// gap
	// location
	protected double mcr; // mean
	// centriodal
	// radius
	protected double pb; // percent
	// of
	// black
	protected double cx, cy; // centroid
	// x
	// and
	// centroid
	// y
	protected double height = 0;
	protected double width = 0;
	protected double realHeight = 0;
	protected double realWidth = 0;
	int[][] surrondLabelImage;
	int countBlack;
	int countWhite;
	int CountWSB;
	protected double heightoverwidth;
	protected double widthoverheight;
	protected double countSurr2, countSurr3, countSurr4;
	protected double fromLeft, fromRight, fromDown, fromUp;
	protected double lvbb, lhbb, lvbbi, lhbbi;
	protected double countSurr1;
	int bUs, bUe, bLs, bLe, bRs, bRe, bDs, bDe;
	// private int blocksCountMaxRowLocation;
	protected double MaxHorizentalBlackBlockLength;
	protected double MaxHorizentalBlackBlockLengthRowLocation;
	protected double MaxNumberOfHorizontalBlackBlockCount;
	protected double MaxNumberOfHorizontalBlackBlockCountRowLocation;
	protected double MaxVerticalBlackBlockLength;
	protected double MaxVerticalBlackBlockLengthColLocation;
	protected double MaxVerticalblocksCount;
	protected double MaxVerticalblocksCountColLocation;
	protected int LocationOfMaxWhite;
	protected double countWsbLower;
	protected double countWsbUppper;
	protected double PBinLeftVsRight;
	protected double PBinUpVsDown;
	protected double PbCountL;
	protected double PbCountR;
	protected double PbCountU;
	protected double PbCountD;
	protected double BlackWide;
	protected double SrB3FromRight;
	protected double FromRightUp;
	double MaxBlackWide = 0;
	double MaxBlackWideRowLocation = 0;
	protected double PbCountLOverCountInRight;
	protected double PbinF4R;
	protected double PbinL4R;
	protected double PbinF4CinUpper;
	protected double CountNegativeTransition;
	static int NUMBER_FIRST = 3;
	protected double CountLargeNegativeTransition;
	protected double CountZeroTransition;
	protected double SudenChangeFRight;
	protected double SudenChangeFRightLocation;
	protected double SudenChangeFLeft;
	protected double SudenChangeFLeftLocation;
	protected double FromLeftDown;
	protected double PbinL4C;
	protected double AverageWideUp;
	protected double CountLowWide;
	protected double AverageLastInlower;
	protected double PbinF4C;
	protected double NegativeTrainsionFromRight;
	protected double BorderLocationLengthInLastColumn;
	protected double AverageLastinUpper;
	protected double CountBigWide;
	protected double CountPositiveTransition;
	protected double BorderLocationLastColumn;
	protected double BorderLocationDownEnd;
	protected double AvgHorizentalBlackBlockLength;
	protected double BorderLocationDownLength;
	protected double SrB3FromLeft;
	protected double AverageVerticalLenRight;
	protected double BlackWideVertical;
	// protected double AverageWideUpVertical;
	protected double CountBigWideVertical;
	protected double CountLowWideVertical;
	protected double AverageWideLeftVertical;
	protected double BorderLocationUpLength;
	protected double BorderLocationFColLength;
	protected double BorderLocationUpEnd;
	protected double BorderLocationFColEnd;
	protected double AverageWideRightVertical;
	protected double fromDownLeft;
	protected double AvgVerticalBlockLengthInRight;
	protected double Count1VG;
	protected double AvgVGapLength;
	protected double FromDownAfterBorDown;
	protected double FromLeftBeforeBorLeft;
	protected double FromLeftAfterBorLeft;
	protected double FromLeftUp;
	public final static byte BLACK = (byte) 1;
	final static byte WHITE = (byte) 0;
	ArrayList<Feature> features;
	boolean computed = false;
	protected byte[][] digitImage;
	protected double SrB3FromUp;
	protected double SrB3FromDown;
	private ImageRowProfile[] columeProfile;
	private ImageRowProfile[] rowProfile;
	private BlockList[] rowsBlocks;
	private BlockList[] columnsBlocks;
	protected double wrb, wre, wcb;
	protected double wu = 0;
	protected double w2r = 0;
	protected double w4lft = 0;
	protected double wd = 0;
	protected double w4 = 0; // protected
	// double
	// W4=0.25*W;
	protected double im2 = 0;
//	private double fli = 0;
//	protected double fld = 0;
//	protected double fli2 = 0;
	protected double u1 = 0;

	//protected double flb = 0;
	protected double wcbe = 0;
	protected double wce;
	protected double snv;
	protected double szero;
	protected double sle;
	protected double sle3;
	protected double smallwd;
	protected double sre;
	protected double btop, bbot, bright, blft;
	protected double bplxrb, w4Alxrb, w2rAlxrb, w4lftlxrb, wCupLeft, wwulxrb,turn4,
			wwr, mbe, lbe, mxb2, lxrb2, wwlhllxrb, wwlhrlxrb;
	protected  double maxBlock, maxBlockLocation,maxBlocklxrb,maxBlockLocationlxrb,BWBWBCount,mnb,lnrb;
	protected double wwuhrlxrb,lxrbNormalized,minBlock,maxOminBlock,rowsFive,maxBlockUpperQuarter,maxBlockUpperHalf;
	protected double avgBlockSize,bplxre6,w4Blxre6;
	protected double pbArea;
	protected double sr;
	protected double mce69;
	protected double cbe;
	protected double mxce;
	protected double lxce;
	protected double mxb;
	protected double lxrb;
	protected double mxlre6, lxre6;
	double vol2;
	protected double vol_1;
	protected double vol_2;
	protected double wsbLeft, lastBleft, dlrightleft, pbTop, w2left, dmwf,
			dmwl, dmwfl, dmwll, nHgab;
	protected double nHgabLower,nVgab,imNorm,mbeUp,lbeUp,lbeNormalized,turn5left,turn5right;
	
	protected double pbLeft = 0;
	protected double pbRight = 0;
	private double wwlhl;
	private double wwlhr;
	private double mtwnr;
	private double ltwnr;
	private double wwuhr;
	private double wwuhl;
	private int lne;
	// static protected boolean
	// addArabic,addBorder,addSudden,addSround,addGeneral,addBlock,addWhite,addPercent;
	// static protected boolean addGabs,addWide,addTransition;
	static protected boolean addArabic = true;
	static protected boolean addPercent = true;
	static protected boolean addBlock = true;
	static protected boolean addGeneral = true;
	static protected boolean addSudden = true;
	static protected boolean addBorder = true;
	static protected boolean addSround = true;
	static protected boolean addWhite = true;
	static protected boolean addTransition = true;
	static protected boolean addGabs = true;
	static protected boolean addWide = true;
	static ArrayList<String> ComputedFeatures = null;
	static ArrayList<String> AllFeatures;

	public static void setAllFeatures(ArrayList<String> allFeatures) {
		AllFeatures = allFeatures;
	}

	public static int getComputedFeaturesCount() {
		if (ComputedFeatures == null)
			return AllFeatures.size();
		else
			return ComputedFeatures.size();
	}

	public static String getFeatureName(int i) {
		if (ComputedFeatures == null)
			return AllFeatures.get(i);
		else
			return ComputedFeatures.get(i);
	}

	public static void setFeaturesToCompute(ArrayList<String> featuresNames) {
		ComputedFeatures = featuresNames;
	}

	public static ArrayList<String> getComputedFeatures() {
		if (ComputedFeatures == null)
			return AllFeatures;
		else
			return ComputedFeatures;
	}

	public abstract void SaveImage(String filename);

	// {
	// try {
	// BufferedImage bi = im.getIm(); // retrieve image
	// File outputfile = new File(filename+".png");
	// ImageIO.write(bi, "png", outputfile);
	// } catch (IOException e) {
	// logger.error("could not write the message",e);
	// }
	// }
	public abstract void ReadImage(ImageReader im);

	// {
	// im.ReadBoundedBinary();
	// this.im=im;
	// digitImage = im.getBinaryBounded();
	// //
	// logger.info("-------------------------------------Reading the image of orginally ----------------------------");
	// // displayImage(im.getBinaryOrg());
	//
	// height = digitImage.length;
	// width = digitImage[0].length;
	// offset=im.getOffset();
	// computed = false;
	// }
	public ArrayList<Feature> getFeatures() {
		if (!computed) {
			computeAllFeatures();
		}
		if (ComputedFeatures == null)
			return features;
		else {
			ArrayList<Feature> Computedfeat;
			Computedfeat = new ArrayList<Feature>();
			for (int i = 0; i < ComputedFeatures.size(); i++) {
				//
				for (int k = 0; k < features.size(); k++) {
					if (ComputedFeatures.get(i).equals(
							features.get(k).getSmallName())) {
						Computedfeat.add(features.get(k));
						break;
					}
				}
			}
			// for (int i = 0; i < Computedfeat.size(); i++) {
			// logger.info("features "+Computedfeat.get(i).getSmallName()+" = "+Computedfeat.get(i).getValue());
			// }
			return Computedfeat;
		}
	}

	public double[] getFeaturesArray() {
		ArrayList<Feature> temp = getFeatures();
		double[] data = new double[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			data[i] = temp.get(i).getValue();
		}
		return data;
	}

	static public String getFeatureString() {
		String str = " { ";
		for (int i = 0; i < AllFeatures.size(); i++) {
			if (i != 0) {
				str += ",";
			}
			str += "\"" + AllFeatures.get(i) + "\"";
		}
		str += "  } ";
		return str;
	}

	static public String FeatureString() {
		String str = "";
		for (int i = 0; i < AllFeatures.size(); i++) {
			if (i != 0) {
				str += ",";
			}
			str += AllFeatures.get(i);
		}
		str += "";
		return str;
	}

	// public static void DisplayFeatureString() {
	//
	// Level TempLevel = logger.getLevel();
	// logger.setLevel(Level.INFO);
	//
	// logger.info(" number of feature computed is " + AllFeatures.size());
	// String str=getFeatureString();
	// logger.info(" Features are " + str);
	//			
	// logExample.trace( " Features are " + str);
	//
	// logExample.trace(
	// "####################################################################");
	//			
	// logger.setLevel(TempLevel);
	//
	// }
	protected abstract int computeAllFeatures();

	// public static abstract void loadAllFeatureArray();
	/**
	 * 
	 */
	public String toString() {
		String str = "";
		String newline = System.getProperty("line.separator");
		// str+=" Label is = "+ Label +"  offset is  "+offset+"   width  "+width
		// + " and height " + height;
		// str+=newline;
		// str+=" Count of white = "+
		// countWhite+"  count of black is  "+countBlack ;
		// str+=newline;
		for (int i = 0; i < features.size(); i++) {
			str += " Feature " + features.get(i).getSmallName() + "  = "
					+ features.get(i).getValue();
			str += newline;
		}
		return str;
	}

	public ImageBase() {
	}

	protected void computeHW() {
		// width = digitImage.length;
		// height = digitImage[0].length;
		heightoverwidth = (double) height / (double) width;
		widthoverheight = (double) width / (double) height;
		logger.trace("   heightoverwidth  " + heightoverwidth);
	}

	protected void computeWsb() {
		// digitImage
		// loop on the image to calculate the dimension
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				// i need to count the white pixels
				if (digitImage[i][j] == WHITE) // if they are a like
				{
					if (checkSuroundByBlack(i, j)) {
						wsb++;
					}
				}// pixel is white
			}// for loop in column j
		}// for loops on rowsBlocks i
		logger.trace("  white surroned by black as number is " + wsb
				+ "  divide by number of white " + countWhite);
		CountWSB = (int) wsb;
		wsb /= ((double) countWhite);
	}

	private boolean checkSuroundByBlack(int i, int j) {
		// now i need to check if pixel i,j is surrounded by black pixel in all
		// 4 direction
		int suroundby = 0;
		// check up.
		for (int itemp = i; itemp > -1; itemp--) {
			if (digitImage[itemp][j] == BLACK) {
				suroundby++;
				break;
			}
		}
		// check down
		for (int itemp = i; itemp < digitImage.length; itemp++) {
			if (digitImage[itemp][j] == BLACK) {
				suroundby++;
				break;
			}
		}
		// check right
		for (int jtemp = j; jtemp < digitImage[i].length; jtemp++) {
			if (digitImage[i][jtemp] == BLACK) {
				suroundby++;
				break;
			}
		}
		// check left
		for (int jtemp = j; jtemp > -1; jtemp--) {
			if (digitImage[i][jtemp] == BLACK) {
				suroundby++;
				break;
			}
		}
		// if surronded by black from more than 3 direction then return true
		if (suroundby > 3) {
			return true;
		}
		return false;
	}

	protected void computeLhg() {
		boolean inBlack = false;
		double MaxLength = 0;
		int MaxLengthLocation = 0;
		int startj, endj;
		double length;
		int nGaps = 0;
		// loop on the image to calculate the dimension
		for (int r = 0; r < digitImage.length; r++) {
			startj = 0;
			endj = 0;
			for (int j = 0; j < digitImage[r].length; j++) {
				// i need to count the white pixels
				if (digitImage[r][j] == BLACK) // if they are a like
				{
					if (inBlack) {
						// do nothing
					} else { // pereviously was not in black but now start in a
						// new black zone
						// logger.info("  black pixel first in a block ");
						inBlack = true;
						endj = j;
						if (startj == 0) { // pervious was wall and
							// do nothing
						} else {
							length = endj - startj;
							// logger.info(" the length of gap is "+length+"  in "+i);
							if (MaxLength < length) {
								MaxLength = length;
								MaxLengthLocation = r;
							}
						}
					}
				}// if black ....
				else {
					if (inBlack) // previously in black // and beginign of gap
					{
						startj = j;
						inBlack = false;
					}
				}
			}
		}
		logger.trace(" computing largest horizontal gappp " + MaxLength
				+ "  location of " + MaxLengthLocation);
		lhg = MaxLength / width;
		lhgi = MaxLengthLocation / height;
	}

	protected void computeLvg() {
		boolean inBlack = false;
		double MaxLength = 0;
		int MaxLengthLocation = 0;
		int starti, endi;
		double length;
		double countRowGaps = 0;
		double tempcount = 0;
		double totalCountOfGaps = 0;
		double avgVGapLenth = 0;
		for (int j = 0; j < digitImage[0].length; j++) {
			starti = 0;
			endi = 0;
			// loop on the image to calculate the dimension
			for (int i = 0; i < digitImage.length; i++) {
				// i need to count the white pixels
				if (digitImage[i][j] == BLACK) // if they are a like
				{
					if (inBlack) {
						// do nothing
					} else { // pereviously was not in black but now start in a
						// new black zone
						inBlack = true;
						;
						endi = i;
						if (starti == 0) { // pervious was wall and
							// do nothing
						} else {
							length = endi - starti;
							totalCountOfGaps++;
							avgVGapLenth += length;
							tempcount++;
							// logger.info(" the length of gap is "+length+"  in "+j);
							if (MaxLength < length) {
								MaxLength = length;
								MaxLengthLocation = j;
							}
						}
					}
				}// if black ....
				else {
					if (inBlack) // previously in black // and beginign of gap
					{
						starti = i;
						inBlack = false;
					}
				}
			}// after the column end
			if (tempcount == 1) {
				countRowGaps++;
				tempcount = 0;
			}
		}
		logger.trace(" computing largest vertical  gappp " + MaxLength
				+ "  location of " + MaxLengthLocation);
		lvg = MaxLength / height;
		lvgi = MaxLengthLocation / width;
		Count1VG = (double) countRowGaps / width;
		if (totalCountOfGaps == 0) {
			totalCountOfGaps = 1;
		}
		AvgVGapLength = (avgVGapLenth / totalCountOfGaps) / height;
	}

	protected void CountSurrondsNumber() {
		int test;
		countSurr1 = 0;
		countSurr2 = 0;
		countSurr3 = 0;
		countSurr4 = 0;
		countSurr3 = 0;
		countSurr4 = 0;
		for (int i = 0; i < surrondLabelImage.length; i++) {
			for (int j = 0; j < surrondLabelImage[i].length; j++) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 1) {
					countSurr1++;
				}
				if (test == 2) {
					countSurr2++;
				}
				if (test == 3) {
					countSurr3++;
				}
				if (test == 4) {
					countSurr4++;
				}
			}
		}
		countSurr1 /= (double) countWhite;
		countSurr2 /= (double) countWhite;
		countSurr3 /= (double) countWhite;
		countSurr4 /= (double) countWhite;
	}

	protected void countWhiteBlockTillBlack() {
		byte test;
		int count;
		double halfw = width / 2.0;
		// countSurr2=0 ;countSurr3=0;countSurr4=0;
		// countSurr3=0;
		// countSurr4=0;
		// count from up
		count = 0;
		for (int c = 0; c < digitImage[0].length; c++) {
			for (int r = 0; r < digitImage.length; r++) {
				// from left to right from up to down
				test = digitImage[r][c];
				if (test == BLACK) {
					break;
				} else {
					count++;
				}
			}
		}
		fromUp = count;
		count = 0;
		int countleft = 0;
		// ///////////////from down
		for (int c = 0; c < digitImage[0].length; c++) {
			for (int r = digitImage.length - 1; r > 0; r--) {
				// from left to right from up to down
				test = digitImage[r][c];
				if (test == BLACK) {
					break;
				} else {
					count++;
					if (c < halfw) {
						countleft++;
					}
				}
			}
		}
		fromDown = count;
		fromDownLeft = countleft;
		count = 0;
		// ///from left
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[0].length; j++) {
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				test = digitImage[i][j];
				if (test == BLACK) {
					break;
				} else {
					count++;
				}
			}
		}
		fromLeft = count;
		count = 0;
		// ///from right
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = digitImage[0].length - 1; j > 0; j--) {
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				test = digitImage[i][j];
				if (test == BLACK) {
					break;
				} else {
					count++;
				}
			}
		}
		fromRight = count;
		fromDown /= (double) countWhite;
		fromUp /= (double) countWhite;
		fromLeft /= (double) countWhite;
		fromRight /= (double) countWhite;
		fromDownLeft /= (double) countWhite;
		double maxW = fromUp;
		LocationOfMaxWhite = 1;
		if (maxW < fromLeft) {
			maxW = fromLeft;
			LocationOfMaxWhite = 2;
		}
		if (maxW < fromRight) {
			maxW = fromRight;
			LocationOfMaxWhite = 3;
		}
		if (maxW < fromDown) {
			maxW = fromDown;
			LocationOfMaxWhite = 4;
		}
		if (maxW < wsb) {
			maxW = wsb;
			LocationOfMaxWhite = 5;
		}
	}

	protected void computeLargestHorizentalBlackBlock() {
		byte test;
		boolean inBlack = false;
		int count;
		count = 0;
		MaxHorizentalBlackBlockLength = 0;
		double MinLength = width;
		// MaxLengthRowLocation;
		double MinLengthRowLocation;
		double countBlockInImage = 0;
		double avgeBlockLength = 0;
		double averageBlackContinuesWidth = 0;
		MaxNumberOfHorizontalBlackBlockCount = 0;
		int tempBlockCount = 0;
		MaxNumberOfHorizontalBlackBlockCountRowLocation = 0;
		for (int r = 0; r < digitImage.length; r++) {
			for (int c = 0; c < digitImage[r].length; c++) {
				// from left to right from up to down
				test = digitImage[r][c];
				if (test == BLACK) {
					if (!inBlack) {// if start of black block
						inBlack = true; // in block
						tempBlockCount++;
					}
					count++;
				}// finish black pixel
				else {
					// i am white pixel
					if (inBlack) // was in black
					{
						countBlockInImage++;
						avgeBlockLength += count;
						// the previous count is measure if min or max
						if (count > MaxHorizentalBlackBlockLength) {
							MaxHorizentalBlackBlockLength = count;
							MaxHorizentalBlackBlockLengthRowLocation = r;
						}
						if (count < MinLength) {
							MinLength = count;
							MinLengthRowLocation = r + 1;
						}
						// now i do not need the count and i need to make to
						// reset it to zero
						//
						count = 0;
						inBlack = false;
					} else { // same as before white pixel (gap or at border)
						// do nothing.
					}
					// the previuos count is the count of the last black block .
				}
			}
			if (inBlack) // was in black
			{
				countBlockInImage++;
				avgeBlockLength += count;
				// the previous count is measure if min or max
				if (count > MaxHorizentalBlackBlockLength) {
					MaxHorizentalBlackBlockLength = count;
					MaxHorizentalBlackBlockLengthRowLocation = r;
				}
				if (count < MinLength) {
					MinLength = count;
					MinLengthRowLocation = r;
				}
				// now i do not need the count and i need to make to reset it to
				// zero
				//
				count = 0;
				inBlack = false;
			}
			if (tempBlockCount > MaxNumberOfHorizontalBlackBlockCount) {
				MaxNumberOfHorizontalBlackBlockCount = tempBlockCount;
				MaxNumberOfHorizontalBlackBlockCountRowLocation = r;
			}
			tempBlockCount = 0;
		}
		// fromUp=count;
		logger.debug("	MaxHorizentalBlackBlockLength = "
				+ MaxHorizentalBlackBlockLength + "  width" + width);
		logger.debug("MaxHorizentalBlackBlockLengthRowLocation = "
				+ MaxHorizentalBlackBlockLengthRowLocation);
		MaxHorizentalBlackBlockLength /= width;
		MaxHorizentalBlackBlockLengthRowLocation /= height;
		MaxNumberOfHorizontalBlackBlockCountRowLocation /= height;
		AvgHorizentalBlackBlockLength = ((double) avgeBlockLength / (double) countBlockInImage)
				/ width;
	}

	protected void computeLargestVerticalBlackBlock() {
		byte test;
		boolean inBlack = false;
		int count;
		count = 0;
		MaxVerticalBlackBlockLength = 0;
		double MinLength = width;
		MaxVerticalBlackBlockLengthColLocation = 0;
		double MinLengthColLocation;
		double averageBlackContinuesWidth = 0;
		MaxVerticalblocksCount = 0;
		int tempBlockCount = 0;
		MaxVerticalblocksCountColLocation = 0;
		double avgVerticalBlockSizeInRight = 0;
		double halfw = width / 2.0;
		int countBlock = 0;
		for (int c = 0; c < digitImage[0].length; c++) {
			for (int r = 0; r < digitImage.length; r++) {
				// from left to right from up to down
				test = digitImage[r][c];
				if (test == BLACK) {
					if (!inBlack) {// if start of black block
						inBlack = true; // in block
						tempBlockCount++;
					}
					count++;
				}// finish black pixel
				else {
					// i am white pixel
					if (inBlack) // was in black
					{// the previous count is measure if min or max
						if (c > halfw) {
							avgVerticalBlockSizeInRight += count;
							countBlock++;
						}
						if (count > MaxVerticalBlackBlockLength) {
							MaxVerticalBlackBlockLength = count;
							MaxVerticalBlackBlockLengthColLocation = c + 1;
						}
						if (count < MinLength) {
							MinLength = count;
							MinLengthColLocation = c + 1;
						}
						// now i do not need the count and i need to make to
						// reset it to zero
						//
						count = 0;
						inBlack = false;
					} else { // same as before white pixel (gap or at border)
						// do nothing.
					}
					// the previuos count is the count of the last black block .
				}
			}
			if (inBlack) // was in black
			{// the previous count is measure if min or max
				if (c > halfw) {
					avgVerticalBlockSizeInRight += count;
					countBlock++;
				}
				if (count > MaxVerticalBlackBlockLength) {
					MaxVerticalBlackBlockLength = count;
					MaxVerticalBlackBlockLengthColLocation = c + 1;
				}
				if (count < MinLength) {
					MinLength = count;
					MinLengthColLocation = c + 1;
				}
				// now i do not need the count and i need to make to reset it to
				// zero
				//
				count = 0;
				inBlack = false;
			}
			if (tempBlockCount > MaxVerticalblocksCount) {
				MaxVerticalblocksCount = tempBlockCount;
				MaxVerticalblocksCountColLocation = c + 1;
			}
			tempBlockCount = 0;
		}
		// fromUp=count;
		MaxVerticalBlackBlockLength /= height;
		MaxVerticalBlackBlockLengthColLocation /= width;
		MaxVerticalblocksCountColLocation /= width;
		AvgVerticalBlockLengthInRight = avgVerticalBlockSizeInRight / height;
		// now i need to calculat this image
	}

	protected void computeCountWhiteWithBorder() {
		int countlbbl = 0;
		int countlabl = 0;
		int countdabd = 0;
		// from left
		for (int r = 0; r < digitImage.length; r++) {
			for (int c = 0; c < digitImage[0].length; c++) {
				if (digitImage[r][c] == BLACK) {
					// go next row.
					break;
				} else {
					// add this white to correct count
					if (r <= bLs) {
						countlbbl++;
					} else if (r >= bLe) {
						countlabl++;
					}
				}
			}
		}
		// now from down ...
		for (int c = digitImage[0].length - 1; c > 0; c--) {
			for (int r = digitImage.length - 1; r > 0; r--) {
				if (digitImage[r][c] == BLACK) {
					// go next row.
					break;
				} else {
					// add this white to correct count
					if (c >= bDe) {
						countdabd++;
					}
					// else if (r>=bLe){
					// countlabl++;
					// }
				}
			}
		}
		FromDownAfterBorDown = (double) countdabd / (double) countWhite;
		FromLeftBeforeBorLeft = (double) countlabl / (double) countWhite;
		FromLeftAfterBorLeft = (double) countlbbl / (double) countWhite;
	}

	private void arCountWhiteFeatures() {
		// /--------------------------------------------------------------------------------------------------------------------------------
		// / COUNTING BLACK AND WHITE
		// //------------------------------------------------------------------------------------------------------------------------
		double half_h = height / 2.0;
		double width_4 = width / 4.0;
		double half_w = width / 2.0;
		pbLeft = 0;
		pbRight = 0;
		double count_5 = 0;
		double countRight = 0;
		double countLeft = 0;
		// double twoThrid_h=height *(2.0/3.0);
		wsbLeft = 0;
		w2left = 0;
		pbTop = 0;
		wu = 0;
		im2 = 0;
		wd = 0;
		w2r = 0;
		w4 = 0;
		w4lft = 0;
		int lrb, lre, lcb, lce;
		btop = 0;
		bbot = 0;
		bright = 0;
		blft = 0;
		bplxrb = 0;
		w4Alxrb = 0;
		w2rAlxrb = 0;
		w4lftlxrb = 0;
		wCupLeft = 0;
		wcbe=0;

		double h_5 = height / 5.0;
		int CountWhiteWCBE = 0;
		int countBRows=110;
		rowsFive=0;
		
	bplxre6=0;
	w4Blxre6=0;
		for (int ri = 0; ri < height; ri++) {
			
			if (countBRows<=5 ){
				rowsFive++;
			}
			countBRows=0;
			for (int cj = (int) width - 1; cj >= 0; cj--) {
				// /////////////// count the numberss...
				if (ri < h_5) {
					count_5++;
				}
				if (cj <= half_w) {
					countLeft++;
				} else {
					countRight++;
				}
				// /////////////now check image pixel
				if (digitImage[ri][cj] == BLACK) {
					countBRows++;
					
					
					if (ri < h_5) {
						pbTop++;
					}
					if (cj < half_w) {
						pbLeft++;
					} else {
						pbRight++;
					}
					if (cj < NUMBER_FIRST) {
						blft++;
					}
					// bbot=sum(sum(m(H-2:H,:)))/3;
					// btop=sum(sum(m(1:3,:)))/3;
					if (cj > (width - 1 - NUMBER_FIRST)) {
						bright++;
					}
					if (ri < NUMBER_FIRST) {
						btop++;
					}

					if (ri > (height - 1 - NUMBER_FIRST)) {
						bbot++;
					}
					if (ri < lxrb) {

						bplxrb++;
					}
					if (ri<lxre6){
						bplxre6++;
					}

				} else if (digitImage[ri][cj] == WHITE) // if they black chekd
														// if
				// this is
				// first in row
				{
					lrb = rowProfile[ri].begin; // row begin of black
					lre = rowProfile[ri].end; // row end of blac
					lcb = columeProfile[cj].begin; // column begin of black
					lce = columeProfile[cj].end; // column end of black ...

					if (ri < half_h && cj < half_w) {
						CountWhiteWCBE++;
					}
					// see the condition to corret
					// if(j>lrb(i)&j<lre(i)&i<lcb(j))
					// wu=wu+1;
					// im=i;
					// if column is > row begin, and less than end
					// (surrone from left and down and right from by black
					// (white from right)//
					// if(j>lrb(i)&j<lre(i)&i<lcb(j))wu=wu+1;im=i;
					if (cj > lrb && cj < lre && ri < lcb) {
						wu++; // sround from up,down and right by black
						im2 = ri + 1; // / last row of with
					}
					// column > begin and c les than end
					// row > end column
					// surrond left, right and from up by black (white 8)
					// elseif(j>lrb(i)&j<lre(i)&i>lce(j))wd=wd+1;
					if (cj > lrb && cj < lre && ri > lce) {
						wd++;
					}
					// / sround from left, up and down (white from right) white
					if (cj > lre && ri > lcb && ri < lce) {
						w2r++;
						if (ri > lxrb) {
							w2rAlxrb++;
						}

					}
					// elseif(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))
					// w4=w4+1;
					// c > bgiin
					// surrond from up, down, left, righ (surrond by 4)
					if (cj > lrb && cj < lre && ri > lcb && ri < lce) {

						w4++;
						if (ri < half_h && cj < half_w) {
							wcbe++;
						}

						if (cj < half_w) {
							wsbLeft++;
						}

						if (ri > lxrb) {
							w4Alxrb++;
						}
						if (ri<	lxre6){
						w4Blxre6++;
						}
						
					}
					// elseif(i>lcb(j)&i<lce(j)&j<lrb(i))
					// w4lft=w4lft+1;
					// surrond by up and right by black (white from left ) white
					// 4
					if (ri > lcb && ri < lce && cj < lrb) {
						w4lft++;
						if (ri > lxrb) {
							w4lftlxrb++;
						}
					}
					// surrond by up , left, only
					if (cj > lre && ri > lce) {
						// after last
						w2left++;
					}
					// surrond by down , left, only
					if (ri < lcb && cj < lrb) {
						// after last
						wCupLeft++;
					}
				}
			}
		}
		
		imNorm=im2;
		
		if (logger.isTraceEnabled()) {
			int rt = 0;
			int ct = 0;
			for (int i = 0; i < digitImage.length; i++) {
				ct = 0;
				for (int j = 0; j < digitImage[i].length; j++) {
					if (digitImage[i][j] == BLACK) {
						ct++;
					}

				}
				logger.trace(" In row   " + i + "  number of white is "
						+ (digitImage[i].length - ct) + "  number of black "
						+ ct);
				ct = 0;
			}

			logger
					.trace("----------------------------------------------------------------------");
			for (int j = 0; j < digitImage[0].length; j++) {
				rt = 0;
				for (int i = 0; i < digitImage.length; i++) {
					if (digitImage[i][j] == BLACK) {
						rt++;
					}

				}
				logger.trace("  in column  " + j + "  number of white is "
						+ (digitImage.length - rt) + "  number of black " + rt);
				rt = 0;
			}

			logger.trace("  pbTop   " + pbTop);
			logger.trace(" pbLeft  " + pbLeft);
			logger.trace("  pbRight  " + pbRight);
			logger.trace(" count right   " + countRight);
			logger.trace(" count left    " + countLeft);
			logger.trace(" count white  " + countWhite);
			logger.trace(" count white for wcbe.. " + CountWhiteWCBE);
			logger.trace(" w4  " + w4);
			logger.trace(" wu  " + wu);
			logger.trace(" wd  " + wd);
			logger.trace(" im  " + im2);
			logger.trace(" imNormalized  " + imNorm);
			logger.trace("  wsbLeft  " + wsbLeft);
			logger.trace("  w4lft    " + w4lft);
			logger.trace("  w2left  " + w2left);
			logger.trace("  wcbe  " + wcbe);
			logger.trace(" btop   " + btop);
			logger.trace("   bbot " + bbot);
			logger.trace("   blft     " + blft);
			logger.trace("   bright   " + bright);
			logger.trace(" bplxrb " + bplxrb);
			logger.trace("  w4Alxrb " + w4Alxrb);
			logger.trace("  w2rAlxrb,	 " + w2rAlxrb);
			logger.trace("   wCupLeft   " + wCupLeft);
			logger.trace("  w4lftlxrb   " + w4lftlxrb);
			logger.trace("  	bplxre6    "+	bplxre6);
			logger.trace("  w4 before lxre6 "+w4Blxre6);
		}
		// /logger.trace("        " + );
	
		bplxrb /= lxrb;
		bplxrb /= width;
		
		if (lxre6!=0){
			bplxre6/=lxre6;
			bplxre6/=width;
		}
		 
		imNorm/= height;
			
		btop /= (double) NUMBER_FIRST;
		bbot /= (double) NUMBER_FIRST;
		blft /= (double) NUMBER_FIRST;
		bright /= (double) NUMBER_FIRST;
		
		if (AppDefaults.NormalizeFeatures) {
	
			w4 /= (double) countWhite;
			w2r /= (double) countWhite;
			wu /= (double) countWhite;
	
			w4lft /= (double) countWhite;// wu = 0;
			wCupLeft /= (double) countWhite;//	
			// im2 = 0;
			// wd = 0;
			// w2r = 0;
			// w4 = 0;
			// w4lft = 0;
			// int lrb, lre, lcb, lce;)countWhite;
			w4Alxrb /= (double) (height - lxrb);// after lxrb 
			w4Alxrb /= width;
			w2rAlxrb /= (double) (height - lxrb);
			w2rAlxrb /= width;
			w4lftlxrb /= (double) (height - lxrb);
			w4lftlxrb /= width;
		w4Blxre6/=(double) (lxre6);		// before lxre6 
		w4Blxre6/=width;
		
			wd /= (double) countWhite;
			;
			im2 /= height;

			if (CountWhiteWCBE != 0)
				wcbe /= (double) CountWhiteWCBE;
			pbRight /= countRight;
			pbLeft /= countLeft;
		}

		pbTop /= count_5;
		pbTop /= width;
		
		if (heightoverwidth<=1){
			
			rowsFive=rowsFive/height;
		}
		else {
			rowsFive=-1;
		}
		
		
	}

	private int[] arRowProfileFeatures() {
		// /--------------------------------------------------------------------------------------------------------------------------------
		// / ROW AND COLUMN PROFILE (TRANSITION AND SOME WIDE)
		// //------------------------------------------------------------------------------------------------------------------------
		wrb = 0;
		wre = 0;
		double half_h = height / 2.0;
		//wcbe = 0;
		wwlhl = 0;
		wwlhr = 0;
		mtwnr = 0;
		ltwnr = 0;
		wwuhr = 0;
		wwuhl = 0;
		wwulxrb = 0;
		wwuhrlxrb=0;
		wwr = 0;

		int mne = 9999;
		lne = -1;

		double lre6;
		int[] wdbe = new int[rowProfile.length];

		mtwnr = -99;
		ltwnr = 0;
		double mtwnr2 = -99;
		double ltwnr2 = 0;
		double lre2;
		lxre6 =-1;
		for (int i = 0; i < rowProfile.length; i++) {

			if (i < (rowProfile.length - 1) ) {
              if (! rowProfile[i].empty &! rowProfile[i+1].empty ){
				// get differene bettwen rowsBlocks...
				lre6 = rowProfile[i].end - rowProfile[i + 1].end;
				lre2 = rowProfile[i + 1].end - rowProfile[i].end;
				if (i==0|| 				lxre6 ==-1) {
					mxlre6 = lre6;
					lxre6 = i;
					mtwnr = lre6;
					ltwnr = i;
					mtwnr2 = lre2;
					ltwnr2 = i;
				}
				if (mxlre6 < lre6) {
					mxlre6 = lre6;
					lxre6 = i + 1;
				}
				if (mtwnr > lre6) {
					mtwnr = lre6;
					ltwnr = i;
				}
				if (mtwnr2 < lre2) {
					mtwnr2 = lre2;
					ltwnr2 = i;
				}
				// trying with
				//min...
              }
			}
			// wrb=sum(lrb-1);wrb=wrb/H;
			// wre=sum(W-lre);wre=wre/H;
			// white from left (till begins of black rowsBlocks..)
			wrb += rowProfile[i].begin;
			// white from right (till end of black rowsBlocks)
			wre += (width - (rowProfile[i].end + 1));
			// Black width at each row
			if (i == 0) {
				mbe = rowProfile[i].end;
				lbe = i;
				mbeUp = rowProfile[i].end;
				lbeUp = i;
				

			}
			if (mbe > rowProfile[i].end) {
				mbe = rowProfile[i].end;
				lbe = i;
			}
			if (i<half_h){
				if (mbeUp > rowProfile[i].end) {
				mbeUp = rowProfile[i].end;
				lbeUp = i;
				}
			}

			wdbe[i] = (rowProfile[i].end - rowProfile[i].begin) + 1;
			// maximum transiiton from left (6,9)(in the first half of image
			// location of maximum transition
			// lrb1=lrb(1:H3-1);lrb2=lrb(2:H3);lrb3=lrb2-lrb1;
			// [mxb,lxrb]=max(lrb3);
			// if (i>0){ //if (i <= height_3 && i > 0) {
			// tempdif = rowProfile[i].begin - rowProfile[i - 1].begin;
			// if (lxrb == -1) {
			// mxb = tempdif;
			// lxrb = i;
			// }
			// if (mxb < tempdif) {
			// mxb = tempdif;
			// lxrb = i;
			// }
			// }
			if (i <= half_h)// [mne,lne]=min(lre(1:H2));
			{
				if (lne == -1) {
					mne = rowProfile[i].end;
					lne = i;
				}
				// in the uppser half
				// shortest row and location of the shortest row..
				if (mne > rowProfile[i].end) { // get minimum
					lne = i;
					mne = rowProfile[i].end;
				}
			} // lre4=lre(1:H_1);lre5=lre(2:H);lre6=lre4-lre5;
			// [mxlre6,lxre6]=max(lre6);
			if (i >= lxrb2) { // lower half
				wwlhllxrb += rowProfile[i].begin;
				wwlhrlxrb += width - 1 - rowProfile[i].end;
			}
			if (i >= half_h) { // lower half
				wwlhl += rowProfile[i].begin;
				wwlhr += width - 1 - rowProfile[i].end;
			} else { // upper half

				wwuhl += rowProfile[i].begin;
				wwuhr += width - 1 - rowProfile[i].end;

			}
			if (i < lxrb) {
				wwulxrb += rowProfile[i].begin;
				wwuhrlxrb += width - 1 - rowProfile[i].end;
			}
			wwr += width - 1 - rowProfile[i].end;

		}

		mbe = width -1- mbe;
		mbeUp=width-1-mbeUp;
		
		lbeNormalized=lbe/height;
		if (logger.isTraceEnabled()) {
			logger.trace(" wrb  " + wrb);
			logger.trace(" wre  " + wre);
			logger.trace("  lne    " + lne);
			// logger.trace("  mxb   " + mxb );
			// logger.trace("  lxrb   " + lxrb );
			logger.trace(" 	lxre6   " + lxre6);
			logger.trace("  mxlre6  " + mxlre6);
			logger.trace("  wdbe  " + lib.ToString(wdbe));

			logger.trace("   wwlhl = " + wwlhl);
			logger.trace("   wwlhr = " + wwlhr);
			logger.trace("   wwuhl = " + wwuhl);
			logger.trace("  wwuhr = " + wwuhr);

			logger.trace("	wwulxrb   " + wwulxrb);
			logger.trace(" wwr  " + wwr);

			logger.trace("  mtwnr  1 " + mtwnr);
			logger.trace("  ltwnr  1 " + ltwnr);

			logger.trace("  mtwnr  2  " + mtwnr2);
			logger.trace("  ltwnr  2 " + ltwnr2);

			logger.trace("  mbe " + mbe);
			logger.trace("  lbe  " + lbe);
			logger.trace("  wwlhllxrb   " + wwlhllxrb);
			logger.trace("  wwlhrlxrb   " + wwlhrlxrb);

		}

		mtwnr = Math.abs(mtwnr);
		if (AppDefaults.NormalizeFeatures) {
			// lxrb/=height;
			lxre6 /= height;
			ltwnr /= height;
			mbe /= width;
			lbe /= height;
			mtwnr /= width;
			// mxb/=width;
			mxlre6 /= width;
			// wrb /= height; // (white from left / hight)
			wrb /= (double) countWhite;
			;
			// wre /= height;
			wre /= (double) countWhite;
		}

		wwlhl /= half_h;
		wwlhl /= width;
		wwlhr /= half_h;
		wwlhr /= width;

		wwlhrlxrb /= (height - lxrb2);
		wwlhrlxrb /= width;

		wwlhllxrb /= (height - lxrb2);
		wwlhllxrb /= width;

		wwulxrb /= lxrb;
		wwulxrb /= width;

		wwr /= height;
		wwr /= width;

		wwuhl /= half_h;
		wwuhl /= width;

		wwuhr /= half_h;
		wwuhr /= width;

		return wdbe;

	}

	private void arColumnProfileFeatuers() {
		double half_w = width / 2.0;
		wce = 0.0;
		wcb = 0;
		int countW = 0;
		mce69 = 0.0;
		cbe = 0.0;
		// wcbe = 0.0;
		mxce = 99999;
		lxce = -1;

		int wcbe2 = 0;
		int difflce;
		for (int i = 0; i < columeProfile.length; i++) {
			// wcb=sum(lcb-1);wcb=wcb/W;
			// wce=sum(H-lce);wce=wce/W;
			// if (columeProfile[i].end>0)
			// white down , column end sum
			wce += (height - (columeProfile[i].end + 1));
			// white from up
			wcb += columeProfile[i].begin;
			if (i <= half_w) {
				// mce69=mean(lce(1:W2));
				// cbe=mean(lce(1:W2)-lcb(1:W2))/H;
				mce69 += columeProfile[i].end + 1;
				cbe += (columeProfile[i].end - columeProfile[i].begin) + 1;
				countW++;
			}
			// for j=1:W2
			// if (lcb(j))wcbe=wcbe+sum(~m(lcb(j):lce(j),j));end
			// end
			// lce1=lce(1:W-1);lce2=lce(2:W);lce3=lce2-lce1;
			// [mxce,lxce]=max(lce3);lxce=lxce/lre(H);
			if (i < columeProfile.length - 1) {
				
				if (!columeProfile[i].empty & !columeProfile[i+1].empty){
				difflce = columeProfile[i + 1].end - columeProfile[i].end;
				if (lxce == -1) {
					mxce = difflce;
					lxce = i; 
				}

				if (mxce < difflce) {
					mxce = difflce;
					lxce = i;
				}
				}
			}
			if (i <= half_w) {

				// for j=1:W2
				// if (lcb(j))wcbe=wcbe+sum(~m(lcb(j):lce(j),j));end
				// end
				if (columeProfile[i].begin >= 0) {
					int tempsum = 0;
					int rb, re;
					int c = i;

					for (int r = columeProfile[c].begin; r < columeProfile[c].end; r++) {
						rb = rowProfile[r].begin;
						re = rowProfile[r].end;
						if (digitImage[r][c] == WHITE) {
							if (c > rb && c < re) {
								tempsum++;
							}
						}

					}// column begin....
					wcbe2 += tempsum;
				}// half width
			}// / only in the fist half width...
			// will note need so do not compute
		}
		if (logger.isTraceEnabled()) {
			logger.trace(" wce  " + wce);
			logger.trace(" wcb  " + wcb);
			logger.trace(" wcbe2  ************** " + wcbe2);
			logger.trace("	cbe  " + cbe);
			logger.trace(" 	mce69   " + mce69);
			logger.trace(" 	lxce   " + lxce);
			logger.trace(" 	mxce   " + mxce);
		}

		// lxce=lxce/lre(H);---
		// if(rowProfile[rowProfile.length - 1].end!=0)
		// lxce = lxce / (double) rowProfile[rowProfile.length - 1].end;

		mce69 /= (double) countW;
		cbe /= (double) countW;

		if (AppDefaults.NormalizeFeatures) {
			mxce /= height;
			lxce /= width;

			// wce /= width;
			// wcb /= width;
			wce /= (double) countWhite;
			wcb /= (double) countWhite;
			mce69 /= height;
			cbe /= height;
		}

	}

	private void arBlockFeatures() {
		// /--------------------------------------------------------------------------------------------------------------------------------
		// / NEW FEATUERSSS...........
		// //------------------------------------------------------------------------------------------------------------------------
double h_4=height/4.0;
double h_2=height/2.0;
		double maxgab = 0, maxgabl = -1;
		double firstgabl = -1, lastgabl = -1, firstgab = 0, lastgab = 0;
		double gab = 0;
		int countGap = 0;
		double mgab = 0;
		int countRows;
		
		nHgabLower=0;
		nVgab=0;
  maxBlock=-888;
  maxBlockUpperQuarter=-8888;
	maxBlockUpperHalf=-9999;
   minBlock=99;
//		
  maxBlockLocation=0;
  maxBlocklxrb=-99;

  BWBWBCount=0;
 maxBlockLocationlxrb=-99;
 
 avgBlockSize=0;
 
		// now for the gabs features
		for (int i = 0; i < rowsBlocks.length; i++) {
			// there is computing size...
			if (rowsBlocks[i].size()>0){
				// this rows will have black blocks...
		      // i want to detect the combination 
				//BWBWB  ==> three black 
				if (rowsBlocks[i].size()>2){
			 
					BWBWBCount++;
					
				}
				
				
				// now get the max maxblokc
				for (int j = 0; j < rowsBlocks[i].size(); j++) {
					double blockSize=rowsBlocks[i].get(j).count;
					if (blockSize< minBlock){
						 minBlock=blockSize;
						
					}	
				if(i<h_4){
					if (blockSize>maxBlockUpperQuarter){
						maxBlockUpperQuarter=blockSize;
				 
					}
				}
				
				if(i<h_2){
					if (blockSize>maxBlockUpperHalf){
						maxBlockUpperHalf=blockSize;
				 
					}
				}
					if (blockSize>maxBlock){
						maxBlock=blockSize;
						maxBlockLocation=i;
					}
					if (i>lxrb){
						
						if (blockSize>maxBlocklxrb){
							maxBlocklxrb=blockSize;
							maxBlockLocationlxrb=i;
						}
				
				}	
				}
			 
				
			
			}
			if (rowsBlocks[i].size()>0){
				
						for (int j = 0; j < rowsBlocks[i].size() ; j++) {
								avgBlockSize+= rowsBlocks[i].get(j).count;
						}
			}
			
			if (rowsBlocks[i].size() > 1) {
				mgab = 0;
				// theres is two black blocks in the array
				// get the maximm gain in the array...
				for (int j = 0; j < rowsBlocks[i].size() - 1; j++) {
					Block temp = rowsBlocks[i].get(j);
					Block temp_1 = rowsBlocks[i].get(j + 1);
					gab = temp_1.start - temp.end - 1;
					// this is the size of gap....
					if (gab > mgab) {
						mgab = gab;
					}
				}
				if (mgab > 0) {
					
					countGap++;
					if(i>h_2){
						
						nHgabLower++;
					}
					lastgab = mgab;
					lastgabl = i;
					if (firstgabl == -1) {
						firstgabl = i;
						firstgab = mgab;
					}
					// now test the mgab for our main features...
					if (mgab > maxgab) {
						maxgab = mgab;
						maxgabl = i;
					}// if max gab
				} // if gab exist
			}// if more than one bloock..
		}
		dlrightleft = 0;
		if (columeProfile.length > 0) {
			dlrightleft = columeProfile[columeProfile.length - 1].end
					- columeProfile[0].end;
		}
		
		maxOminBlock=maxBlock/minBlock;
		avgBlockSize=(double)avgBlockSize/(double)rowsBlocks.length;
		
		if (logger.isTraceEnabled()) {
			logger.trace("	nHgab  " + nHgab);
			logger.trace("dmwf   " + dmwf);
			logger.trace("	dmwl   " + dmwl);
			logger.trace("		dlrightleft  " + dlrightleft);
			logger.trace("  BWBWBCount "+BWBWBCount);
			logger.trace( " maxBlock "+maxBlock);	
			logger.trace( " maxBlockLocation  "+maxBlockLocation);
				logger.trace( " maxBlocklxrb  "+maxBlocklxrb);
				logger.trace( " maxBlockLocationlxrb  "+maxBlockLocationlxrb);
				logger.trace("			maxOverMin  "+			maxOminBlock);
			
					logger.trace("			maxBlockUpperQuarter  "+			maxBlockUpperQuarter);	
					logger.trace("			maxBlockUpperHalf  "+			maxBlockUpperHalf);		
					
					logger.trace("  	nHgabLower   "+	nHgabLower);
					logger.trace("   the avg block size   "+avgBlockSize);
		}
		nHgab = (double) countGap;
		
		dmwf = maxgab - firstgab;
		dmwl = maxgab - lastgab;
		dmwfl = maxgabl - firstgabl;
		dmwll = maxgabl - lastgabl;
		
		

		  
		if (AppDefaults.NormalizeFeatures) {	
			BWBWBCount/=height;
	    maxBlock/=width;
	    maxBlockLocation/=height;
	    maxBlocklxrb/=width;
	    maxBlockLocationlxrb/=height;
		nHgabLower/=h_2;
			nHgab /= height;
			
			dmwf /= width;
			dmwl /= width;
			dmwll /= height;
			dmwfl /= height;
			dlrightleft /= height;
		}
		
		
		
		////////////////////////////////////////////Repeating for vertical ....
		// now for the gabs features
		for (int i = 0; i < columnsBlocks.length; i++) {
			// there is computing size...
		 
			if (columnsBlocks[i].size() > 1) {
				mgab = 0;
				// theres is two black blocks in the array
				// get the maximm gain in the array...
				for (int j = 0; j < columnsBlocks[i].size() - 1; j++) {
					Block temp = columnsBlocks[i].get(j);
					Block temp_1 = columnsBlocks[i].get(j + 1);
					gab = temp_1.start - temp.end - 1;
					// this is the size of gap....
					if (gab > mgab) {
						mgab = gab;
					}
				}
				if (mgab > 0) {
					nVgab++;
					 
				} // if gab exist
			}// if more than one bloock..
		}
	
		if (logger.isTraceEnabled()) {
		 
					logger.trace("  nVgab  "+	nVgab);
		}
		
		if (AppDefaults.NormalizeFeatures) {	
			 
			nVgab /= height;
		}
		
		
		
		
	}
	
	protected void computeTurnFeatures(){
		
		//////////////////////////////////turn 4 
		turn4=0;
		 double fli = 0;
	 double fld = 0;
	 double fli2 = 0;
		for (int i = 0; i < rowProfile.length; i++) {
			if (i > 0) {
				if (rowProfile[i].end < rowProfile[i - 1].end) {
					fli = 1;
					fli2 = 1;
				} else if (rowProfile[i].end > rowProfile[i - 1].end
						& fli > 0) {
					turn4++;
					fli = 0;
				}
			}
			}
	 ////////////////////////////////turn 3 
		fli=0;
		u1=0;
		//flb=0;
		for (int i = 0; i < columeProfile.length; i++) {
			if (i > 0) {
//				if (columeProfile[i].begin > half_h) {
//					flb = 1;
//					break;
//				}
				if (columeProfile[i].begin > columeProfile[i - 1].begin) {
					fli = 1;
					fli2 = 1;
				} else if (columeProfile[i].begin < columeProfile[i - 1].begin
						& fli > 0) {
					u1++;
					fli = 0;
				}
			}
		}
		 ////////////////////////////////turn 5  left 
 
 		turn5right=0;
		turn5left=0;
	 fli = 0;
	  fld = 0;
	   fli2 = 0;
		for (int i = 0; i < rowProfile.length; i++) {
			if (i > 0) {
				if (rowProfile[i].end > rowProfile[i - 1].end) {
					fli = 1;
					fli2 = 1;
				} else if (rowProfile[i].end < rowProfile[i - 1].end
						& fli > 0) {
				turn5right++;
					fli = 0;
				}
			}
			}
		 ////////////////////////////////turn 5 right 
		 
 

	 fli = 0;
	  fld = 0;
	   fli2 = 0;
		for (int i = 0; i < rowProfile.length; i++) {
			if (i > 0) {
				if (rowProfile[i].begin < rowProfile[i - 1].begin) {
					fli = 1;
					fli2 = 1;
				} else if (rowProfile[i].begin > rowProfile[i - 1].begin
						& fli > 0) {
					turn5left++;
					fli = 0;
				}
			}
			}
		
		
		
		
		if (logger.isTraceEnabled()) {
			logger.trace(" u  " + u1);
			logger.trace(" turn4  " + turn4);
			logger.trace(" turn 5 left  " + turn5left);
			logger.trace(" turn 5 right " + turn5right);
			
		}
		
		
	}

	protected void computeAllArabicFeatures() {
		computeHW();
		computeImageProfile();
		generateHorizonalBlockProfiles();
		generateVerticalBlockProfiles();
		
		vol2 = realHeight * realWidth;
		vol_1 = realHeight;
		vol_2 = realWidth;

		pbArea = (double) countBlack / (height*width);
		
		double half_h = height / 2.0;
		double width_4 = width / 4.0;
		double half_w = width / 2.0;
		double h_5 = height / 5.0;
		int rowp = 0;
		double height_3 = 2.0 * (height / 3.0);
		logger.trace(" height is " + height + "  width is " + width
				+ "   half width is " + half_w + "   width/4 " + width_4
				+ "   h_5 = " + h_5 + "   2 thrid height  " + height_3);

		// /--------------------------------------------------------------------------------------------------------------------------------
		// / LXRE AND MXB Feature only
		// //------------------------------------------------------------------------------------------------------------------------
		mxb = 999;
		lxrb = -1;
		mxb2 = 999;
		lxrb2 = -1;
		double tempdif;

		for (int i = 0; i < rowProfile.length; i++) {
			if (i > 0) {

				if (rowProfile[i].empty || rowProfile[i - 1].empty)
					continue;
				// if (i <= height_3 && i > 0) {
				tempdif = rowProfile[i].begin - rowProfile[i - 1].begin;

				if (lxrb == -1) {// intial 
					mxb = tempdif;
					lxrb = i;
					lxrb2 = i;
					mxb2 = tempdif;
					mnb=tempdif;
					lnrb=i-1;
				}
				if (mxb < tempdif) {  // max difference
					mxb = tempdif;
					lxrb = i;
				}
				if (mnb>tempdif){  // min difference 
					mnb=tempdif;
					lnrb=i-1;
					
				}
				
				if (i <= height_3) {
					if (mxb2 <= tempdif) {
						mxb2 = tempdif;
						lxrb2 = i;
					}
				}
			}

		}
		
		mnb=Math.abs(mnb);
		if (logger.isTraceEnabled()) {

			logger.trace("  mxb   " + mxb);
			logger.trace("  lxrb   " + lxrb);
			logger.trace("  mnb  "+mnb);
				logger.trace(" lnrb  "+lnrb);

			logger.trace("  mxb2   " + mxb2);
			logger.trace("  lxrb2   " + lxrb2);

		}
	
		int[] wdbe = arRowProfileFeatures();
		arColumnProfileFeatuers();
		arBlockFeatures();
		// /--------------------------------------------------------------------------------------------------------------------------------
		// // NO. OF TURNS , ZERO TRANSIIONS
		// //------------------------------------------------------------------------------------------------------------------------
		computeTurnFeatures();
	// locationOflxrb=lxrb;

		arCountWhiteFeatures();
		int sizele12 = (int) (height - 1 - lne);
		int[] le12 = new int[sizele12];
		for (int i = lne; i < sizele12; i++) {
			// le1=lre(lne:H_1-1);
			// le2=lre(lne+1:H_1);
			// le12=le2-le1;
			if ((i + 1) < rowProfile.length)
				le12[i] = rowProfile[i + 1].end - rowProfile[i].end;
		}
		snv = 0.0;
		szero = 0.0;
		sle = 0.0;
		sle3 = 0.0;
		for (int i = 0; i < le12.length; i++) {
			if (le12[i] == 0) {
				szero++;
			} else if (le12[i] < 0) {
				snv++;
			}
			if (le12[i] >= 0) {
				sle++;
				if (le12[i] >= 3) {
					sle3++;
				}
			}
		}
		if (logger.isTraceEnabled()) {
			//logger.trace(" u  " + u1);
			logger.trace(" sle  " + sle);
			logger.trace("sle3  " + sle3);
			logger.trace("snv  " + snv);
			logger.trace("szero  " + szero);
			logger.trace(" turn4 "+turn4);
			
		}
		if (AppDefaults.NormalizeFeatures) {
			sle /= height;

			szero /= height;

			snv /= height;

			sle3 /= height;
		}
		// sle /= (double) le12.length; (before normalization..)
		// [mxlre6,lxre6]=max(lre6); ===> move this colume to location of
		// computatieon of lxre6

		// /--------------------------------------------------------------------------------------------------------------------------------
		// // WIDE FEATURES
		// //------------------------------------------------------------------------------------------------------------------------

		sr = 0.0;
		smallwd = 0.0;
		sre = 0.0;
		// wdbe=lre-lrb;
		// smallwd=sum(wdbe(H2:H)<5);
		// re=wdbe<W2;
		// sre=sum(re)/H;
		// sr=sum(wdbe>=W4)/H; % try make sr same as smallwd or sre later
		for (int i = 0; i < wdbe.length; i++) {
			if (wdbe[i] < half_w) {
				sre++;
			}
			if (wdbe[i] >= width_4) {
				sr++;
			}
			if (wdbe[i] <= 5) {
				if (i >= half_h && i < height) {
					smallwd++;
				}
			}
		}

		if (logger.isTraceEnabled()) {
			logger.trace(" sr  " + sr);
			logger.trace("sre  " + sre);
			logger.trace("	smallwd  " + smallwd);
		}
		if (AppDefaults.NormalizeFeatures) {
			sr /= height;
			sre /= height;
			smallwd /= half_h;
		}
		lxrbNormalized=lxrb/height;
		if (AppDefaults.NormalizeFeatures) {
			lxrb /= height;
			lxrb2 /= height;
			lnrb/=height;
			// lxre6/=height;
			mxb /= width;
			mnb/=width;
			mxb2 /= width;
			
			// mxlre6/=width;
		}

		// /--------------------------------------------------------------------------------------------------------------------------------
		// // Finished.............................
		// //------------------------------------------------------------------------------------------------------------------------

		// final features
		// Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
	}

	class ImageRowProfile {
		public boolean empty = true;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			if (empty) {
				return " [ Empty profile ]";
			}

			return "  [begin=" + begin + ", end=" + end + "]";
		}

		int begin = 0;
		int end = 0;

	}

	protected void computeImageProfile() {
		columeProfile = new ImageRowProfile[(int) width];
		rowProfile = new ImageRowProfile[(int) height];

		// Iniit... e.........................

		for (int i = 0; i < columeProfile.length; i++) {
			columeProfile[i] = new ImageRowProfile();
			columeProfile[i].begin = 0;
			columeProfile[i].end = (int) (0);
			columeProfile[i].empty = true;
		}
		for (int i = 0; i < rowProfile.length; i++) {
			rowProfile[i] = new ImageRowProfile();
			rowProfile[i].begin = 0;
			rowProfile[i].end = (int) (0);
			rowProfile[i].empty = true;
		}

		// now computeeee.........................
		// for each row get last and first and last column of this row.
		for (int r = 0; r < height; r++) {
			// look for the first black pixel..
			for (int c = 0; c < width; c++) {
				if (digitImage[r][c] == BLACK) {
					rowProfile[r].begin = c;
					rowProfile[r].empty = false;
					break;
				}
			}
			for (int c = (int) (width - 1); c >= 0; c--) {
				if (digitImage[r][c] == BLACK) {
					rowProfile[r].end = c;
					rowProfile[r].empty = false;
					break;
				}
			}
		}
		// for each column
		for (int c = 0; c < width; c++) {
			// look for the first black pixel..
			for (int r = 0; r < height; r++) {
				if (digitImage[r][c] == BLACK) {
					columeProfile[c].begin = r;
					columeProfile[c].empty = false;
					break;
				}
			}
			for (int r = (int) (height - 1); r >= 0; r--) {
				if (digitImage[r][c] == BLACK) {
					columeProfile[c].end = r;
					columeProfile[c].empty = false;
					break;
				}
			}
		}

		// //////if trace wriet ethe profile.............
		if (logger.isTraceEnabled()) {

			for (int i = 0; i < columeProfile.length; i++) {
				logger.trace(" profile of column...  " + i + "  is "
						+ columeProfile[i]);

			}
			for (int i = 0; i < rowProfile.length; i++) {
				logger.trace(" profile of  row  " + i + "  is  "
						+ rowProfile[i]);

			}
		}
	}

	protected void generateHorizonalBlockProfiles() {
		rowsBlocks = new BlockList[(int) height];
		// BlockList[] columns=new BlockList[(int) width];
		byte prev = BLACK;
		int startB = 0, endB;
		int count = 0;
		// if (logExample2.isTraceEnabled()){
		for (int r = 0; r < digitImage.length; r++) {
			BlockList temp = new BlockList();
			temp.Length = digitImage[r].length;
			temp.location = r;
			temp.orientation = BlockList.HORIZONATAL;
			for (int c = 0; c < digitImage[r].length; c++) {
				if (c == 0) {
					prev = digitImage[r][c];
					startB = c;
				}
				if (prev != digitImage[r][c]) {
					// there is a chage in blocksss..
					// change in block...
					// end of block
					endB = c - 1;
					// create a new block from start to end.. only if it is a
					// black..
					if (prev == BLACK) {
						Block tempBlock = new Block(startB, endB, count, prev);
						temp.add(tempBlock);
					}
					// this is also a start of a new block
					startB = c;
					count = 1;
				} else {
					count++;
				}
				prev = digitImage[r][c];
			}
			// the last block..
			endB = digitImage[r].length - 1;
			// create a new block from start to end..
			if (prev == BLACK) {
				Block tempBlock = new Block(startB, endB, count, prev);
				temp.add(tempBlock);
			}
			count = 0;
			// after each roww now add block list to ho
			rowsBlocks[r] = temp;
		}

		if (logger.isTraceEnabled()) {

			for (int i = 0; i < rowsBlocks.length; i++) {
				logger.trace(" profile of row...  " + i + "  is " + rowsBlocks[i]);

			}

		}

	}
	protected void generateVerticalBlockProfiles() {
		columnsBlocks = new BlockList[(int) width];
		// BlockList[] columns=new BlockList[(int) width];
		byte prev = BLACK;
		int startB = 0, endB;
		int count = 0;
		// if (logExample2.isTraceEnabled()){
		for (int c = 0; c < digitImage[0].length; c++) {
			BlockList temp = new BlockList();
			temp.Length = digitImage[0].length;
			temp.location = c;
			temp.orientation = BlockList.VERTICAL;
			for (int r = 0; r < digitImage.length; r++) {
				if (r == 0) {
					prev = digitImage[r][c];
					startB = r;
				}
				if (prev != digitImage[r][c]) {
					// there is a chage in blocksss..
					// change in block...
					// end of block
					endB = r - 1;
					// create a new block from start to end.. only if it is a
					// black..
					if (prev == BLACK) {
						Block tempBlock = new Block(startB, endB, count, prev);
						temp.add(tempBlock);
					}
					// this is also a start of a new block
					startB = r;
					count = 1;
				} else {
					count++;
				}
				prev = digitImage[r][c];
			}
			// the last block..
			endB = digitImage.length - 1;
			// create a new block from start to end..
			if (prev == BLACK) {
				Block tempBlock = new Block(startB, endB, count, prev);
				temp.add(tempBlock);
			}
			count = 0;
			// after each roww now add block list to ho
			columnsBlocks[c] = temp;
		}

		if (logger.isTraceEnabled()) {

			for (int i = 0; i < columnsBlocks.length; i++) {
				logger.trace(" profile of column...  " + i + "  is " + columnsBlocks[i]);

			}

		}

	}

	protected void computeBasicStates() {
		generateSurrondImage();
		ArrayList<BlockList> Horizontal = new ArrayList<BlockList>();
		ArrayList<BlockList> Vertical = new ArrayList<BlockList>();
		byte prev = BLACK;
		int startB = 0, endB;
		int count = 0;
		// if (logExample2.isTraceEnabled()){
		for (int r = 0; r < digitImage.length; r++) {
			BlockList temp = new BlockList();
			temp.Length = digitImage[r].length;
			temp.location = r;
			temp.orientation = BlockList.HORIZONATAL;
			for (int c = 0; c < digitImage[r].length; c++) {
				if (c == 0) {
					prev = digitImage[r][c];
					startB = c;
				}
				if (prev != digitImage[r][c]) {
					// change in block...
					// end of block
					endB = c - 1;
					// create a new block from start to end..
					Block tempBlock = new Block(startB, endB, count, prev);
					temp.add(tempBlock);
					// this is also a start of a new block
					startB = c;
					count = 1;
				} else {
					count++;
				}
				prev = digitImage[r][c];
			}
			// the last block..
			endB = digitImage[r].length - 1;
			// create a new block from start to end..
			Block tempBlock = new Block(startB, endB, count, prev);
			temp.add(tempBlock);
			count = 0;
			// after each roww now add block list to ho
			Horizontal.add(temp);
		}
		// ////////////////////////////////now vertical
		startB = 0;
		count = 0;
		for (int c = 0; c < digitImage[0].length; c++) {
			BlockList temp = new BlockList();
			temp.Length = digitImage[0].length;
			temp.location = c;
			temp.orientation = BlockList.VERTICAL;
			for (int r = 0; r < digitImage.length; r++) {
				if (r == 0) {
					prev = digitImage[r][c];
					startB = r;
				}
				if (prev != digitImage[r][c]) {
					// change in block...
					// end of block
					endB = r - 1;
					// create a new block from start to end..
					Block tempBlock = new Block(startB, endB, count, prev);
					temp.add(tempBlock);
					// this is also a start of a new block
					startB = r;
					count = 1;
				} else {
					count++;
				}
				prev = digitImage[r][c];
			}// for row s
			// the last block..
			endB = digitImage.length - 1;
			// create a new block from start to end..
			Block tempBlock = new Block(startB, endB, count, prev);
			temp.add(tempBlock);
			count = 0;
			Vertical.add(temp);
		}
		// logExample2.trace("--------------------HHHHHHHHHHHHH------------------------------");
		// /// now display all rowsBlocks and
		// for (int i = 0; i < Horizontal.size(); i++) {
		// logExample2.trace( Horizontal.get(i));
		// }
		//		
		// logExample2.trace("--------------------VVVVVVVVVVVVVVVVVVVVVVVVVv------------------------------");
		// // display all columns
		// for (int i = 0; i < Vertical.size(); i++) {
		// logExample2.trace(Vertical.get(i));
		// }
		// }
	}

	protected void computeVerticalLength() {
		double wide;
		double averageWide = 0.0;
		double MaxBlackWide = 0;
		double MaxBlackWideRowLocation;
		int firstCinRow = 0, LastCinRow = 0;
		double AverageOfLeft = 0;
		// count of wide > half
		// average wide
		// number fo wide <1/4
		// average wide up
		double halfw, qarth;
		halfw = width / 2.0;
		qarth = height / 4.0;
		double halfh = height / 2.0;
		int countMoreThanHalf = 0;
		int countLessThanQuart = 0;
		double AverageOfRight = 0;
		boolean BlackVisitedBefore = false;
		for (int c = 0; c < digitImage[0].length; c++) {
			for (int r = 0; r < digitImage.length; r++) {
				if (digitImage[r][c] == BLACK) // if they black chekd if this is
				// first in row
				{
					if (!BlackVisitedBefore) {
						firstCinRow = r;
						BlackVisitedBefore = true;
					}
					LastCinRow = r;
				}
			}
			wide = LastCinRow - firstCinRow;
			BlackVisitedBefore = false;
			if (c < halfw) {
				AverageOfLeft += wide;
			} else {
				AverageOfRight += wide;
			}
			averageWide += wide;
			if (wide > MaxBlackWide) {
				MaxBlackWide = wide;
				MaxBlackWideRowLocation = c;
			}
			if (wide > halfh) {
				countMoreThanHalf++;
			}
			if (wide < qarth) {
				countLessThanQuart++;
			}
		}
		BlackWideVertical = (averageWide / width) / height;
		AverageWideLeftVertical = (AverageOfLeft / halfw) / height;
		AverageWideRightVertical = (AverageOfRight / halfw) / height;
		CountBigWideVertical = (double) countMoreThanHalf / width;
		CountLowWideVertical = (double) countLessThanQuart / width;
	}

	protected void computeBorderFeatures() {
		BorderLocationLengthInLastColumn = 0;
		boolean visitS, visitE;
		visitS = false;
		visitE = false;
		// get left and right borders
		// for rowss look for las and first boreders location
		for (int r = 0; r < digitImage.length; r++) {
			if (digitImage[r][0] == BLACK) // if they black chekd if this is
			// first in row
			{
				bLe = r;
				if (!visitS) {
					bLs = r;
					visitS = true;
				}
			}
			if (digitImage[r][digitImage[r].length - 1] == BLACK) // if they
			// black
			// chekd if
			// this is
			// first in
			// row
			{
				bRe = r;
				if (!visitE) {
					bRs = r;
					visitE = true;
				}
			}
		}
		// /////////////////////////////////Now i have borders of left and right
		visitS = false;
		visitE = false;
		for (int c = 0; c < digitImage[0].length; c++) {
			if (digitImage[0][c] == BLACK) // if they black chekd if this is
			// first in row
			{
				bUe = c;
				if (!visitS) {
					bUs = c;
					visitS = true;
				}
			}
			if (digitImage[digitImage.length - 1][c] == BLACK) // if they black
			// chekd if this
			// is first in
			// row
			{
				bDe = c;
				if (!visitE) {
					bDs = c;
					visitE = true;
				}
			}
		}
		BorderLocationLengthInLastColumn = (double) (bRe - bRs) / height;
		BorderLocationLastColumn = (double) (bRe) / height;
		BorderLocationDownEnd = (double) (bDe) / width;
		BorderLocationDownLength = (double) (bDe - bDs) / width;
		BorderLocationUpLength = (double) (bUe - bUs) / width;
		BorderLocationFColLength = (double) (bRe - bRs) / height;
		BorderLocationUpEnd = (double) (bUe) / width;
		BorderLocationFColEnd = (double) (bRe) / height;
		// for (int r = 0; r < digitImage.length; r++) {
		// for (int c = 0; c < digitImage[r].length;pbLeft c++) {
		// if (digitImage[r][c] == BLACK) // if they black chekd if this is
		// first in row
		// {
		// }
		// }
		// }
		//		
		// see the last column... border
		// for (int c = 0; c < digitImage[r].length; c++) {
		// if (digitImage[r][c] == BLACK) // if they black chekd if this is
		// first in row
		// {
		// }pbLeft
		// }
		// }
	}

	protected void computeAverageOfLast() {
		AverageLastInlower = 0;
		AverageLastinUpper = 0;
		double LastCinRow = 0;
		double halfh = height / 2.0;
		for (int r = 0; r < digitImage.length; r++) {
			for (int c = 0; c < digitImage[r].length; c++) {
				if (digitImage[r][c] == BLACK) // if they black chekd if this is
				// first in row
				{
					//					
					LastCinRow = c;
				}
			}
			// in the last c in row now i have
			if (r < halfh)
				AverageLastinUpper += LastCinRow;
			else
				AverageLastInlower += LastCinRow;
		}
		AverageLastinUpper /= width;
		AverageLastInlower /= width;
	}

	protected void computeSuddenChange() {
		// 2, 5 sudden change in
		int prevLastColumn = 0;
		int LastColumn = 0;
		int MeasureTransition;
		int MeasureTransitionFirst;
		int LargestSuddenChange = 0;
		int countSudden = 0;
		int FirstColumn = 0;
		int prevFirstColumn = 0;
		int countSuddenLeft = 0;
		int LargestSuddenChangeLeft = 0;
		boolean prevBlack = false;
		double halfw = width / 2.0;
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[i][j] == BLACK) {
					if (!prevBlack) {
						FirstColumn = j;
						prevBlack = true;
					}
					// count++;
					LastColumn = j;
				}
			}// end of columns
			if (i > 0) {
				MeasureTransition = Math.abs(prevLastColumn - LastColumn);
				if (MeasureTransition > halfw) {
					// large transisiotn
					countSudden++;
				}
				if (MeasureTransition > LargestSuddenChange) {
					LargestSuddenChange = MeasureTransition;
					SudenChangeFRightLocation = i;
				}
				// //////////////////////
				MeasureTransitionFirst = Math
						.abs(FirstColumn - prevFirstColumn);
				if (MeasureTransitionFirst > halfw) {
					// large transisiotn
					countSuddenLeft++;
				}
				if (MeasureTransitionFirst > LargestSuddenChangeLeft) {
					LargestSuddenChangeLeft = MeasureTransitionFirst;
					SudenChangeFLeftLocation = i;
				}
			}
			prevFirstColumn = FirstColumn;
			prevLastColumn = LastColumn;
			prevBlack = false;
		}// rowsBlocks
		SudenChangeFRight = (double) LargestSuddenChange / (double) width;
		SudenChangeFLeft = (double) LargestSuddenChangeLeft / (double) width;
		SudenChangeFRightLocation /= (double) height;
		SudenChangeFLeftLocation /= (double) height;
	}

	protected void computeNegativeTransition() {
		int count = 0;
		int prevLastColumn = 0;
		int LastColumn = 0;
		int MeasureTransition;
		int countLarge = 0;
		int countZero = 0;
		int countPos = 0;
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[i][j] == BLACK) {
					// count++;
					LastColumn = j;
				}
			}// end of columnspbLeft
			if (i > 0) {
				MeasureTransition = prevLastColumn - LastColumn;
				if (MeasureTransition == 0) {
					countZero++;
				}
				if ((MeasureTransition >= 1) && (MeasureTransition <= 2)) {
					count++;
				}
				if (MeasureTransition > 3) {
					countLarge++;
				}
				if (MeasureTransition < 0) {
					// then positive transition
					if ((MeasureTransition <= -1) && (MeasureTransition >= -2)) {
						countPos++;
					}
				}
			}
			prevLastColumn = LastColumn;
		}// rowsBlocks
		CountNegativeTransition = (double) count / height;
		CountLargeNegativeTransition = (double) countLarge / height;
		CountZeroTransition = (double) countZero / height;
		CountPositiveTransition = (double) countPos / height;
	}

	protected void computePercentInRows() {
		// ///from right
		int count = 0;
		double halfh = height / 2.0;
		// int countl,countr;
		// countl=countr=count=0;
		int countAll = 0;
		count = 0;
		countAll = 0;
		for (int i = 0; i < digitImage.length && i < NUMBER_FIRST; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				countAll++;
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[i][j] == BLACK) {
					count++;
				}
			}
		}
		PbinF4R = (double) count / (double) countAll;
		count = 0;
		countAll = 0;
		for (int r = digitImage.length - 1; r > 0
				&& r > (digitImage.length - 1 - NUMBER_FIRST); r--) {
			for (int j = 0; j < digitImage[r].length; j++) {
				countAll++;
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[r][j] == BLACK) {
					count++;
				}
			}
		}
		PbinL4R = (double) count / (double) countAll;
		count = 0;
		countAll = 0;
		for (int c = digitImage[0].length - 1; c > 0
				&& c > (digitImage[0].length - 1 - NUMBER_FIRST); c--) {
			for (int r = 0; r < digitImage.length; r++) {
				countAll++;
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[r][c] == BLACK) {
					count++;
				}
			}
		}
		PbinL4C = (double) count / (double) countAll;
		count = 0;
		countAll = 0;
		for (int c = 0; c < digitImage[0].length && c < NUMBER_FIRST; c++) {
			for (int r = 0; r < digitImage.length; r++) {
				countAll++;
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[r][c] == BLACK) {
					count++;
				}
			}
		}
		PbinF4C = (double) count / (double) countAll;
		count = 0;
		countAll = 0;
		for (int j = 0; j < digitImage[0].length && j < NUMBER_FIRST; j++) {
			for (int i = 0; i < digitImage.length; i++) {
				if (i < halfh) {
					countAll++;
					// for (int j = 0; j < digitImage[i].length; j++) {
					// from left to right from up to down
					if (digitImage[i][j] == BLACK) {
						count++;
					}
				}
			}
		}
		PbinF4CinUpper = (double) count / (double) countAll;
	}

	protected void computeWhiteSides() {
		// ///from right
		int count = 0;
		double halfh = height / 2.0;
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = digitImage[0].length - 1; j > 0; j--) {
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				if (digitImage[i][j] == BLACK) {
					break;
				} else {
					if (i < halfh) {
						count++;
					}
				}
			}
		}
		FromRightUp = (double) count / (double) countWhite;
		byte test;
		count = 0;
		int countup = 0;
		// ///from right
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				// for (int j = 0; j < digitImage[i].length; j++) {
				// from left to right from up to down
				test = digitImage[i][j];
				if (test == BLACK) {
					break;
				} else {
					if (i > halfh) {
						count++;
					} else {
						countup++;
					}
				}
			}
		}
		FromLeftDown = (double) count / (double) countWhite;
		FromLeftUp = (double) countup / (double) countWhite;
	}

	protected void computeSurrondRight() {
		int countSr3From = 0;
		int test;
		for (int i = 0; i < surrondLabelImage.length; i++) {
			for (int j = surrondLabelImage[i].length - 1; j > 0; j--) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 3) {
					countSr3From++;
				}
				if (test == 0) {
					break;
				}
			}
		}
		SrB3FromRight = (double) countSr3From / (double) countWhite;
		countSr3From = 0;
		for (int i = 0; i < surrondLabelImage.length; i++) {
			for (int j = 0; j < surrondLabelImage[i].length; j++) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 3) {
					countSr3From++;
				}
				if (test == 0) {
					break;
				}
			}
		}
		SrB3FromLeft = (double) countSr3From / (double) countWhite;
		countSr3From = 0;
		for (int j = 0; j < surrondLabelImage[0].length; j++) {
			for (int i = 0; i < surrondLabelImage.length; i++) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 3) {
					countSr3From++;
				}
				if (test == 0) {
					break;
				}
			}
		}
		SrB3FromUp = (double) countSr3From / (double) countWhite;
		countSr3From = 0;
		for (int j = surrondLabelImage[0].length - 1; j > 0; j--) {
			for (int i = 0; i < surrondLabelImage.length; i++) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 3) {
					countSr3From++;
				}
				if (test == 0) {
					break;
				}
			}
		}
		SrB3FromDown = (double) countSr3From / (double) countWhite;
	}

	protected void computeWideFeatures() {
		double wide;
		double averageWide = 0.0;
		double MaxBlackWide = 0;
		double MaxBlackWideRowLocation;
		int firstCinRow = 0, LastCinRow = 0;
		double AverageOfup = 0;
		// count of wide > half
		// average wide
		// number fo wide <1/4
		// average wide up
		double halfw, qartw;
		halfw = width / 2.0;
		qartw = width / 4.0;
		double halfh = height / 2.0;
		int countMoreThanHalf = 0;
		int countLessThanQuart = 0;
		boolean BlackVisitedBefore = false;
		for (int r = 0; r < digitImage.length; r++) {
			for (int c = 0; c < digitImage[r].length; c++) {
				if (digitImage[r][c] == BLACK) // if they black chekd if this is
				// first in row
				{
					if (!BlackVisitedBefore) {
						firstCinRow = c;
						BlackVisitedBefore = true;
					}
					LastCinRow = c;
				}
			}
			wide = LastCinRow - firstCinRow;
			BlackVisitedBefore = false;
			if (r < halfh) {
				AverageOfup += wide;
			}
			averageWide += wide;
			if (wide > MaxBlackWide) {
				MaxBlackWide = wide;
				MaxBlackWideRowLocation = r;
			}
			if (wide > halfw) {
				countMoreThanHalf++;
			}
			if (wide < qartw) {
				countLessThanQuart++;
			}
		}
		BlackWide = (averageWide / height) / width;
		AverageWideUp = (AverageOfup / halfh) / width;
		CountBigWide = (double) countMoreThanHalf / height;
		CountLowWide = (double) countLessThanQuart / height;
	}

	protected void computePercentBlackInDirection() {
		PBinUpVsDown = 0;
		PBinLeftVsRight = 0;
		double halfh, halfw;
		halfh = (double) height / 2.0;
		halfw = (double) width / 2.0;
		int countL, countR, countU, countD;
		countL = countR = countU = countD = 0;
		for (int r = 0; r < digitImage.length; r++) {
			for (int c = 0; c < digitImage[r].length; c++) {
				if (digitImage[r][c] == BLACK) // if they are a like
				{
					if (r > halfh) {
						countD++;
					} else {
						countU++;
					}
					if (c > halfw) {
						countR++;
					} else {
						countL++;
					}
				}
			}
		}
		double countHalfH = halfw * height;
		double countHalfw = halfh * width;
		PbCountU = (double) countU / (countHalfw);
		PbCountD = (double) countD / (countHalfw);
		PbCountL = (double) countL / (countHalfH);
		PbCountR = (double) countR / (countHalfH);
		PbCountLOverCountInRight = (double) countR / (double) countBlack;
		if (countR != 0)
			PBinLeftVsRight = (double) countL / (double) countR;
		else
			PBinLeftVsRight = (double) countL / (double) 0.1;
		if (countD != 0)
			PBinUpVsDown = (double) countU / (double) countD;
		else
			PBinUpVsDown = (double) countU / (double) 0.1;
	}

	protected void whiteInLowerAndInUpper() {
		countWsbLower = 0.0;
		countWsbUppper = 0.0;
		int test;
		double halfHeight = height / 2.0;
		for (int i = 0; i < surrondLabelImage.length; i++) {
			for (int j = 0; j < surrondLabelImage[i].length; j++) {
				// from left to right from up to down
				test = surrondLabelImage[i][j];
				if (test == 4) {
					if (i >= halfHeight)
						countWsbLower++;
					else
						countWsbUppper++;
				}
			}
		}
		logger.trace(" halft height  == " + halfHeight);
		logger.trace(" the wsb in lower is " + countWsbLower);
		if (CountWSB != 0) {
			countWsbLower /= (double) CountWSB;
			countWsbUppper /= (double) CountWSB;
		} else {
			countWsbLower /= (double) 1.0;
			countWsbUppper /= (double) 1.0;
		}
	}

	protected void computePb() {
		pb = (double) countBlack / (double) (countBlack + countWhite);
	}

	protected void computeMcr() {
		cx = 0;
		cy = 0;
		int count = 0;
		for (int f = 0; f < digitImage.length; f++) {
			for (int i = 0; i < digitImage[f].length; i++) {
				if (digitImage[f][i] == BLACK) // if they are a like
				{
					cx += f;
					cy += i;
					count++;
				}
			}
		}// getting the center of the pixels
		cx /= ((double) count);
		cy /= ((double) count);
		mcr = 0;
		// for each row
		for (int i = 0; i < digitImage.length; i++) {
			for (int j = 0; j < digitImage[i].length; j++) {
				if (digitImage[i][j] == BLACK) {// if they are a like{
					mcr += length(cx, cy, i, j);
				}
			}
		}
		mcr /= ((double) count);
	}

	private double length(double x0, double y0, double x1, double y1) {
		double dx = x1 - x0;
		double dy = y1 - y0;
		return Math.sqrt(dx * dx + dy * dy);
	}

	protected void generateSurrondImage() {
		surrondLabelImage = new int[digitImage.length][digitImage[0].length];
		countBlack = 0;
		countWhite = 0;
		for (int f = 0; f < digitImage.length; f++) {
			for (int i = 0; i < digitImage[f].length; i++) {
				if (digitImage[f][i] == WHITE) // if they are a like
				{
					surrondLabelImage[f][i] = 0;
					countWhite++;
				} else {
					countBlack++;
					surrondLabelImage[f][i] = 0;
				}
			}
		}
		if (countWhite == 0)
			countWhite = 1;
		// displayImage(digitImage);
		logger.trace("  count white = " + countWhite + "  count black=  "
				+ countBlack);
		// logExample.trace("  count white = " + countWhite + "  count black=  "
		// + countBlack );
		boolean blackBlock = false;
		int starti, endi, startj, endj;
		// for each row
		for (int i = 0; i < digitImage.length; i++) {
			startj = 0;
			// logger.info("  traversing row  "+i);
			// displayImage(surrondLabelImage );
			// change label of each columen before , black , after
			for (int j = 0; j < digitImage[i].length; j++) {
				if (digitImage[i][j] == WHITE) {
					// i am in white do noting just connt till next white
					// if was in black block then set black block to false..
					if (blackBlock) {
						startj = j;
						// this is a start of a new block...
						blackBlock = false;
					}
					// check count of visited black.
					// if (VisitBlackCount==0){
					// // no black i am still in wall
					// }
					// if (VisitedBlackCount==1)
				} else {
					// i am a black pixel
					// if i am already in a black block then do nothing
					if (blackBlock) {
						continue;
					} else {// this is the first black pixels
						// logger.info(" a black pixel start of a block at "+j);
						endj = j;
						blackBlock = true;
						incrementLabels(i, i + 1, startj, endj);
						if (startj != 0) {
							incrementLabels(i, i + 1, startj, endj);
						}
					}// else of the black block
				}// else (black pixel )
			}// / for all j
			if (!blackBlock) // these mean that there is some white at end.
			{
				endj = digitImage[i].length;
				incrementLabels(i, i + 1, startj, endj);
			}
		}
		// //////////////////////////////////////////do the same columne wise
		// ////////////////////////////////////////////////
		blackBlock = false;
		// change label of each columen before , black , after
		for (int j = 0; j < digitImage[0].length; j++) {
			starti = 0;
			for (int i = 0; i < digitImage.length; i++) {
				if (digitImage[i][j] == WHITE) {
					// i am in white do noting just connt till next white
					// if was in black block then set black block to false..
					if (blackBlock) {
						starti = i;
						// this is a start of a new block...
						blackBlock = false;
					}
					// check count of visited black.
					// if (VisitBlackCount==0){
					// // no black i am still in wall
					// }
					// if (VisitedBlackCount==1)
				} else {
					// i am a black pixel
					// if i am already in a black block then do nothing
					if (blackBlock) {
						continue;
					} else {// this is the first black pixels
						endi = i;
						blackBlock = true;
						incrementLabels(starti, endi, j, j + 1);
						if (starti != 0) {
							incrementLabels(starti, endi, j, j + 1);
						}
					}// else of the black block
				}// else (black pixel )
			}// / for all j
			if (!blackBlock) // these mean that there is some white at end.
			{
				endi = digitImage.length;
				incrementLabels(starti, endi, j, j + 1);
			}
		}
		// displayImage(surrondLabelImage );
		// for (int i = 0; i < digitImage[0].length; i++) {
		//		
		// }
		//	
		//
		// for (int i = digitImage.length; i >0 ; i--) {
		//		
		// }
		//
		// for (int i = digitImage.length; i >0 ; i--) {
		//		
		// }
	}

	private void incrementLabels(int ai, int bi, int aj, int bj) {
		// logger.info(" i am incrementing the values from ai "+ai+"  bi  "+bi+" aj  "+aj+"  bj  "+bj);
		for (int i = ai; i < bi; i++) {
			for (int j = aj; j < bj; j++) {
				surrondLabelImage[i][j]++;
			}
		}
	}

	public static void setAddArabic(boolean addArabic) {
		ImageBase.addArabic = addArabic;
	}

	public static void setAddPercent(boolean addPercent) {
		ImageBase.addPercent = addPercent;
	}

	public static void setAddBlock(boolean addBlock) {
		ImageBase.addBlock = addBlock;
	}

	public static void setAddGeneral(boolean addGeneral) {
		ImageBase.addGeneral = addGeneral;
	}

	public static void setAddSudden(boolean addSudden) {
		ImageBase.addSudden = addSudden;
	}

	public static void setAddBorder(boolean addBorder) {
		ImageBase.addBorder = addBorder;
	}

	public static void setAddSround(boolean addSround) {
		ImageBase.addSround = addSround;
	}

	public static void setAddWhite(boolean addWhite) {
		ImageBase.addWhite = addWhite;
	}

	public static void setAddTransition(boolean addTransition) {
		ImageBase.addTransition = addTransition;
	}

	public static void setAddGabs(boolean addGabs) {
		ImageBase.addGabs = addGabs;
	}

	public static void setAddWide(boolean addWide) {
		ImageBase.addWide = addWide;
	}

	public int addingArabicFeatures() {
		computeAllArabicFeatures();
		Feature feat;
		// Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
		// //////////////////////////////////
		feat = new Feature();
		feat.setName(" HW ==> Height over width ");
		feat.setSmallName("HW");
		feat.setValue(heightoverwidth);
		features.add(feat);
		// //////////////////////////////////
		feat = new Feature();
		feat.setName(" vol2 ==> Area ");
		feat.setSmallName("vol2");
		feat.setValue(vol2);
		features.add(feat);
		// // //////////////////////////////////
		feat = new Feature();
		feat.setName(" Vol(1) ==> Real Height ");
		feat.setSmallName("Vol(1)");
		feat.setValue(vol_1);
		features.add(feat);
		// // //////////////////////////////////
		feat = new Feature();
		feat.setName(" Vol(2) ==> Real Width");
		feat.setSmallName("Vol(2)");
		feat.setValue(vol_2);
		features.add(feat);
		// //////////////////////////////////,HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,wd,im,cbe,wcbe
		feat = new Feature();
		feat
				.setName(" w2r ==> (white_2) sround from  left, up and  down (white from right) ");
		feat.setSmallName("w2r");
		feat.setValue(w2r);
		features.add(feat);
		// ////////////////////////////////
		feat = new Feature();
		feat
				.setName(" wu ==> (white_3) sround from left and right and own  by black (white from up white 3) ");
		feat.setSmallName("wu");
		feat.setValue(wu);
		features.add(feat);
		// //////////////////////////////////
		feat = new Feature();
		feat
				.setName(" w4lft ==> (white_4) surrond by up, down and right by black  (white from left ) ");
		feat.setSmallName("w4lft");
		feat.setValue(w4lft);
		features.add(feat);
		// //////////////////////////////////
		// //////////////////////////////////
		feat = new Feature();
		feat.setName("w4 ==> ( white_5 ) surrond by features from all sides.");
		feat.setSmallName("w4");
		feat.setValue(w4);
		features.add(feat);
		// /////////////////////////////////////,HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,wd,im,cbe,wcbe
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("wrb ==> (White_6) white from left (till begins of black rowsBlocks..)");
		feat.setSmallName("wrb");
		feat.setValue(wrb);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("wd ==> (White_8) surrond left, right and from up by black (from down ) ");
		feat.setSmallName("wd");
		feat.setValue(wd);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName(" im ==> (height_depth_7 ) The last row from the bottom that has White3  pixels is considered.");
		feat.setSmallName("im");
		feat.setValue(im2);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("u1 ==>  (Top Black Turns)  number of turn W (3 an 7 )");
		feat.setSmallName("u1");
		feat.setValue(u1);
		features.add(feat);
		// //////////////////////////////////

		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("smallwd ==> (Width lower half) Number of rowsBlocks in the lower half of the  bb(bounding box) that are less than five black pixels.");
		// feat
		// .setName(" smallwd ==>  ");
		feat.setSmallName("smallwd");
		feat.setValue(smallwd);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName(" sre ==> (Small width) Number of rowsBlocks in the bb(bounding box) that have black pixels spanning < 1/2 w of the bb.");
		feat.setSmallName("sre");
		feat.setValue(sre);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName(" mce69 ==> (Last Black Locations) vertical width in upper half of image ");
		feat.setSmallName("mce69");
		feat.setValue(mce69);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("sr  ==> (Large Width) Number of rowsBlocks in the bbthat have black pixels spanning > 1/4  width of the bb.");
		feat.setSmallName("sr");
		feat.setValue(sr);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName(" wce   ==>(white_Bottom )white form down / width ");
		feat.setSmallName("wce");
		feat.setValue(wce);
		features.add(feat);

		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("wcb   ==> (white Top ) white from up (sum/with)");
		feat.setSmallName("wcb");
		feat.setValue(wcb);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("wre    ==> (white Right)  white from right  (till end of black rowsBlocks)");
		feat.setSmallName("wre");
		feat.setValue(wre);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("btop        ==>  Black Top ");
		feat.setSmallName("btop");
		feat.setValue(btop);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("bbot     ==>  Black Bottom");
		feat.setSmallName("bbot");
		feat.setValue(bbot);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("blft      ==>    Black Left");
		feat.setSmallName("blft");
		feat.setValue(blft);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat.setName("bright   ==>   Black Right");
		feat.setSmallName("bright");
		feat.setValue(bright);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName(" sle3 ==> (Sum big transitions) count postive large transition (rowsBlocks grows quicly)");
		feat.setSmallName("sle3");
		feat.setValue(sle3);
		features.add(feat);

		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("sle ==> (Sum positive transitions) count postive   transition (rowsBlocks grows  or be longer)");
		feat.setSmallName("sle");
		feat.setValue(sle);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("snv  ==> (Sum negative transitions )count negative transition (rowsBlocks shorters)");
		feat.setSmallName("snv");
		feat.setValue(snv);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("szero ==>  (Sum zero transitions) Count zero transition ");
		feat.setSmallName("szero");
		feat.setValue(szero);
		features.add(feat);
		// //////////////////////////////////
		// btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright

		feat = new Feature();
		feat.setName(" cbe  ==> (height_9) ");
		feat.setSmallName("cbe");
		feat.setValue(cbe);
		features.add(feat);
		// //wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName(" wcbe ==> (white_9) number of white pixel in left half that are between black pixels .");
		feat.setSmallName("wcbe");
		feat.setValue(wcbe);
		features.add(feat);

		// ///wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("mxlre6 ==> Max right transition   get maximum transition from right (row end) ");
		feat.setSmallName("mxlre6");
		feat.setValue(mxlre6);
		features.add(feat);
		// //////////////////////////////////////////////
		feat = new Feature();
		feat
				.setName("lxre6  ==> Max right transition location location of  maximum transition from right (row end)");
		feat.setSmallName("lxre6");
		feat.setValue(lxre6);
		features.add(feat);
		// //////////////////////////////////
		// wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright
		feat = new Feature();
		feat
				.setName("mxb  ==> Max_Left transition maximum transiiton from left  (in the first half of image ");
		feat.setSmallName("mxb");
		feat.setValue(mxb);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("lxrb ==> (Max left transition location)  maximum transiiton from left (in the first half of image location ");
		feat.setSmallName("lxrb");
		feat.setValue(lxrb);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("mxce ==> (Max Downward Transition ) maximum transition downwar (vertical ) ");
		feat.setSmallName("mxce");
		feat.setValue(mxce);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("lxce  ==> (Max Downward Transition Location )maximum transition downwar (vertical ) ");
		feat.setSmallName("lxce");
		feat.setValue(lxce);
		features.add(feat);
		feat = new Feature();
		feat.setName("wsbLeft ==> white surrond by black in the left  ");
		feat.setSmallName("wsbLeft ");
		feat.setValue(wsbLeft);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("dlrightleft ==> distance between Last black pixel from right and left  (column wise) ");
		feat.setSmallName("dlrightleft");
		feat.setValue(dlrightleft);
		features.add(feat);

		feat = new Feature();
		feat.setName(" pbTop ==> percent of black in 1/5 percent of digit.");
		feat.setSmallName("pbTop");
		feat.setValue(pbTop);
		features.add(feat);

		feat = new Feature();
		feat
				.setName(" w2left ==>  white srounded by up, left only from right. ");
		feat.setSmallName("w2left");
		feat.setValue(w2left);
		features.add(feat);

		feat = new Feature();
		feat
				.setName(" Dmwf ==>  Difference between maximum and first gab   width");
		feat.setSmallName("dmwf");
		feat.setValue(dmwf);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("dmwl ==> Difference between maximum and last gab  width ");
		feat.setSmallName("dmwl");
		feat.setValue(dmwl);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("Dmwfl ==> Difference between maximum and first gab   width  locations ");
		feat.setSmallName("dmwfl");
		feat.setValue(dmwfl);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("dmwll ==> Difference between maximum and last gab  width locations  ");
		feat.setSmallName("dmwll");
		feat.setValue(dmwll);
		features.add(feat);

		feat = new Feature();
		feat.setName(" BWB ==>  number of horizontal gabs ");
		feat.setSmallName("nHgab");
		feat.setValue(nHgab);
		features.add(feat);

		feat = new Feature();
		feat.setName("pbLeft ==>   percent fo black in the left.. ");
		feat.setSmallName("pbLeft");
		feat.setValue(pbLeft);
		features.add(feat);

		feat = new Feature();
		feat.setName("pbRight ==>  percent black in right ");
		feat.setSmallName("pbRight");
		feat.setValue(pbRight);
		features.add(feat);

		// ///////////////////////////////////////////////////////////////Adding
		// another set of features..

		feat = new Feature();
		feat.setName("wwlhl==> F36  average white width lower half from left ");
		feat.setSmallName("wwlhl");
		feat.setValue(wwlhl);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("wwlhr==> F37  average white width lower half from right ");
		feat.setSmallName("wwlhr");
		feat.setValue(wwlhr);
		features.add(feat);

		feat = new Feature();
		feat.setName("mtwnr ==> F38 max wide narrow transition from right ");
		feat.setSmallName("mtwnr");
		feat.setValue(mtwnr);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("ltwnr ==> F39 location of max wide narrow transition from right");
		feat.setSmallName("ltwnr");
		feat.setValue(ltwnr);
		features.add(feat);

		feat = new Feature();
		feat
				.setName("wwuhr==> F40  average white width upper half from right ");
		feat.setSmallName("wwuhr");
		feat.setValue(wwuhr);
		features.add(feat);

		feat = new Feature();
		feat.setName("wwuhl==> F41  average white width upper half from left ");
		feat.setSmallName("wwuhl");
		feat.setValue(wwuhl);
		features.add(feat);

		
		//////////////////////////////////////////////////////////////////////////
	     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("bplxrb==> F42  percent of black above the lxrb (density of pb over lxrb) "); 
		 feat.setSmallName("bplxrb"); 
		 feat.setValue(bplxrb );
		 features.add(feat);
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" w4Alxrb==> F43 white surrounds by black in after the lxrb  "); 
		 feat.setSmallName("w4Alxrb"); 
		 feat.setValue(w4Alxrb  );
		 features.add(feat);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("wwr ==> F44 average distance from border to black pixel from right "); 
		 feat.setSmallName("wwr"); 
		 feat.setValue(wwr  );
		 features.add(feat);
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" BWBWBCount ==>F45  number of rows with combinations of BWBWB  "); 
		 feat.setSmallName("BWBWBCount"); 
		 feat.setValue(BWBWBCount  );
		 features.add(feat);
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 feat = new Feature(); 
		 feat.setName(" maxBlock==> F46  Max black block size ( max stroke size ) "); 
		 feat.setSmallName("maxBlock"); 
		 feat.setValue(maxBlock  );
		 features.add(feat);
				 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" maxBlockLocation ==> F47 Location of   Max black block size ( max stroke size ) "); 
		 feat.setSmallName("maxBlockLocation"); 
		 feat.setValue(maxBlockLocation  );
		 features.add(feat);
		 
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  
		 feat = new Feature(); 
		 feat.setName("mbe==> F48 maximum with of first black pixel from right  "); 
		 feat.setSmallName("mbe"); 
		 feat.setValue(mbe  );
		 features.add(feat);
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" lbe==> F49  location of maximum with of first black pixel from right    "); 
		 feat.setSmallName("lbe"); 
		 feat.setValue(lbe  );
		 features.add(feat);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  maxBlocklxrb ==> F50  Max black block size ( max stroke size ) bellow lxrb "); 
		 feat.setSmallName("maxBlocklxrb"); 
		 feat.setValue(maxBlocklxrb  );
		 features.add(feat);
				 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" maxBlockLocationlxrb  ==>  F51  Location of Max black block size ( max stroke size ) bellow lxrb  "); 
		 feat.setSmallName("maxBlockLocationlxrb"); 
		 feat.setValue(maxBlockLocationlxrb  );
		 features.add(feat); 
		 
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  wwlhllxrb  ==> F52  average white width lower half from left below lxrb "); 
		 feat.setSmallName("wwlhllxrb"); 
		 feat.setValue(wwlhllxrb  );
		 features.add(feat);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  wwlhrlxrb ==> F53  average white width lower half from right below lxrb "); 
		 feat.setSmallName("wwlhrlxrb"); 
		 feat.setValue(wwlhrlxrb);
		 features.add(feat);
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		 feat = new Feature(); 
		 feat.setName("  w2rAlxrb ==> F54  white surronds from left, up and down after the  lxrb  "); 
		 feat.setSmallName("w2rAlxrb"); 
		 feat.setValue(w2rAlxrb  );
		 features.add(feat);
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  w4lftlxrb  ==> F55  white surronds from right, up and down after the lxrb "); 
		 feat.setSmallName("w4lftlxrb"); 
		 feat.setValue( w4lftlxrb  );
		 features.add(feat);
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  wCupLeft  ==> F56 white surronds from left and down.  "); 
		 feat.setSmallName("wCupLeft"); 
		 feat.setValue(wCupLeft  );
		 features.add(feat);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
		 feat = new Feature(); 
		 feat.setName("  pbArea ===> F57  density of black pixel over the area  "); 
		 feat.setSmallName("pbArea"); 
		 feat.setValue(pbArea  );
		 features.add(feat);
		 
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  mnb ==> F58  max transition from far to near (from the left)"); 
		 feat.setSmallName("mnb"); 
		 feat.setValue(mnb  );
		 features.add(feat);
				 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  lnrb ==> F59  location of  max transition from far to near (from the left)"); 
		 feat.setSmallName("lnrb"); 
		 feat.setValue(lnrb);
		 features.add(feat);
		
		



 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  mxb2  ==> F60  modified mxb for only upper two thrid of block "); 
		 feat.setSmallName("mxb2"); 
		 feat.setValue(mxb2  );
		 features.add(feat);
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  lxrb2 ==> F61  modified lxrb for only upper two thrid of block "); 
		 feat.setSmallName("lxrb2"); 
		 feat.setValue(lxrb2  );
		 features.add(feat);
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("  wwulxrb ===> F62 average distance to pixel from right above lxrb  "); 
		 feat.setSmallName("wwulxrb"); 
		 feat.setValue(wwulxrb  );
		 features.add(feat);
		 
		 
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" turn4===> F63 turns of 4 from high to low to high to low  "); 
		 feat.setSmallName("turn4"); 
		 feat.setValue(turn4  );
		 features.add(feat);	 
		 
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
		 feat = new Feature(); 
		 feat.setName(" wwuhrlxrb===> F64   "); 
		 feat.setSmallName("wwuhrlxrb"); 
		 feat.setValue( wwuhrlxrb );
		 features.add(feat);	 
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName("lxrbNormalized===> F65 "); 
		 feat.setSmallName("lxrbNormalized"); 
		 feat.setValue(lxrbNormalized );
		 features.add(feat);	 
		 	 	
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" minBlock===> F66 "); 
		 feat.setSmallName("minBlock"); 
		 feat.setValue(minBlock);
		 features.add(feat);	 
		 
//////////////////////////////////////////////////////////////////////////////////////////////
		 feat = new Feature(); 
		 feat.setName(" maxOminBlock===> F67"); 
		 feat.setSmallName("maxOminBlock"); 
		 feat.setValue(maxOminBlock  );
		 features.add(feat);	 
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" rowsFive===> F68 "); 
		 feat.setSmallName("rowsFive"); 
		 feat.setValue(rowsFive);
		 features.add(feat);	 
		 	 	
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		 feat = new Feature(); 
		 feat.setName(" maxBlockUpperQuarter===> F69  "); 
		 feat.setSmallName("maxBlockUpperQuarter"); 
		 feat.setValue(maxBlockUpperQuarter );
		 features.add(feat);	 
		 
		 
	 	 	
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	 feat = new Feature(); 
	 feat.setName("maxBlockUpperHalf===> F70  "); 
	 feat.setSmallName("maxBlockUpperHalf"); 
	 feat.setValue(maxBlockUpperHalf );
	 features.add(feat);	 
		 
	 
//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: nHgabLower =");
	feat.setSmallName("nHgabLower" );
	feat.setValue( nHgabLower );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: nVgab =");
	feat.setSmallName("nVgab" );
	feat.setValue( nVgab );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: imNorm =");
	feat.setSmallName("imNorm" );
	feat.setValue( imNorm );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: mbeUp =");
	feat.setSmallName("mbeUp" );
	feat.setValue( mbeUp );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: lbeUp =");
	feat.setSmallName("lbeUp" );
	feat.setValue( lbeUp );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: lbeNormalized =");
	feat.setSmallName("lbeNormalized" );
	feat.setValue( lbeNormalized );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: turn5left =");
	feat.setSmallName("turn5left" );
	feat.setValue( turn5left );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: turn5right =");
	feat.setSmallName("turn5right" );
	feat.setValue( turn5right );
	features.add(feat); 
	 
//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: avgBlockSize =");
	feat.setSmallName("avgBlockSize" );
	feat.setValue( avgBlockSize );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: bplxre6 =");
	feat.setSmallName("bplxre6" );
	feat.setValue( bplxre6 );
	features.add(feat); 

	//////////////////////////////////
	 feat = new Feature(); 
	feat.setName( " Feature: w4Blxre6 =");
	feat.setSmallName("w4Blxre6" );
	feat.setValue( w4Blxre6 );
	features.add(feat); 

	 
		 	return 0;
	}

	public int addingTransitionFeatures() {
		Feature feat;
		computeNegativeTransition();
		feat = new Feature();
		feat.setName("Count number of Negative Transition");
		feat.setSmallName("CountNegativeTransition");
		feat.setValue(CountNegativeTransition);
		features.add(feat);
		// /-------------------------------------------------------------
		// CountLargeNegativeTransition
		computeNegativeTransition();
		feat = new Feature();
		feat.setName("Count Large Negative Transition");
		feat.setSmallName("CountLargeNegativeTransition");
		feat.setValue(CountLargeNegativeTransition);
		features.add(feat);
		feat = new Feature();
		feat.setName("Count Positive Transition");
		feat.setSmallName("CountPositiveTransition");
		feat.setValue(CountPositiveTransition);
		features.add(feat);
		feat = new Feature();
		feat.setName("Count Zero Transition");
		feat.setSmallName("CountZeroTransition");
		feat.setValue(CountZeroTransition);
		features.add(feat);
		return 0;
	}

	public int addingSuddenFeatures() {
		Feature feat;
		// ////------------------------------------------------------------
		computeSuddenChange();
		feat = new Feature();
		feat
				.setName("Sudden Change From Right (large length between col of r1 and col or r2)");
		feat.setSmallName("SudenChangeFRight");
		feat.setValue(SudenChangeFRight);
		features.add(feat);
		feat = new Feature();
		feat.setName("SudenChangeFRightLocation");
		feat.setSmallName("SudenChangeFRightLocation");
		feat.setValue(SudenChangeFRightLocation);
		features.add(feat);
		feat = new Feature();
		feat.setName("SudenChangeFLeft");
		feat.setSmallName("SudenChangeFLeft");
		feat.setValue(SudenChangeFLeft);
		features.add(feat);
		feat = new Feature();
		feat.setName("SudenChangeFLeftLocation");
		feat.setSmallName("SudenChangeFLeftLocation");
		feat.setValue(SudenChangeFLeftLocation);
		features.add(feat);
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////
		return 0;
	}

	public int addingSurrondFeatures() {
		Feature feat;
		CountSurrondsNumber();
		feat = new Feature();
		feat.setName("White pixels that are Surrond by 3 black pixel");
		feat.setSmallName("srby3");
		feat.setValue(countSurr3);
		features.add(feat);
		feat = new Feature();
		feat.setName("White pixels that are Surrond by 2 black pixel");
		feat.setSmallName("srby2");
		feat.setValue(countSurr2);
		features.add(feat);
		computeSurrondRight();
		feat = new Feature();
		feat
				.setName("Number of white pixels that are Srround by three black pixel from the right till first black pixel. ");
		feat.setSmallName("SrB3FromRight");
		feat.setValue(SrB3FromRight);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Number of white pixels that are Srround by three black pixel from the left till first black pixel.");
		feat.setSmallName("SrB3FromLeft");
		feat.setValue(SrB3FromLeft);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Number of white pixels that are Srround by three black pixel from the left till first black pixel.");
		feat.setSmallName("SrB3FromUp");
		feat.setValue(SrB3FromUp);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Number of white pixels  SrB3FromDown that are Srround by three black pixel from the left till first black pixel.");
		feat.setSmallName("SrB3FromDown");
		feat.setValue(SrB3FromDown);
		features.add(feat);
		// feat=new Feature();
		// feat.setName("Surrond by 1 pixel");
		// feat.setSmallName("srby1");
		// feat.setValue(countSurr1);
		// features.add(feat);
		// feat=new Feature();
		// feat.setName("Surrond by 4 pixel");
		// feat.setSmallName("srby4");
		// feat.setValue(countSurr4);
		// features.add(feat);
		// //////////////////////////////////////////////////////
		return 0;
	}

	public int addingGabFeatures() {
		// //////////////////////////////////
		Feature feat;
		computeLhg();
		feat = new Feature();
		feat
				.setName("The length of the largest Horizontal Gap (A gab is white space between black pixels [- -])");
		feat.setSmallName("lhg");
		feat.setValue(lhg);
		features.add(feat);
		// ///////////////////////////
		// //////////////////////////////////
		computeLvg();
		feat = new Feature();
		feat.setName("The length of the Largest vertical Gap");
		feat.setSmallName("lvg");
		feat.setValue(lvg);
		features.add(feat);
		feat = new Feature();
		feat.setName("The location of the Largest Horizontal Gap ");
		feat.setSmallName("lhgi");
		// computeLhg();
		feat.setValue(lhgi);
		features.add(feat);
		feat = new Feature();
		feat.setName("The location of the Largest vertical Gap ");
		feat.setSmallName("lvgi");
		feat.setValue(lvgi);
		features.add(feat);
		feat = new Feature();
		feat.setName("Count number of Vertical Gaps");
		feat.setSmallName("Count1VG");
		feat.setValue(Count1VG);
		features.add(feat);
		feat = new Feature();
		feat.setName(" Average length of vertical gaps");
		feat.setSmallName("AvgVGapLength");
		feat.setValue(AvgVGapLength);
		features.add(feat);
		return 0;
	}

	public int addingBlockFeatures() {
		Feature feat;
		// ////////////////////////////////////////////////////////////////////////////////////////
		computeLargestHorizentalBlackBlock();
		feat = new Feature();
		feat
				.setName(" Max Horizontal Black Block (continous black pixel in a direction) Length ");
		feat.setSmallName("MaxHBlackLength");
		feat.setValue(MaxHorizentalBlackBlockLength);
		features.add(feat);
		feat = new Feature();
		feat.setName(" Average Horizental Black Block Length ");
		feat.setSmallName("AvgHBlackLength");
		feat.setValue(AvgHorizentalBlackBlockLength);
		features.add(feat);
		// //////////////////////
		feat = new Feature();
		feat.setName(" Max Horizental Black Block Length Row Location ");
		feat.setSmallName("MaxHBlackLengthLocation");
		feat.setValue(MaxHorizentalBlackBlockLengthRowLocation);
		features.add(feat);
		// ///////////////////////////////////
		feat = new Feature();
		feat.setName(" Max count Of Horizontal Black Blocks in a Row ");
		feat.setSmallName("MaxNumberOfHBlackBlocks");
		feat.setValue(MaxNumberOfHorizontalBlackBlockCount);
		features.add(feat);
		// //////////////////////////////////////
		feat = new Feature();
		feat
				.setName("The Row location of the  Max count Of Horizontal Black Block in a row  ");
		feat.setSmallName("MaxNumberOfHBlackBlocksLoc");
		feat.setValue(MaxNumberOfHorizontalBlackBlockCountRowLocation);
		features.add(feat);
		// ////////////////////////////////////////////
		//							
		// ////////////////////////////////////////////
		computeLargestVerticalBlackBlock();
		feat = new Feature();
		feat.setName("  Max Vertical Black Block Length ");
		feat.setSmallName("MaxVBlackLength");
		feat.setValue(MaxVerticalBlackBlockLength);
		features.add(feat);
		// ////////////////////////////////////////////
		feat = new Feature();
		feat.setName(" Max Vertical Black Block Length Col Location ");
		feat.setSmallName("MaxVBlackLengthLocation");
		feat.setValue(MaxVerticalBlackBlockLengthColLocation);
		features.add(feat);
		// ////////////////////////////////////////////
		feat = new Feature();
		feat.setName(" Max Number Of Vertical BlackBlocks  ");
		feat.setSmallName("MaxNumberOfVBlackBlocks");
		feat.setValue(MaxVerticalblocksCount);
		features.add(feat);
		feat = new Feature();
		feat.setName(" Max Vertical blocks Count Col Location ");
		feat.setSmallName("MaxNumberOfVBlackBlocksLoc");
		feat.setValue(MaxVerticalblocksCountColLocation);
		features.add(feat);
		// AvgVerticalBlockLengthInRight
		feat = new Feature();
		feat.setName("AvgVerticalBlockLengthInRight");
		feat.setSmallName("AvgVerticalBlockLengthInRight");
		feat.setValue(AvgVerticalBlockLengthInRight);
		features.add(feat);
		// //////////////////////////////////////////////////////////////
		return 0;
	}

	public int addingWhiteTillBlack() {
		Feature feat;
		countWhiteBlockTillBlack();
		feat = new Feature();
		feat
				.setName("Count of White pixels from upper border till the first black pixel ");
		feat.setSmallName("frup");
		feat.setValue(fromUp);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Count of White pixels from lower border (down) till the first black pixel ");
		feat.setSmallName("frdown");
		feat.setValue(fromDown);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Count of White pixels from left border till the first black pixel");
		feat.setSmallName("frleft");
		feat.setValue(fromLeft);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Count of White pixels from right border till the first black pixel");
		feat.setSmallName("frright");
		feat.setValue(fromRight);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Direction of Maximum count of white pixels (up,down,left..)");
		feat.setSmallName("dirMaxW");
		feat.setValue(LocationOfMaxWhite);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Count of White pixels from lower border (down) till the first black pixel in the Left half of the digit");
		feat.setSmallName("fromDownLeft");
		feat.setValue(fromDownLeft);
		features.add(feat);
		// ---------------------------------------------------
		computeWhiteSides();
		feat = new Feature();
		feat.setName("FromRightUp");
		feat.setSmallName("FromRightUp");
		feat.setValue(FromRightUp);
		features.add(feat);
		feat = new Feature();
		feat.setName("FromLeftDown");
		feat.setSmallName("FromLeftDown");
		feat.setValue(FromLeftDown);
		features.add(feat);
		feat = new Feature();
		feat.setName("FromLeftUp");
		feat.setSmallName("FromLeftUp");
		feat.setValue(FromLeftUp);
		features.add(feat);
		return 0;
	}

	public int addingWideFeatures() {
		Feature feat;
		computeWideFeatures();
		feat = new Feature();
		feat.setName("Average width of the digit");
		feat.setSmallName("BlackWide");
		feat.setValue(BlackWide);
		features.add(feat);
		// ---------------------------------------------------
		feat = new Feature();
		feat.setName("Average Width of the digit in the upper half ");
		feat.setSmallName("AverageWideUp");
		feat.setValue(AverageWideUp);
		features.add(feat);
		// --------------------------------------------------------------------------------------------
		feat = new Feature();
		feat.setName("Count number of row that has smaller than 1/4 width");
		feat.setSmallName("CountLowWide");
		feat.setValue(CountLowWide);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Count number of rowsBlocks that has width larger than 1/2 width ");
		feat.setSmallName("CountBigWide");
		feat.setValue(CountBigWide);
		features.add(feat);
		computeVerticalLength();
		// AllFeatures.add("BlackWideVertical");
		// AllFeatures.add("CountBigWideVertical");
		// AllFeatures.add("CountLowWideVertical");
		// AllFeatures.add("AverageWideLeftVertical");
		// //--------------------------------------------------------------------------------------------
		feat = new Feature();
		feat.setName("BlackWideVertical");
		feat.setSmallName("BlackWideVertical");
		feat.setValue(BlackWideVertical);
		features.add(feat);
		// ---------------------------------------------------
		feat = new Feature();
		feat.setName("CountBigWideVertical");
		feat.setSmallName("CountBigWideVertical");
		feat.setValue(CountBigWide);
		features.add(feat);
		feat = new Feature();
		feat.setName("CountLowWideVertical");
		feat.setSmallName("CountLowWideVertical");
		feat.setValue(CountLowWide);
		features.add(feat);
		feat = new Feature();
		feat.setName("AverageWideLeftVertical");
		feat.setSmallName("AverageWideLeftVertical");
		feat.setValue(AverageWideLeftVertical);
		features.add(feat);
		feat = new Feature();
		feat.setName("AverageWideRightVertical");
		feat.setSmallName("AverageWideRightVertical");
		feat.setValue(AverageWideRightVertical);
		features.add(feat);
		// feat=new Feature();
		// feat.setName("BlackWideVertical");
		// feat.setSmallName("BlackWideVertical");
		//																		
		// feat.setValue(BlackWideVertical);
		//																			
		// features.add(feat);
		// //---------------------------------------------------
		//		 
		//		
		//		 
		// feat=new Feature();
		// feat.setName("CountBigWideVertical");
		// feat.setSmallName("CountBigWideVertical");
		// feat.setValue(CountBigWide );
		// features.add(feat);
		//		 
		//		 
		// feat=new Feature();
		// feat.setName("CountLowWideVertical");
		// feat.setSmallName("CountLowWideVertical");
		// feat.setValue(CountLowWide );
		// features.add(feat);
		//		 
		//		 
		// feat=new Feature();
		// feat.setName("AverageWideLeftVertical");
		// feat.setSmallName("AverageWideLeftVertical");
		// feat.setValue(AverageWideLeftVertical);
		// features.add(feat);
		//		 
		// feat=new Feature();
		// feat.setName("AverageWideRightVertical");
		// feat.setSmallName("AverageWideRightVertical");
		// feat.setValue( AverageWideRightVertical);
		// features.add(feat);
		return 0;
	}

	public int addingPercentInRows() {
		Feature feat;
		// //////////////////////////////////////
		computePercentBlackInDirection();
		feat = new Feature();
		feat
				.setName(" Percent of Black in Left half / Percent of black in Right half");
		feat.setSmallName("PBinLeftVsRight");
		feat.setValue(PBinLeftVsRight);
		features.add(feat);
		// ------------------------------------------------------
		feat = new Feature();
		feat
				.setName("Percent of Black in upper half / Percent of black in lower half");
		feat.setSmallName("PBinUpVsDown");
		feat.setValue(PBinUpVsDown);
		features.add(feat);
		// ------------------------------------------------------
		feat = new Feature();
		feat.setSmallName("PbCountD");
		feat.setName("Percent of black in lower half");
		feat.setValue(PbCountD);
		features.add(feat);
		// ------------------------------------------------------
		feat = new Feature();
		feat.setName("Percent of black in upper half");
		feat.setSmallName("PbCountU");
		feat.setValue(PbCountU);
		features.add(feat);
		// ------------------------------------------------------
		feat = new Feature();
		feat.setName("Percent of black in left half");
		feat.setSmallName("PbCountL");
		feat.setValue(PbCountL);
		features.add(feat);
		// ------------------------------------------------------
		feat = new Feature();
		feat.setName("Percent of black in right half");
		feat.setSmallName("PbCountR");
		feat.setValue(PbCountR);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Percent of black in the right over the total number of black pixels");
		feat.setSmallName("PbCountLOverCountInRight");
		feat.setValue(PbCountLOverCountInRight);
		features.add(feat);
		// ///////////////////////////////////////////////////////////////
		//								
		// --------------------------------------------------------------
		computePercentInRows();
		feat = new Feature();
		feat.setName("Percent of black pixel in the first four rowsBlocks");
		feat.setSmallName("PbinF4R");
		feat.setValue(PbinF4R);
		features.add(feat);
		feat = new Feature();
		feat.setName("Percent of black pixel in the last four rowsBlocks ");
		feat.setSmallName("PbinL4R");
		feat.setValue(PbinL4R);
		features.add(feat);
		feat = new Feature();
		feat.setName("Percent of black pixel in the last four columns ");
		feat.setSmallName("PbinL4C");
		feat.setValue(PbinL4C);
		features.add(feat);
		// --------------------------------------------------------------------------------------------
		// see previous
		feat = new Feature();
		feat.setName("Percent of black pixel in the first four columns");
		feat.setSmallName("PbinF4C");
		feat.setValue(PbinF4C);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("Percent of black pixel in the first four columns in the upper half");
		feat.setSmallName("PbinF4CinUpper");
		feat.setValue(PbinF4CinUpper);
		features.add(feat);
		return 0;
	}

	public int addingGeneralFeatures() {
		Feature feat;
		computeHW();
		feat = new Feature();
		feat.setName("Height over width");
		feat.setSmallName("hOw");
		feat.setValue(heightoverwidth);
		features.add(feat);
		computeWsb();
		// //////////////////////////////////
		feat = new Feature();
		feat.setName("white pixels surrond by black from four directions");
		feat.setSmallName("wsb");
		feat.setValue(wsb);
		features.add(feat);
		// //////////////////////////////////
		// ///////////////////////////
		// ///////////////////////////
		computeMcr();
		// //////////////////////////////////
		feat = new Feature();
		feat.setName(" Center of gravity (centroid) in x direction");
		feat.setSmallName("cx");
		feat.setValue(cx);
		features.add(feat);
		// //////////////////////////////////
		// //////////////////////////////////
		feat = new Feature();
		feat
				.setName("Mean of the radius from each point to the center of gravity (centroid)");
		feat.setSmallName("MCR");
		feat.setValue(mcr);
		features.add(feat);
		feat = new Feature();
		feat.setName(" Center of gravity (centroid) in x direction ");
		feat.setSmallName("cy");
		feat.setValue(cy);
		features.add(feat);
		feat = new Feature();
		feat.setName("Percent of black pixels with respect to all pixels.");
		feat.setSmallName("pb");
		computePb();
		feat.setValue(pb);
		features.add(feat);
		whiteInLowerAndInUpper();
		feat = new Feature();
		feat.setName("white surrond  by black in lower half of the digit");
		feat.setSmallName("wsbInLower");
		feat.setValue(countWsbLower);
		features.add(feat);
		feat = new Feature();
		feat
				.setName("white surrond  by black in upper in the upper half of the digit");
		feat.setSmallName("wsbInUpper");
		feat.setValue(countWsbUppper);
		features.add(feat);
		// /
		// --------------------------------------------------------------------------------------------
		computeAverageOfLast();
		feat = new Feature();
		feat.setName("Average location of the Last in a rowsBlocks  In lower");
		feat.setSmallName("AverageLastInlower");
		feat.setValue(AverageLastInlower);
		features.add(feat);
		// --------------------------------------------------------------------------------------------
		feat = new Feature();
		feat.setName("Average location of the Last in a rowsBlocks in Upper");
		feat.setSmallName("AverageLastinUpper");
		feat.setValue(AverageLastinUpper);
		features.add(feat);
		return 0;
	}

	public int addingBorderFeatures() {
		Feature feat;
		// --------------------------------------------------------------------------------------------
		computeBorderFeatures();
		feat = new Feature();
		feat
				.setName("Length of difference between first and last location of right border");
		feat.setSmallName("BorLocationLengthInLastCol");
		feat.setValue(BorderLocationLengthInLastColumn);
		features.add(feat);
		feat = new Feature();
		feat.setName("Location of last pixel (column wise) in lower border");
		feat.setSmallName("BorLocationDownEnd");
		feat.setValue(BorderLocationDownEnd);
		features.add(feat);
		feat = new Feature();
		feat.setName("Location of last pixel (row wise) in right border");
		feat.setSmallName("BorLocationLastColumn");
		feat.setValue(BorderLocationLastColumn);
		features.add(feat);
		feat = new Feature();
		feat.setName("Length of lower border in row direction");
		feat.setSmallName("BorLocationDownLength");
		feat.setValue(BorderLocationDownLength);
		features.add(feat);
		// AllFeatures.add("BorderLocationUpLength");
		feat = new Feature();
		feat.setName("Length of  upper border in row direction");
		feat.setSmallName("BorderLocationUpLength");
		feat.setValue(BorderLocationUpLength);
		features.add(feat);
		// AllFeatures.add("BorderLocationFColLength");
		// AllFeatures.add("BorderLocationUpEnd");
		// AllFeatures.add("BorderLocationFColEnd");
		feat = new Feature();
		feat.setName("BorderLocationFColLength");
		feat.setSmallName("BorderLocationFColLength");
		feat.setValue(BorderLocationFColLength);
		features.add(feat);
		feat = new Feature();
		feat.setName("BorderLocationUpEnd");
		feat.setSmallName("BorderLocationUpEnd");
		feat.setValue(BorderLocationUpEnd);
		features.add(feat);
		;
		feat = new Feature();
		feat.setName("BorderLocationFColEnd");
		feat.setSmallName("BorderLocationFColEnd");
		feat.setValue(BorderLocationFColEnd);
		features.add(feat);
		// --------------------------------------------------------------------------------------------
		computeCountWhiteWithBorder();
		// AllFeatures.add("FromDownAfterBorDown");
		// AllFeatures.add("FromLeftBeforeBorLeft");
		// AllFeatures.add("FromLeftAfterBorLeft");
		//			   
		feat = new Feature();
		feat.setName("FromDownAfterBorDown");
		feat.setSmallName("FromDownAfterBorDown");
		feat.setValue(FromDownAfterBorDown);
		features.add(feat);
		feat = new Feature();
		feat.setName("FromLeftBeforeBorLeft");
		feat.setSmallName("FromLeftBeforeBorLeft");
		feat.setValue(FromLeftBeforeBorLeft);
		features.add(feat);
		feat = new Feature();
		feat.setName("FromLeftAfterBorLeft");
		feat.setSmallName("FromLeftAfterBorLeft");
		feat.setValue(FromLeftAfterBorLeft);
		features.add(feat);
		return 0;
	}
}
