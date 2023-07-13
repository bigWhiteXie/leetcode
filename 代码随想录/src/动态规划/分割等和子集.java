package 动态规划;

public class 分割等和子集 {


    /**
     * 将该问题转化为筛选出部分元素，他们的总和 = 数组之和/2即可
     * 也就是一个01背包问题
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if(sum % 2 != 0){
            return false;
        }
        sum /= 2;

        boolean[][] dp = new boolean[nums.length][sum+1];

        for(int i=0; i<nums.length; i++) {
            dp[i][0] = true;
        }
        int rest = sum;
        for(int index = nums.length-1;index>=0; index--){
            for(int j = 1;j<= sum;j++){
                int n = nums[index];
                boolean r1 = false;
                if(j >= n) {
                     r1 = dp[index + 1][j - n];
                }
                boolean r2 = dp[index + 1][j];

                dp[index][j] = r1 || r2;
            }


        }

        return dp[0][sum];
    }

    public boolean process(int[] nums,int index,int rest){
        if(rest == 0){
            return true;
        }

        if(index == nums.length || rest < 0){
            return false;
        }
        int n = nums[index];
        //放进背包
        boolean r1 = process(nums, index + 1, rest - n);

        //不放进背包
        boolean r2 = process(nums, index + 1, rest);

        return r1 || r2;
    }
}
