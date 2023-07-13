package 贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 合并区间 {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int right = intervals[0][1];
        int left = intervals[0][0];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] <= right ){
                right = Math.max(right,intervals[i][1]);
            }else {
                res.add(new int[]{left,right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }

        res.add(new int[]{left,right});
        return res.toArray(new int[0][0]);
    }
}
