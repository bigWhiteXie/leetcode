package 排序;

public class 直接插入排序 {
    public static void insertSort(int[] arr){
        //初始右边界
        int right = 0;
        for(int i=1;i<arr.length; i++){
            int temp = arr[i];
            int j = right;
            while(j>=0 && temp < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            right++;
            arr[j+1] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = RandonmAccess.getRandomArr(10);
        RandonmAccess.printArr(arr);

        insertSort(arr);
        RandonmAccess.printArr(arr);
    }
}
