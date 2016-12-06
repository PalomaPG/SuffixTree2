package node;

import java.util.HashMap;


public abstract class Node {

		protected HashMap<Character, Arc> children;
		
		public Node(){
			
			children = new HashMap<Character, Arc>();
		}
	
		private boolean notRegistered(char c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		public void addChild(char c,Arc e){
			
	
			
		}
		
		
}
