package class10;

import java.util.LinkedList;

/**
 * 层次遍历二叉树
 */
public class Code01_LevelTravel {
    static class Node{
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }


    public static void levelTravel(Node root){
        LinkedList<Node> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            Node node = que.poll();
            System.out.println(node.val);

            if(node.left != null){
                que.add(node.left);
            }

            if(node.right != null){
                que.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTravel(head);
        System.out.println("========");
    }
}
