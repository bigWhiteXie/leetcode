package 动态规划;

public class 不同路径 {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        dp[m][n] = 1;

        for(int x=n;x>=1;x--){
            for(int y=m;y>=1;y--){
                if(x == n && y==m){
                    continue;
                }
                if(x != n && y != m){
                    int r1 = dp[y][x+1];
                    int r2 = dp[y+1][x];
                    dp[y][x] = r1+r2;
                }else if(x == n){
                    dp[y][x] = dp[y+1][x];
                }else if(y == m){
                    dp[y][x] = dp[y][x+1];
                }
            }
        }

        return dp[1][1];
    }

    public int dp(int m, int n,int x, int y){
        if(x == n && y == m){
            return 1;
        }
        if(x != n && y != m){
            int r1 = dp(m,n,x+1,y);
            int r2 = dp(m,n,x,y+1);
            return r1+r2;
        }else if(x == n){
            return dp(m,n,x,y+1);
        }else{
            return dp(m,n,x+1,y);
        }

    }

    public static void main(String[] args) {
        int paths = uniquePaths(3, 7);
        System.out.println(paths);
    }
}
