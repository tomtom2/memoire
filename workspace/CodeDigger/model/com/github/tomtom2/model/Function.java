package com.github.tomtom2.model;

import java.util.HashMap;
import java.util.Map;

public class Function {

	private String name;
	private Map<String, String> arguments = new HashMap<String, String>();
	private Cube cube = new Cube();
	private NodeStore graph;
}
