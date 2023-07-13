package 回溯;

import java.util.ArrayList;
import java.util.List;

public class 复原ip地址 {
    private static List<String> res = new ArrayList<>();
    private static List<String> path = new ArrayList<>();
    public static List<String> restoreIpAddresses(String s) {
        backTracking(s,0,0);
        return res;
    }

    public static void backTracking(String s, int index,int time) {
        //当index超出数组范围或者递归次数超过3时结束递归(ip地址最多分割3次)
        if (index >= s.length() || time == 4) {
            //当字符串完整遍历且分割次数=3时符合条件，将此时的结果添加到结果集中
            if (index >= s.length() && time == 4) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < path.size(); i++) {
                    builder.append(path.get(i));
                    if (i != path.size() - 1) {
                        builder.append(".");
                    }
                }
                res.add(builder.toString());

            }
            return;
        }
        //若首字母为'0',直接分割
        if (s.charAt(index) == '0') {
            path.add("0");
            backTracking(s,index+1,time+1);
            path.remove(path.size()-1);
        } else {
            int num = 0;
            //计算s(index,i)的值是否在0-255之间，是则进行分割
            for (int i = index; i < index + 3; i++) {
                num = num * 10 + Integer.valueOf(String.valueOf(s.charAt(i)));

                if (num > 0 && num <= 255) {
                    path.add(s.substring(index, i + 1));
                    backTracking(s, i + 1, time + 1);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        restoreIpAddresses(s);
    }
}
