/**
 * 
 */
package General.gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LF5Appender;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

import General.tasks.TaskController;
import General.tasks.TaskSettings;
import General.tasks.RunnableTask;


/**
 * @author Maha
 *
 */
public class TaskMainInterface extends JFrame implements Observer {
	private static transient final Logger logger = Logger.getLogger( TaskMainInterface .class);
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JProgressBar jProgressBar = null;
	private JProgressBar jProgressBar1 = null;
	private JButton jButton5 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JButton jButton6 = null;
	private JLabel jLabel2 = null;
	private JProgressBar jProgressBar2 = null;
	private JButton jButton7 = null;
	private JButton jButton8 = null;
	ArrayList<TaskSettings> tasklist=new ArrayList<TaskSettings>();  //  @jve:decl-index=0:
	private DefaultListModel listModel;
	
	Thread th;
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 19, 464, 219));
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			//if (listModel==null)
				listModel = new DefaultListModel();
				listModel.addElement(" Tasks........... ");
			jList = new JList(listModel);
			jList.setVisibleRowCount(15);
			jList.setModel(listModel);
		}
		return jList;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(503, 21, 87, 24));
			jButton.setText("Add Task");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Open the task options ............."); // TODO Auto-generated Event stub actionPerformed()
					TaskSettings task= OpenAndCreateTask();
					AddTaskToList(task);   
				}
			});
		}
		return jButton;
	}

	
	public void AddTaskToList(TaskSettings task){//add to list of task and inteface list.
//		if (task==null){
//			
//			task=new 
//		}
	if (task!=null){
	
		tasklist.add(task);
		task.generateTheFullString();
		//now add to the list of h
		logger.info(" adding the task "+task.getTaskString());
		listModel.addElement(task.getTaskString());
		jList.setModel(listModel);
		jList.updateUI();
		jList.setSelectedIndex(listModel.size()-1);
		jList.ensureIndexIsVisible(listModel.size()-1 );
	//	getJList().
//		listModel.addElement("Scott Hommel");
//		listModel.addElement("Alan Sommerer");
	}
	
		
	}
	
	public TaskSettings OpenAndCreateTask(){//generate the task..
		
		//
		TaskSettingInterface frameop=new TaskSettingInterface();
		//frameop.setVisible(true);
		frameop.setModal(true);

		
		if (frameop.TaskOK){
			logger.info(" this is inside the a task returive.....");
			return frameop.getTask();
		}
		 
		return null;
		
	}
	
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(503, 68, 70, 24));
			jButton1.setText("Delete");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(503, 162, 109, 24));
			jButton2.setText("Run Selected");
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(503, 209, 73, 24));
			jButton3.setText("Run All");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Run the taskssssss ............."); // TODO Auto-generated Event stub actionPerformed()
				  
					RunAllTasks();
					
				}
			});
		}
		return jButton3;
	}
private void RunAllTasks(){
	
	TaskController t=new TaskController();
	
//	for (int i = 0; i < this.tasklist.size(); i++) {
//		
//		
//		
//	}
	t.setTasks(this.tasklist);
	
	t.addObserver(this);
	
	th=new Thread(t);
	th.start();
	
	
}
	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(503, 115, 133, 24));
			jButton4.setText("Edit Task Options");
		}
		return jButton4;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(78, 256, 462, 19));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes jProgressBar1	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar1() {
		if (jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
			jProgressBar1.setBounds(new Rectangle(78, 287, 463, 19));
		}
		return jProgressBar1;
	}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setBounds(new Rectangle(594, 285, 127, 25));
			jButton5.setText("Open Output ");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(" Open logger. ............."); // TODO Auto-generated Event stub actionPerformed()
			   
				 Console c=new Console();
			}
		});
			
		}
		return jButton5;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setBounds(new Rectangle(594, 315, 127, 25));
			jButton6.setText("Exit");
		}
		return jButton6;
	}

	/**
	 * This method initializes jProgressBar2	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar2() {
		if (jProgressBar2 == null) {
			jProgressBar2 = new JProgressBar();
			jProgressBar2.setBounds(new Rectangle(219, 316, 324, 21));
		}
		return jProgressBar2;
	}

	/**
	 * This method initializes jButton7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setBounds(new Rectangle(564, 251, 75, 23));
			jButton7.setText("Pause");
		}
		return jButton7;
	}

	/**
	 * This method initializes jButton8	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setBounds(new Rectangle(664, 251, 78, 25));
			jButton8.setText("Re run");
		}
		return jButton8;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
				TaskMainInterface thisClass = new TaskMainInterface();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TaskMainInterface() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(766, 381);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(14, 318, 189, 18));
			jLabel2.setText("File Progress:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(13, 287, 46, 19));
			jLabel1.setText("Task");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 250, 60, 25));
			jLabel.setText("All Tasks");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJProgressBar1(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJButton6(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJProgressBar2(), null);
			jContentPane.add(getJButton7(), null);
			jContentPane.add(getJButton8(), null);
		}
		return jContentPane;
	}

	public void update(Observable o, Object arg) {
		
		if (o instanceof TaskController ) {
			TaskController  temp = (TaskController ) o;
			
		//	logger.trace( " inside the inteface updated from controller ");
			// set current file 
//			getJProgressBarFiles().setValue(temp.getCurrentFile());
//			getJProgressBarFiles().setString(""+temp.getCurrentFile());
//			
//			//get category 
//			
//			  getJProgressBarCategory().setMaximum(temp.getMaxCurrentCat()-1);
//			 jLabelMaxCat.setText(""+temp.getMaxCurrentCat());
//			// update the progress bars
//		   getJProgressBarCategory().setValue(temp.getCurrentCat());
//		   getJProgressBarCategory().setString(""+temp.getCurrentCat());
//		   jLabelCategoryname.setText(temp.getCurrentCatString());
//		   
//		   // now for example 
//		   
//		   getJProgressBarExamples().setMaximum(temp.getMaxCurExample()-1);
//		   getJProgressBarExamples().setValue(temp.getCurrentExample());
//		   getJProgressBarExamples().setString(""+temp.getCurrentExample());
//		   jLabelMaxExamples.setText(""+temp.getMaxCurExample());
			
		}
		// TODO Auto-generated method stub
		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
