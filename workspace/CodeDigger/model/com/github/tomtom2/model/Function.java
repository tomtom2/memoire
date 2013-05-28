package com.github.tomtom2.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class Function {

	private String name;
	private List<SingleVariableDeclaration> parameters; //public, private...
	private Map<String, String> arguments = new HashMap<String, String>();
	private Cube cube = new Cube();
	private NodeStore graph;
	
	public Function(String name, List<SingleVariableDeclaration> parameters){
		this.name = name;
		this.parameters = parameters;
	}

	public String toString(){
		String label = name;
		for(SingleVariableDeclaration param : parameters){
			label = label + "_";
			label = label + param.getType().toString().trim().replace(" ", "_");
		}
		return label;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SingleVariableDeclaration> getParameters() {
		return parameters;
	}

	public void setParameters(List<SingleVariableDeclaration> parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getArguments() {
		return arguments;
	}

	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}

	public Cube getCube() {
		return cube;
	}

	public void setCube(Cube cube) {
		this.cube = cube;
	}

	public NodeStore getGraph() {
		return graph;
	}

	public void setGraph(NodeStore graph) {
		this.graph = graph;
	}

	
	
}
