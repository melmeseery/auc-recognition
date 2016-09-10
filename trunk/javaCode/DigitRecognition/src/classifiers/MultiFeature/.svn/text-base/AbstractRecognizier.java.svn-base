/**
 * 
 */
package classifiers.MultiFeature;

import gui.AppDefaults;
import classifiers.GeneralClassifier;
import classifiers.LibLinearSingleClassifier;
import classifiers.LibSVMSingleClassifier;
import classifiers.wekaClassifier;
import data.dataset.DataSet;

/**
 * @author TOSHIBA
 *
 */
public class AbstractRecognizier {

	protected  static final int classes = AppDefaults.CATEGORY_SIZE;
	// classifier that will be used in classificaiton 
	protected GeneralClassifier classifier=null;
	protected DataSet TrainData=null;
	protected DataSet TestData=null;
	
	public static int CLASSIFIER_WEKA=1;
	public static int CLASSIFIER_LIBLINEAR=2;
	public static int CLASSIFIER_LIBSVM=3;
	protected int ClassifierType=CLASSIFIER_LIBLINEAR;
	public static int TRAIN_TEST=0;
	public static int TRAIN_VALIDATE=1;
protected	int TrainMode=TRAIN_VALIDATE;
	
	
public void createClassifier(){
	
	switch (ClassifierType) {
	case 1:
		classifier =new wekaClassifier();
		break;
	case 2:
		classifier= new LibLinearSingleClassifier();
		break;
	case 3:
		classifier =new LibSVMSingleClassifier();
		break;
		default:
			classifier =new wekaClassifier();
			break;
		
	}
	
	classifier.setDefaultOptions();
	 
}
	
}
