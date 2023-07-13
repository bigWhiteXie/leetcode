package class19;

/**
 * 设棋盘大小为(10,9),马从(0,0)开始出发，跳k步到(a,b)，有多少种跳法
 *
 * 业务限制模型：棋盘大小限制
 */
public class Code02_HorseJump {

	// 当前来到的位置是（x,y）
	// 还剩下rest步需要跳
	// 跳完rest步，正好跳到a，b的方法数是多少？
	// 10 * 9
	public static int jump(int a, int b, int k){
		return process(0,0,a,b,k);
	}

	/**
	 *
	 * @param x 当前x位置
	 * @param y	当前y坐标
	 * @param a 目标x坐标
	 * @param b 目标y坐标
	 * @param k 剩余步数
	 * @return
	 */
	private static int process(int x, int y, int a, int b, int k) {
		if(x < 0 || x >= 10 || y < 0 || y>=9){
			return 0;
		}
		if(k == 0){
			if(x == a && y == b){
				return 1;
			}
			return 0;
		}
		int ways = process(x-2,y+1,a,b,k-1);
		ways += process(x+2,y+1,a,b,k-1);
		ways += process(x-1,y+2,a,b,k-1);
		ways += process(x+1,y+2,a,b,k-1);
		ways += process(x-2,y-1,a,b,k-1);
		ways += process(x+2,y-1,a,b,k-1);
		ways += process(x-1,y-2,a,b,k-1);
		ways += process(x+1,y-2,a,b,k-1);

		return ways;
	}

	public static int dp(int a, int b, int k) {
		int[][][] dp = new int[10][9][k+1];

		dp[a][b][0] = 1;

		for(int m = 1; m<=k; m++){
			for(int i=0;i<10;i++){
				for(int j=0;j<9;j++){
					int ways = pick(dp,i+2,j+1,m-1);
					ways+= pick(dp,i-2,j+1,m-1);
					ways+= pick(dp,i-1,j+2,m-1);
					ways+= pick(dp,i+1,j+2,m-1);
					ways+= pick(dp,i-2,j-1,m-1);
					ways+= pick(dp,i+2,j-1,m-1);
					ways+= pick(dp,i-1,j-2,m-1);
					ways+= pick(dp,i+1,j-2,m-1);
					dp[i][j][m] = ways;
				}
			}
		}

		return dp[0][0][k];


	}

	public static int pick(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		return dp[x][y][rest];
	}

	public static int ways(int a, int b, int step) {
		return f(0, 0, step, a, b);
	}

	public static int f(int i, int j, int step, int a, int b) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		if (step == 0) {
			return (i == a && j == b) ? 1 : 0;
		}
		return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
				+ f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
				+ f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

	}

	public static int waysdp(int a, int b, int s) {
		int[][][] dp = new int[10][9][s + 1];
		dp[a][b][0] = 1;
		for (int step = 1; step <= s; step++) { // 按层来
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 9; j++) {
					dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
							+ getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
							+ getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
							+ getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
				}
			}
		}
		return dp[0][0][s];
	}

	// 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
	public static int getValue(int[][][] dp, int i, int j, int step) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		return dp[i][j][step];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
//		System.out.println(ways(x, y, step));
		System.out.println(dp(x, y, step));

		System.out.println(jump(x, y, step));
	}
}
