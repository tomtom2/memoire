package com.github.tomtom2.objectgrapher.store;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.Type;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.tomtom2.objectgrapher.domain.Field;
import com.github.tomtom2.objectgrapher.domain.Method;
import com.github.tomtom2.objectgrapher.util.Dot;

public class Graph {

	private String pathToClass = "";
	private CompilationUnit cu;
	public Map<String, Field> fieldsMap = new HashMap<String, Field>();
	public Map<String, Method> methodsMap = new HashMap<String, Method>();
	
	
	public Graph(String path){
		this.pathToClass = path;
		setUpCompilationUnit(pathToClass);
		setUpclassMembers();
	}
	
	private void setUpCompilationUnit(String path){
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void getFields(BodyDeclaration member){
		FieldDeclaration field = (FieldDeclaration) member;
		int mod = field.getModifiers();
		Type type = field.getType();
		for(VariableDeclarator var : field.getVariables()){
			fieldsMap.put(var.toString(), new Field(mod, type, var.toString()));
		}
	}
	
	private void getMethods(BodyDeclaration member){
		MethodDeclaration method = (MethodDeclaration) member;
		methodsMap.put(method.getName(), new Method(method));
		
	}
	
	private void getConstructors(BodyDeclaration member){
		
	}
	
	public void makeBasicGraph(int maxIteration){
		Dot dot = new Dot("Solver", 3);
		int init = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for(String meth : this.methodsMap.keySet()){
			count1++;
			dot.addRelation("0", "1."+count1, meth);
			for(String meth2 : this.methodsMap.keySet()){
				count2++;
				dot.addRelation("1."+count1, "2."+count2, meth2);
//				for(String meth3 : this.methodsMap.keySet()){
//					count3++;
//					dot.addRelation(""+init+2, "3."+count3, meth3);
//				}
			}
		}
		dot.makeGraph("/home/thomas/Solver_basic.dot");
	}
	
	private void setUpclassMembers(){
        List<TypeDeclaration> types = cu.getTypes();
        for (TypeDeclaration type : types) {
            List<BodyDeclaration> members = type.getMembers();
            for (BodyDeclaration member : members) {
                if (member instanceof MethodDeclaration) {
                	getMethods(member);
                }
                else if(member instanceof ConstructorDeclaration){
//                	System.out.println(">>> constructor");
                }
                else if(member instanceof FieldDeclaration){
                	getFields(member);

                }
            }
        }
    }
	
}
