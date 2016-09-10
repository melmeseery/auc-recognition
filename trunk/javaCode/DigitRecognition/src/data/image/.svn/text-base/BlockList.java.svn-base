/**
 * 
 */
package data.image;

import java.util.ArrayList;

/**
 * @author TOSHIBA
 *
 */
public class BlockList extends ArrayList<Block> {

	int location ;
	int orientation=HORIZONATAL;
	public final static int HORIZONATAL=0;
	public final static int VERTICAL=1;
	int Length;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6320476781120387518L;

	public int getBlockNumbers(int Type){
		
		// get the number of black or white blocks... 
		
		return 0;
	}
	
	
	public boolean checkBlockTotalLenght(){
		//check if all blocks sums to length... 
		
	return true;	
	}


	@Override
	public String toString() {
 
//		return super.toString();
		
		String st;
		
		
		st="  "+location+ "=== (";
		
		Block temp;
		for (int i = 0; i < size(); i++) {
			temp=get(i);
			if (temp.Type==Block.WHITE)
			{
				st+=temp.count+" ";
			}else{
				st+="|B "+temp.count+"|";	
			}
			
		}
		st+=")=== [";
		for (int i = 0; i < size(); i++) {
			temp=get(i);
			if (temp.Type==Block.BLACK)
			{
				st+=" "+ temp.start+","+temp.end+"--";
			}
		}
		
		st+=" ] ";
		
		return st;
	}
	
}
