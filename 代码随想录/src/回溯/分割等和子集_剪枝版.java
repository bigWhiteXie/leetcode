package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 分割等和子集_剪枝版 {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();

    public boolean canPartition(int[] nums) {
        return backtracking(nums, 0);
    }


    /**
     * 回溯剪枝版
     * @param nums
     * @param index
     * @return
     */
    public boolean backtracking(int[] nums,int index){
        if(index == nums.length){
            if(sum(l1) == sum(l2)){
                return true;
            }else {
                return false;
            }
        }

        //1.加入集合1
        l1.add(nums[index]);
        boolean b = backtracking(nums, index + 1);
        if(b){
            return true;
        }
        l1.remove(l1.size() - 1);


        //2. 加入集合2
        l2.add(nums[index]);
        boolean b1 = backtracking(nums, index + 1);
        l2.remove(l2.size()-1);


        return b1;


    }

    public int sum(List<Integer> list){
        int s = 0;
        for (Integer i : list) {
            s += i;
        }
        return s;
    }
}
