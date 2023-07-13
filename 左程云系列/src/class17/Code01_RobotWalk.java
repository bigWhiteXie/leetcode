package class17;

/**
 * 机器人从start位置走K步，能有多少种方式到达aim(最长只能走到N)
 */
public class Code01_RobotWalk {

	public static int ways1(int N, int start, int aim, int K){
		int[][] info = new int[N+1][K+1];
		for(int i=1;i<=N;i++){
			for (int j=0;j<=K;j++){
				info[i][j] = -1;
			}
		}
		return process(start,aim,K,N,info);
	}

	private static int process(int start, int aim, int k,int N,int[][] info) {
		if(info[start][k] != -1){
			return info[start][k];
		}
		if(start == aim && k== 0){
			return 1;
		}else if(start != aim && k==0){
			return 0;
		}
		int res;
		if(start == 1){
			 res = process(2,aim,k-1,N,info);
		}else if(start == N){
			res = process(N-1,aim,k-1,N,info);
		}else {
			res = process(start+1,aim,k-1,N,info) + process(start-1,aim,k-1,N,info);
		}
		info[start][k] = res;


		return  res;
	}

	public static int ways3(int N, int start, int aim, int K) {
		int[][] info = new int[N+1][K+1];
		info[aim][0] = 1;
		for (int k = 1; k <= K ; k++) {
			info[1][k] = info[2][k-1];
			for (int i = 1; i < N; i++) {
				info[i][k] = info[i-1][k-1] + info[i+1][k-1];
			}
			info[N][k] = info[N-1][k-1];
		}

		return info[start][K];
	}

	public static int ways2(int N, int start, int aim, int K){
		//N+1表示0-N这n+1个位置，k表示K步也就是递归深度
		int[][] info = new int[N+1][K+1];
		//初始化info数组：当i=aim,k=0时设为1
		info[aim][0] = 1;
		for(int j=1;j<=K;j++){
			for(int i=1;i<=N;i++){
//				if(start == 1){
//					res = process(2,aim,k-1,N,info);
//				}else if(start == N){
//					res = process(N-1,aim,k-1,N,info);
//				}else {
//					res = process(start+1,aim,k-1,N,info) + process(start-1,aim,k-1,N,info);
//				}
				if(i == 1){
					info[i][j] = info[2][j-1];
				}else if(i == N){
					info[i][j] = info[i-1][j-1];
				}else {
					info[i][j] = info[i+1][j-1] + info[i-1][j-1];
				}
			}
		}

		return info[start][K];

	}




	public static void main(String[] args) {
		System.out.println(ways1(5, 2, 4, 6));
		System.out.println(ways2(5, 2, 4, 6));
		System.out.println(ways3(5, 2, 4, 6));
	}

}
