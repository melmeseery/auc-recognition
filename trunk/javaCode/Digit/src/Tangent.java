/**
 * 
 */

/**
 * @author Maha
 *
 */
public class Tangent {

	   @Override
	public String toString() {
		   String s="";
		   
		   s+=" r1= "+r1+"  c1b=  "+c1b+"  c1e=  "+c1e;
		   s+=" c2= "+c2+"  r2b=  "+r2b+"  r2e=  "+r2e;
		   s+=" c3= "+c3+"  r3b=  "+r3b+"  r3e=  "+r3e;
		   s+=" r4= "+r4+"  c4b=  "+c4b+"  c4e=  "+c4e;
		   
		// TODO Auto-generated method stub
		return s;
	}
	int r1, c1b, c1e;
	    int r2b, r2e, c2;
	    int r3b, r3e, c3;
	    int r4, c4b, c4e;
	/**
	 * 
	 */
	public Tangent() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c1b;
		result = prime * result + c1e;
		result = prime * result + c2;
		result = prime * result + c3;
		result = prime * result + c4b;
		result = prime * result + c4e;
		result = prime * result + r1;
		result = prime * result + r2b;
		result = prime * result + r2e;
		result = prime * result + r3b;
		result = prime * result + r3e;
		result = prime * result + r4;
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
		final Tangent other = (Tangent) obj;
		if (c1b != other.c1b)
			return false;
		if (c1e != other.c1e)
			return false;
		if (c2 != other.c2)
			return false;
		if (c3 != other.c3)
			return false;
		if (c4b != other.c4b)
			return false;
		if (c4e != other.c4e)
			return false;
		if (r1 != other.r1)
			return false;
		if (r2b != other.r2b)
			return false;
		if (r2e != other.r2e)
			return false;
		if (r3b != other.r3b)
			return false;
		if (r3e != other.r3e)
			return false;
		if (r4 != other.r4)
			return false;
		return true;
	}

}
