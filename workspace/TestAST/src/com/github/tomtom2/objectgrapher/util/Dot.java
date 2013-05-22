package com.github.tomtom2.objectgrapher.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dot {

	private String name;
	private int limit;
	private ArrayList<String> relations = new ArrayList<String>();
	
	public Dot(String graphName, int limit){
		this.name = graphName;
		this.limit = limit;
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
				System.out.println(link);
			}
			fw.write("}");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
