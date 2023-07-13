package class11;

import java.util.ArrayList;

/**
 * 获得最大排序二叉树的节点个数
 */
public class Code05_MaxSubBSTSize {

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

	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
	}


	public static int maxSubBSTSize2(Node head) {
		if(head == null){
			return 0;
		}

		return process(head).nodes;
	}

	static class Info{
		int max;
		int min;
		int nodes;
		boolean isBST;

		public Info(int max, int min, int nodes, boolean isBST) {
			this.max = max;
			this.min = min;
			this.nodes = nodes;
			this.isBST = isBST;
		}
	}



	public static Info process(Node x) {
		if(x == null){
			return null;
		}

		Info l_info = process(x.left);
		Info r_info = process(x.right);

		if(l_info!= null && !l_info.isBST){
			return r_info != null? new Info(-1,-1,Math.max(l_info.nodes,r_info.nodes),false):new Info(-1,-1, l_info.nodes ,false);
		}
		if(r_info!= null && !r_info.isBST){
			return l_info != null? new Info(-1,-1,Math.max(l_info.nodes,r_info.nodes),false):new Info(-1,-1, r_info.nodes ,false);
		}

		if(l_info == null && r_info == null){
			return new Info(x.value,x.value,1,true);
		}else if(l_info == null && r_info != null){
			if(x.value < r_info.min){
				return new Info(r_info.max,x.value, r_info.nodes+1,true );
			}else{
				return new Info(-1,-1, r_info.nodes,false );
			}
		}else if(r_info == null && l_info != null){
			if(x.value > l_info.max){
				return new Info(x.value, l_info.min, l_info.nodes+1,true );
			}else{
				return new Info(-1,-1, l_info.nodes,false );
			}
		}

		int max = Math.max(Math.max(r_info.max, l_info.max),x.value);
		int min = Math.min(Math.min(r_info.min, l_info.min),x.value);
		int nodes = -1;
		boolean isBST = false;
		if(l_info.max < x.value && r_info.min > x.value){
			isBST = true;
			nodes = l_info.nodes + r_info.nodes + 1;
		}else{
			nodes = Math.max(l_info.nodes,r_info.nodes);
		}

		return new Info(max,min,nodes, isBST);


	}

	public static Info process2(Node x){
		if(x == null){
			return null;
		}

		Info l_info = process2(x.left);
		Info r_info = process2(x.left);

		int max = x.value, min = x.value;
		int nodes = 0;
		if(l_info != null){
			max = Math.max(max, l_info.max);
			min = Math.min(min, l_info.min);
			nodes = Math.max(nodes,l_info.nodes);
		}

		if(r_info != null){
			max = Math.max(max, r_info.max);
			min = Math.min(min, r_info.min);
			nodes = Math.max(nodes,r_info.nodes);
		}

		boolean isBST = false;
		if(l_info != null && r_info != null) {
			if (l_info.isBST && r_info.isBST) {
				if (l_info.max < r_info.min && l_info.max < x.value && r_info.min > x.value) {
					isBST = true;
					nodes += 1;
				}
			}
		}else {
			if(l_info == null && r_info != null && r_info.isBST){
				isBST = r_info.min > x.value ? true:false;
				nodes = isBST ? nodes+1 : nodes;

			}else if(r_info == null && l_info != null && l_info.isBST ){
				isBST = l_info.max < x.value ? true :false;
				nodes = isBST ? nodes+1 : nodes;
			}else if(l_info == null && r_info == null){
				isBST = true;
				nodes += 1;
			}
		}

		return new Info(max,min,nodes,isBST);
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
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
