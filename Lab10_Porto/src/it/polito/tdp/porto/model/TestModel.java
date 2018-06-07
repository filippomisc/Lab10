package it.polito.tdp.porto.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		m.createGraph();
		
	
		System.out.println(m.getCollaboratoriDiAutore(1847).toString());
		
//		System.out.println(m.get);
		
		m.getShortestPath(1847,2041);
	}

}
