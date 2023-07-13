package class02;

public class 递归方式求数组中的最大值 {
    public static int getMax(int[] arr,int start,int end){
        if(start == end){
            return arr[start];
        }


        int middle = start + ((end -start)>>1);

        //由于middle的取值为向下取整，所以采用[start,middle] 和[middle+1,end]区间
        //若采用[start,middle-1] [middle,end]区间，会出现start > middle-1的情况
        int r = getMax(arr,middle+1,end);
        int l = getMax(arr,start,middle);
        int max = r > l ? r:l;
        return max;
    }

    public static void main(String[] args) {
        int[] ints = {5, 1, 3, 2, 6, 4, 9};
        int max = getMax(ints, 0, ints.length - 1);
        System.out.println(max);
    }
}
