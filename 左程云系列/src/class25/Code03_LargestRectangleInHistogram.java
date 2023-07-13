package class25;

import java.util.Stack;

// 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
public class Code03_LargestRectangleInHistogram {

	public static int largestRectangleArea1(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static int largestRectangleArea2(int[] height) {
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
