package class11;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 递归判断一棵树是否为二叉排序树
 */
public class Code02_IsBST {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBST1(Node head) {
		if (head == null) {
			return true;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static boolean isBST2(Node head) {
		if (head == null) {
			return true;
		}
		return process2(head).isBST;
	}

	public static class Info {
		boolean isBST;
		int max;
		int min;

		public Info(boolean isBST, int max, int min) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}
	}

	public static Info process(Node x) {
		if (x == null) {
			return null;
		}

		Info l_info = process(x.left);
		Info r_info = process(x.right);

		if(r_info != null && !r_info.isBST){
			return new Info(false,x.value,x.value);
		}

		if(l_info != null && !l_info.isBST){
			return new Info(false,x.value,x.value);
		}

		if(l_info == null && r_info == null){
			return new Info(true,x.value,x.value);
		}else if(l_info == null && r_info != null){
			return r_info.min > x.value ? new Info(true, r_info.max, x.value) : new Info(false, Math.max(r_info.max, x.value),Math.min(x.value, r_info.min));
		}else if(l_info != null && r_info == null){
			return l_info.max < x.value ? new Info(true, x.value, l_info.min): new Info(false, Math.max(l_info.max ,x.value),Math.min(x.value, l_info.min));
		}

		int max = Math.max(Math.max(l_info.max, r_info.max),x.value);
		int min = Math.min(Math.min(l_info.min, r_info.min),x.value);

		boolean isBST = false;

		if((l_info.isBST && r_info.isBST) && (l_info.max < r_info.min)){
			if( x.value > l_info.max && x.value < r_info.min ){
				isBST = true;
			}
		}

		return new Info(isBST,max,min);
	}

	public static Info process2(Node x){
		if(x == null){
			return null;
		}

		Info l_info = process2(x.left);
		Info r_info = process2(x.right);

		int max = x.value, min = x.value;
		if(l_info != null){
			max = Math.max(max, l_info.max);
			min = Math.min(min, l_info.min);
		}

		if(r_info != null){
			max = Math.max(max, r_info.max);
			min = Math.min(min, r_info.min);
		}

		boolean isBST = false;
		if(l_info != null && r_info != null) {
			if (l_info.isBST && r_info.isBST) {
				if (l_info.max < r_info.min && l_info.max < x.value && r_info.min > x.value) {
					isBST = true;
				}
			}
		}else {
			if(l_info == null && r_info != null && r_info.isBST){
				isBST = r_info.min > x.value ? true:false;
			}else if(r_info == null && l_info != null && l_info.isBST ){
				isBST = l_info.max < x.value ? true :false;
			}else if(l_info == null && r_info == null){
				isBST = true;
			}
		}



		return new Info(isBST,max,min);
	}
	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
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

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		int oops = 0;
		int nice = 0;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			boolean bst1 = isBST1(head);
			boolean bst2 = isBST2(head);
			if (bst1 != bst2) {
				printTree(head);
				oops++;
				System.out.println("bst1: " + bst1);
				System.out.println("bst2: " + bst2);
			}else {
				nice++;
			}
		}
		System.out.println("oops:" + oops);
		System.out.println("nice:" + nice);
	}
//public static void main(String[] args) {
//	Node head = new Node(7);
//	head.right = new Node(98);
//	head.right.right = new Node(33);
//	isBST2(head);
//}

}
