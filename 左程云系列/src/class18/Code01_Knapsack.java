package class18;

/**
 * w[]表示一组物品的重量
 * v[]表示一组物品的价值
 * bag表示背包容量
 * 问：装背包装的物品总价值最大是多少
 *
 * 从左到右尝试模型：以index从左到右展开逻辑
 */
public class Code01_Knapsack {

	public static int maxValue(int[] w, int[] v, int bag){
		return process(w,v,0,bag);

	}

	/**
	 * 从w[index]开始往后装，能装的最大价值
	 * @param w
	 * @param v
	 * @param index
	 * @param bag
	 * @return
	 */
	private static int process(int[] w, int[] v,int index, int bag) {
		if(bag < 0){
			return -1;
		}
		if(index >= w.length){
			return 0;
		}
		int r1 = process(w, v, index + 1, bag - w[index]);
		//判断背包容量是否不够，若不够则置为0
		r1 = r1 == -1 ? 0:r1+v[index];
		int r2 = process(w, v, index + 1, bag);


		return Math.max(r1,r2);
	}

	public static int dp(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		int[][] dp = new int[w.length+1][bag+1];
		for(int i=0; i<=bag;i++){
			dp[w.length][i] = 0;
		}
		for(int j=0;j<=bag;j++){
			for(int i=w.length-1; i>=0; i--){
				int r1 = dp[i+1][j];
				int r2 = 0;
				if(j - w[i] >= 0){
					r2 = v[i] + dp[i+1][j-w[i]];
				}
				dp[i][j] = Math.max(r1,r2);
			}
		}

		return dp[0][bag];
	}

	public static int maxBag(int[] w, int[] v, int bag){
		return process2(w,v,0,bag);
	}

	private static int process2(int[] w, int[] v, int i, int bag) {
		if(bag <= 0 || i == w.length){
			return 0;
		}

		int r1 = process2(w,v,i+1,bag);
		int r2 = 0;
		if(bag >= w[i]){
			r2 = v[i] + process2(w,v,i+1,bag-w[i]);
		}

		return Math.max(r1,r2);
	}

	public static int dp2(int[] w, int[] v, int bag){
		//设置index、bag为状态变量
		int[][] info = new int[w.length+1][bag+1];

		//初始化状态变量，bag<=0,i=w.length时设为0，由于int初始化本身就为0故不用添加代码

		//此时bag=0这一列，index=w.length这一行已经初始化完毕
		//根据初始化结果，向上回溯，得到答案Info[0][bag]
		for(int i=w.length-1; i>=0;i--){
			for(int j=1;j<=bag;j++){
				int r1 = info[i+1][j];
				int r2 = 0;
				if(j >= w[i]){
					r2 = v[i] + info[i+1][j-w[i]];
				}
				info[i][j] = Math.max(r1,r2);
			}
		}

		return info[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7, 3, 1, 7 ,1};
		int[] values = { 5, 6, 3, 19, 12, 4, 2 ,10};
		int bag = 15;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dp(weights, values, bag));
		System.out.println(dp2(weights, values, bag));
		System.out.println(maxBag(weights,values,bag));
	}

}
