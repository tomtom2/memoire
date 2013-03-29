package source;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class Grapher {

	private int depth = 1;
	@SuppressWarnings("rawtypes")
	private Class classToGraph;
	
	@SuppressWarnings("rawtypes")
	public Grapher(Class classToGraph, int depth){
		this.classToGraph = classToGraph;
		this.setDepth(depth);
	}
	
	public void printResume(){
		System.out.println("\n"+classToGraph.getName());
		System.out.println("----------");
		for(Field field : classToGraph.getDeclaredFields()){
			System.out.println(field.getName());
		}
		System.out.println("----------");
		for(Method meth : classToGraph.getDeclaredMethods()){
			System.out.println(meth.getName());
		}
		System.out.println("\n");
	}
	
	public ArrayList<ArrayList<String>> methodGroups(){
		//TODO
		return null;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
