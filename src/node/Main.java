package node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import suffixtree.AbsSuffixTree;
import suffixtree.SuffixTree;

public class Main {
	
	static String great = "";
	
	public static void read(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            great = great + cadena;
        }
        b.close();        
    }


	public static void main(String[] args) throws FileNotFoundException, IOException {	
		String word = "theprojectgutenbergebookof";
		String text = word.concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word);		
		String text2 = text.concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).
				       concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).
				       concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text);
		SuffixTree st = new SuffixTree();	
		
		String path = "C:/Users/natto/Downloads/tt15.txt";
		
		//st = st.ukkonen("xyzxyaxyz$");    
		//st = (SuffixTree)st.ukkonen("abcabxabcd");
		//st = (SuffixTree)st.ukkonen(text2); 
		
		read(path);
		st = (SuffixTree)st.ukkonen(great); 
		
		st.convertToReal();
		//st.labelPrint();
		
		System.out.println(st.search("abcd", new LinkedList<Integer>(),st.getRoot()));	
		
		System.out.println(great.length());
		
	}

}
