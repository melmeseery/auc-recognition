/**
 *
 */
package data;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * @author TOSHIBA
 *
 */
public abstract  class Label implements Comparable<Label>, Serializable, Cloneable{

	protected int index=0;
	protected int intValue=0;
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}



	/**
	 *
	 */
	private static final long serialVersionUID = -7014314877629720817L;



	public int index(){

		return index;
	}
    public void setIndex(int in){
    	index=in;
    }



	public Object clone()  {
		return null;

	}

	 abstract Label  ReadObjectTxt(Scanner input);
	abstract void  write(PrintStream out);
//	public static   Label  creatObject(){
//		return null;
//	}
}
