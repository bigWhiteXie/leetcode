package 笔试.赛码网风格;

import java.util.Scanner;

/**
 * 求一个数组中满足元素累乘和元素逐个异或相等时的子数组个数
 * 例子：1 2 1
 * [1] [2] [1] [1 2 1] 4个
 */
public class Test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[][] tab = new int[n][n];
        int begin = 0,end = arr.length-1;
        int num = numberArr(arr,begin,end,tab);
        System.out.println(num);
    }

    private static int numberArr(int[] arr,int i,int j,int[][] tab) {
        if(i>j || tab[i][j]!=0){
            return 0;
       }

        tab[i][j] = 1;
        int ans = 0;
        int a = cj(i, j, arr);
        int b = yh(i, j, arr);
        if(a == b){
            ans++;
        }
        ans += numberArr(arr,i+1,j,tab);
        ans += numberArr(arr,i,j-1,tab);
        ans += numberArr(arr,i+1,j-1,tab);
        return ans;

    }
    private static int cj ( int i, int j, int[] arr){
        int ans = 1;
        while (i <= j) {
            ans *= arr[i++];
        }
        return ans;
    }
    private static int yh ( int i, int j, int[] arr){
        int ans = 0;
        while (i <= j) {
            ans ^= arr[i++];
        }
        return ans;
    }
}
