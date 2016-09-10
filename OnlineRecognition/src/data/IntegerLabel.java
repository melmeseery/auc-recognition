/**
 *
 */
package data;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author TOSHIBA
 *
 */
public class IntegerLabel extends Label {

	int cat;
	public int getCat() {
		return cat;
	}
	public int getIntValue() {
		return cat;
	}
	public void setCat(int cat) {
		this.cat = cat;
		index=cat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerLabel other = (IntegerLabel) obj;
		if (cat != other.cat)
			return false;
		return true;
	}



	@Override
	public int compareTo(Label o) {
		if (o instanceof IntegerLabel) {
			IntegerLabel new_name = (IntegerLabel) o;

			return (new Integer(cat)).compareTo(new_name.cat);
		}
		else return 0;
	}

	@Override
	public String toString() {
		return ""+ cat ;
	}
	@Override
	Label ReadObjectTxt(Scanner input) {

		IntegerLabel data=new IntegerLabel();

		if (input.hasNext())
	   data.cat=input.nextInt();
       return data;
	}
	@Override
	void write(PrintStream out) {
		out.print(this.cat);

	}

}
