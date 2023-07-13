package class15;

import java.util.*;

public class Code04_Kruskal {
    public static List<Edge> kruscal(Graph graph){
//        Set<Integer> keySet = graph.nodes.keySet();
//        ArrayList<Node> nodes = new ArrayList<>();
//        for (Integer key : keySet) {
//            nodes.add(graph.nodes.get(key));
//        }
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdegeComp());

        HashSet<Node> set = new HashSet<>();
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }

        ArrayList<Edge> res = new ArrayList<>();
        while (!heap.isEmpty()){
            Edge edge = heap.poll();
            Node from = edge.from;
            Node to = edge.to;

            if(!set.contains(from) || !set.contains(to)){
                set.add(from);
                set.add(to);
                res.add(edge);
            }
        }

        return res;
    }



    public static List<Edge> kruscal2(Graph graph){
        Set<Integer> keySet = graph.nodes.keySet();
        ArrayList<Node> nodes = new ArrayList<>();
        for (Integer key : keySet) {
            nodes.add(graph.nodes.get(key));
        }
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdegeComp());
        UnionFind unionFind = new UnionFind(nodes);
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }

        ArrayList<Edge> res = new ArrayList<>();
        while (!heap.isEmpty()){
            Edge edge = heap.poll();
            Node from = edge.from;
            Node to = edge.to;
            if(!unionFind.isSameUnion(from,to)){
                unionFind.union(from,to);
                res.add(edge);
            }

        }

        return res;
    }
    static class EdegeComp implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    static class UnionFind{
        HashMap<Node,Node> parentMap = new HashMap<>();
        HashMap<Node,Integer> sizeMap = new HashMap<>();

        public UnionFind(List<Node> list){
            for (Node node : list) {
                parentMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        private Node findFather(Node node){
            ArrayList<Node> list = new ArrayList<>();
            while(parentMap.get(node) != node){
                list.add(node);
                node = parentMap.get(node);
            }

            for (Node item : list) {
                parentMap.put(item,node);
            }

            return node;
        }

        public void union(Node a, Node b){
            Node f1 = findFather(a);
            Node f2 = findFather(b);

            if (f1 != f2){
                Integer size1 = sizeMap.get(f1);
                Integer size2 = sizeMap.get(f2);

                Node more = size1 > size2? f1:f2;
                Node less = more == f1? f2 : f1;

                parentMap.put(less,more);
                sizeMap.put(more,size1+size2);
                sizeMap.put(less,0);
            }
        }

        public boolean isSameUnion(Node a, Node b){
            return findFather(a) == findFather(b);
        }
    }

    public static void main(String[] args) {
        int[][] martix = {{1,0,1},{2,0,2},{3,0,3},{5,1,2},{4,1,4},{6,1,5},{9,2,3},{8,2,6},{3,2,1}};
        Graph graph = GraphGenerator.createGraph(martix);
        List<Edge> list = kruscal(graph);
        System.out.println("------------------------------------------");
        List<Edge> list1 = kruscal2(graph);
        int ans1 = 0;
        for (int i = 0; i < list.size(); i++) {
            ans1 += list.get(i).weight;
        }
        int ans2 = 0;
        for (int i = 0; i < list1.size(); i++) {
            ans2 += list1.get(i).weight;
        }

        if(ans1!=ans2){
            System.out.println("oops");
        }
        System.out.println("ans1: " + ans1);
        System.out.println("ans2 " + ans2);
        System.out.println("finish");
    }
}
