package 贪心;

public class 跳跃游戏 {
    public boolean canJump(int[] nums) {
        int max = 0,i=0;
       while(i<nums.length){
           int right = i+nums[i];
           if(right > max){
               max = right;
           }
           if(max >= nums.length-1){
               return true;
           }
           if(i == max && nums[i] == 0){
               return false;
           }
           i++;
       }
       return false;
    }


}
