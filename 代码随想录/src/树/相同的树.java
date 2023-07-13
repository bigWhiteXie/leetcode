package 树;

public class 相同的树 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return compare(p,q);
    }

    private boolean compare(TreeNode p, TreeNode q) {
        if(p==null && q!=null){
            return false;
        }else if(p!=null && q==null){
            return false;
        }else if(p == null && q == null){
            return true;
        }else if(p.val != q.val){
            return false;
        }

        boolean r1 = compare(p.left, q.left);
        boolean r2 = compare(p.right, q.right);

        return r1&&r2;
    }


}
