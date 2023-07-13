package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 */
public class 分割回文字符串 {
    private List<List<String>> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracking(s,0);
        return res;
    }

    public void backTracking(String s,int index){
        if(index == s.length()){
            res.add(new ArrayList<>(path));
        }

        for(int i=index; i<s.length();i++){
            String t = s.substring(index,i+1);
            if(judge(t) ){
                path.add(t);
                backTracking(s,index+1);
                path.remove(path.size()-1);
            }
        }
    }

    public boolean judge(String s){
        int i = 0, j = s.length()-1;
        while(i<j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }
}
