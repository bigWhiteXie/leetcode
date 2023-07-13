package class16;

import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if(!stack.isEmpty()) {
			int bottom = f(stack);
			reverse(stack);
			stack.push(bottom);
		}

	}

	// 栈底元素移除掉
	// 上面的元素盖下来
	// 返回移除掉的栈底元素
	public static int f(Stack<Integer> stack) {
		if (stack.size() == 1){
			return stack.pop();
		}

		int cur = stack.pop();
		int bottom = f(stack);
		stack.add(cur);
		return bottom;
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
