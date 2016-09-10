/**
 *
 */
package io;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

import com.jmatio.io.MatFileWriter;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLChar;
import com.jmatio.types.MLDouble;
import com.jmatio.types.MLEmptyArray;

/**
 * @author TOSHIBA
 *
 */
public class MatHandler {
	 MatFileWriter wr;
	 ArrayList data;

	 public MatHandler(){

		 data=new ArrayList();

	 }


	public void addString(String name, String value){

		 MLChar mlChar = new MLChar( name, value );
		 data.add(mlChar);

	}
	public void addDoubleMatrix(String name, double[][] mat){
		 MLDouble mlDouble = new MLDouble( name, mat );
		 data.add(mlDouble);
	}
    public void addCellArray( MLCell cell){
    	data.add(cell);


    }

	public void addDoubleArray(String name, double[] mat){
		 MLDouble mlDouble = new MLDouble( name, mat,mat.length );
		 data.add(mlDouble);
	}
	public void writeFile(String filename){

		try {
			wr=new MatFileWriter(  filename, data );


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

 public static void	main(String [] arg){

	 MatHandler mat=new MatHandler();
	 double[] src = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
	 mat.addDoubleArray("x", src);

	 double[] src3 = new double[] { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6 };
	 mat.addDoubleArray("y", src3);


	 double[][] src2 =  { {1.0, 2.0},{ 3.0, 4.0}, {5.0, 6.0} };
	 mat.addDoubleMatrix("doubleMat", src2);


    mat.addString("anyname", "filename fo the same file is ");




    mat.writeFile("tryit.mat");


//	 //1. First create example arrays
//	 double[] src = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
//	 MLDouble mlDouble = new MLDouble( "double_arr", src, 3 );
//	 MLChar mlChar = new MLChar( "char_arr", "I am dummy" );
//
//	 //2. write arrays to file
//	 ArrayList list = new ArrayList();
//	 list.add( mlDouble );
//	 list.add( mlChar );
//	 try {
//		new MatFileWriter( "mat_file.mat", list );
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	}



public void addDoubleArray(String name, ArrayList<? extends Number> c) {
	double [] mat=new double[c.size()];

	for (int i = 0; i < mat.length; i++) {
		mat[i]=  c.get(i).doubleValue();
	}
	 MLDouble mlDouble = new MLDouble( name, mat,mat.length );
	 data.add(mlDouble);
}


public void addStringArray(String name, ArrayList<String> value) {
	int[] dims=new int[2];
	dims[0]=value.size();
	dims[1]=1;

	MLCell arr=new MLCell(name, dims);

	for (int j = 0; j < value.size(); j++) {
		MLChar mlChar = new MLChar( name, value.get(j) );

		arr.set(mlChar, j);
	}

	 data.add(arr);
}


}
