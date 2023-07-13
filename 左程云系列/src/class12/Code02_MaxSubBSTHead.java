package class12;

import class11.Code02_IsBST;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 给定一个头结点，返回这个二叉树中最大的二叉搜索树的头结点
 */
public class Code02_MaxSubBSTHead {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static Node maxSubBSTHead1(Node head) {
		if (head == null) {
			return null;
		}
		if (getBSTSize(head) != 0) {
			return head;
		}
		Node leftAns = maxSubBSTHead1(head.left);
		Node rightAns = maxSubBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
	}

	public static Node maxSubBSTHead2(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).maxSubBSTHead;
	}

	static class Info{
		Node maxSubBSTHead;
		int min;
		int max;
		boolean isBST;
		int maxSize;

		public Info(Node maxSubBSTHead, int min, int max, boolean isBST, int size) {
			this.maxSubBSTHead = maxSubBSTHead;
			this.min = min;
			this.max = max;
			this.isBST = isBST;
			this.maxSize = size;
		}
	}

	public static Info process(Node X) {
		if(X == null){
			return null;
		}

		Info l_info = process(X.left);
		Info r_info = process(X.right);


		if(l_info == null && r_info == null){
			return new Info(X, X.value, X.value,true,1);
		}else if(l_info != null  && r_info == null){
			if(l_info.isBST && l_info.max < X.value){
				return new Info(X, l_info.min,  X.value,true, l_info.maxSize+1);
			}
			return new Info(l_info.maxSubBSTHead, -1,-1,false, l_info.maxSize);
		}else if(r_info != null  && l_info == null){
			if(r_info.isBST && r_info.min > X.value){
				return new Info(X, X.value, r_info.max,true, r_info.maxSize+1);
			}
			return new Info(r_info.maxSubBSTHead, -1,-1,false, r_info.maxSize);
		}

		int min = Math.min(Math.min(l_info.min, r_info.min), X.value);
		int max = Math.max(Math.max(l_info.max, r_info.max), X.value);

		Node maxHead = l_info.maxSize >= r_info.maxSize ? l_info.maxSubBSTHead : r_info.maxSubBSTHead;
		boolean isBST = false;
		int maxSize = l_info.maxSize >= r_info.maxSize ? l_info.maxSize : r_info.maxSize;

		if(l_info.isBST && r_info.isBST){
			if(X.value < r_info.min && X.value > l_info.max){
				isBST = true;
				maxSize = r_info.maxSize +l_info.maxSize + 1;
				maxHead = X;
			}
		}

		return new Info(maxHead,min,max,isBST,maxSize);


	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void printTree(Node head){
		Stack<Node> stack = new Stack<>();
		stack.push(head);
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(node != null){
				System.out.print(node.value + " ");
				stack.push(node.right);
				stack.push(node.left);
			}else{
				System.out.print("# ");
			}

		}
		System.out.println("");
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		int count = 0;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node head1 = maxSubBSTHead1(head);
			Node head2 = maxSubBSTHead2(head);
			if (head2 != head1) {
				printTree(head);
				count++;
			}
		}
		System.out.println("count = " + count);
		System.out.println("finish!");
	}

}
