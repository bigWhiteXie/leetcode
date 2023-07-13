package 动态规划;

public class 爬楼梯 {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[n] = 1;

        int i=n-1;
        while(i >= 0){
            if(i == n-1){
                dp[i] = dp[i+1];
            }else{
                dp[i] = dp[i+1] + dp[i+2];
            }
        }

        return dp[0];
    }

    public int dp(int index, int n){
        if(index > n){
            return 0;
        }
        if(index == n){
            return 1;
        }

        int r1 = dp(index + 1, n);
        int r2 = dp(index + 2, n);

        return r1+r2;
    }
}
