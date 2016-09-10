package gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import applications.RunFeatureWithWekaClassifier;
import applications.RunWekaFeatureClassifierTest;
import applications.subGeneralTest.RunDisplayFrame;
import applications.subGeneralTest.RunFeatureEvaluater;
import applications.subGeneralTest.RunFeatureGenerationAllDigits;
import applications.subGeneralTest.RunSimpleTests;
import applications.subMuliClassifiers.RunOVOLibLinear;
import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSetGenerator;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;
import data.image.ImageReader;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ImageViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private ImagePanel imagePanel = null;
	private JTextField jTextField = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JButton jButton5 = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	private JButton jButton6 = null;
	private JRadioButton jRadioButtonMNIST = null;
	private JPanel jPanel = null;
	private JRadioButton jRadioButtonMad = null;
	private int  dbType=DataBaseConnector.MADBASE;
	ButtonGroup group;
	public ImageViewer() throws HeadlessException {
		
		super();
		initialize();
	}

	public ImageViewer(GraphicsConfiguration gc) {
		super(gc);
	
		initialize();
	}

	public ImageViewer(String title) throws HeadlessException {
		super(title);
		
		initialize();
	}

	public ImageViewer(String title, GraphicsConfiguration gc) {
		super(title, gc);

		initialize();
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(184, 11, 91, 32));
			jButton.setText("Display");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				//().setImage();
					DataSetGenerator db;
				if (  dbType ==  DataBaseConnector.MNIST){
//					Digit image=new Digit();
//					ImageReader imreader=ImageReader.CreateImageReader(	DataBaseConnector.MNIST);
//					imreader.setFile(getJTextField().getText());
//					//read image and compute features. 
//					imreader.ReadBoundedBinary();
//					Digit d=new Digit();
//					d.ReadImage(imreader);
//					d.computeAllFeatures();
					  db=new  MNISTDataSetGenerator();
				}	
				else {
					db=new ArabicDataSetGenerator();
				}
				
					 Digit d=db.getSingleImage( getJTextField().getText());
//			  
				       d.computeAllFeatures();	 
					getImagePanel().setImage( d.getImage()  );
					 getJTextArea().setText( d.toString());
					
			           repaint();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes imagePanel	
	 * 	
	 * @return gui.ImagePanel	
	 */
	private ImagePanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new ImagePanel();
			imagePanel.setBounds(new Rectangle(300, 14, 271, 290));
		}
		return imagePanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(70, 12, 108, 34));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(15, 55, 183, 31));
			jButton1.setText("Run Weka Classifier(OVO)");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 

						 
					  RunFeatureWithWekaClassifier test=new  RunFeatureWithWekaClassifier();
					
						Thread th=new Thread(test);
						th.run();

				
				}
			});
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
			jButton2.setBounds(new Rectangle(16, 247, 185, 36));
			jButton2.setText("Run OVO LibLinear");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RunOVOLibLinear test=new RunOVOLibLinear();
					Thread th=new Thread(test);
					th.run();
					}
			});
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
			jButton3.setBounds(new Rectangle(15, 102, 183, 33));
			jButton3.setText("Generate FeaturesAll.txt");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					RunFeatureGenerationAllDigits test=new RunFeatureGenerationAllDigits();
					
					Thread th=new Thread(test);
					th.run();
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(15, 148, 183, 33));
			jButton4.setText("Feature Evaluator");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RunFeatureEvaluater test=new RunFeatureEvaluater();
					Thread th=new Thread(test);
					th.run();
				
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
			jButton5.setBounds(new Rectangle(15, 195, 183, 35));
			jButton5.setText("Run Weka Classifier (Features)");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					 
					RunWekaFeatureClassifierTest test=new RunWekaFeatureClassifierTest();
					Thread th=new Thread(test);
					th.run();
				
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
			jTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
//					//logger.info("mouseClicked()"); 
					String str;
				str="";
//					// read file from 
//					
//					try {
//						File afile = new File("Example.log");
//						BufferedReader input = new BufferedReader(
//								new FileReader(afile));
//						Scanner scanner = new Scanner(input);
//					    try {
//					      //first use a Scanner to get each line
//					      while ( scanner.hasNextLine() ){
//					       str+=scanner.next();
//					      }
//					    }
//					    finally {
//					      //ensure the underlying stream is always closed
//					      scanner.close();
//					    }
//					} catch (FileNotFoundException e1) {
//						
//						e1.printStackTrace();
//					}

					
					getJTextArea().setText(str );
				// read the example.log. ... 
				}
			});
		}
		return jTextArea;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(595, 17, 333, 291));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setBounds(new Rectangle(213, 57, 66, 27));
			jButton6.setText("Test");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					RunSimpleTests test=new RunSimpleTests();
					Thread th=new Thread(test);
					th.run();
				
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jRadioButtonMNIST	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonMNIST() {
		if (jRadioButtonMNIST == null) {
			jRadioButtonMNIST = new JRadioButton();
			jRadioButtonMNIST.setText("MNIST");
			jRadioButtonMNIST.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					if (getJRadioButtonMNIST().isSelected()){
						 dbType=DataBaseConnector.MNIST;
					}
//					else {
//						
//					}
				}
			});
		}
		return jRadioButtonMNIST;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(200, 88, 95, 101));
			jPanel.add(getJRadioButtonMNIST(), gridBagConstraints);
			jPanel.add(getJRadioButtonMad(), gridBagConstraints1);
			
		
	         group = new ButtonGroup();
		    group.add(getJRadioButtonMNIST());
		    group.add(getJRadioButtonMad());
		    
				getJRadioButtonMad().setSelected(true);
		}
		return jPanel;
	}

	/**
	 * This method initializes jRadioButtonMad	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonMad() {
		if (jRadioButtonMad == null) {
			jRadioButtonMad = new JRadioButton();
			jRadioButtonMad.setText("MADBASE");
			jRadioButtonMad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("actionPerformed()"); 	if (getJRadioButtonMad().isSelected()){
						 dbType=DataBaseConnector.MADBASE;
					}
				}
			});
		}
		return jRadioButtonMad;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ImageViewer thisClass = new ImageViewer();
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
		 
		this.setSize(1038, 360);
		this.setContentPane(getJContentPane());
		this.setTitle("Image Frame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 13, 49, 32));
			jLabel.setText("Image");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getImagePanel(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton6(), null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
