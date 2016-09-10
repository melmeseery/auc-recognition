package io;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author stealth
 */
public class JDHW_GUI extends JFrame implements ActionListener {

private String sHeader = "";
private int fileversion;
private int paperwidth,  paperheight;
private int pagetype;
private String paper = "";
private String orientation = "";
private FileWriter fw;
private int colorlayers = 0;
private JButton jbOpen,  jbExit,  jbConvert;
private File[] omFiles;
private String omDirectory;
private JFrame jf;
private float delta;
private boolean bGridH,  bGridV;
private float StrokeThick,  GridThick;
private JTextField jtStrokeThickness,  jtGridThickness,  jtGridDistance;
private JCheckBox jcbGridVertical,  jcbGridHorizontal,  jcbCombineAllPages;
private Dimension screenSize;

public JDHW_GUI() {

   jf = new JFrame("DHW to PDF converter");
   JPanel jpanel = new JPanel();
   jpanel.setLayout((new GridLayout(6, 1)));
   jbOpen = new JButton("Select Files");
   jpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

   JLabel jlStrokeThickness = new JLabel(" Stroke thickness: ");
   jtStrokeThickness = new JTextField("0.6");

   JPanel jp2 = new JPanel();
   jp2.setLayout(new GridLayout(1, 2));
   jp2.add(jlStrokeThickness);
   jp2.add(jtStrokeThickness);

   jcbGridVertical = new JCheckBox("Add vertical grid lines");
   jcbGridHorizontal = new JCheckBox("Add horizontal grid lines");
   jcbCombineAllPages = new JCheckBox("Combine all pages");

   JLabel jlGridThickness = new JLabel(" Grid line thickness: ");
   jtGridThickness = new JTextField("0.1");
   JPanel jp22 = new JPanel();
   jp22.setLayout(new GridLayout(1, 2));
   jp22.add(jlGridThickness);
   jp22.add(jtGridThickness);

   JLabel jlGridDistance = new JLabel(" Grid line distance: ");
   jtGridDistance = new JTextField("11.22");

   JPanel jp23 = new JPanel();
   jp23.setLayout(new GridLayout(1, 2));
   jp23.add(jlGridDistance);
   jp23.add(jtGridDistance);

   jpanel.add(jcbCombineAllPages);
   jpanel.add(jcbGridVertical);
   jpanel.add(jcbGridHorizontal);

   jcbGridVertical.setSelected(true);
   jcbGridHorizontal.setSelected(true);

   jpanel.add(jp22);
   jpanel.add(jp23);
   jpanel.add(jp2);

   JPanel jp21 = new JPanel();
   jp21.setLayout(new GridLayout(3, 1));
   jbConvert = new JButton("Convert");
   jbExit = new JButton("Exit");
   jp21.add(jbOpen);
   jp21.add(jbConvert);
   jp21.add(jbExit);

   jbConvert.setEnabled(false);

   jbOpen.addActionListener(this);
   jbExit.addActionListener(this);
   jbConvert.addActionListener(this);

   jf.setLayout((new BorderLayout()));
   jf.add(jpanel, BorderLayout.CENTER);
   jf.add(jp21, BorderLayout.SOUTH);
   jf.setSize(280, 300);
   screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   jf.setLocation(screenSize.width - 300, screenSize.height / 2 - 300);
   jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   jf.setVisible(true);

}

public void actionPerformed(ActionEvent e) {
   if (e.getSource() == jbOpen) {
      try {
         JFileChooser fc = new JFileChooser();
         fc.setMultiSelectionEnabled(true);
         File dir = null;
         int returnVal = fc.showOpenDialog(jf);
         if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
         }
         omFiles = fc.getSelectedFiles();
         if (omFiles.length == 0) {
            omFiles = new File[1];
            omFiles[0] = fc.getSelectedFile();
         }
         jbConvert.setEnabled(true);
         omDirectory = fc.getCurrentDirectory().getPath() + File.separator;
      } catch (Exception ee) {
      }
   }

   if (e.getSource() == jbConvert) {
      jbExit.setEnabled(false);
      jbOpen.setEnabled(false);
      jbConvert.setEnabled(false);
      bGridV = jcbGridVertical.isSelected();
      bGridH = jcbGridHorizontal.isSelected();
      delta = Float.valueOf(jtGridDistance.getText());
      StrokeThick = Float.valueOf(jtStrokeThickness.getText());
      GridThick = Float.valueOf(jtGridThickness.getText());
      ReadDHW Reader = new ReadDHW();
      boolean combine = jcbCombineAllPages.isSelected();
      if (!combine) {
         for (int i = 0; i < omFiles.length; i++) {
            Reader.readTheFile(omDirectory, omFiles[i].getName());
//            DHWtoPDF conv = new DHWtoPDF(omDirectory + omFiles[i].getName() + ".pdf",
//                              Reader.getPaperWidth(), Reader.getPaperHeight(),
//                              delta, bGridH, bGridV, StrokeThick, GridThick);
//            conv.convert(Reader.getStrokes());
//            conv.closeDocument();
         }
      } else {
         Reader.readTheFile(omDirectory, omFiles[0].getName());
//         DHWtoPDF conv = new DHWtoPDF(omDirectory + omFiles[0].getName() + ".pdf",
//                           Reader.getPaperWidth(), Reader.getPaperHeight(),
//                           delta, bGridH, bGridV, StrokeThick, GridThick);
//         conv.convert(Reader.getStrokes());
//         for (int i = 1; i < omFiles.length; i++) {
//            Reader.readTheFile(omDirectory, omFiles[i].getName());
//            conv.addNewPage();
//            conv.convert(Reader.getStrokes());
//         }
     //    conv.closeDocument();
      }

      JOptionPane.showMessageDialog(jf, "Done! ", "Message", 1);
      jbExit.setEnabled(true);
      jbOpen.setEnabled(true);
      jbConvert.setEnabled(true);

   }

   if (e.getSource() == jbExit) {
      System.exit(1);
   }

}
}