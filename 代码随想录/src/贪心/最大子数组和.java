package 贪心;

public class 最大子数组和 {
    /**
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int cursum = 0;

        for(int i =0;i<nums.length;i++){
            cursum += nums[i];
            if(cursum > sum){
                sum = cursum;
            }
            if(cursum < 0){
                cursum = 0;
            }
        }

        return sum;
    }
}
