package node;


import org.eclipse.jdt.core.dom.IfStatement;

public class ConditionalNode extends Node {
	
	private Node elseChild = null;

	public ConditionalNode(IfStatement node){
		super(node);
		child = new Node(node.getThenStatement());
		child.setBody("tmp_if:"+node.getExpression());
		child.setType("tmp");
		child.setParent(this);
		if(node.getElseStatement()!=null){
			elseChild = new Node(node.getElseStatement());
			elseChild.setBody("tmp_else:"+node.getExpression());
			elseChild.setType("tmp");
			elseChild.setParent(this);
		}
	}
	public ConditionalNode(){
		this.type = "ELSE";
	}
	
	public Node getElseChild() {
		return elseChild;
	}

	public void setElseChild(Node elseChild) {
		this.elseChild = elseChild;
	}

}
