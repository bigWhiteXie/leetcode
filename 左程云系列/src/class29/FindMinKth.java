package class29;


import java.util.Arrays;

/**
 * 给定一个数组，找到第k小的数
 */
public class FindMinKth {

    public static int findMinK(int[] arr, int k){
        k =  k - 1;
        int l = 0, r = arr.length-1;
        while(l<r){
            int pivot = arr[(int)((r-l+1) * Math.random())];
            int[] pos = partition(arr,pivot,l,r);
            if(k<=pos[1] && k >= pos[0]){
                return arr[k];
            }

            if(k < pos[0]){
                r = pos[0]- 1;
            }

            if(k > pos[1]){
                l = pos[1] + 1;
            }
        }
        return arr[l];
    }

    public static int[] partition(int[] arr,int pivot, int l, int r){
        int[] pos = new int[2];
        int cur = l;
        int left = l-1,right = r+1;
        while(cur < right){
            if (arr[cur] < pivot){
                swap(arr,cur++,++left);
            }else if(arr[cur] > pivot){
                swap(arr,cur,--right);
            }else {
                cur++;
            }
        }
        pos[0] = left+1;
        pos[1] = right-1;
        return pos;
    }

    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,2,4,3,7,10};
        int k = 4;
        int minK = findMinK(arr, k);
        Arrays.sort(arr);
        System.out.println(minK);
        System.out.println(arr[k - 1]);
    }

}
