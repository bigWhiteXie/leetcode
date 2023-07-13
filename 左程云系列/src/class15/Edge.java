package class15;

public class Edge {
    int weight;
    Node from;
    Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
}
