package main;

public class Edge {
	private int weight;
	private Vertex from, to;
	public Edge(Vertex from, Vertex to, int weight){
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
	public Vertex getStart(){
		return this.from;
	}
	public Vertex getEnd(){
		return this.to;
	}
	public int getWeight(){
		return this.weight;
	}
}

