package 排序;

public class 冒泡排序 {
    public static void bubbleSort(int[] arr){
        for(int i=arr.length-1;i>=0;i--){
            for(int j=0;j<i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = RandonmAccess.getRandomArr(10);
        RandonmAccess.printArr(arr);
        bubbleSort(arr);

        RandonmAccess.printArr(arr);
    }
}
