package class20;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 题目
 * arr是面值数组，其中的值都是正数但有重复。再给定一个正数aim。
 * 返回组成aim的方法数
 *
 */
public class Code04_CoinsWaySameValueSamePapper {

	public static class Info {
		public int[] coins;
		public int[] zhangs;

		public Info(int[] c, int[] z) {
			coins = c;
			zhangs = z;
		}
	}

	/**
	 * 对数组进行遍历得到coins装载面值不同的货币，zhang[]装载每个货币的张数
	 * @param arr
	 * @return
	 */
	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i : arr) {
			if (map.containsKey(i)){
				map.put(i,map.get(i)+1);
			}else{
				map.put(i,1);
			}
		}

		int i = 0;
		Set<Entry<Integer, Integer>> entries = map.entrySet();
		int[] coins = new int[entries.size()];
		int[] zhang = new int[entries.size()];
		for (Entry<Integer, Integer> entry : entries) {
			coins[i] = entry.getKey();
			zhang[i++] = entry.getValue();
		}

		return new Info(coins,zhang);
	}

	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		return process(info.coins, info.zhangs, 0, aim);
	}

	// coins 面值数组，正数且去重
	// zhangs 每种面值对应的张数
	public static int process(int[] coins, int[] zhangs, int index, int rest) {
		if(rest == 0){
			return 1;
		}else if(rest < 0){
			return 0;
		}
		if(index == coins.length){
			return rest == 0? 1 : 0;
		}

		int ways = 0;
		for(int i = 0; i<=zhangs[index] && rest >= 0; i++){
			int re = rest - i * coins[index];
			ways += process(coins,zhangs,index+1,re);
		}

		return ways;
	}

	public static int dp1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
					ways += dp[index + 1][rest - (zhang * coins[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}

	public static int dp2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
				//当rest-coins[index]已经能够完全包括zhang[index]时，需要考虑张数的限制
				if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
					dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
				}
			}
		}
		return dp[0][aim];
	}

	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
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
		int maxLen = 10;
		int maxValue = 20;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);

			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3 ) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
