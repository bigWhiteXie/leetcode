package 回溯;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 该题和之前题目最大的区别：该题只求出一个最优解，而之前题目需要求所有解
 * 因此回溯算法中若满足条件则返回true，否则返回false
 * 解法：
 *      将所有机票按照(from,to)联合索引方式进行排序
 *      used[]:表示使用过的机票索引
 *      success[]：表示成功使用过的机票索引
 */
public class 重新安排行程 {
    List<String> path = new ArrayList<>();
    int[] used;
    int[] success;
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(new TicketComp());
        used = new int[tickets.size()];
        success = new int[tickets.size()];
        backtracking(tickets,"JFK",-1);
        return path;
    }

    public boolean backtracking(List<List<String>> tickets,String from,int prev){
        if(path.size() == tickets.size()){
            path.add(from);
            return true;
        }

        for(int i=0; i<tickets.size(); i++){
            List<String> ticket = tickets.get(i);
            if(from.equals(ticket.get(0))){
                if(i>0 && tickets.get(i-1).get(0).equals(from) && used[i-1] == 0 && success[i-1] == 1){
                    continue;
                }

                if(used[i] == 1){
                    continue;
                }
                if(prev != -1){
                    success[prev] = 1;
                }
                path.add(ticket.get(0));
                used[i] = 1;
                if(backtracking(tickets,ticket.get(1),i)){
                    return true;
                }
                path.remove(path.size()-1);
                used[i] = 0;
                if (prev != -1) {
                    success[prev] = 0;
                }

            }
        }
        return false;
    }

    static class TicketComp implements Comparator<List<String>>{
        @Override
        public int compare(List<String> o1, List<String> o2) {
            int compare = o1.get(0).compareTo(o2.get(0));
            return  compare == 0 ?o1.get(1).compareTo(o2.get(1)):compare;
        }
    }

}
