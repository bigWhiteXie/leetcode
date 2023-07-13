package class10;

import java.util.ArrayList;
import java.util.List;

/**
 * 将多叉树转化为二叉树
 */
public class Code03_EncodeNaryTreeToBinaryTree {
    static class Node{
        int val;
        List<Node> childs = new ArrayList<>();

        public Node(int val, List<Node> childs) {
            this.val = val;
            this.childs = childs;
        }
    }

    static class TreeNode{
        int val;
        TreeNode left,right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 采用递归思想：每次递归将多叉树的childs转化为用right指针相连的链表，并返回该链表头结点
     * @param root
     * @return
     */
    public static TreeNode encode(Node root){
        if(root == null){
            return null;
        }

        TreeNode treeNode = new TreeNode(root.val);
        treeNode.left = change(root.childs);
        return treeNode;
    }

    private static TreeNode change(List<Node> childs) {
        if(childs == null || childs.isEmpty()){
            return null;
        }
        TreeNode head = null,cur = null;
        while(!childs.isEmpty()){
            Node node = childs.remove(0);

            if(head == null){
                head = new TreeNode(node.val);
                cur = head;
            }else{
                cur.right = new TreeNode(node.val);
            }

            cur.left = change(node.childs);
            cur = cur.right;
        }
        return head;
    }

    public static Node decode(TreeNode root){
        if(root == null){
            return null;
        }

        Node node = new Node(root.val, deserilaze(root.left));

        return node;
    }

    private static List<Node> deserilaze(TreeNode node) {
        if(node == null){
            return null;
        }

        ArrayList<Node> list = new ArrayList<>();
        while (node != null){
            Node cur = new Node(node.val, deserilaze(node.left));
            list.add(cur);

            node = node.right;
        }

        return list;
    }

    private static Node decode2(TreeNode node) {
        return  new Node(node.val, deserilaze2(node.left));

    }

    private static List<Node> deserilaze2(TreeNode left) {
        if(left == null){
            return null;
        }

        ArrayList<Node> list = new ArrayList<>();
        while(left != null){
            list.add(new Node(left.val, deserilaze2(left.left)));
            left = left.right;
        }

        return list;
    }

}
