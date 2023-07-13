package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test20 {
    public static List<Integer> mergeAndOrder(List<Integer> l1,List<Integer> l2){
        HashSet<Integer> set = new HashSet<>();
        for (Integer item : l1) {
            set.add(item);
        }

        for (Integer item : l2) {
            set.add(item);
        }

        ArrayList<Integer> list = new ArrayList<>(set);
        list.sort((a,b)->a-b);
        return list;
    }

    public static List<Integer> judge(int begin,int end,int num){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=begin; i<=end;i++){
            if(i > 0 && i % num == 0){
                list.add(i);
                continue;
            }
            int res = i;
            while(res > 0){
                int last = res % 10;
                if(last == num){
                    list.add(i);
                    break;
                }
                res /= 10;

            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = judge(0, 20, 7);
        System.out.println(list);
    }
}
