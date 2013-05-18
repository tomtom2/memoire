package main;
import java.util.List;

import model.NodeStore;
import node.ActionNode;
import node.ConditionalNode;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;


public class InsideMethodVisitor extends ASTVisitor {

	private NodeStore store;
	
	public InsideMethodVisitor(NodeStore store){
		this.store = store;
	}
	
	public boolean visit(Block node) {
//		System.out.println("start = "+node.getStartPosition()+"\tlength = "+node.getLength());
//		printTree(node, 0);
		return true;
	}
	
	public boolean visit(ExpressionStatement node) {
		//System.out.println("ExpressionStatement Exp: " + node.getExpression());
		ActionNode actionNode = new ActionNode(node);
		System.out.println("adding action : ["+actionNode.getStart()+":"+actionNode.getEnd()+"]");
		store.addNode(actionNode);
		return true;
	}
//	public boolean visit(ASTNode node) {
//		System.out.println("ASTNode: " + node);
//		return true;
//	}
//	
	public boolean visit(IfStatement node) {
//		System.out.println("IfStatement: " + node.toString().substring(0, Math.min(15, node.toString().length())));
//		System.out.println("["+node.getStartPosition()+", "+(node.getStartPosition()+node.getLength())+"]");
//		System.out.println("create instance of ConditionalNode");
		System.out.println("Vivitor exp. : "+node.getExpression());
		ConditionalNode condNode = new ConditionalNode(node);
		condNode.setType("IF");
		condNode.setBody(node.getExpression().toString());
		store.addNode(condNode);
		if(node.getElseStatement()==null){
			return true;
		}
		String elseStrart = node.getElseStatement().toString().substring(0, Math.min(7, node.getElseStatement().toString().length()));
		if(elseStrart.startsWith("{")){
			ConditionalNode tmp = new ConditionalNode();
			tmp.setStart(node.getElseStatement().getStartPosition());
			tmp.setEnd(node.getElseStatement().getStartPosition()+node.getElseStatement().getLength());
			tmp.setBody("else:"+node.getExpression());
			store.addNode(tmp);
		}

		return true;
	}
//	
//	public boolean visit(VariableDeclarationExpression node) {
//		System.out.println("VariableDeclarationExpression: " + node);
//		return true;
//	}
	
	public void printTree(ASTNode node, int tab){
		String Tab = "";
		for(int i=0; i<tab; i++){
			Tab += "\t";
		}
		System.out.println(Tab+"blk : "+node.toString().substring(0, Math.min(15, node.toString().length())));

		
//		if(node instanceof ExpressionStatement){
//			System.out.println(Tab+"ExpressionStatement : "+node.toString().replace("\n", "\n"+Tab));
//		}
		if(node instanceof Block){
			List<ASTNode> blkList = ((Block)node).statements();
			for(ASTNode blk : blkList){
				//System.out.println("new Block");
				//System.out.println("start = "+node.getStartPosition()+"\tlength = "+node.getLength());
				if(blk instanceof ExpressionStatement){
					//System.out.println(Tab+((ExpressionStatement)blk));
				}
				if(node instanceof ConditionalExpression){
					//System.out.println(Tab+"ConditionalExpression : "+node.toString().replace("\n", "\n"+Tab));
				}
				printTree(blk, tab+1);
			}
		}
	}
	
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
