package com.github.tomtom2.model;

import com.github.tomtom2.model.visitor.ClassParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "/home/thomas/master/memoire/workspace/TestAST/src/node/Node.java";
		
		ObjectModel mod = ClassParser.parseFile(path);
		for(Function f : mod.getFunctions()){
        	System.out.println(f);
        }
		
		System.out.println("\nEND OF PGM");
//		System.out.println((new File("")).getAbsolutePath());
//		Dot.makeImageFromDot("test.dot", "test", "png");
//		System.out.println("OK...");
	}

}
