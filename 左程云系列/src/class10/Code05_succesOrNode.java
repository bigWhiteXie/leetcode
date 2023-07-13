package class10;

/**
 * 题目描述：给你一个二叉树的节点，返回它的后继节点
 * 后继节点：中序遍历的下一个节点
 */
public class Code05_succesOrNode {
    static class Node{
        int val;
        Node left, right,parent;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 核心思想：求一个节点的后继节点有两种情况：
     *      1. 该节点存在右子树，则后继节点是右子树的左下方节点
     *      2. 若该节点无右子树，则后继节点是该节点向上找，找到第一个左子树包含该节点的祖先节点
     * @param node
     * @return
     */
    public static Node getSuccessNode(Node node){
        if(node == null){
            return null;
        }
        Node ans = null;
        if(node.right != null){
            ans = getLeftMotst(node.right);
            return ans;
        }

        ans = getUpperAns(node.parent,node);

        return ans;
    }

    private static Node getUpperAns(Node parent,Node child) {
        Node cur = parent;
        while(cur != null){
            if(cur.left == child){
                return cur;
            }
            child = cur;
            cur = cur.parent;
        }
        return null;
    }

    private static Node getLeftMotst(Node node) {
        Node ans = null;
        Node cur = node;
        while(cur != null){
            ans = cur;
            cur = cur.left;
        }

        return ans;
    }

    public static void main(String[] args) {

        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.left.left.right;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.left;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.left.right;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.left.right.right;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.right.left.left;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.right.left;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.right;
        System.out.println(test.val + " next: " + getSuccessNode(test).val);
        test = head.right.right; // 10's next is null
        System.out.println(test.val + " next: " + getSuccessNode(test));
    }
}
