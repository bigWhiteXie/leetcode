package 笔试.赛码网风格;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 小美现在有一串彩带，假定每一厘米的彩带上都是一种色彩。
 *
 * 因为任务的需要，小美希望从彩带上截取一段，使得彩带中的颜色数量不超过K种。
 *
 * 显然，这样的截取方法可能非常多。于是小美决定尽量长地截取一段。
 *
 * 你的任务是帮助小美截取尽量长的一段，使得这段彩带上不同的色彩数量不超过K种。
 */
public class Test6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];

        for(int i=0;i<n; i++){
            arr[i] = in.nextInt();
        }

        int len = maxSpilt(arr,k);
        System.out.println(len);

    }

    private static int maxSpilt(int[] arr, int k) {
        HashMap<Integer, Integer> table = new HashMap<>();
        int max = 0, slow = 0;

        for(int i=0; i<arr.length;i++){

            while(table.keySet().size() > k){
                max = Math.max(max,i - slow - 1);
                Integer sv = table.get(arr[slow]);
                if(sv == 1){
                    table.remove(arr[slow]);
                }else{
                    table.put(arr[slow],sv-1);
                }
                slow++;
            }

            Integer times = table.getOrDefault(arr[i], 0);
            table.put(arr[i],times+1);

        }
        return max;
    }

}
