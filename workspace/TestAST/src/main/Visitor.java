package main;

import java.util.List;

import model.NodeStore;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public class Visitor extends ASTVisitor {

	private CompilationUnit cu;
	private NodeStore store = new NodeStore();

	public Visitor(CompilationUnit cu) {
		this.cu = cu;
	}

	public boolean visit(MethodDeclaration node) {
		System.out.println("\n\n\nMethodDeclaration : " + node.getName());
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(("public class A{" + node.getBody().toString() + "}")
				.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new InsideMethodVisitor(store));
		System.out.println(node.parameters() + " --> " + node.getReturnType2()
				+ "\n");
		return true;
	}

	
	public NodeStore getStore() {
		return store;
	}

	public void setStore(NodeStore store) {
		this.store = store;
	}
}
