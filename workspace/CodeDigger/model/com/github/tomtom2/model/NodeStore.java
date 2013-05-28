package com.github.tomtom2.model;

import java.util.ArrayList;

import com.github.tomtom2.model.node.ActionNode;
import com.github.tomtom2.model.node.ConditionalNode;
import com.github.tomtom2.model.node.Node;
import com.github.tomtom2.util.Dot;


public class NodeStore {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private Node start;
	private Node end;

	public NodeStore() {
		Node start = new Node();
		start.setBody("start");
		start.setType("start");
		start.setStart(0);
		start.setEnd(0);
		this.start = start;
		nodes.add(this.start);

		Node end = new Node();
		end.setBody("end");
		end.setType("end");
		end.setStart(-1);
		end.setEnd(-1);
		this.end = end;
		nodes.add(this.end);
	}

	public void addNode(Node node) {
		if (node instanceof ConditionalNode) {
			addNode((ConditionalNode) node);
			return;
		} else if (node instanceof ActionNode) {
			addNode((ActionNode) node);
			return;
		}
	}

	public void addNode(ConditionalNode node) {
		if (contains(node)) {
			return;
		}
		if (nodes.isEmpty()) {
			nodes.add(node);
			if (node.hasChild()) {
				nodes.add(node.getChild());
			}
			if (node.getElseChild() != null) {
				nodes.add(node.getElseChild());
			}
			return;
		}
		makeParentLink(node);
		nodes.add(node);
		if (node.hasChild()) {
			nodes.add(node.getChild());
		}
		if (node.getElseChild() != null) {
			nodes.add(node.getElseChild());
		}
	}

	public void addNode(ActionNode node) {
		if (contains(node)) {
			return;
		}
		if (nodes.isEmpty()) {
			nodes.add(node);
			return;
		}
		makeParentLink(node);
		nodes.add(node);
	}

	private void makeParentLink(ConditionalNode node) {
		Node parentContainer = findBestParentContainer(node);
		Node parent = this.findParent(node);

		if (parentContainer == null) {
			if (parent != null) {
				parent.setChild(node);
				node.setParent(parent);
			}
			return;
		}
		if (!parentContainer.equals(parent) && parent != null) {
			parent.setChild(node);
			node.setParent(parent);

			return;
		}
		if (!parentContainer.hasChild()) {
			parentContainer.setChild(node);
			node.setParent(parentContainer);
		} else {
			Node containedParent = null;
			if (parentContainer.getChild().getEnd() < node.getStart()) {
				containedParent = parentContainer.getChild();
			}
			while (containedParent.hasChild()) {
				if (containedParent.getChild().getEnd() < node.getStart()) {
					containedParent = parentContainer.getChild();
				} else {
					break;
				}
			}
			if (containedParent != null) {
				containedParent.setChild(node);
				node.setParent(containedParent);
			}
		}
	}

	private void makeParentLink(ActionNode node) {
		Node parentContainer = findBestParentContainer(node);
		if (parentContainer == null) {
			Node selectedNode = null;
			for (Node presentNode : nodes) {
				if (presentNode.isBetterParentFor(selectedNode, node)
						&& !(presentNode instanceof ConditionalNode)) {
					selectedNode = presentNode;
				}
			}
			if (selectedNode == null) {
				return;
			} else {
				selectedNode.setChild(node);
				node.setParent(selectedNode);
			}
			return;
		}
		if (!parentContainer.hasChild()) {
			parentContainer.setChild(node);
			node.setParent(parentContainer);
		} else {
			Node parent = parentContainer.getChild();
			while (parent.hasChild()
					&& parent.getChild().isBetterParentFor(parent, node)) {
				parent = parent.getChild();
			}
			parent.setChild(node);
			node.setParent(parent);
		}
	}

	private Node findBestParentContainer(Node node) {
		Node bestContainer = null;
		for (Node oldNode : nodes) {
			if (bestContainer == null && node.isContainedBy(oldNode)) {
				bestContainer = oldNode;
			} else {
				if (bestContainer != null) {
				}
				bestContainer = node
						.isBetterContainedBy(bestContainer, oldNode);
			}
		}

		return bestContainer;
	}

	public boolean hasParent(Node node) {
		for (Node oldNode : nodes) {
			if (oldNode.hasChild()) {
				if (oldNode.getChild().equals(node)) {
					return true;
				}
			}
		}
		return false;
	}

	private Node findParent(ConditionalNode node) {
		Node parent = null;
		parent = findBestParentContainer(node);

		if (parent == null) {
			for (Node oldNode : nodes) {
				if (oldNode.isBetterParentFor(parent, node)) {
					parent = oldNode;
				}
			}
			return parent;
		} else {
			Node lastParentTrial = new Node();
			while (!parent.equals(lastParentTrial) && parent.hasChild()) {
				lastParentTrial = parent;
				if (node.getStart() >= parent.getChild().getStart()
						&& node.getEnd() <= parent.getChild().getEnd()) {
					parent = parent.getChild();
				} else if (parent instanceof ConditionalNode) {
					if (((ConditionalNode) parent).getElseChild() != null) {
						if (node.getStart() >= ((ConditionalNode) parent)
								.getElseChild().getStart()
								&& node.getEnd() <= ((ConditionalNode) parent)
										.getElseChild().getEnd()) {
							parent = ((ConditionalNode) parent).getElseChild();
						}
					}
				} else if (parent instanceof ActionNode && parent.hasChild()) {
					if (((ConditionalNode) parent).getElseChild() != null) {
						if (node.getStart() > ((ConditionalNode) parent)
								.getElseChild().getStart()
								&& node.getEnd() <= ((ConditionalNode) parent)
										.getElseChild().getEnd()) {
							parent = ((ConditionalNode) parent).getElseChild();
						}
					}
				} else {
					break;
				}
			}
		}
		return parent;
	}

	public boolean contains(Node node) {
		if (node.getType().equals("") || node.getBody().equals("")) {
			return true;
		}
		for (Node containedNode : nodes) {
			if (containedNode.equals(node)) {
				return true;
			}
		}
		return false;
	}

	private void removeTemporaryNodeLinks(Node node) {
		if (node.getType().startsWith("tmp")) {
			if (node.isElseChild()) {
				((ConditionalNode) node.getParent()).setElseChild(node
						.getChild());
				node.getChild().setParent(node.getParent());
				node.setChild(null);
				node.setParent(null);
			} else {
				node.getParent().setChild(node.getChild());
				node.getChild().setParent(node.getParent());
				node.setChild(null);
				node.setParent(null);
			}
		}
	}
	private void removeTemporaryNodes() {
		ArrayList<Node> nodsToRemove = new ArrayList<Node>();
		for(Node n : nodes){
			if(n.getType().startsWith("tmp")){
				removeTemporaryNodeLinks(n);
				nodsToRemove.add(n);
			}
		}
		for(Node n : nodsToRemove){
			nodes.remove(n);
		}
	}
	
	private void removeElseNodeLinks(Node node){
		if(node instanceof ConditionalNode && node.getType().equals("ELSE")){
			if(node==null || node.getParent()==null || node.getChild()==null){
				return;
			}
			if(node.getParent() instanceof ConditionalNode){
				((ConditionalNode) node.getParent()).setElseChild(node.getChild());
				node.getChild().setParent(node.getParent());
				node.setChild(null);
				node.setParent(null);
				((ConditionalNode)node).setElseChild(null);
			}
		}
	}
	private void elseNodeHandling(){
		ArrayList<Node> nodsToRemove = new ArrayList<Node>();
		for(Node node : nodes){
			if(node.getType().equals("ELSE")){
				removeElseNodeLinks(node);
				nodsToRemove.add(node);
			}
		}
		for(Node n : nodsToRemove){
			nodes.remove(n);
		}
	}
	private void returnNodeHandling(){
		for(Node node : nodes){
			if(node.getType().equals("RETURN")){
				node.setChild(end);
			}
		}
	}

	private void setAnchor(ConditionalNode node) {
		// TODO - not robust -> depends on the insertion order
		Node potentialAncore = findAncoreOnIfBrancheOf(node);
		if (potentialAncore == null) {
			potentialAncore = findChildFor(node);
		} else {
			if (findLeafOnElseBrancheOf(node) == null) {
				node.setElseChild(potentialAncore);
			} else {
				findLeafOnElseBrancheOf(node).setChild(potentialAncore);
			}
			return;
		}
	}
	private void setAnchor(ActionNode node){
		if(node.hasChild()){
			return;
		}
		Node container = findBestParentContainer(node);
		if(container==null){
			return;
		}
		Node selectedAnchor = end;
		for(Node graphNode : nodes){
			if(graphNode.getStart()>node.getEnd() && (graphNode.getStart()<selectedAnchor.getStart() || selectedAnchor.equals(end)) && !graphNode.isContainedBy(container)){
				selectedAnchor = graphNode;
			}
		}
		node.setChild(selectedAnchor);
	}

	private Node findAncoreOnIfBrancheOf(ConditionalNode node) {
		Node ancore = node;
		while (ancore.hasChild()) {
			ancore = ancore.getChild();
			if (!ancore.isContainedBy(node)) {
				return ancore;
			}
		}
		return null;
	}

	private Node findLeafOnElseBrancheOf(ConditionalNode node) {
		Node leaf = node.getElseChild();
		if (leaf == null) {
			return null;
		}
		while (leaf.hasChild()) {
			leaf = leaf.getChild();
			if (leaf.isContainedBy(node)) {
				return leaf;
			}
		}
		return null;
	}

	private Node findChildFor(ConditionalNode node) {
		Node child = null;
		//TODO
		return child;
	}

	/**
	 * 
	 */
	public void regularize() {
		removeTemporaryNodes();
		for (Node node : nodes) {
			if (node instanceof ConditionalNode) {
				setAnchor((ConditionalNode) node);
			}
		}
		elseNodeHandling();
		for (Node node : nodes) {
			if(node instanceof ActionNode){
				setAnchor((ActionNode) node);
			}
		}
		returnNodeHandling();
	}

	/**
	 * 
	 * @param path
	 */
	public void makeGraph(String path, String name) {
		Dot dot = new Dot(name);
		for (Node node : nodes) {
			if (node.hasChild()) {
				String label = "";
				String from = "\"" + node.getBody().replace("\"", "'") + " ["
						+ node.getStart() + ":" + node.getEnd() + "]\"";
				String to = "\"" + node.getChild().getBody().replace("\"", "'")
						+ " [" + node.getChild().getStart() + ":"
						+ node.getChild().getEnd() + "]\"";
				if(!node.getChild().getShape().equals("")){
					dot.addShapeRelation(to, node.getChild().getShape());
				}
				if (node instanceof ConditionalNode) {
					dot.addShapeRelation(from, node.getShape());
					label = "true";
				}
				if (node.getChild() instanceof ConditionalNode) {
					dot.addShapeRelation(to, "diamond");
				}
				dot.addRelation(from, to, label);
			}
			if (node instanceof ConditionalNode) {
				if (((ConditionalNode) node).getElseChild() != null) {
					String from = "\"" + node.getBody().replace("\"", "'")
							+ " [" + node.getStart() + ":" + node.getEnd()
							+ "]\"";
					String to = "\""
							+ ((ConditionalNode) node).getElseChild().getBody()
									.replace("\"", "'")
							+ " ["
							+ ((ConditionalNode) node).getElseChild()
									.getStart() + ":"
							+ ((ConditionalNode) node).getElseChild().getEnd()
							+ "]\"";
					if (((ConditionalNode) node).getElseChild() instanceof ConditionalNode) {
						dot.addShapeRelation(to, "diamond");
					}
					dot.addRelation(from, to, "false");
				}
			}
		}
		dot.makeGraph(path);
	}

	public void printLinks() {
		for (Node node : nodes) {
			if (node.hasChild()) {
				System.out.println("link : [" + node.getBody() + "] --> ["
						+ node.getChild().getBody() + "]");
			} else {
				System.out.println("link : [" + node.getBody() + "]");
			}
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

}
