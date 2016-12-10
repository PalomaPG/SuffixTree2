package suffixtree;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import node.*;

public abstract class AbsSuffixTree {
	protected Root root;
	protected String text; // String del cual se sacar�n los sufijos
	protected int num = 0; // Contador para etiquetar los nodos (s�lo para fines de impresi�n)
	protected NotLeafNode v;
	protected String gamma = "";
	protected InnerNode w;
	//count_w = 0: w reci�n fue creado; count_w = 1: w fue creado en la extensi�n anterior, por lo tanto, se le asigna un SuffixLink
	protected int count_w; 	
	protected NotLeafNode last; // �ltimo nodo que se recorri�
	protected int jL;
	protected int [] counter_by_phase;
	
	public AbsSuffixTree(){
		root = new Root(this);	
	}	
	

	public String getText() {
		return text;
	}	
	
	
	public Root getRoot() {
		return root;
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
	
	public void labelPrint() {
		root.labelPrint();
	}
	
	public SuffixTree convertToReal() {
		int end = text.length() - 1;
		root.replaceEnd(end);
		return null;
	}
	
	public AbsSuffixTree ukkonen(String s) {
		counter_by_phase = new int [s.length()];
		root.setName(num);
		text = s.concat("$");
		root.addChild(new Arc(root, new Leaf(0, this), 0, -1));		// -1 = END
		//labelPrint();
		//System.out.println();
		int finish;
		for (int i = 1; i < text.length(); i ++) {
			//fase i	
			//System.out.println();
			//System.out.println("fase " + i);			
			w = null;
			v = root;
			for (int j = 0; j <= i; j ++) {		
				/*System.out.println();
				System.out.println();
				System.out.println("j = " + j);	*/			
				finish = extension(i, j, counter_by_phase[i-1]);
				/*System.out.println("v : " + v.getName());
				if (w != null) System.out.println("w : " + w.getName());
				else System.out.println("w : null");			
				labelPrint();
				System.out.println();*/
				
				if (finish == 1){
					
					break;
				}
			}			
		}
		return this;
	}
	
	
	public int extension(int i, int j, int phase_counter) {	
		//extensionByRules(i, j, root, text);	
		//Extensi�n normal
		int finish;
		if (j == 0) {
			finish = extensionByRules(i, j, root, text);					
		}
		//Extensi�n con suffix links
		else {			
			NotLeafNode ini;				
			ini = v.getInitialNode();			
			//System.out.println("gamma= " + gamma);			
			if (v instanceof Root) finish = extensionByRules(i, j, ini, text);
			else {								
				//System.out.println("Se recorre desde " + ini.getName() + " por " + gamma);				
				finish = extensionByRules(i, j, ini, gamma);					
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
		
		return finish;
	}
	
	public void printST(Node n){
		
		if(n instanceof Leaf) return;
		
		else{
			HashMap<Character, Arc> children_= n.getChildren();
			Iterator<Entry<Character, Arc>> it = children_.entrySet().iterator();
			
			try{
			    PrintWriter writer = new PrintWriter("output/tree-printing.txt", "UTF-8");
			    while(it.hasNext()){
			    	Entry<Character, Arc> pair = it.next();
			    	writer.print("Key character:");
			    	writer.println(pair.getKey());
			    	writer.println("Edge label:");
			    	writer.println(pair.getValue().getKey());
			    	printST(pair.getValue().getChild());
			    	//System.out.println(pair.getValue().getKey());
			    	}
			    writer.close();
				} catch (IOException e) {
				   // do something
				}
		}
		
	}
	
	public abstract int extensionByRules(int i, int j, NotLeafNode ini, String s);
	
	public abstract LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root, String text);
}