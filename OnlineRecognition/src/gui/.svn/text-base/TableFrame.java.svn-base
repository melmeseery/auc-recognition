package gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import data.SimpleInkObject;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

import app.SystemSettings;

import javax.swing.JTextField;

public class TableFrame extends JFrame {
	private static final Logger logger = Logger.getLogger(TableFrame.class);  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;

	StrokeDisplayFrame PageDisplayframe;
	GuiPageModel data=new GuiPageModel();  //  @jve:decl-index=0:
	//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	private File fileOpen=null;
	private File fileSave=null;
//
//	StrokeGroupTableModel  strokeData;
//
//	ArrayList<SimpleInkObject>  DeletedData;


	private JScrollPane jScrollPaneTable = null;
	private JTable jTableStroke = null;
	private JPanel jPanelControls = null;
	private JButton jButtonOpenFile = null;
	private JButton jButtonSaveStrokes = null;
	private JButton jButtonRefineStokes = null;
	private JButton jButtonDeleteStrokes = null;
	private JButton jButtonLoadIntialLabels = null;
	private JButton jButtonClose = null;
	private JButton jButtonShowPage = null;
	private JButton jButtonSavePage = null;

	private JButton jButtonSelectFile = null;

	private JTextField jTextFieldOpenFile = null;

	private JButton jButtonSelectSave = null;

	private JTextField jTextFieldSaveFolder = null;





	public TableFrame() throws HeadlessException {

		super();
		initialize();
	}
	public TableFrame(GuiPageModel datai)   {
		super();
		initialize();
		data=datai;
		getJButtonOpenFile().setEnabled(true);

	}
	public TableFrame(GraphicsConfiguration arg0) {
		super(arg0);

		initialize();
	}

	public TableFrame(String arg0) throws HeadlessException {
		super(arg0);

		initialize();
	}

	public TableFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes jScrollPaneTable
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneTable() {
		if (jScrollPaneTable == null) {
			jScrollPaneTable = new JScrollPane();
			jScrollPaneTable.setBounds(new Rectangle(3, 2, 670, 191));
			jScrollPaneTable.setViewportView(getJTableStroke());

		}
		return jScrollPaneTable;
	}

	/**
	 * This method initializes jTableStroke
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTableStroke() {
		if (jTableStroke == null) {
			jTableStroke = new JTable(data.getStrokeGroupData());//
			jTableStroke.setRowSelectionAllowed(true);
			jTableStroke.setShowGrid(true);
			jTableStroke.setDefaultRenderer(Color.class, new ColorRenderer(true));
			//jTableStroke.setSelectionMode(arg0)
			//jTableStroke.setBounds(2, 2, 200,100);
			//data.getStrokeGroupData().addTableModelListener(jTableStroke);


		}
		return jTableStroke;
	}

	/**
	 * This method initializes jPanelControls
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelControls() {
		if (jPanelControls == null) {
			jPanelControls = new JPanel();
			jPanelControls.setLayout(null);
			jPanelControls.setBounds(new Rectangle(3, 213, 688, 204));
			jPanelControls.add(getJButtonOpenFile(), null);
			jPanelControls.add(getJButtonSaveStrokes(), null);
			jPanelControls.add(getJButtonRefineStokes(), null);
			jPanelControls.add(getJButtonDeleteStrokes(), null);
			jPanelControls.add(getJButtonLoadIntialLabels(), null);
			jPanelControls.add(getJButtonClose(), null);
			jPanelControls.add(getJButtonShowPage(), null);
			jPanelControls.add(getJButtonSavePage(), null);
			jPanelControls.add(getJButtonSelectFile(), null);
			jPanelControls.add(getJTextFieldOpenFile(), null);
			jPanelControls.add(getJButtonSelectSave(), null);
			jPanelControls.add(getJTextFieldSaveFolder(), null);
		}
		return jPanelControls;
	}

	/**
	 * This method initializes jButtonOpenFile
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOpenFile() {
		if (jButtonOpenFile == null) {
			jButtonOpenFile = new JButton();
			jButtonOpenFile.setBounds(new Rectangle(573, 7, 108, 30));
			jButtonOpenFile.setText("OpenFile");
			jButtonOpenFile.setEnabled(false);
			jButtonOpenFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
     				System.out.println("   open the file noww...... ");
                     if(fileOpen!=null){
				data.openDHWFile(fileOpen.getAbsolutePath());
				getJButtonLoadIntialLabels().setEnabled(true);
                     }
                     else {
                    		data.openDHWFile();
                    		getJButtonLoadIntialLabels().setEnabled(true);
                     }


				}
			});
		}
		return jButtonOpenFile;
	}

	/**
	 * This method initializes jButtonSaveStrokes
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSaveStrokes() {
		if (jButtonSaveStrokes == null) {
			jButtonSaveStrokes = new JButton();
			jButtonSaveStrokes.setBounds(new Rectangle(563, 88, 113, 26));
			jButtonSaveStrokes.setText("Save Strokes");
			jButtonSaveStrokes.setEnabled(false);
			jButtonSaveStrokes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Save strokes. ...  "); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonSaveStrokes;
	}

	/**
	 * This method initializes jButtonRefineStokes
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonRefineStokes() {
		if (jButtonRefineStokes == null) {
			jButtonRefineStokes = new JButton();
			jButtonRefineStokes.setBounds(new Rectangle(168, 116, 131, 33));
			jButtonRefineStokes.setText("Remove Labels");
			jButtonRefineStokes.setEnabled(false);
			jButtonRefineStokes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonRefineStokes;
	}

	/**
	 * This method initializes jButtonDeleteStrokes
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonDeleteStrokes() {
		if (jButtonDeleteStrokes == null) {
			jButtonDeleteStrokes = new JButton();
			jButtonDeleteStrokes.setBounds(new Rectangle(168, 165, 130, 30));
			jButtonDeleteStrokes.setText("Delete Selected");
			jButtonDeleteStrokes.setEnabled(false);
			jButtonDeleteStrokes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  Delete the seelected strokes...   ");
				}
			});
		}
		return jButtonDeleteStrokes;
	}

	/**
	 * This method initializes jButtonLoadIntialLabels
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoadIntialLabels() {
		if (jButtonLoadIntialLabels == null) {
			jButtonLoadIntialLabels = new JButton();
			jButtonLoadIntialLabels.setBounds(new Rectangle(6, 161, 136, 35));
			jButtonLoadIntialLabels.setText("Label (intial)");
			jButtonLoadIntialLabels.setEnabled(false);
			jButtonLoadIntialLabels.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  Label the page using standared operation..."); // TODO Auto-generated Event stub actionPerformed()

			       data.getIntialLabels();
			   	getJButtonShowPage().setEnabled(true);
				}
			});
		}
		return jButtonLoadIntialLabels;
	}

	/**
	 * This method initializes jButtonClose
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonClose() {
		if (jButtonClose == null) {
			jButtonClose = new JButton();
			jButtonClose.setBounds(new Rectangle(549, 171, 129, 25));
			jButtonClose.setText("Close");
			jButtonClose.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Closee..... the frame. ... "); // TODO Auto-generated Event stub actionPerformed()

				}
			});
		}
		return jButtonClose;
	}

	/**
	 * This method initializes jButtonShowPage
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonShowPage() {
		if (jButtonShowPage == null) {
			jButtonShowPage = new JButton();
			jButtonShowPage.setBounds(new Rectangle(8, 112, 136, 37));
			jButtonShowPage.setText("Show Page");
			jButtonShowPage.setEnabled(false);
			jButtonShowPage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Display the other frame.. (show page)... "); // TODO Auto-generated Event stub actionPerformed()


					DisplayPageFrame();

				}
			});
		}
		return jButtonShowPage;
	}
   private StrokeDisplayFrame  getPageDisplayFrame(){

	   //if (PageDisplayframe==null){

		   PageDisplayframe =new StrokeDisplayFrame(this,data);
		   PageDisplayframe.pack();
		   PageDisplayframe.setVisible(false);
	   //}

	   return PageDisplayframe;
   }

	public void DisplayPageFrame(){

		getPageDisplayFrame().setVisible(true);

		//PageDisplayframe.setVisible(true);


	}


	/**
	 * This method initializes jButtonSavePage
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSavePage() {
		if (jButtonSavePage == null) {
			jButtonSavePage = new JButton();
			jButtonSavePage.setBounds(new Rectangle(561, 54, 100, 25));
			jButtonSavePage.setText("Save page");jButtonSavePage.setEnabled(false);
			jButtonSavePage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  Save  page ... "); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonSavePage;
	}

	/**
	 * This method initializes jButtonSelectFile
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSelectFile() {
		if (jButtonSelectFile == null) {
			jButtonSelectFile = new JButton();
			jButtonSelectFile.setBounds(new Rectangle(542, 11, 12, 30));
			jButtonSelectFile.setText("...");
			jButtonSelectFile.addActionListener(new java.awt.event.ActionListener() {


				public void actionPerformed(java.awt.event.ActionEvent e) {

					System.out.println(" open ffile....   "); // TODO Auto-generated Event stub actionPerformed()" +

					if (fileOpen==null)
						fc.setCurrentDirectory(new File(SystemSettings.CurrentRunDir));
					else
					fc.setCurrentDirectory( fileOpen);

					int returnVal =fc.showOpenDialog(getRootPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			           fileOpen = fc.getSelectedFile();
			            //This is where a real application would open the file.
			            logger.info("Opening: " +  fileOpen.getName() + "."  );
			            getJTextFieldOpenFile().setText(fileOpen.getAbsolutePath());
			            getJButtonOpenFile().setEnabled(true);

			        } else {
			            logger.info("Open command cancelled by user."  );
			        }

						}
			});
		}
		return jButtonSelectFile;
	}

	/**
	 * This method initializes jTextFieldOpenFile
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldOpenFile() {
		if (jTextFieldOpenFile == null) {
			jTextFieldOpenFile = new JTextField();
			jTextFieldOpenFile.setBounds(new Rectangle(8, 9, 515, 25));
		}
		return jTextFieldOpenFile;
	}

	/**
	 * This method initializes jButtonSelectSave
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSelectSave() {
		if (jButtonSelectSave == null) {
			jButtonSelectSave = new JButton();
			jButtonSelectSave.setBounds(new Rectangle(541, 53, 10, 28));
			jButtonSelectSave.setText("....");
			jButtonSelectSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" Show save dialog ... "); // TODO Auto-generated Event stub actionPerformed()


				//	System.out.println(" open ffile....   "); // TODO Auto-generated Event stub actionPerformed()" +

					if (fileSave==null)
						fc.setCurrentDirectory(new File(SystemSettings.CurrentRunDir));
					else
					fc.setCurrentDirectory( fileSave);

					int returnVal =fc.showSaveDialog(getRootPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			           fileSave = fc.getSelectedFile();
			            //This is where a real application would open the file.
			            logger.info("Opening: " +  fileSave.getName() + "."  );
			            getJTextFieldSaveFolder().setText(fileSave.getAbsolutePath());
			            getJButtonSavePage().setEnabled(true);
			            getJButtonSelectSave().setEnabled( true);

			        } else {
			            logger.info("Open command cancelled by user."  );
			        }





				}
			});
		}
		return jButtonSelectSave;
	}

	/**
	 * This method initializes jTextFieldSaveFolder
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSaveFolder() {
		if (jTextFieldSaveFolder == null) {
			jTextFieldSaveFolder = new JTextField();
			jTextFieldSaveFolder.setBounds(new Rectangle(6, 49, 519, 31));
		}
		return jTextFieldSaveFolder;
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SystemSettings.StartApplication();
				SystemSettings.ReadSetting(null);
				TableFrame thisClass = new TableFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(714, 460);
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

			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			//gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;

			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			//gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			//gridBagConstraints.weightx = 1.0;
		//	gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 1;


			jContentPane = new JPanel();
			jContentPane.setLayout(null);

			jContentPane.add(getJScrollPaneTable(), null);
			jContentPane.add(getJPanelControls(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
