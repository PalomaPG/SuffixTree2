package node;

public class Leaf extends Node {

	protected int position;
	
	public Leaf(){
		position = -1;
	}
	
	public Leaf(int position){
		this.position = position;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position=position;
	}
}
