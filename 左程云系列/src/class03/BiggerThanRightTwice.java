package class03;

public class BiggerThanRightTwice {
    /**
     * 将arr中两个有序数部分并成一个有序部分
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    public static int merge(int[] arr, int L, int M, int R){
        int p1 = L, p2 = M+1;
        int[] help = new int[R-L+1];
        int i = 0;
        int res = 0;
        int q1 = M,q2 = R;
        while(q1>=L && q2 >= M+1){
            if(arr[q1] > arr[q2] * 2){
                res += (q2 - M);
                q1--;
            }else {
                q2--;
            }
        }


        while (p1 <= M && p2 <= R){
            if (arr[p1] < arr[p2]){
                help[i++] = arr[p1++];
            }else{
                help[i++] = arr[p2++];
            }
        }

        while(p1 <= M){
            help[i++] = arr[p1++];
        }

        while(p2 <= R){
            help[i++] = arr[p2++];
        }

        for(int j=0; j<help.length; j++){
            arr[L+j] = help[j];
        }

        return res;
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

    public static int biggerThenRightTwice(int[] arr){
        int ans = precess(arr, 0, arr.length - 1);
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = {4,1};
        System.out.println(biggerThenRightTwice(ints));
    }

}
