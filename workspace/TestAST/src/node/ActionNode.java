package node;

import main.Node;

public class ActionNode extends Node {

	private ConditionalNode conditionalParent = null;

	public ConditionalNode getConditionalParent() {
		return conditionalParent;
	}

	public void setConditionalParent(ConditionalNode conditionalParent) {
		this.conditionalParent = conditionalParent;
	}
	
	
}
