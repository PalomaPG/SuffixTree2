package node;

import java.util.LinkedList;

public class InnerNode extends Node {

	protected SuffixLink sl;
	
	public InnerNode(){
		
		super();
		
	}
	
	public void addChild(InnerNode n){
		
		if (n.notInList(this.children)){
			this.children.add(n);
		}
		
	}
	
	private boolean notInList(LinkedList<InnerNode> children) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setSuffixLink(SuffixLink sl){
		this.sl = sl;
	}

	public boolean equals(Object o){
		if (this.getClass() != o.getClass()) return false;
		else{
			InnerNode n = (InnerNode)o;
			return this.children.equals(n.children) && this.sl.equals(n.sl);
		}
	}
}
