package com.github.tomtom2.model.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.tomtom2.model.Function;
import com.github.tomtom2.model.NodeStore;

public class Visitor extends ASTVisitor {

	private ArrayList<Function> functions = new ArrayList<Function>();
    

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

}
