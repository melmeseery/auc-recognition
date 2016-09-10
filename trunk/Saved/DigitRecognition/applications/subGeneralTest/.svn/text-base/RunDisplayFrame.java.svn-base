package applications.subGeneralTest;

import gui.ImageViewer;

import java.awt.image.BufferedImage;
import java.util.Observable;

import org.apache.log4j.Logger;

import data.dataset.DataBaseConnector;

import tasks.RunnableTask;
import tasks.TaskController;
import tasks.TaskSettings;

public class RunDisplayFrame extends Observable implements RunnableTask  {
	private static transient final Logger logger = Logger.getLogger(  RunDisplayFrame .class);
	public static void main(String[] args) {
		
		 DataBaseConnector.OS=DataBaseConnector.OS_WINDOWS;
	org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
	
		 
	RunDisplayFrame test=new RunDisplayFrame();
		Thread th=new Thread(test);
		th.run();
	}
	public static BufferedImage ReadFrom(String id){
		
		return null;
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
		ImageViewer frame=new ImageViewer();
		//frame.pack();
		frame.setVisible(true);
		
	}
	@Override
	public void update(Observable o, Object arg) {
	 
		
	}
}
