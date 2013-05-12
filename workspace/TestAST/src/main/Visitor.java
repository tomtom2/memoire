package main;
import java.util.List;

import model.PipeStore;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public class Visitor extends ASTVisitor {

	private CompilationUnit cu;
	private PipeStore statementsList = new PipeStore();

	public Visitor(CompilationUnit cu) {
		this.cu = cu;
	}

	public boolean visit(MethodDeclaration node) {
		System.out.println("\n\n\nMethodDeclaration : " + node.getName());
		// ASTParser parser = ASTParser.newParser(AST.JLS4);
		// parser.setSource(("public class A{"+node.getBody().toString()+"}").toCharArray());
		// parser.setKind(ASTParser.K_COMPILATION_UNIT);
		// final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		// cu.accept(new InsideMethodVisitor());
		System.out.println(node.parameters() + " --> " + node.getReturnType2()
				+ "\n");
		return true;
	}

	// public boolean visit(VariableDeclarationFragment node) {
	// System.out.println("VariableDeclarationFragment "+node);
	// return false; // do not continue to avoid usage info
	// }
	// public boolean visit(VariableDeclaration node) {
	// System.out.println("VariableDeclaration "+node);
	// return false; // do not continue to avoid usage info
	// }
	// public boolean visit(VariableDeclarationExpression node) {
	// System.out.println("VariableDeclarationExpression: " + node);
	// return false;
	// }
	// public boolean visit(VariableDeclarationStatement node) {
	// System.out.println("VariableDeclaration: " + node);
	// return false;
	// }

	public boolean visit(IfStatement node) {
		// System.out.println("IfStatement: " + node.getExpression());
		// System.out.println("Then : "+node.getThenStatement());
		//System.out.println("Else : "+node.getElseStatement());
		statementsList.addNode(new Node(node));
		if (node.getElseStatement()==null) {
			return true;
		}
		if (node.getElseStatement().toString().startsWith("{")) {
			Node elseNode = new Node(node.getElseStatement());
			System.out.println(elseNode.getStart());
			elseNode.setType("else");
			statementsList.addNode(elseNode);
		}
		for(Object obj : getChildren(node)){
			System.out.println("child :"+obj);
			if(obj instanceof ASTNode){
				for(Object obj2 : getChildren((ASTNode)obj)){
					System.out.println("\tchild :"+obj2);
					if(obj2 instanceof ASTNode){
						for(Object obj3 : getChildren((ASTNode)obj2)){
							System.out.println("\t\tchild :"+obj3);
						}
					}
				}
			}
		}
		return true;
	}

	public PipeStore getStatementsList() {
		return statementsList;
	}

	public void setStatementsList(PipeStore statementsList) {
		this.statementsList = statementsList;
	}

	public boolean visit(org.eclipse.jdt.core.dom.ExpressionStatement node) {
//		System.out.println("ExpressionStatement Exp: " + node.getExpression());
//		System.out.print("parentStart : "
//				+ cu.getLineNumber(node.getParent().getStartPosition()));
//		System.out.println("\tstart : "
//				+ cu.getLineNumber(node.getStartPosition()));
		Node expNode = new Node(node);
		expNode.setType("exp");
		expNode.setBody(node.getExpression().toString());
		statementsList.addNode(expNode);
		return true;
	}
	//
	// public boolean visit(org.eclipse.jdt.core.dom.FieldAccess node) {
	// System.out.println("FieldAccess: " + node);
	// return true;
	// }
	
	
	public Object[] getChildren(ASTNode node) {
	    List list= node.structuralPropertiesForType();
	    for (int i= 0; i < list.size(); i++) {
	        StructuralPropertyDescriptor curr= (StructuralPropertyDescriptor) list.get(i);
	            Object child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                return ((List) child).toArray();
	        } else if (child instanceof ASTNode) {
	            return new Object[] { child };
	            }
	        return new Object[0];
	    }
	    return null;
	}
}
