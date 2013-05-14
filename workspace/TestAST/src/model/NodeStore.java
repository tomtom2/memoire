package model;

import java.util.ArrayList;

import main.Node;
import node.ActionNode;
import node.ConditionalNode;

public class NodeStore {

	private ArrayList<Node> nodes = new ArrayList<Node>();

	public NodeStore() {

	}

	public void addNode(Node node) {
		System.out.println("adding : ["+node.getType()+"]");
		if (node instanceof ConditionalNode) {
			addNode((ConditionalNode) node);
			return;
		}
		else if (node instanceof ActionNode) {
			addNode((ActionNode) node);
			return;
		}
	}

	public void addNode(ConditionalNode node) {
		if(contains(node)){
			return;
		}
		if(nodes.isEmpty()){
			nodes.add(node);
			return;
		}
		makeParentLink(node);
		nodes.add(node);
	}

	private void makeParentLink(ConditionalNode node) {
		Node parent = findParent(node);
		if(parent==null){
			return;
		}
		if(!parent.hasChild()){
			parent.setChild(node);
			node.setParent(parent);
			System.out.println("create link : ["+parent.getType()+"] --> ["+node.getType()+"]");
		}
	}

	private boolean hasParent(Node node) {
		for (Node oldNode : nodes) {
			if (oldNode.getChild().equals(node)) {
				return true;
			}
		}
		return false;
	}

	private Node findParent(ConditionalNode node) {
		Node parent = null;
		for (Node oldNode : nodes) {
			if (node.getStart() > oldNode.getStart()
					&& node.getEnd() <= oldNode.getEnd()) {
				parent = oldNode;
				break;
			}
		}
		if(parent==null){
			return null;
		}
		while (parent.hasChild()) {
			if (node.getStart() > parent.getChild().getStart()
					&& node.getEnd() <= parent.getChild().getEnd()) {
				parent = parent.getChild();
			}
			else if(parent instanceof ConditionalNode){
				if(((ConditionalNode) parent).getElseChild()!=null){
					if(node.getStart() > ((ConditionalNode) parent).getElseChild().getStart()
					&& node.getEnd() <= ((ConditionalNode) parent).getElseChild().getEnd()){
						parent = ((ConditionalNode) parent).getElseChild();
					}
				}
			}
			else{
				break;
			}
		}
		return parent;
	}
	
	public boolean contains(Node node){
		for(Node containedNode : nodes){
			if(containedNode.equals(node)){
				return true;
			}
		}
		return false;
	}
	
	public void printLinks(){
		for(Node node : nodes){
			if(node instanceof ConditionalNode){
				if(node.hasChild()){
					System.out.println("IFlink : ["+node.getType()+"] --> ["+node.getChild().getType()+"]");
				}
				if(((ConditionalNode)node).getElseChild()!=null){
					System.out.println("ELSElink : ["+node.getType()+"] --> ["+((ConditionalNode) node).getElseChild().getType()+"]");
				}
			}
			else if(node.hasChild()){
				System.out.println("link : ["+node.getType()+"] --> ["+node.getChild().getType()+"]");
			}
		}
	}
}
