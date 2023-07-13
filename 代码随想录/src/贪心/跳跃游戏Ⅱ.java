package 贪心;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;

public class 跳跃游戏Ⅱ {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for(int i=dp.length - 2;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for(int j=1;j<=nums[i];j++){
                if(i+j<=nums.length-1) {
                    if(dp[i+j] != Integer.MAX_VALUE) {
                        min = Math.min(min, 1 + dp[i + j]);
                    }
                }else{
                    min = 1;
                    break;
                }
            }
            dp[i] = min;
        }
        return dp[0];
    }

    public int backtracking(int[] nums, int index, Map<Integer,Integer> map){
        if(map.containsKey(index)){
            return map.get(index);
        }
        if(index >= nums.length - 1){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int j=1; j<=nums[index]; j++){
            int next = backtracking(nums, index + j, map);
            if(next != Integer.MAX_VALUE){
                min = Math.min(min,1+next);
            }
        }
        map.put(index,min);
        return min;
    }
}
