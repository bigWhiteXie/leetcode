package 树;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 二叉搜索树转为累加树 {
    //错误写法
    //原因：因为sum是局部变量，回溯的时候sum的值依然使用的是原来函数上下文的值，哪怕在之前的递归中sum经过累加
    //更正：将sum由局部变量改为全局变量即可
    public TreeNode convertBST(TreeNode root) {
       return buildTree(root,0);
    }

    private TreeNode buildTree(TreeNode root, int sum) {
        if(root == null){
            return null;
        }

        TreeNode right = buildTree(root.right, sum);
        sum += root.val;;
        root.val = sum;
        TreeNode left = buildTree(root.left, sum);
        return root;
    }
}
