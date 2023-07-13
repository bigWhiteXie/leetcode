package 动态规划;

public class 整数拆分 {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        dp[3] = 2;

        if(n<=3){
            return dp[n];
        }

        for(int i=3;i<=n;i++){
            for(int j = 1; j <= i - 1;j++){
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),dp[j] * (i-j)));
            }
        }

        return dp[n];

    }
}
