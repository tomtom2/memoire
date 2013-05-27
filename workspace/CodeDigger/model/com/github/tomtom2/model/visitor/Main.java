package com.github.tomtom2.model.visitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = "/home/thomas/master/memoire/workspace/ObjectGrapher/src/source/Solver.java";
		path = "/home/thomas/master/memoire/workspace/TestAST/src/main/Test.java";
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String source = "";
		int c;
		try {
			while((c=in.read())!=-1){
				source += (char)c;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		//parser.setSource("public class A { int i = 9;  \n int j; \n ArrayList<Integer> al = new ArrayList<Integer>();j=1000; if(j>i){al.add(j);}}".toCharArray());
		//parser.setSource("/*abc*/".toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		//ASTNode node = parser.createAST(null);
 
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		Visitor viz = new Visitor(cu);
        cu.accept(viz);
        
        System.out.println("\nWork on method body:");
		String methodBody = "public class A {int val = 0; if(this.a<arg1){ val = arg1; } if(val<arg2){ val += arg2; } else if(val==arg1){ System.out.println(\"LOL\"); } else{val += 1; } return val;}";
		ASTParser parserM = ASTParser.newParser(AST.JLS3);
		parserM.setSource(source.toCharArray());
		parserM.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cuM = (CompilationUnit) parserM.createAST(null);
        cuM.accept(new Visitor(cuM));
        
        viz.getStore().regularize();
        viz.getStore().makeGraph("/home/thomas/my_graph.dot");
        
        System.out.println("\n\nSUMMARY:");
        viz.getStore().printLinks();
	}
}
