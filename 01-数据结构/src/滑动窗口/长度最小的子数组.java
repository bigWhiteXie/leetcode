package 滑动窗口;

public class 长度最小的子数组 {
    public static int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE,size = 0,sum = 0;
        int j = 0;
        for(int i=0; i < nums.length ;i++){
           sum += nums[i];
           while(sum >= target){
               size = i -j + 1;
               min = size < min ? size:min;
               sum -= nums[j++];
           }

        }
        return min == Integer.MAX_VALUE ? 0 :min;
    }

    public static void main(String[] args) {
        int target = 7, nums[] = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target,nums));
    }
}
