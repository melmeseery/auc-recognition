/**
 * 
 */
package applications.subMuliClassifiers;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import applications.subMuliClassifiers.RunMultiClassFeaturesTest.RegionFeaturePair;
import classifiers.MultiFeature.ClassifierData;
import classifiers.MultiFeature.ImageRecognizier;
import classifiers.MultiFeature.MultiClassFeatureRecognizier;
import classifiers.results.ClassifierResult;

import data.dataset.DataBaseConnector;
import data.image.FeaturedRegion;
import data.image.RegionCreater;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;
import util.lib;

/**
 * @author TOSHIBA
 * 
 */
public class RunMultiClassFeatureTestingCode extends Observable implements
		RunnableTask {
	private static transient final Logger logger = Logger
			.getLogger(RunMultiClassFeatureTestingCode.class);
	private static final String OutDir = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\stats\\";
	private static final int WSBFeatures = 3;
	int BasicFeature = 1;
	int OtherFeatures = 2;
	int NoFeature = 0;
	boolean SIMILARFEATURE=false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		RunMultiClassFeatureTestingCode test = new RunMultiClassFeatureTestingCode();
		DataBaseConnector.OS = DataBaseConnector.OS_WINDOWS;
		Thread th = new Thread(test);
		th.run();

	}

	@Override
	public void addObserver(TaskController taskController) {

	}

	@Override
	public TaskSettings getSettings() {

		return null;
	}

	@Override
	public void setSettings(TaskSettings task) {

	}

	@Override
	public void run() {

		RunTaskDetails();
	}

	public void RunTaskDetails() {

		MultiClassFeatureRecognizier classifier = new MultiClassFeatureRecognizier();

		FeaturedRegion.setFeaturesToCompute(null);

		// ArrayList<ArrayList <String>> featArrs=null;
		// ArrayList<RegionCreater> regions=null;//new
		// ArrayList<RegionCreater>();

		// IR.setMainOptions(ImageRecognizier.TRAIN_VALIDATE,
		// ImageRecognizier.CLASSIFIER_LIBSVM );

		String str = "";
		String newline = System.getProperty("line.separator");
		for (int i = 0; i < 10; i++) {
			for (int k = i + 1; k < 10; k++) {
				ArrayList<Integer> digit = new ArrayList<Integer>();
				digit.add(new Integer(i));
				digit.add(new Integer(k));

				logger.info(" " + i + " VS " + k);
				ClassifierData data = createClassifierData(digit);
				data.createFeatureNameList();
				data.setType(data.MultiClass);
				data.setName(i+"Vs"+k);
				classifier.addClassifierData(data);
				logger.info(data);
				
//logger.info( " the data is "+data.getClassifierName()+" with name "+data.getName());
				// IR.setDataOptions(digit, temp.feat, temp.regions);
				// logger.info("the features size array is equal to "+featArrs.size()
				// +"  and the region size array is = "+regions.size());

				//		
				// logger.info(
				// "Classifier  of "+i+" VS "+k+"  "+result.toString());
				// str+= "Classifier  of "+i+" VS "+k+"  "+ result.toString();
				// str+=newline;

			}

		}
		logger
				.info("-----------------------finihsed the settings now train and tst ");
		classifier.initClassifiers();
		classifier.startTrainid=0;
		classifier.Interupt=50;
		classifier.TrainSystem();
		classifier.TestSystem();
		logger.info(classifier.getResult());
		logger
				.info("@###################################################################333");
		logger.info(str);
		// IR.LoadData();
		// IR.BuildClassifier();

	}

	// private ClassifierData createClassifierData(ArrayList<Integer> digit) {
	// ClassifierData d=new ClassifierData();
	// d.digit=digit;
	//		
	//		
	// return d;
	// }
  
	private ClassifierData createClassifierData(ArrayList<Integer> digit) {
		ClassifierData temp = null;
		ClassifierData tempLoop= null;
			int d1 = digit.get(0);
		for (int i = 0; i < digit.size(); i++) {
			d1 = digit.get(i);
		
	   //logger.info(" digit" + d1);
//		if ( SIMILARFEATURE)
//		{
//			temp=addFeaturesForALLDigit();
//			temp.digit = digit;
//			temp.createFeatureNameList();
//			return temp;
//		}
		if (d1 == 0) {

			temp = addFeaturesForDigit0();

		}
		if (d1 == 1) {

			temp = addFeaturesForDigit1();

		}
		if (d1 == 2) {

			temp = addFeaturesForDigit2();

		}
		if (d1 == 3) {

			temp = addFeaturesForDigit3();

		}
		if (d1 == 4) {

			temp = addFeaturesForDigit4();

		}
		if (d1 == 5) {

			temp = addFeaturesForDigit5();

		}
		if (d1 == 6) {

			temp = addFeaturesForDigit6();

		}
		if (d1 == 7) {

			temp = addFeaturesForDigit7();

		}
		if (d1 == 8) {

			temp = addFeaturesForDigit8();

		}
		if (d1 == 9) {

			temp = addFeaturesForDigit9();

		}
		temp.digit = digit;
		temp.createFeatureNameList();
		
		tempLoop=lib.mergeClassifier(tempLoop, temp);
		
		
		
	}
	tempLoop.createFeatureNameList();	
	//	logger.info(" The info of the is classifier is "+tempLoop.getClassifierName()+"and"+temp.getName());

		return tempLoop;
	}
	
	private ClassifierData addFeaturesForALLDigit() {

		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		int MaxH=2;
		int MaxV=2;
		ArrayList<String> AllFeatures2;
		for (int i = 0; i < MaxH; i++) {
			for (int j = 0; j < MaxV; j++) {
				AllFeatures2 = getGeneralFeatures(BasicFeature);
				feat.add(AllFeatures2);
				regions.add(getRegion(MaxH, MaxV, i, j));
			}
				
		}
		
//		// -------------------------------------------
//	
//		// ------------------------------------------
//		AllFeatures2 = getGeneralFeatures(BasicFeature);
////		AllFeatures2.add("PbinF4R");
////		AllFeatures2.add("PbinL4C");
//		feat.add(AllFeatures2);
//		regions.add(getRegion(2, 2, 0, 1));
//		// -------------------------------------------
//		AllFeatures2 = getGeneralFeatures(BasicFeature);
//
////		AllFeatures2.add("PbinL4R");
////
////		AllFeatures2.add("PbinF4C");
//
//		feat.add(AllFeatures2);
//		regions.add(getRegion(2, 2, 1, 0));
//		// -------------------------------------------
//
//		AllFeatures2 = getGeneralFeatures(BasicFeature);
//
////		AllFeatures2.add("PbinL4R");
////		AllFeatures2.add("PbinL4C");
//
//		feat.add(AllFeatures2);
//		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;

		// logger.info( " the regions sizeeeeeeeee is "+temp.regions.size());
		return temp;

	}

	

	private ArrayList<String> getGeneralFeatures(int type) {
		ArrayList<String> AllFeatures2 = new ArrayList<String>();

		switch (type) {
		case 1:
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			//AllFeatures2.add("pb");
			
			break;
		case 2:
			AllFeatures2.add("frup");
			AllFeatures2.add("frdown");
			AllFeatures2.add("frleft");
			AllFeatures2.add("frright");
			
			//AllFeatures2.add("frup");
			//AllFeatures2.add("frdown");
			//AllFeatures2.add("pb");
			break;
		case 3:
			break;
		case 4:
			break;

		default:
			break;
		}

		return AllFeatures2;

	}

	private RegionCreater getRegion(int maxh, int maxv, int h, int v) {
		RegionCreater reg = new RegionCreater();
		reg.setMaxHorizontalRegion(maxh);
		reg.setMaxVerticalRegion(maxv);
		reg.setHorizonal(h);
		reg.setVertical(v);
		return reg;
	}

	private ClassifierData addFeaturesForDigit0() {

		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(NoFeature);
		AllFeatures2.add("wsb");
		feat.add(AllFeatures2);

		regions.add(getRegion(1, 1, 0, 0));
		
		// -------------------------------------------
	 AllFeatures2 = getGeneralFeatures(BasicFeature);
	 AllFeatures2.add( "CountNegativeTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));
		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);
		 AllFeatures2.add( "CountPositiveTransition");
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4C");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));
		// -------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);
		 
//		AllFeatures2.add("PbinL4R");
//
//		AllFeatures2.add("PbinF4C");
		AllFeatures2.add( "CountPositiveTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

//		AllFeatures2.add("PbinL4R");
//		AllFeatures2.add("PbinL4C");
		 AllFeatures2.add( "CountNegativeTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;

		// logger.info( " the regions sizeeeeeeeee is "+temp.regions.size());
		return temp;

	}

	private ClassifierData addFeaturesForDigit1() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		AllFeatures2.add( "CountZeroTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);
		AllFeatures2.add( "CountZeroTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);
		AllFeatures2.add( "CountZeroTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);
		AllFeatures2.add( "CountZeroTransition");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit2() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);
		
		AllFeatures2.add("PbinF4R");
		AllFeatures2.add("PbinL4R");
	
		
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4R");
//		AllFeatures2.add("PbinL4C");
//		AllFeatures2.add("PbinF4C");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);
		AllFeatures2.add("PbinF4R");
	
		AllFeatures2.add("PbinL4C");
		
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);
		
		AllFeatures2.add("PbinL4R");
		

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);
		
		AllFeatures2.add("PbinL4R");
	
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit3() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);
AllFeatures2.add("pb");
AllFeatures2.add("PbinF4R");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);
		AllFeatures2.add("PbinF4R");

		AllFeatures2.add("PbinL4C");
	
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		AllFeatures2.add("pb");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);
	
		AllFeatures2.add("PbinL4R");
		AllFeatures2.add("PbinL4C");
		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit4() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit5() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit6() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit7() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit8() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}
	private ClassifierData addFeaturesForDigit9() {
		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
		// -------------------------------------------
		ArrayList<String> AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 0));

		// ------------------------------------------
		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 0, 1));

		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 0));
		// -------------------------------------------

		AllFeatures2 = getGeneralFeatures(BasicFeature);

		feat.add(AllFeatures2);
		regions.add(getRegion(2, 2, 1, 1));

		// -------------------------------------------

		ClassifierData temp = new ClassifierData();
		temp.feat = feat;
		temp.regions = regions;
		return temp;

	}

//	private ClassifierData addFeaturesForDigit2() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit3() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit4() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit5() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit6() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit7() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit8() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4R");
//
//		AllFeatures2.add("PbinF4C");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4R");
//		AllFeatures2.add("PbinL4C");
//		AllFeatures2.add("PbinF4C");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}
//
//	private ClassifierData addFeaturesForDigit9() {
//
//		ArrayList<ArrayList<String>> feat = new ArrayList<ArrayList<String>>();
//		ArrayList<RegionCreater> regions = new ArrayList<RegionCreater>();
//		// -------------------------------------------
//		ArrayList<String> AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4R");
//		AllFeatures2.add("PbinL4C");
//		AllFeatures2.add("PbinF4C");
//
//		RegionCreater reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//
//		// ------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(0);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(0);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//		AllFeatures2 = new ArrayList<String>();
//		AllFeatures2.add("frup");
//		AllFeatures2.add("frdown");
//		AllFeatures2.add("frleft");
//
//		AllFeatures2.add("frright");
//		AllFeatures2.add("PbinF4R");
//		AllFeatures2.add("PbinL4R");
//		AllFeatures2.add("PbinL4C");
//		AllFeatures2.add("PbinF4C");
//
//		reg = new RegionCreater();
//		reg.setMaxVerticalRegion(2);
//		reg.setMaxHorizontalRegion(2);
//		reg.setHorizonal(1);
//		reg.setVertical(1);
//
//		feat.add(AllFeatures2);
//		regions.add(reg);
//		// -------------------------------------------
//
//		ClassifierData temp = new ClassifierData();
//		temp.feat = feat;
//		temp.regions = regions;
//		return temp;
//
//	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
