package 栈队;

import java.util.*;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 */
public class 滑动窗口最大值 {

    /**
     * 要点在于维护一个队列能够动态表示当前窗口最大值，队头表示当前窗口最大值，依次递减
     * 当滑动窗口移动时有旧的元素出去新的元素进来
     * 需要判断是否需要出队，以及入队元素插入在哪
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        MyQue que = new MyQue();
        for(int i=0; i<k;i++){
            que.add(nums[i]);
        }
       int count = 0;
        int left = 1, right = k;
        ans[count++] = que.peek();

        while(right<nums.length){
            //更新窗口
            que.poll(nums[left-1]);
            que.add(nums[right]);

            //获取当前窗口最大值
            ans[count++] = que.peek();

            //移动窗口
            left++;
            right++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] window = maxSlidingWindow(nums, 3);
        System.out.println(window);
    }
    /**
     * 构建单调递减队列，按照从大->小记录元素值
     * 当窗口出元素时判断元素是否是当前最大值，是则移除
     * 当窗口进元素时判断是否大于末尾元素，大于则移除末尾元素保持单调递减
     */
    static class MyQue{

        Deque<Integer> que = new ArrayDeque<>();

        public void add(int data){
            //若新加的元素大于队尾元素，为了保持队列单调递减，去掉末尾元素
            while(!que.isEmpty() && data>que.getLast()){
                que.removeLast();
            }
            que.add(data);
        }


        public void poll(int data){
            if(!que.isEmpty() && data == que.peek()){
                que.poll();
            }
        }

        public int peek(){
            return que.peek();
        }
    }
}
