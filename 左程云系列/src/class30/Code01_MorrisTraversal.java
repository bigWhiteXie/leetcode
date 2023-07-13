package class30;

public class Code01_MorrisTraversal {

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * mirros遍历
	 * 判断当前cur节点是否有左孩子
	 *   若有左孩子，找到左子树的最右节点
	 *   	1. 若最右节点的右孩子指向null，将其指向cur，cur左移
	 *      2. 若最右节点的右孩子指向cur，右移
	 *   若没有左孩子，直接右移
	 * 当cur指向空的时候循环结束
	 * @param root
	 */
	public static void process2(Node root){
		Node cur = root;
		while(cur != null){
			Node left = cur.left;
			if(left != null){
				Node mostRight = left;
				while(mostRight.right != null && mostRight.right != null){
					mostRight = mostRight.right;
				}

				if(mostRight.right == null){
					mostRight.right = cur;
					cur = cur.left;
				}else {
					mostRight.right = null;
					cur = cur.right;
				}
			}else {
				cur = cur.right;
			}
		}
	}
	public static void process(Node root){
		Node cur = root;
		while (cur != null){
			System.out.print(cur.value+ " ");
			Node leftChild = cur.left;
			if(leftChild != null){
				while (leftChild.right != null && leftChild.right != cur){
					leftChild = leftChild.right;
				}
				if(leftChild.right == null){
					leftChild.right = cur;
					cur = cur.left;
					continue;
				}else {
					leftChild.right = null;
				}
			}
			cur = cur.right;

		}
	}


	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight = null;
		Integer pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre >= cur.value) {
				ans = false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return ans;
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
//		process(head);
//		morrisIn(head);
		System.out.println();
//		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

	/**
	 * cur节点有左子树时，会经过该cur两次
	 * 在第二次经过时逆序遍历左子树的right边
	 * cur为空时，反向遍历root的right边
	 * 问题：为什么只用遍历右边？
	 * 因为在进行回溯过程中，由低->高已经把左边遍历过了
	 * @param root
	 */
	private static void morrisPos(Node root) {
		Node cur = root;
		while (cur != null){
			Node leftChild = cur.left;
			if(leftChild != null){
				while (leftChild.right != null && leftChild.right != cur){
					leftChild = leftChild.right;
				}
				if(leftChild.right == null){
					leftChild.right = cur;
					cur = cur.left;
					continue;
				}else {
					leftChild.right = null;
					printEdge(cur.left);
				}
			}
			cur = cur.right;

		}
		printEdge(root);
	}

	/**
	 * 遍历到cur节点时判断
	 * 若cur有左子树，且左子树最右节点指向自己，打印
	 * 否则cur向左移动
	 * 若cur无左子树，则直接打印
	 * @param root
	 */
	private static void morrisIn(Node root) {
		Node cur = root;
		while (cur != null){
			Node leftChild = cur.left;
			if(leftChild != null){
				while (leftChild.right != null && leftChild.right != cur){
					leftChild = leftChild.right;
				}
				if(leftChild.right == null){
					leftChild.right = cur;
					cur = cur.left;
					continue;
				}else {
					leftChild.right = null;
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}else {
				System.out.print(cur.value + " ");
				cur = cur.right;
			}


		}
	}

	/**
	 * 当若cur有左子树且左子树最右节点为null时打印
	 * 当若cur无左子树时直接打印
	 * @param root
	 */
	private static void morrisPre(Node root) {
		Node cur = root;
		while (cur != null){

			Node leftChild = cur.left;
			if(leftChild != null){
				while (leftChild.right != null && leftChild.right != cur){
					leftChild = leftChild.right;
				}
				if(leftChild.right == null){
					System.out.print(cur.value + " ");
					leftChild.right = cur;
					cur = cur.left;
					continue;
				}else {
					leftChild.right = null;
				}
			}else {
				System.out.print(cur.value + " ");
			}
			cur = cur.right;

		}
	}

}
