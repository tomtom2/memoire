package com.github.tomtom2.model.datadependency;

import java.util.ArrayList;

import com.github.tomtom2.model.ObjectModel;
import com.github.tomtom2.util.Dot;

public class ObjectSet {

	private ArrayList<ObjectModel> objects = new ArrayList<ObjectModel>();
	
	public void printDependencyGraph(){
		Dot dot = new Dot("Metadata");
		for(ObjectModel object1 : objects){
			System.out.print(object1.getName());
			String end = "\""+object1.getName()+" ("+object1.getPackageName()+")\"";
			dot.addShapeRelation(end, "box");
			for(ObjectModel object2 : objects){
				System.out.println("\t"+object2.getName());
				if(object1.getComponents().contains(object2.getPackageName()+"."+object2.getName())){
					String start = "\""+object2.getName()+" ("+object2.getPackageName()+")\"";
					dot.addRelation(start, end, "");
				}
			}
		}
		dot.makeGraph("dataDependencies.dot");
	}
	
	public boolean add(ObjectModel newObject){
		for(ObjectModel object : objects){
			if(newObject.equals(object)){
				return false;
			}
		}
		objects.add(newObject);
		return true;
	}
}
