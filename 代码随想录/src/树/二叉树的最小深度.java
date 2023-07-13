package 树;

public class 二叉树的最小深度 {
    public static int minDeep(TreeNode root){
        if(root == null){
            return 0;
        }

        int l = minDeep(root.left);
        int r = minDeep(root.right);

        //出现只有单孩子的情况要使用不为空的子树的高度
        //原因：最小深度指的第一次出现叶子节点时候的深度
        if(root.left == null && root.right != null){
            return 1 + r;
        }

        if(root.left != null && root.right == null){
            return 1 + l;
        }

        return 1 + Math.min(l,r);
    }
}
