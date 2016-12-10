package node;

import java.util.LinkedList;

import suffixtree.AbsSuffixTree;
import suffixtree.SuffixTree;

public class Main {

	public static void main(String[] args) {	
		SuffixTree st = new SuffixTree();		
		//st = st.ukkonen("xyzxyaxyz$");    
		st = (SuffixTree)st.ukkonen("abcabxabcd");
		st.convertToReal();
		st.labelPrint();
		
		System.out.println(st.search("abcd", new LinkedList<Integer>(),st.getRoot()));	
		
		
	}

}
