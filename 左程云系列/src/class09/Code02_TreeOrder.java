package class09;

import java.util.Stack;

public class Code02_TreeOrder {
     static class Node{
        int val;
        Node left,right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void preOrder(Node root){

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node node = stack.pop();

            System.out.println(node.val);

            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 关键点：
     *      1. 利用额外变量cur来代表需要入栈的节点
     *      2. 用cur来遍历出栈节点的左孩子直到左孩子为空并依次将左孩子入栈
     *      3. 出栈得到bottom_left节点，并将cur指向该节点的右孩子
     * @param root
     */
    public static Node midOrder(Node root,Node node){
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
             stack.push(cur);
             cur = cur.left;
            }
            Node pop = stack.pop();
            if (pre == node){
                return pop;
            }
            pre = pop;
            System.out.println(pop.val);
            if(pop.right != null){
                cur = pop.right;
            }
        }
        return null;
    }

    public static void postOrder(Node root){
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node node = stack.pop();
            stack2.push(node);

            stack.push(node.left);
            stack.push(node.right);
        }

        while(!stack2.isEmpty()){
            Node node = stack2.pop();
            System.out.println(node.val);
        }
    }

    public static void main(String[] args) {
        //              1
        //             2  3
        //            4 5 6 7
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

//        preOrder(head);
//        System.out.println("========");
        System.out.println(midOrder(head, head.left.right).val);
//        System.out.println("========");
//        postOrder(head);
//        System.out.println("========");



    }
}
