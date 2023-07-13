package class07;

import java.util.ArrayList;
import java.util.List;

public class Code02_BucktSort {
    /**
     * 1.建立10个桶
     * 2.求出最大值的位数
     * @param arr
     */
    public static void bucketSort(int[] arr){
        List<Integer>[] buckts = new ArrayList[10];
        for (int i = 0; i < buckts.length; i++) {
            buckts[i] = new ArrayList<>();
        }

        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if(i > max){
                max = i;
            }
        }

        int position = position(max);
        for(int i=1; i<=position; i++){
            //按照位数，将数放入桶里
            for (int j = 0; j < arr.length; j++) {
                int num = positionNum(arr[j],i);
                buckts[num].add(arr[j]);
            }
            //从桶中取出数
            int k = 0;
            for (List<Integer> buckt : buckts) {
                while (!buckt.isEmpty()){
                    arr[k++] = buckt.remove(0);
                }
            }

        }




    }

    public static int positionNum(int data,int index) {
        return ((int) (data/Math.pow(10,index-1)))%10;

    }

    public static int position(int max) {
        int res = 1;
        while(max/10 != 0){
            max = max / 10;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = {5, 3, 235, 123, 789, 96, 141};
        bucketSort(ints);
        for (int i : ints) {
            System.out.print(i+" ");
        }
    }
}
