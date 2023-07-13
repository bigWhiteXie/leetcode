package class22;

/**
 * 数字分割：将n分解成多个数字之和，且后面的数不得小于前面的数，问有多少种分割方法
 * 例如：7 = 1+1+2+3 或 7 = 1+2+4 但7=1+4+2不行
 */
public class Code03_SplitNumber {

	// n为正数
	public static int ways(int n) {
		return process(n,1);
	}

	/**
	 * 从cur开始合成res的方法数
	 * @param res
	 * @param cur
	 * @return
	 */
	private static int process(int res, int cur) {
		if(res <= 0){
			return res == 0 ? 1 : 0;
		}
		int ways = 0;
		for(int i = cur; i<=res; i++){
			ways += process(res - i,i);
		}
		return ways;
	}

	public static int dp(int n){
		//行表示res，列表示cur
		int[][] dp = new int[n+1][n+1];
		for (int i = 0; i < n + 1; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < n+1; i++) { //i表示res
			for(int j=1;j<n+1; j++){ //j表示cur
				for(int c = j; c<=i; c++){
					dp[i][j] += dp[i-c][c];
				}
			}

		}

		return dp[n][1];
	}

	// 上一个拆出来的数是pre
	// 还剩rest需要去拆
	// 返回拆解的方法数


	public static int dp1(int n) {
		if( n <= 0){
			return 0;
		}
		//1. 创建dp，cur为行，res为列
		int[][] dp = new int[n+1][n+1];
		for(int i=0;i<=n; i++){
			dp[i][0] = 1;
		}

		for(int res = 1; res<=n; res++){
			for(int cur = 0; cur <= n; cur++){
				int ways = 0;
				for(int i = cur; i<=res; i++){
					ways += dp[i][res - i];
				}
				dp[cur][res] =ways;
			}
		}

		return dp[1][n];
	}

	public static int dp2(int n) {
		if( n <= 0){
			return 0;
		}
		//1. 创建dp，cur为行，res为列
		int[][] dp = new int[n+1][n+1];
		for(int i=0;i<=n; i++){
			dp[i][0] = 1;
		}

		for(int res = 1; res<=n; res++){
			for(int cur = 1; cur <= n; cur++){
				if(cur == 1){
					int ways = 0;
					for(int i = cur; i<=res; i++){
						ways += dp[i][res - i];
					}
					dp[cur][res] =ways;
				}else {
					int ways = dp[cur-1][res];
					if(res - cur+1 >= 0){
						ways -= dp[cur-1][res-cur+1];

					}
					dp[cur][res] = ways;
				}

			}
		}

		return dp[1][n];
	}

	public static void main(String[] args) {
		int test = 31;
		System.out.println(ways(test));
		System.out.println(dp1(test));
		System.out.println(dp2(test));
		System.out.println(dp(test));
	}

}
