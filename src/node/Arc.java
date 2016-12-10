package node;

import java.util.LinkedList;

public class Arc {

	protected NotLeafNode parent;
	protected Node child;
	//protected StringBuffer key;	
	protected int [] label;
	
	
	
	/***
	 * 
	 * @param p
	 * @param c
	 * @param start
	 * @param fin
	 */
	public Arc(NotLeafNode p, Node c, int start, int fin){		
			parent = p;
			child = c;
			label = new int[2];
			label[0] = start;
			label[1] = fin;		
	}
	
	public Node getChild() {
		return child;
	}
	
	public NotLeafNode getParent() {
		return parent;
	}
	
	public String getKey() {
		return child.st.getText().substring(label[0], label[1] + 1);
	}
	
	/*public void extendKey(char c){
		key.append(c);
	}*/
	
	public boolean notInList(LinkedList<Arc> children) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int [] getLabel() {
		return label;
	}
	
	public String getKeyByIndex(String text){
		return text.substring(label[0], label[1] + 1);
	}
	
	public void setStartIndex(int start_index){
		this.label[0] = start_index;
	}
	
	public void setEndIndex(int end_index){
		this.label[1]= end_index;
	}
	
	public int getStartIndex(){
		return label[0];
	}
	
	public int getEndIndex(){
		return label[1];
	}
	
	/*public String toString(){
		return child.st.getText().substring(label[0], label[1] + 1);
	}*/
	
}