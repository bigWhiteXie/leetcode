package 动态规划;

public class 判断子序列 {
    public boolean isSubsequence(String s, String t) {
        return dp(s,t,0,0) - s.length() == 0;
    }

    private int dp(String s, String t, int i, int j) {
        if(i == s.length() | j == t.length()){
            return 0;
        }

        if(s.charAt(i) == t.charAt(j)){
            return 1 + dp(s,t,i+1,j+1);
        }

        int r1 = dp(s,t,i+1,j);
        int r2 = dp(s,t,i,j+1);

        return Math.max(r1,r2);
    }
}
