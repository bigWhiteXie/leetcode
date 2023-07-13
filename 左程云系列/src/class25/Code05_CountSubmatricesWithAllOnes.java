package class25;

import java.util.Stack;

// 测试链接：https://leetcode.cn/problems/count-submatrices-with-all-ones/
public class Code05_CountSubmatricesWithAllOnes {

	public static int numSubmat(int[][] mat) {
		if(mat.length == 0){
			return 0;
		}
		int[] height = new int[mat[0].length];
		int count = 0;

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				height[j] = mat[i][j] == 0 ? 0 : 1+height[j];
			}
			count += countFromBottom(height);
		}

		return count;
	}

	public static int countFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int count = 0;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < height.length; i++) {
			while(!stack.isEmpty() && height[stack.peek()] >= height[i]){
				Integer minH = height[stack.pop()];
				int width = stack.isEmpty() ? i : i-1-stack.peek();
				int down = Math.max(height[i],stack.isEmpty()?0:height[stack.peek()]);
				count += (minH - down) * num(width);
			}
			stack.push(i);
		}

		while(!stack.isEmpty()){
			Integer minH = height[stack.pop()];
			int width = stack.isEmpty() ? height.length : (height.length-1-stack.peek()) ;
			int down = stack.isEmpty()?0:height[stack.peek()];
			count += (minH - down) * num(width);
		}

		return count;
	}

	public static int num(int n) {
		return ((n * (1 + n)) >> 1);
	}

}
