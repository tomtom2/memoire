package node;

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
	
	protected String shape = "";
	
	
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
	
	public boolean isBetterParentFor(Node parent, Node child){
		if(parent==null){
			return true;
		}
		int currentGap1 = child.start-this.start;
		int oldGap = child.start-parent.start;
		return currentGap1<=oldGap && currentGap1>=0;
	}
	
	public boolean isElseChild(){
		if(parent instanceof ConditionalNode){
			if(((ConditionalNode)parent).getElseChild()==null){
				return false;
			}
			else{
				return ((ConditionalNode)parent).getElseChild().equals(this);
			}
		}
		
		return false;
	}
	
	public Node isBetterContainedBy(Node bestContainer, Node newNode){
		if(!this.isContainedBy(newNode) || newNode.equals(this)){
			return bestContainer;
		}
		int gap1Start = -1;
		int gap1End = -1;
		int gap2Start = -1;
		int gap2End = -1;
		if(bestContainer!=null){
			gap1Start = start-bestContainer.getStart();
			gap1End = bestContainer.getEnd()-end;
		}
		if(newNode!=null){
			gap2Start = start-newNode.getStart();
			gap2End = newNode.getEnd()-end;
		}
		
		Node selectedParent = null;
		int fittingScore = -1;
		if(gap1Start>=0 && gap1End>=0){
			fittingScore = gap1Start + gap1End;
			selectedParent = bestContainer;
		}
		if(gap2Start>=0 && gap2End>=0 && fittingScore>=(gap2Start + gap2End)){
			fittingScore = gap2Start + gap2End;
			selectedParent = newNode;
		}
		
		if(fittingScore>=0){
			return selectedParent;
		}
		return null;
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
		if(node==null){
			return false;
		}
		return this.start==node.start && this.end==node.end && this.type==node.type;
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

	public boolean isContainedBy(Node node) {
		if(node == null){
			return false;
		}
		return start>=node.start && end<=node.end;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	
}
