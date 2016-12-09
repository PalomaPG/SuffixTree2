package suffixtree;
import java.util.HashMap;
import java.util.LinkedList;

import node.*;

public class SuffixTree extends AbsSuffixTree{
		
	//count_w = 0: w reci�n fue creado; count_w = 1: w fue creado en la extensi�n anterior, por lo tanto, se le asigna un SuffixLink
		
	public SuffixTree(){
		root = new Root(this);	
	}	
	
	public void extensionByRules(int i, int j, NotLeafNode ini, String s) {
		
		String beta;		
		if (j >= i) beta = "";
		else if (s.equals(text)) beta = s.substring(j, i);	
		else beta = s;			
		Object [] found = ini.searchFinalArc(beta, null);				
		Arc edge = (Arc)found[0];	
		int last_pos = (int)found[1];			
		
		//System.out.println("beta : " + beta);		
		//Regla 1. Beta termina en una hoja
		if (last_pos == -2) {	
			//System.out.println("beta termina en una hoja");			
			edge.extendKey(text.charAt(i));			
		}
		//// Beta era vac�o. Estamos en la ra�z � Regla 2.1 Beta termina en un nodo interno
		else if (last_pos == -1) {			
			NotLeafNode node;			
			if (edge == null || edge.getChild() instanceof Leaf) {
				//System.out.println("beta era vac�o");					
				node = ini; 
				last = ini;
			}
			else {
				//System.out.println("beta termina en un nodo interno");
				node = (NotLeafNode)edge.getChild();
				last = edge.getParent();	
			}
			HashMap<Character, Arc> chldrn = node.getChildren();			
			if (chldrn.containsKey(text.charAt(i))) {
				// Regla 3. Si existe un camino que comienza con s[i]. No se hace nada
				//System.out.println("No se hace nada");	
			}
			// Si no existe un camino que comienza con s[i], se crea un nuevo arco
			else {
				//System.out.println("Se crea nuevo arco");
				Arc nuevo = new Arc(node, new Leaf(j, this), text.charAt(i));
				node.addChild(text.charAt(i), nuevo);					
			}					
		}
		// Regla 2.2 Beta se termina a mitad de un arco
		else {	
				
			//System.out.println("beta termina en mitad de un arco");			
			if (edge.getKey().charAt(last_pos) == text.charAt(i)) {
				//System.out.println("No se hace nada");
				last = edge.getParent();	
			}
			else {
				//System.out.println("Se crea nodo interno");
				NotLeafNode parent = edge.getParent();	
				
				// Se crea un nodo interno.
				InnerNode new_node = new InnerNode(this);
				//new_node.setName(++num);
				
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
					//System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + new_node.getName());					
					w.setSuffixLink(new SuffixLink(new_node));					
				}				
				w = new_node;				
				count_w = 0;				
			}			
		}			
	}
	
	public LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root){
		
		HashMap<Character, Arc> children_ = root.getChildren();
		System.out.println(children_);
		Arc arc = children_.get(s.charAt(0));
		//System.out.println(arc);
		
		if (arc!= null){
			
			String arc_s = arc.toString();
			Node next = arc.getChild();
			
			if (s.equals(arc_s)){
				
				if(next.getPosition()>-1){
					positions.add(next.getPosition());
					return positions;
				}
				else {	
					next.getLeavesValues(positions);
					return positions;
				}
			}
			
			else{
				/*The string s is larger than the arc stored string*/
				if(s.length()>arc.getKey().length()){
					
					if(s.indexOf(arc.getKey())==0){
						String new_s = s.substring(arc.getKey().length(), s.length());
						return search(new_s, positions, next);
					}
					
					else return positions;
				}
				else{
					/*The string s is shorter than the arc stored string*/
					if(arc.getKey().toString().indexOf(s)==0){
						next.getLeavesValues(positions);
						return positions;
					}
					/*The string s is differs in at least one character*/
					else return positions;
				}		
			}
		}
				
		return positions;
	}
	
	public static void main (String [] args){
		
		SuffixTree st = new SuffixTree();
		Root root=st.getRoot();
		Arc arc1, arc2, arc3, arc4, arc5, arc6, arc7, arc8, arc9, arc10, arc11;
		
		root = new Root();
		
		arc1= new Arc(root, new Leaf(8), "$");
		arc2= new Arc(root, new InnerNode(st), "A");
		arc3= new Arc(root, new Leaf(6), "CA$");
		arc4= new Arc(root, new InnerNode(st), "GA");
		arc5= new Arc(root, new Leaf(2), "TAGACA$");
		
		System.out.println(arc5);
		root.addChild('$', arc1);
		root.addChild('A', arc2);
		root.addChild('C', arc3);
		root.addChild('G', arc4);
		root.addChild('T', arc5);
		System.out.println(root.getChildren());

		arc6 = new Arc((InnerNode)arc2.getChild(), new Leaf(7), "$");
		arc7 = new Arc((InnerNode)arc2.getChild(), new Leaf(5), "CA$");
		arc8 = new Arc((InnerNode)arc2.getChild(), new Leaf(3), "GACA$");
		arc9 = new Arc((InnerNode)arc2.getChild(), new Leaf(1), "TAGACA$");
		
		
		
		((NotLeafNode)arc2.getChild()).addChild('$', arc6);
		((NotLeafNode)arc2.getChild()).addChild('C', arc7);
		((NotLeafNode)arc2.getChild()).addChild('G', arc8);
		((NotLeafNode)arc2.getChild()).addChild('T', arc9);
		System.out.println(((InnerNode)arc2.getChild()).getChildren());
		
		arc10 = new Arc((NotLeafNode)arc4.getChild(), new Leaf(4), "CA$");
		arc11 = new Arc((NotLeafNode)arc4.getChild(), new Leaf(0), "TAGACA$");
		
		
		((NotLeafNode)arc4.getChild()).addChild('C', arc10);
		((NotLeafNode)arc4.getChild()).addChild('T', arc11);
		System.out.println(((InnerNode)arc4.getChild()).getChildren());
		st.setRoot(root);
		
		System.out.println(st.search("A", new LinkedList<Integer>(), root));
		System.out.println(st.search("GA", new LinkedList<Integer>(), root));
		System.out.println(st.search("$", new LinkedList<Integer>(), root));
		System.out.println(st.getRoot().getChildren());
		System.out.println(root.getChildren());
	}
	
}
