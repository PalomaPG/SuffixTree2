package suffixtree;
import node.*;

public class SuffixTree {

	private Root root;

	public SuffixTree(){
		root = new Root();
	}
	
	
	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}
	
}
