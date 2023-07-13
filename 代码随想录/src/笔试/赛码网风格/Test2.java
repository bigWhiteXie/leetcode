package 笔试.赛码网风格;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test2 {
    /**
     * 输入包含多组数据，每组数据两行。
     * 每组数据的第一行包含4个整数，n,m,a,b，空格隔开。这里不保证a和b的大小关系。
     * 接下来一行m个数，空格隔开，代表烤好的蛋糕重量
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int m = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();


            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<m;i++){
                list.add(in.nextInt());
            }

            System.out.println(judge(list,a,b,n));
        }
    }


    public static String judge(List<Integer> weights, int a, int b,int n){
        Integer[] ins = new Integer[1];
        Integer[] arr = weights.toArray(ins);
        int bigger = a > b? a : b;
        int smaller = bigger == a ? b:a;
        Arrays.sort(arr);
        int len = arr.length;
        if(arr[0] < smaller || arr[len-1] > bigger){
            return "NO";
        }
        int count = 0;

        if(arr[0] == smaller){
            count++;
        }

        if(arr[len-1] == bigger){
            count++;
        }

        if(n-len+count >= 2){
            return "YES";
        }else{
            return "NO";
        }
    }
}
