package class15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Code01_BFS {
    public static void BFS(Node start){
        //用来记录到装进过队中的元素
        Set<Node> set = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();

        queue.add(start);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.val);

            if(!set.contains(node)){
                set.add(node);
            }

            for (Node next : node.nexts) {
                if(!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    public static void BFS2(Node start){
        ArrayList<Node> queue = new ArrayList<>();
        HashSet<Node> set = new HashSet<>();

        queue.add(start);
        while(!queue.isEmpty()){
            Node node = queue.remove(0);
            if(!set.contains(node)){
                System.out.println(node);

                for (Node next : node.nexts) {
                    queue.add(next);
                }
            }
        }
    }
}
