package 树;

public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        return balanced(root).isBalanced;
    }

    public Info balanced(TreeNode root){
        if(root == null){
            return new Info(0,true);
        }

        Info l = balanced(root.left);
        Info r = balanced(root.right);

        Info info = new Info(1 + Math.max(l.height, r.height), false);

        if(l.isBalanced && r.isBalanced){
            if(Math.abs(l.height - r.height) <= 1){
                info.isBalanced = true;
            }
        }

        return info;
    }

    static class Info{
        int height;
        boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
