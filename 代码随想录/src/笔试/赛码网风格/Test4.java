package 笔试.赛码网风格;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        // 注意 hasNext 和 hasNextLine 的区别
        while(num-->0){
            int n = in.nextInt();
            int[] cakes = new int[n];
            for(int i=0; i<n; i++){
                cakes[i] = in.nextInt();
            }

        }
    }
}
