package node;

import java.util.LinkedList;

public abstract class Node {

		protected LinkedList<InnerNode> children;
		
		public Node(){
			
			children = new LinkedList<InnerNode>();
		}
	
}
