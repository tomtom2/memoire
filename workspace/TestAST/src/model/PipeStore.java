package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import main.Node;

public class PipeStore {

	//private ArrayList<Node> mainList = new ArrayList<Node>();
	private LinkedList<Node> pipeList = new LinkedList<Node>();
	private LinkedList<Integer> indexesList = new LinkedList<Integer>();
	private int currentIndex = 0;
	private Node lastNodeAdded = null;
	
	
	public PipeStore(){
		
	}
	
	public void addNode(Node node){
		if(this.lastNodeAdded==null){
			init(node);
			return;
		}
		if(node.getType().equals("exp")){
			pipeList.addLast(node);
		}
		else if(node.getStart()>this.lastNodeAdded.getStart() && node.getEnd()<this.lastNodeAdded.getEnd()){
			Node indent = new Node();
			indent.setType("{");
			indent.setBody("{");
			pipeList.addLast(indent);
			pipeList.addLast(node);
		}
		else if(node.getStart()>this.lastNodeAdded.getEnd()){
			Node unindent = new Node();
			unindent.setType("}");
			unindent.setBody("}");
			pipeList.addLast(unindent);
			pipeList.addLast(node);
		}
	}
	
	private void init(Node node){
		LinkedList<Node> nodeList = new LinkedList<Node>();
		nodeList.addLast(node);
		this.lastNodeAdded = node;
		pipeList.addLast(node);
	}
	
	public ArrayList<Node> toNodeArray(){
		ArrayList<Node> array = new ArrayList<Node>();
		for(Node node : pipeList){
			array.add(node);
		}
		return array;
	}
}
