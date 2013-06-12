package com.github.tomtom2.model.visitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.tomtom2.model.ObjectModel;


public class ClassParser {


	public static ObjectModel parseFile(String path){
		//TODO
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String source = "";
		int c;
		try {
			while((c=in.read())!=-1){
				source += (char)c;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT); 
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		if(cu==null){
			System.out.println("no CompilationUnit");
			return null;
		}
		Visitor viz = new Visitor();
        cu.accept(viz);
        
        ObjectModel model = new ObjectModel(cu);
        model.setName(viz.getName());
        model.setComponents(viz.getImports());
        model.setFunctions(viz.getFunctions());
        
		return model;
	}
}
