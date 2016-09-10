package gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

public class StrokeDisplayFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private DrawingSheet drawingSheet = null;

	private TableFrame SourceTable;
	GuiPageModel data;

	public StrokeDisplayFrame(TableFrame tableFrame, GuiPageModel data2) throws HeadlessException {
		super();
		SourceTable=tableFrame;
		data=data2;


		initialize();
		//getDrawingSheet()
		getDrawingSheet().setStrokeData(data);
        data.addObserver(getDrawingSheet());



	}
	public StrokeDisplayFrame() throws HeadlessException{
		super();
		initialize();
	}
	public StrokeDisplayFrame(GraphicsConfiguration arg0) {
		super(arg0);

		initialize();
	}

	public StrokeDisplayFrame(String arg0) throws HeadlessException {
		super(arg0);

		initialize();
	}

	public StrokeDisplayFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);

		initialize();
	}

	public StrokeDisplayFrame(TableFrame tableFrame) {
		super();
		initialize();
		SourceTable=tableFrame;
	}

	/**
	 * This method initializes drawingSheet
	 *
	 * @return gui.DrawingSheet
	 */
	private DrawingSheet getDrawingSheet() {
		if (drawingSheet == null) {
			drawingSheet = new DrawingSheet();
			//drawingSheetsetSize(600, 380);
		}
		return drawingSheet;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StrokeDisplayFrame thisClass = new StrokeDisplayFrame();
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
		this.setSize(745, 410);
		this.setTitle("Degi Memo Page");
		//this.setTitle("Page Viewer");
		this.setContentPane(getJContentPane());
		//this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setSize(700, 380);
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getDrawingSheet(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
//	@Override
//	public void update(Observable o, Object arg) {
//		// TODO Auto-generated method stub
//
//	}
	@Override
	public void paintAll(Graphics g) {
		// TODO Auto-generated method stub
		super.paintAll(g);


	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
//
//		if(data!=null){
//
//			data.getStrokeGroupData().paint((Graphics2D) g);
//
//		}


	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
