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
	public void extensionByRules(int i, int j, NotLeafNode ini, String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root) {
		// TODO Auto-generated method stub
		return null;
	}

}
