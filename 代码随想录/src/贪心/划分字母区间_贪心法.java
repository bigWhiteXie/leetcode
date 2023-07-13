package 贪心;

import java.util.*;

public class 划分字母区间_贪心法 {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        Map<Character,Integer> global_map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            global_map.put(s.charAt(i),global_map.getOrDefault(s.charAt(i),0)+1);
        }

        int slow = 0,fast = 0;
        HashMap<Character, Integer> cur_map = new HashMap<>();
        while(fast < s.length()){
            cur_map.put(s.charAt(fast),cur_map.getOrDefault(s.charAt(fast),0)+1);
            if(next(cur_map,global_map)){
                res.add(fast-slow+1);
                slow = fast+1;
                fast = slow;
                cur_map.clear();
                continue;
            }
            fast++;
        }

        return res;

    }

    public boolean next(Map<Character,Integer> cur_map,Map<Character,Integer> global_map){
        Set<Character> keySet = cur_map.keySet();
        for (Character key : keySet) {
            if(!cur_map.get(key).equals(global_map.get(key))){
                return false;
            }
        }
        return true;
    }
}
