package node;

import suffixtree.SuffixTree;

public class Main {

	public static void main(String[] args) {		
		SuffixTree st = new SuffixTree();		
		//st = st.ukkonen("xyzxyaxyz$");    
		st = st.ukkonen("abcabxabcd");    
	}

}
