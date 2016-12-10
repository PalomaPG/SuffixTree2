package node;

import java.util.HashMap;
import java.util.LinkedList;

import suffixtree.AbsSuffixTree;
import suffixtree.SuffixTree;

public class Leaf extends Node {

	protected int position;
	
	public Leaf(){
		position = -1;
	}
	
		
	public Leaf(int position, AbsSuffixTree stree){
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
	public Object[] searchFinalArc(int fase, String beta, Arc last) {		
		Object [] res = new Object[2];
		res[0] = last;
		res[1] = -2;	
		if (last != null) st.setGamma(st.getText().substring(last.getLabel()[0], fase));		
		return res;					
	}

	@Override
	public void print() {	
		System.out.print("(" + position + ")");
	}
	
	public void labelPrint() {
		print();
	}
	
	@Override
	public void getLeavesValues(LinkedList<Integer> positions) {
		// TODO Auto-generated method stub
		positions.add(position);
	}
	
	public HashMap<Character, Arc> getChildren() {
		return null;
	}
	
}
