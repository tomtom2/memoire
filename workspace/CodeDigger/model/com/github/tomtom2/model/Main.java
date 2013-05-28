package com.github.tomtom2.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.github.tomtom2.model.visitor.ClassParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "/home/thomas/master/memoire/workspace/CodeDigger/model/com/github/tomtom2/model/ObjectModel.java";
		
		ObjectModel mod = ClassParser.parseFile(path);
		for(Function f : mod.getFunctions()){
        	System.out.println(f);
        }
		
		System.out.println("\nEND OF PGM");
	}

}
