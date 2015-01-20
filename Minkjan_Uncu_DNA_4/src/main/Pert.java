package main;

import java.util.ArrayList;

public class Pert {
	private ArrayList<Vertex> verticles;
	private ArrayList<Edge> edges;
	public Pert(){
		this.verticles = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}
	public void addVertex(int vertexValue){
		verticles.add(new Vertex(vertexValue));
	}
	public void createRelation(int vertexFrom, int vertexTo, int weight){
		Vertex from=null , to =null;
		for(Vertex v: verticles){
			if(vertexFrom == v.getValue()){
				from = v;
			}else if(vertexTo == v.getValue()){
				to = v;
			}
		}
		if(from == null){
			from = new Vertex(vertexFrom);
			System.out.println("VertexFrom does not exist, but is created");
			verticles.add(from);
		}
		if(to == null){
			to = new Vertex(vertexTo);
			verticles.add(to);
			System.out.println("VertexTo does not exist, but is created");
		}
		edges.add(new Edge(from, to, weight));
		
	}
	public void print(){
		for(Edge e : edges){
			System.out.println("start: "+ e.getStart()+ " with weight: " + e.getWeight()  +" -> end: "+ e.getEnd());
		}
	}
}
