package class14;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/

/**
 * 题目表述
 * 	在一个班级里有N个同学， 有些同学是朋友，有些不是。他们之间的友谊是可以传递的比如A和B是朋友，
 * 	B和C是朋友，那么A和C也是朋友。我们定义 friend circle为由直接或者间接都是朋友组成的group.
 * 	Example 1:
 *   Input:
 * 		[[1,1,0],
 * 		[1,1,0],
 * 		[0,0,1]]
 * 	 Output: 2
 * 解释: 0和1号同学是朋友，所以他们是一个friend cricle，2号同学自己是个friend circle，所有返回2。
 *
 */
public class Code01_FriendCircles {

	public static int findCircleNum(int[][] M) {
		int N = M.length;
		UnionFind unionFind = new UnionFind(N);
		// {0} {1} {2} {N-1}
		for (int i = 0; i < M.length; i++) {
			for(int j = i+1; j<M[i].length;j++){
				if(M[i][j] == 1){
					unionFind.union(i,j);
				}
			}
		}
		return unionFind.sets;
	}

	public static int findCircleNum2(int[][] M){
		int n = M.length;
		class14.UnionFind.UnionArr<Integer> union = new class14.UnionFind.UnionArr<>(n);

		for(int i=0; i<n; i++){
			for(int j=i+1; j<M[i].length; j++){
				if(M[i][j] == 1){
					union.union(i,j);
				}
			}
		}

		return union.sets;
	}
	public static class UnionFind {
		int[] parent;
		int[] size;
		int[] help;
		int sets;

		public UnionFind(int sets) {
			this.sets = sets;
			parent = new int[sets];
			size = new int[sets];
			help = new int[sets];

			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public void union(int i, int j){
			int f1 = findFather(i);
			int f2 = findFather(j);

			if(f1 != f2){
				int more = size[f1] > size[f2] ? f1 : f2;
				int less = more == f1 ? f2 : f1;
				parent[less] = more;
				size[more] += size[less];
				size[less] = 0;
				sets--;
			}

		}

		public int sets(){
			return sets;
		}

		private int findFather(int i) {
			int index = 0;
			while(parent[i] != i){
				help[index++] = i;
				i = parent[i];
			}

			index--;
			while(index >= 0){
				parent[help[index--]] = i;
			}
			return i;
		}


	}

	public static void main(String[] args) {
		int[][] M = {{1,1,1},{1,1,1},{1,1,1}};
		System.out.println(findCircleNum(M));
		System.out.println(findCircleNum2(M));
	}


}
