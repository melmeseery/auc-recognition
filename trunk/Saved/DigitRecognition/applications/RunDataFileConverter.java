/**
 * 
 */
package applications;

import gui.AppDefaults;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Observable;

import org.apache.log4j.Logger;

import applications.subGeneralTest.RunSimpleTests;
import data.dataset.DataBaseConnector;
import data.dataset.DataSet;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

/**
 * @author maha
 *
 */
public class RunDataFileConverter implements RunnableTask {
	static String  ext=DataSet. FILE_INPUT_EXTENTION_ARFF;
	
	private static transient final Logger logger = Logger.getLogger(AppDefaults.class);
	/**
	 * 
	 */
	public RunDataFileConverter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#addObserver(tasks.TaskController)
	 */
	@Override
	public void addObserver(TaskController taskController) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#getSettings()
	 */
	@Override
	public TaskSettings getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see tasks.RunnableTask#setSettings(tasks.TaskSettings)
	 */
	@Override
	public void setSettings(TaskSettings task) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		AppDefaults.ReadSetting("set.txt");
//		convert();
//	compare();	
//		
       ConvertAllFilesInDic();	
      	AppDefaults.saveSettings("set.txt");
	}

	private void ConvertAllFilesInDic() {
		File dir = new File(AppDefaults.CurrentRunDir);
		String[] children;// = dir.list();

		// It is also possible to filter the list of returned files.
		// This example does not return any files that start with `.'.
		FilenameFilter filterAr = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith("."+ext);
		    }
		};		
//		FilenameFilter filtercsv = new FilenameFilter() {
//		    public boolean accept(File dir, String name) {
//		        return !name.startsWith(".csv");
//		    }
//		};
		
		logger.info(" the dir "+AppDefaults.CurrentRunDir+"   ext"+ext);
		children = dir.list(filterAr);
	
		String withoutext;
		for (int i = 0; i < children.length & i<AppDefaults.Interupt; i++) {
			DataSet ds=new DataSet();
			ds.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		     logger.info(" the childrent first is "+children[i]);
		withoutext=AppDefaults.CurrentRunDir+AppDefaults.CurrentOSSeperator+children[i];
			
			ds.ReadFromFile(withoutext);	
			ds.ConvertToArrays();
			ds.setFormat(DataSet.FILE_INPUT_FORMAT_EXCELL);
			
		
			ds.SaveToFile(withoutext+".xls");
		}
		
		
		///// 
		
		

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		//DataBaseConnector.OS=DataBaseConnector.OS_LINUX;
			
		RunDataFileConverter test=new RunDataFileConverter();
			Thread th=new Thread(test);
			th.run();
	}
}
