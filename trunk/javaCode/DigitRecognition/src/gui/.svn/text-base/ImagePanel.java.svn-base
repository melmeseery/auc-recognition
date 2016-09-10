package gui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Maha
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image=null;  //  @jve:decl-index=0:

	private double scaleFactor;
	/**
	 * 
	 */
	public ImagePanel() {
		
		super();
		initialize();
	}
    public void setImage(BufferedImage im){
    	image=im;
    	
    	scaleFactor= (double)this.getWidth()/(double)image.getWidth();
    }
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (image!=null)
		{
			  Insets insets = getInsets();
		        AffineTransform xform = AffineTransform.getTranslateInstance(insets.left, insets.top);
		        xform.scale(scaleFactor, scaleFactor);
		        Graphics2D g2 = (Graphics2D) g;
		        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		        g2.drawRenderedImage(image, xform);
		     //   ((Graphics2D)g).drawImage(image,null ,0, 0);
		        
		        
		
		}
	
	}

	/**
	 * @param layout
	 */
	public ImagePanel(LayoutManager layout) {
		super(layout);
		
		initialize();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public ImagePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
		initialize();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public ImagePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(100, 100);
		this.setLayout(new GridBagLayout());
	}

}
