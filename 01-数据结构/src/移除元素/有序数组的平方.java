package 移除元素;

import java.util.Arrays;

public class 有序数组的平方 {
    /**
     * 对撞双指针，由于平方的原因。较大的元素会分布在数组两端，可以采用双指针的方式
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int start = 0, end = nums.length - 1;
        int[] arr = new int[nums.length];
        int temp;
        int j = nums.length - 1;
        for(int i = 0; i <= end; i++){
            nums[i] = nums[i] * nums[i];
        }
        while(start <= end){

            if(nums[start] > nums[end]){
                arr[j--] = nums[start++];
            }else{
                arr[j--] = nums[end--];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] sortedSquares = sortedSquares(nums);
        System.out.println(Arrays.toString(sortedSquares));
    }

}
