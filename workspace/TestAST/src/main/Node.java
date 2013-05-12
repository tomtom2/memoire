package main;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;


public class Node {

	public static int END_OF_PIPE=1;
	public static int START_OF_PIPE=2;
	
	protected int start;
	protected int end;
	protected int length;
	
	protected String type = "";
	protected String body = "";
	
	public Node(ASTNode astNode){
		start = astNode.getStartPosition();
		length = astNode.getLength();
		end = start + length;
		if(astNode instanceof IfStatement){
			findBody((IfStatement)astNode);
		}
	}
	
	public Node(){
		start = 0;
		length = 0;
		end = 0;
	}
	
	private void findBody(IfStatement node){
		body = node.getExpression().toString();
	}
	
	public String getBody(){
		return body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
	
	
}
