package class01;

public class 局部最小值 {
    public static int binarySearch(int[] arr){
        if(arr[0] < arr[1]){
            return 0;
        }

        if(arr[arr.length-2] > arr[arr.length-1]){
            return arr.length - 1;
        }

        int left = 1, right = arr.length-2,middle = -1;
        while(left <= right){
            middle = left + (right - left) / 2;
            if(arr[middle] >= arr[middle - 1]){
                right = middle-1;
            }else if (arr[middle] >= arr[middle + 1]){
                left = middle + 1;
            }else {
                return middle;
            }
        }

        return middle;
    }

    public static void main(String[] args) {
        int[] ints = {1, -1, 4, 5, 7, 2, 4, 50};
        int i = binarySearch(ints);
        System.out.println(i);
    }
}
