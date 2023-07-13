package class19;

import java.util.Scanner;

public class FreeFood {

    public static int maxHappy(int[] a, int[] b,int n,int choice){
        if(n == 0){
            return 0;
        }
        int c = maxHappy(a,b,n-1,0);
        int a1 = a[a.length - n] + maxHappy(a,b,n-1,1);
        int b1 = b[b.length - n] + maxHappy(a,b,n-1,2);
        //上一天没吃零食
        if(choice == 0){
            return Math.max(Math.max(a1,b1),c);
        }

        //上一天吃的a零食
        if(choice == 1){
            return Math.max(c,b1);
        }else {
            return Math.max(c,a1);
        }

    }

    public static int dp(int[] a, int[] b,int n){
        int[][] dp = new int[n+1][3];

        //初始状态choice=0，而i = 1,val = 0
        for(int i=1;i<=5; i++){
            int c = Math.max(dp[i-1][1], dp[i-1][2]); //不吃水果
            int a1 = a[i-1] + dp[i-1][2]; //选择吃a水果
            int b1 = b[i-1] + dp[i-1][1]; //选择吃b水果

            dp[i][0] = c; //选择不吃水果
            dp[i][1] = Math.max(a1,c);  //选择吃a水果
            dp[i][2] = Math.max(b1,c); //选择吃b水果

        }

        return Math.max(Math.max(dp[5][1],dp[5][2]),dp[5][0]);

    }

    public static void main(String[] args) {

        for(int i=0; i<1000; i++){
            int[] a = generator(5),b = generator(5);
            int happy = maxHappy(a, b, 5, 0);
            int dp = dp(a, b, 5);
            if(dp != happy){
                System.out.println("err! happy = " + happy +",dp = " + dp);
                printArr(a);
                printArr(b);

            }
        }

        System.out.println("nice");

    }

    public static int[] generator(int n){
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public static  void printArr(int[] a){
        for (int i:a){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
