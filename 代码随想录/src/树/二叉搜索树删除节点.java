package 树;

public class 二叉搜索树删除节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return root;
        }

        if(root.val > key){
            root.left = deleteNode(root.left,key);
        }else if (root.val < key){
            root.right = deleteNode(root.right,key);
        }else{
            //情况1：左右孩子都为空
            if(root.left == null && root.right == null){
                return null;
            }
            //情况2：左孩子不为空，右孩子为空
            if(root.left != null && root.right == null){
                return root.left;
            }
            //情况3：左孩子为空，右孩子不为空
            if(root.left == null && root.right != null){
                return root.right;
            }
            //情况4：左右孩子都不为空

            TreeNode right = root.right;
            while(right.left != null){
                right = right.left;
            }
            right.left = root.left;

        }

        return root;

    }
}
