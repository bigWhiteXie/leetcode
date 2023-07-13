package 排序;

import java.util.concurrent.RecursiveTask;

public class 快排 {
    public static void quickSort(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int l = L-1, r = R+1;
        int index = L;
        int temp = arr[L];
        while (index < r){
            if(arr[index] < temp){
                swap(arr,index++,++l);
            }else if(arr[index] > temp){
                swap(arr,index,--r);
            }else{
                index++;
            }
        }

        quickSort(arr,L,l);
        quickSort(arr,r,R);
    }


    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = RandonmAccess.getRandomArr(10);

        quickSort(arr,0,arr.length-1);
        RandonmAccess.printArr(arr);
    }
}
