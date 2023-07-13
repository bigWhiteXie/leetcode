package class15;

import java.util.*;

public class Code05_Prim {
    public static List<Edge> prim(Graph graph){
        HashSet<Node> set = new HashSet<>();
        ArrayList<Edge> res = new ArrayList<>();

        Set<Integer> keySet = graph.nodes.keySet();
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdegeComp());
        for (Integer key : keySet) {
            Node node = graph.nodes.get(key);
            if(!set.contains(node)){
                set.add(node);
                for (Edge edeg : node.edegs) {
                    heap.add(edeg);
                }

                while (!heap.isEmpty()){
                    Edge edge = heap.poll();
                    Node to = edge.to;
                    if(!set.contains(to)){
                        set.add(to);
                        res.add(edge);
                        for (Edge edeg : to.edegs) {
                            heap.add(edeg);
                        }
                    }
                }

            }
            break;
        }
        return res;
    }

    public static List<Edge> prim2(Graph graph){
        Set<Integer> keySet = graph.nodes.keySet();
        Node start = null;
        for (Integer key : keySet) {
             start = graph.nodes.get(key);
             break;
        }

        PriorityQueue<Edge> heap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Edge> rest = new ArrayList<>();
        HashSet<Node> set = new HashSet<>();
        set.add(start);
        list.add(start);
        while(!list.isEmpty()){
            Node node = list.remove(0);
            for (Edge edeg : node.edegs) {
                heap.add(edeg);
            }
            Edge poll = heap.poll();
            if(!set.contains(poll.to)) {
                set.add(poll.to);
                list.add(poll.to);
                rest.add(poll);
            }
        }
        return rest;
    }

    public List<Edge> prim3(Node start){
        ArrayList<Edge> res = new ArrayList<>();
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdegeComp());
        HashSet<Node> set = new HashSet<>();

        set.add(start);
        for (Edge edeg : start.edegs) {
            heap.add(edeg);
        }

        while(!heap.isEmpty()){
            Edge edge = heap.poll();
            Node to = edge.to;
            if(!set.contains(to)) {
                for (Edge edeg : edge.to.edegs) {
                    heap.add(edge);
                }
                set.add(to);
                res.add(edge);
            }
        }

        return res;
    }


    static class EdegeComp implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) {

    }
}
