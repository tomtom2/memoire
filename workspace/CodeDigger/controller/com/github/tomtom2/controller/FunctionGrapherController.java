package com.github.tomtom2.controller;

import java.io.File;
import java.util.ArrayList;

import com.github.tomtom2.model.Function;
import com.github.tomtom2.model.ObjectModel;
import com.github.tomtom2.model.visitor.ClassParser;
import com.github.tomtom2.services.ObjectModelObservable;
import com.github.tomtom2.services.ObjectModelObserver;
import com.github.tomtom2.util.Dot;

public class FunctionGrapherController implements ObjectModelObservable {

	private ArrayList<ObjectModel> objects = new ArrayList<ObjectModel>();
	private ArrayList<ObjectModelObserver> observers = new ArrayList<ObjectModelObserver>();
	
	public void processJavaClass(String path){
		ObjectModel model = ClassParser.parseFile(path);
		objects.add(model);
	}

	@Override
	public void update(String pathToParse) {
		ObjectModel model = ClassParser.parseFile(pathToParse);
		File folder = new File(model.getPackageName());
		folder.mkdir();
		
		for (Function function : model.getFunctions()) {
			String inputPath = folder.getAbsolutePath().toString()+"/"+function.getName();
			String outputPath = folder.getAbsolutePath().toString() +"/" 
					+ function.getName();
			function.getGraph().makeGraph(inputPath, function.getName());
			Dot.makeImageFromDot(inputPath, outputPath, "svg");
		}
		
		for(ObjectModelObserver obs : observers){
			obs.update(model);
		}
	}

	@Override
	public void addObserver(ObjectModelObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObservers() {
		observers.clear();
	}
	
}
