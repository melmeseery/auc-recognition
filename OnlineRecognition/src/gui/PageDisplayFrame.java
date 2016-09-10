/**
 *
 */
package gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import org.apache.log4j.Logger;

import data.GeneraicDataSet;
import data.IntegerLabel;
import data.SimpleInkObject;

import app.SystemSettings;

/**
 * @author TOSHIBA
 *
 */
public class PageDisplayFrame extends JFrame {
	private static final Logger logger = Logger.getLogger(PageDisplayFrame.class);
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenuFile = null;
	private Dimension oldSize;
	private JMenu jMenuOperations = null;
	private JMenuItem jMenuItemOpenFile = null;
	private JMenuItem jMenuItemOpenFolder = null;
	private JMenuItem jMenuItemSaveMatFile = null;
	private JMenuItem jMenuItemStartSelecting = null;
	private JMenuItem jMenuItemDelete = null;
	private JMenuItem jMenuItemSaveSeperateFiles = null;
	private JMenuItem jMenuItemSaveMetaData = null;
	private JLabel jLabelText = null;
	private JTable jTable = null;
	private JComboBox jComboBoxPages = null;
	private JButton jButtonOpen = null;
	private JButton jButtonAddToDataSet = null;
	private JCheckBox jCheckBoxAccepted = null;
	private JButton jButtonSave = null;

	final JFileChooser fc = new JFileChooser();
	private File fileOpen=null;
	private File fileSave=null;
	private File  DirOpen=null;
	private int SelectDirFile;

   private ArrayList<GuiPageModel>  dataPages=new ArrayList<GuiPageModel>() ;
private JButton jButtonNext = null;

GeneraicDataSet<SimpleInkObject, IntegerLabel> dataset=null;

	/**
	 * This method initializes jJMenuBar
	 *
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuFile());
			jJMenuBar.add(getJMenuOperations());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenuFile
	 *
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenuFile() {
		if (jMenuFile == null) {
			jMenuFile = new JMenu();
			jMenuFile.setText("File");
			jMenuFile.add(getJMenuItemSaveMetaData());
			jMenuFile.add(getJMenuItemSaveSeperateFiles());
			jMenuFile.add(getJMenuItemOpenFile());
			jMenuFile.add(getJMenuItemOpenFolder());
			jMenuFile.add(getJMenuItemSaveMatFile());
		}
		return jMenuFile;
	}

	/**
	 * This method initializes jMenuOperations
	 *
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenuOperations() {
		if (jMenuOperations == null) {
			jMenuOperations = new JMenu();
			jMenuOperations.setText("Function");
			jMenuOperations.add(getJMenuItemStartSelecting());
			jMenuOperations.add(getJMenuItemDelete());
		}
		return jMenuOperations;
	}

	/**
	 * This method initializes jMenuItemOpenFile
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemOpenFile() {
		if (jMenuItemOpenFile == null) {
			jMenuItemOpenFile = new JMenuItem();
			jMenuItemOpenFile.setText("Open Files");
			jMenuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {


				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()


					System.out.println(" open ffile....   "); // TODO Auto-generated Event stub actionPerformed()" +
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY) ;
					if (fileOpen==null)
						fc.setCurrentDirectory(new File(SystemSettings.CurrentRunDir));
					else
					fc.setCurrentDirectory( fileOpen);

					int returnVal =fc.showOpenDialog(getRootPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			           fileOpen = fc.getSelectedFile();
			            //This is where a real application would open the file.
			            logger.info("Opening: " +  fileOpen.getName() + "."  );
			            //ad
			            jLabelText.setText(fileOpen.getAbsolutePath());
			            //  add the file open to the column
			           // getJButtonOpenFile().setEnabled(true);
			            SelectDirFile=2;


			        } else {
			            logger.info("Open command cancelled by user."  );
			        }

						}



			});
		}
		return jMenuItemOpenFile;
	}

	private void UpdateComboBox(){
		if (SelectDirFile==2){

			// the new file is in fileopn



		}
		if (SelectDirFile==1){

			// new file in fileDir
		}






	}


	/**
	 * This method initializes jMenuItemOpenFolder
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemOpenFolder() {
		if (jMenuItemOpenFolder == null) {
			jMenuItemOpenFolder = new JMenuItem();
			jMenuItemOpenFolder.setText("Open folder");
		}
		return jMenuItemOpenFolder;
	}

	/**
	 * This method initializes jMenuItemSaveMatFile
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemSaveMatFile() {
		if (jMenuItemSaveMatFile == null) {
			jMenuItemSaveMatFile = new JMenuItem();
			jMenuItemSaveMatFile.setText("Save Mat files");
		}
		return jMenuItemSaveMatFile;
	}

	/**
	 * This method initializes jMenuItemStartSelecting
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemStartSelecting() {
		if (jMenuItemStartSelecting == null) {
			jMenuItemStartSelecting = new JMenuItem();
			jMenuItemStartSelecting.setText("Start Select");
		}
		return jMenuItemStartSelecting;
	}

	/**
	 * This method initializes jMenuItemDelete
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemDelete() {
		if (jMenuItemDelete == null) {
			jMenuItemDelete = new JMenuItem();
			jMenuItemDelete.setText("Delete Strokes");
		}
		return jMenuItemDelete;
	}

	/**
	 * This method initializes jMenuItemSaveSeperateFiles
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemSaveSeperateFiles() {
		if (jMenuItemSaveSeperateFiles == null) {
			jMenuItemSaveSeperateFiles = new JMenuItem();
			jMenuItemSaveSeperateFiles.setText("Save Seprate Files");
		}
		return jMenuItemSaveSeperateFiles;
	}

	/**
	 * This method initializes jMenuItemSaveMetaData
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemSaveMetaData() {
		if (jMenuItemSaveMetaData == null) {
			jMenuItemSaveMetaData = new JMenuItem();
			jMenuItemSaveMetaData.setText("Save Meta Files");
		}
		return jMenuItemSaveMetaData;
	}

	/**
	 * This method initializes jTable
	 *
	 * @return javax.swing.JTable
	 */


	/**
	 * This method initializes jComboBoxPages
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxPages() {
		if (jComboBoxPages == null) {
			jComboBoxPages = new JComboBox();
			jComboBoxPages.setBounds(new Rectangle(39, 56, 170, 33));
			jComboBoxPages.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  change the selected dsata..."); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jComboBoxPages;
	}

	/**
	 * This method initializes jButtonOpen
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOpen() {
		if (jButtonOpen == null) {
			jButtonOpen = new JButton();
			jButtonOpen.setBounds(new Rectangle(281, 53, 103, 33));
			jButtonOpen.setText("Open");
			jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" dispalay details "); // TODO Auto-generated Event stub actionPerformed()

					DisplayPageDetails();
				}
			});
		}
		return jButtonOpen;
	}


	private void DisplayPageDetails(){
		// get the seleted index from combo box...

	     GuiPageModel temp = getSelectedPage();
         if (temp!=null){


		TableFrame frame=new TableFrame(temp);

          frame.pack();
          frame.setVisible(true);

		}




	}

	/**
	 * This method initializes jButtonAddToDataSet
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddToDataSet() {
		if (jButtonAddToDataSet == null) {
			jButtonAddToDataSet = new JButton();
			jButtonAddToDataSet.setBounds(new Rectangle(172, 122, 148, 33));
			jButtonAddToDataSet.setText("Add to DataSet");
			jButtonAddToDataSet.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  adding to the data set.. "); // TODO Auto-generated Event stub actionPerformed()
					addToDataSet();


				}
			});
		}
		return jButtonAddToDataSet;
	}
	private GuiPageModel getSelectedPage(){

		Object item = getJComboBoxPages().getSelectedItem();

		int  i = getJComboBoxPages().getSelectedIndex();

		if (i>0)
		{
			GuiPageModel temp=dataPages.get(i);

		return temp;


		}
		else{

			return null;
		}
	}

    private void addToDataSet(){



               GuiPageModel temp = getSelectedPage();
               if (temp!=null){

            	  StrokeGroupTableModel g = temp.getStrokeGroupData();

                    if (dataset==null){
                    	dataset=new GeneraicDataSet<SimpleInkObject, IntegerLabel>(new SimpleInkObject(), new IntegerLabel());
                    }

                    for (int j = 0; j < g.getRowCount(); j++) {
						StrokeGroup group = g.getRow(j);

						ArrayList<SimpleInkObject> strokes = group.getStrokes();
						IntegerLabel labels = group.getIntegerLabel();
						  dataset.addSamples(strokes,  labels);

					}




               temp.setAdded(true);

               }




    }
	/**
	 * This method initializes jCheckBoxAccepted
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxAccepted() {
		if (jCheckBoxAccepted == null) {
			jCheckBoxAccepted = new JCheckBox();
			jCheckBoxAccepted.setBounds(new Rectangle(40, 124, 102, 23));
			jCheckBoxAccepted.setText("Accepted");
			jCheckBoxAccepted.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(" accepted or deaccepte. . "); // TODO Auto-generated Event stub actionPerformed()

					AcceptSelected();
				}

			});
		}
		return jCheckBoxAccepted;
	}

				private void AcceptSelected() {
				     GuiPageModel temp = getSelectedPage();
		               if (temp!=null){
		                 temp.setAccepted(getJCheckBoxAccepted().isSelected());
		               }


				}
	/**
	 * This method initializes jButtonSave
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			jButtonSave.setBounds(new Rectangle(43, 222, 173, 33));
			jButtonSave.setText("Save Data Set");
			jButtonSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					SaveDataSet();
				}
			});
		}
		return jButtonSave;
	}

	public void SaveDataSet(){

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);


		if (fileSave==null)
			fc.setCurrentDirectory(new File(SystemSettings.CurrentRunDir));
		else
		fc.setCurrentDirectory( fileSave);

		int returnVal =fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
           fileSave = fc.getSelectedFile();
            //This is where a real application would open the file.
            logger.info("Opening: " +  fileSave.getName() + "."  );

            String name=fileSave.getName();


              dataset.setFormat(name.substring( name.lastIndexOf(".")-1));
              dataset.SaveToFile(fileSave.getAbsolutePath());



        } else {
            logger.info("Open command cancelled by user."  );
        }

		//



	}

	/**
	 * This method initializes jButtonNext
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNext() {
		if (jButtonNext == null) {
			jButtonNext = new JButton();
			jButtonNext.setBounds(new Rectangle(222, 54, 45, 34));
			jButtonNext.setText(">");
			jButtonNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("  Next "); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonNext;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PageDisplayFrame thisClass = new PageDisplayFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public PageDisplayFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(630, 326);
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("DegiMemo Reader");
		oldSize=this.getSize();
		this.setContentPane(getJContentPane());
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				Dimension newSize = getSize();
				int w=(int) (newSize.getWidth()-oldSize.getWidth());
				int h=(int) (newSize.getHeight()-oldSize.getHeight());
				//Dimension diff = newSize-oldSize;
				System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()
		//		drawingSheet.setBounds(new Rectangle(drawingSheet.getBounds().x, drawingSheet.getBounds().y, (int)drawingSheet.getSize().getWidth()+w,(int) drawingSheet.getSize().getHeight()+h));
				oldSize=newSize;
			}
		});
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelText = new JLabel();
			jLabelText.setBounds(new Rectangle(40, 10, 542, 25));
			jLabelText.setText("Check All Data");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelText, null);
			jContentPane.add(getJComboBoxPages(), null);
			jContentPane.add(getJButtonOpen(), null);
			jContentPane.add(getJButtonAddToDataSet(), null);
			jContentPane.add(getJCheckBoxAccepted(), null);
			jContentPane.add(getJButtonSave(), null);
			jContentPane.add(getJButtonNext(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
