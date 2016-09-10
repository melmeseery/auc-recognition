/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

import classifiers.command.LibSvmCommandClassifier;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

/**
 * @author TOSHIBA
 *
 */
public class TestRuns {

	private static transient final Logger logger = Logger.getLogger(  TestRuns .class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TaskController tc;
		
		
		ArrayList<RunnableTask> tasks=new ArrayList<RunnableTask>(); 
		
		TestRuns test=new TestRuns();
		
		 RunnableTask tr=test.getTask();
		tasks.add(tr);
	//// create the controller 
		tc=new TaskController();
		
		tc.setTasks(tasks);
		//create the thread and runnnnnnnnnnn
		Thread th=new Thread(tc);
		th.start();
		//logger.info("finished.............");
		

	}

	public RunnableTask getTask(){
		
	return	new   runReadDigit();
		
		
	}
	public class runReadDigit implements  RunnableTask {

		
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
			
			logger.info("runnning..................................");
		}

		@Override
		public void update(Observable o, Object arg) {
			
			
		}
		 
	}
	
}
