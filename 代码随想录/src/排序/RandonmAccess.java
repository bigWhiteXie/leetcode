package 排序;

public class RandonmAccess {
    final static int RANGE = 10000;

    public static int[] getRandomArr(int size){
        int[] arr = new int[size];

        for(int i=0; i<size; i++){
            arr[i] = (int ) (Math.random()*RANGE) + 1;
        }

        return arr;
    }


    public static void printArr(int[] arr){
        for(int i:arr){
            System.out.print(i +" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            int[] arr = getRandomArr(10);
            printArr(arr);
        }
    }
}
