package 树;

public class 树左下角的值 {
    public int findBottomLeftValue(TreeNode root) {
        return leftVal(root).leftBot;
    }

    private Info leftVal(TreeNode root) {
        if(root == null){
            return null;
        }

        if(root.left == null && root.right == null){
            return new Info(root.val,1);
        }

        Info leftVal = leftVal(root.left);
        Info rightVal = leftVal(root.right);
        int left = 0;
        int height = 0;
        if(leftVal != null){
            height = leftVal.height;
            left = leftVal.leftBot;
        }

        if(rightVal != null){
            if((leftVal != null && rightVal.height > leftVal.height) || leftVal == null){
                left = rightVal.leftBot;
            }
            height = Math.max(height, rightVal.height);
        }

        return new Info(left,height+1);



    }

    static class Info{
        int leftBot;
        int height ;
        public Info(int leftBot,int h) {
            this.leftBot = leftBot;
            height = h;
        }
    }
}
