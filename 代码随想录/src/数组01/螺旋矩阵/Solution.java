package 数组01.螺旋矩阵;

/**
 * 螺旋矩阵
 * 注意以下几个点：
 *  变量startx、starty控制住，每遍历一圈它两+1
 *  每次遍历都是左闭右开，用n-offset表示边界，offset初始为1，每遍历一圈+1，这样能够保证每一圈越来越小
 *
 */
class Solution {
    public int[][] generateMatrix(int n) {
        int loop = n / 2;
        int offset = 1;
        int startx = 0, starty = 0;
        int[][] arr = new int[n][n];
        int cur = 1;
        while(loop-->0){
            int i,j;

            //从左到右
            for(j=startx; j<n-offset;j++){
                arr[starty][j] = cur;
                cur++;
            }
            
            //从上到下
            for(i=starty; i<n-offset;i++){
                arr[i][j] = cur;
                cur++;
            }

            //从右到左
            for(;j>startx;j--){
                arr[i][j] = cur;
                cur++;
            }

            //从下到上
            for(;i>starty;i--){
                arr[i][j] = cur;
                cur++;
            }

            startx++;
            starty++;
            offset++;

        }

        if(n%2 == 1){
            arr[startx][starty] = cur;
        }

        return arr;
    }
}