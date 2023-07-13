package 树;

public class 有序数组转化为平衡二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return generatorTree(nums,0,nums.length-1);
    }

    private TreeNode generatorTree(int[] nums, int left, int right) {
        if(left > right){
            return null;
        }
        int mid = left + (right-left)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = generatorTree(nums,left,mid-1);
        node.right = generatorTree(nums,mid+1,right);
        return node;
    }
}
