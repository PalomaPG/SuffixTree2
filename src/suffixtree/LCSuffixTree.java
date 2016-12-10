package suffixtree;

import java.util.LinkedList;

import node.Node;
import node.NotLeafNode;
import node.Root;

public class LCSuffixTree extends AbsSuffixTree{

	
	public LCSuffixTree(){
		root = new Root(this); 
		}	
	

	@Override
	public int extensionByRules(int i, int j, NotLeafNode ini, String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root) {
		// TODO Auto-generated method stub
		return null;
	}

}