package suffixtree;
import java.util.LinkedList;

import node.*;

public abstract class AbsSuffixTree {
	//protected int num = 0; // Contador para etiquetar los nodos (s�lo para fines de impresi�n)

	protected Root root;
	protected String text; // String del cual se sacar�n los sufijos
	protected String gamma = "";
	protected NotLeafNode v;
	protected int count_w; 
	protected InnerNode w;
	protected NotLeafNode last; // �ltimo nodo que se recorri�
	protected int [] counter_by_phase;
	
	public Root getRoot(){
		return this.root;
	}
	
	public void setRoot(Root root) {
		this.root = root;
	}
	
	public void setV(NotLeafNode n) {
		v = n;
	}
	
	public void setGamma(String g) {
		gamma = g;
	}
	
	public void print() {
		root.print();
	}
		
	public AbsSuffixTree ukkonen(String s) {
		
		counter_by_phase = new int [s.length()-1];
		
		for(int i=0; i<s.length()-1; i++)
			counter_by_phase[i]=0;
		
		//root.setName(num);
		text = s;
		root.addChild(s.charAt(0), new Arc(root, new Leaf(0, this), s.charAt(0)));		
		//print();
		//System.out.println();
		for (int i = 1; i < s.length(); i ++) {
			//fase i	
			//System.out.println();
			//System.out.println("fase " + i);			
			w = null;
			v = root;
			for (int j = 0; j <= i; j ++) {		
				//System.out.println();
				//System.out.println();
				//System.out.println("j = " + j);				
				extension(i, j);
				//System.out.println("v : " + v.getName());
				//if (w != null) System.out.println("w : " + w.getName());
				//else System.out.println("w : null");				
				//print();
				//System.out.println();
			}			
		}
		return this;
	}
	
	public void extension(int i, int j) {			
		//Extensi�n normal
		if (j == 0) {			
			extensionByRules(i, j, root, text);					
		}
		//Extensi�n con suffix links
		else {			
			NotLeafNode ini;				
			ini = v.getInitialNode();			
			//System.out.println("gamma= " + gamma);			
			if (v instanceof Root) extensionByRules(i, j, ini, text);
			else {								
				//System.out.println("Se recorre desde " + ini.getName() + " por " + gamma);				
				extensionByRules(i, j, ini, gamma);					
			}
		}		
		// Si se sigui� la regla 2.2 en la extensi�n anterior
		if (w != null) {
			if (count_w == 1) {
				//System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + last.getName());				
				w.setSuffixLink(new SuffixLink(last));
				w = null;
			}
			else count_w = 1;						
		}		
	}
	
	public abstract void extensionByRules(int i, int j, NotLeafNode ini, String s);
	
	public abstract LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root);
}
