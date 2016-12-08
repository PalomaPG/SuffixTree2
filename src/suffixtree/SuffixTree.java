package suffixtree;
import java.util.HashMap;

import node.*;

public class SuffixTree {

	private Root root;
	protected String text; // String del cual se sacar�n los sufijos
	protected int num = 0; // Contador para etiquetar los nodos (s�lo para fines de impresi�n)
	protected NotLeafNode v;
	protected String gamma = "";
	protected InnerNode w;
	//count_w = 0: w reci�n fue creado; count_w = 1: w fue creado en la extensi�n anterior, por lo tanto, se le asigna un SuffixLink
	protected int count_w; 	
	protected NotLeafNode last; // �ltimo nodo que se recorri�

	public SuffixTree(){
		root = new Root(this);	
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
	
	public SuffixTree ukkonen(String s) {	
		root.setName(num);
		text = s;
		root.addChild(s.charAt(0), new Arc(root, new Leaf(0, this), s.charAt(0)));		
		print();
		System.out.println();
		for (int i = 1; i < s.length(); i ++) {
			//fase i	
			System.out.println();
			System.out.println("fase " + i);			
			w = null;
			v = root;
			for (int j = 0; j <= i; j ++) {		
				System.out.println();
				System.out.println();
				System.out.println("j = " + j);				
				extension(i, j);
				System.out.println("v : " + v.getName());
				if (w != null) System.out.println("w : " + w.getName());
				else System.out.println("w : null");				
				print();
				System.out.println();
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
			System.out.println("gamma= " + gamma);			
			if (v instanceof Root) extensionByRules(i, j, ini, text);
			else {								
				System.out.println("Se recorre desde " + ini.getName() + " por " + gamma);				
				extensionByRules(i, j, ini, gamma);					
			}
		}		
		// Si se sigui� la regla 2.2 en la extensi�n anterior
		if (w != null) {
			if (count_w == 1) {
				System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + last.getName());				
				w.setSuffixLink(new SuffixLink(last));
				w = null;
			}
			else count_w = 1;						
		}		
	}
	
	
	public void extensionByRules(int i, int j, NotLeafNode ini, String s) {		
		String beta;		
		if (j >= i) beta = "";
		else if (s.equals(text)) beta = s.substring(j, i);	
		else beta = s;			
		Object [] found = ini.searchFinalArc(beta, null);				
		Arc edge = (Arc)found[0];	
		int last_pos = (int)found[1];			
		
		System.out.println("beta : " + beta);		
		//Regla 1. Beta termina en una hoja
		if (last_pos == -2) {	
			System.out.println("beta termina en una hoja");			
			edge.extendKey(text.charAt(i));			
		}
		//// Beta era vac�o. Estamos en la ra�z � Regla 2.1 Beta termina en un nodo interno
		else if (last_pos == -1) {			
			NotLeafNode node;			
			if (edge == null || edge.getChild() instanceof Leaf) {
				System.out.println("beta era vac�o");					
				node = ini; 
				last = ini;
			}
			else {
				System.out.println("beta termina en un nodo interno");
				node = (NotLeafNode)edge.getChild();
				last = edge.getParent();	
			}
			HashMap<Character, Arc> chldrn = node.getChildren();			
			if (chldrn.containsKey(text.charAt(i))) {
				// Regla 3. Si existe un camino que comienza con s[i]. No se hace nada
				System.out.println("No se hace nada");	
			}
			// Si no existe un camino que comienza con s[i], se crea un nuevo arco
			else {
				System.out.println("Se crea nuevo arco");
				Arc nuevo = new Arc(node, new Leaf(j, this), text.charAt(i));
				node.addChild(text.charAt(i), nuevo);					
			}					
		}
		// Regla 2.2 Beta se termina a mitad de un arco
		else {	
				
			System.out.println("beta termina en mitad de un arco");			
			if (edge.getKey().charAt(last_pos) == text.charAt(i)) {
				System.out.println("No se hace nada");
				last = edge.getParent();	
			}
			else {
				System.out.println("Se crea nodo interno");
				NotLeafNode parent = edge.getParent();	
				
				// Se crea un nodo interno.
				InnerNode new_node = new InnerNode(this);
				new_node.setName(++num);
				
				// Se elimina el arco actual
				parent.removeChild(edge);	
				
				// Se crea nuevo arco con el key del arco actual, pero s�lo hasta donde lleg� beta
				Arc beta_edge = new Arc(parent, new_node, edge.getKey().substring(0, last_pos));							
				parent.addChild(edge.getKey().charAt(0), beta_edge);				
				
				// Se crea nuevo arco con el key del arco actual con la parte que no se recorri�
				Arc rest = new Arc(new_node, edge.getChild(), edge.getKey().substring(last_pos));
				new_node.addChild(edge.getKey().charAt(last_pos), rest);		
				
				// Se crea otro arco, hijo del nuevo nodo interno, con la letra que se agrega en esta fase
				Arc new_edge = new Arc(new_node, new Leaf(j, this), text.charAt(i));
				new_node.addChild(text.charAt(i), new_edge);
				
				// El nuevo nodo parte con un suffix link apuntando a la ra�z
				new_node.setSuffixLink(new SuffixLink(root));
				
				last = new_node;
				
				// Si se sigui� la regla 2.2 en la extensi�n anterior
				if (w != null) {
					System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + new_node.getName());					
					w.setSuffixLink(new SuffixLink(new_node));					
				}				
				w = new_node;				
				count_w = 0;				
			}			
		}			
	}
	
}