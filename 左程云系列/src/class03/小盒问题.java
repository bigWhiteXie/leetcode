package class03;

/**求每个数左边比它小的数都累加起来
 * [6 3 2 1 6 7]
 *  0 0 0 0 6 18
 *  思路：转化为求每个数右边比它大的数有几个，代表这个数将会被累加几次
 *        用归并排序的过程
 */
public class 小盒问题 {
    /**
     * 将arr中两个有序数部分并成一个有序部分
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    public static int merge(int[] arr, int L, int M, int R){
       int[] help = new int[R - L + 1];
       int s1 = L, s2 = M + 1;
       int i1 = L, i2 = M+1;
       int ans = 0,j = M+1;
//       for(int i = L; i <= M; i++ ){
//           while (j <= R && arr[i] >= arr[j]){
//               j++;
//           }
//           if(j <= R) {
//               ans += arr[i] * (R - j + 1);
//           }else {
//               break;
//           }
//       }
        while(i1<=M && i2<=R){
            if (arr[i1] < arr[i2]) {
                ans += (R - i2 + 1) * arr[i1];
                i1++;
            }else {
                i2++;
            }
        }


       int i = 0;
       while(s1 <= M && s2 <= R){
           if(arr[s1] <= arr[s2]){
               help[i++] = arr[s1++];
           }else {
               help[i++] = arr[s2++];
           }
       }

       while(s1 <= M){
           help[i++] = arr[s1++];
       }

       while (s2 <= R){
           help[i++] = arr[s2++];
       }

        for (int i3 = 0; i3 < help.length; i3++) {
            arr[L+i3] = help[i3];
        }

        return ans;
    }

    public static int precess(int[] arr,int L, int R){
        if(L == R){
            return 0;
        }
        int middle = L + ((R - L) >> 1);
        int leftRes = precess(arr, L, middle);
        int rightRes = precess(arr, middle + 1, R);

        int ans = merge(arr, L, middle, R);
        return leftRes + rightRes + ans;
    }

    public static int leftMinSum(int[] arr){
        int ans = precess(arr, 0, arr.length - 1);
        return ans;
    }
    public static int comparator(int[] arr){
        int ans = 0;
        for(int i=1; i<arr.length; i++){
            for(int j=0; j<i; j++){
                if(arr[j] < arr[i]){
                    ans += arr[j];
                }
            }
        }
        return ans;
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

    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5,7,6};  //1+3+6+10+15+15
        int[] ints1 = copyArray(ints);
        System.out.println(leftMinSum(ints));
        System.out.println(comparator(ints1));
        for(int i:ints){
            System.out.print(i + " ");
        }
    }
}
