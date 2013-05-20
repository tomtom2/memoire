package model;

import java.util.ArrayList;

import com.github.tomtom2.objectgrapher.util.Dot;

import node.ActionNode;
import node.ConditionalNode;
import node.Node;

public class NodeStore {

	private ArrayList<Node> nodes = new ArrayList<Node>();

	public NodeStore() {
		Node start = new Node();
		start.setBody("start");
		start.setType("start");
		start.setStart(0);
		start.setEnd(0);
		nodes.add(start);
	}

	public void addNode(Node node) {
		System.out.println("adding : [" + node.getBody() + "] "
				+ node.getStart() + ":" + node.getEnd());
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
		if (parentContainer == null) {
			makeDirectLink(node);
			return;
		}
		if (!parentContainer.hasChild()) {
			parentContainer.setChild(node);
			node.setParent(parentContainer);
			System.out.println("create cond. link : ["
					+ parentContainer.getBody() + "] --> [" + node.getBody()
					+ "]");
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
				System.out.println("create cond. link : ["
						+ containedParent.getBody() + "] --> [" + node.getBody()
						+ "]");
			}
		}
	}

	private void makeDirectLink(ConditionalNode node) {
		// TODO Auto-generated method stub
		
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

			System.out.println("create action link : ["
					+ parentContainer.getBody() + "] --> [" + node.getBody()
					+ "]");
			System.out.println("######################################");
			printLinks();
			System.out.println("######################################");
		} else {
			Node parent = parentContainer.getChild();
			while (parent.hasChild()
					&& parent.getChild().isBetterParentFor(parent, node)) {
				parent = parent.getChild();
			}
			if (parent == null) {
				return;
			} else {
				parent.setChild(node);
				node.setParent(parent);
				System.out.println("create action link : [" + parent.getBody()
						+ "] --> [" + node.getBody() + "]");
				System.out.println("**************************************");
				printLinks();
				System.out.println("**************************************");
			}
		}
	}

	private Node findBestParentContainer(Node node) {
		Node bestContainer = null;
		System.out.println("node = " + node.getBody() + " [" + node.getStart()
				+ ":" + node.getEnd() + "]");
		for (Node oldNode : nodes) {
			if (bestContainer == null && node.isContainedBy(oldNode)) {
				bestContainer = oldNode;
			} else {
				if (bestContainer != null) {
					System.out
							.println("best container for "+node.getBody()+"[" + node.getStart()
									+ ":" + node.getEnd() + "] between ["
									+ bestContainer.getStart() + ":"
									+ bestContainer.getEnd() + "] and ["
									+ oldNode.getStart() + ":"
									+ oldNode.getEnd() + "]");
				}
				bestContainer = node
						.isBetterContainedBy(oldNode, bestContainer);
				// System.out.println("--> ["+bestContainer.getStart()+":"+bestContainer.getEnd()+"]\n");
			}
		}
		if (bestContainer != null) {
			System.out.println("bestContainer = " + bestContainer.getBody()
					+ " [" + bestContainer.getStart() + ":"
					+ bestContainer.getEnd() + "]");
		}

		return bestContainer;
	}

	private boolean hasParent(Node node) {
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
		System.out.println("looking for parent...");
		Node parent = null;
		for (Node oldNode : nodes) {
			if (node.getStart() > oldNode.getStart()
					&& node.getEnd() <= oldNode.getEnd()) {
				parent = oldNode;
				break;
			}
		}
		if (parent == null) {
			System.out.println("no parent");
			return null;
		}
		System.out.println("entering while loop...");
		Node lastParentTrial = new Node();
		while (!parent.equals(lastParentTrial) && parent.hasChild()) {
			lastParentTrial = parent;
			if (node.getStart() > parent.getChild().getStart()
					&& node.getEnd() <= parent.getChild().getEnd()) {
				parent = parent.getChild();
			} else if (parent instanceof ConditionalNode) {
				// -TODO
				if (((ConditionalNode) parent).getElseChild() != null) {
					if (node.getStart() > ((ConditionalNode) parent)
							.getElseChild().getStart()
							&& node.getEnd() <= ((ConditionalNode) parent)
									.getElseChild().getEnd()) {
						parent = ((ConditionalNode) parent).getElseChild();
					}
				}
			} else if (parent instanceof ActionNode && parent.hasChild()) {
				// -TODO
				if (((ConditionalNode) parent).getElseChild() != null) {
					if (node.getStart() > ((ConditionalNode) parent)
							.getElseChild().getStart()
							&& node.getEnd() <= ((ConditionalNode) parent)
									.getElseChild().getEnd()) {
						parent = ((ConditionalNode) parent).getElseChild();
					}
				}
			} else if (parent instanceof ConditionalNode) {
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
		return parent;
	}

	public boolean contains(Node node) {
		if(node.getType().equals("") || node.getBody().equals("")){
			return true;
		}
		for (Node containedNode : nodes) {
			if (containedNode.equals(node)) {
				return true;
			}
		}
		return false;
	}

	public void makeGraph(String path) {
		Dot dot = new Dot("my_graph", 0);
		for (Node node : nodes) {
			if(node.hasChild()){
				String from = "\""+node.getBody().replace("\"", "'")+"\"";
				String to = "\""+node.getChild().getBody().replace("\"", "'")+"\"";
				dot.addRelation(from, to, "");
			}
			if(node instanceof ConditionalNode){
				if(((ConditionalNode)node).getElseChild()!=null){
					String from = "\""+node.getBody().replace("\"", "'")+"\"";
					String to = "\""+((ConditionalNode) node).getElseChild().getBody().replace("\"", "'")+"\"";
					dot.addRelation(from, to, "");
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
