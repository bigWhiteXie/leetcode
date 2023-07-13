package class25;

import java.util.Stack;

/**
 * 给定一个数组，求满足要求的子序列的最大值
 * 要求：子序列的最小值 * 子序列的累加和
 *
 * 思路一：0-0 0-1 0-2 0-n 1-1 1-2 ... 这样遍历所有子序列，求出子序列中的最小值，再乘以子序列累加和后与max做比较
 *
 * 思路二：先求出数组的前缀和，再以每个元素为最小值，
 * 查看当该元素为最小值时数组的范围，用累加和数组算出该范围的和，再乘以该元素
 */
public class Code02_AllTimesMinToMax {

	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, minNum * sum);
			}
		}
		return max;
	}

	public static int max2(int[] arr) {
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		int[] sum = new int[arr.length];
		sum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			sum[i] = sum[i-1] + arr[i];
		}

		for (int i = 0; i < arr.length; i++) {
			while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
				Integer minNum = arr[stack.pop()];
				int l_index = stack.isEmpty() ? -1 : stack.peek();
				max =l_index != -1 ? Math.max(max,(sum[i-1]-sum[l_index])*minNum) : Math.max(max,sum[i-1]*minNum);
			}
			stack.push(i);
		}

		while (!stack.isEmpty()){
			Integer minNum = arr[stack.pop()];
			int l_index = stack.isEmpty() ? -1:stack.peek();

			max =l_index == -1 ?  Math.max(max,sum[arr.length-1]*minNum) : Math.max(max,(sum[arr.length-1] - sum[l_index])*minNum);
		}

		return max;
	}

	public static int[] gerenareRondomArray() {
		int[] arr = new int[(int) (Math.random() * 20) + 10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 101);
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTimes = 2000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerenareRondomArray();
			if (max1(arr) != max2(arr)) {
				System.out.println("FUCK!");
				break;
			}
		}
		System.out.println("test finish");
	}

}
