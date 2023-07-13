package 移除元素;

public class 移除元素 {
}
class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0 ;
        for(int i=0; i < nums.length ; i++){
           if(nums[i] != val){
               nums[slow++] = nums[i];
           }
        }
        return slow;
    }
}