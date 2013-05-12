package main;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;


public class InsideMethodVisitor extends ASTVisitor {

	
	public boolean visit(Block node) {
		System.out.println("start = "+node.getStartPosition()+"\tlength = "+node.getLength());
		printTree(node, 0);
		return true;
	}
	
	public boolean visit(ExpressionStatement node) {
		System.out.println("ExpressionStatement Exp: " + node.getExpression());
		return true;
	}
//	public boolean visit(ASTNode node) {
//		System.out.println("ASTNode: " + node);
//		return true;
//	}
//	
	public boolean visit(ConditionalExpression node) {
		System.out.println("ConditionalExpression: " + node);
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

		
//		if(node instanceof ExpressionStatement){
//			System.out.println(Tab+"ExpressionStatement : "+node.toString().replace("\n", "\n"+Tab));
//		}
		if(node instanceof Block){
			List<ASTNode> blkList = ((Block)node).statements();
			for(ASTNode blk : blkList){
				System.out.println("new Block");
				System.out.println("start = "+node.getStartPosition()+"\tlength = "+node.getLength());
				if(blk instanceof ExpressionStatement){
					System.out.println(Tab+((ExpressionStatement)blk));
				}
				if(node instanceof ConditionalExpression){
					System.out.println(Tab+"ConditionalExpression : "+node.toString().replace("\n", "\n"+Tab));
				}
				printTree(blk, tab+1);
			}
		}
	}	
}
