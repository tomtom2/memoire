package com.github.tomtom2.objectgrapher.domain;

import japa.parser.ast.body.MethodDeclaration;

import java.util.ArrayList;

public class Method {

	private ArrayList<String> inputVars = new ArrayList<String>();
	private ArrayList<String> outputVars = new ArrayList<String>();
	
	public Method(MethodDeclaration method){
		System.out.println(method.getName());
		System.out.println(method.getParameters());
		System.out.println(method.getType().toString());
		
		System.out.println();
	}
}
