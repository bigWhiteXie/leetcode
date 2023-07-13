package 移除元素;

public class 删除有序数组中的重复项 {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(Solution3.removeDuplicates(nums));
    }
}


class Solution3 {
    public static int removeDuplicates(int[] nums) {
        int slow = 0,fast = 1;
        double num = 0;
        while(fast < nums.length){
            //找到第一个与fast不相同的元素
            while(fast < nums.length && nums[slow] == nums[fast]){
                fast++;
            }
            if(slow < nums.length && fast<nums.length) {
                nums[++slow] = nums[fast];
            }
        }
        return slow+1;
    }
}
