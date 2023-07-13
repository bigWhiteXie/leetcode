package 贪心;

import 树.TreeNode;

public class 监控二叉树 {
    public int minCameraCover(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        Info info = backtracking(root);
        return info.count;
    }

    public Info backtracking(TreeNode root){
        if(root == null){
            return null;
        }

        Info l_info = backtracking(root.left);
        Info r_info = backtracking(root.right);
        int count = 0;
        boolean isLeaf = false;
        boolean isLight = false;

        if(l_info == null && r_info == null){
            isLeaf = true;
            return new Info(isLeaf,count,isLight);
        }
        count += r_info != null ? r_info.count : 0;
        count += l_info != null ? l_info.count : 0;

        //情况：子节点都没亮时,点亮该节点
        if((r_info == null || !r_info.isLight) && (l_info == null || !l_info.isLight)){
            isLight = true;
            count++;
        }else if((r_info != null &&r_info.isLeaf && !r_info.isLight) || (l_info!=null && l_info.isLeaf && !l_info.isLight)){
            isLight = true;
            count++;
        }else{
            isLight = false;
        }

        return new Info(isLeaf,count,isLight);


    }

    static class Info{
        boolean isLeaf;
        int count;
        boolean isLight;

        public Info(boolean isLeaf, int count, boolean nextLight) {
            this.isLeaf = isLeaf;
            this.count = count;
            this.isLight = nextLight;
        }
    }
}
