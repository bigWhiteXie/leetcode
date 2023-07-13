package 贪心;

import java.util.Arrays;
import java.util.PriorityQueue;

public class k次取反后最大化数组和 {
    /**
     * 贪心策略：每次选择最小的元素进行反转
     * 具体实现：用小根堆放置所有元素，方便取出最小的元素
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
        }
        int i = 0;
        while(k>0){
            Integer poll = heap.poll();
            poll = -poll;
            heap.add(poll);
            k--;
        }

        int sum = 0;
        for (int num : heap) {
            sum += num;
        }
        return sum;
    }
}
