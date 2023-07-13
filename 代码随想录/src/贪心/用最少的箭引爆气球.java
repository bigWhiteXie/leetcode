package 贪心;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.Arrays;

public class 用最少的箭引爆气球 {
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)-> {
            if (a[0] > b[0]){
                return 1;
            }else{
                return -1;
            }
        });

        int count = 1;
        int right =  points[0][1];
        for(int i=1; i< points.length;i++){
            //若当前元素开始>右边界，说明需要额外的箭去射击
            if(points[i][0] > right){
                count++;
                right = points[i][1];
            }else {    //若当前元素开始<右边界，求它们的交集右边界
                right = Math.min(points[i][1],right);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(points));
    }
}
