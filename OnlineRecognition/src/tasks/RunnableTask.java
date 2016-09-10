/**
 *
 */
package tasks;

import java.util.Observer;

/**
 * @author TOSHIBA
 *
 */
public interface RunnableTask extends Runnable , Observer{

	//public void RunTask();

	//public void setSettings(TaskSettings task);
	//public TaskSettings getSettings();

	public void addObserver(TaskController taskController);

}
