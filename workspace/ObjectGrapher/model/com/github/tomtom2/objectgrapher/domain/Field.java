package com.github.tomtom2.objectgrapher.domain;

import japa.parser.ast.type.Type;

public class Field {

	private int modifier;
	private Type type;
	private String name;
	
	public Field(int mod, Type type, String name){
		this.modifier = mod;
		this.type = type;
		this.name = name;
	}
	
	public String toString(){
		return name+"|"+type.toString()+"|"+modifier;
	}
}
