package 贪心;

import java.util.Arrays;

public class 无重叠区间 {
    /**
     * 核心思想：将问题转化为求重叠区间的个数
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->{
            return Integer.compare(a[0],b[0]);
        });
        int count = 0;
        int right = intervals[0][1];

        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] < right){
                count++;
                right = Math.min(intervals[i][1],right);
            }else{
                right = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] points = {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
        eraseOverlapIntervals(points);
    }
}
