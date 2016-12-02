package node;

public class Arc {

	protected Node parent, child;
	protected String key;
	protected int position;
	
	
	public Arc(Node parent, Node child, String key){
		this.parent = parent;
		this.child = child;
		this.key = key;
	}
	
	public void extendKey(){
		
	}
	
	@Override
	public String toString(){
		return key;
	}
	
}