package 移除元素;

/**
 * 对撞指针往往会改变元素的相对顺序。但是快慢指针不会
 */
public class 移动0 {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] nums = {0,1,0,3,12};
        int[] zeroes = solution2.moveZeroes(nums);
        for(int item:zeroes){
            System.out.println(item);
        }
    }
}
class Solution2 {
    public int[] moveZeroes(int[] nums) {
       int slow = 0,num = 0;
       for(int i=0; i < nums.length; i++){
           if(nums[i] != 0){
               nums[slow++] = nums[i];
           }else{
               num++;
           }
       }
       int end = nums.length - 1;
       for(int i=nums.length-1;num>0; i--,num--){
           nums[i] = 0;
       }
       return nums;
    }
}