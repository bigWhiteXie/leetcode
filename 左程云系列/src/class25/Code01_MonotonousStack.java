package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组，返回每个元素左边第一个比他小的坐标和右边第一个比它小的坐标
 * 若没有则置为-1
 */
public class Code01_MonotonousStack {

	// arr = [ 3, 1, 2, 3]
	//         0  1  2  3
	//  [
	//     0 : [-1,  1]
	//     1 : [-1, -1]
	//     2 : [ 1, -1]
	//     3 : [ 2, -1]
	//  ]
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
				Integer index = stack.pop();
				int leftIndex = stack.isEmpty() ? -1 : stack.peek();
				res[index][0] = leftIndex;
				res[index][1] = i;
			}
			stack.push(i);
		}

		while(!stack.isEmpty()){
			Integer pop_i = stack.pop();
			int left_i = stack.isEmpty() ? -1 : stack.peek();
			res[pop_i][0] = left_i;
			res[pop_i][1] = -1;
		}

		return res;
	}

	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			while(!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
				List<Integer> list = stack.pop();
				int left_i = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
				for (Integer index : list) {
					res[index][0] = left_i;
					res[index][1] = i;
				}
			}
			if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
				stack.peek().add(i);
			}else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}

		while(!stack.isEmpty()){
			List<Integer> pop = stack.pop();
			int left_i = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
			for (Integer index : pop) {
				res[index][0] = left_i;
				res[index][1] = -1;

			}
		}
		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
//			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
//			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
//				System.out.println("Oops1!");
//				printArray(arr1);
//				break;
//			}
			if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
				System.out.println("Oops2!");
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}
}
