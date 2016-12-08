package node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public abstract class NotLeafNode extends Node {
	
	int name;
    protected HashMap<Character, Arc> children;
    
    public NotLeafNode(){			
		children = new HashMap<Character, Arc>();
	}
    
    
	public boolean notRegistered(char c) {
		
		return children.get(c) == null;
	}
    
    public void addChild(char start, Arc edge){
		children.put(start, edge);			
	}
	
	public void removeChild(Arc edge) {
		children.remove(edge.getKey().charAt(0));
	}
    
    public void setName(int n) {
		name = n;
	}
	
	public int getName() {
		return name;
	}
	
	public HashMap<Character, Arc> getChildren(){
		return children;
	}
	
    
	
	/* [Arc, position] 
	 * position = -1: se termin� el recorrido en un nodo interno
	 * position = -2: se termin� el recorrido en una hoja
	 * position = p: se termin� el recorrido en medio del arco en la posici�n p de la llave de ese arco
	  */	 
	public Object [] searchFinalArc(String beta, Arc last) {
		Object [] res = new Object[2];			
		if (beta.equals("")) {
			res[0] = last;
			res[1] = -1;			
			st.setGamma("");
			return res;
		}		
		st.setV(this);
		HashMap<Character, Arc> chldrn = children;		
		Arc chld = null;
		// Buscamos el arco que siga el camino de beta
		if (chldrn.containsKey(beta.charAt(0))) {
			chld = chldrn.get(beta.charAt(0));
		}
		else {
			res[0] = null;
			res[1] = -3;			
			return res;
		}		
		// Seguimos el camino de beta
		for (int chr = 0; chr < chld.getKey().length(); chr ++) {
			//Beta termina en mitad del arco
			if (chr == beta.length()) {
				res[0] = chld;
				res[1] = chr;				
				st.setGamma(beta);
				return res;
			}
		}	
		return chld.child.searchFinalArc(beta.substring(chld.key.length()), chld);		
	}
	
	

	@Override
	public abstract NotLeafNode getInitialNode();
	
		
	public void print() {
		System.out.println();
		System.out.println("Node " + name);
		System.out.print("[ ");
		Arc chld;
		for (Character start: children.keySet()) {
			chld = children.get(start);
			System.out.print(chld.key.toString() + " ");
		}					
		System.out.println("]");
		for (Character start: children.keySet()) {
			chld = children.get(start);
			chld.child.print();			
		}			
	}
	
	@Override
	public void getLeavesValues(LinkedList<Integer> positions) {
		// TODO Auto-generated method stub
		Iterator<Entry<Character, Arc>> it = children.entrySet().iterator();
		while(it.hasNext()){
			Entry<Character, Arc> pair = it.next();
			pair.getValue().child.getLeavesValues(positions);
		}
		
		
	}
	

}
