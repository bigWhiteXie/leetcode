package class31;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 在二维平面上的 x 轴上，放置着一些方块。
 *
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi]
 * 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 *
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，
 * 直到着陆到 另一个正方形的顶边 或者是 x 轴上 。一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。
 * 一旦着陆，它就会固定在原地，无法移动。
 *
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 *
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 */
public class Code02_FallingSquares {

	public static class SegmentTree {
		private int[] max;
		private int[] change;
		private boolean[] update;

		public SegmentTree(int size) {
			max = new int[size << 2];
			change = new int[size << 2];
			update = new boolean[size << 2];
		}

		public void update(int L, int R,int num,int l,int r,int rt){
			if(l>=L && r<=R){
				update[rt] = true;
				change[rt] = num;
				max[rt] = num;
				return;
			}
			pushdown(rt);

			int mid = l + (r-l)/2;
			if(L<=mid){
				update(L,R,num,l,mid,rt<<1);
			}
			if(R > mid){
				update(L,R,num,mid+1,r,rt<<1|1);
			}
			recall(rt);
		}


		private void recall(int rt) {
			max[rt] = Math.max(max[rt<<1],max[rt<<1|1]);
		}

		private void pushdown(int rt) {
			if(update[rt]){
				update[rt] = false;

				update[rt<<1] = true;
				update[rt<<1|1] = true;
				change[rt<<1] = change[rt];
				change[rt<<1|1] = change[rt];
				max[rt<<1] = change[rt];
				max[rt<<1|1] = change[rt];
				change[rt] = 0;
			}
		}

		public int query(int L, int R, int l, int r, int rt) {
			if(l >= L && r <= R){
				return max[rt];
			}
			pushdown(rt);

			int mid = l + (r-l)/2;
			int left = Integer.MIN_VALUE,right = Integer.MIN_VALUE;
			if(L<=mid){
				left = query(L,R,l,mid,rt<<1);
			}
			if(R > mid){
				right = query(L,R,mid+1,r,rt<<1|1);
			}
			recall(rt);
			return Math.max(left,right);
		}
	}

	/**
	 * 将离散的positions转化为连续的数组
	 * 目的：将范围缩小，每个position代表数组的一个范围
	 * @param positions
	 * @return
	 */
	public static HashMap<Integer, Integer> index(int[][] positions) {
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (int[] position : positions) {
			treeSet.add(position[0]);
			treeSet.add(position[0] + position[1] - 1);
		}
		int index = 0;
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (Integer i : treeSet) {
			indexMap.put(i,++index);
		}
		return indexMap;
	}

	public static List<Integer> fallingSquares(int[][] positions) {
		HashMap<Integer, Integer> map = index(positions);
		int N = map.size();
		SegmentTree segmentTree = new SegmentTree(N);
		int max = 0;
		List<Integer> res = new ArrayList<>();
		// 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
		for (int[] arr : positions) {
			int L = map.get(arr[0]);
			int R = map.get(arr[0] + arr[1] - 1);
			int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
			max = Math.max(max, height);
			res.add(max);
			segmentTree.update(L, R, height, 1, N, 1);
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] position = {{100,100},{200,100}};
		List<Integer> list = fallingSquares(position);
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}

}
