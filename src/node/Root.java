package node;

import suffixtree.AbsSuffixTree;
import suffixtree.SuffixTree;

public class Root extends NotLeafNode {		
	
	
	
	public Root(AbsSuffixTree absSuffixTree) {
		st = absSuffixTree;
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
