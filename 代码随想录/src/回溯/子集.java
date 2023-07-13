package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 子集 {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    private Set<Integer> used = new HashSet<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums,0);
        return res;
    }

    public void backtracking(int[] nums,int index){
        res.add(path);
        if(index == nums.length){
            return;
        }

        for(int i=index;i<nums.length;i++){
            path.add(nums[i]);
            backtracking(nums,i+1);
            path.remove(nums[i]);
        }


    }
}
