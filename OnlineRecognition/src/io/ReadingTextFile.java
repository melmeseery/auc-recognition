/**
 *
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import data.DoubleObjects;
import data.GeneraicDataSet;
import data.IntegerLabel;

import app.RunTestConverter;

/**
 * @author TOSHIBA
 *
 */
public class ReadingTextFile {
	private static transient final Logger logger = Logger.getLogger(ReadingTextFile.class);
	File afile;
	Scanner input;

	public ReadingTextFile (String filename)
	{


		 afile = new File(filename);
			 try {
				input = new Scanner(new BufferedReader(new FileReader(afile)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	/**
	 * @return
	 *
	 */
//	public void ReadDataset(GeneraicDataSet<DoubleObjects , IntegerLabel>  dataset) {
//		// String line;
//		try {
//			logger.info("reading the file................ wait");
//
//
//			int linecount = 0;
//			int readcount = 0;
//			// String[] datastrings;
//			int j = 0;
//			// input.useDelimiter(" ");
//
//			while (input.hasNext()) {
//
//				// datastrings = line.split(" ");
//
//				if (readcount == 0) {
//					this.NumOfSamples = input.nextInt();
//					readcount++;
//					NumOfFeatures = input.nextInt() - 1;
//					readcount++;
//
//					dataAdd=new ArrayList<Ob>();
//					trainDataTargets=new ArrayList<LabOb>();
////					data = new double[NumOfSamples][NumOfFeatures];
////					dataTargets = new int[NumOfSamples];
//					logger.info(" the first time read = " + NumOfSamples
//							+ "  with " + NumOfFeatures + " features ");
//					j = 0;
//
//				} else {
//
//
//					//Ob t=EmptyObject;
//
//					Ob obj= (Ob) EmptyObject.ReadObjectTxt(input,NumOfFeatures );
//					dataAdd.add(obj);
//					LabOb tar=(LabOb) LabOb.creatObject();
//					TargetsAdd.add(tar);
//					// logger.info();
//					// System.out.print(" sample "+j);
////					for (int i = 0; i < NumOfFeatures && input.hasNext(); i++) {
////
////						data[j][i] = input.nextDouble();
////						readcount++;
////						// System.out.print("  "+data[j][i]);
////					}
////
////					if (input.hasNext()) {
////						dataTargets[j] = input.nextInt();
////						readcount++;
////						// System.out.print("  target is =  "+ dataTargets[j]);
////					}
//
//					j++;
//
//				}
//
//				if (j > NumOfSamples) {
//					break;
//				}
//				linecount++;
//
//				if (linecount % 3000 == 0)
//					logger.info(" reading line n " + linecount);
//
//				// this statement reads the line from the file and print it to
//				// the console.
//				// logger.info(line);
//			}
//
//			// dispose all the resources after using them.
//			input.close();
//
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		}
//		// catch (IOException e) {
//		//
//		// e.printStackTrace();
//	}

}
