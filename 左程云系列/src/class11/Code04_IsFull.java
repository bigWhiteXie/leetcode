package class11;

/**
 * 判断该树是否是满二叉树
 */
public class Code04_IsFull {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isFull1(Node head) {
		if (head == null) {
			return true;
		}
		int height = h(head);
		int nodes = n(head);
		return (1 << height) - 1 == nodes;
	}

	public static int h(Node head) {
		if (head == null) {
			return 0;
		}
		return Math.max(h(head.left), h(head.right)) + 1;
	}

	public static int n(Node head) {
		if (head == null) {
			return 0;
		}
		return n(head.left) + n(head.right) + 1;
	}

	public static boolean isFull2(Node head) {
		if (head == null) {
			return true;
		}
		return process2(head).isFull;
	}

	public static class Info {
		public boolean isFull;
		public int nodes;
		public int h;

		public Info(boolean isFull, int nodes, int h) {
			this.isFull = isFull;
			this.nodes = nodes;
			this.h = h;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			return new Info(true,0,0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);

		if(!leftInfo.isFull || !rightInfo.isFull){
			return new Info(false,-1,-1);
		}

		int h = Math.max(leftInfo.h, rightInfo.h) + 1;
		int nodes = leftInfo.nodes + rightInfo.nodes + 1;
		boolean isFull = false;
		if((1<<h) -1 == nodes){
			isFull = true;
		}

		return new Info(isFull,nodes,h);

	}


	static class Info2{
		boolean isFull;
		int height;

		public Info2(boolean isFull, int height) {
			this.isFull = isFull;
			this.height = height;
		}
	}

	public static Info2 process2(Node x){
		if(x == null){
			return new Info2(true,0);
		}

		Info2 l_info = process2(x.left);
		Info2 r_info = process2(x.right);

		if(!l_info.isFull || !r_info.isFull){
			return new Info2(false,Math.max(l_info.height, r_info.height) + 1);
		}

		int h = Math.max(l_info.height, r_info.height) + 1;
		boolean isFull = false;

		if(l_info.isFull && r_info.isFull) {
			isFull = l_info.height == r_info.height ? true : false;
		}

		return new Info2(isFull,h);
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

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isFull1(head) != isFull2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
