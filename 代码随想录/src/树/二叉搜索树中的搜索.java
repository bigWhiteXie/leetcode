package 树;

public class 二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root.val == val){
            return root;
        }
        if(root == null){
            return null;
        }
        if(root.val > val){
            return searchBST(root.left,val);
        }

        return searchBST(root.right,val);
    }

}
