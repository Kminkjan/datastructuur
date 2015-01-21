package main;

import java.util.ArrayList;

public class Vertex {

    private int edgesProcessed, minValue;
    private final String vertexName;

	private final ArrayList<Edge> incomingEdges, outgoingEdges;

    public Vertex(String vertexName) {
        this.vertexName = vertexName;
		this.incomingEdges = new ArrayList<Edge>();
		this.outgoingEdges = new ArrayList<Edge>();
    }

    public String getName() {
        return this.vertexName;
    }

    public void calculateMinTime(int value) {
        /* If this is the first edge to be processed OR the value of the edge is lower then the current minValue */
        if (edgesProcessed == 0 || this.minValue > value) {
            this.minValue = value;
        }
        ++this.edgesProcessed;

        /* all the edges have arrived in the vertex */
        if (this.edgesProcessed == incomingEdges.size()) {

            /* Recursively call all the outgoing edges */
            for (final Edge edge : outgoingEdges) {
                edge.calculateMinTime(value);
            }
            this.edgesProcessed = 0;
        }
    }

    public void print() {
        System.out.print(vertexName + ": ");
        for(final Edge edge : outgoingEdges) {
            System.out.print(", ");
            edge.print();
        }
        System.out.println();
    }

	public ArrayList<Edge> getEdgesFromVertex(){
		return edgess;
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
