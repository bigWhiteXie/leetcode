package 螺旋矩阵;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 螺旋矩阵 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        //每圈的起始坐标，第一圈(0,0) 第二圈(1,1)....
        int start_x = 0, start_y = 0;
        //偏移量，每走一圈偏移量+2
        int offset = 1;
        if(matrix.length == 0){
            return list;
        }

        int bottom = matrix.length - 1, right = matrix[0].length - 1;
        //极端情况剔除


        while(start_x <= bottom && start_y <=right){
            //对上侧边做添加
            for(int i=start_y; i<=right; i++){
                list.add(matrix[start_x][i]);
            }
            //对右侧边
            for(int i=start_x+1; i<=bottom;i++){
                list.add(matrix[i][right]);
            }

            if(start_x < bottom && start_y < right){
                //下侧边
                for(int i=right-1; i>=start_y; i--){
                    list.add(matrix[bottom][i]);
                }
                //左侧边
                for(int i=bottom-1; i>=start_x+1; i--){
                    list.add(matrix[i][start_y]);
                }
            }
            start_x++;
            start_y++;
            right--;
            bottom--;
        }


        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> order = spiralOrder(matrix);
        System.out.println(Arrays.toString(order.toArray()));
    }
}
