package main;

import java.util.ArrayList;

public class Vertex {

    private int edgesProcessed, minValue, maxValue;
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
        if (edgesProcessed == 0 || this.minValue < value) {
            this.minValue = value;
        }
        ++this.edgesProcessed;

        /* all the edges have arrived in the vertex */
        if (incomingEdges.isEmpty() || this.edgesProcessed == incomingEdges.size()) {
        	this.edgesProcessed = 0;
            /* Recursively call all the outgoing edges */
            for (final Edge edge : outgoingEdges) {
                edge.calculateMinTime(value);
            }
            
            if(outgoingEdges.isEmpty()){
            	calculateMaxTime(minValue);
            }
        }
    }
    public void calculateMaxTime(int value){
    	if(edgesProcessed == 0|| this.maxValue> value){
    		this.maxValue = value;
    	}
    	++this.edgesProcessed;
        /* all the edges have arrived in the vertex */
        if (outgoingEdges.isEmpty() || this.edgesProcessed == outgoingEdges.size()) {
        	this.edgesProcessed = 0;
            /* Recursively call all the outgoing edges */
            for (final Edge edge : incomingEdges) {
                edge.calculateMaxTime(value);
            }
            
        }
    }

    public void print() {
        System.out.print("VERTEX: " + vertexName +"(" + minValue + "," + maxValue + ")" + ": ");
        for(final Edge edge : outgoingEdges) {
            System.out.print(", ");
            edge.print();
        }
        System.out.println();
    }

	public ArrayList<Edge> getOutGoingEdges(){
		return this.outgoingEdges;
	}
	public ArrayList<Edge> getInCommingEdges(){
		return this.incomingEdges;
	}
	public void addOutGoingEdge(Edge edge){
		this.outgoingEdges.add(edge);
	}
	public void addInCommingEdge(Edge edge){
		this.incomingEdges.add(edge);
	}
	public boolean hasEdge(Vertex to){
		for(Edge e: outgoingEdges){
			if(e.getEnd()==to){
				return true;
			}
		}
		return false;
	}
}
