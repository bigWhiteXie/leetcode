package class10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目描述：将二叉树序列化为一个list，该list存储二叉树的值，并要求通过该list将二叉树反序列化
 */
public class Code02_serializeAndRecoverTree {
    static class Node{
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static List<Integer> encode(Node root){
        ArrayList<Integer> list = new ArrayList<>();

        if(root != null){
            preEncode(root,list);
        }
        return list;
    }



    private static void preEncode(Node root, ArrayList<Integer> list) {
        if(root == null){
            list.add(null);
            return;
        }

        list.add(root.val);
        preEncode(root.left,list);
        preEncode(root.right,list);
    }

    public static Node preRecover(List<Integer> list){
        if(list == null || list.size() == 0){
            return null;
        }

        Node root = new Node(list.remove(0));
        root.left = decode(list.remove(0),list);
        root.right = decode(list.remove(0),list);
        return root;
    }

    private static Node decode(Integer val,List<Integer> list) {
        if(val == null || val == 0){
            return null;
        }
        Node node = new Node(val);
        node.left = decode(list.remove(0),list);
        node.right = decode(list.remove(0),list);

        return node;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.val != head2.val) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        List<Integer> list = encode(head);
        for(Integer val:list){
            System.out.print(val + " ");
        }
        System.out.println();
        Node node = preRecover(list);
        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        while(!que.isEmpty()){
            Node poll = que.poll();
            System.out.print(poll.val + " ");
            if(poll.left != null){
                que.add(poll.left);
            }

            if(poll.right != null){
                que.add(poll.right);
            }
        }
        System.out.println("test finish!");

    }

}
