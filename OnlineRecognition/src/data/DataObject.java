package data;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class DataObject implements Cloneable {


	public   Object clone(){

		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	 public abstract double[][] getDataArray();

//	 public static   DataObject createObject() {
//		return null;
//	}

	 abstract DataObject  ReadObjectTxt(Scanner   input, int numOfFeatures);
	abstract void  write(PrintStream out);



}

//public interface DataObject extends Cloneable {
//
//
//	public Object clone();
//
//	 public double[][] getDataArray();
//
//	 public  DataObject createObject();
//
//	 DataObject  ReadObjectTxt(Scanner   input, int numOfFeatures);
//
//
//}
