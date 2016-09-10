/**
 * 
 */
package util;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.apache.log4j.Logger;

/**
 * @author maha
 *
 */
public class BinaryTree  <T extends Comparable>  {

	private static transient final Logger logger = Logger.getLogger( BinaryTree .class);
	/* 
	   --Node-- 
	   The binary tree is built using this nested node class. 
	   Each node stores one data element, and has left and right 
	   sub-tree pointer which may be null. 
	   The node is a "dumb" nested class -- we just use it for 
	   storage; it does not have any methods. 
	  */ 
	  private static class Node   <T extends Comparable> implements Cloneable , Serializable{ 
	    Node left; 
	    Node right; 
	    T  data;

	    Node(T  newData) { 
	      left = null; 
	      right = null; 
	      data = newData; 
	    } 
	  }
	  Node root; 

		public BinaryTree() {
			 root=null;
		}
		
		 /** 
		   Returns true if the given target is in the binary tree. 
		   Uses a recursive helper. 
		  */ 
		  public boolean find(T data) { 
		    return(find(root, data)); 
		  } 
		 

		  /** 
		   Recursive lookup  -- given a node, recur 
		   down searching for the given data. 
		  */ 
		  private boolean find(Node node, T data) { 
		    if (node==null) { 
		      return(false); 
		    }

		    if (data.equals(node.data)) { 
		      return(true); 
		    } 
		    else {
		       if( data.compareTo( node.data ) < 0 )
		    	    find( node.left,data );
		       else if( data.compareTo( node.data ) > 0 )
		    		       find (node.right,data );
		    }
			return false;
		     
		  } 
		  /** 
		   Inserts the given data into the binary tree. 
		   Uses a recursive helper. 
		  */ 
		  public void insert(T data) { 
		    root = insert(root, data); 
		  } 
		 

		  /** 
		   Recursive insert -- given a node pointer, recur down and 
		   insert the given data into the tree. Returns the new 
		   node pointer (the standard way to communicate 
		   a changed pointer back to the caller). 
		  */ 
		  private Node insert(Node node, T data) { 
		    if (node==null) { 
		      node = new Node(data); 
		    } 
		    else { 
		    	if (data.compareTo(node.data)<0){
		      //if (data <= node.data) { 
		        node.left = insert(node.left, data); 
		      } 
		      else { 
		        node.right = insert(node.right, data); 
		      } 
		    }

		    return(node); // in any case, return the new pointer to the caller 
		  } 
		  

	        /**
	         * Remove from the tree. Nothing is done if x is not found.
	         * @param x the item to remove.
	         */
	        public void remove( T x )
	        {
	            root = remove( x, root );
	        }

	 
	      /**
	         * Internal method to remove from a subtree.
	         * @param x the item to remove.
	         * @param t the node that roots the tree.
	         * @return the new root.
	         */
	        private Node remove( T x, Node t )
	        {
	            if( t == null )
	                return t;   // Item not found; do nothing
	            if( x.compareTo( t.data ) < 0 )
	                t.left = remove( x, t.left );
	            else if( x.compareTo( t.data ) > 0 )
	                t.right = remove( x, t.right );
	            else if( t.left != null && t.right != null ) // Two children
	            {
	                t.data = findMin( t.right ).data;
	                t.right = remove( (T) t.data, t.right );
	            }
	            else
	                t = ( t.left != null ) ? t.left : t.right;
	            return t;
	        }

		  
/** 
Returns the number of nodes in the tree. 
Uses a recursive helper that recurs 
down the tree and counts the nodes. 
*/ 
public int size() { 
 return(size(root)); 
}
private int size(Node node) { 
 if (node == null) return(0); 
 else { 
   return(size(node.left) + 1 + size(node.right)); 
 } 
} 
/** 
Returns the max root-to-leaf depth of the tree. 
Uses a recursive helper that recurs down to find 
the max depth. 
*/ 
public int maxDepth() { 
 return(maxDepth(root)); 
}
private int maxDepth(Node node) { 
 if (node==null) { 
   return(0); 
 } 
 else { 
   int lDepth = maxDepth(node.left); 
   int rDepth = maxDepth(node.right);

   // use the larger + 1 
   return(Math.max(lDepth, rDepth) + 1); 
 } 
} 

/** 
Returns the min value in a non-empty binary search tree. 
Uses a helper method that iterates to the left to find 
the min value. 
*/ 
public T findMin() {
	
Node n= findMin(root) ;
if (n==null)
	return null;
else 
	return (T) n.data;

} 

/** 
Finds the min value in a non-empty binary search tree. 
*/ 
private Node findMin(Node node) { 
 Node current = node; 
 while (current.left != null) { 
   current = current.left; 
 }

 return current; 
}


/**
 * Find the largest item in the tree.
 * @return the largest item of null if empty.
 */
public T findMax( )
{
     
    Node n= findMax(root) ;
    if (n==null)
    	return null;
    else 
    	return (T) n.data;

}
/**
 * Internal method to find the largest item in a subtree.
 * @param t the node that roots the tree.
 * @return node containing the largest item.
 */
private Node findMax( Node t )
{
    if( t != null )
        while( t.right != null )
            t = t.right;

    return   t;
  
}
/**
 * Make the tree logically empty.
 */
public void makeEmpty( )
{
    root = null;
}

/**
 * Test if the tree is logically empty.
 * @return true if empty, false otherwise.
 */
public boolean isEmpty( )
{
    return root == null;
}
/** 
Prints the node values in the "inorder" order. 
Uses a recursive helper to do the traversal. 
*/ 
public void printTree() { 
printTree(root); 
logger.info(" "); 
}
private void printTree(Node node) { 
if (node == null) return;

// left, node itself, right 
printTree(node.left); 
         System.out.print(node.data + "  "); 
printTree(node.right); 
 
} 
/** 
Prints the node values in the "postorder" order. 
Uses a recursive helper to do the traversal. 
*/ 
public void printPostorder() { 
printPostorder(root); 
logger.info(" "); 
}
public void printPostorder(Node node) { 
 if (node == null) return;
//logger.info(" left ");
 // first recur on both subtrees 
 printPostorder(node.left); 
 //logger.info(" right ");
 printPostorder(node.right);
 // then deal with the node 
System.out.print(node.data + "  "); 
} 
 public ArrayList<T> toList() {
	ArrayList<T> result = new ArrayList<T>();
    treeToList(root, result);
    return result;
}
private void treeToList(Node<T> node, List<T> goal) {
    if (node != null) {
        treeToList(node.left, goal);
        goal.add(node.data);
        treeToList(node.right, goal);
    }
}
public void displayTree()
{
Stack globalStack = new Stack();
globalStack.push(root);
int nBlanks = 32;
boolean isRowEmpty = false;
logger.info("......................................................");
while(isRowEmpty==false)
   {
   Stack localStack = new Stack();
   isRowEmpty = true;
   for(int j=0; j<nBlanks; j++)
      System.out.print(' ');
   while(globalStack.isEmpty()==false)
      {
      Node temp = (Node)globalStack.pop();
      if(temp != null)
         {
         System.out.print(temp.data);
         localStack.push(temp.left);
         localStack.push(temp.right);

         if(temp.left != null ||
                             temp.right != null)
            isRowEmpty = false;
         }
      else
         {
         System.out.print("--");
         localStack.push(null);
         localStack.push(null);
         }
      for(int j=0; j<nBlanks*2-2; j++)
         System.out.print(' ');
      }  // end while globalStack not empty
   logger.info("");
   nBlanks /= 2;
   while(localStack.isEmpty()==false)
      globalStack.push( localStack.pop() );
   }  // end while isRowEmpty is false
logger.info(
"......................................................");
}  // end displayTree()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		         
          Double x; 
           BinaryTree<Double> tree=new BinaryTree<Double>();
        
           tree.insert(new Double (10));
           tree.insert(new Double (2));
           tree.insert(new Double(14));
           tree.insert(new Double (3));
           tree.insert(new Double (6));
           tree.insert(new Double (8));
           tree.insert(new Double (6));
           tree.insert(new Double  (16));
           tree.insert(new Double (30));
           tree.insert(new Double  (15));
           tree.insert(new Double (11));
           
           logger.info(" -----------------Print tree---------------" );
           tree.printTree();
           
           logger.info(" -----------------26Print post order ---------------" );
           tree.printPostorder();
           
           logger.info(" -----------------Print structure  tree ---------------" );
           tree.displayTree();
//           tree.printStructure();
          logger.info(" -----------------Print  paths  tree ---------------" );
//           tree.printPaths();
     //     tree.levelOrder();
	}

}
