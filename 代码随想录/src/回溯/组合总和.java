package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 组合总和 {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates,target,0);
        return res;
    }

    public void backtracking(int[] candidates,int target,int index){
        if(index == candidates.length || target == 0){
            if(target == 0){
                res.add(new ArrayList<>(path));
            }
            return;
        }

        //i从index开始可以保证不出现重复组合
        for(int i=index; i<candidates.length ; i++){
            int tmp = target;
            int times = 1;
            while(candidates[i]*times<=target){
                tmp -=  candidates[i];
                for(int j=0;j<times;j++) {
                    path.add(candidates[i]);
                }
                times++;
                backtracking(candidates,tmp,i+1);
                for(int j=0;j<times;j++){
                    path.remove(path.size()-1);
                }
            }
        }
    }
}
