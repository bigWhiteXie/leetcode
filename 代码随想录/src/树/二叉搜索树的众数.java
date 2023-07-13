package 树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 二叉搜索树的众数 {
    static HashMap<Integer, Integer> tab = new HashMap<>();

    public static int[] findMode(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while(cur != null || !stack.isEmpty()){
            if(cur !=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode pop = stack.pop();
                Integer val = tab.getOrDefault(pop.val, 0);
                tab.put(pop.val,val+1);
                cur = pop.right;
            }
        }

        Integer max = null;
        for (Map.Entry<Integer, Integer> entry : tab.entrySet()) {
            if(max == null){
                max = entry.getKey();
            }else{
                max = tab.get(entry.getKey()) >= tab.get(max) ? entry.getKey() : max;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer key : tab.keySet()) {
            if(tab.get(key) == tab.get(max)){
                list.add(key);
            }
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        tab.clear();
        return nums;
    }
}
