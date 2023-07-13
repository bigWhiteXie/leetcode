package 二分查找;

public class 二分查找 {
    public static void main(String[] args) {
        int[]  nums = {-1,0,3,5,9,12};
        Solution solution = new Solution();
        int target = 9;
        System.out.println(solution.search(nums, target));
    }

}

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int middle;
        while(left <= right){
            middle = left + ((right -left)>>1);
            if(nums[middle] == target){
                return middle;
            }
            if(nums[middle] < target){
                left = middle + 1;
            }
            if(nums[middle] > target){
                right = middle - 1;
            }
        }
        return -1;
    }
}
