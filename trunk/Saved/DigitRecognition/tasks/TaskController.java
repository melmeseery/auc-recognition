/**
 * 
 */
package tasks;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Maha
 *
 */
public class TaskController extends Observable implements Runnable, Observer  {
	ArrayList<RunnableTask> tasks=null;
	boolean Runall=true;
	
	boolean StopAll=false;
	boolean StopCurrent=false;
	
    Thread th;
	
	int CurrentTaskNo=0;
	
	
	public ArrayList<RunnableTask> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<RunnableTask> tasks) {
		this.tasks = tasks;
	}

	public TaskController() {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		for (int j = 0; j < tasks.size(); j++) {
			
			RunnableTask t=tasks.get(j);
			t.addObserver(this);
			
          th=new Thread(t);
            //check time if time is less than threshold then wait for the thread to finish......
            th.start();
             int count=0;
         while (th.isAlive()){
//         for (int i = 0; i < 100000; i++) {
//			//do nothing just cont ...
//		} 
        	 if (count>2000)
        	 {
				 this.setChanged();
				 this.notifyObservers();
				 count=0;
        	 } 
        	 count++;
			
			
        	  
        	  
          }
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

	public void update(Observable o, Object arg) {
		
		if (o instanceof TaskController ) {
			RunnableTask temp = (	RunnableTask ) o;
		  //now set the thing that neeed changes in the interface 
			
			
			// then call set changedddddd
			 this.setChanged();
			 this.notifyObservers();
		}
		
	}

}
