package class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Code04_TreeMaxWidth {
    static class Node{
        int val;
        Node left,right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 核心思路：用curEnd声明当前层的结束标记,利用结束标记来计算每层长度
     * 使用curEnd来表示当前层的最后一个节点，nextEnd表示下一层最后一个节点
     * 初始：curEnd为头结点，nextEnd随着遍历会发生变化（每遍历一个判断其是否有左右孩子，有左孩子先将nextEnd指向左孩子，有右孩子再将nextEnd指向右孩子），
     *      遍历到curEnd后，执行 curEnd = nextEnd
     * @param root
     * @return
     */
    public static int maxWidth(Node root){
       if (root == null){
           return 0;
       }

       Node cur = root, curEnd = root, nextEnd = null;
       ArrayList<Node> list = new ArrayList<>();
       list.add(cur);

       int max = 0;
       int n = 0;
       while(!list.isEmpty()){
           Node node = list.remove(0);
           n++;

           if(node.left != null){
               nextEnd = node.left;
               list.add(node.left);
           }

           if(node.right != null){
               nextEnd = node.right;
               list.add(node.right);
           }

           //进入下一层前更新max、n和curEnd
           if(node == curEnd){
               curEnd = nextEnd;
               max = max > n ? max : n;
               n = 0;
           }
       }

       return max;
    }

    static class Info{
        int level;
        int width;

        public Info(int level, int width) {
            this.level = level;
            this.width = width;
        }
    }

    public static int getMaxWidth(Node x){
        if(x == null){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        process2(x,1,map);

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : set) {
            max = max > entry.getValue() ? max : entry.getValue();
        }
        return max;
    }

    private static void process2(Node x, int level, Map<Integer,Integer> map) {
        if(x == null){
            return ;
        }

        if(!map.containsKey(level)){
            map.put(level,1);
        }else {
            map.put(level,map.get(level)+1);
        }

        process2(x.left,level+1,map);
        process2(x.right,level+1,map);

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new  Node(4);
        head.left.right = new  Node(5);
        head.right.left = new  Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        System.out.println(maxWidth(head));
        System.out.println(getMaxWidth(head));
    }
}
