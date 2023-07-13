package class15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {
    public static void DFS(Node start){
        //对节点的nexts遍历之前，先将节点记录下来，避免重复
        HashSet<Node> set = new HashSet<>();

        process(start,set);
    }

    private static void process(Node start, HashSet<Node> set) {
        if(!set.contains(start)){
            System.out.println(start.val);
            set.add(start);
            for (Node next : start.nexts) {
                process(next, set);
            }
        }
    }

    public static void DFS2(Node start){
        HashSet<Node> set = new HashSet<>();

        Stack<Node> stack = new Stack<>();

        stack.push(start);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            if(!set.contains(node)){
                System.out.println(node.val);
                set.add(node);
                for (Node next : node.nexts) {
                    stack.push(next);
                }
            }
        }
    }

    public static void DFS3(Node start){
        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            if(!set.contains(pop)) {
                System.out.println(pop.val);
                set.add(pop);
                for (Node next : pop.nexts) {
                    if (!set.contains(next)) {
                        stack.push(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] martix = {{0,0,1},{0,0,2},{0,0,3},{0,1,2},{0,1,4},{0,1,5},{0,2,3},{0,2,6},{0,2,1}};
        Graph graph = GraphGenerator.createGraph(martix);
        Node start = graph.nodes.get(0);
        DFS(start);
        System.out.println("------------------------------------------");
        DFS2(start);
        System.out.println("------------------------------------------");
        DFS3(start);
    }
}
