package class04;

public class Code01_CountOfRangeSum {
    /**
     * 给定一个数组和一个范围，求有多少个子序列它们内部元素相加在这个范围中
     * 例子：[-3,-1,1,3,4]  range[-1,1]
     * 对应子序列 [-3,-1,1,3] [-1] [-1,1] [1]
     * @param arr
     * @return
     */
    public static int countOfRangeSum(int[] arr, int[] range){
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        //1.求出前缀和
        //前缀和数组用途： sum(i,j) = sum(j) - sum(i-1)
        for(int i=1; i<sum.length; i++){
            sum[i] = sum[i-1] + arr[i];
        }

        //2.对前缀和进行归并排序
         return process(sum, 0, arr.length - 1, range);

    }

    public static int process(int[] sum,int l, int r,int[] range){
        int lower = range[0], upper = range[1];
        if(l == r){
            if(sum[l] >= lower && sum[r] <= upper){
                return 1;
            }else{
                return 0;
            }
        }

        int m = l + ((r - l) >> 1);
        int leftCount = process(sum, l, m, range);
        int rightCount = process(sum, m + 1, r, range);

        int count = merge(sum, l, m, r, range);
        return count + leftCount + rightCount;
    }

    private static int merge(int[] sum, int l, int m, int r, int[] range) {
        int lower = range[0], upper = range[1];
        int window_L = l, window_R = l;
        int count = 0;

        //误区代码
//        for(int i=m+1; i<=r; i++){
//            int target_l = sum[i] - upper, target_r = sum[i] - lower;
//            //误区：遍历左边序列，每迭代一次就会重新计算一遍count，每个sum[i]对应的count被重复计算m-l+1遍
//            for(int j=l; j<= m; j++){
//                while(window_L <= m && sum[window_L] < target_l){
//                    window_L++;
//                }
//
//                while(window_R <= m && sum[window_R] <= target_r){
//                    window_R++;
//                }
//
//                count += (window_R -window_L);
//            }
//
//        }


        for(int i=m+1; i<=r; i++){
            //已知sum[i],求sum[j]符合 (sum[i] - sum[j]) ∈ [lower,upper]
            //推导 sum[j] ∈ [sum[i] - upper, sum[i] - lower]
            int target_l = sum[i] - upper, target_r = sum[i] - lower;

            while(window_L <= m && sum[window_L] < target_l){
                window_L++;
            }
            while(window_R <= m && sum[window_R] <= target_r){
                window_R++;
            }

             count += (window_R -window_L);

        }

        int[] help = new int[r - l + 1];
        int begin = 0;
        int left = l, right = m+1;
        while(left <= m && right <= r){
            if(sum[left] < sum[right]){
                help[begin++] = sum[left++];
            }else{
                help[begin++] = sum[right++];
            }
        }

        while(left<=m){
            help[begin++] = sum[left++];
        }

        while (right <= r){
            help[begin++] = sum[right++];
        }

        for(int k=0; k<help.length; k++){
            sum[l+k] = help[k];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ints = {-3, -1, 1, 3, 4};
        System.out.println(countOfRangeSum(ints, new int[]{-1, 1}));
    }
}
