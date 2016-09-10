package data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataSet extends Observable implements Cloneable, Serializable {
	public static final String FILE_INPUT_EXTENTION_ARFF = "arff";
	public static final String FILE_INPUT_EXTENTION_CSV = "csv";

	public static final String FILE_INPUT_EXTENTION_EXCELL = "xls";
	public static final String FILE_INPUT_EXTENTION_LIBSVM = ".xt";
	public static final String FILE_INPUT_EXTENTION_TORCH = "txt";
	public static final int FILE_INPUT_FORMAT_ARFF = 3;
	public static final int FILE_INPUT_FORMAT_CSV = 1;

	public static final int FILE_INPUT_FORMAT_EXCELL = 4;
	public static final int FILE_INPUT_FORMAT_LIBSVM = 2;
	public static final int FILE_INPUT_FORMAT_TORCH = 0;
	private static transient final Logger logger = Logger
			.getLogger(DataSet.class);
	private static final long RandomSEED = 55;

	protected HashMap _labelToType = null;
	// transient FeatureSet features=null;
	String AllFeatureString = "";
	transient ArrayList<String> ClassLabels;

	ArrayList<Integer> ClassSamples; // count of the number of sample in each
										// categories
	ArrayList<Integer> ClassValues;
	private boolean ClassValuesGenerated = false;
	transient double data[][] = null;
	transient ArrayList<double[]> dataAdd = null;
	int dataTargets[] = null;
	boolean FeatureDataSet = false;

	ArrayList<String> featuresNames;
	int format = FILE_INPUT_FORMAT_TORCH; // format to read
	protected int NumOfClasses = 2;

	// read and save file from/to torch format.
	// read and save file from/to libsvm format.
	// read and save file from/to arff format.
	int NumOfFeatures = 0;
	protected int NumOfSamples = 0;
	ArrayList<Integer> Offsets = null;
	ArrayList<String> OrignalLocations = null;
	boolean RandomizeData = false;
	private boolean SaveSampleOffset = false;
	boolean shuffled = false;
	private double SplitPercent = 0.75;
	boolean StoreLocations = false;
	transient ArrayList<Integer> TargetsAdd = null;
	private ArrayList<String> targetsString;

	transient ArrayList<double[]> trainData;
	transient ArrayList<Integer> trainDataTargets;

	protected int TrainSamples = 0;
	boolean twoClass = false;
	boolean UseAllFeatures = true;

	// private boolean binaryClasses;

	private boolean UseStringLabels = false;

	transient ArrayList<double[]> Validation;

	boolean ValidationCreated = false;

	protected int ValidationSamples = 0;
	transient ArrayList<Integer> validationTargets;
	boolean valuesOk;

	public DataSet() {

	}

	public DataSet(DataSet tset) {

		this.ClassLabels = (ArrayList<String>) tset.ClassLabels.clone();
		this.ClassValues = (ArrayList<Integer>) tset.ClassValues.clone();
		this.ClassSamples = (ArrayList<Integer>) tset.ClassSamples.clone();
		// this.features=tset.features;
		this.NumOfFeatures = tset.NumOfFeatures;
		this.NumOfSamples = tset.NumOfSamples;

	}

	// public void addSample(Digit image) {
	// logger.trace("inside sample digit  ");
	//
	// if (dataAdd==null)
	// {
	// dataAdd=new ArrayList<double []>();
	// TargetsAdd=new ArrayList<Integer>();
	// }
	// dataAdd.add(image.getFeaturesArray());
	// TargetsAdd.add(image.getLabel());
	// NumOfSamples=dataAdd.size();
	//
	// }
	public void addSample(double[] vals, int i) {
		if (dataAdd == null) {
			dataAdd = new ArrayList<double[]>();
			TargetsAdd = new ArrayList<Integer>();
		}
		dataAdd.add(vals);
		TargetsAdd.add(new Integer(i));
		NumOfSamples = dataAdd.size();

	}

	public void addSampleLocation(String string) {
		if (OrignalLocations == null) {
			OrignalLocations = new ArrayList<String>();
		}
		OrignalLocations.add(string);

	}

	public void clear() {
		// cear all data....................

	}

	/**
	 */

	// public void CreateValidationSet(){
	// if (data==null){
	//
	// ConvertToArrays();
	//
	// }
	//
	// if (RandomizeData){
	// if (!shuffled){
	// logger.trace("  now starting toshuffle the data");
	// ShuffleDataSet();
	// logger.trace("  Finish the shuffling procedure ");
	// //}
	// }
	//
	//
	// // if (NumOfSamples==0){
	// // NumOfSamples=data.length;
	// // }
	// TrainSamples=(int)(SplitPercent*this.NumOfSamples);
	// logger.info("number of training samples "+TrainSamples);
	// ValidationSamples=(int)((100-SplitPercent)*this.NumOfSamples);//(int)(0.2*this.NumOfSamples);
	//
	// }
	// else{
	//
	// GenerateSequencedValidation();
	// }
	// ValidationCreated=true;
	//
	//
	//
	// }
	// protected void GenerateSequencedValidation(){
	// //int totalSamples=(int)(SplitPercent*this.NumOfSamples);
	// // int totalvalidSamples=(int)((100-SplitPercent)*this.NumOfSamples);
	//
	// //init the train and validat
	// trainData=new ArrayList<double[]> ();
	// Validation=new ArrayList<double[]>();
	// trainDataTargets=new ArrayList<Integer>();
	// validationTargets=new ArrayList<Integer>();
	// if ( ClassValuesGenerated==false){
	// fixClassArray();
	//
	//
	// }
	// double tempFeat[]=null;
	//
	// int trainclasssample;
	// // int validclasssample;
	//
	//
	// ArrayList<Integer> currentCount=new ArrayList<Integer>();
	//
	// int targ;
	// int currentSample=0;
	// int samp;
	// // int vi,ti;
	// // vi=0;
	// // ti=0;
	// for (int i = 0; i <ClassValues.size(); i++) {
	// currentCount.add(new Integer(0));
	// }
	//
	// for (int i = 0; i < data.length; i++) {
	//
	// // get data targes
	// targ=dataTargets[i];
	//
	// if (i%1500==0){
	// logger.info(" Moving data "+i);
	// //logger.info("current target is "+targ);
	// }
	//
	// for (int j = 0; j < ClassValues.size(); j++) {
	// if ( ClassValues.get(j).equals( targ))
	// {
	// samp=ClassSamples.get(j);
	// /// number of samples of this class or categories
	// // and the trainsample is the
	// trainclasssample=(int)(SplitPercent*samp);
	// // logger.info("  size  "+currentCount.size()+"  j "+j);
	//
	// currentSample=currentCount.get(j);
	//
	//
	// //
	// logger.info(" current samples "+currentSample+"   trian sample of this "+trainclasssample);
	//
	// //// nowo add it to validation or samples
	// tempFeat=new double[data[i].length];
	//
	//
	//
	// for (int k = 0; k <data[i].length; k++) {
	// tempFeat[k]=data[i][k];
	//
	// }//for moving data to trian and validation
	//
	//
	// if(currentSample<trainclasssample){
	// trainData.add(tempFeat);
	// trainDataTargets.add(dataTargets[i]);
	//
	// }
	// else {
	// Validation.add(tempFeat);
	// validationTargets.add(dataTargets[i]);
	//
	// }
	//
	// // if(currentSample<trainclasssample){
	// // ti++;
	// // }
	// // else {
	// // vi++;
	// // } //else (incrementing the indeces....
	//
	//
	// //now increment current coutn
	// currentCount.set(j, new Integer(currentSample+1));
	//
	// break;
	// }/// if same sample
	// }//for looking for class label
	//
	//
	//
	//
	// }/// for all data
	//
	//
	// logger.info(" Finished Moving the data ");
	// }//function

	@Override
	protected Object clone() throws CloneNotSupportedException {

		DataSet tempdata = null;
		tempdata = (DataSet) super.clone();
		// tempdata.features= (FeatureSet) features.clone();
		// tempSolution.particlePoints=(int [])data.clone();
		// tempSolution.velocities=velocities.clone();

		// tempSolution.polygonVertices=(ArrayList<Point2D>)this.polygonVertices.clone();

		return tempdata;

	}

	public void compareDataSet(DataSet dset) {

		int minF, minS;
		minS = NumOfSamples;
		// if sets has different number of sample
		if (dset.NumOfSamples < NumOfSamples) {
			minS = dset.NumOfSamples;
		}
		if (dset.NumOfSamples != NumOfSamples) {

			logger.info(" number of sample is different but will work on min which is  "
					+ minS);
		}
		// if sets has different number of features
		minF = NumOfFeatures;
		if (dset.NumOfFeatures < NumOfFeatures) {
			minF = dset.NumOfFeatures;
		}
		if (dset.NumOfFeatures != NumOfFeatures) {

			logger.info(" Number of features is different but will work on min which is "
					+ minF);
		}
		// //////////now looping on the samples in all feautes
		int errors = 0;
		for (int i = 0; i < minS; i++) {
			Double[] sample = getSample(i);
			Double[] tsample = dset.getSample(i);
			for (int j = 0; j < minF; j++) {
				if (!tsample[j].equals(sample[j])) {
					logger.info("  There is features " + j
							+ "  not equal in both data set in Sample  " + i
							+ "   first data value is " + sample[j]
							+ "  and the second is " + tsample[j]);
					errors++;
					break;
				}
			}
		}
	}

	public void ConvertToArrays() {
		if (dataAdd != null) {
			data = new double[dataAdd.size()][dataAdd.get(0).length];
			dataTargets = new int[TargetsAdd.size()];
			for (int i = 0; i < dataAdd.size(); i++) {
				dataTargets[i] = TargetsAdd.get(i);
				for (int j = 0; j < dataAdd.get(i).length; j++) {
					data[i][j] = dataAdd.get(i)[j];
				}
			}
			this.NumOfSamples = dataAdd.size();
			this.NumOfFeatures = dataAdd.get(0).length;
			// this.UseAllFeatures=true;
			// if (this.ClassLabels==null){
			// fixClassArray();
			// }
		}
	}

	private void ConvertToList() {

		if (data != null) {
			dataAdd = new ArrayList<double[]>();
			TargetsAdd = new ArrayList<Integer>();
			for (int i = 0; i < data.length; i++) {
				TargetsAdd.add(dataTargets[i]);// =TargetsAdd.get(i);
				dataAdd.add(data[i]);
				// for (int j = 0; j < dataAdd.get(i).length; j++) {
				// data[i][j]=dataAdd.get(i)[j];
				// }
			}
			this.NumOfSamples = dataAdd.size();
			if (dataAdd.size() == 0)
				this.NumOfFeatures = 0;
			else
				this.NumOfFeatures = dataAdd.get(0).length;
			// this.UseAllFeatures=true;
		}
	}

	// public void addSample(ArrayList<Feature> features2, int x, int offset) {
	// addSample( features2,x);
	// if(Offsets==null){
	// Offsets=new ArrayList<Integer>();
	//
	// }
	// Offsets.add( new Integer(offset));
	//
	// }
	// public void setStoreSampleOffset(boolean b) {
	// SaveSampleOffset=b;
	//
	// }
	public void doNoShuffle() {
		shuffled = true;

	}

	public void FixArrayAndClassLabel() {
		if (data == null) {
			ConvertToArrays();
		}
		if (this.ClassLabels == null) {
			fixClassArray();
		}
	}

	private void fixClassArray() {

		ClassLabels = new ArrayList<String>();
		ClassValues = new ArrayList<Integer>();
		ClassSamples = new ArrayList<Integer>();
		Integer targ = new Integer(0);
		Integer Count;
		boolean found = false;
		if (dataTargets == null) {
			ConvertToArrays();
		}
		for (int i = 0; i < dataTargets.length; i++) {
			targ = dataTargets[i];
			found = false;
			for (int j = 0; j < ClassValues.size(); j++) {
				if (ClassValues.get(j).equals(targ)) {
					found = true;
					Count = ClassSamples.get(j) + 1;
					ClassSamples.set(j, Count);

					break;
				}
			}

			if (!found) {// not found in current classs values
				boolean added = false;
				// i want to addd it in a sorted way
				for (int j = 0; j < ClassValues.size(); j++) {

					if (ClassValues.get(j) > targ) {
						added = true;
						logger.info("adding the " + targ + "  in location " + j);
						ClassValues.add(j, targ);
						ClassLabels.add(j, "" + targ);
						ClassSamples.add(j, new Integer(1));

						break;
					}
				}
				if (added == false) {// add in the end
					ClassValues.add(targ);
					ClassLabels.add("" + targ);
					ClassSamples.add(new Integer(1));
				}

			}

		}// for targets
		logger.trace(" There are " + this.NumOfClasses
				+ " classes in the data set ");
		for (int i = 0; i < ClassSamples.size(); i++) {
			logger.trace("  the label " + ClassValues.get(i) + "  has "
					+ ClassSamples.get(i) + "  sample ");
		}
		this.NumOfClasses = ClassValues.size();

		ClassValuesGenerated = true;

	}

	private void fixClassBinaryArray(int i, int j) {

		ClassLabels = new ArrayList<String>();
		ClassValues = new ArrayList<Integer>();

		ClassLabels.add("" + i);
		ClassValues.add(1);
		ClassLabels.add("" + j);
		ClassValues.add(-1);

		this.NumOfClasses = ClassValues.size();

	}

	public void GenearteBinaryTargest(int i, int j) {

		for (int j2 = 0; j2 < this.NumOfSamples; j2++) {
			// for each target
			if (this.dataTargets[j2] == i) {
				dataTargets[j2] = 1;
			} else {
				dataTargets[j2] = -1;
			}
		}
		fixClassBinaryArray(i, j);
	}

	public String getAllFeatureString() {
		return AllFeatureString;
	}

	public ArrayList<Integer> getAllTargets() {

		ArrayList<Integer> tar = new ArrayList<Integer>();

		for (int i = 0; i < this.NumOfSamples; i++) {
			tar.add(new Integer(dataTargets[i]));
		}
		return tar;
	}

	protected int getByValue(HashMap<Integer, String> map, String type) {

		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, String> e = (Entry<Integer, String>) iterator.next();
			if (e.getValue().equals(type)) {
				return e.getKey();
			}

		}

		return -1;
	}

	// @Deprecated
	// public void initDataSetFeatures(ArrayList<String> computedFeatures) {
	// logger.info("inside the init of data set... ");
	// if (computedFeatures==null){
	// logger.info("compute all featues...............");
	//
	// }
	//
	// features=new FeatureSet();
	// ArrayList<FeatureStat> FeatureStatList=new ArrayList<FeatureStat>();
	// FeatureStat feat;
	// String tempF;
	// int indexF;
	// AllFeatureString="";
	// this.NumOfFeatures=computedFeatures.size();
	// for (int i = 0; i < computedFeatures.size(); i++) {
	// tempF=computedFeatures.get(i);
	// indexF=i;
	// feat=new FeatureStat();
	// feat.setFeatureIndex(indexF);
	// feat.setFeatureName(tempF);
	// AllFeatureString+=tempF;
	// AllFeatureString+=",";
	// logger.info(" adding feature "+tempF+" in the index "+indexF);
	// FeatureStatList.add(feat);
	// }
	//
	// UseAllFeatures=true;
	// // if (FeatureStatList.size()==2){
	// // this.twoClass=true;
	// // }
	// if (FeatureStatList.size()!=this.NumOfFeatures){
	//
	// UseAllFeatures=false;
	// logger.error(" Error the number of featurs in data file not equal to that in the feature file ");
	// logger.error(" Reading  ... "+NumOfFeatures+"  feature from the data file and "+FeatureStatList.size()+" feature from the feautre file");
	// }
	//
	// features.setAllFeaturesRead(FeatureStatList);
	//
	// }
	// public ArrayList<FeatureStat> getComputeFeatureState() {
	// ArrayList<FeatureStat> fstate=new
	// ArrayList<FeatureStat>(features.getAllFeaturesRead());
	//
	//
	// if ( ClassValuesGenerated==false){
	// fixClassArray();
	//
	//
	// }
	// //AppDefaults.CATEGORY_SIZE=ClassValues.size();//ClassValues.size()
	//
	//
	// int CategoryCounts[]=new int [ClassValues.size()];
	//
	// for (int i = 0; i < CategoryCounts.length; i++) {
	// CategoryCounts[i]=0;
	// }
	// for (int i = 0; i < fstate.size(); i++) {
	// fstate.get(i).changeSize(ClassValues.size() );
	// }
	// for (int i = 0; i < fstate.size(); i++) {
	// for (int j = 0; j < ClassValues.size(); j++) {
	// fstate.get(i).setClassValue( ClassValues.get(j)+"", j);
	// }
	//
	//
	// }
	//
	//
	// double [] d;
	// double avg=0,max,min,std;
	// int digit,digitIndex;
	// int offset;//=Offsets.get(i);
	// String OrginalLoc="";
	// for (int i = 0; i <dataAdd.size(); i++) {
	//
	// d=dataAdd.get(i);
	// offset=Offsets.get(i);
	// if (StoreLocations)
	// OrginalLoc= OrignalLocations.get(i);
	//
	// digitIndex=TargetsAdd.get(i);
	// digit=getClassIndex(digitIndex);
	//
	//
	// CategoryCounts[digit]++;
	// for (int k = 0; k < d.length; k++) {
	//
	//
	// //adding the average
	// avg= fstate.get(k).getAvg()[digit];
	// avg+=d[k];
	// fstate.get(k).setAvg(avg, digit);
	//
	// // checking the min
	//
	// min= fstate.get(k).getMin()[digit];
	// if (min>d[k]){
	//
	// fstate.get(k).setMin(d[k], digit);
	//
	// if (StoreLocations)
	// fstate.get(k).setMinFilename(OrginalLoc,digit);
	// else
	// fstate.get(k).setMinFilenameIndex( offset,digit);
	//
	// }
	//
	// // checking the max.
	// max= fstate.get(k).getMax()[digit];
	// if (max<d[k]){
	// fstate.get(k).setMax(d[k], digit);
	// if (StoreLocations)
	// fstate.get(k).setMaxFilename( OrginalLoc, digit);
	// else
	// fstate.get(k).setMaxFilenameIndex( offset, digit);
	// }
	//
	//
	//
	// }
	// //adjusting
	//
	//
	// }
	//
	// //correcting all the averages
	// for (int k = 0; k <fstate.size(); k++) {
	// //adding the average
	// double [] avgArray= fstate.get(k).getAvg();
	// for (int i = 0; i < avgArray.length; i++) {
	// avgArray[i]/=(double)CategoryCounts[i];
	// }
	//
	//
	// }
	// ////////////////////now compute the std
	// double temp=0;
	// for (int i = 0; i <dataAdd.size(); i++) {
	//
	// d=dataAdd.get(i);
	// // digit=TargetsAdd.get(i);
	// digitIndex=TargetsAdd.get(i);
	// digit=getClassIndex(digitIndex);
	// CategoryCounts[digit]++;
	// for (int k = 0; k < d.length; k++) {
	//
	//
	// //adding the average
	// std= fstate.get(k).getStd()[digit];
	//
	// avg= fstate.get(k).getAvg()[digit];
	// temp=(d[k]-avg)*(d[k]-avg);
	// std+=temp;
	// fstate.get(k).setStd(std, digit);
	//
	//
	// }
	// //adjusting
	//
	//
	// }
	//
	//
	//
	// // now compute the std.
	// for (int k = 0; k <fstate.size(); k++) {
	// //adding the average
	// double [] stdArray= fstate.get(k).getStd();
	// for (int i = 0; i < stdArray.length; i++) {
	// stdArray[i]/=(double)CategoryCounts[i];
	// }
	//
	//
	// }
	//
	//
	//
	//
	//
	//
	// // for (int i = 0; i < ClassValues.size(); i++) {
	// // FeatureStat fs=new FeatureStat ();
	// // fstate.add(fs) ;
	// // }
	//
	// // for each category in the cat
	//
	//
	//
	// return fstate;
	//
	// }
	private int getClassIndex(int digitIndex) {
		int index = 0;
		for (int j = 0; j < ClassValues.size(); j++) {
			if (digitIndex == ClassValues.get(j)) {
				return j;
			}

		}

		return 0;
	}

	// public void initAllFeatures(String filename) {
	//
	//
	//
	// filename= filename.replace(".txt", "_Feat.txt");
	// // String line;
	// if (features!=null)
	// return ;
	//
	// try {
	// File afile ;
	// Scanner input;
	// logger.info("reading the file feature ................ wait");
	// try{
	// afile = new File(filename);
	// input = new Scanner(new BufferedReader(new FileReader(afile)));
	// }catch (FileNotFoundException e) {
	//
	// logger.info("file not found will try using the default file ");
	// afile=new File("feat.txt");
	// input = new Scanner(new BufferedReader(new FileReader(afile)));
	//
	// // e.printStackTrace();
	// }
	//
	//
	//
	//
	// features=new FeatureSet();
	// ArrayList<FeatureStat> FeatureStatList=new ArrayList<FeatureStat>();
	// FeatureStat feat;
	// String tempF;
	// int indexF;
	// AllFeatureString="";
	// while (input.hasNext()) {
	// tempF=input.nextLine();
	// indexF=Integer.parseInt(input.nextLine());
	//
	// feat=new FeatureStat();
	// feat.setFeatureIndex(indexF);
	// feat.setFeatureName(tempF);
	// AllFeatureString+=tempF;
	// AllFeatureString+=",";
	// logger.info(" adding feature "+tempF+" in the index "+indexF);
	// FeatureStatList.add(feat);
	// }
	// UseAllFeatures=true;
	// // if (FeatureStatList.size()==2){
	// // this.twoClass=true;
	// // }
	// if (FeatureStatList.size()!=this.NumOfFeatures){
	//
	// UseAllFeatures=false;
	// logger.error("Error the number of featurs in data file not equal to that in the feature file");
	// logger.error(" Reading  ... "+NumOfFeatures+"  feature from the data file and "+FeatureStatList.size()+" feature from the feautre file");
	// }
	//
	// features.setAllFeaturesRead(FeatureStatList);
	//
	// logger.info("finihsed reading the feature file..........");
	// }
	// catch (FileNotFoundException e) {
	// UseAllFeatures=true;
	// logger.info(" the default file is not found... ");
	//
	// //e.printStackTrace();
	// }
	// // catch (IOException e) {
	// //
	// // e.printStackTrace();
	// // }
	//
	// }

	public ArrayList<String> getClassLabels() {
		return ClassLabels;
	}

	public ArrayList<Integer> getClassValues() {
		return ClassValues;
	}

	public int getFeatureCount() {
		if (UseAllFeatures)
			return this.NumOfFeatures;
		else
			return this.getSample(0).length;
		// return 0;
	}

	// @Deprecated
	// void SaveToxlsFileValidationSet(String filename) {
	//
	//
	//
	// HSSFWorkbook wb = new HSSFWorkbook();
	// HSSFSheet sheet = wb.createSheet("Data");
	// HSSFRow row = sheet.createRow((short)0);
	//
	// row.createCell((short)0).setCellValue( new HSSFRichTextString (
	// "Features"));
	// ArrayList<FeatureStat> featemp= features.getAllFeaturesRead();
	//
	// for (int i = 0; i < featemp.size(); i++) {
	// row.createCell((short)(i+1)).setCellValue( new HSSFRichTextString
	// (featemp.get(i).getFeatureName()));
	// }
	//
	// row.createCell((short)(featemp.size()+1)).setCellValue( new
	// HSSFRichTextString ("Classes"));
	//
	//
	// for (int i = 0; i < this.NumOfSamples; i++) {
	// row = sheet.createRow((short)i+1);
	// for(int j=0; j< this.NumOfFeatures ;j++){
	//
	// row.createCell((short)j).setCellValue( data[i][j]);
	//
	//
	// }
	// if (UseStringLabels){
	// row.createCell((short)this.NumOfFeatures).setCellValue(new
	// HSSFRichTextString ( targetsString.get(i)));
	// }
	// else {
	// row.createCell((short)this.NumOfFeatures).setCellValue(dataTargets[i]);
	// }
	// }
	//
	// FileOutputStream fileOut;
	// try {
	// fileOut = new FileOutputStream(filename);
	// wb.write(fileOut);
	// fileOut.close();
	// } catch (FileNotFoundException e) {
	// logger.error(e.getMessage(), e);
	// //e.printStackTrace();
	// } catch (IOException e) {
	// logger.error(e.getMessage(), e);
	// //e.printStackTrace();
	// }
	//
	// }
	// @Deprecated
	/*
	 * void SaveToxlsFileTrainSet(String filename) {
	 *
	 *
	 *
	 * HSSFWorkbook wb = new HSSFWorkbook(); HSSFSheet sheet =
	 * wb.createSheet("Data"); HSSFRow row = sheet.createRow((short)0);
	 *
	 * row.createCell((short)0).setCellValue( new HSSFRichTextString (
	 * "Features")); ArrayList<FeatureStat> featemp=
	 * features.getAllFeaturesRead();
	 *
	 * for (int i = 0; i < featemp.size(); i++) {
	 * row.createCell((short)(i+1)).setCellValue( new HSSFRichTextString
	 * (featemp.get(i).getFeatureName())); }
	 *
	 * row.createCell((short)(featemp.size()+1)).setCellValue( new
	 * HSSFRichTextString ("Classes"));
	 *
	 *
	 * for (int i = 0; i < this.NumOfSamples; i++) { row =
	 * sheet.createRow((short)i+1); for(int j=0; j< this.NumOfFeatures ;j++){
	 *
	 * row.createCell((short)j).setCellValue( data[i][j]);
	 *
	 *
	 * } if (UseStringLabels){
	 * row.createCell((short)this.NumOfFeatures).setCellValue(new
	 * HSSFRichTextString ( targetsString.get(i))); } else {
	 * row.createCell((short)this.NumOfFeatures).setCellValue(dataTargets[i]); }
	 * }
	 *
	 * FileOutputStream fileOut; try { fileOut = new FileOutputStream(filename);
	 * wb.write(fileOut); fileOut.close(); } catch (FileNotFoundException e) {
	 * logger.error(e.getMessage(), e); //e.printStackTrace(); } catch
	 * (IOException e) { logger.error(e.getMessage(), e); //e.printStackTrace();
	 * }
	 *
	 * }
	 */
	// @Deprecated
	// void SaveToxlsFile(String filename) {
	//
	//
	// HSSFWorkbook wb = new HSSFWorkbook();
	// HSSFSheet sheet = wb.createSheet("Data");
	// HSSFRow row = sheet.createRow((short)0);
	//
	// // row.createCell((short)0).setCellValue( new HSSFRichTextString (
	// "Features"));
	// ArrayList<FeatureStat> featemp= features.getAllFeaturesRead();
	//
	// for (int i = 0; i < featemp.size(); i++) {
	// row.createCell((short)(i)).setCellValue( new HSSFRichTextString
	// (featemp.get(i).getFeatureName()));
	// }
	//
	// row.createCell((short)(featemp.size())).setCellValue( new
	// HSSFRichTextString ("Classes"));
	//
	//
	// for (int i = 0; i < this.NumOfSamples; i++) {
	// row = sheet.createRow((short)i+1);
	// for(int j=0; j< this.NumOfFeatures ;j++){
	//
	// row.createCell((short)j).setCellValue( data[i][j]);
	//
	//
	// }
	// if (UseStringLabels ){
	// row.createCell((short)this.NumOfFeatures).setCellValue(new
	// HSSFRichTextString ( targetsString.get(i)));
	// }
	// else {
	// row.createCell((short)this.NumOfFeatures).setCellValue(TargetsAdd.get(i));
	// }
	// }
	//
	// FileOutputStream fileOut;
	// try {
	// fileOut = new FileOutputStream(filename);
	// wb.write(fileOut);
	// fileOut.close();
	// } catch (FileNotFoundException e) {
	// logger.error(e.getMessage(), e);
	// //e.printStackTrace();
	// } catch (IOException e) {
	// logger.error(e.getMessage(), e);
	// //e.printStackTrace();
	// }
	//
	//
	// }
	// private void saveSampleOffsetsTrain(String filename) {
	// FileOutputStream file;
	// PrintStream out; // declare a print stream object
	// try {
	// if (Offsets==null)
	// return;
	// // Create a new file output stream
	// file = new FileOutputStream(filename);
	//
	// // Connect print stream to the output stream
	// out = new PrintStream(file);
	// int index=0;
	// // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
	// // double dataw;
	// for (int i = 0; i < this.TrainSamples; i++) {
	//
	// out.print( i);
	// out.print("  ");
	// out.print( index);
	// out.print("  ");
	// index++;
	// out.print( Offsets.get(i));
	// out.print("  ");
	// out.print(dataTargets[i]);
	//
	//
	// out.println("");
	//
	// if (i%1500==0){
	//
	// out.flush();
	// logger.info(" writing samples number "+i);
	// }
	//
	// }
	//
	//
	// }
	// catch (Exception e){
	// logger.error ("Error in writing to file",e);
	// }
	//
	//
	// }
	// private void saveSampleOffsetsValidation(String filename) {
	//
	// FileOutputStream file;
	// PrintStream out; // declare a print stream object
	// try {
	// if (Offsets==null)
	// return;
	// // Create a new file output stream
	// file = new FileOutputStream(filename);
	//
	// // Connect print stream to the output stream
	// out = new PrintStream(file);
	// int index=0;
	// // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
	// // double dataw;
	// for (int i = this.TrainSamples; i < this.NumOfSamples; i++) {
	//
	// out.print( i);
	// out.print("  ");
	// out.print( index);
	// out.print("  ");
	// index++;
	// out.print( Offsets.get(i));
	// out.print("  ");
	// out.print(dataTargets[i]);
	//
	//
	// out.println("");
	//
	// if (i%1500==0){
	//
	// out.flush();
	// logger.trace(" writing samples number "+i);
	// }
	//
	// }
	//
	//
	// }
	// catch (Exception e){
	// logger.error("Error in writing to file",e);
	// }
	//
	// }
	// private void saveSampleOffsets(String filename) {
	// FileOutputStream file;
	// PrintStream out; // declare a print stream object
	// try {
	// if (Offsets==null)
	// return;
	// // Create a new file output stream
	// file = new FileOutputStream(filename);
	//
	// // Connect print stream to the output stream
	// out = new PrintStream(file);
	// int index=0;
	// // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
	// // double dataw;
	// for (int i = 0; i < this.NumOfSamples; i++) {
	//
	// out.print( i);
	// out.print("  ");
	// out.print( index);
	// out.print("  ");
	// index++;
	// out.print( Offsets.get(i));
	// out.print("  ");
	// out.print(dataTargets[i]);
	//
	//
	// out.println("");
	//
	// if (i%1000==0){
	//
	// out.flush();
	// logger.info(" writing offsets of  "+i);
	// }
	//
	// }
	//
	//
	// }
	// catch (Exception e){
	// logger.error ("Error in writing to file",e);
	// }
	//
	//
	// }

	public int getFormat() {
		return format;
	}

	public String getFormatString(int format) {
		if (format == FILE_INPUT_FORMAT_TORCH)
			return "Torch";
		if (format == FILE_INPUT_FORMAT_LIBSVM)
			return "LibSVM";
		if (format == FILE_INPUT_FORMAT_ARFF)
			return "Arff";
		if (format == FILE_INPUT_FORMAT_CSV)
			return "CSV";
		return "";
	}

	public int getNumOfSamples() {
		return NumOfSamples;
	}

	// public void addSample(ArrayList<Feature> feat, int x) {
	// logger.trace("inside sample feature array, and label ");
	// if (dataAdd==null)
	// {
	// dataAdd=new ArrayList<double []>();
	// TargetsAdd=new ArrayList<Integer>();
	// }
	// double [] dat=new double [feat.size()];
	//
	// for (int i = 0; i < feat.size(); i++) {
	//
	// dat[i]=feat.get(i).getValue();
	//
	// }
	//
	// dataAdd.add(dat);
	//
	// TargetsAdd.add(x);
	// NumOfSamples=dataAdd.size();
	//
	//
	// }
	/**
	 * @return the orignalLocations
	 */
	public ArrayList<String> getOrignalLocations() {
		return OrignalLocations;
	}

	public Double[] getSample(int i) {
		Double[] sample = null;

		if (UseAllFeatures) {
			sample = new Double[NumOfFeatures];

			if (data == null) {
				for (int j = 0; j < sample.length; j++) {
					sample[j] = dataAdd.get(i)[j];
				}
			} else {
				for (int j = 0; j < sample.length; j++) {

					sample[j] = data[i][j];
				}
			}

		} else {
			// int [] indeces=features.getSelectedFeaturesIndeces();
			// sample=new Double [indeces.length];
			//
			// for (int j = 0; j < indeces.length; j++) {
			// if (data==null){
			// if (indeces[j]< dataAdd.get(i).length){
			// sample[j]=new Double ( dataAdd.get(i)[indeces[j]]);
			// // if (sample[j]==null)
			// // {
			// // logger.debug(" the indeces  [ "+j+" ] "+indeces[j]);
			// //
			// logger.debug(" data "+data[i].length+"  data "+data[i][indeces[j]]);
			// //
			// // }
			// }
			// }
			// else {
			// if (indeces[j]<data[i].length){
			// sample[j]=new Double (data[i][indeces[j]]);
			// // if (sample[j]==null)
			// // {
			// // logger.debug(" the indeces  [ "+j+" ] "+indeces[j]);
			// //
			// logger.debug(" data "+data[i].length+"  data "+data[i][indeces[j]]);
			// //
			// // }
			// }
			// }
			// }
		}
		return sample;

	}

	public String getStringTarget(int i) {
		if (dataTargets == null)
			return this.TargetsAdd.get(i) + "";
		return dataTargets[i] + "";

	}

	public int getTarget(int i) {
		if (dataTargets == null)
			return this.TargetsAdd.get(i);
		return dataTargets[i];

	}

	public ArrayList<Integer> getValidationsTargets() {
		ArrayList<Integer> tar = new ArrayList<Integer>();

		for (int i = TrainSamples; i < this.NumOfSamples; i++) {
			tar.add(new Integer(dataTargets[i]));
		}
		return tar;
	}

	public void initDataSetFeatures() {

		UseAllFeatures = true;

	}

	public boolean isRandomizeData() {
		return RandomizeData;
	}

	public DataSet loadDataSet(String name) {

		// wirte other data...

		// unserialize the Queue

		try {
			FileInputStream fin = new FileInputStream(name + "_Serial.dat");
			ObjectInputStream ois = new ObjectInputStream(fin);
			DataSet data = (DataSet) ois.readObject();

			data.ReadFromFile(name);

			ois.close();

			return data;

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" No file ", e);

		}

		// logger.info(theQueue=.toString());

		return null;

	}

	void ReadFromARFFFile(String filename) {

		// @attribute 'lxre6' numeric
		// @attribute 'lxrb' numeric
		// @attribute 'class' {0,1,2,3,4,5,6,7,8,9}
		// String
		// line;HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright]
		try {

			logger.info("reading the file................ wait  " + filename);
			File afile = new File(filename);

			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			int linecount = 0;
			// int readcount=0;
			// reading the @attrib....
			boolean dataFound = false;
			String temp;

			ArrayList<String> FeatureStatList = new ArrayList<String>();
			String feat3;
			String tempF;
			int indexF;
			AllFeatureString = "";
			int F = 0;
			while (input.hasNext() & !dataFound) {
				// i did not found
				temp = input.nextLine();
				if (temp.trim().startsWith("@")) {

					if (temp.trim().startsWith("@data")) {
						dataFound = true;
					}
					if (temp.trim().startsWith("@attrib")) {
						// in attrib...
						if (temp.contains("class")) {
							ClassValues = new ArrayList<Integer>();
							ClassLabels = new ArrayList<String>();
							String t;
							int s = temp.indexOf("{");
							int e = temp.indexOf("}");

							t = temp.substring(s + 1, e);
							String[] classesnames = t.split(",");
							Integer cs;
							for (int i = 0; i < classesnames.length; i++) {
								try {
									cs = Integer.parseInt(classesnames[i]);
									ClassValues.add(cs);
									ClassLabels.add(classesnames[i]);
								} catch (NumberFormatException ex) {
									logger.info("EEEEEEEEEEEEEEEEEE --- not number targetssss  s");
									if (_labelToType == null) {
										UseStringLabels = true;
										_labelToType = new HashMap<String, Integer>();
									}

									if (_labelToType
											.containsKey(classesnames[i])) {
										cs = (Integer) _labelToType.get(t);
									} else {

										_labelToType.put(classesnames[i], i);
										cs = new Integer(i);

									}
									ClassValues.add(cs);
									ClassLabels.add(classesnames[i]);
								}

							}

						} else {
							String t;

							int s = temp.indexOf("'");
							int e = temp.lastIndexOf("'");

							// logger.info(" trying to find a ' in the "+s+"  to "
							// +e );
							t = temp.substring(s + 1, e);
							tempF = t.trim();
							indexF = F;
							F++;
							feat3 = tempF;
							// feat3.setFeatureIndex(indexF);
							// feat3.setFeatureName(tempF);
							AllFeatureString += tempF;
							AllFeatureString += ",";

							FeatureStatList.add(feat3);
						}
					}

				}

			}

			featuresNames = FeatureStatList;// .setAllFeaturesRead(FeatureStatList);
			logger.info("number of features got after  reading "
					+ featuresNames.size());
			ArrayList<double[]> dataArray = new ArrayList<double[]>();
			dataAdd = new ArrayList<double[]>();
			ArrayList<Integer> targets = new ArrayList<Integer>();
			TargetsAdd = new ArrayList<Integer>();
			targetsString = new ArrayList<String>();
			// input.useDelimiter(" ");
			// String line1;
			String lin;
			int tar = 0;
			int maxFeatures = 0;
			int ClassCount = 0;
			logger.info("Start Readind  ............................");

			while (input.hasNext()) {

				temp = input.nextLine();

				String[] featLinArr = temp.split(",");
				// first read the first line to get the maximum features and the
				double[] feat = new double[featLinArr.length - 1];
				maxFeatures = feat.length;
				for (int i = 0; i < feat.length; i++) {
					Double f = Double.parseDouble(featLinArr[i]);
					feat[i] = f;

				}// from i to index.
				dataArray.add(feat);
				dataAdd.add(feat);

				Double f = new Double(0);
				// check if a String targets...
				try {
					f = Double.parseDouble(featLinArr[featLinArr.length - 1]);
				} catch (NumberFormatException ex) {
					// this is a string

					String t = featLinArr[featLinArr.length - 1];
					if (_labelToType.containsKey(t)) {
						f = Double.valueOf((Integer) _labelToType.get(t));
					} else {

						_labelToType.put(t, ClassCount);
						f = new Double(ClassCount);
						ClassCount++;

					}
				}

				targetsString.add(featLinArr[featLinArr.length - 1]);
				targets.add(f.intValue());
				TargetsAdd.add(f.intValue());

				linecount++;
				if (linecount % 2000 == 0)
					logger.info(" reading line n " + linecount);

				// this statement reads the line from the file and print it to
				// the console.
				// logger.info(line);
			}
			//
			// after all data i sread...
			data = new double[dataArray.size()][maxFeatures];
			dataTargets = new int[dataArray.size()];
			for (int i = 0; i < dataArray.size(); i++) {
				dataTargets[i] = targets.get(i);

				for (int k = 0; k < maxFeatures; k++) {
					if (k < dataArray.get(i).length) {
						data[i][k] = dataArray.get(i)[k];
					} else {
						data[i][k] = 0.0;
					}
				}
			}

			this.NumOfClasses = ClassValues.size();
			this.NumOfFeatures = maxFeatures;
			this.NumOfSamples = linecount;
			UseAllFeatures = true;
			input.close();

			logger.info("  total number of lines read  =  " + linecount
					+ "  each has " + NumOfFeatures + "  feature");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// @Deprecated
	// public void setFeaturesToUse(int [] indeces){
	// features.createFeatureSelectedList(indeces);
	// }
	// @Deprecated
	// public void setFeaturesToUse(String [] featuresNames){
	//
	// features.createFeatures(featuresNames);
	//
	// }
	// @Deprecated
	// public DataSet GenearteClassDataSetTwoClasses( int c1,int c2 ,String []
	// features ){
	//
	// DataSet tempDataSet= GenearteFeatureDataSet( features);
	//
	// logger.info(" the new tempdata set has features is "+tempDataSet.NumOfFeatures+" number of samples = "+tempDataSet.NumOfSamples);
	// return tempDataSet.GenearteClassDataSetTwoClasses(c1,c2);
	// }
	// @Deprecated
	// public DataSet GenerateTrainDataSet() {
	// DataSet NewDataSet = null;
	// //get the used data
	// if (data==null)
	// return null;
	// else{
	//
	//
	// if (!ValidationCreated)
	// {
	// //this.CreateValidationSet();
	//
	// }
	// try {
	// NewDataSet=(DataSet) this.clone();
	//
	// int newFeatureCount=0;
	// int newSampleCount=0;
	// //set the new data
	//
	//
	//
	// ArrayList<Double[]> NewDataList= new ArrayList<Double[]>();
	// ArrayList<Integer> targets=new ArrayList<Integer>();
	// int tSamples=this.TrainSamples;
	// if (!RandomizeData) {
	// tSamples=trainData.size();
	// }
	//
	//
	// for (int s = 0; s < tSamples; s++) {
	// Double [] tempData= getSample(s);
	//
	// if (!RandomizeData)
	// {
	//
	// tempData= new Double[trainData.get(s).length] ;
	// for (int i = 0; i < tempData.length; i++) {
	// tempData[i]=trainData.get(s)[i];
	// }
	//
	// }
	//
	//
	// NewDataList.add( tempData);
	//
	// Integer tar=getTarget(s);
	// if (!RandomizeData){
	//
	// tar= trainDataTargets.get(s);
	// }
	//
	// targets.add(tar );
	//
	//
	//
	// }
	// int [] indeces=features.getSelectedFeaturesIndeces();
	// //logger.info("number of selected features = "+indeces.length);
	//
	// newSampleCount=NewDataList.size();
	// newFeatureCount=indeces.length;
	// //logger.info(" new number of samples  "+newSampleCount+" new features count is "+newFeatureCount);
	// // sample=new double [indeces.length];
	//
	// double [][] NewData=new double [newSampleCount][indeces.length];
	//
	//
	// int [] NewTarget=new int [targets.size()];
	//
	// for (int i = 0; i < newSampleCount; i++) {
	// NewTarget[i]=targets.get(i);
	// for (int j = 0; j < indeces.length; j++) {
	// // logger.info("i = "+i+" j= "+j);
	// // logger.info( "data= "+ NewDataList.get(i)[j] );
	//
	// NewData[i][j]= NewDataList.get(i)[j];
	// }
	//
	// }//for i number of samples
	// NewDataSet.NumOfClasses=this.NumOfClasses;
	// NewDataSet.NumOfSamples=newSampleCount;
	// NewDataSet.NumOfFeatures=newFeatureCount;
	// NewDataSet.FeatureDataSet=this.FeatureDataSet;
	// NewDataSet.UseAllFeatures=this.UseAllFeatures;
	// NewDataSet.twoClass=this.twoClass;
	// NewDataSet.data=NewData;
	// NewDataSet.dataTargets=NewTarget;
	// //NewDataSet.ClassValues=this.ClassValues;
	//
	//
	// NewDataSet.fixClassArray();
	//
	//
	//
	// // if (this.ValidationCreated){
	// //
	// // NewDataSet.CreateValidationSet();
	// // }
	// //
	//
	// } catch (CloneNotSupportedException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }//else
	//
	// return NewDataSet;
	// }
	//
	// public DataSet GenerateValidationDataSet() {
	// DataSet NewDataSet = null;
	// //get the used data
	// if (data==null)
	// return null;
	// else{
	//
	//
	// if (!ValidationCreated)
	// {
	// this.CreateValidationSet();
	//
	// }
	// try {
	// NewDataSet=(DataSet) this.clone();
	//
	// int newFeatureCount=0;
	// int newSampleCount=0;
	// //set the new data
	//
	//
	//
	// ArrayList<Double[]> NewDataList= new ArrayList<Double[]>();
	// ArrayList<Integer> targets=new ArrayList<Integer>();
	// int tSamples=this.NumOfSamples;
	// int tStart=this.TrainSamples;
	// if (!RandomizeData) {
	// tStart=0;
	// tSamples=Validation.size();
	//
	// }
	//
	// for (int s = tStart; s < tSamples; s++) {
	// Double [] tempData= getSample(s);
	//
	// if (!RandomizeData)
	// {
	//
	// tempData= new Double[Validation.get(s).length] ;
	// for (int i = 0; i < tempData.length; i++) {
	// tempData[i]=Validation.get(s)[i];
	// }
	//
	// }
	//
	//
	// NewDataList.add( tempData);
	//
	// Integer tar=getTarget(s);
	// if (!RandomizeData){
	//
	// tar= validationTargets.get(s);
	// }
	//
	// targets.add(tar );
	//
	//
	//
	// }
	// int [] indeces=features.getSelectedFeaturesIndeces();
	// //logger.info("number of selected features = "+indeces.length);
	//
	// newSampleCount=NewDataList.size();
	// newFeatureCount=indeces.length;
	// //logger.info(" new number of samples  "+newSampleCount+" new features count is "+newFeatureCount);
	// // sample=new double [indeces.length];
	//
	// double [][] NewData=new double [newSampleCount][indeces.length];
	//
	//
	// int [] NewTarget=new int [targets.size()];
	//
	// for (int i = 0; i < newSampleCount; i++) {
	// NewTarget[i]=targets.get(i);
	// for (int j = 0; j < indeces.length; j++) {
	// // logger.info("i = "+i+" j= "+j);
	// // logger.info( "data= "+ NewDataList.get(i)[j] );
	//
	// NewData[i][j]= NewDataList.get(i)[j];
	// }
	//
	// }//for i number of samples
	// NewDataSet.NumOfClasses=this.NumOfClasses;
	// NewDataSet.NumOfSamples=newSampleCount;
	// NewDataSet.NumOfFeatures=newFeatureCount;
	// NewDataSet.FeatureDataSet=this.FeatureDataSet;
	// NewDataSet.UseAllFeatures=this.UseAllFeatures;
	// NewDataSet.twoClass=this.twoClass;
	// NewDataSet.data=NewData;
	// NewDataSet.dataTargets=NewTarget;
	// //NewDataSet.ClassValues=this.ClassValues;
	// NewDataSet.fixClassArray();
	//
	// // if (this.ValidationCreated){
	// //
	// // NewDataSet.CreateValidationSet();
	// // }
	// //
	//
	// } catch (CloneNotSupportedException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }//else
	//
	// return NewDataSet;
	// }
	// public DataSet GenerateDataSetFromSettings(ClassifierData data2) {
	//
	//
	// DataSet temp=GenearteFeatureDataSet(data2.FeatureNames );
	// return temp.GenerateSplitClassesDataSet(data2.digit,data2.digitC2);
	//
	// }
	// @Deprecated
	// public DataSet GenerateSplitClassesDataSet(ArrayList<Integer> c1,
	// ArrayList<Integer> c2){
	//
	// DataSet NewDataSet = null;
	//
	// if (data==null)
	// ConvertToArrays();
	//
	// if (data==null){
	// return null;
	// }
	//
	// else{
	//
	// logger.info("  I am changing the data set into binarry data set with 0 mand 1");
	//
	// try {
	// NewDataSet=(DataSet) this.clone();
	//
	// int newFeatureCount=0;
	// int newSampleCount=0;
	// //set the new data
	//
	//
	//
	// ArrayList<Double[]> NewDataList= new ArrayList<Double[]>();
	// ArrayList<Integer> ntargets=new ArrayList<Integer>();
	// for (int s = 0; s < NumOfSamples; s++) {
	// if (lib.isInArray(dataTargets[s],c1)){
	// // if (s%100==0)
	// // logger.info(c1+"  changing  "+"  sample  ("+s
	// +") "+dataTargets[s]+"   int  "+1);
	// NewDataList.add(getSample(s));
	// ntargets.add(new Integer(1));
	//
	// }
	// else if (lib.isInArray(dataTargets[s],c2)){
	// //if (s%2000==0)
	// //logger.info(c2+"  changing  "+"  sample  ("+s
	// +") "+dataTargets[s]+"   int  "+11);
	//
	// // double[] tempData;//=new Double[indeces.length];
	// //
	// // tempData=;
	// //
	// // Double[] tempD=new Double[tempData.length];
	// //
	// // for (int i = 0; i < tempD.length; i++) {
	// // tempD[i]=new Double(tempData[i]);
	// // }
	// //
	// NewDataList.add(getSample(s));
	//
	// ntargets.add(new Integer(0));
	//
	// }
	//
	//
	// }
	// int [] indeces=features.getSelectedFeaturesIndeces();
	// //logger.info("number of selected features = "+indeces.length);
	//
	// newSampleCount=NewDataList.size();
	// newFeatureCount=indeces.length;
	// //logger.info(" new number of samples  "+newSampleCount+" new features count is "+newFeatureCount);
	// // sample=new double [indeces.length];
	//
	// double [][] NewData=new double [newSampleCount][indeces.length];
	//
	//
	// int [] NewTarget=new int [ntargets.size()];
	//
	// for (int i = 0; i < newSampleCount; i++) {
	// NewTarget[i]=ntargets.get(i);
	// for (int j = 0; j < indeces.length; j++) {
	// // logger.info("i = "+i+" j= "+j);
	// // logger.info( "data= "+ NewDataList.get(i)[j] );
	//
	// NewData[i][j]= NewDataList.get(i)[j];
	// }
	//
	// }//for i number of samples
	// NewDataSet.NumOfClasses=2;
	// NewDataSet.NumOfSamples=newSampleCount;
	// NewDataSet.NumOfFeatures=newFeatureCount;
	// NewDataSet.FeatureDataSet=true;
	// NewDataSet.UseAllFeatures=true;
	// NewDataSet.twoClass=true;
	// NewDataSet.data=NewData;
	// NewDataSet.dataTargets=NewTarget;
	// NewDataSet.ConvertToList();
	// NewDataSet.FixArrayAndClassLabel();
	// if (this.ValidationCreated){
	//
	// NewDataSet.CreateValidationSet();
	// }
	//
	//
	// logger.info(" Finished all change and new return  to the data set. ");
	// } catch (CloneNotSupportedException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }//else
	//
	// return NewDataSet;
	//
	//
	// }
	// @Deprecated
	// public DataSet GenearteClassDataSetTwoClasses(int c1,int c2){
	// DataSet NewDataSet = null;
	// //get the used data
	// if (data==null)
	// return null;
	// else{
	//
	//
	//
	// try {
	// NewDataSet=(DataSet) this.clone();
	//
	// int newFeatureCount=0;
	// int newSampleCount=0;
	// //set the new data
	//
	//
	//
	// ArrayList<Double[]> NewDataList= new ArrayList<Double[]>();
	// ArrayList<Integer> targets=new ArrayList<Integer>();
	// for (int s = 0; s < NumOfSamples; s++) {
	// if (dataTargets[s]==c1||dataTargets[s]==c2){
	//
	// // double[] tempData;//=new Double[indeces.length];
	// //
	// // tempData=;
	// //
	// // Double[] tempD=new Double[tempData.length];
	// //
	// // for (int i = 0; i < tempD.length; i++) {
	// // tempD[i]=new Double(tempData[i]);
	// // }
	// //
	// NewDataList.add(getSample(s));
	//
	// targets.add(new Integer(getTarget(s) ));
	//
	// }
	//
	//
	// }
	// int [] indeces=features.getSelectedFeaturesIndeces();
	// //logger.info("number of selected features = "+indeces.length);
	//
	// newSampleCount=NewDataList.size();
	// newFeatureCount=indeces.length;
	// //logger.info(" new number of samples  "+newSampleCount+" new features count is "+newFeatureCount);
	// // sample=new double [indeces.length];
	//
	// double [][] NewData=new double [newSampleCount][indeces.length];
	//
	//
	// int [] NewTarget=new int [targets.size()];
	//
	// for (int i = 0; i < newSampleCount; i++) {
	// NewTarget[i]=targets.get(i);
	// for (int j = 0; j < indeces.length; j++) {
	// // logger.info("i = "+i+" j= "+j);
	// // logger.info( "data= "+ NewDataList.get(i)[j] );
	//
	// NewData[i][j]= NewDataList.get(i)[j];
	// }
	//
	// }//for i number of samples
	// NewDataSet.NumOfClasses=2;
	// NewDataSet.NumOfSamples=newSampleCount;
	// NewDataSet.NumOfFeatures=newFeatureCount;
	// NewDataSet.FeatureDataSet=true;
	// NewDataSet.UseAllFeatures=false;
	// NewDataSet.twoClass=true;
	// NewDataSet.data=NewData;
	// NewDataSet.dataTargets=NewTarget;
	// if (this.ValidationCreated){
	//
	// NewDataSet.CreateValidationSet();
	// }
	//
	//
	// } catch (CloneNotSupportedException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }//else
	//
	// return NewDataSet;
	// }
	// // public DataSet GenearteFeatureDataSet(ArrayList<String> featureNames)
	// {
	// // String featna
	// // return GenearteFeatureDataSet(featnames);
	// // }
	// @Deprecated
	// public DataSet GenearteFeatureDataSet(String[] featStrings)
	// {
	// logger.info("inside the feature strings...........");
	// setFeaturesToUse (featStrings);
	//
	// ArrayList<FeatureStat> testing = features.getSelectedFeatures();
	//
	// for (int i = 0; i < testing.size(); i++) {
	// logger.info(" The selected  feature "+testing.get(i).getFeatureName()+"  index "+testing.get(i).FeatureIndex);
	// }
	// logger.info( "  number of  features is   "+testing.size());
	//
	// return GenearteFeatureDataSet();
	//
	//
	// }
	// @Deprecated
	// public DataSet GenearteFeatureDataSet(){
	// DataSet NewDataSet = null;
	// if (data==null)
	// {
	// ConvertToArrays();
	// }
	//
	// //get the used data
	// if (data==null)
	// {
	// return null;
	// }
	//
	//
	// else{
	//
	//
	//
	// try {
	//
	// NewDataSet=(DataSet) this.clone();
	//
	//
	// //set the new data
	//
	// int [] indeces=features.getSelectedFeaturesIndeces();
	//
	// logger.info("  the number of selected features in creating an new DS"+indeces.length);
	// // sample=new double [indeces.length];
	// double [][] NewData=new double [this.NumOfSamples][indeces.length];
	// int [] NewTarget=new int [this.NumOfSamples];
	//
	// for (int i = 0; i < NewData.length; i++) {
	// NewTarget[i]=dataTargets[i];
	// for (int j = 0; j < NewData[i].length; j++) {
	// if (indeces[j]<data[i].length){
	// NewData[i][j]=data[i][indeces[j]];
	// }//check on indeces.
	// }//for number of features
	// }//for i number of samples
	//
	//
	// NewDataSet.NumOfFeatures= indeces.length;
	// NewDataSet.NumOfSamples=this.NumOfSamples;
	// NewDataSet.FeatureDataSet=true;
	//
	// ////set all the features set
	//
	// FeatureSet feat=new FeatureSet();
	//
	// ArrayList<FeatureStat> old =this.features.getSelectedFeatures();
	//
	// ArrayList<FeatureStat> temp = new ArrayList<FeatureStat>() ;
	//
	// FeatureStat te=new FeatureStat();
	//
	// for (int i = 0; i < old.size(); i++) {
	//
	//
	// logger.trace(" the feature "+old.get(i).getFeatureName()+"  index "+old.get(i).FeatureIndex+"  now will have the following index "+i);
	// te=new FeatureStat();
	// te.setFeatureName(old.get(i).getFeatureName());
	// te.setFeatureIndex(i);
	// temp.add(te);
	//
	//
	// }
	//
	// feat.setAllFeaturesRead(temp);
	//
	// NewDataSet.features=feat;
	// NewDataSet.UseAllFeatures=true;
	//
	// NewDataSet.data=NewData.clone();
	//
	// NewDataSet.dataTargets=NewTarget.clone();
	// NewDataSet.ConvertToList();
	//
	//
	// if (this.ValidationCreated){
	//
	// NewDataSet.CreateValidationSet();
	// }
	//
	//
	// } catch (CloneNotSupportedException e) {
	//
	// e.printStackTrace();
	// }
	//
	// logger.info("  NewDataSet.NumOfFeatures  "+ NewDataSet.NumOfFeatures );
	//
	// }//else
	//
	// return NewDataSet;
	// }
	public void ReadFromFile(String filename) {
		featuresNames = null;

		if (format == FILE_INPUT_FORMAT_TORCH) {
			ReadFromTorchFile(filename);

			logger.info(" Finished reading the file ..." + filename);

		}

		if (format == FILE_INPUT_FORMAT_LIBSVM) {
			ReadFromLibSVMFile(filename);
			logger.info(" Finished reading the file ..." + filename);

		}

		if (format == FILE_INPUT_FORMAT_ARFF) {
			if (!filename.contains(".arff")) {
				filename += ".arff";

			}

			ReadFromARFFFile(filename);
		}
		// logger.info(" reading the feature file ");
		// initAllFeatures(filename);
		fixClassArray();

	}

	void ReadFromLibSVMFile(String filename) {
		// String line;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename);
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			int linecount = 0;
			// int readcount=0;
			// String[] datastrings;
			// int j=0;
			//
			// int noSamples=0;
			// int noFeat=0;

			ArrayList<ArrayList<Double>> dataArray = new ArrayList<ArrayList<Double>>();
			ArrayList<Integer> targets = new ArrayList<Integer>();
			// input.useDelimiter(" ");
			// String line1;
			String lin;
			int tar = 0;
			int maxFeatures = 0;
			int correctFeatcount = 0;
			while (input.hasNext()) {

				tar = input.nextInt();
				correctFeatcount = 0;
				targets.add(new Integer(tar)); // adding targes now add the
												// features
				lin = input.nextLine();
				// logger.info("the input line is "+lin);
				String[] featLinArr = lin.split(" ");
				// first read the first line to get the maximum features and the
				ArrayList<Double> feat = new ArrayList<Double>();

				for (int i = 0; i < featLinArr.length; i++) {

					if (featLinArr[i].trim().equals("")) {
						continue;
					}

					String[] tm = featLinArr[i].trim().split(":");
					if (tm.length == 2) {

						int index = Integer.parseInt(tm[0]);
						double f = Double.parseDouble(tm[1]);
						// logger.info("  the index is "+index+"  correct count is "+correctFeatcount);
						if (index >= correctFeatcount) {
							// feat.set(index,f);
							for (int k = correctFeatcount; k <= index; k++) {
								if (correctFeatcount == index)
									feat.add(f);
								else
									feat.add(0.0);
							}// from i to index.
							correctFeatcount = index;
							// feat.set(index,f);
						}// /if index larget then i from 14 to 16 fill features
							// with 0
						correctFeatcount++;
					}

					// System.out.print("  "+data[j][i]);
				}// /for the features array
				if (correctFeatcount > maxFeatures) {
					maxFeatures = correctFeatcount;
				}

				dataArray.add(feat);

				linecount++;
				if (linecount % 3000 == 0) {
					logger.info(" reading line n " + linecount);
					// logger.info(" the features of this sample count is "+feat.size());
				}

				// this statement reads the line from the file and print it to
				// the console.
				// logger.info(line);
			}

			// after all data i sread...
			data = new double[dataArray.size()][maxFeatures];
			dataTargets = new int[dataArray.size()];
			for (int i = 0; i < dataArray.size(); i++) {
				dataTargets[i] = targets.get(i);

				for (int k = 0; k < maxFeatures; k++) {
					if (k < dataArray.get(i).size()) {
						data[i][k] = dataArray.get(i).get(k);
					} else {
						data[i][k] = 0.0;
					}
				}

				this.NumOfFeatures = maxFeatures;
				this.NumOfSamples = data.length;
			}

			// dispose all the resources after using them.
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// catch (IOException e) {

		// e.printStackTrace();
		// }

	}

	void ReadFromTorchFile(String filename) {
		// String line;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename);
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));

			int linecount = 0;
			int readcount = 0;
			// String[] datastrings;
			int j = 0;
			// input.useDelimiter(" ");

			while (input.hasNext()) {

				// datastrings = line.split(" ");

				if (readcount == 0) {
					this.NumOfSamples = input.nextInt();
					readcount++;
					NumOfFeatures = input.nextInt() - 1;
					readcount++;
					data = new double[NumOfSamples][NumOfFeatures];
					dataTargets = new int[NumOfSamples];
					logger.info(" the first time read = " + NumOfSamples
							+ "  with " + NumOfFeatures + " features ");
					j = 0;

				} else {
					// logger.info();
					// System.out.print(" sample "+j);
					for (int i = 0; i < NumOfFeatures && input.hasNext(); i++) {

						data[j][i] = input.nextDouble();
						readcount++;
						// System.out.print("  "+data[j][i]);
					}

					if (input.hasNext()) {
						dataTargets[j] = input.nextInt();
						readcount++;
						// System.out.print("  target is =  "+ dataTargets[j]);
					}

					j++;

				}

				if (j > NumOfSamples) {
					break;
				}
				linecount++;

				if (linecount % 3000 == 0)
					logger.info(" reading line n " + linecount);

				// this statement reads the line from the file and print it to
				// the console.
				// logger.info(line);
			}

			// dispose all the resources after using them.
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// catch (IOException e) {
		//
		// e.printStackTrace();
		// }

	}

	void SaveToARFFFile(String filename) {
		/*******
		 *
		 *
		 * @RELATION DigitsClassifier
		 * @attribute 'Class' numeric
		 * @attribute '1' numeric
		 * @attribute '2' numeric
		 * @attribute '3' numeric
		 * @attribute '4' numeric
		 * @attribute '5' numeric
		 * @attribute '6' numeric
		 * @attribute '7' numeric
		 * @attribute '8' numeric
		 * @attribute '9' numeric
		 * @attribute '10' numeric
		 * @data 1 , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6 1 , 80 , 0 , 1
		 *       , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667 1 , 129 , 0 , 1 , 1 , 0 , 6 ,
		 *       0 , 0 , 7.33333 , 14.666
		 *
		 * ****/
		logger.debug("  In the same file add ng th arff extentions.... ");
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);
			out.println(" @RELATION  DigitsClassifier ");
			out.println("");

			ArrayList<String> featemp = featuresNames;

			for (int i = 0; i < featemp.size(); i++) {

				out.println(" @attribute   '" + featemp.get(i) + "'  numeric  ");

			}

			String Classes = "";
			Classes += " {";
			for (int i = 0; i < ClassValues.size(); i++) {

				Classes += ClassValues.get(i);
				if ((i + 1) < ClassValues.size()) {
					Classes += ",";

				} else {
					Classes += "}";
				}

			}
			if (Classes.equals("")) {

				Classes = " numeric ";
			}

			out.println(" @attribute 'class'  " + Classes);

			out.println("");
			out.println("@data");
			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			double dataw;
			logger.info("Start writing ............................"
					+ this.NumOfSamples + "  samples");
			for (int i = 0; i < this.NumOfSamples; i++) {

				// out.print("  ");
				for (int j = 0; j < this.NumOfFeatures; j++) {

					if (Double.isNaN(data[i][j])) {
						logger.error("NANA ins SAMPLE " + i + "  FEATURE  " + j);
						dataw = 0.0;
					} else
						dataw = data[i][j];

					out.print(dataw);

					// }

					out.print(",");
					out.print("  ");
				}

				out.print(dataTargets[i]);
				out.println("");

				if (i % 2000 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}
			out.close();
			logger.info("  total number of lines read  =  " + this.NumOfSamples
					+ "  each has " + NumOfFeatures + "  feature");
		} catch (Exception e) {
			System.err.println("Error in writing to file");
			logger.error(e.getMessage(), e.getCause());
		}

	}

	void SaveToARFFFileTrain(String filename) {
		/*******
		 *
		 *
		 * @RELATION DigitsClassifier
		 * @attribute 'Class' numeric
		 * @attribute '1' numeric
		 * @attribute '2' numeric
		 * @attribute '3' numeric
		 * @attribute '4' numeric
		 * @attribute '5' numeric
		 * @attribute '6' numeric
		 * @attribute '7' numeric
		 * @attribute '8' numeric
		 * @attribute '9' numeric
		 * @attribute '10' numeric
		 * @data 1 , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6 1 , 80 , 0 , 1
		 *       , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667 1 , 129 , 0 , 1 , 1 , 0 , 6 ,
		 *       0 , 0 , 7.33333 , 14.666
		 *
		 * ****/

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename + ".arff");

			// Connect print stream to the output stream
			out = new PrintStream(file);
			out.println(" @RELATION  DigitsClassifier ");
			out.println("");
			// out.println(" @attribute 'Class' numeric  ");
			ArrayList<String> featemp = featuresNames;

			for (int i = 0; i < featemp.size(); i++) {

				out.println(" @attribute   '" + featemp.get(i) + "'  numeric  ");

			}
			String Classes = "";
			Classes += " {";
			for (int i = 0; i < ClassValues.size(); i++) {

				Classes += ClassValues.get(i);
				if ((i + 1) < ClassValues.size()) {
					Classes += ",";

				} else {
					Classes += "}";
				}

			}
			if (Classes.equals("")) {

				Classes = " numeric ";
			}

			out.println(" @attribute 'class'  " + Classes);
			out.println("");
			out.println("@data");

			double dataw;

			// now add condition
			//
			int tSamples = this.TrainSamples;
			if (!RandomizeData) {
				tSamples = trainData.size();
			}

			for (int i = 0; i < tSamples; i++) {

				for (int j = 0; j < this.NumOfFeatures; j++) {

					double tempData = data[i][j];
					if (!RandomizeData) {
						tempData = trainData.get(i)[j];
					}

					if (Double.isNaN(tempData)) {
						dataw = 0.0;
					} else
						dataw = tempData;
					out.print(dataw);

					// }

					out.print(",");
					out.print("  ");
				}

				int tempTargest = dataTargets[i];
				if (!RandomizeData) {
					tempTargest = trainDataTargets.get(i);
				}
				out.print(tempTargest);
				out.println("");

				if (i % 1500 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}

	}

	void SaveToARFFFileValidation(String filename) {
		/*******
		 *
		 *
		 * @RELATION DigitsClassifier
		 * @attribute 'Class' numeric
		 * @attribute '1' numeric
		 * @attribute '2' numeric
		 * @attribute '3' numeric
		 * @attribute '4' numeric
		 * @attribute '5' numeric
		 * @attribute '6' numeric
		 * @attribute '7' numeric
		 * @attribute '8' numeric
		 * @attribute '9' numeric
		 * @attribute '10' numeric
		 * @data 1 , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6 1 , 80 , 0 , 1
		 *       , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667 1 , 129 , 0 , 1 , 1 , 0 , 6 ,
		 *       0 , 0 , 7.33333 , 14.666
		 *
		 * ****/

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename + ".arff");

			// Connect print stream to the output stream
			out = new PrintStream(file);
			out.println(" @RELATION  DigitsClassifier ");
			out.println("");

			// out.println(" @attribute 'Class' numeric  ");
			ArrayList<String> featemp = featuresNames;

			for (int i = 0; i < featemp.size(); i++) {

				out.println(" @attribute   '" + featemp.get(i) + "'  numeric  ");

			}
			String Classes = "";
			Classes += " {";
			for (int i = 0; i < ClassValues.size(); i++) {

				Classes += ClassValues.get(i);
				if ((i + 1) < ClassValues.size()) {
					Classes += ",";

				} else {
					Classes += "}";
				}

			}
			if (Classes.equals("")) {

				Classes = " numeric ";
			}

			out.println(" @attribute 'class'  " + Classes);
			out.println("");
			out.println("@data");
			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			double dataw;

			int tSamples = this.NumOfSamples;
			int tStart = this.TrainSamples;
			if (!RandomizeData) {
				tStart = 0;
				tSamples = Validation.size();

			}

			for (int i = tStart; i < tSamples; i++) {

				// out.print("  ");
				for (int j = 0; j < this.NumOfFeatures; j++) {

					double tempData = data[i][j];
					if (!RandomizeData) {
						tempData = Validation.get(i)[j];
					}
					if (Double.isNaN(tempData)) {
						dataw = 0.0;
					} else
						dataw = tempData;
					out.print(dataw);

					// }

					out.print(",");
					out.print("  ");
				}

				int tempTargest = dataTargets[i];
				if (!RandomizeData) {
					tempTargest = validationTargets.get(i);
				}
				out.print(tempTargest);
				out.println("");

				if (i % 1500 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}

	}

	public void SaveToFile(String filename) {
		logger.info(" Saving file " + filename);

		if (ClassValuesGenerated == false) {
			fixClassArray();

		}
		if (format == FILE_INPUT_FORMAT_EXCELL) {
			if (this.ValidationCreated) {

				String trainFile = filename.replace(".xls", "_train.xls");
				// SaveToxlsFileTrainSet(trainFile );

				String ValidationFile = filename.replace(".xls",
						"_validation.xls");
				// SaveToxlsFileValidationSet(ValidationFile);

			} else {
				// this.SaveToxlsFile(filename);
			}
		}
		if (format == FILE_INPUT_FORMAT_TORCH) {
			if (this.ValidationCreated) {

				String trainFile = filename.replace(".txt", "_train.txt");
				SaveToTorchFileTrainSet(trainFile);

				String ValidationFile = filename.replace(".txt",
						"_validation.txt");
				SaveToTorchFileValidationSet(ValidationFile);

			} else {
				this.SaveToTorchFile(filename);
			}
		} else if (format == FILE_INPUT_FORMAT_LIBSVM) {

			if (this.ValidationCreated) {

				String trainFile = filename.replace(".txt", "_train.txt");
				SaveToLibSVMFileTrain(trainFile);

				String ValidationFile = filename.replace(".txt",
						"_validation.txt");
				SaveToLibSVMFileValidation(ValidationFile);

			} else {
				this.SaveToLibSVMFile(filename);

			}

		} else if (format == FILE_INPUT_FORMAT_ARFF) {
			if (this.ValidationCreated) {

				String trainFile = filename;
				String ValidationFile = filename;
				if (filename.contains(".txt")) {
					trainFile = filename.replace(".txt", "_train.txt");

					ValidationFile = filename
							.replace(".txt", "_validation.txt");
				} else if (filename.contains(FILE_INPUT_EXTENTION_ARFF)) {

					trainFile = filename.replace(".arff", "_train.arff");

					ValidationFile = filename.replace(".arff",
							"_validation.arff");
				} else {
					trainFile = filename + "_train.arff";
					ValidationFile = filename + "_validation.arff";
				}

				SaveToARFFFileTrain(trainFile);
				SaveToARFFFileValidation(ValidationFile);

			} else {
				if (!filename.contains(".arff")) {
					filename += ".arff";

				}

				SaveToARFFFile(filename);
			}
		}
		// //////////////////////////////////Now the offsets
		if (SaveSampleOffset) {
			// if (this.ValidationCreated){
			//
			// String trainFile=filename.replace(".txt", "_train_offset.txt");
			//
			//
			//
			// saveSampleOffsetsTrain(trainFile);
			//
			// String ValidationFile=filename.replace(".txt",
			// "_validation_offset.txt");
			//
			//
			// saveSampleOffsetsValidation(ValidationFile);
			//
			// }
			// else {
			// String nFile=filename.replace(".txt", "_offset.txt");
			// saveSampleOffsets(nFile);
			// }
		}

	}

	public void SaveToFileAll(String name) {
		SaveToFile(name);

		// write object....

		try {
			FileOutputStream fout = new FileOutputStream(name + "_Serial.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);

			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void SaveToLibSVMFile(String filename) {
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			// double dataw;
			for (int i = 0; i < this.NumOfSamples; i++) {
				out.print(dataTargets[i]);
				out.print("  ");
				for (int j = 0; j < this.NumOfFeatures; j++) {
					if (data[i][j] != 0) {
						if (!Double.isNaN(data[i][j])) {

							out.print(j + 1);
							out.print(":");
							out.print(data[i][j]);
							out.print("  ");
						}
					}
				}
				out.println("");

				if (i % 1000 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			logger.error("Error in writing to file");
		}

	}

	void SaveToLibSVMFileTrain(String filename) {
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			// double dataw;
			for (int i = 0; i < this.TrainSamples; i++) {
				out.print(dataTargets[i]);
				out.print("  ");
				for (int j = 0; j < this.NumOfFeatures; j++) {
					if (data[i][j] != 0) {
						if (!Double.isNaN(data[i][j])) {

							out.print(j);
							out.print(":");
							out.print(data[i][j]);
							out.print("  ");
						}
					}
				}
				out.println("");

				if (i % 1000 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			logger.error("Error in writing to file", e);
		}

	}

	void SaveToLibSVMFileValidation(String filename) {
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			// double dataw;
			for (int i = this.TrainSamples; i < this.NumOfSamples; i++) {
				out.print(dataTargets[i]);
				out.print("  ");
				for (int j = 0; j < this.NumOfFeatures; j++) {
					if (data[i][j] != 0) {
						if (!Double.isNaN(data[i][j])) {

							out.print(j);
							out.print(":");
							out.print(data[i][j]);
							out.print("  ");
						}
					}
				}

				out.println("");

				if (i % 1000 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			logger.error("Error in writing to file", e);
		}

	}

	void SaveToTorchFile(String filename) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			out.println(this.NumOfSamples + " " + (this.NumOfFeatures + 1));

			for (int i = 0; i < this.NumOfSamples; i++) {

				for (int j = 0; j < this.NumOfFeatures; j++) {
					out.print(data[i][j]);
					out.print("  ");
				}

				out.println(dataTargets[i]);

				if (i % 1500 == 0) {

					out.flush();
					logger.info(" writing samples n " + i);
				}

			}

		} catch (Exception e) {
			logger.error("Error in writing to file");
		}

	}

	void SaveToTorchFileTrainSet(String filename) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			out.println(this.TrainSamples + " " + (this.NumOfFeatures + 1));

			for (int i = 0; i < this.TrainSamples; i++) {

				for (int j = 0; j < this.NumOfFeatures; j++) {
					out.print(data[i][j]);
					out.print("  ");
				}

				out.println(dataTargets[i]);

				if (i % 1300 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}

	}

	void SaveToTorchFileValidationSet(String filename) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);

			out.println((this.NumOfSamples - this.TrainSamples) + " "
					+ (this.NumOfFeatures + 1));

			for (int i = this.TrainSamples; i < this.NumOfSamples; i++) {

				for (int j = 0; j < this.NumOfFeatures; j++) {
					out.print(data[i][j]);
					out.print("  ");
				}

				out.println(dataTargets[i]);

				if (i % 1300 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}

		} catch (Exception e) {
			logger.error("Error in writing to file");
		}

	}

	public void setAllFeatureString(String allFeatureString) {
		AllFeatureString = allFeatureString;
	}

	public void setClassValues(ArrayList<Integer> classValues) {
		ClassValues = classValues;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	/**
	 * @param orignalLocations
	 *            the orignalLocations to set
	 */
	public void setOrignalLocations(ArrayList<String> orignalLocations) {
		OrignalLocations = orignalLocations;
	}

	// public DataSet CreateOVADataset(int i) {
	// this.UseAllFeatures=true;
	// DataSet temp=GenearteFeatureDataSet();
	// temp.SetOVATargets(i);
	// return temp;
	// }
	public void SetOVATargets(int i) {

		for (int j = 0; j < dataTargets.length; j++) {
			if (dataTargets[j] == i) {

				dataTargets[j] = 1;
			} else {
				dataTargets[j] = -1;
			}
		}

	}

	public void setRandomizeData(boolean randomizeData) {
		RandomizeData = randomizeData;
	}

	// public FeatureSet getFeatures() {
	// return features;
	// }
	public void ShuffleDataSet() {

		if (data == null) {

			ConvertToArrays();

		}
		double[][] RandomData = new double[data.length][NumOfFeatures];
		int[] RandomTarget = new int[data.length];
		// ArrayList<Integer>
		ArrayList<Integer> indeces = new ArrayList<Integer>(data.length);
		indeces.ensureCapacity(data.length);
		// logger.info("suffling   "+data.length+" data ... ");
		// logger.info( " their is  "+indeces.size());
		for (int i = 0; i < data.length; i++) {
			indeces.add(new Integer(i));

		}
		Random r1Random = new Random(RandomSEED);
		int randomi;// =(int) Math.floor(Math.random()*indeces.size());
		int index;
		int j = 0;
		for (int i = 0; indeces.size() > 0; i++) {
			// logger.info("suffling index  i "+i+" using the");
			// if (j>NumOfSamples)
			// break;

			randomi = (int) Math.floor(r1Random.nextDouble() * indeces.size());
			// now move files from he input to the ouptut array .
			index = indeces.get(randomi);
			// logger.info("suffling index  i "+i+" using the "+"  with the new index "+index);
			// RandomSet.add( files.get(index));
			// for (int k = 0; k < data[index].length; k++) {
			// RandomData[j][k]=data[index][k];
			// }
			RandomData[j] = data[index].clone();
			RandomTarget[j] = dataTargets[index];
			j++;

			indeces.remove(randomi);

		}
		logger.info("shuffled  " + j + "  samples........");
		// if(indeces.size()>0)
		// {
		// for (int i = 0; i <indeces.size(); i++) {
		// index=indeces.get(i);
		// RandomData[i]=data[index];
		// RandomTarget[i]=dataTargets[index];
		// //RandomSet.add( files.get(index));
		// }
		// }
		// if (logger.isTraceEnabled()){
		//
		// for (int i = 0; i < 100 && i<RandomSet.size(); i++) {
		// logger.trace(" -- "+RandomSet.get(i));
		// }
		shuffled = true;
		// RandomData[j]=data[index];
		// for ( j = 0; j<NumOfSamples; j++) {
		//
		// data[j]=RandomData[j];
		// dataTargets[j]=RandomTarget[j];
		// }
		data = RandomData.clone();
		dataTargets = RandomTarget.clone();
		// RandomTarget[j]=dataTargets[index];

	}

}
