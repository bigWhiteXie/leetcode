package 树;

public class 二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Info info = lowestAncestor(root,p,q);

        return info.lowestAncestor;
    }

    private Info lowestAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return new Info(null,false,false);
        }

        Info l_info = lowestAncestor(root.left, p, q);
        Info r_info = lowestAncestor(root.right, p, q);
        if(l_info.lowestAncestor != null){
            return l_info;
        }
        if(r_info.lowestAncestor != null){
            return r_info;
        }


        boolean hasP = l_info.hasP || r_info.hasP || root == p;
        boolean hasQ = l_info.hasQ || r_info.hasQ || root == q;
        TreeNode ancestor = null;
        if(hasP && hasQ){
            ancestor = root;
        }

        return new Info(ancestor,hasP,hasQ);
    }

    static class Info{
        TreeNode lowestAncestor;
        boolean hasP;
        boolean hasQ;

        public Info(TreeNode lowestAncestor, boolean hasP, boolean hasQ) {
            this.lowestAncestor = lowestAncestor;
            this.hasP = hasP;
            this.hasQ = hasQ;
        }
    }
}
