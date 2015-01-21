package main;

import java.util.ArrayList;

public class Vertex {

    private int edgesProcessed, minValue, maxValue;
    private final String vertexName;
    private final ArrayList<Edge> incomingEdges, outgoingEdges;

    /**
     * Create a {@link main.Vertex} with the specified name
     *
     * @param vertexName The name the {@link main.Vertex} should have
     */
    public Vertex(String vertexName) {
        assert vertexName != null : "vertexName is null";
        assert !vertexName.isEmpty() : "vertexName is empty";

        this.vertexName = vertexName;
        this.incomingEdges = new ArrayList<Edge>();
        this.outgoingEdges = new ArrayList<Edge>();
    }

    public String getName() {
        return this.vertexName;
    }

    /**
     * Calculates the minimal time needed for this Vertex
     *
     * @param value The Value to check
     */
    public void calculateMinTime(int value) {
        assert value >= 0 : "value is to low";

        /* If this is the first edge to be processed OR the value of the edge is lower then the current minValue */
        if (edgesProcessed == 0 || this.minValue < value) {
            this.minValue = value;
        }
        ++this.edgesProcessed;

//		System.out.println(vertexName + " : " + edgesProcessed + " : " + incomingEdges.size());
		/* all the edges have arrived in the vertex */
        if (incomingEdges.isEmpty() || this.edgesProcessed == incomingEdges.size()) {
            this.edgesProcessed = 0;
            /* Recursively call all the outgoing edges */
            for (final Edge edge : outgoingEdges) {
                edge.calculateMinTime(minValue);
            }

			/* Am i an endpoint */
            if (outgoingEdges.isEmpty()) {
                calculateMaxTime(minValue);
            }
        }

    }

    /**
     * Calculates the maximal time that is available
     *
     * @param value The value to use
     */
    public void calculateMaxTime(int value) {
        assert value >= 0 : "value is to low";

        if (!incomingEdges.isEmpty() && (edgesProcessed == 0 || this.maxValue > value)) {
            this.maxValue = value;
        }
        ++this.edgesProcessed;
        /* all the edges have arrived in the vertex */
        if (outgoingEdges.isEmpty() || this.edgesProcessed == outgoingEdges.size()) {
            this.edgesProcessed = 0;
            /* Recursively call all the outgoing edges */
            for (final Edge edge : incomingEdges) {
                edge.calculateMaxTime(maxValue);
            }

        }
    }

    /**
     * Print out the {@link main.Vertex}. Also prints out all the {@link main.Vertex Vertexes} that there are
     * connections to.
     */
    public void print() {
        System.out.print("VERTEX: " + vertexName + "(" + minValue + "," + maxValue + ")" + ": ");
        for (final Edge edge : outgoingEdges) {
            System.out.print(", ");
            edge.print();
        }
        System.out.println();
    }

    public ArrayList<Edge> getOutGoingEdges() {
        return this.outgoingEdges;
    }

    public ArrayList<Edge> getInCommingEdges() {
        return this.incomingEdges;
    }

    public void addOutGoingEdge(Edge edge) {
        this.outgoingEdges.add(edge);
    }

    public void addInCommingEdge(Edge edge) {
        this.incomingEdges.add(edge);
    }

    /**
     * Checks if this {@link main.Vertex} contains the given {@link main.Edge}.
     * @param to    The {@link main.Vertex} the {@link main.Edge} has a connection to.
     * @return  True if this {@link main.Edge} exists.
     */
    public boolean hasEdge(Vertex to) {
        assert to != null : "to is null";

        for (final Edge edge : outgoingEdges) {
            if (edge.getEnd() == to) {
                return true;
            }
        }
        return false;
    }
}
