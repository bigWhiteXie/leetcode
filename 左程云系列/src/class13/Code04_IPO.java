package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 项目问题
 * 有初始资金w，每个项目需要c的初始资金、得到p的纯利润，最多能做k各项目，问w最多能到多少
 */
public class Code04_IPO {

	// 最多K个项目
	// W是初始资金
	// Profits[] Capital[] 一定等长
	// 返回最终最大的资金
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Program> minCost = new PriorityQueue<>((a, b) -> a.c - b.c);
		PriorityQueue<Program> maxProfit = new PriorityQueue<>((a, b) -> b.p - a.p);

		for (int i = 0; i < Capital.length; i++) {
			Program program = new Program(Profits[i], Capital[i]);
			minCost.add(program);
		}

		for(int i=0; i<K; i++){
			while(minCost.peek().c <= W){
				maxProfit.add(minCost.poll());
			}

			if(maxProfit.size() > 0){
				W += maxProfit.poll().p;
			}else{
				break;
			}
		}

		return W;
	}

	public static int findMax(int K, int W, int[] Profits, int[] Capital){
		int max = 0,initial = W;
		PriorityQueue<Program> minCostHeap = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) {
			minCostHeap.add(new Program(Profits[i],Capital[i]));
		}
		int i = 0;
		while(i<K){
			while (minCostHeap.peek().c <= initial){
				maxProfitHeap.add(minCostHeap.poll());
			}

			initial += maxProfitHeap.poll().p;
			i++;
		}

		return initial;
	}
	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
