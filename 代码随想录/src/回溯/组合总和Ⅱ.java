package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class 组合总和Ⅱ {
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> path = new ArrayList<>();
    HashSet<Integer> used = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates,target,0,0);
        return res;
    }

    public void backTracking(int[] candiates,int target,int index,int sum){
        if(sum > target){
            return;
        }
        if(sum == target || index == candiates.length){
            if(sum == target){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for(int i=index; i<candiates.length; i++){

            if(i>index && candiates[i] == candiates[i-1] && used.contains(candiates[i])){
                continue;
            }
            path.add(candiates[i]);
            used.add(candiates[i]);
            backTracking(candiates, target, i + 1, sum + candiates[i]);
            path.remove(path.size() - 1);
            used.remove(candiates[i]);
        }

    }
}
