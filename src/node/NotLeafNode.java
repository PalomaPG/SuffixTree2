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
    
    
    public void replaceEnd(int end) {
    	Arc chld;
    	for (Character start: children.keySet()) {
    		chld = children.get(start);
    		if (chld.getEndIndex() == -1) {
    			chld.setEndIndex(st.getText().length() - 1);
    		}
    		else {
    			((NotLeafNode)chld.getChild()).replaceEnd(end); 
    		}					
		}				
	}
    
    public boolean notRegistered(char c) {
		return children.get(c) == null;
	}
    
    
    public void addChild(Arc edge){
		children.put(st.getText().charAt(edge.getLabel()[0]), edge);			
	}
	
	public void removeChild(Arc edge) {
		children.remove(st.getText().charAt(edge.getLabel()[0]));
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
	 * position = -1: se terminó el recorrido en un nodo interno
	 * position = -2: se terminó el recorrido en una hoja
	 * position = p: se terminó el recorrido en medio del arco en la posición p de la llave de ese arco
	  */	 
	public Object [] searchFinalArc(int fase, String beta, Arc last) {
		Object [] res = new Object[2];	
		int g = beta.length();			
		
		// Se termina beta en un nodo interno
		if (beta.equals("")) {
			res[0] = last;
			res[1] = -1;
			st.setGamma(beta);
			return res;
		}
		
		st.setV(this);	
		Arc chld = null;
		// Buscamos el arco que siga el camino de beta
		HashMap<Character, Arc> chldrn = children;		
		if (chldrn.containsKey(beta.charAt(0))) {
			chld = chldrn.get(beta.charAt(0));
		}
		else { 
			res[0] = null;
			res[1] = -3;			
			return res;
		}
		
		int gg;
		int chld_start = chld.getLabel()[0];
		int chld_fin = chld.getLabel()[1];
		if (chld_fin == -1) gg = (fase - 1) - chld_start + 1;
		else gg = chld_fin - chld_start + 1;		
				
		// Se pasa al nodo siguiente directamente.
		if (gg <= g) {			
			return chld.getChild().searchFinalArc(fase, beta.substring(gg), chld);
		}
		
		// Beta termina en mitad de un arco
		else {
			res[0] = chld;
			res[1] = g;
			st.setGamma(beta);
			return res;
		}	 
	}
		
		
	
	
	

	@Override
	public abstract NotLeafNode getInitialNode();
	
	
	public void print() {
		System.out.println();
		System.out.println("Node " + name);
		System.out.print("( ");
		Arc chld;
		for (Character start: children.keySet()) {
			chld = children.get(start);
			if (chld.getLabel()[1] == -1) {
				System.out.print("[" + st.getText().substring(chld.getLabel()[0], st.getText().length()) + "] ");
			}
			else {
				System.out.print("[" + st.getText().substring(chld.getLabel()[0], chld.getLabel()[1] + 1) + "] ");
			}
		}					
		System.out.println(")");
		for (Character start: children.keySet()) {
			chld = children.get(start);
			chld.child.print();			
		}			
	}
	
		
	public void labelPrint() {
		System.out.println();
		System.out.println("Node " + name);
		System.out.print("( ");
		Arc chld;
		for (Character start: children.keySet()) {
			chld = children.get(start);
			System.out.print("[" + chld.getLabel()[0] + "," + chld.getLabel()[1] + "] ");
		}					
		System.out.println(")");
		for (Character start: children.keySet()) {
			chld = children.get(start);
			chld.child.labelPrint();			
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
