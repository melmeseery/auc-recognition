/**
 * 
 */
package applications.subGeneralTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import data.dataset.DataBaseConnector;
import data.dataset.DataSet;
import data.dataset.MNISTDataSetGenerator;
import data.feature.FeatureStat;
import data.image.Digit;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

/**
 * @author TOSHIBA
 *
 */
public class RunFeatureEvaluater extends Observable implements RunnableTask{

	private static transient final Logger logger = Logger.getLogger(  RunFeatureEvaluater.class);
	private static final String OutDir = "D:\\AUC\\Programs\\Workspace\\DigitRecognition\\ouput\\stats\\";
	//need to have data set connector 
	//MNISTDataSetGenerator  db;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		
		RunFeatureEvaluater test=new RunFeatureEvaluater();
		 DataBaseConnector.OS=DataBaseConnector.OS_WINDOWS;
		Thread th=new Thread(test);
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
	public void FromDataSetToFeatureStat(DataSet dataset ){
		ArrayList<FeatureStat> fstate=new 	ArrayList<FeatureStat> ();
		if (dataset!=null)
		{	
			logger.info("now i will create the state and savve files ");	
			fstate= dataset.getComputeFeatureState();
		}

		
		
		logger.info("Savvving............");
            HSSFWorkbook wb =  new HSSFWorkbook();
		
	   
		
		for (int i = 0; i <fstate.size(); i++) {
			fstate.get(i).writeToXlsSheet(wb);
		}
		
		
		
			try { 
				FileOutputStream fileOut;
				fileOut = new FileOutputStream( OutDir+"fstateAftercorrcet.xls");
				 wb.write(fileOut);
				    fileOut.close();
				    
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
			
		
		
	}
	public DataSet runTheDigitsFeatureAllDigits( ArrayList<String> featuresNames){
		logger.info("inside the rundigitfeature in main ");
		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		for (int i = 0; i < 10; i++) {
			digitsForTest.add(new Integer(i));
		}
		
		//digitsForTest.add(new Integer(d2));
		Digit.setFeaturesToCompute(featuresNames);
		Digit.loadAllFeatureArray();
		//Digit.DisplayFeatureString();
		MNISTDataSetGenerator db=new MNISTDataSetGenerator();
		db.setStatus( DataBaseConnector.TRAIN);
		DataSet dataset = db.GetDataSetByDigits(digitsForTest);
	//	dataset.ShuffleDataSet();
	//dataset.setFeaturesToUse(featuresNames);
//		dataset.setFormat(format);
	//dataset.SaveToFile( datasetfilename);
	Digit.DisplayFeatureString();
	return dataset;
		
		
	}

	@Override
	public void run() {
		 logger.info("  in the ruuuuuuuunnnnnnnnnnnn");
		DataSet data =null;
		
		data=runTheDigitsFeatureAllDigits(null);
		 FromDataSetToFeatureStat(data);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}
