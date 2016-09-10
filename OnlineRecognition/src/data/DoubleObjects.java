package data;

import java.awt.geom.Rectangle2D;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class DoubleObjects extends DataObject implements  List<Double> {


	/**
	 *
	 */
	ArrayList<Double>  list=new ArrayList<Double>();


	private static final long serialVersionUID = -5012529414015896043L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		DoubleObjects other = (DoubleObjects) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer st=new StringBuffer("DoubleObjects [list=");
		for (int i = 0; i < list.size(); i++) {
			st.append(list.get(i));
		}
		st.append(" ");
		return new String(st);
	}
	@Override
	public double[][] getDataArray() {

		double [][] temp=new double[1][this.size()];

		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i]=this.get(i);
		}

		return temp;
	}
    public void addArray(double [] arr){
    	for (int i = 0; i < arr.length; i++) {
			add(new Double (arr[i]));
		}

    }
    public void add(double value){
    	add(new Double(value));
    }

	@Override
	DataObject ReadObjectTxt(Scanner input, int  NumOfFeatures) {

		DoubleObjects d=new DoubleObjects();

	for (int i = 0; i < NumOfFeatures && input.hasNextDouble(); i++) {

								d.add(  input.nextDouble());
			 }


		return d;
	}

	@Override
	void write(PrintStream out) {
		double dataw;
		for (int j = 0; j < this.size(); j++) {

			if (Double.isNaN(list.get(j))) {
				//logger.error("NANA ins SAMPLE " + i + "  FEATURE  " + j);
				dataw = 0.0;
			} else
				dataw = list.get(j);

			out.print(dataw);

			// }

			out.print(",");
			out.print("  ");
		}

	}



	public Object clone(){

		//try {
			DoubleObjects clone=(DoubleObjects)super.clone();

		for (int i = 0; i < clone.size(); i++) {
			//Double t=this.get(i);
			clone.set(i,new Double(get(i).doubleValue()));

		}
				return clone;

	}
	@Override
	public boolean add(Double e) {
		 return list.add(e);
		//return false;
	}
	@Override
	public void add(int index, Double element) {
		// TODO Auto-generated method stub
		list.add(index, element);
	}
	@Override
	public boolean addAll(Collection<? extends Double> c) {

		return list.addAll(c);

	}
	@Override
	public boolean addAll(int index, Collection<? extends Double> c) {
	          return list.addAll(index, c);
	}
	@Override
	public void clear() {

		list.clear();
	}
	@Override
	public boolean contains(Object o) {
		return list.contains(o) ;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c) ;
	}
	@Override
	public Double get(int index) {
	 return list.get(index);
	}
	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}
	@Override
	public boolean isEmpty() {

		return list.isEmpty();
	}
	@Override
	public Iterator<Double> iterator() {

		return list.iterator();
	}
	@Override
	public int lastIndexOf(Object o) {
	 	return list.lastIndexOf(o);
	}
	@Override
	public ListIterator<Double> listIterator() {

		return list.listIterator();
	}
	@Override
	public ListIterator<Double> listIterator(int index) {

		return list.listIterator(index);
	}
	@Override
	public boolean remove(Object o) {

		return list.remove(o);
	}
	@Override
	public Double remove(int index) {

		return  list.remove(index);
	}
	@Override
	public boolean removeAll(Collection<?> c) {

		return  list.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {

		return  list.retainAll(c);
	}
	@Override
	public Double set(int index, Double element) {

		return list.set(index, element);
	}
	@Override
	public int size() {

		return list.size();
	}
	@Override
	public List<Double> subList(int fromIndex, int toIndex) {

		return list.subList(fromIndex, toIndex) ;
	}
	@Override
	public Object[] toArray() {

		return  list.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {

		return  list.toArray(a);
	}




}
