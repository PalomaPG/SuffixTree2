package node;

import suffixtree.SuffixTree;

public class InnerNode extends NotLeafNode {

	protected SuffixLink sl;		
	
	
	public InnerNode(SuffixTree stree){
		st = stree;	
		position = -1;
		st.counter_by_phase[st.fase] += 2;
	}	
	

	public void setSuffixLink(SuffixLink sl){
		st.counter_by_phase[st.fase]++;
		this.sl = sl;
	}
	
	@Override	
	public NotLeafNode getInitialNode() {
		st.counter_by_phase[st.fase]++;
		return sl.node;
	}

	public boolean equals(Object o){
		st.counter_by_phase[st.fase]++;
		if (this.getClass() != o.getClass()) return false;
		else{
			InnerNode n = (InnerNode)o;
			st.counter_by_phase[st.fase]++;
			return this.children.equals(n.children) && this.sl.equals(n.sl);
		}
	}
	
	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		st.counter_by_phase[st.fase]++;
		return position;
	}
	
}
