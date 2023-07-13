package 二分查找;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 */
public class 搜索插入位置 {
    public static void main(String[] args) {
        int[]  nums = {1,3,5,6};
        int target = 2;
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.searchInsert(nums, target));
    }
}

class Solution2 {
    /**
     * 思路：采用二分法的开区间方式，当target不在nyms中时能够被插入到正确的位置上
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        int middle;
        while(left < right){
            middle = left + ((right - left) / 2);
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] > target){
                right = middle;
            }else{
                left = middle+1;
            }
        }
        return left;
    }
}
