package node;

import java.util.LinkedList;

public class Arc {

	protected NotLeafNode parent;
	protected Node child;
	protected StringBuffer key;	
	
	public Arc(NotLeafNode parent, Node child, String key){
		this.parent = parent;
		this.child = child;
		this.key = new StringBuffer(key);
		//this.position = pos;
	}
	
	public Arc(NotLeafNode parent, Node child, char key){		
		this.parent = parent;
		this.child = child;
		this.key = new StringBuffer();
		this.key.append(key);
		//this.position = pos;		
	}
	
	public Node getChild() {
		return child;
	}
	
	public NotLeafNode getParent() {
		return parent;
	}
	
	public String getKey() {
		return key.toString();
	}
	
	public void extendKey(char c){
		key.append(c);
	}
	
	public boolean notInList(LinkedList<Arc> children) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}