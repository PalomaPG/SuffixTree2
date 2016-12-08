package node;

import suffixtree.SuffixTree;

public class Leaf extends Node {

	protected int position;
	
	public Leaf(){
		position = -1;
	}
	
	public Leaf(int position, SuffixTree stree){
		this.position = position;
		st = stree;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position=position;
	}

	@Override
	public NotLeafNode getInitialNode() {		
		return null;
	}

	@Override
	public Object[] searchFinalArc(String beta, Arc last) {
		Object [] res = new Object[2];
		res[0] = last;
		res[1] = -2;	
		if (last != null) st.setGamma(last.getKey());		
		return res;					
	}

	@Override
	public void print() {	
		System.out.print("(" + position + ")");
	}
	
}
