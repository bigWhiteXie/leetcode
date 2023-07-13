package class17;

import class11.Code04_IsFull;

/**
 * 有一组卡牌，两个人可以从两端抽一张牌直到牌抽光，抽牌总和最大的赢,返回最大的数
 * 	设甲乙两人都是及其聪明的人，每次都会抽出最优解
 * 	比如[1,3,100,4]
 * 	甲[1,100]              乙[4,3]
 */
public class Code02_CardsInLine {



	public static int win1(int[] arr){
		//1.由于可以从左或右边拿牌，故存在两个状态遍历left、right
		//它们都属于[0,n-1]
		int[][] fmap = new int[arr.length][arr.length];
		int[][] gmap = new int[arr.length][arr.length];

		//甲调用先手
		int r1 = f(arr, 0, arr.length - 1, fmap, gmap);
		//乙调用后手
		int r2 = g(arr, 0, arr.length - 1, fmap, gmap);

		return Math.max(r1,r2);

	}

	//该函数表示先手拿
	public static int f(int[] arr,int left,int right, int[][] fmap, int[][] gmap){
		if(fmap[left][right] != 0){
			return fmap[left][right];
		}

		//当抽到最后一张牌时，先手函数返回arr[left]
		if(left == right){
			fmap[left][right] = arr[left];
			return arr[left];
		}

		int r1 = arr[left] + g(arr,left+1,right,fmap,gmap);
		int r2 = arr[right] + g(arr,left,right-1,fmap,gmap);

		//两个人都绝顶聪明，先手可以选择此状态最大的结果
		fmap[left][right] = Math.max(r1,r2);

		return fmap[left][right];


	}
	public static int g (int[] arr,int left,int right, int[][] fmap, int[][] gmap){
		if(gmap[left][right] != 0){
			return gmap[left][right];
		}

		//当抽到最后一张牌时，后手函数抢不到牌
		if(left == right){
			return 0;
		}

		int r1 = f(arr,left+1,right,fmap,gmap);
		int r2 = f(arr,left,right-1,fmap,gmap);

		//后手只能被迫选择较小的结果，较大的结果被先手选了
		gmap[left][right] = Math.min(r1,r2);

		return gmap[left][right];


	}

	public static int win2(int[] arr){
		//1.由于可以从左或右边拿牌，故存在两个状态遍历left、right
		//它们都属于[0,n-1]
		int[][] fmap = new int[arr.length][arr.length];
		int[][] gmap = new int[arr.length][arr.length];

		//将状态初始化
		for(int i=0;i<arr.length; i++){
			fmap[i][i] = arr[i];
		}

		//由初始化得知，状态矩阵对角线已经确定，现在要求[0][arr.length-1]
		for (int i=arr.length-2;i>=0;i--){
			for (int j=i+1;j<arr.length;j++){
				fmap[i][j] = Math.max(arr[i] + gmap[i+1][j],arr[j] + gmap[i][j-1]);
				gmap[i][j] = Math.min(fmap[i+1][j],fmap[i][j-1]);
			}
		}


		return Math.max(fmap[0][arr.length-1],gmap[0][arr.length-1]);
	}

	public static int win3(int[] arr) {
		int[][] fmap = new int[arr.length][arr.length];
		int[][] gmap = new int[arr.length][arr.length];
		for(int i=0; i<arr.length; i++){
			fmap[i][i] = arr[i];
		}

		for(int startCol = 1; startCol < arr.length; startCol++){
			int r = 0;
			int col = startCol;
			while (col < arr.length){
				fmap[r][col] = Math.max(arr[r] + gmap[r+1][col],arr[col] + gmap[r][col-1]);
				gmap[r][col] = Math.min(fmap[r+1][col],fmap[r][col-1]);
				r++;
				col++;
			}
		}

		return Math.max(gmap[0][arr.length-1], fmap[0][arr.length-1]);
	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7,9,14,51 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));
		System.out.println(win3(arr));
	}

}
