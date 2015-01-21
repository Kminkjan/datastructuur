package main;

import java.util.ArrayList;

public class Vertex {

	private int vertexValue;
	private ArrayList<Edge> edges;
	public Vertex(int vertexValue){
		this.vertexValue = vertexValue;
		edges = new ArrayList<Edge>();
	}
	public int getValue(){
		return this.vertexValue;
	}
	public ArrayList<Edge> getEdgesFromVertex(){
		return edges;
	}
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	public boolean hasEdge(Vertex to){
		for(Edge e: edges){
			if(e.getEnd()==to){
				return true;
			}
		}
		return false;
	}
}
