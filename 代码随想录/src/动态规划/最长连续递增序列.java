package 动态规划;

public class 最长连续递增序列 {
    public int findLengthOfLCIS1(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        for(int i=0;i<=nums.length;i++){
            dp[nums.length][i] = i;
        }


        for(int i=nums.length-1;i>=0;i--){
            for(int j=nums.length;j>=0;j--){
                int r = j;
                if(i<nums.length-1 && j<=nums.length-1 && nums[i] < nums[i+1]){
                    r = dp[i+1][j+1];
                }

                int r2 = dp[i+1][1];
                dp[i][j] = Math.max(r,r2);

            }
        }
        print_arr(dp);
        return dp[0][1];
    }

    public int findLengthOfLCIS2(int[] nums) {
        int[] dp = new int[nums.length+1];

        int max = 0;

        for(int i = nums.length-1;i>=0;i--){
            if(i < nums.length-1 && nums[i] < nums[i+1]){
                dp[i] = 1 + dp[i+1];
            }else{
                dp[i] = 1;
            }

            max = Math.max(max,dp[i]);
        }

        return max;
    }


    private int dp(int[] nums, int i ,int count) {
        if(i == nums.length){
            return count;
        }


        if(i<nums.length-1 && nums[i] < nums[i+1]){
            count = dp(nums,i+1,1+count);
        }

        int count1 = dp(nums,i+1,1);

        return Math.max(count,count1);
    }


    private void print_arr(int[][] dp){
        int m = dp.length;
        int n = dp[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(dp[i][j]+ "  ");
            }
            System.out.println();
        }
    }
}
