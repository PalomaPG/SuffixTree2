package node;

public class SuffixLink {
		
	protected NotLeafNode node;
	
	public SuffixLink(NotLeafNode n){
		node = n;
		node.st.counter_by_phase[node.st.fase]++;
	}
}
