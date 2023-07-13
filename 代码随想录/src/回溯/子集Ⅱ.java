package 回溯;

import java.util.*;

//核心思想：此题依然是数层去重，若前面有相等的元素，意味着它已经包含了该值对应的所有集合
//例：[1,2,2,3,3]，当我们遍历到第一个2时我们会获得子集：[2] [2,2] [2,2,3] [2,2,3,3] 再到第二个2时，它得到的所有子集第一个2已经全部获取过因此不必再用
public class 子集Ⅱ {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    private Set<Integer> used = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums,0);
        return res;
    }

    public void backtracking(int[] nums, int index){
        ArrayList<Integer> item = new ArrayList<>(path);
        res.add(item);
        if(index == nums.length){
            return;
        }

        for(int i=index;i<nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1] && !used.contains(nums[i])){
                continue;
            }
            used.add(nums[i]);
            path.add(nums[i]);
            backtracking(nums,i+1);
            path.remove(path.size()-1);
            used.remove(nums[i]);

        }
    }
}
