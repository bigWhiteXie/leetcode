package class03;

/**
 * 逆序对问题
 * [6 3 2 1 6 7]
 * 逆序对：(6,3)  (6,2) (6,1) (3,2) (3,1) (2,1)
 * 求数组中逆序对的数量
 */
public class ReservedPair {
    /**
     * 将arr中两个有序数部分并成一个有序部分
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    public static int merge(int[] arr, int L, int M, int R){
//        int p1 = L, p2 = M+1;
//        int[] help = new int[R-L+1];
//        int i = 0;
//        int res = 0;
//        int q1 = M,q2 = R;
//        //此时认为[L,M]有序  [M+1,R]有序，利用两个有序列表求逆序对
//        while(q1>=L && q2 >= M+1){
//            if (arr[q1] > arr[q2]){
//                res += (q2 - M);
//                q1--;
//            }else{
//                q2--;
//            }
//        }
//
//
//        while (p1 <= M && p2 <= R){
//            if (arr[p1] < arr[p2]){
//                help[i++] = arr[p1++];
//            }else{
//                help[i++] = arr[p2++];
//            }
//        }
//
//        while(p1 <= M){
//            help[i++] = arr[p1++];
//        }
//
//        while(p2 <= R){
//            help[i++] = arr[p2++];
//        }
//
//        for(int j=0; j<help.length; j++){
//            arr[L+j] = help[j];
//        }
//
//        return res;
        int res = 0;
        int p1 = M, p2 = R;
        while(p1>=L && p2>=M+1){
            if (arr[p1] > arr[p2]){
                res += p2 - M;
                p1--;
            }else {
                p2--;
            }
        }

        int[] help = new int[R - L + 1];
        int q1 = L, q2 = M + 1;
        int index = 0;
        while (q1 <= M && q2 <= R) {
            if (arr[q1] < arr[q2]) {
                help[index++] = arr[q1++];
            } else {
                help[index++] = arr[q2++];
            }
        }

        while (q1 <= M) {
            help[index++] = arr[q1++];
        }

        while (q2 <= R) {
            help[index++] = arr[q2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }

    public static int precess(int[] arr,int L, int R){
        if(L >= R){
            return 0;
        }
        int middle = L + ((R - L) >> 1);
        int leftRes = precess(arr, L, middle);
        int rightRes = precess(arr, middle + 1, R);

        int ans = merge(arr, L, middle, R);
        return leftRes + rightRes + ans;
    }

    public static int reverPairNumber(int[] arr){
        int ans = precess(arr, 0, arr.length - 1);
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
