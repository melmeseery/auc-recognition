/**
 *
 */
package io;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import app.RunTestFileReaders;

import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLDouble;
import com.jmatio.types.MLNumericArray;

import data.SimpleInkObject;

/**
 * @author TOSHIBA
 *
 */
public class StrokeToMat {
	private static transient final Logger logger = Logger.getLogger(StrokeToMat .class);
	MatHandler mat;
	MLCell cell;
	public StrokeToMat(){

		mat=new MatHandler();
	}


  public void writeMatFile(String filename){

	  mat.writeFile(filename);

  }
	public void addStroke(ArrayList<SimpleInkObject> strokes){

		int[] dims=new int[2];
		dims[0]=strokes.size();
		dims[1]=1;


	//	mat.addDoubleMatrix("strok3333", strokes.get(0).getPointsArray());


		MLCell cell=new MLCell("Strokes", dims);

          logger.info("  dmiss is "+dims);
          MLArray arr;

          logger.info("the size of strokes. is "+strokes.size()+"  cell size is  "+cell.getSize());
          for (int j = 0; j < strokes.size(); j++) {
        	  logger.info(" j "+j+"  index is "+ cell.getIndex(j,1)  );
        	  arr=new MLDouble ("S"+j,strokes.get(j).getPointsArray())  ;




     	   cell.set(arr,j);

		}

          mat.addCellArray(cell);


	}



}
