package main;

import java.util.ArrayList;

public class Pert {
	private ArrayList<Vertex> verticles;
	private ArrayList<Edge> edges;
	public Pert(){
		this.verticles = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}
	public void addVertex(String vertexName){
		verticles.add(new Vertex(vertexName));

	}
	public void createRelation(String vertexFrom, String vertexTo, int weight){
		Vertex from=null , to =null;
		for(Vertex v: verticles){
			if(vertexFrom.equals(v.getName())){
				from = v;
			}else if(vertexTo.equals(v.getName())){
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
		if(from.hasEdge(to)){
			System.out.println("Edge already exists.");
		}else{
			from.addOutGoingEdge(new Edge(from, to, weight));	
		}
		
//		edges.add(new Edge(from, to, weight));
	}
	public void print(){
		for(final Vertex vertex : verticles) {
			vertex.print();
		}

//		for(Edge e : edges){
//			System.out.println("start: "+ e.getStart()+ " with weight: " + e.getWeight()  +" -> end: "+ e.getEnd());
//		}
	}
}
