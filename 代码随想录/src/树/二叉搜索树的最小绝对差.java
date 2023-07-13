package 树;

import java.util.ArrayList;
import java.util.Stack;

public class 二叉搜索树的最小绝对差 {
    public int getMinimumDifference(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur!= null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode node = stack.pop();
                list.add(node);
                cur = node.right;
            }
        }
        int minDis = Integer.MAX_VALUE;
        for(int i=0;i<list.size()-1;i++){
            minDis = Math.min(minDis,Math.abs(list.get(i).val - list.get(i+1).val));
        }
        return minDis;
    }
}
