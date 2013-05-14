package node;

import main.Node;

import org.eclipse.jdt.core.dom.ASTNode;

public class ConditionalNode extends Node {
	
	private Node elseChild;

	public ConditionalNode(ASTNode node){
		super(node);
	}
	public ConditionalNode(){
		this.type = "else";
	}
	
	public Node getElseChild() {
		return elseChild;
	}

	public void setElseChild(Node elseChild) {
		this.elseChild = elseChild;
	}

}
