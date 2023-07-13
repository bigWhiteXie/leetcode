package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 数组本身不存在重复元素
 *
 * 题解：
 *  题目要求每个组合只能出现一次，但是序列为1-9，不存在重复元素，因此不用考虑对元素的去重
 *  题目要求组合中同一个数字不能重复出现，因此遍历规则为for(int i=index;i<=9;i++)
 *  递归出口：path.size = k 或 index>9
 */
public class 组合总和Ⅲ {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k,n,n,1);
        return res;
    }

    public void backtracking(int k, int n,int rest, int index){
        if(path.size() == k){
            if(rest == 0){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if(index > 9){
            return;
        }

        for(int i=index;i<=9;i++){
            if(rest>=i){
                path.add(i);
                backtracking(k,n,rest-i,i+1);
                path.remove(path.size()-1);
            }
        }
    }
}
