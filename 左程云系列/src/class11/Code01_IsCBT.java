package class11;

import java.util.LinkedList;

/**
 * 判断一棵树是否是完全二叉树
 */
public class Code01_IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			    (leaf && (l != null || r != null)) 
			    || 
			    (l == null && r != null)

			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCBT2(Node head) {
		return precess(head).isCBT;
	}

	/**
	 * @param head
	 * @return
	 */
	private static Info precess(Node head) {
		if(head == null){
			return new Info(true,0,true);
		}

		Info l_info = precess(head.left);
		Info r_info = precess(head.right);

		int max_h = Math.max(l_info.height, r_info.height) + 1;
		boolean isFully = (l_info.hasFully && r_info.hasFully &&l_info.height == r_info.height);
		boolean isCBT = false;
		if(l_info.isCBT && r_info.isCBT){
			int dis = l_info.height - r_info.height;
			if(l_info.hasFully && r_info.hasFully ){
				if(dis >= 0 && dis <= 1){
					isCBT = true;
				}
			}

			if(l_info.hasFully && !r_info.hasFully){
				if(dis == 0){
					isCBT = true;
				}
			}

			if(!l_info.hasFully && r_info.hasFully){
				if(dis == 1){
					isCBT = true;
				}
			}
		}

		return new Info(isFully,max_h,isCBT);
	}

	static class Info{
		boolean hasFully;
		int height;
		boolean isCBT;

		public Info(boolean hasFully, int height, boolean isCBT) {
			this.hasFully = hasFully;
			this.height = height;
			this.isCBT = isCBT;
		}
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
			if (isCBT1(head) != isCBT2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
