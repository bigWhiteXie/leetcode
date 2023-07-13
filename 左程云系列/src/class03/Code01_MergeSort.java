package class03;

public class Code01_MergeSort {
    public static void mergeSort(int[] arr){

        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int l,  int r) {
        if(l == r){
            return;
        }

        int m = l + ((r-l) >> 1);
        process(arr,l,m);
        process(arr,m+1,r);

        merge(arr,l,m,r);

    }

    private static void merge(int[] arr, int l, int m, int r) {
        if(l == r){
            return;
        }
        int[] help = new int[r-l+1];
        int index = 0;
        int i = l,j = m+1;
        while(i<=m && j<=r){
            if(arr[i]<=arr[j]){
                help[index++] = arr[i++];
            }else{
                help[index++] = arr[j++];
            }
        }

        while(i<=m){
            help[index++] = arr[i++];
        }

        while(j<=r){
            help[index++] = arr[j++];
        }

        for(int k=0; k<help.length; k++){
            arr[l+k] = help[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,1,3,4,2,5,6};
        mergeSort(arr);
        for(int i:arr){
            System.out.print(i + " ");
        }
    }
}
