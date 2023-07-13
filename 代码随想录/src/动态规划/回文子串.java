package 动态规划;



public class 回文子串 {
    public int countSubstrings(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0; i<s.length();i++){
            dp[i][i] = 1;
        }

        int count = 0;
        for(int i = s.length()-1;i>=0;i--){
            for(int j=s.length(); j>=i+1;j--){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i <= 1){
                        dp[i][j] = 1;
                        count++;
                    }else{
                        if(dp[i+1][j-1] == 1){
                            dp[i][j] = 1;
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int countSubstrings2(String s) {
        return dp(s,0,s.length()-1,true);
    }

    private int dp(String s, int i, int j,boolean flag) {
        if(i == j && flag){
            return 1;
        }
        if(i > j | (i==j && !flag)){
            return 0;
        }
        int r1 = dp(s,i+1,j,false);
        int r2 = dp(s,i,j-1,false);
        int r3 = 0;
        if(s.charAt(i) == s.charAt(j)){
            r3 = dp(s,i+1,j-1,true);
        }else{
            r3 = dp(s,i+1,j-1,false);
        }

        return r1+r2+r3;

    }


}
