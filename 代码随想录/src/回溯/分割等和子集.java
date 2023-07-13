package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 分割等和子集 {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public boolean canPartition(int[] nums) {
         backtracking(nums, 0);
         if(res.size() != 0){
             return true;
         }

         return false;
    }


    /**
     * 回溯：每个数字有两个选择，加入集合1或2
     * 等所有集合都加入后判断两个集合是否相等
     * @param nums
     * @param index
     * @return
     */
    public void backtracking(int[] nums,int index){
        if(index == nums.length){
            if(sum(l1) == sum(l2)){
                res.add(new ArrayList<>(l1));
                res.add(new ArrayList<>(l2));
                return ;
            }else {
                return;
            }
        }

        //1.加入集合1
        l1.add(nums[index]);
        backtracking(nums, index + 1);
        l1.remove(l1.size() - 1);


        //2. 加入集合2
        l2.add(nums[index]);
        backtracking(nums,index+1);
        l2.remove(l2.size()-1);


    }

    public int sum(List<Integer> list){
        int s = 0;
        for (Integer i : list) {
            s += i;
        }
        return s;
    }
}
