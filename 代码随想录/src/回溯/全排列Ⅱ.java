package 回溯;

import java.util.*;

public class 全排列Ⅱ {
    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int[] used = new int[nums.length];
        Arrays.sort(nums);
        backtracking(nums,used);
        return res;
    }

    public static void backtracking(int[] nums,int[] used){
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<nums.length;i++){
            //同层的相同元素已经被访问过
            if(i>0 && nums[i] == nums[i-1] && used[i-1] == 0){
                continue;
            }

            if(used[i] == 1){
                continue;
            }

            path.add(nums[i]);

            used[i] = 1;
            backtracking(nums,used);
            path.remove(path.size()-1);

            used[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};

    }
}
