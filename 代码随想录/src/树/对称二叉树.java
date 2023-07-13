package 树;

public class 对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if(root != null){
            return compare(root.left,root.right);
        }
        return true;
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if(left == null && right != null){
            return false;
        }else if(left != null && right == null){
            return false;
        }else if(left == null && right == null){
            return true;
        }else if(left.val != right.val){
            return false;
        }

        boolean r1 = compare(left.left, right.right);
        boolean r2 = compare(left.right, right.left);

        return r1 && r2;
    }


}
