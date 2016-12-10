package node;

import suffixtree.AbsSuffixTree;

public class Root extends NotLeafNode {			
	
	public Root(AbsSuffixTree absSuffixTree) {
		st = absSuffixTree;
		position = -1;
		//st.counter_by_phase[st.fase] += 3;
	}		
	
	public Root(){
		position = -1;
		st.counter_by_phase[st.fase] += 2;
	}
	
	public NotLeafNode getInitialNode() {
		st.counter_by_phase[st.fase]++;
		return this;
	}	
	
	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		st.counter_by_phase[st.fase]++;
		return position;
	}

	
	
	
}
