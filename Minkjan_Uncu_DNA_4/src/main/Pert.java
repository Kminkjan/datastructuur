package main;

import java.util.ArrayList;

public class Pert {
	private ArrayList<Vertex> vertices, startVertices;
	private ArrayList<Edge> edges;
	public Pert(){
		this.vertices = new ArrayList<Vertex>();
		this.startVertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}
	public void addVertex(String vertexName){
		vertices.add(new Vertex(vertexName));

	}

	/**
	 * Creates a wheighted relation ({@link main.Edge}) between two {@link main.Vertex Vertexes}.
	 * If a {@link main.Vertex} does not exist, a new one is created.
	 *
	 * @param vertexFrom    The {@link main.Vertex} name where that has the outgoing relation
	 * @param vertexTo  The {@link main.Vertex} name where that has the incoming relation
	 * @param weight    The weight of the relation
	 */
	public void createRelation(String vertexFrom, String vertexTo, int weight){
		Vertex from=null , to =null;
		for(Vertex v: vertices){
			if(vertexFrom.equals(v.getName())){
				from = v;
			}else if(vertexTo.equals(v.getName())) {
				to = v;

				/* If also in in startVertices */
				if (startVertices.contains(to)) {
					/* The to vertex is not a start anymore */
					startVertices.remove(to);
				}

			}
		}
		if(from == null){
			from = new Vertex(vertexFrom);
			System.out.println("VertexFrom does not exist, but is created");
			vertices.add(from);
			startVertices.add(from);
		}
		if(to == null){
			to = new Vertex(vertexTo);
			vertices.add(to);
			System.out.println("VertexTo does not exist, but is created");
		}
		if(from.hasEdge(to)){
			System.out.println("Edge already exists.");
		}else{
			Edge e = new Edge(from, to, weight);
			from.addOutGoingEdge(e);	
			to.addInCommingEdge(e);
		}
		
//		edges.add(new Edge(from, to, weight));
	}

	public void calculateMin() {
		for (Vertex vertex : startVertices) {
			vertex.calculateMinTime(0);
		}
	}

	/**
	 * Print the Pert diagram
	 */
    public void print() {
        for (final Vertex vertex : vertices) {
            vertex.print();
        }
    }
}
