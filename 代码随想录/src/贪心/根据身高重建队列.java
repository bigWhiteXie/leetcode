package 贪心;

import java.time.Period;
import java.util.Arrays;
import java.util.LinkedList;

public class 根据身高重建队列 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            int r1 = b[0] - a[0];
            if(r1 == 0){
                return a[1] - b[1];
            }
            return r1;
        });

        LinkedList<int[]> res = new LinkedList<>();

        for (int[] person : people) {
            if(person[1] == res.size()){
                res.add(person);
            }else{
                res.add(person[1],person);
            }
        }
        int[][] out = res.toArray(new int[0][0]);
        return out;
    }
}
