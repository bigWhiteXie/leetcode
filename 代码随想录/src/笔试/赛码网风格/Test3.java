package 笔试.赛码网风格;

import java.util.Arrays;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int s = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n;i++){
                nums[i] = in.nextInt();
            }

            System.out.println(successNum(nums,s));
        }
    }



    public static int successNum(int[] nums,int s){
        Arrays.sort(nums);
        int begin = nums.length - s;
        while(nums[begin] <= 0){
            begin++;
            s--;
        }

        return s;
    }
}
