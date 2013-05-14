package main;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;


public class Node {
	
	protected int start;
	protected int end;
	protected int length;
	
	protected String type = "";
	protected String body = "";
	protected Node child;
	protected Node parent;
	
	
	public Node(ASTNode astNode){
		start = astNode.getStartPosition();
		length = astNode.getLength();
		end = start + length;
		
		child = null;
		parent = null;
	}
	
	public Node(){
		start = 0;
		length = 0;
		end = 0;
		
		child = null;
		parent = null;
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

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	public boolean equals(Node node){
		return this.start==node.start && this.end==node.end;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean hasChild() {
		return this.getChild()!=null;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	
}
