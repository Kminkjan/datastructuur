package main;

public class Edge {
	private final int weight;
	private final Vertex from, to;
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


	public void calculateMinTime(int value) {
		System.out.println("calculatemin: " + from.getName() + " -> " + to.getName() + ", value: " + (value+weight));
		this.to.calculateMinTime(value+weight);
	}

	public void print() {
		System.out.print("-> "  + to.getName() + "(" + weight + ")");
	}
	public void calculateMaxTime(int value) {
		System.out.println("calculatemax: " + from.getName() + " -> " + to.getName() + ", value: " + (value-weight));
		this.from.calculateMaxTime(value - weight);
		
	}
}

