package com.github.tomtom2.model.visitor;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import com.github.tomtom2.model.NodeStore;
import com.github.tomtom2.model.node.ActionNode;
import com.github.tomtom2.model.node.ConditionalNode;


public class InsideMethodVisitor extends ASTVisitor {

	private NodeStore store;
	
	public InsideMethodVisitor(NodeStore store){
		this.store = store;
	}
	
	
	public boolean visit(ExpressionStatement node) {
		//System.out.println("expression : "+node.getExpression());
		ActionNode actionNode = new ActionNode(node);
		store.addNode(actionNode);
		return true;
	}
	public boolean visit(ReturnStatement node) {
		//System.out.println("Return : "+node.getExpression());
		ActionNode returnNode = new ActionNode(node);
		store.addNode(returnNode);
		return true;
	}
	
	public boolean visit(IfStatement node) {
		//System.out.println("if : "+node.getExpression());
		ConditionalNode condNode = new ConditionalNode(node);
		condNode.setType("IF");
		condNode.setBody(node.getExpression().toString());
		store.addNode(condNode);
		if(node.getElseStatement()==null){
			return true;
		}
		String elseStrart = node.getElseStatement().toString().substring(0, Math.min(15, node.getElseStatement().toString().length()));
		if(elseStrart.startsWith("{")){
			ConditionalNode tmp = new ConditionalNode();
			tmp.setStart(node.getElseStatement().getStartPosition());
			tmp.setEnd(node.getElseStatement().getStartPosition()+node.getElseStatement().getLength());
			tmp.setBody("else:"+node.getExpression());
			store.addNode(tmp);
		}
		if(elseStrart.startsWith("if")){
			if(node.getElseStatement() instanceof IfStatement){
				ConditionalNode tmp = new ConditionalNode((IfStatement) node.getElseStatement());
				tmp.setBody("else_if:"+((IfStatement) node.getElseStatement()).getExpression());
				
				store.addNode(tmp);
			}			
		}

		return true;
	}
	
	

	public NodeStore getStore() {
		return store;
	}

	public void setStore(NodeStore store) {
		this.store = store;
	}
	
	
}
