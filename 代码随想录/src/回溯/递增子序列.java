package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 递增子序列 {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums,0);
        return res;
    }

    public void backtracking(int[] nums, int index){
        if(path.size() >=2){
            ArrayList<Integer> list = new ArrayList<>(path);
            res.add(list);
        }
        if(nums.length == index){
            return;
        }

        for(int i=index;i<nums.length; i++){
            if(path.size() == 0 || nums[i] >= path.get(path.size()-1)){
                path.add(nums[i]);
                backtracking(nums,i+1);
                path.remove(path.size()-1);
            }
        }
    }
}
