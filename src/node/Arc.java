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
			parent.st.counter_by_phase[parent.st.fase] += 5;
			
	}
	
	public Node getChild() {
		parent.st.counter_by_phase[parent.st.fase]++;
		return child;		
	}
	
	public NotLeafNode getParent() {
		parent.st.counter_by_phase[parent.st.fase]++;
		return parent;
	}
	
	public String getKey() {
		parent.st.counter_by_phase[parent.st.fase]++;
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
		parent.st.counter_by_phase[parent.st.fase]++;
		return label;
	}
	
	public String getKeyByIndex(String text){
		parent.st.counter_by_phase[parent.st.fase] += 2;
		return text.substring(label[0], label[1]);
	}
	
	public void setStartIndex(int start_index){
		parent.st.counter_by_phase[parent.st.fase]++;
		this.label[0] = start_index;
	}
	
	public void setEndIndex(int end_index){
		parent.st.counter_by_phase[parent.st.fase]++;
		this.label[1]= end_index;
	}
	
	public int getStartIndex(){
		parent.st.counter_by_phase[parent.st.fase]++;
		return label[0];
	}
	
	public int getEndIndex(){
		parent.st.counter_by_phase[parent.st.fase]++;
		return label[1];
	}
	
	/*public String toString(){
		return child.st.getText().substring(label[0], label[1] + 1);
	}*/
	
}