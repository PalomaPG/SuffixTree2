package node;

import java.util.LinkedList;

public class InnerNode extends Node {

	protected SuffixLink sl;
	
	public InnerNode(){
		
		super();
		
	}
	
	
	public void setSuffixLink(SuffixLink sl){
		this.sl = sl;
	}

	public boolean equals(Object o){
		return false;
	}
}
