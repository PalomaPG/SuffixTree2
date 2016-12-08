package node;

import suffixtree.SuffixTree;

public abstract class Node {		   
	    
	    protected SuffixTree st;			
		
		private boolean notRegistered(char c) {
			// TODO Auto-generated method stub
			return false;
		}		
		
		// If the node is a root, returns root. Otherwise, returns the node of its suffixlink.
		public abstract NotLeafNode getInitialNode();	
		
		public abstract Object [] searchFinalArc(String beta, Arc last);
		
		public abstract void print();
		
	
}
