package 回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合 {
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();

    Map<Integer,String> map = new HashMap<>();
    {
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
    }
    public List<String> letterCombinations(String digits) {
        backtracking(digits,0);
        return res;
    }

    public void backtracking(String digits,int index){
        if(index == digits.length() || !map.containsKey(Integer.valueOf(String.valueOf(digits.charAt(index))))){
            if(index == digits.length()){
                res.add(path.toString());
            }
        }


        Integer key = Integer.valueOf(String.valueOf(digits.charAt(index)));
        String s = map.get(key);
        for(int i=0;i<s.length();i++){
            path.append(s.charAt(i));
            backtracking(digits,index+1);
            path.deleteCharAt(path.length()-1);
        }

    }
}
