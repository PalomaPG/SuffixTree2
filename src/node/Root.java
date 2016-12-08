package node;

import java.util.LinkedList;

import suffixtree.SuffixTree;

public class Root extends NotLeafNode {		
	
	public Root(SuffixTree stree) {
		st = stree;
		position = -1;
	}		
	
	public Root(){
		position = -1;
	}
	
	public NotLeafNode getInitialNode() {
		return this;
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}


	
	
	
}
