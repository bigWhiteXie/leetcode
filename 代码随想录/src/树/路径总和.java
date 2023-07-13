package 树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 路径总和 {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        Info info = computeSums(root);

        for (Integer sum : info.sums) {
            if(sum == targetSum){
                return true;
            }
        }

        return false;
    }

    public static Info computeSums(TreeNode root) {
        if(root == null){
            return null;
        }

        Info left = computeSums(root.left);
        Info right = computeSums(root.right);
        Info info = new Info();

        if(left == null && right == null){
            info.sums.add(root.val);
            return info;
        }

        if(left != null){
            for (Integer sum : left.sums) {
                info.sums.add(sum+root.val);
            }
        }

        if(right != null){
            for (Integer sum : right.sums) {
                info.sums.add(sum+root.val);
            }
        }

        return info;
    }

    /**
     * 层次遍历建树
     * @param list
     * @return
     */
    public static TreeNode buildTree(List<Integer> list){
        Integer val = list.remove(0);
        TreeNode node = new TreeNode(val);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        TreeNode root = node;
        while(!list.isEmpty()){
            node = queue.poll();
            if(node != null) {
                Integer lVal = list.remove(0);
                Integer rVal = list.remove(0);
                node.left = lVal != null ? new TreeNode(lVal):null;
                node.right = rVal != null ? new TreeNode(rVal):null;

                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }

        }

        return root;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,null,1));
        int[] tab = new int[list.size()];
        TreeNode node = buildTree(list);
        boolean flag = hasPathSum(node, 22);
        System.out.println(flag);
    }
    static class Info{
        public ArrayList<Integer> sums = new ArrayList<>();
    }
}
