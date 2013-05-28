package com.github.tomtom2.model;

import java.util.ArrayList;

public class ObjectModel {

	private String name;
	private String packageName;
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	public ObjectModel(String name, String packageName){
		this.name = name;
		this.packageName = packageName;
	}

	public ArrayList<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public String getPackageName() {
		return packageName;
	}
	
	
}
