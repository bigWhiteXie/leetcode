package 树;

public class 左叶子之和 {
    public int sumOfLeftLeaves(TreeNode root) {


        return leftLeaves(root,false).ans;
    }

    private Info leftLeaves(TreeNode root,boolean isLeft) {
        if(root == null){
            return null;
        }

        //分别处理左右叶子节点
        if(root.left == null && root.right == null ){
            if(isLeft) {
                return new Info(true, root.val);
            }else {
                return new Info(true,0);
            }
        }


        Info l = leftLeaves(root.left,true);
        Info r = leftLeaves(root.right,false);
        int sum = 0;
        if(l != null){
            sum += l.ans;
        }
        if(r!=null){
            sum += r.ans;
        }
        Info info = new Info(false, sum);

        return info;


    }


    static class Info{
        boolean isLeaves;
        int ans;

        public Info(boolean isLeaves, int ans) {
            this.isLeaves = isLeaves;
            this.ans = ans;
        }
    }
}
