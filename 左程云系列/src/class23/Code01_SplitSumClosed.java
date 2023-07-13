package class23;

/**
 * 将一组货币面值分成两个集合，使得其和尽量相等，返回小的那一个
 */
public class Code01_SplitSumClosed {

	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return process(arr, 0, sum / 2);
	}

	private static int process(int[] arr, int index, int res) {
		if(res <= 0){
			return res == 0 ? 0:Integer.MIN_VALUE;
		}
		if(index == arr.length){
			return 0;
		}

		int p1 = process(arr,index+1,res);

		int p2 = process(arr,index+1,res-arr[index]);
		if(p2 != Integer.MIN_VALUE){
			p2 += arr[index];

		}

		return Math.max(p1,p2);
	}


	public static int dp(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		sum /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][sum + 1];
		for(int i=0; i<=N; i++){
			dp[i][0] = 0;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int rest = 1; rest <= sum; rest++) {
				// 可能性1，不使用arr[i]
				int p1 = dp[i + 1][rest];
				// 可能性2，要使用arr[i]
				int p2 = 0;
				if (rest - arr[i] < 0) {
					p2 = Integer.MIN_VALUE;
				}else{
					p2 = arr[i] + dp[i+1][rest-arr[i]];
				}
				dp[i][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][sum];
	}

	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * value);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 50;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxLen);
			int[] arr = randomArray(len, maxValue);
			int ans1 = right(arr);
			int ans2 = dp(arr);
			if (ans1 != ans2) {
				printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
				System.out.println("times: " + i+1);
				break;
			}

		}
		System.out.println("测试结束");
	}

}
