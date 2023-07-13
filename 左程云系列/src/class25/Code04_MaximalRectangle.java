package class25;

import java.util.Stack;

// 测试链接：https://leetcode.com/problems/maximal-rectangle/

/**
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
 * 注意：此题 matrix 输入格式为一维 01 字符串数组。
 *
 */
public class Code04_MaximalRectangle {

	public static int maximalRectangle(String[] martix) {

		if(martix.length == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;

		int[] height = new int[martix[0].length()];

		for (int i = 0; i < martix.length; i++) {
			for (int j = 0; j < martix[i].length(); j++) {
				height[j] = martix[i].charAt(j) == '0' ? 0 : height[j]+1;
			}
			int area = largestRectangleArea(height);
			max = Math.max(area,max);
		}
		return max;
	}

	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < height.length; i++) {
			while(!stack.isEmpty() && height[stack.peek()] >= height[i]){
				Integer minH = height[stack.pop()];
				int area = stack.isEmpty() ? (i)*minH : (i-1-stack.peek()) * minH;
				max = Math.max(max,area);
			}
			stack.push(i);
		}

		while(!stack.isEmpty()){
			Integer minH = height[stack.pop()];
			int area = stack.isEmpty() ? (height.length)*minH : (height.length-1-stack.peek()) * minH;
			max = Math.max(max,area);
		}

		return max;
	}



}
