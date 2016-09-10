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
public class StringLabel extends Label {



	String l;
	/**
	 *
	 */
	public StringLabel() {
		// TODO Auto-generated constructor stub
	}

	public String getL() {
		return l;

	}

	public void setL(String l) {
		this.l = l;
		index=l.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Label o) {
		if (o instanceof StringLabel) {
			StringLabel new_name = (StringLabel) o;

			return l.compareTo(new_name.l);
		}
		else return 0;


	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((l == null) ? 0 : l.hashCode());
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
		StringLabel other = (StringLabel) obj;
		if (l == null) {
			if (other.l != null)
				return false;
		} else if (!l.equals(other.l))
			return false;
		return true;
	}

	@Override
	Label ReadObjectTxt(Scanner input) {
		StringLabel data=new 	StringLabel();

		if (input.hasNext())
	   data.l=input.next();
       return data;
	}

	@Override
	void write(PrintStream out) {
		out.print(l);

	}

}
