package class20;

public class Code01_MinPathSum {
	/**
	 * 一个矩阵，从左下角开始走，走到右上角经过节点和最小的路径
	 *
	 * 例如:
	 * 		1, 2, 3, 1
	 * 		1, 2, 1, 4
	 * 		1, 1, 1, 4
	 * 		1, 2, 3, 4
	 *
	 * 结果是9，up -> right -> right -> up -> right
	 * @param m
	 * @return
	 */
	public static int minPathSum1(int[][] m) {
		return process(m,0,0);
	}

	private static int process(int[][] m, int x, int y) {
		if(x < 0 || x >= m.length || y < 0 || y >= m[0].length){
			return 0;
		}

		if(x == m.length - 1 && y == m[0].length - 1){
			return m[x][y];
		}

		int p1 = process(m,x+1,y);
		p1 = p1 == 0?  Integer.MAX_VALUE : m[x][y] + p1;
		int p2 = process(m,x,y+1) ;
		p2 = p2 == 0 ? Integer.MAX_VALUE : m[x][y] + p2;


		return Math.min(p1,p2);
	}

	public static int minPathSum2(int[][] m){
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[row-1][col-1] = m[row-1][col-1];


		for(int i = row - 1; i>=0; i--){
			for(int j = col - 1; j>=0; j--){
				if(i == row - 1 && j == col - 1){
					continue;
				}
				int p1 = pick(dp,i+1,j,m[i][j]);

				int p2 = pick(dp,i, j+1,m[i][j]);
				dp[i][j] = Math.min(p1,p2);
			}
		}
		return dp[0][0];
	}

	private static int pick(int[][] dp, int i, int j,int ans){
		if (i < 0 || i >= dp.length || j < 0 || j >= dp[0].length) {
			return Integer.MAX_VALUE;
		}
		return ans + dp[i][j];
	}
	public static int minPathSum3(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0];
			for (int j = 1; j < col; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		return dp[col - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 100);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowSize = 10;
		int colSize = 10;
		int[][] m = generateRandomMatrix(rowSize, colSize);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));

	}
}
