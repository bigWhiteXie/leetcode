package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 全排列 {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        backtracking(nums,set);
        return res;
    }

    public void backtracking(int[] nums, Set<Integer> set){
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
        }

        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                path.add(nums[i]);
                backtracking(nums,set);
                set.remove(nums[i]);
                path.remove(path.size()-1);
            }
        }
    }
}
