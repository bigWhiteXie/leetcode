package 树;

import java.util.ArrayList;
import java.util.Arrays;

public class 验证二叉搜索树 {
    public static boolean isValidBST(TreeNode root) {
        Info info  = validBST(root);
        return info.isBST;
    }

    private static Info validBST(TreeNode root) {
        if(root == null){
            return null;
        }

        Info l_info = validBST(root.left);
        Info r_info = validBST(root.right);
        boolean isBST = false;
        int min = root.val;
        int max = root.val;
        if(l_info == null && r_info == null){
            return new Info(true,root.val,root.val);
        }
        if(l_info != null && r_info == null){
            isBST = l_info.isBST && l_info.max < root.val ? true : false;
            max = Math.max(root.val, r_info.max);
            min = Math.min(l_info.min, root.val);
            return new Info(isBST,min,max);
        }

        if(l_info == null && r_info != null){
            isBST = r_info.isBST && r_info.min > root.val ? true : false;
            max = Math.max(root.val, r_info.max);
            min = Math.min(r_info.min, root.val);
            return new Info(isBST,min,max);
        }

        if(l_info.isBST && r_info.isBST){
            isBST = l_info.max < root.val && r_info.min > root.val ? true:false;
            max = Math.max(root.val, Math.max(r_info.max ,l_info.max));
            min = Math.min(root.val, Math.min(r_info.min ,l_info.min));
        }

        return new Info(isBST,min,max);

    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,4,6,null,null,3,7));
        TreeNode node = BuilderTree.buildTree(list);

        System.out.println(isValidBST(node));
    }

    static class Info{
        boolean isBST;
        int min;
        int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
