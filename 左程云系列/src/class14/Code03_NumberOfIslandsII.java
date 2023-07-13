package class14;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
// 所有方法都可以直接通过
/**
 * 题目描述：
 * 	有一个m*n的区域，positions中的每个元素代表一个岛屿的坐标(x,y)，求这片区域岛屿的数量
 * 	例题：postions：[[0,0], [0,1],[1,0], [2,2]] ，结果为2
 * 动态并查集
 * 		初始化：根据矩阵大小生成 m*n的parent[]]和size[]
 * 		连接新元素：若该元素此前没有进来过，则对其parent和size进行初始化、set+1，再让他与四个方向上的节点区union，若union成功则set-1
 */
public class Code03_NumberOfIslandsII {

	public static List<Integer> numIslands21(int m, int n, int[][] positions) {
		UnionFind1 uf = new UnionFind1(m, n);
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(uf.connect(position[0], position[1]));
		}
		return ans;
	}

	public static class UnionFind1 {
		private int[] parent;
		private int[] size;
		private int[] help;
		private final int row;
		private final int col;
		private int sets;

		public UnionFind1(int m, int n) {
			row = m;
			col = n;
			sets = 0;
			int len = row * col;
			parent = new int[len];
			size = new int[len];
			help = new int[len];
		}

		private int index(int r, int c) {
			return r * col + c;
		}

		private int find(int i) {
			int hi = 0;
			while (i != parent[i]) {
				help[hi++] = i;
				i = parent[i];
			}
			for (hi--; hi >= 0; hi--) {
				parent[help[hi]] = i;
			}
			return i;
		}

		private void union(int r1, int c1, int r2, int c2) {
			if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
				return;
			}
			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			if (size[i1] == 0 || size[i2] == 0) {
				return;
			}
			int f1 = find(i1);
			int f2 = find(i2);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					size[f1] += size[f2];
					parent[f2] = f1;
				} else {
					size[f2] += size[f1];
					parent[f1] = f2;
				}
				sets--;
			}
		}

		public int connect(int r, int c) {
			int index = index(r, c);
			if (size[index] == 0) {
				parent[index] = index;
				size[index] = 1;
				sets++;
				union(r - 1, c, r, c);
				union(r + 1, c, r, c);
				union(r, c - 1, r, c);
				union(r, c + 1, r, c);
			}
			return sets;
		}

	}

	// 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
	public static List<Integer> numIslands22(int m, int n, int[][] positions) {
		UnionFind2 unionFind2 = new UnionFind2(m, n);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < positions.length; i++) {
			int ans = unionFind2.connect(positions[i][0], positions[i][1]);
			list.add(ans);
		}
		return list;
	}

	public static class UnionFind2 {
		private HashMap<String,String> parentMap;
		private HashMap<String,Integer> sizetMap;
		private List<String> list;
		private int sets;
		int rows;
		int cols;

		public UnionFind2(int m, int n) {
			parentMap = new HashMap<>();
			sizetMap = new HashMap<>();
			list = new ArrayList<>();
			rows = m;
			cols = n;
		}

		public void union(String k1, String k2){
			if(parentMap.get(k1) != null && parentMap.get(k2) != null){
				String f1 = findFather(k1);
				String f2 = findFather(k2);
				if(!f1.equals(f2)) {
					String more = sizetMap.get(f1) > sizetMap.get(f2) ? f1 : f2;
					String less = more == f2 ? f1 : f2;

					parentMap.put(less, more);
					sizetMap.put(more, sizetMap.get(more) + sizetMap.get(less));
					sizetMap.put(less, 0);

					sets--;
				}

			}
		}

		private String findFather(String k1){
			while (!parentMap.get(k1).equals(k1)){
				list.add(k1);
				k1 = parentMap.get(k1);
			}

			for (String s : list) {
				parentMap.put(s,k1);
			}

			list.clear();
			return k1;
		}
		public int connect(int r, int j){
			String key = key(r, j);
			if(parentMap.get(key) == null){
				parentMap.put(key,key);
				sizetMap.put(key,1);
				sets++;
				String left = key(r,j-1);
				String right = key(r,j+1);
				String up = key(r-1,j);
				String down = key(r+1,j);
				union(key,left);
				union(key,right);
				union(key,up);
				union(key,down);
			}
			return sets;
		}

		public String key(int r, int j){
			if(r >= 0 && r< rows && j >= 0 && j < cols) {
				return String.valueOf(r) + "_" + String.valueOf(j);
			}
			return "";
		}
	}

	public static void main(String[] args) {
		int[][] pos = {{0,0},{0,1},{0,3},{1,0},{1,1},{1,2},{1,3}};
		List<Integer> list = numIslands21(2, 4, pos);
		List<Integer> list1 = numIslands22(2, 4, pos);
		for (int i = 0; i < list.size(); i++) {
			if(!list1.get(i).equals(list.get(i))){
				System.out.println("oops");
				break;
			}
		}

		System.out.println("finish");
	}

}
