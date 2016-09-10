package General.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import General.AppDataSettings;
import General.tasks.TaskSettings;

import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import javax.swing.WindowConstants;

public class TaskSettingInterface extends JDialog {
	private static transient final Logger logger = Logger.getLogger( TaskSettingInterface.class);
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanelParameters = null;
	private JCheckBox jCheckBox3 = null;
	private JSlider jSlider = null;
	private JSlider jSlider1 = null;
	private JSlider jSlider2 = null;
	private JSlider jSlider3 = null;
	private JCheckBox jCheckBox4 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JPanel jPanelAlgoirthms = null;
	private JComboBox jComboBoxLibraries = null;
	private JLabel jLabelLibrary = null;
	private JComboBox jComboBoxAlgorithm = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel1 = null;
	private JPanel jPanelDigitPairs = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox1 = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox2 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel14 = null;
	private JLabel jLabel15 = null;
	private JLabel jLabel16 = null;
	private JButton jButton4 = null;
	private JButton jButton5 = null;
	private JTextArea jTextArea = null;
	private JLabel jLabel17 = null;
	//String Direct/ory=".";
	
	private TaskSettings Task=null;  //  @jve:decl-index=0:
	public TaskSettings getTask() {
		return Task;
	}
	public void setTask(TaskSettings task) {
		Task = task;
	}
	private JComboBox jComboBox3 = null;
	private JLabel jLabel18 = null;
	
	boolean TaskOK=false;

	/**
	 * This is the default constructor
	 */
	public TaskSettingInterface() {
		super();
		Task =new TaskSettings();
		initialize();
		setVisible(true);
	}
        public TaskSettingInterface(TaskSettings task){
        	Task =new TaskSettings (task);
    		initialize();
    		setVisible(true);
        	
        	
        }
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(690, 426);
		this.setModal(true);
		this.setContentPane(getJContentPane());
		this.setTitle("Task Options ");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setVisible(true);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel17 = new JLabel();
			jLabel17.setBounds(new Rectangle(5, 311, 68, 32));
			jLabel17.setText("Commands");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanelParameters(), null);
			jContentPane.add(getJPanelAlgoirthms(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanelDigitPairs(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(getJTextArea(), null);
			jContentPane.add(jLabel17, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelParameters	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelParameters() {
		if (jPanelParameters == null) {
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(198, 103, 41, 10));
			jLabel16.setText("JLabel");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(200, 83, 39, 14));
			jLabel15.setText("JLabel");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(206, 61, 32, 13));
			jLabel14.setText("JLabel");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(208, 34, 27, 15));
			jLabel8.setText("JLabel");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(3, 100, 21, 17));
			jLabel7.setText("  H");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(3, 78, 21, 17));
			jLabel6.setText("  S");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(3, 55, 21, 17));
			jLabel5.setText("  G");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(3, 38, 21, 17));
			jLabel4.setText(" C");
			jPanelParameters = new JPanel();
			jPanelParameters.setLayout(null);
			jPanelParameters.setBounds(new Rectangle(402, 169, 254, 126));
			jPanelParameters.add(getJCheckBox3(), null);
			jPanelParameters.add(getJSlider(), null);
			jPanelParameters.add(getJSlider1(), null);
			jPanelParameters.add(getJSlider2(), null);
			jPanelParameters.add(getJSlider3(), null);
			jPanelParameters.add(getJCheckBox4(), null);
			jPanelParameters.add(jLabel4, null);
			jPanelParameters.add(jLabel5, null);
			jPanelParameters.add(jLabel6, null);
			jPanelParameters.add(jLabel7, null);
			jPanelParameters.add(jLabel8, null);
			jPanelParameters.add(jLabel14, null);
			jPanelParameters.add(jLabel15, null);
			jPanelParameters.add(jLabel16, null);
		}
		return jPanelParameters;
	}

	/**
	 * This method initializes jCheckBox3	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox3() {
		if (jCheckBox3 == null) {
			jCheckBox3 = new JCheckBox();
			jCheckBox3.setBounds(new Rectangle(13, 8, 107, 21));
			jCheckBox3.setText("Use Defaules");
			jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" select defaults...."); // TODO Auto-generated Event stub actionPerformed()
					if (jCheckBox3.isSelected()){
						
						//// change the visibility of the  
						logger.info(" need to disable the sliders.................");
						
					}
					else 
					{
						
					}
				}
			});
		}
		return jCheckBox3;
	}

	/**
	 * This method initializes jSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider() {
		if (jSlider == null) {
			jSlider = new JSlider();
			jSlider.setBounds(new Rectangle(20, 37, 181, 16));
		}
		return jSlider;
	}

	/**
	 * This method initializes jSlider1	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider1() {
		if (jSlider1 == null) {
			jSlider1 = new JSlider();
			jSlider1.setBounds(new Rectangle(20, 62, 181, 16));
		}
		return jSlider1;
	}

	/**
	 * This method initializes jSlider2	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider2() {
		if (jSlider2 == null) {
			jSlider2 = new JSlider();
			jSlider2.setBounds(new Rectangle(20, 80, 181, 16));
		}
		return jSlider2;
	}

	/**
	 * This method initializes jSlider3	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider3() {
		if (jSlider3 == null) {
			jSlider3 = new JSlider();
			jSlider3.setBounds(new Rectangle(20, 101, 181, 16));
		}
		return jSlider3;
	}

	/**
	 * This method initializes jCheckBox4	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox4() {
		if (jCheckBox4 == null) {
			jCheckBox4 = new JCheckBox();
			jCheckBox4.setBounds(new Rectangle(141, 7, 102, 21));
			jCheckBox4.setText("Optimize");
			jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Use optimize... "); // TODO Auto-generated Event stub actionPerformed()
//					if (getJCheckBox4().isSelected()){
//						
//						//need to 
//						
//						
//					}
					Task.RunOptimize=getJCheckBox4().isSelected();
				}
			});
		}
		return jCheckBox4;
	}

	/**
	 * This method initializes jPanelAlgoirthms	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelAlgoirthms() {
		if (jPanelAlgoirthms == null) {
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(13, 61, 61, 19));
			jLabel18.setText("Type");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 115, 69, 15));
			jLabel.setText("Algorithms");
			jLabelLibrary = new JLabel();
			jLabelLibrary.setBounds(new Rectangle(12, 27, 53, 20));
			jLabelLibrary.setText("Library");
			jPanelAlgoirthms = new JPanel();
			jPanelAlgoirthms.setLayout(null);
			jPanelAlgoirthms.setBounds(new Rectangle(401, 17, 258, 141));
			jPanelAlgoirthms.add(getJComboBoxLibraries(), null);
			jPanelAlgoirthms.add(jLabelLibrary, null);
			jPanelAlgoirthms.add(getJComboBoxAlgorithm(), null);
			jPanelAlgoirthms.add(jLabel, null);
			jPanelAlgoirthms.add(getJComboBox3(), null);
			jPanelAlgoirthms.add(jLabel18, null);
		}
		return jPanelAlgoirthms;
	}

	/**
	 * This method initializes jComboBoxLibraries	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxLibraries() {
		if (jComboBoxLibraries == null) {
			String [] libs={"Torch","LibSVM","LibSVM Java","Weka"};
			jComboBoxLibraries = new JComboBox(libs);
			jComboBoxLibraries.setBounds(new Rectangle(90, 27, 114, 21));
			jComboBoxLibraries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" use library... "); // TODO Auto-generated Event stub actionPerformed()
					
					//int alg=getJComboBoxAlgorithm().getSelectedIndex();
					Task.setLibrary(getJComboBoxLibraries().getSelectedIndex(),(String) getJComboBoxLibraries().getSelectedItem());
		
					
				}
			});
		}
		return jComboBoxLibraries;
	}

	/**
	 * This method initializes jComboBoxAlgorithm	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxAlgorithm() {
		if (jComboBoxAlgorithm == null) {
			String[]  algs={"SVM RBF","SVM Linear","Linear","Neural"};
			jComboBoxAlgorithm = new JComboBox(algs);
			jComboBoxAlgorithm.setBounds(new Rectangle(90, 111, 114, 21));
			jComboBoxAlgorithm.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" algorithm "); // TODO Auto-generated Event stub actionPerformed()
					//int alg=getJComboBoxAlgorithm().getSelectedIndex();
					Task.setAlgorithm(getJComboBoxAlgorithm().getSelectedIndex(),(String) getJComboBoxAlgorithm().getSelectedItem());
				}
			});
		}
		return jComboBoxAlgorithm;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(3, 16, 96, 22));
			jLabel1.setText("Main Tasks");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(8, 10, 358, 60));
			jPanel.add(getJComboBox(), null);
			jPanel.add(jLabel1, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {

			String[] taString={"Train","Test","Formate","Validate","Train-Test","Verify","Command"};
			jComboBox = new JComboBox(taString);
			jComboBox.setBounds(new Rectangle(103, 17, 220, 25));
			jComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" selecting task "); // TODO Auto-generated Event stub actionPerformed()
					Task.setTask(getJComboBox().getSelectedIndex(),(String)getJComboBox().getSelectedItem());
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jPanelDigitPairs	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDigitPairs() {
		if (jPanelDigitPairs == null) {
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(4, 120, 337, 25));
			jLabel13.setText("Option File");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(4, 150, 353, 27));
			jLabel12.setText("Directory");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(4, 79, 337, 25));
			jLabel11.setText("Format File");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(4, 45, 337, 25));
			jLabel10.setText("Test File");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(4, 13, 337, 25));
			jLabel9.setText("Train File");
			jPanelDigitPairs = new JPanel();
			jPanelDigitPairs.setLayout(null);
			jPanelDigitPairs.setBounds(new Rectangle(13, 115, 368, 184));
			jPanelDigitPairs.add(getJButton2(), null);
			jPanelDigitPairs.add(getJButton3(), null);
			jPanelDigitPairs.add(getJButton(), null);
			jPanelDigitPairs.add(getJButton1(), null);
			jPanelDigitPairs.add(jLabel9, null);
			jPanelDigitPairs.add(jLabel10, null);
			jPanelDigitPairs.add(jLabel11, null);
			jPanelDigitPairs.add(jLabel12, null);
			jPanelDigitPairs.add(jLabel13, null);
		}
		return jPanelDigitPairs;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(345, 13, 17, 26));
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				private String FileName;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" change train file "); // TODO Auto-generated Event stub actionPerformed()
					
					
					
					JFileChooser fc = new JFileChooser(new File(Task.DirName));
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//	fc.setCurrentDirectory();
					// System.out.println("actionPerformed()");
				
				//	fc.setFileFilter( new ImageFileFilter());

					// In response to a button click:
					if (fc.showOpenDialog(getJContentPane().getParent()) == JFileChooser.APPROVE_OPTION) { // .showOpenDialog(this);
						// System.out.println("actionPerformed open ()");
						if (fc.getSelectedFile().isFile()){
						FileName=fc.getSelectedFile().getAbsolutePath();
						Task.TrainFilename=FileName;
						jLabel9.setText( fc.getSelectedFile().getName());
						jLabel12.setText( fc.getSelectedFile().getParent() );
						}
						Task.DirName=fc.getCurrentDirectory().getAbsolutePath();
						logger.trace(" setting directory .................."+Task.DirName);
						/*
					if (fc.getSelectedFile().isFile()){
				
						//logger.trace(" setting directory .................."+fc.gets);
					}
					else if (fc.getSelectedFile().isDirectory())
					{
						DirName=fc.getSelectedFile().getAbsolutePath();
	
						getJTextAreaFilename().setText(DirName);
						
					}*/
						
					}	
				}
					
			 
			});
			jButton2.setText("Train File");
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
			jButton3.setBounds(new Rectangle(345, 45, 17, 26));
			jButton3.setText("Test File");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				private String FileName;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Change test file "); // TODO Auto-generated Event stub actionPerformed()
					
					
					
					JFileChooser fc = new JFileChooser(new File(Task.DirName));
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//	fc.setCurrentDirectory();
					// System.out.println("actionPerformed()");
				
				//	fc.setFileFilter( new ImageFileFilter());

					// In response to a button click:
					if (fc.showOpenDialog(getJContentPane().getParent()) == JFileChooser.APPROVE_OPTION) { // .showOpenDialog(this);
						// System.out.println("actionPerformed open ()");
						if (fc.getSelectedFile().isFile()){
						FileName=fc.getSelectedFile().getAbsolutePath();
						Task.TestFilename=FileName;
						jLabel10.setText( fc.getSelectedFile().getName());
						jLabel12.setText( fc.getSelectedFile().getParent() );
						}
						Task.DirName=fc.getCurrentDirectory().getAbsolutePath();
						logger.trace(" setting directory .................."+Task.DirName);
						/*
					if (fc.getSelectedFile().isFile()){
				
						//logger.trace(" setting directory .................."+fc.gets);
					}
					else if (fc.getSelectedFile().isDirectory())
					{
						DirName=fc.getSelectedFile().getAbsolutePath();
	
						getJTextAreaFilename().setText(DirName);
						
					}*/
						
					}	
				}
					
			 
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(345, 79, 17, 26));
			jButton.setText("Format File");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				private String FileName;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" format file "); // TODO Auto-generated Event stub actionPerformed()
					
					
					
					JFileChooser fc = new JFileChooser(new File(Task.DirName));
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//	fc.setCurrentDirectory();
					// System.out.println("actionPerformed()");
				
				//	fc.setFileFilter( new ImageFileFilter());

					// In response to a button click:
					if (fc.showOpenDialog(getJContentPane().getParent()) == JFileChooser.APPROVE_OPTION) { // .showOpenDialog(this);
						// System.out.println("actionPerformed open ()");
						if (fc.getSelectedFile().isFile()){
						FileName=fc.getSelectedFile().getAbsolutePath();
						Task.FormatFilename=FileName;
						jLabel11.setText( fc.getSelectedFile().getName());
						jLabel12.setText( fc.getSelectedFile().getParent() );
						}
						Task.DirName=fc.getCurrentDirectory().getAbsolutePath();
						logger.trace(" setting directory .................."+Task.DirName);
						/*
					if (fc.getSelectedFile().isFile()){
				
						//logger.trace(" setting directory .................."+fc.gets);
					}
					else if (fc.getSelectedFile().isDirectory())
					{
						DirName=fc.getSelectedFile().getAbsolutePath();
	
						getJTextAreaFilename().setText(DirName);
						
					}*/
						
					}	
				}
					
			 
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(345, 120, 17, 26));
			jButton1.setText("Option File");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				private String FileName;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" option file "); // TODO Auto-generated Event stub actionPerformed()
					
					
					
					JFileChooser fc = new JFileChooser(new File(Task.DirName));
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//	fc.setCurrentDirectory();
					// System.out.println("actionPerformed()");
				
				//	fc.setFileFilter( new ImageFileFilter());

					// In response to a button click:
					if (fc.showOpenDialog(getJContentPane().getParent()) == JFileChooser.APPROVE_OPTION) { // .showOpenDialog(this);
						// System.out.println("actionPerformed open ()");
						if (fc.getSelectedFile().isFile()){
						FileName=fc.getSelectedFile().getAbsolutePath();
						Task.OptionFilename=FileName;
						jLabel13.setText( fc.getSelectedFile().getName());
						jLabel12.setText( fc.getSelectedFile().getParent() );
						}
						Task.DirName=fc.getCurrentDirectory().getAbsolutePath();
						logger.trace(" setting directory .................."+Task.DirName);
						/*
					if (fc.getSelectedFile().isFile()){
				
						//logger.trace(" setting directory .................."+fc.gets);
					}
					else if (fc.getSelectedFile().isDirectory())
					{
						DirName=fc.getSelectedFile().getAbsolutePath();
	
						getJTextAreaFilename().setText(DirName);
						
					}*/
						
					}	
				}
					
			 
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(171, 8, 31, 15));
			jLabel3.setText("To ");
			jLabel2 = new JLabel();
			jLabel2.setText("From");
			jLabel2.setBounds(new Rectangle(5, 7, 39, 16));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(11, 74, 351, 31));
			jPanel1.add(jLabel2, null);
			jPanel1.add(getJComboBox1(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJComboBox2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			String[] formates={"Torch","CSV","LibSVM","Arff"};
			jComboBox1 = new JComboBox( formates);
			jComboBox1.setBounds(new Rectangle(54, 6, 109, 17));
			jComboBox1.setSelectedIndex(0);
			Task.setFromFormat(getJComboBox1().getSelectedIndex());
			jComboBox1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					Task.setFromFormat(getJComboBox1().getSelectedIndex());
					
				}
			});
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jComboBox2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			String[] formates={"Torch","CSV","LibSVM","Arff"};
			jComboBox2 = new JComboBox(formates);
			jComboBox2.setSelectedIndex(0);
			Task.setToFormat(getJComboBox2().getSelectedIndex());
			jComboBox2.setBounds(new Rectangle(211, 6, 134, 17));
			jComboBox2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" format "); // TODO Auto-generated Event stub actionPerformed()
					Task.setToFormat(getJComboBox2().getSelectedIndex());
				}
			});
			 
		}
		return jComboBox2;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(163, 354, 145, 32));
			jButton4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			jButton4.setMnemonic(KeyEvent.VK_UNDEFINED);
		
			jButton4.setText("Save");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Save "); // TODO Auto-generated Event stub actionPerformed()
					Task.GenearalCommand=getJTextArea().getText();
					 TaskOK=true;
					 setVisible(false);
					
					
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			
			jButton5 = new JButton();
			jButton5.setBounds(new Rectangle(332, 356, 145, 32));
			jButton5.setText("Cancel");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Cancel   "); // TODO Auto-generated Event stub actionPerformed()
					 TaskOK=false;
					 Task=null;
					 setVisible(false);
				}
			});
		}
		return jButton5;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(70, 311, 586, 31));
		}
		return jTextArea;
	}
	/**
	 * This method initializes jComboBox3	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			String [] typess={"OVO","OVA","Single Class","Other"};
			jComboBox3 = new JComboBox(typess);
			jComboBox3.setBounds(new Rectangle(90, 62, 124, 19));
			jComboBox3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Multi Class type "); // TODO Auto-generated Event stub actionPerformed()
					Task.seMultiClassTypes(getJComboBox3().getSelectedIndex(),(String)getJComboBox3().getSelectedItem());
				}
			});
		}
		return jComboBox3;
	}

}  //  @jve:decl-index=0:visual-constraint="14,11"
