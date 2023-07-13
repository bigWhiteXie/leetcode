package class22;

/**
 * 有一个数组，每个元素代表不同面值的货币，设每个货币有无数张，
 * 问组成aim的最少张数？注意：这是问组成aim的最少张数而不是组成aim有多少种方式
 */
public class Code02_MinCoinsNoLimit {

	public static int minCoins(int[] arr, int aim) {
//		if(arr.length == 0 || aim <= 0){
//			return 0;
//		}

		return process(0,aim,arr);
	}

	//返回从数组index开始，组成res的最少张数
	private static int process(int index, int res,int[] arr) {
		if(index == arr.length || res <= 0){
			return res == 0 ? 0 : Integer.MAX_VALUE;
		}


		int min = Integer.MAX_VALUE;
		for(int zhang = 0; zhang * arr[index] <= res; zhang++){
			int next = process(index+1,res - zhang*arr[index],arr);
			if(next != Integer.MAX_VALUE){
				min = Math.min(min,next + zhang);
			}
		}
		return min;
	}


	public static int dp1(int[] arr, int aim) {
		//1. 创建dp矩阵，index为行， res为列
		int[][] dp = new int[arr.length+1][aim+1];
		int len = arr.length;

		//2. 设置basecase，res=0设置为0，index=len且aim!=0时，设置为Max_value
		for(int i=0; i<=len; i++){
			dp[i][0] = 0;
		}

		for(int j = 1; j<=aim; j++){
			dp[len][j] = Integer.MAX_VALUE;
		}

		for(int index = len-1; index >= 0; index--){
			for(int res = 1; res <= aim; res++){
				int min = Integer.MAX_VALUE;
				for(int zhang = 0; zhang * arr[index] <= res; zhang++){
					int next = dp[index+1][res - zhang*arr[index]];
					if(next != Integer.MAX_VALUE){
						min = Math.min(min,next + zhang);
					}
				}
				dp[index][res] = min;
			}
		}

		return dp[0][aim];
	}

	public static int dp2(int[] arr, int aim) {
		//1. 创建dp矩阵，index为行， res为列
		int[][] dp = new int[arr.length+1][aim+1];
		int len = arr.length;

		//2. 设置basecase，res=0设置为0，index=len且aim!=0时，设置为Max_value
		for(int i=0; i<=len; i++){
			dp[i][0] = 0;
		}

		for(int j = 1; j<=aim; j++){
			dp[len][j] = Integer.MAX_VALUE;
		}

		for(int index = len-1; index >= 0; index--){
			for(int res = 1; res <= aim; res++){
				int min = dp[index+1][res];
				//当相邻位置不越界且不为Max_value时跟它做比较
				if(res - arr[index] >= 0 && dp[index][res - arr[index]] != Integer.MAX_VALUE){
					min = Math.min(min,dp[index][res - arr[index]] + 1);
				}
				dp[index][res] = min;
			}
		}

		return dp[0][aim];
	}



	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		boolean[] has = new boolean[maxValue + 1];
		for (int i = 0; i < N; i++) {
			do {
				arr[i] = (int) (Math.random() * maxValue) + 1;
			} while (has[arr[i]]);
			has[arr[i]] = true;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 30;
		int testTime = 300000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * maxLen);
			int[] arr = randomArray(N, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = minCoins(arr, aim);
			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("功能测试结束");
	}

}
