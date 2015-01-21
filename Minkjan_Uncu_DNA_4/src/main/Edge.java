package main;

public class Edge {
    private final int weight;
    private final Vertex from, to;

    /**
     * Creates a new {@link main.Edge}
     *
     * @param from   The {@link main.Vertex} this {@link main.Edge} comes from
     * @param to     The {@link main.Vertex} this {@link main.Edge} goes to
     * @param weight The weight of this {@link main.Edge} in an int
     */
    public Edge(Vertex from, Vertex to, int weight) {
        assert from != null : "from is null";
        assert to != null : "to is null";
        assert weight >= 0 : "weight is to low";

        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public Vertex getEnd() {
        return this.to;
    }

    public void calculateMinTime(int value) {
        assert value >= 0 : "value is to low";

//        System.out.println("calculatemin: " + from.getName() + " -> " + to.getName() + ", value: " + (value + weight));
        this.to.calculateMinTime(value + weight);
    }

    public void print() {
        System.out.print("-> " + to.getName() + "(" + weight + ")");
    }

    public void calculateMaxTime(int value) {
        assert value >= 0 : "value is to low";

//        System.out.println("calculatemax: " + from.getName() + " -> " + to.getName() + ", value: " + (value - weight));
        this.from.calculateMaxTime(value - weight);

    }
}

