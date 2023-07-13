package class15;

public class GraphGenerator {
    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    //
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int[] item : matrix) {
            int weight = item[0];
            int from = item[1];
            int to = item[2];

            Node fromNode = new Node(from);
            Node toNode = new Node(to);

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, fromNode);
            }
            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, toNode);
            }
            fromNode = graph.nodes.get(from);
            toNode = graph.nodes.get(to);

            Edge edge = new Edge(weight, fromNode, toNode);
            graph.edges.add(edge);

            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edegs.add(edge);
        }
        return graph;
    }
}
