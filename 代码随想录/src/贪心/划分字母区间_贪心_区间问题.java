package 贪心;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class 划分字母区间_贪心_区间问题 {
    /**
     * 找到每个字母的起始位置和终止位置
     * 将区间根据起始位置进行排序
     * 遍历区间，若两区间相交则找出最大的右边界
     * 若不相交则该point的起始位置 - left = 分片长度
     * @param s
     * @return
     */
    public static List<Integer> partitionLabels(String s) {
        HashMap<Character, Point> map = new HashMap<>();

        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(!map.containsKey(c)){
                Point point = new Point();
                point.begin = i;
                map.put(c,point);
            }else{
                Point point = map.get(c);
                point.end = i;
            }
        }
        Collection<Point> points = map.values();
        ArrayList<Point> list = new ArrayList<>(points);
        list.sort((a,b)->{
            return a.begin - b.begin == 0 ? a.end - b.end : a.begin - b.begin;
        });

        ArrayList<Integer> res = new ArrayList<>();
        int right = list.get(0).end;
        int left = 0;
        for (int i = 1; i < list.size(); i++) {
            Point point = list.get(i);
            if(point.begin > right){
                res.add(point.begin-left);
                right = point.end !=0 ?point.end: point.begin;
                left = point.begin;
            }else{
                right = Math.max(right,point.end);
            }
        }
        res.add(s.length() - left);
        return res;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        partitionLabels(s);
    }

    static class Point{
        int begin;
        int end;

    }
}
