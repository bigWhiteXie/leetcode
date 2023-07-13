package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Test10 {
    //[ai,bi],学习ai必须先学习bi
    public static boolean canFinish (int numCourses, int[][] prerequisites) {
        HashMap<Integer, Node> map = new HashMap<>();
        for(int[] item:prerequisites){
            //当长度为1时说明是根节点
            if(item.length == 1){
                Node node = new Node(item[0]);
                node.in = 0;
                map.put(item[0],node);
            }else{
                Node from = map.getOrDefault(item[1], new Node(item[1]));
                Node to = map.getOrDefault(item[0], new Node(item[0]));
                from.nexts.add(to);
                to.in++;
                from.out++;
                if(!map.containsKey(item[1])){
                    map.put(item[1],from);
                }

                if(!map.containsKey(item[0])){
                    map.put(item[0],to);
                }
            }
        }

        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.in - b.in);
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            heap.add(entry.getValue());
        }

        while(!heap.isEmpty()){
            Node node = heap.poll();
            if(node.in != 0){
                return false;
            }
            for (Node next : node.nexts) {
                next.in--;
            }
        }

        return true;
    }

    static class Edge{
        Node from,to;
    }

    static class Node{
        int val;
        int in,out;
        ArrayList<Node> nexts = new ArrayList<>();

        public Node(int v){
            val = v;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{2},{1,0}};
        System.out.println(canFinish(arr.length, arr));
    }
}
