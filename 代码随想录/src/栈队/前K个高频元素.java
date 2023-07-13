package 栈队;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 */
public class 前K个高频元素 {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> b.v - a.v);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());
            heap.add(pair);
        }

        int[] ans = new int[k];
        int count = 0;

        while(count < k){
            ans[count++] = heap.poll().k;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int[] frequent = topKFrequent(nums, 2);
        printArr(frequent);
    }

    public static void printArr(int[] arr ){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    static class Pair{
        int k;
        int v;

        public Pair(Integer key, Integer value) {
            k = key;
            v = value;
        }
    }
}
