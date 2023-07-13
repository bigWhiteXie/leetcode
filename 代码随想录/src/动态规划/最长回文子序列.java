package 动态规划;

/**
 * 范围尝试模型
 */
public class 最长回文子序列 {
    public static int longestPalindromeSubseq1(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();

        return process(str,0,str.length - 1);
    }

    private static int process(char[] str, int start, int end) {
        if(start > end){
            return 0;
        }
        if(start == end){
            return 1;
        }

        int p1 = process(str,start+1,end);
        int p2 = process(str,start,end-1);
        if(str[start] == str[end]){
            return 2 + process(str,start+1,end-1);
        }
        return Math.max(p1,p2);
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0;i<s.length(); i++){
            dp[i][i] = 1;
        }

        for(int i = s.length()-2; i>=0; i--){
            for(int j=i+1; j < s.length(); j++){
                if(str[i] == str[j]){
                    dp[i][j] = 2 + dp[i+1][j-1];
                    continue;
                }
                dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }

        return dp[0][s.length() - 1];
    }
}
