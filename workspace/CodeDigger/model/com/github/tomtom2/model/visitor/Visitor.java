package com.github.tomtom2.model.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.CompilationUnit;
//import org.eclipse.jdt.core.dom;

import com.github.tomtom2.model.Function;
import com.github.tomtom2.model.NodeStore;

public class Visitor extends ASTVisitor {

	private ArrayList<Function> functions = new ArrayList<Function>();
	private String name = "";
	private ArrayList<String> imports = new ArrayList<String>();
    
	
	public boolean visit(ImportDeclaration importDeclaration) {
		imports.add(importDeclaration.getName().toString());
		System.out.println("import : "+importDeclaration.getName().toString());
		return true;
	}
	
	public boolean visit(MethodDeclaration node) {
		System.out.println("visiting "+node.getName());
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(("public class A{" + node.getBody().toString() + "}")
				.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		InsideMethodVisitor insideVisitor = new InsideMethodVisitor(new NodeStore());
		cu.accept(insideVisitor);

		@SuppressWarnings("unchecked")
		Function function = new Function(node.getName().toString(), node.parameters());
		NodeStore store = insideVisitor.getStore();
		store.regularize();
		function.setGraph(store);
		functions.add(function);
		return true;
	}

	
	public ArrayList<Function> getFunctions() {
		return functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getImports() {
		return imports;
	}

	public void setImports(ArrayList<String> imports) {
		this.imports = imports;
	}

	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}

}
