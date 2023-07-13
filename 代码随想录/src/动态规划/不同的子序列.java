package 动态规划;

public class 不同的子序列 {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];

        for(int i=0;i<=s.length();i++){
            dp[i][t.length()] = 1;
        }

        for(int i = s.length()-1;i>=0;i--){
            for(int j= t.length()-1; j>=0;j--){
                int r1 = 0;
                if(s.charAt(i) == t.charAt(j)){
                    r1 = dp[i+1][j+1];
                }
                int r2 = dp[i+1][j];

                dp[i][j] = r1 + r2;
            }
        }

        return dp[0][0];
    }

    private int dp(String s, String t, int i, int j) {
        if(j == t.length()){
            return 1;
        }

        if(i == s.length()){
            return 0;
        }

        int r1 = 0;
        if(s.charAt(i) == t.charAt(j)){
            r1 = dp(s,t,i+1,j+1);
        }

        int r2 = dp(s,t,i+1,j);


        return r1 + r2 ;
    }
}
