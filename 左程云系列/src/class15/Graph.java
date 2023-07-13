package class15;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    HashMap<Integer,Node> nodes;
    HashSet<Edge> edges;

    public Graph(HashMap<Integer, Node> nodes, HashSet<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
