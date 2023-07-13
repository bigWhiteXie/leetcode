package 螺旋矩阵;

import java.util.ArrayList;
import java.util.Arrays;

public class 顺时针打印矩阵 {
    public static int[] spiralOrder(int[][] matrix) {

        int left = 0,top = 0;
        if(matrix.length == 0){
            return new int[]{};
        }
        int right = matrix[0].length - 1, bottom = matrix.length - 1;
        int[] arr = new int[(bottom+1) * (right+1)];
        int count = 0;
        while(left <= right && top <= bottom){
            for(int i=left; i<=right; i++){
                arr[count++] = matrix[top][i];
            }

            for(int i=top+1; i<=bottom; i++){
                arr[count++] = matrix[i][right];
            }

            if(left < right && top < bottom){
                for(int i=right-1; i >= left; i--){
                    arr[count++] = matrix[bottom][i];
                }

                for(int i=bottom-1; i>top; i--){
                    arr[count++] = matrix[i][left];
                }
            }
            left++;
            top++;
            right--;
            bottom--;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(Arrays.toString(spiralOrder(matrix)));
    }
}
