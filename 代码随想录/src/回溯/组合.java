package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 组合 {
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n,k,1);
        return res;
    }

    public void backTracking(int n,int k, int start){
        if(path.size() == k){
            res.add(path);
            return;
        }

        for(int i=start; i<=n - (k-path.size())+1;i++){
            path.add(i);
            backTracking(n,k,i+1);
            path.remove(path.size()-1);
        }
    }
}
