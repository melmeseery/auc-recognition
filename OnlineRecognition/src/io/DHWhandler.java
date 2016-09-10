package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import app.SystemSettings;

import data.PointData;
import data.SimpleInkObject;

public class DHWhandler {

	private static final Logger logger = Logger.getLogger(ReadDHW.class);
	private String sHeader = "";
	private int fileversion;
	private int paperwidth,  paperheight;
	private int pagetype;
	private String paper = "";
	private String orientation = "";
	private int colorlayers = 0;

	private SimpleInkObject Stroke;
	private ArrayList<SimpleInkObject> AllStrokes;


	public DHWhandler() {
	//	org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
	}

	public void readTheFile(  String  FileName){
	    AllStrokes = new ArrayList<SimpleInkObject>();
	    Stroke = new SimpleInkObject();
	    boolean debug = SystemSettings.DEBUG;
	    try {
	        RandomAccessFile file = new RandomAccessFile(new File( FileName), "r");

	        // read the header
	        char[] header = new char[32];
	        for (int i = 0; i < 32; i++) {
	            sHeader += (char) file.readUnsignedByte();
	        }
	        if (debug) {
	            System.out.println("Header -> " + sHeader);
	        }
	        logger.info("Header -> " + sHeader );
	//logger.info();
	        // read the fileversion
	        fileversion = (int) file.readUnsignedByte();
	        if (debug) {
	            System.out.println("fileversion -> " + fileversion);
	        }
	        logger.info("fileversion -> " + fileversion);
	        //read the paperwidth
	        paperwidth = file.readUnsignedByte() + (file.readUnsignedByte() << 8);

	        //read the paperheight
	        paperheight = file.readUnsignedByte() + (file.readUnsignedByte() << 8);

	        //page type "1" = a4, "0" = a5
	        pagetype = file.readUnsignedByte();
	        if (pagetype == 1) {
	            paper = "a4";
	        }
	        else {
	            paper = "a5";
	        }

	        if (paperwidth > paperheight) {
	            orientation = "Landscape";
	        }
	        else {
	            orientation = "Portrait";
	        }

	        if (debug) {
	            System.out.println("paper width/height -> " + paperwidth + "   " + paperheight + "   " + pagetype);
	           logger.info("paper width/height -> " + paperwidth + "   " + paperheight + "   " + pagetype);
	     }
	        int temp = file.readUnsignedByte();
	        temp = file.readUnsignedByte();

	        int llx = 0;  //  87  for A4 centering on A4
	        int lly = 0;  // 123  for A4 centering on A4
	        int urx = llx + (int) (paperwidth / 1000.0 * 72.0 + 0.999999);
	        int ury = lly + (int) (paperheight / 1000.0 * 72.0 + 0.999999);

	        // read dhw file
	        int color = 0;
	        int lastcolor = 0;
	        int layer = 0;
	        int strokes = 0;
	        int points = 0;
	        String pointcmd = "S";
	        String[] colors = new String[]{"0 0 0", "1 0 0", "0 1 0", "0 0 1"};

	        int count = 41;
	        int next = 0;
	        while ((next = file.read()) != -1) {
	            if ((next & ~0x07) == 0x80) {//# pen up/down

	                if ((next & 0x01) > 0) {
	                    Stroke = new SimpleInkObject();
	                    pointcmd = "S";
	                    if (debug) {
	                        System.out.println("pen down POSITION " + count); logger.info("pen down POSITION " + count);
	                    }
	                 //
	                }
	                else {
	                    AllStrokes.add(Stroke);
	                    pointcmd = "L\nE\n";
	                    strokes++;
	                    if (debug) {
	                        System.out.println("pen up POSITION " + count); logger.info("pen up POSITION " + count);
	                    }
	                 //
	                }
	                color = ((next >> 1) + (layer * colorlayers)) & 0x03;
	                if (debug) {
	                    System.out.println("pen color " + color); logger.info("pen color " + color);
	                }
	               //
	                if (color != lastcolor) {
	                    lastcolor = color;
	                }
	            }
	            else if (next == 136) {//0x88  //# time stamp

	                int timestamp = file.readUnsignedByte();
	                if (timestamp < 0x7f) {
	                    Stroke = new SimpleInkObject();
	                }
	            }
	            else if (next == 0x90) { //   end of layer

	                layer = file.readUnsignedByte();
	                if (debug) {
	                    System.out.println("DEBUG End of LAYER " + layer);
	                    logger.info("DEBUG End of LAYER " + layer);
	                }
	             //
	            }
	            else if ((next & ~0x7f) == 0) { // # coordinates

	                int b1 = next;
	                int b2 = file.readUnsignedByte();
	                int b3 = file.readUnsignedByte();
	                int b4 = file.readUnsignedByte();
	                if (debug) {
	                    System.out.println("DEBUG CHECK position " + count + "  " + b1 + "  " + b2 + "  " + b3 + "  " + b4);
	                    logger.info("DEBUG CHECK position " + count + "  " + b1 + "  " + b2 + "  " + b3 + "  " + b4);

}
	         //
	                int x = b1 + (b2 << 7);
	                int y = b3 + (b4 << 7);
	              //  logger.info(" x = " +x+"  y = "+y);
	                if (debug) {
	                			logger.info(" x = " +x+"  y = "+y);
	                }
	                PointData p=new PointData(x,y);

	                Stroke.addPoint(p);

	                points++;
	                pointcmd = "L";
	            }
	            count++;
	        }

	        // finish file
	        file.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	public ArrayList<SimpleInkObject> getStrokes() {
	    return AllStrokes;
	}
	public int getPaperWidth() {
	    return paperwidth;
	}
	public int getPaperHeight() {
	    return paperheight;
	}


	public static void main (String[] arg){

		SystemSettings.StartApplication();
		DHWhandler dhw=new DHWhandler();
		dhw.readTheFile("page1.DHW");

		logger.info(dhw.AllStrokes);


	}


}
