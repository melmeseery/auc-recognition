package gui;

 
import javax.swing.JPanel;
import javax.swing.JFrame;
 
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;

 
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import java.awt.Rectangle;
import javax.swing.ListSelectionModel;
import java.awt.Point;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import util.lib;

import classifiers.MultiFeature.ClassifierData;
import data.image.Digit;
import java.awt.Insets;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class ClassifierDataFrame extends JFrame {
	private static transient final Logger logger = Logger
	.getLogger(ClassifierDataFrame.class);  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButtonOk = null;
	private JCheckBox jCheckBoxUseRegions = null;
	private JFormattedTextField  jTextFieldHierarchyKey = null;
    private NumberFormat amountFormat;  //  @jve:decl-index=0:
	private JList jListDigitsClass1 = null;
	private JList jListDigitsClass2 = null;
	private JLabel jLabelClass2 = null;
	private JLabel jLabelClass1 = null;
	private JButton jButtonAnother = null;
	private DefaultListModel listModelClass1;  
	private DefaultListModel  listModelClass2;
	private DefaultListModel  listModelFeatures;
	private JScrollPane jScrollPaneTable = null;
	private JTable jTableClassifier = null;
	private JScrollPane jScrollPaneClass1 = null;
	private JScrollPane jScrollPaneClass2 = null;
	private JScrollPane jScrollPaneFeatures = null;
	private JList jListFeatures = null;
	private JButton jButtonEdit = null;
	private JButton jButtonSave = null;
	private JButton jButtonGenerateFile = null;
	private JLabel jLabelHierarchalKey = null;
	private JLabel jLabelFeatures = null;
	private JComboBox jComboBoxType = null;
	private JLabel jLabelType = null;
	private DefaultComboBoxModel typeString=null;
	private ClassifierTableModel ClassifiersData;
	private JButton jButtonLoad = null;
	ClassifierData CurrentData=new ClassifierData();  //  @jve:decl-index=0:
	private JLabel jLabelD1 = null;
	private JLabel jLabel = null;
	private JLabel jLabelD2 = null;
	final JFileChooser fc = new JFileChooser(new File("."));
	protected int row_Edit;
	protected boolean EditMode=false;
	private JPanel jPanel = null;
	private JLabel jLabel1 = null;
	private JPanel jPanel1 = null;
	private JButton jButtonRemove = null;
	private JScrollPane jScrollPaneText = null;
	private JTextArea jTextAreaFeatures = null;
	private JLabel jLabelSummary = null;
	/**
	 * This is the default constructor
	 */
	public ClassifierDataFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(849, 549);
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
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.insets = new Insets(5, 3, 4, 9);
			gridBagConstraints25.gridy = 2;
			gridBagConstraints25.ipadx = 0;
			gridBagConstraints25.ipady = 32;
			gridBagConstraints25.fill = GridBagConstraints.BOTH;
			gridBagConstraints25.gridheight = 1;
			gridBagConstraints25.weighty = 1.0;
			gridBagConstraints25.gridx = 1;
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.insets = new Insets(7, 8, 7, 2);
			gridBagConstraints24.gridy = 2;
			gridBagConstraints24.ipadx = 0;
			gridBagConstraints24.ipady = 73;
			gridBagConstraints24.fill = GridBagConstraints.BOTH;
			gridBagConstraints24.gridx = 0;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.insets = new Insets(7, 8, 4, 383);
			gridBagConstraints23.gridy = 1;
			gridBagConstraints23.ipadx = 17;
			gridBagConstraints23.ipady = 0;
			gridBagConstraints23.gridx = 0;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.fill = GridBagConstraints.BOTH;
			gridBagConstraints22.gridwidth = 2;
			gridBagConstraints22.gridx = 0;
			gridBagConstraints22.gridy = 0;
			gridBagConstraints22.ipadx = 368;
			gridBagConstraints22.ipady = 1;
			gridBagConstraints22.weightx = 1.0;
			gridBagConstraints22.weighty = 20.0;
			gridBagConstraints22.insets = new Insets(7, 3, 6, 5);
			jLabel1 = new JLabel();
			jLabel1.setText("            ");
			jLabelD2 = new JLabel();
			jLabelD2.setText("              ");
			jLabel = new JLabel();
			jLabel.setText("  Verus");
			jLabelD1 = new JLabel();
			jLabelD1.setText("               ");
			jLabelType = new JLabel();
			jLabelType.setText("Type");
			
			
			jLabelFeatures = new JLabel();
			jLabelFeatures.setText("Features");
			jLabelHierarchalKey = new JLabel();
			jLabelHierarchalKey.setText("Hierarcal Key");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.gridx = 4;
			jLabelClass1 = new JLabel();
			jLabelClass1.setText("Class 1");
			jLabelClass2 = new JLabel();
			jLabelClass2.setText("Class 2");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPaneTable(), gridBagConstraints22);
			jContentPane.add(getJButtonLoad(), gridBagConstraints23);
			jContentPane.add(getJPanel(), gridBagConstraints24);
			jContentPane.add(getJPanel1(), gridBagConstraints25);
			
			jContentPane.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentResized(java.awt.event.ComponentEvent e) {
				//	logger.info("componentResized()"); /
					
					
					 getJScrollPaneTable().setSize(getJContentPane().getSize().width-20, getJScrollPaneTable().getHeight());
					
				}
			});
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setText("OK-Close");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()");          
					System.exit(EXIT_ON_CLOSE);
				}
			});
			 
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jCheckBoxUseRegions	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxUseRegions() {
		if (jCheckBoxUseRegions == null) {
			jCheckBoxUseRegions = new JCheckBox();
			jCheckBoxUseRegions.setEnabled(false);
			jCheckBoxUseRegions.setText("Use Regions");
		}
		return jCheckBoxUseRegions;
	}

	/**
	 * This method initializes jTextFieldHierarchyKey	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField  getJTextFieldHierarchyKey() {
		if (jTextFieldHierarchyKey == null) {
			 
			jTextFieldHierarchyKey = new JFormattedTextField ();
			jTextFieldHierarchyKey.setValue(new Integer(0));
		}
		return jTextFieldHierarchyKey;
	}

	/**
	 * This method initializes jListDigitsClass1	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJListDigitsClass1() {
		if (jListDigitsClass1 == null) {
			//int[] data = {0,1,2,3,4,5,6,7,8,9};
		 
			 listModelClass1 = new DefaultListModel();
			for (int i = 0; i < 10; i++) {
				listModelClass1.addElement( i +"");	
			}
		 
			jListDigitsClass1 = new JList(listModelClass1);
			jListDigitsClass1.setSelectedIndex(-1);
			jListDigitsClass1.setEnabled(false);
			jListDigitsClass1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			jListDigitsClass1.setName("Digit Class1");
			jListDigitsClass1
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							logger.info("valueChanged()  N1 ");         
							
							   if (e.getValueIsAdjusting()) {
					                return;
					            }
							   
							    ListSelectionModel lsm =getJListDigitsClass1().getSelectionModel();

					            int firstIndex;
					            int lastIndex;
					             
					            if (lsm.isSelectionEmpty()){
					            	return ;
					            }
					            int index1=lsm.getLeadSelectionIndex();
					          	String currentSelection2=  (String) getJListDigitsClass1().getModel().getElementAt(index1);
					        Integer value2=Integer.parseInt(currentSelection2);
					        if (lsm.isSelectedIndex(index1)){
					        	 if (lib.isInArray(value2,CurrentData.digitC2)){
					        		
							            lsm.removeSelectionInterval(index1,index1);
									   JOptionPane.showMessageDialog(null," Could not add digit"+value2+" as it is already selected for other class","Table Example",JOptionPane.INFORMATION_MESSAGE);
									   return;
					        		 }
					
									}//array 
					            
					            
					            firstIndex =lsm.getMinSelectionIndex();
					            lastIndex=lsm.getMaxSelectionIndex();
					        	CurrentData.digit=new ArrayList<Integer>();
					        for(int index=firstIndex;index<=lastIndex;index++){	
					        
					          	String currentSelection=  (String) getJListDigitsClass1().getModel().getElementAt(index);
						        Integer value=Integer.parseInt(currentSelection);
						        
					         
					        		     if (lsm.isSelectedIndex(index)){
					        		
					                        		CurrentData.digit.add(value);
					                           		logger.info(" adding "+value);
					                        		
					                        }//if is selected 
					        	
							 
					        	 
					        	}//else index is not in d2
					         
					     
					        	jLabelD1.setText(CurrentData.digit.toString());
					          	CurrentData.createClassifierName();
					        	CurrentData.createFeatureNameList();
					        	getJTextAreaFeatures().setText(CurrentData.toStringDetailed());
							
						}
					});
		}
		return jListDigitsClass1;
	}

	/**
	 * This method initializes jListDigitsClass2	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJListDigitsClass2() {
		if (jListDigitsClass2 == null) {
			listModelClass2 = new DefaultListModel();
			for (int i = 0; i < 10; i++) {
				listModelClass2.addElement( i+"" );	
			}
			jListDigitsClass2 = new JList(listModelClass2);
 
			jListDigitsClass2.setName("Digit for Class2");
			jListDigitsClass2.setSelectedIndex(-1);
		
			jListDigitsClass2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			jListDigitsClass2.setVisibleRowCount(8);
			jListDigitsClass2.setEnabled(false);
			jListDigitsClass2.setVisible(true);
			jListDigitsClass2
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							
							
							logger.info("valueChanged()  N1 ");         
							
							   if (e.getValueIsAdjusting()) {
					                return;
					            }
							   
							    ListSelectionModel lsm =getJListDigitsClass2().getSelectionModel();

					            int firstIndex;
					            int lastIndex;
					             
					            if (lsm.isSelectionEmpty()){
					            	return ;
					            }
					            int index1=lsm.getLeadSelectionIndex();
					          	String currentSelection2=  (String) getJListDigitsClass2().getModel().getElementAt(index1);
					        Integer value2=Integer.parseInt(currentSelection2);
					        if (lsm.isSelectedIndex(index1)){
					        	 if (lib.isInArray(value2,CurrentData.digit)){
					        		
							            lsm.removeSelectionInterval(index1,index1);
									   JOptionPane.showMessageDialog(null," Could not add digit"+value2+" as it is already selected for other class","Table Example",JOptionPane.INFORMATION_MESSAGE);
									   return;
					        		 }
					
									}//array 
					            
					            
					            firstIndex =lsm.getMinSelectionIndex();
					            lastIndex=lsm.getMaxSelectionIndex();
					        	CurrentData.digitC2=new ArrayList<Integer>();
					        for(int index=firstIndex;index<=lastIndex;index++){	
					        logger.info("  index of "+index);
					          	String currentSelection=  (String) getJListDigitsClass2().getModel().getElementAt(index);
						        Integer value=Integer.parseInt(currentSelection);
						        
					         
					        		     if (lsm.isSelectedIndex(index)){
					        		
					                        		CurrentData.digitC2.add(value);
					                        		logger.info(" adding "+value);
					                        		
					                        }//if is selected 
					        	
							 
					        	 
					        	}//else index is not in d2
					         
					     
					        	jLabelD2.setText(CurrentData.digitC2.toString());
					        	CurrentData.createClassifierName();
					        	CurrentData.createFeatureNameList();
					        	getJTextAreaFeatures().setText(CurrentData.toStringDetailed());
					        	getJTextAreaFeatures().updateUI();
						}
					});
		}
		return jListDigitsClass2;
	}
	/**
	 * This method initializes jButtonLoad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoad() {
		if (jButtonLoad == null) {
			jButtonLoad = new JButton();
			jButtonLoad.setText("Load");
			jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("load ..........."); 
					
					 File f=new File(AppDefaults.classifiersFilename);
					 if (f.exists()){
						 fc.setCurrentDirectory(f);
						 
					 }
					 
					// In response to a button click:
					if (fc.showOpenDialog(getJContentPane().getParent()) == fc.APPROVE_OPTION){
					
						ArrayList<ClassifierData> temp = ClassifierData.ReadClassifiersDetails(fc.getSelectedFile().getAbsolutePath().replace(".txt", ""));
				        ClassifiersData.setData(temp);
						
					}  
						
						
				}
			});
		}
		return jButtonLoad;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 4;
			gridBagConstraints15.gridy = 2;
			gridBagConstraints15.ipadx=20;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			
			
			
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints14.gridx = 5;
			gridBagConstraints14.gridy = 2;
			gridBagConstraints14.ipady=20;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 3;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.ipadx=120;
			gridBagConstraints11.gridx = 5;
			gridBagConstraints11.gridheight=4;
			jPanel.add(getJScrollPaneFeatures(), gridBagConstraints11);
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridx = 3;
			gridBagConstraints10.gridy = 5;
			gridBagConstraints10.gridwidth = 2;
			gridBagConstraints10.ipadx=20;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2; 
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 3;
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 2;
			
			
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 3;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.gridx = 3;
			
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 0;
			
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 5;
			gridBagConstraints2.ipadx=20;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagConstraints.insets = new Insets(2, 0, 0, 0);
			gridBagConstraints.weightx=2;
		
			jPanel.add(jLabelD2, gridBagConstraints10);
			jPanel.add(jLabelClass1, gridBagConstraints7);
			jPanel.add(jLabelClass2, gridBagConstraints6);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(jLabelD1, gridBagConstraints2);
			jPanel.add(getJScrollPaneClass1(), gridBagConstraints3);
			jPanel.add(getJScrollPaneClass2(), gridBagConstraints5);
			jPanel.add(jLabelFeatures, gridBagConstraints14);
			jPanel.add(jLabel1, gridBagConstraints15);
		
		
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 2;
			gridBagConstraints30.gridy = 5;
	 
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 4;
			gridBagConstraints28.gridy = 4;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 1;
			gridBagConstraints27.fill = GridBagConstraints.BOTH;
			gridBagConstraints27.gridy = 6;
			jLabelSummary = new JLabel();
			jLabelSummary.setText("Summary");
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.fill = GridBagConstraints.BOTH;
			gridBagConstraints26.gridy = 7;
			gridBagConstraints26.weightx = 1.0;
			gridBagConstraints26.weighty = 43.0;
			gridBagConstraints26.gridwidth = 4;
			gridBagConstraints26.gridheight = 2;
			gridBagConstraints26.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints26.gridx = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 3;
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.ipadx = 12;
			gridBagConstraints21.ipady = 3;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.weighty = 1.0;
			gridBagConstraints21.insets = new Insets(0, 3, 1, 4);
			gridBagConstraints21.gridy = 1;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.weighty = 1.0; 
			gridBagConstraints20.gridx = 4;
			gridBagConstraints20.fill = GridBagConstraints.BOTH;
			gridBagConstraints20.insets = new Insets(0, 0, 0, 9);
			gridBagConstraints20.gridwidth = 1;
			gridBagConstraints20.gridy = 6;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.weighty = 1.0; 
			gridBagConstraints19.gridx = 4;
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.insets = new Insets(11, 0, 11, 9);
			gridBagConstraints19.gridy = 5;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints18.gridy = 4;
			gridBagConstraints18.weightx = 1.0;
			gridBagConstraints18.gridheight = 1;
			gridBagConstraints18.insets = new Insets(18, 5, 10, 0);
			gridBagConstraints18.weighty = 1.0;
			gridBagConstraints18.ipadx = 2;
			gridBagConstraints18.gridwidth = 2;
			gridBagConstraints18.gridx = 2;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 1;
			gridBagConstraints17.insets = new Insets(5, 4, 0, 4);
			gridBagConstraints17.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.gridy = 4;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints16.gridy = 3;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 4;
			gridBagConstraints13.gridy = 3;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 3;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridy = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints8.ipadx = 29;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 4;
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 2, 0, 2);
			gridBagConstraints1.gridy = 1;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJButtonAnother(), gridBagConstraints1);
			jPanel1.add(getJButtonEdit(), gridBagConstraints8);
			jPanel1.add(getJButtonSave(), gridBagConstraints9);
			jPanel1.add(getJComboBoxType(), gridBagConstraints12);
			jPanel1.add(getJCheckBoxUseRegions(), gridBagConstraints13);
			jPanel1.add(jLabelType, gridBagConstraints16);
			jPanel1.add(jLabelHierarchalKey, gridBagConstraints17);
			jPanel1.add(getJTextFieldHierarchyKey(), gridBagConstraints18);
			jPanel1.add(getJButtonGenerateFile(), gridBagConstraints19);
			jPanel1.add(getJButtonOk(), gridBagConstraints20);
			jPanel1.add(getJButtonRemove(), gridBagConstraints21);
			jPanel1.add(getJScrollPaneText(), gridBagConstraints26);
			jPanel1.add(jLabelSummary, gridBagConstraints27);
			jPanel1.add(getJCheckBox(), gridBagConstraints28);
			jPanel1.add(getJCheckBoxSplit(), gridBagConstraints30);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButtonRemove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonRemove() {
		if (jButtonRemove == null) {
			jButtonRemove = new JButton();
			jButtonRemove.setText("Remove");
			jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()");          
					
					int row=getJTableClassifier().getSelectedRow();
					if (row>-1)
					     ClassifiersData.removeRow(row);
					getJTableClassifier().updateUI();
					
				}
			});
		}
		return jButtonRemove;
	}

	/**
	 * This method initializes jScrollPaneText	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneText() {
		if (jScrollPaneText == null) {
			jScrollPaneText = new JScrollPane();
			jScrollPaneText.setViewportView(getJTextAreaFeatures());
		}
		return jScrollPaneText;
	}

	/**
	 * This method initializes jTextAreaFeatures	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaFeatures() {
		if (jTextAreaFeatures == null) {
			jTextAreaFeatures = new JTextArea();
			jTextAreaFeatures.setLineWrap(true);
		}
		return jTextAreaFeatures;
	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
//	private JCheckBox getJCheckBoxSplit() {
//		if (jCheckBoxSplit == null) {
//			jCheckBoxSplit = new JCheckBox();
//			jCheckBoxSplit.setText("SplitData");
//			
//		}
//		return jCheckBox;
//	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setText("Probability");
			
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jCheckBoxSplit	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxSplit() {
		if (jCheckBoxSplit == null) {
			jCheckBoxSplit = new JCheckBox();
			jCheckBoxSplit.setText("Split");
		}
		return jCheckBoxSplit;
	}

	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ClassifierDataFrame thisClass = new ClassifierDataFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	/**
	 * This method initializes jButtonAnother	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAnother() {
		if (jButtonAnother == null) {
			jButtonAnother = new JButton();
			jButtonAnother.setText("Add new ");
			jButtonAnother.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()");          
					CurrentData=new ClassifierData();
					
					getJListDigitsClass1().setSelectedIndex(-1);
					getJListDigitsClass2().setSelectedIndex(-1);
					getJListFeatures().setSelectedIndex(-1);
					getJListDigitsClass2().setEnabled( true); 
					 
					getJListDigitsClass1().setEnabled( true);
					getJListFeatures().setEnabled( true);
				  	CurrentData.createClassifierName();
		        	CurrentData.createFeatureNameList();
		        	 	getJCheckBox().setSelected(CurrentData.useProbability);
		        	getJCheckBoxSplit().setSelected(CurrentData.useSplit);
//		        	getJCheckBox().setSelected(true);
//		        	getJCheckBoxSplit().setSelected(false);
					getJTextAreaFeatures().setText(CurrentData.toStringDetailed());
				}
			});
		}
		return jButtonAnother;
	}

	/**
	 * This method initializes jScrollPaneTable	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTable() {
		if (jScrollPaneTable == null) {
			jScrollPaneTable = new JScrollPane();
			jScrollPaneTable.setViewportView(getJTableClassifier());
		}
		return jScrollPaneTable;
	}

	/**
	 * This method initializes jTableClassifier	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableClassifier() {
		if (jTableClassifier == null) {
			ClassifiersData=	new ClassifierTableModel();
			
			jTableClassifier = new JTable(ClassifiersData);
			jTableClassifier.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				
				
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					//logger.info("valueChanged()");         
					
				int row=
					jTableClassifier.getSelectedRow();
				 if (row>-1){
					 ClassifierData tempC = ClassifiersData.getRow(row);
					 //tempC.createClassifierName();
					 getJTextAreaFeatures().setText( tempC.toStringDetailed());
				 }
				
				
				
				
				}
			});
			jTableClassifier.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableClassifier.setShowGrid(true);
		 
		}
		return jTableClassifier;
	}

	/**
	 * This method initializes jScrollPaneClass1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneClass1() {
		if (jScrollPaneClass1 == null) {
			jScrollPaneClass1 = new JScrollPane();
			jScrollPaneClass1.setViewportView(getJListDigitsClass1());
		}
		return jScrollPaneClass1;
	}

	/**
	 * This method initializes jScrollPaneClass2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneClass2() {
		if (jScrollPaneClass2 == null) {
			jScrollPaneClass2 = new JScrollPane();
			jScrollPaneClass2.setViewportView(getJListDigitsClass2());
		}
		return jScrollPaneClass2;
	}

	/**
	 * This method initializes jScrollPaneFeatures	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneFeatures() {
		if (jScrollPaneFeatures == null) {
			jScrollPaneFeatures = new JScrollPane();
			jScrollPaneFeatures.setViewportView(getJListFeatures());
			
		}
		return jScrollPaneFeatures;
	}
	Digit smallImage;
	private JCheckBox jCheckBox = null;
		//private JCheckBox jCheckBoxSplit=null;
		private JCheckBox jCheckBoxSplit = null;
	/**
	 * This method initializes jListFeatures	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJListFeatures() {
		if (jListFeatures == null) {
			listModelFeatures =new DefaultListModel();
			
			Digit.loadAllFeatureArray();
			Digit.setFeaturesToCompute(null);
			ArrayList<String> feats = Digit.getComputedFeatures();
//		    smallImage=new Digit();
//		    
//			smallImage.computeAllFeatures();
			
			for (int i = 0; i < feats.size(); i++) {
				
				listModelFeatures.addElement(feats.get(i));
				
			}
			
			jListFeatures = new JList(listModelFeatures){
			
			 public String getToolTipText(MouseEvent e) {
			        int index = locationToIndex(e.getPoint());
			        if (-1 < index) {
	 
						ArrayList<String> feats = Digit.getComputedFeatures();
						if (index<feats.size())
						{
						//	String item=smallImage.getFeatures().get(index).getName();
			               String item = (String)feats.get(index);
			          return item;
						}
			          
			          else return null;
			        } else {
			          return null;
			        }
			      }
			    };
			jListFeatures.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			jListFeatures.setEnabled(false);
			jListFeatures
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							//logger.info("valueChanged()");         
							
							   if (e.getValueIsAdjusting()) {
					                return;
					            }
							CurrentData.feat=new ArrayList<ArrayList<String>>();
							ArrayList<String> feats=new ArrayList<String>();
							//get featu 
							 ListSelectionModel selm = getJListFeatures().getSelectionModel();
							for (int i = selm.getMinSelectionIndex(); i <selm.getMaxSelectionIndex()+1; i++) {
								if (selm.isSelectedIndex(i)){
									
									feats.add(  (String) getJListFeatures().getModel().getElementAt(i) );
						       		logger.info(" adding "+ (String) getJListFeatures().getModel().getElementAt(i));
									
								}
							}
							CurrentData.feat.add(feats);
						  	CurrentData.createClassifierName();
				        	CurrentData.createFeatureNameList();
							getJTextAreaFeatures().setText(CurrentData.toStringDetailed());
						}
					});
		}
		return jListFeatures;
	}

	/**
	 * This method initializes jButtonEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonEdit() {
		if (jButtonEdit == null) {
			jButtonEdit = new JButton();
			jButtonEdit.setText("Edit ");
			jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()");          
					EditMode=true;
					if (getJTableClassifier().getSelectedRow()==-1){
						CurrentData=new ClassifierData();
					}
					else 
						{
						getJListDigitsClass1().setSelectedIndex(-1);
						getJListDigitsClass2().setSelectedIndex(-1);
						getJListFeatures().setSelectedIndex(-1);
						row_Edit=getJTableClassifier().getSelectedRow();
						   CurrentData=ClassifiersData.getRow( getJTableClassifier().getSelectedRow());
						   getJTextFieldHierarchyKey().setValue( CurrentData.HierarchyKey);
						   //gest selectied form 
						   int [] indeces=new int[CurrentData.digit.size()];
						   for (int i = 0; i < CurrentData.digit.size(); i++) {
							indeces[i]=CurrentData.digit.get(i);
							   
							   
						}
						   
						   getJListDigitsClass1().setSelectedIndices( indeces );
						   indeces=new int[CurrentData.digitC2.size()];
						   for (int i = 0; i < CurrentData.digitC2.size(); i++) {
							indeces[i]=CurrentData.digitC2.get(i);
							   
							   
						}
						   getJListDigitsClass2().setSelectedIndices( indeces );
						   
						   if (!CurrentData.useRegions)
						   {
							   ArrayList<String>  feats= CurrentData.feat.get(0);
							   int[] indecesFeat =new int[feats.size()];
							   for (int l = 0; l < feats.size(); l++) {
								   indecesFeat[l]=-1;
								   for (int j = 0; j <  getJListFeatures().getModel().getSize(); j++) {
									   if (
								         getJListFeatures().getModel().getElementAt(j).equals(feats.get(l)))
									   {
										   indecesFeat[l]=j;
										   break;
									   }
									   
							        }
							}
							   
							   getJListFeatures().setSelectedIndices(indecesFeat);
							   
						    
						   }
						   
						   
						   
						   
						   
						   
						}
				  	CurrentData.createClassifierName();
		        	CurrentData.createFeatureNameList();
		         	getJCheckBox().setSelected(CurrentData.useProbability);
		        	getJCheckBoxSplit().setSelected(CurrentData.useSplit);
					getJTextAreaFeatures().setText(CurrentData.toStringDetailed());
					getJListDigitsClass2().setEnabled( true); 
					 
					getJListDigitsClass1().setEnabled( true);
					getJListFeatures().setEnabled( true);
				}
			});
		}
		return jButtonEdit;
	}

	/**
	 * This method initializes jButtonSave	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			jButtonSave.setText("Save");
			jButtonSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("Saving to the table...");          
					
					
					//get Current data 
					CurrentData.useRegions=false;
					CurrentData.HierarchyKey=Integer.parseInt( getJTextFieldHierarchyKey().getText());
				//	CurrentData.HierarchyKey = ((Long) getJTextFieldHierarchyKey().getValue()).intValue();
					
					
					
					////////////adding the features.. 
					CurrentData.setType(ClassifierData.Hierarchal);
					CurrentData.createClassifierName();
					CurrentData.createFeatureNameList();
					CurrentData.useProbability=getJCheckBox().isSelected();
					CurrentData.useSplit=getJCheckBoxSplit().isSelected();
					if (EditMode){
					 
						ClassifiersData.setRow(row_Edit, CurrentData);
						
					}
					else {
					ClassifiersData.addClassifier(CurrentData);
					}
					getJListDigitsClass2().setEnabled( false); 
					 
					getJListDigitsClass1().setEnabled( false);
					getJListFeatures().setEnabled( false);
					getJListDigitsClass1().setSelectedIndex(-1);
					getJListDigitsClass2().setSelectedIndex(-1);
					getJListFeatures().setSelectedIndex(-1);
					
					EditMode=false;
					getJTableClassifier().updateUI();
					logger.info(" out of edit mode.............");
				}
			});
		}
		return jButtonSave;
	}

	/**
	 * This method initializes jButtonGenerateFile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonGenerateFile() {
		if (jButtonGenerateFile == null) {
			jButtonGenerateFile = new JButton();
			jButtonGenerateFile.setText("Generate File");
			jButtonGenerateFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()");          
					
					// In response to a button click:
					if (fc.showSaveDialog(getJContentPane().getParent()) == fc.APPROVE_OPTION){
					
						
						ClassifierData.SaveClassifiersDetails(ClassifiersData.getData(), fc.getSelectedFile().getAbsolutePath().replace(".txt", ""), false);
						
						ClassifierData.SaveClassifiersSummary (ClassifiersData.getData(), fc.getSelectedFile().getAbsolutePath().replace(".txt", "")+"_Sorted", true,true);
						//ArrayList<ClassifierData> temp = ClassifierData.ReadClassifiersDetails(fc.getSelectedFile().getAbsolutePath());
				      
						
					}  
					
				}
			});
		}
		return jButtonGenerateFile;
	}

	/**
	 * This method initializes jComboBoxType	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxType() {
		if (jComboBoxType == null) {
			  typeString=new DefaultComboBoxModel();
			typeString.addElement( "Multi Class");
			typeString.addElement("Binary");
			typeString.addElement("Hierarchal");
 
			jComboBoxType = new JComboBox(typeString);
			jComboBoxType.setSelectedIndex(2);
			jComboBoxType.setEnabled(false);
		
			jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.info("actionPerformed()  combo ");          
				}
			});
		}
		return jComboBoxType;
	}
 
}  //  @jve:decl-index=0:visual-constraint="0,0"
