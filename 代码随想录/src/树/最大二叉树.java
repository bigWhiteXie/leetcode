package 树;

import java.util.HashMap;
import java.util.Map;

public class 最大二叉树 {
    //记录(nums[i],i)
    private static Map<Integer,Integer> map = new HashMap<>();

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }

        return builderMaxTree(nums,0,nums.length-1);

    }

    private TreeNode builderMaxTree(int[] nums, int begin, int end) {
        if(begin > end) {
            return null;
        }
        int max = -1;
        for(int i=begin; i<=end;i++){
            max = max < nums[i] ? nums[i]:max;
        }

        Integer rootIndex = map.get(max);
        TreeNode node = new TreeNode(max);
        node.left = builderMaxTree(nums,begin,rootIndex-1);
        node.right = builderMaxTree(nums,rootIndex+1,end);

        return node;
    }
}
