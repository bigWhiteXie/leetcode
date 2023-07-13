package 贪心;

import java.util.ArrayList;

public class 单调递增的数字 {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] array = s.toCharArray();

        int start = -1;
        for(int i=array.length-1;i>0;i--){
            if(array[i-1] > array[i]){
                array[i-1]--;
                start = i;
            }
        }

        if(start != -1){
            for(int i = start;i<array.length;i++){
                array[i] = '9';
            }
        }

        return Integer.parseInt(new String(array));
    }
}
