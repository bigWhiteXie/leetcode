package 滑动窗口;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 最小覆盖子串 {
    public static String minWindow(String s, String t) {
        char[] sa = s.toCharArray(), ta = t.toCharArray();
        int[] ans = new int[2];
        int wr = 0, wl = 0, min = Integer.MAX_VALUE;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        Map<Character,Integer> map_t = new HashMap<Character,Integer>();

        for(int i=0; i<ta.length; i++){
            if(map_t.get(ta[i]) != null){
                map_t.put(ta[i],map_t.get(ta[i])+1);
            }else{
                map_t.put(ta[i],1);
            }
        }

        //窗口右侧往后遍历
        for(;wr<sa.length;wr++){
            //将最新的元素纳入窗口
            if(map.containsKey(sa[wr])){
                map.put(sa[wr],map.get(sa[wr])+1);
            }else{
                map.put(sa[wr],1);
            }
            //若满足条件，开始收缩
            while(includeStr(map,map_t)){
                //记录当前满足条件的状态
                if(min > wr - wl + 1){
                    min = wr - wl + 1;
                    ans[0] = wl;
                    ans[1] = wr;
                }

                //收缩窗口，更新数据
                if(map.get(sa[wl]) == 1){
                    map.remove(sa[wl]);
                }else{
                    map.put(sa[wl],map.get(sa[wl])-1);

                }
                wl++;
            }
            if(min > wr - wl){
                min = wr - wl + 2;
                ans[0] = wl - 1;
                ans[1] = wr;
            }
        }

        if(min == Integer.MAX_VALUE){
            return "";
        }

        return s.substring(ans[0],ans[1]+1);


    }

    public static boolean includeStr(Map<Character,Integer> map, Map<Character,Integer> map2){
        Set<Character> set = map2.keySet();
        for(Character key: set){
            if(map.getOrDefault(key,0) < map2.get(key)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(s.substring(1, 5));
        System.out.println(minWindow(s, t));
    }
}
