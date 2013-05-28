package com.github.tomtom2.model.node;

import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;


public class ActionNode extends Node {

	private ConditionalNode conditionalParent = null;

	public ActionNode(ExpressionStatement node){
		super(node);
		this.type = "EXP";
		this.body = node.getExpression().toString();
	}
	public ActionNode(ReturnStatement node) {
		// TODO Auto-generated constructor stub
		super(node);
		this.type = "RETURN";
		if(node.getExpression()==null){
			this.body = "return : null";
		}
		else{
			this.body = "return : "+node.getExpression().toString();
		}
		this.shape = "box";
	}
	public ConditionalNode getConditionalParent() {
		return conditionalParent;
	}

	public void setConditionalParent(ConditionalNode conditionalParent) {
		this.conditionalParent = conditionalParent;
	}
	
	
}
