package 贪心;

public class 最大子数组和_dp {
    int max = Integer.MIN_VALUE;

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        for(int i=dp.length-1;i>=0;i--){
            int r = nums[i] + dp[i+1];
            max = Math.max(max,r);
            if(r > 0){
                dp[i] = r;
            }else {
                dp[i] = 0;
            }
        }
        return max;
    }

    public int dp(int[] nums, int index){
        if(index == nums.length){
            return 0;
        }

        int r = nums[index] + dp(nums,index+1);
        max = Math.max(max,r);
        if(r > 0){
            return r;
        }else {
            return 0;
        }
    }
}
