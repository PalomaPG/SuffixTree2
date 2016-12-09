package node;

import suffixtree.*;

public class Root extends NotLeafNode {		
	
	public Root(AbsSuffixTree stree) {
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
