package class14;

import javax.swing.*;
import java.util.*;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands/
// 所有方法都可以直接通过

/**
 * 题目描述
 * 	 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 	 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 	 你可以假设网格的四个边均被水包围。
 * 	 Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 */
public class Code02_NumberOfIslands {

	public static int numIslands3(char[][] board) {
		int islands = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '1') {
					islands++;
					infect(board, i, j);
				}
			}
		}
		return islands;
	}

	// 从(i,j)这个位置出发，把所有练成一片的'1'字符，变成0
	public static void infect(char[][] board, int i, int j) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
			return;
		}
		board[i][j] = 0;
		infect(board, i - 1, j);
		infect(board, i + 1, j);
		infect(board, i, j - 1);
		infect(board, i, j + 1);
	}

	public static int numIslands1(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		Dot[][] dots = new Dot[row][col];
		List<Dot> dotList = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == '1') {
					dots[i][j] = new Dot();
					dotList.add(dots[i][j]);
				}
			}
		}
		UnionFind1<Dot> uf = new UnionFind1<>(dotList);
		for (int j = 1; j < col; j++) {
			// (0,j)  (0,0)跳过了  (0,1) (0,2) (0,3)
			if (board[0][j - 1] == '1' && board[0][j] == '1') {
				uf.union(dots[0][j - 1], dots[0][j]);
			}
		}
		for (int i = 1; i < row; i++) {
			if (board[i - 1][0] == '1' && board[i][0] == '1') {
				uf.union(dots[i - 1][0], dots[i][0]);
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == '1') {
					if (board[i][j - 1] == '1') {
						uf.union(dots[i][j - 1], dots[i][j]);
					}
					if (board[i - 1][j] == '1') {
						uf.union(dots[i - 1][j], dots[i][j]);
					}
				}
			}
		}
		return uf.sets();
	}

	public static class Dot {

	}

	public static class Node<V> {

		V value;

		public Node(V v) {
			value = v;
		}

	}

	public static class UnionFind1<V> {
		public HashMap<V, Node<V>> nodes;
		public HashMap<Node<V>, Node<V>> parents;
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionFind1(List<V> values) {
			nodes = new HashMap<>();
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V cur : values) {
				Node<V> node = new Node<>(cur);
				nodes.put(cur, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			while (!path.isEmpty()) {
				parents.put(path.pop(), cur);
			}
			return cur;
		}

		public void union(V a, V b) {
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
				Node<V> small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				sizeMap.put(big, aSetSize + bSetSize);
				sizeMap.remove(small);
			}
		}

		public int sets() {
			return sizeMap.size();
		}

	}

	public Code02_NumberOfIslands() {
	}

	public static int numIslands2(int[][] board) {
		UnionMap unionFind2 = new UnionMap(board);

		for(int i=1; i<board[0].length; i++){
			if(board[0][i] == 1 && board[0][i-1] == 1){
				unionFind2.union(0,i,0,i-1);
			}
		}

		for(int i=1; i<board.length; i++){
			if(board[i][0] == 1 && board[i-1][0] == 1){
				unionFind2.union(i,0,i-1,0);
			}
		}

		for(int i=1; i<board.length; i++){
			for(int j=1;j<board[i].length; j++){
				int c = board[i][j];
				if(c == 1){
					if(c == board[i-1][j]){
						unionFind2.union(i,j,i-1,j);
					}
					if(c == board[i][j-1]){
						unionFind2.union(i,j,i,j-1);
					}
				}
			}
		}

		int max = 0;
		Set<String> keySet = unionFind2.size.keySet();
		for (String key : keySet) {
			max = max < unionFind2.size.get(key) ? unionFind2.size.get(key) : max;
		}

		return max;


	}

	public static class UnionFind2 {
		int[] parent;
		int[] help;
		Map<Integer,Integer> mapSize = new HashMap<>();
		int row;
		int col;
		int sets;
		public UnionFind2(int[][] board) {
			row = board.length;
			col = board[0] != null ? board[0].length:0;
			parent = new int[row*col];
			help = new int[row*col];
			for (int i = 0; i < board.length; i++) {
				for(int j=0; j<board[i].length; j++){
					if (board[i][j] == 1){
						int index = index(i, j);
						parent[index] = index;
						mapSize.put(index,1);
						sets++;
					}
				}
			}
		}

		public void union(int r1, int c1, int r2,int c2){
			int index1 = index(r1, c1);
			int index2 = index(r2, c2);

			int f1 = findFather(index1);
			int f2 = findFather(index2);

			if(f1 != f2){
				int more = mapSize.get(f1) > mapSize.get(f2) ? f1 : f2;
				int less = more == f1 ? f2 : f1;

				parent[less] = more;
				mapSize.put(more,mapSize.get(more) + mapSize.get(less));
				mapSize.remove(less);
				sets--;
			}
		}
		private int findFather(int i){
			int hi = 0;
			while(parent[i] != i){
				help[hi++] = i;
				i = parent[i];
			}

			hi--;

			while(hi >= 0){
				parent[help[hi--]] = i;
			}

			return i;
		}

		private int index(int i, int j){
			return i*col + j;
		}
	}

	static class UnionMap{
		Map<String, String> parent;
		Map<String,Integer> size;

		public UnionMap(int[][] M) {
			parent = new HashMap<>();
			size = new HashMap<>();
			for (int i = 0; i < M.length; i++) {
				for (int j = 0; j < M[i].length; j++) {
					if(M[i][j] == 1){
						String key = key(i, j);
						parent.put(key,key);
						size.put(key,1);
					}
				}
			}
		}

		private String key(int row, int col){
			return String.valueOf(row) + "_" + String.valueOf(col);
		}

		public String findFather(String key){
			Stack<String> stack = new Stack<>();

			while(!parent.get(key).equals(key)){
				stack.push(key);
				key = parent.get(key);
			}

			while(!stack.isEmpty()){
				String pop = stack.pop();
				parent.put(pop,key);
			}

			return key;
		}

		public void union(int r1, int c1, int r2, int c2){
			String k1 = key(r1, c1);
			String k2 = key(r2, c2);

			if(parent.containsKey(k1) && parent.containsKey(k2)){
				String f1 = findFather(k1);
				String f2 = findFather(k2);
				if(!f1.equals(f2)){
					Integer s1 = size.get(f1);
					Integer s2 = size.get(f2);

					String more = s1 >= s2 ? f1 : f2;
					String less = f1 == more ? f2 : f1;

					parent.put(less,more);
					size.put(more,s1+s2);
					size.remove(less);
				}
			}
		}
		public int sets(){
			return size.size();
		}
	}


	// 为了测试
	public static char[][] generateRandomMatrix(int row, int col) {
		char[][] board = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = Math.random() < 0.5 ? '1' : '0';
			}
		}
		return board;
	}

	// 为了测试
	public static char[][] copy(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		char[][] ans = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ans[i][j] = board[i][j];
			}
		}
		return ans;
	}

	// 为了测试
//	public static void main(String[] args) {
//		int row = 0;
//		int col = 0;
//		char[][] board1 = null;
//		char[][] board2 = null;
//		char[][] board3 = null;
//		long start = 0;
//		long end = 0;
//
//		row = 1000;
//		col = 1000;
//		board1 = generateRandomMatrix(row, col);
//		board2 = copy(board1);
//		board3 = copy(board1);
//
//		System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
//		System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);
//
//		start = System.currentTimeMillis();
//		System.out.println("感染方法的运行结果: " + numIslands3(board1));
//		end = System.currentTimeMillis();
//		System.out.println("感染方法的运行时间: " + (end - start) + " ms");
//
//		start = System.currentTimeMillis();
//		System.out.println("并查集(map实现)的运行结果: " + numIslands1(board2));
//		end = System.currentTimeMillis();
//		System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");
//
//		start = System.currentTimeMillis();
//		System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
//		end = System.currentTimeMillis();
//		System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
//
//		System.out.println();
//
//		row = 10000;
//		col = 10000;
//		board1 = generateRandomMatrix(row, col);
//		board3 = copy(board1);
//		System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
//		System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);
//
//		start = System.currentTimeMillis();
//		System.out.println("感染方法的运行结果: " + numIslands3(board1));
//		end = System.currentTimeMillis();
//		System.out.println("感染方法的运行时间: " + (end - start) + " ms");
//
//		start = System.currentTimeMillis();
//		System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
//		end = System.currentTimeMillis();
//		System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
//
//	}

	public static void main(String[] args) {
		int[][] a = {{1,0,1},{1,1,1}};
		System.out.println(numIslands2(a));
	}
}
