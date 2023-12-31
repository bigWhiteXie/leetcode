package class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 求两个节点的最低公共祖先
 */
public class Code03_lowestAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		// key的父节点是value
		HashMap<Node, Node> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static Node lowestAncestor2(Node head, Node a, Node b) {
		return process(head, a, b).ans;
	}

	static class Info{
		boolean findA;
		boolean findB;
		Node ans;

		public Info(boolean findA, boolean findB, Node ans) {
			this.findA = findA;
			this.findB = findB;
			this.ans = ans;
		}
	}

	public static Info process(Node x, Node a, Node b) {
		if(x == null){
			return new Info(false,false,null);
		}

		Info l_info = process(x.left, a, b);
		Info r_info = process(x.right, a, b);

		if(l_info.ans != null){
			return new Info(true,true,l_info.ans);
		}

		if(r_info.ans != null){
			return new Info(true,true,r_info.ans);
		}

		boolean findA = l_info.findA || r_info.findA;
		boolean findB = l_info.findB || r_info.findB;

		if(x == a){
			findA = true;
		}

		if(x == b){
			findB = true;
		}

		Node ans = findA && findB ? x:null;

		return new Info(findA,findB,ans);

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

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
