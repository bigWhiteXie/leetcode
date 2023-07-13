package class15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Code03_TopoSort {
    public static void TopoSort(Graph graph){
        LinkedList<Node> inZeroQue = new LinkedList<>();
        Set<Integer> keySet = graph.nodes.keySet();
        for (Integer key : keySet) {
            if(graph.nodes.get(key).in == 0){
                inZeroQue.add(graph.nodes.get(key));
            }
        }

        while (!inZeroQue.isEmpty()){
            Node node = inZeroQue.poll();
            System.out.println(node.val);
            for (Node next : node.nexts) {
                next.in--;
                if(next.in == 0){
                    inZeroQue.add(next);
                }
            }
        }
    }

    public static void TopoSort2(Graph graph){
        HashMap<Integer, Node> nodes = graph.nodes;
        LinkedList<Node> list = new LinkedList<>();
        for (Node node : nodes.values()) {
            if(node.in == 0){
                list.add(node);
            }
        }

        while(!list.isEmpty()){
            Node node = list.remove(0);
            System.out.println(node.val);
            for (Node next : node.nexts) {
                next.in--;
                if(next.in == 0){
                    list.add(next);
                }
            }
        }
    }
}
