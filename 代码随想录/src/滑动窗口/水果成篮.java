package 滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class 水果成篮 {
    /**
     * 递归行不通
     * @param nums
     * @param index
     * @param res
     * @param map
     * @return
     */
    public static int getMaxFruit(int[] nums, int index, int res, HashMap<Integer,Integer> map){
        if(res < 0 || index == nums.length || (map.size() == 2 && !map.containsKey(nums[index]))){
            return 0;
        }
        int r1 = 0;
        if(map.containsKey(nums[index])) {
            map.put(nums[index],map.get(nums[index])+1);
            r1 = 1 + getMaxFruit(nums, index + 1, res,map);
        }else if(res > 0){
            map.put(nums[index],1);
            r1 = 1 + getMaxFruit(nums,index+1,res-1,map);
        }else {
            return 0;
        }


        map.computeIfPresent(nums[index], (key, val) -> {
            if (val == 1) {
                return null;
            }
            return val - 1;
        });

        int r2 = getMaxFruit(nums, index + 1, res, map);

        return Math.max(r1,r2);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(getMaxFruit(nums, 0, 2,map));
    }
}
