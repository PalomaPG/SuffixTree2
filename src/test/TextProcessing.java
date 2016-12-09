package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import suffixtree.*;

public class TextProcessing {

	
	public static String readFile(String filename)
	{
	  StringBuilder records = new StringBuilder();
	  try
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	      records.append(line);
	    }
	    reader.close();
	    return records.toString();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	    return null;
	  }
	}
	
	public static void main(String [] args){
		
		String curr_dir = Paths.get(".").toAbsolutePath().normalize().toString();
		String rel_path = new StringBuilder(curr_dir).append("/input/t15.txt").toString();
		String text = readFile(rel_path);
		
		SuffixTree st = new SuffixTree();
		st.ukkonen(text);
		
	}
}
