package node;

import suffixtree.SuffixTree;

public class InnerNode extends NotLeafNode {

	protected SuffixLink sl;	
	
	public InnerNode(SuffixTree stree){
		st = stree;	
	}	
	

	public void setSuffixLink(SuffixLink sl){
		this.sl = sl;
	}
	
	@Override	
	public NotLeafNode getInitialNode() {
		return sl.node;
	}

	public boolean equals(Object o){
		if (this.getClass() != o.getClass()) return false;
		else{
			InnerNode n = (InnerNode)o;
			return this.children.equals(n.children) && this.sl.equals(n.sl);
		}
	}
	
}
