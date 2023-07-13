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
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        Solution3 solution3 = new Solution3();
        int[] ints = solution3.searchRange(nums, 6);
        System.out.println(ints[0]+ "," +ints[1]);
    }
}

class Solution3 {
    public int[] searchRange(int[] nums, int target) {
        int[] data = new int[]{-1,-1};
        int left = 0, right = nums.length;
        int middle,i = -1,j = -1;
        while(left < right){
            middle = left + ((right - left) / 2);
            if(nums[middle] == target){
                i = middle;
                j = middle;
                while((i>=0 && nums[middle] == nums[i]) || (j < right && nums[middle] == nums[j])){
                    if(i>=0 && nums[middle] == nums[i]){
                        data[0] = i;
                        i--;
                    }
                    if(j < right && nums[middle] == nums[j]){
                        data[1] = j;
                        j++;
                    }
                }
                return data;
            }else if(nums[middle] > target){
                right = middle;
            }else{
                left = middle+1;
            }
        }
        return data;
    }
}
