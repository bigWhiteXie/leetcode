package class22;

/**
 * 给定3个参数，N,K,M
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每次打击，都会让怪兽流失[0,M]滴血
 * 问英雄在K次打击内能杀死怪兽的概率
 */
public class Code01_KillMonster {

	public static double killMonster(int N, int K, int M){
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process(N, K, M);
		return (double) ((double) kill / (double) all);
	}

	/**
	 * 怪兽还有hp滴血，攻击time次能消灭怪兽的情况数
	 * @param hp
	 * @param time
	 * @param m
	 * @return
	 */
	private static long process(int hp, int time, int m) {
		if(time == 0){
			return hp <= 0 ? 1 : 0;
		}
		if(hp <= 0){
			return (long) Math.pow(m+1,time);
		}

		//分别计算打出0-M伤害时，击杀怪兽的情况数
		long count = 0;
		for(int i = 0; i<=m; i++){
			long p = process(hp - i, time - 1, m);
			count += p;

		}
		return count;
	}

	public static double dp1(int N, int M, int K){
		if (N <= 0 || M < 1 || K < 1){
			return 0;
		}
		// time为行 hp为列
		long all = (long) Math.pow(M+1,K);
		long[][] dp = new long[K+1][N+1];
		dp[0][0] = 1;
		for(int i=1;i<=K;i++){
			dp[i][0] = (long) Math.pow(M+1,i);
			for(int j=1; j<=N;j++){
				long count = 0;
				//无论j等于多少都是走M步
				for(int h = 0; h<=M; h++){
					long p = 0;
					if(j-h  >= 0) {
						p = dp[i - 1][j - h];
					}else {
						p = (long) Math.pow(M+1,i-1);
					}
					count += p;
				}
				dp[i][j] = count;
			}
		}

		return (double) ((double)dp[K][N] / (double) all);
	}

	public static double dp2(int N, int M, int K){
		if (N <= 0 || M < 1 || K < 1){
			return 0;
		}
		// time为行 hp为列
		long all = (long) Math.pow(M+1,K);
		long[][] dp = new long[K+1][N+1];
		dp[0][0] = 1;
		for(int i=1;i<=K;i++){
			dp[i][0] = (long) Math.pow(M+1,i);
			for(int j=1; j<=N;j++){
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
				if(j - M - 1 < 0){
					dp[i][j] -= Math.pow(M+1,i-1);
				}else {
					dp[i][j] -= dp[i-1][j-M-1];
				}
			}
		}

		return (double) ((double)dp[K][N] / (double) all);
	}

//	public static double dp2(int N, int M, int K) {
//		if (N < 1 || M < 1 || K < 1) {
//			return 0;
//		}
//		long all = (long) Math.pow(M + 1, K);
//		long[][] dp = new long[K + 1][N + 1];
//		dp[0][0] = 1;
//		for (int times = 1; times <= K; times++) {
//			dp[times][0] = (long) Math.pow(M + 1, times);
//			for (int hp = 1; hp <= N; hp++) {
//				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
//				if (hp - 1 - M >= 0) {
//					dp[times][hp] -= dp[times - 1][hp - 1 - M];
//				} else {
//					dp[times][hp] -= Math.pow(M + 1, times - 1);
//				}
//			}
//		}
//		long kill = dp[K][N];
//		return (double) ((double) kill / (double) all);
//	}

	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = killMonster(N, K, M);
			double ans2 = dp2(N, M, K);
			double ans3 = dp1(N, M, K);
			if ( ans1 != ans3) {
				System.out.println("ans1 :" + ans1);
				System.out.println("ans2 :" + ans2);
				System.out.println("ans3 :" + ans3);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
