package node;

import suffixtree.SuffixTree;

public class Root extends NotLeafNode {		
	
	public Root(SuffixTree stree) {
		st = stree;
	}		
	
	public NotLeafNode getInitialNode() {
		return this;
	}	
	
	
}
