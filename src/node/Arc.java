package node;

import java.util.LinkedList;

public class Arc {

	protected NotLeafNode parent;
	protected Node child;
	protected StringBuffer key;
	protected int start_index, end_index;
	
	public Arc(NotLeafNode parent, Node child, String key){
		this.parent = parent;
		this.child = child;
		this.key = new StringBuffer(key);
		//this.position = pos;
	}
	
	public Arc(NotLeafNode parent, Node child, int start_index, int end_index){
		
		this.parent = parent;
		this.child = child;
		this.start_index = start_index;
		this.end_index = end_index;
		
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
	
	public String getKeyByIndex(String text){
		return text.substring(start_index, end_index+1);
	}
	
	public void setStartIndex(int start_index){
		this.start_index=start_index;
	}
	
	public void setEndIndex(int end_index){
		this.end_index=end_index;
	}
	
	public int getStartIndex(){
		return start_index;
	}
	
	public int getEndIndex(){
		return end_index;
	}
	
	public String toString(){
		return key.toString();
	}
}