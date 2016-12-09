package node;

import java.util.HashMap;
import java.util.LinkedList;

import suffixtree.*;

public abstract class Node {		   
	    
	    protected AbsSuffixTree st;			
		protected int position;	
		
		// If the node is a root, returns root. Otherwise, returns the node of its suffixlink.
		public abstract NotLeafNode getInitialNode();	
		public abstract HashMap<Character, Arc> getChildren();
		
		public abstract Object [] searchFinalArc(String beta, Arc last);
		
		public abstract int  getPosition();
		
		public abstract void print();

		public abstract void getLeavesValues(LinkedList<Integer> positions);
		
		
	
}
