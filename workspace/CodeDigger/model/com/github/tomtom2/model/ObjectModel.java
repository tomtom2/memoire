package com.github.tomtom2.model;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ObjectModel {

	private String name;
	private String packageName;
	private ArrayList<String> components = new ArrayList<String>();
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	public ObjectModel(CompilationUnit cu){
		this.name = cu.getClass().getName();
		this.packageName = cu.getPackage().toString().replace("package ", "").replace(";", "").replace("\n", "").trim();
		for(Object imp : cu.imports()){
			this.components.add(imp.toString().replace("import ", "").replace(";", "").replace("\n", "").trim());
		}
	}

	public boolean equals(ObjectModel object){
		return this.name.equals(object.name) && this.packageName.equals(object.packageName);
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

	public ArrayList<String> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<String> components) {
		this.components = components;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	
	
}
