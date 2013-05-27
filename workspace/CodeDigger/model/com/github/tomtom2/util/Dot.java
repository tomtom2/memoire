package com.github.tomtom2.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dot {

	private String name;
	private ArrayList<String> relations = new ArrayList<String>();
	
	public Dot(String graphName){
		this.name = graphName;
	}
	
	public void addRelation(String startState, String endState, String label){
		String line = startState+" -> "+endState+" [label=\""+label+"\"];";
		relations.add(line);
	}
	
	public void addShapeRelation(String nodeName, String shape){
		String line = nodeName+" [shape="+shape+"];";
		relations.add(line);
	}
	
	public void makeGraph(String path){
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			fw.write("digraph "+name+" {\n");
			for(String link : relations){
				fw.write("    "+link+"\n");
			}
			fw.write("}");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
