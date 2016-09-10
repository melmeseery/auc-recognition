/**
 * 
 */
package util;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.log4j.Logger;

import classifiers.command.LibSvmCommandClassifier;

 

/**
 * @author maha
 *
 */
public class BinaryTreeWithKey   <T  , K extends Comparable >  {
	private static transient final Logger logger = Logger.getLogger( BinaryTreeWithKey .class);
 public static final  int POST_ORDER_ITERATTION=0;
     public static final  int PRE_ORDER_ITERATTION=1;
        public static final  int IN_ORDER=2;
         
         private  int sequence=0;
	/* 
	   --Node-- 
	   The binary tree is built using this nested node class. 
	   Each node stores one data element, and has left and right 
	   sub-tree pointer which may be null. 
	   The node is a "dumb" nested class -- we just use it for 
	   storage; it does not have any methods. 
	  */ 
	  private static class Node   <T   , K extends Comparable> implements Cloneable , Serializable{ 
	    Node left; 
	    Node right; 
	    T  data;
	   K  key;

	    Node(T  newData) { 
	      left = null; 
	      right = null; 
	      data = newData; 
	    }

		public Node(T data2, K key2) {
			  left = null; 
		      right = null; 
		      data =data2;
		      key=key2;
		      
		} 
	  }
	  Node root; 

		public BinaryTreeWithKey() {
			 root=null;
		}
		
		 /** 
		   Returns true if the given target is in the binary tree. 
		   Uses a recursive helper. 
		  */ 
		  public T find(K data) { 
		    return(find(root, data)); 
		  } 
		 

		  /** 
		   Recursive lookup  -- given a node, recur 
		   down searching for the given data. 
		  */ 
		  private T find(Node node, K data) { 
		    if (node==null) { 
		      return null; 
		    }

		    if (data.equals(node.key)) { 
		      return (T) node.data; 
		    } 
		    else {
		       if( data.compareTo( node.key ) < 0 )
		    	return    find( node.left,data );
		       else if( data.compareTo( node.key) > 0 )
		    		     return  find (node.right,data );
		    }
			 return null;
		     
		  } 
		  /** 
		   Inserts the given data into the binary tree. 
		   Uses a recursive helper. 
		  */ 
		  public void insert(T data, K key) { 
		    root = insert(root, data, key); 
		  } 
		 

		  /** 
		   Recursive insert -- given a node pointer, recur down and 
		   insert the given data into the tree. Returns the new 
		   node pointer (the standard way to communicate 
		   a changed pointer back to the caller). 
		  */ 
		  private Node insert(Node node, T data, K key) { 
		    if (node==null) { 
		      node = new Node(data,key); 
		    } 
		    else { 
		    	if (key.compareTo(node.key)<0){
		      //if (data <= node.data) { 
		        node.left = insert(node.left, data,key); 
		      } 
		      else { 
		        node.right = insert(node.right, data,key); 
		      } 
		    }

		    return(node); // in any case, return the new pointer to the caller 
		  } 
		  

	        /**
	         * Remove from the tree. Nothing is done if x is not found.
	         * @param x the item to remove.
	         */
	        public void remove( K  x )
	        {
	            root = remove( x, root );
	        }

	 
	      /**
	         * Internal method to remove from a subtree.
	         * @param x the item to remove.
	         * @param t the node that roots the tree.
	         * @return the new root.
	         */
	        private Node remove( K x, Node t )
	        {
	            if( t == null )
	                return t;   // Item not found; do nothing
	            if( x.compareTo( t.key) < 0 )
	                t.left = remove( x, t.left );
	            else if( x.compareTo( t.key ) > 0 )
	                t.right = remove( x, t.right );
	            else if( t.left != null && t.right != null ) // Two children
	            {
	                t.data = findMin( t.right ).data;
	                t.right = remove( (K) t.key, t.right );
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
logger.info(""); 
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
logger.info(""); 
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
public ArrayList<K>  keyList(){
	ArrayList<K> result = new ArrayList<K>();
    treeToKeyList(root, result);
    return result;
} 
private void treeToKeyList(Node<T,K> node, List<K> goal) {
    if (node != null) {
        treeToKeyList(node.left, goal);
        goal.add(node.key);
        treeToKeyList(node.right, goal);
    }
}
 public ArrayList<T> toList() {
	ArrayList<T> result = new ArrayList<T>();
    treeToList(root, result);
    return result;
}
private void treeToList(Node<T,K> node, List<T> goal) {
    if (node != null) {
        treeToList(node.left, goal);
        goal.add(node.data);
        treeToList(node.right, goal);
    }
}
public void writeTree(int dis, PrintStream out) {
	Stack globalStack = new Stack();
	globalStack.push(root);
	int nBlanks = 32;
	boolean isRowEmpty = false;
       out.println("......................................................");
       logger.info("......................................................");
	while(isRowEmpty==false)
	   {
	   Stack localStack = new Stack();
	   isRowEmpty = true;
	   for(int j=0; j<nBlanks; j++)
	       out.print(' ');
	   System.out.print(" ");
	   while(globalStack.isEmpty()==false)
	      {
	      Node temp = (Node)globalStack.pop();
	      if(temp != null)
	         {
	    	  if (dis==0){
	          out.print(temp.data+"  ");   
	          System.out.print(temp.data+"  ");}
	    	  else {
	    	 out.print(temp.key);
	    	 System. out.print(temp.key);}
	         localStack.push(temp.left);
	         localStack.push(temp.right);

	         if(temp.left != null ||
	                             temp.right != null)
	            isRowEmpty = false;
	         }
	      else
	         {
	         out.print("--");  
	         System. out.print("--");
	         localStack.push(null);
	         localStack.push(null);
	         }
	      for(int j=0; j<nBlanks*2-2; j++)
	        out.print(' '); 
	      System.out.print(' ');
	      }  // end while globalStack not empty
          out.println();
          System. out.println(); 
	   nBlanks /= 2;
	   while(localStack.isEmpty()==false)
	      globalStack.push( localStack.pop() );
	   }  // end while isRowEmpty is false
	 out.println(
	"......................................................");	 
	 logger.info(
	"......................................................");
	
}

public void displayTree(int dis)
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
    	  if (dis==0)
         System.out.print(temp.data);
    	  else 
    	 System.out.print(temp.key);
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

/**
 * Exception class for duplicate item errors
 * in search tree insertions.
 * @author Mark Allen Weiss
 */
//public class DuplicateItemException extends Exception {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 4588465719582132752L;
//	/**
//     * Construct this exception object.
//     */
//    public DuplicateItemException( ) {
//        super( );
//    }
//    /**
//     * Construct this exception object.
//     * @param message the error message.
//     */
//    public DuplicateItemException( String message ) {
//        super( message );
//    }
//}/**
// * Exception class for duplicate item errors
// * in search tree insertions.
// * @author Mark Allen Weiss
// */


public TreeIterator     Iterator( ){
	
	return new TreeIterator();
}

public class TreeIterator  implements Iterator<T> {
Stack<Node<T,K>> stack = null;

TreeIterator (){
	stack = new Stack<Node<T,K>> ();
	   if (root!=null)
	      stack.push(root);
	
}
   
@Override
public boolean hasNext() {
   return stack.isEmpty();
}
public T  peekLeft(){
	
	   Node<T,K>  current = stack.peek();
//	 // we add it's children on the stack
//	    if (current.left != null)
//	    {
//	        stack.push(current.left);
//	    }
//	
	return (T) current.left.data; 
}
public T getLeft(){
	  Node<T,K>  current = stack.pop();
//		 // we add it's children on the stack
	    if (current.left != null)
	    {
	        stack.push(current.left);
	    }
//	
	return (T) current.left.data;
	
}
public T peekRight(){
	   Node<T,K>  current = stack.peek();
	  
//		 // we add it's children on the stack
//		    if (current.right != null)
//		    {
//		        stack.push(current.right);
//		    }
//		
		return (T) current.right.data;
}
public T getRight(){
    // top of the stack
	  Node<T,K>  current = stack.pop();
	    if (current.right != null)
	    {
	        stack.push(current.right);
	    }
//	
	return (T) current.right.data;
}

@Override
public T next() {
	// the current element is the element from the
    // top of the stack
  Node<T,K>  current = stack.pop();

    // we add it's children on the stack
    if (current.left != null)
    {
        stack.push(current.left);
    }

    if (current.right != null)
    {
        stack.push(current.right);
    }

    // and we return the element to the iterator
    return current.data;
}

@Override
public void remove() {
	         
	
}
}
	public static void main(String[] args) {
//		         
//          Double x; 
//           BinaryTree<Double> tree=new BinaryTree<Double>();
//        
//           tree.insert(new Double (10));
//           tree.insert(new Double (2));
//           tree.insert(new Double(14));
//           tree.insert(new Double (3));
//           tree.insert(new Double (6));
//           tree.insert(new Double (8));
//           tree.insert(new Double (6));
//           tree.insert(new Double  (16));
//           tree.insert(new Double (30));
//           tree.insert(new Double  (15));
//           tree.insert(new Double (11));
//           
//           logger.info(" -----------------Print tree---------------" );
//           tree.printTree();
//           
//           logger.info(" -----------------26Print post order ---------------" );
//           tree.printPostorder();
//           
//           logger.info(" -----------------Print structure  tree ---------------" );
//           tree.displayTree();
////           tree.printStructure();
//          logger.info(" -----------------Print  paths  tree ---------------" );
////           tree.printPaths();
     //     tree.levelOrder();
	}




}
