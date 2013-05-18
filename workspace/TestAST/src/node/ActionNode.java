package node;

import org.eclipse.jdt.core.dom.ExpressionStatement;

import main.Node;

public class ActionNode extends Node {

	private ConditionalNode conditionalParent = null;

	public ActionNode(ExpressionStatement node){
		super(node);
		this.type = "EXP";
		this.body = node.getExpression().toString();
	}
	public ConditionalNode getConditionalParent() {
		return conditionalParent;
	}

	public void setConditionalParent(ConditionalNode conditionalParent) {
		this.conditionalParent = conditionalParent;
	}
	
	
}
