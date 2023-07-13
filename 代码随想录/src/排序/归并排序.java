package 排序;

public class 归并排序 {
    public static void mergeSort(int[] arr){
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        if(l >= r){
            return;
        }

        int m = l + (r-l) / 2;
        //处理左边数组
        process(arr,l,m);

        //处理右边数组
        process(arr,m+1,r);

        //合并两边的处理结果
        merge(arr,l,m,r);

    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] res = new int[r - l + 1];

        int i=l,j=m+1;
        int index = 0;
        while(i<=m && j<=r){
            if(arr[i] < arr[j]){
                res[index++] = arr[i++];
            }else{
                res[index++] = arr[j++];
            }
        }

        while(i<=m){
            res[index++] = arr[i++];
        }

        while(j<=r){
            res[index++] = arr[j++];
        }

        for(int k=0;k<index;k++){
            arr[l+k] = res[k];
        }
    }


    public static void main(String[] args) {
        int[] arr = RandonmAccess.getRandomArr(10);
        RandonmAccess.printArr(arr);
        mergeSort(arr);
        RandonmAccess.printArr(arr);
    }
}
