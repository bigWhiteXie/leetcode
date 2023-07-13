package 二分查找;

/**
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 要求时间复杂度为对数
 */
public class 在排序数组中查找元素的第一个和最后一个位置2 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        Solution3_2 solution3 = new Solution3_2();
        int l = solution3.searchLeftBound(nums, 8);
        int r = solution3.searchRightBound(nums, 8);
        System.out.println(l + "," +r);

    }
}

class Solution3_2 {
    public int[] searchRange(int[] nums, int target) {
        int l = searchLeftBound(nums,target), r = searchRightBound(nums,target);
        if(l <= r && l >=0 && r < nums.length && nums[l] == target){
            return new int[]{l,r};
        }
        return new int[]{-1,-1};
    }

    public int searchLeftBound(int[] nums, int target) {
        int left = 0,right = nums.length - 1,middle = -1,ans = -1;
        while(left <= right){
            middle = left + (right -left) / 2;
            if(nums[middle] >= target ){
                right = middle - 1;
                ans = middle;
            }else{
                left = middle + 1;
            }
        }

        return ans;
    }
    public int searchRightBound(int[] nums, int target) {
        int left = 0,right = nums.length - 1,middle = -1,ans = -1;
        while(left <= right){
            middle = left + (right -left) / 2;
            if(nums[middle] <= target ){
                left = middle + 1;
                ans = middle;
            }else{
                right = middle - 1;
            }
        }

        return ans;
    }
}
