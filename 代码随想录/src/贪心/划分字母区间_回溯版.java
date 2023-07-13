package 贪心;

import java.util.ArrayList;
import java.util.List;

public class 划分字母区间_回溯版 {
    private List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();


    public List<Integer> partitionLabels(String s) {
        StringBuilder builder = new StringBuilder(s);
        backtracking(builder,0);

        int max_index = 0;
        for(int i=1;i<res.size();i++){
            if(res.get(i).size() > res.get(max_index).size()){
                max_index = i;
            }
        }

        return res.get(max_index);
    }

    public void backtracking(StringBuilder s,int index){
        if(index == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        String prior = s.substring(0,index);
        for(int i=index; i<s.length();i++){
            String s1 = s.substring(index,i+1);
            if(!hasRepeat(prior,s1)){
                path.add(s1.length());
                backtracking(s,i+1);
                path.remove(path.size()-1);
            }
        }
    }

    public boolean hasRepeat(String s1,String s2){
        int[] used = new int[26];
        for(int i=0;i<s1.length();i++){
            used[s1.charAt(i) - 'a']++;
        }

        for(int i=0; i<s2.length();i++){
            if(used[s2.charAt(i) - 'a'] != 0){
                return true;
            }
        }

        return false;
    }
}
